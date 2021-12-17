USE [GreenHouseDW]
GO



/*-----------------------------------------------------------------Create Stage Schema-------------------------------------------------------------------------*/

CREATE SCHEMA [stage]
GO


/*-----------------------------------------------------------------Create Device Dimension-------------------------------------------------------------------------*/
DROP TABLE [stage].[Dim_Device]
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[stage].[Dim_Device]') AND TYPE IN (N'U'))
CREATE TABLE [stage].[Dim_Device] (
	[Eui] [NVARCHAR](16) NOT NULL,
	[DeviceLocation] [NVARCHAR](30),
	[MinThresholdTemp] [INT],
	[MaxThresholdTemp] [INT],
	[TargetTemp] [DECIMAL](18, 0),
	[MinThresholdHum] [INT],
	[MaxThresholdHum] [INT],
	[TargetHum] [DECIMAL](18, 0),
	[MinThresholdLight] [INT],
	[MaxThresholdLight] [INT],
	[TargetLight] [DECIMAL](18, 0),
	[MinThresholdCo2] [INT],
	[MaxThresholdCo2] [INT],
	[TargetCo2] [DECIMAL](18, 0)
);

ALTER TABLE [stage].[[Dim_Device] ADD CONSTRAINT PK_[Dim_Device PRIMARY KEY (Eui);



/*-----------------------------------------------------------------Create Measurement Dimension-------------------------------------------------------------------------*/
DROP TABLE [stage].[Dim_Measurement]
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[stage].[Dim_Measurement]') AND TYPE IN (N'U'))
CREATE TABLE [stage].[Dim_Measurement] (
	[MeasurementId] [INT] NOT NULL,
	[MinTemp] [INT],
	[MaxTemp] [INT],
	[MedianTemp] [DECIMAL](18, 0),
	[MinHum] [INT],
	[MaxHum] [INT],
	[MedianHum] [DECIMAL](18, 0),
	[MinLight] [INT],
	[MaxLight] [INT],
	[MedianLight] [DECIMAL](18, 0),
	[MinCo2] [INT],
	[MaxCo2] [INT],
	[MedianCo2] [DECIMAL](18, 0)
);

ALTER TABLE [stage].[[Dim_Measurement] ADD CONSTRAINT PK_[Dim_Measurement PRIMARY KEY (MeasurementId);



/*-----------------------------------------------------------------Create Fact Measurement-------------------------------------------------------------------------*/
DROP TABLE [stage].[Fact_Measurement]
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
