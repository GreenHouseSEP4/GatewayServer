USE [GreenHouseDW]
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
FROM [GreenhouseDB].[dbo].[device]

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
FROM [GreenHouseDB].[dbo].[sensor_data] AS s
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
 FROM [GreenHouseDB].[dbo].[device] d
 INNER JOIN [GreenHouseDB].[dbo].[sensor_data] s
 ON s.[Eui] = d.[Eui]


 UPDATE [stage].[Fact_Measurement] SET Co2Value = '4999' WHERE Co2Value = '-4444' 
 UPDATE [stage].[Fact_Measurement] SET TempValue = '0' WHERE Co2Value = '-4444'
 UPDATE [stage].[Fact_Measurement] SET LightValue = '65000' WHERE Co2Value = '-4444'
 UPDATE [stage].[Fact_Measurement] SET HumValue = '49' WHERE Co2Value = '-4444'
