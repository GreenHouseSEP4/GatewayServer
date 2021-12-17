USE [TestDW]
GO


------------------------------------------------------------Populate Device Dimension---------------------------------------------------------------
TRUNCATE TABLE [stage].[Dim_Device]
INSERT INTO [stage].[Dim_Device]
           ([Eui]
           ,[DeviceLocation]
           ,[MinThresholdTemp]
           ,[MaxThresholdTemp]
           ,[TargetTemp]
           ,[MinThresholdHum]
           ,[MaxThresholdHum]
           ,[TargetHum]
           ,[MinThresholdLight]
           ,[MaxThresholdLight]
           ,[TargetLight]
           ,[MinThresholdCo2]
           ,[MaxThresholdCo2]
           ,[TargetCo2])
SELECT [Eui], 
	   [location],
	   [min_Temperature],
       [max_Temperature],
       [target_Temperature],
	   [min_Humidity],
       [max_Humidity],
       [target_Humidity],
	   [min_Light],
       [max_Light],
       [target_Light],
       [minco2],
       [maxco2],
       [targetCo2]
FROM [TestDB].[dbo].[device]

UPDATE [stage].[Dim_Device] SET [DeviceLocation] = 'UNKNOWN' WHERE [DeviceLocation] IS NULL 



----------------------------------------------------------------Populate Measurement table--------------------------------------------------------------
TRUNCATE TABLE [stage].[Dim_Measurement]
INSERT INTO [stage].[Dim_Measurement]
           ([MeasurementId],
            [MinTemp],
			[MaxTemp],
			[MedianTemp],
			[MinHum],
			[MaxHum],
			[MedianHum],
			[MinLight],
			[MaxLight],
			[MedianLight],
			[MinCo2],
			[MaxCo2],
			[MedianCo2])
SELECT  [id],
		 MIN(s.[temperature]),
		 MAX(s.[temperature]),
		 AVG(s.[temperature]),
		 MIN(s.[humidity]),
		 MAX(s.[humidity]),
		 AVG(s.[humidity]),
		 MIN(s.[light]),
		 MAX(s.[light]),
		 AVG(s.[light]),
		 MIN(s.[co2]),
		 MAX(s.[co2]),
		 AVG(s.[co2])
FROM [TestDB].[dbo].[sensor_data] AS s
GROUP BY s.[id]



-----------------------------------------------------------------Populate Fact Measurement-----------------------------------------------------------------
TRUNCATE TABLE [stage].[Fact_Measurement]
INSERT INTO [stage].[Fact_Measurement]
		 ([DateId]
          ,[MeasurementId]
          ,[Eui]
          ,[TimeId]
          ,[Co2Value]
          ,[TempValue]
          ,[LightValue]
          ,[HumValue])
SELECT  CAST(s.date AS DATE),
		 s.[id],
		 s.[Eui],
		 CONVERT(NVARCHAR(10), s.date, 108),
		 s.[co2],
		 s.[temperature],
		 s.[light],
		 s.[humidity]
 FROM [TestDB].[dbo].[device] d
 INNER JOIN [TestDB].[dbo].[sensor_data] s
 ON s.[Eui] = d.[Eui]











--------------------------------------------------------------POPULATE DATE--------------------------------------------------------------------------

GO
DECLARE @StartDate [DATETIME]
DECLARE @EndDate [DATETIME]
SET @StartDate = '2021-01-01'
SET @EndDate = DATEADD(dd, 28853, @StartDate)

WHILE @StartDate <= @EndDate
    BEGIN
        DECLARE @dateIdCalc [INT]
        SET @dateIdCalc = YEAR(@StartDate) * 10000 +MONTH(@StartDate) * 100 +DAY(@StartDate)
        INSERT INTO [edw].[Dim_Date]
        (DateId,
		 CalendarDate,
		 WeekDayName,
		 MonthName,
		 Quarter)
SELECT CAST(@StartDate AS DATE),
		@StartDate,
		DATENAME(weekday, @StartDate),
		DATENAME(month, @StartDate),
		DATEPART(QUARTER, @StartDate)

SET @StartDate = DATEADD(d, 1 , @StartDate)
END



