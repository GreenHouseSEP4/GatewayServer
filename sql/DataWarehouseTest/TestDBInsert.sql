USE TestDB
GO

TRUNCATE TABLE dbo.device
GO

TRUNCATE TABLE dbo.sensor_data
GO


INSERT INTO [dbo].[device]
           ([eui]
           ,[location]
           ,[min_temperature]
           ,[max_temperature]
           ,[min_humidity]
           ,[max_humidity]
           ,[min_light]
           ,[max_light]
           ,[target_temperature]
           ,[target_humidity]
           ,[target_light]
           ,[maxco2]
           ,[minco2]
           ,[targetco2])
SELECT [eui]
           ,[location]
           ,[min_temperature]
           ,[max_temperature]
           ,[min_humidity]
           ,[max_humidity]
           ,[min_light]
           ,[max_light]
           ,[target_temperature]
           ,[target_humidity]
           ,[target_light]
           ,[maxco2]
           ,[minco2]
           ,[targetco2]
FROM GreenHouseDB.dbo.device
WHERE eui = 'DUMMY30B00259D2B'
GO



INSERT INTO [dbo].[sensor_data]
           ([humidity]
           ,[temperature]
           ,[light]
           ,[co2]
           ,[date]
           ,[eui])
SELECT [humidity]
           ,[temperature]
           ,[light]
           ,[co2]
           ,[date]
           ,[eui]
FROM GreenHouseDB.dbo.sensor_data
WHERE eui = 'DUMMY30B00259D2B'
GO

