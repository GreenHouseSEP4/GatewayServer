USE [GreenhouseDW]
GO

-----------------------------------------------------------------DELETE ONE DEVICE-----------------------------------------------------------------------------

DELETE FROM [GreenHouseDB].[dbo].[device] WHERE eui = '2021-15-12T02:16'


-----------------------------------------------------------------INSERT INTO STAGE-----------------------------------------------------------------

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



 
 UPDATE [stage].[Fact_Measurement] SET Co2Value = '4999' WHERE Co2Value = '-4444' 
 UPDATE [stage].[Fact_Measurement] SET TempValue = '0' WHERE Co2Value = '-4444'
 UPDATE [stage].[Fact_Measurement] SET LightValue = '65000' WHERE Co2Value = '-4444'
 UPDATE [stage].[Fact_Measurement] SET HumValue = '49' WHERE Co2Value = '-4444'





 -------------------------------------------------------------UPDATE EDW-------------------------------------------------------------------


DECLARE @NewLoadDate date
SET @NewLoadDate = CONVERT(date, GETDATE())
DECLARE @RowIndicator BIT
SET @RowIndicator = 0
UPDATE [edw].[Dim_Device] SET ValidTo = @NewLoadDate, IsActive = @RowIndicator
WHERE eui NOT IN (SELECT eui FROM stage.Dim_Device)




--------------------------------------------------------LOG THE UPDATE----------------------------------------------------------------

DECLARE @NewLoadDate DATE
SET @NewLoadDate = CONVERT(date, GETDATE())
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) 
VALUES ('Dim_Device', @NewLoadDate)