--------------------------------------------------------------POPULATE DEVICE--------------------------------------------------------------------------
INSERT INTO [edw].[Dim_Device] 
		([Eui]
		,[DeviceLocation]
		,[MinThresholdTemp]
		,[MaxThresholdTemp]
		,[TargetTemp]
		,[MinThresholdHum]
		,[MaxThresholdHum]
		,[TargetHum]
		,[MinThresholdLight]
		,[MaxThresholdLight]
		,[TargetLight]
		,[MinThresholdCo2]
		,[MaxThresholdCo2]
		,[TargetCo2])
SELECT   [Eui]
        ,[DeviceLocation]
        ,[MinThresholdTemp]
        ,[MaxThresholdTemp]
        ,[TargetTemp]
        ,[MinThresholdHum]
        ,[MaxThresholdHum]
        ,[TargetHum]
        ,[MinThresholdLight]
        ,[MaxThresholdLight]
        ,[TargetLight]
        ,[MinThresholdCo2]
        ,[MaxThresholdCo2]
        ,[TargetCo2]
 FROM [stage].[Dim_Device] 

 --------------------------------------------------------------POPULATE MEASUREMENT--------------------------------------------------------------------------

INSERT INTO [edw].[Dim_Measurement] (
		[MeasurementId],
		[MinTemp],
		[MaxTemp],
		[MedianTemp],
		[MinHum],
		[MaxHum],
		[MedianHum],
		[MinLight],
		[MaxLight],
		[MedianLight],
		[MinCo2],
		[MaxCo2],
		[MedianCo2])
 SELECT [MeasurementId],
        [MinTemp],
		[MaxTemp],
		[MedianTemp],
		[MinHum],
		[MaxHum],
		[MedianHum],
		[MinLight],
		[MaxLight],
		[MedianLight],
		[MinCo2],
		[MaxCo2],
		[MedianCo2]
FROM [stage].[Dim_Measurement] 

 --------------------------------------------------------------POPULATE TIME--------------------------------------------------------------------------
DECLARE @Time AS [TIME];
SET @Time = '00:00:00';
 
DECLARE @counter AS int;
SET @counter = 0;
 
 
WHILE @counter < 86400
BEGIN
    INSERT INTO  [edw].[Dim_Time] (
						TimeId
                       , Hour
                       , Minute
					   , SECOND
                       , TimeOfDay)
                VALUES (@Time
                       , DATEPART(HOUR, @Time)
                       , DATEPART(MINUTE, @Time) 
					   , DATEPART(SECOND, @Time)
                       ,CASE WHEN (@Time >= '00:00:00' AND @Time < '06:00:00') THEN 'Night'
                         WHEN (@Time >= '06:00:00' AND @Time < '12:00:00') THEN 'Morning'
                         WHEN (@Time >= '12:00:00' AND @Time < '18:00:00') THEN 'Afternoon'
                         ELSE 'Evening'
                         END
                    );
 
    SET @Time = DATEADD(SECOND, 1, @Time);
 
    set @counter = @counter + 1;
END


--------------------------------------------------------------POPULATE FACT TABLE--------------------------------------------------------------------------

INSERT INTO [edw].[Fact_Measurement] 
	([D_ID],
	 [M_ID],
	 [DV_ID],
	 [T_ID],
	 [Co2Value],
	 [TempValue],
	 [LightValue],
	 [HumValue])
SELECT d.[D_ID],
	 m.[M_ID],
	 dv.[DV_ID],
	 t.[T_ID],
	 f.[Co2Value],
	 f.[TempValue],
	 f.[LightValue],
	 f.[HumValue]
FROM [stage].[Fact_Measurement] AS f
	INNER JOIN [edw].[Dim_Date] AS d
 ON d.[DateId] = f.[DateId]
	 INNER JOIN [edw].[Dim_Device] AS dv
 ON dv.[Eui] = f.[Eui]
	INNER JOIN [edw].[Dim_Measurement] AS m
 ON m.[MeasurementId] = f.[MeasurementId]
	INNER JOIN [edw].[Dim_Time] AS t
 ON t.[TimeId] = f.[TimeId]
