USE [GreenHouseDW]
GO


---------------------------------------------------------------ADD NEW DATA IN DEVICE-----------------------------------------------------------------


--------------------------------------------------------------LOAD TO STAGE---------------------------------------------------------------------
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
SELECT [eui], 
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
FROM [GreenhouseDB].[dbo].[device]

UPDATE [stage].[Dim_Device] SET deviceLocation = 'UNKNOWN' WHERE deviceLocation IS NULL 


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
SELECT  id,
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
FROM [GreenHouseDB].[dbo].[sensor_data] AS s
GROUP BY s.id


IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[stage].[Fact_Measurement]') AND TYPE IN (N'U'))
CREATE TABLE [stage].[Fact_Measurement] (
	[DateId] [DATE],
	[MeasurementId] [INT] NOT NULL,
	[Eui] [NVARCHAR](16) NOT NULL,
	[TimeId] [TIME],
	[Co2Value] [INT],
	[TempValue] [INT],
	[LightValue] [INT],
	[HumValue] [INT],
);


ALTER TABLE [stage].[Fact_Measurement] ADD CONSTRAINT FK_Fact_Measurement_1 FOREIGN KEY (MeasurementId) REFERENCES [stage].[Dim_Measurement] (MeasurementId);
ALTER TABLE [stage].[Fact_Measurement] ADD CONSTRAINT FK_Fact_Measurement_2 FOREIGN KEY (Eui) REFERENCES [stage].[Dim_Device] (Eui);


INSERT INTO [stage].[Fact_Measurement]
		  ([DateId]
          ,[MeasurementId]
          ,[Eui]
          ,[TimeId]
          ,[Co2Value]
          ,[TempValue]
          ,[LightValue]
          ,[HumValue])
SELECT  CAST(s.[date] AS [DATE]),
		 s.[id],
		 s.[eui],
		 CONVERT([NVARCHAR](10), s.[date], 108),
		 s.[co2],
		 s.[temperature],
		 s.[light],
		 s.[humidity]
 FROM [GreenHouseDB].[dbo].[device] d
 INNER JOIN [GreenHouseDB].[dbo].[sensor_data] s
 ON s.[eui] = d.[eui]
 
 UPDATE [stage].[Fact_Measurement] SET [Co2Value] = '4999' WHERE [Co2Value] = '-4444' 
 UPDATE [stage].[Fact_Measurement] SET [TempValue] = '0' WHERE [TempValue] = '-4444'
 UPDATE [stage].[Fact_Measurement] SET [LightValue] = '65000' WHERE [LightValue] = '-4444'
 UPDATE [stage].[Fact_Measurement] SET [HumValue] = '49' WHERE [HumValue] = '-4444'




 -------------------------------------------------------UPDATE EDW TABLES--------------------------------------------------------------------

DECLARE @NewLoadDate [DATE]
SET @NewLoadDate = CONVERT([DATE], GETDATE())
DECLARE @FutureDate [DATE]
SET @FutureDate = '2023-12-31'
DECLARE @RowIndicator [BIT]
SET @RowIndicator = 1
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
    ,[TargetCo2]
	,[ValidFrom]
	,[ValidTo]
	,[IsActive])
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
		,@NewLoadDate
		,@FutureDate
		,@RowIndicator
 FROM [stage].[Dim_Device] as d
 WHERE d.[eui] NOT IN (SELECT [eui] FROM [edw].[Dim_Device])



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
FROM [stage].[Dim_Measurement] AS sm
WHERE sm.[MeasurementId] NOT IN (SELECT [MeasurementId] FROM [edw].[Dim_Measurement])


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
 ON d.[DateId] = f.[dateId]
	 INNER JOIN [edw].[Dim_Device] AS dv
 ON dv.[eui] = f.[eui]
	INNER JOIN [edw].[Dim_Measurement] AS m
 ON m.[measurementId] = f.[measurementId]
	INNER JOIN [edw].[Dim_Time] AS t
 ON t.[timeId] = f.[timeId]
 WHERE m.[M_ID] NOT IN (SELECT [M_ID] FROM [edw].[Fact_Measurement])



 -------------------------------------------------------------LOG THE UPDATE------------------------------------------------------------------------
DECLARE @NewLoadDate [DATE]
SET @NewLoadDate = CONVERT([DATE], GETDATE())
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) 
VALUES ('Dim_Device', @NewLoadDate)
