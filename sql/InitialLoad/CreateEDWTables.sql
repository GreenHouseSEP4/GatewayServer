USE [GreenHouseDW]
GO

--------------------------------------------------------------CREATE SCHEMA--------------------------------------------------------------------------
CREATE SCHEMA [edw]
GO



--------------------------------------------------------------CREATE DATE--------------------------------------------------------------------------
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[edw].[Dim_Date]') AND TYPE IN (N'U'))
CREATE TABLE [edw].[Dim_Date] (
	[D_ID] [INT] IDENTITY NOT NULL,
	[DateId] [DATE] NOT NULL,
	[CalendarDate] [DATETIME],
	[WeekdayName] [NVARCHAR](10),
	[MonthName] [NVARCHAR](10),
	[Quarter] [NVARCHAR](10),
)

ALTER TABLE [edw].[Dim_Date] ADD CONSTRAINT PK_Dim_Date PRIMARY KEY (D_ID);



--------------------------------------------------------------CREATE DEVICE--------------------------------------------------------------------------
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[edw].[Dim_Device]') AND TYPE IN (N'U'))
CREATE TABLE [edw].[Dim_Device] (
	[DV_ID] [INT] IDENTITY NOT NULL,
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

ALTER TABLE [edw].[Dim_Device] ADD CONSTRAINT PK_Dim_Device PRIMARY KEY (DV_ID);



--------------------------------------------------------------CREATE MEASUREMENT--------------------------------------------------------------------------
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[edw].[Dim_Measurement]') AND TYPE IN (N'U'))
CREATE TABLE [edw].[Dim_Measurement] (
	[M_ID] [INT] IDENTITY NOT NULL,
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

ALTER TABLE [edw].[Dim_Measurement] ADD CONSTRAINT PK_Dim_Measurement PRIMARY KEY (M_ID);




--------------------------------------------------------------CREATE TIME--------------------------------------------------------------------------
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[edw].[Dim_Time]') AND TYPE IN (N'U'))
CREATE TABLE [edw].[Dim_Time] (
	 [T_ID] [INT] IDENTITY NOT NULL,
	 [TimeId] TIME NOT NULL,
	 [Hour] [INT],
	 [Minute] [INT],
	 [Second] [INT],
	 [TimeOfDay] [NVARCHAR](20)
);

ALTER TABLE [edw].[Dim_Time] ADD CONSTRAINT PK_Dim_Time PRIMARY KEY (T_ID);




--------------------------------------------------------------CREATE FACT MEASUREMENT--------------------------------------------------------------------------
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[edw].[Fact_Measurement]') AND type in (N'U'))
CREATE TABLE [edw].[Fact_Measurement] (
	[D_ID] [INT] NOT NULL,
	[M_ID] [INT] NOT NULL,
	[DV_ID] [INT] NOT NULL,
	[T_ID] [INT] NOT NULL,
	[Co2Value] [INT],
	[TempValue] [INT],
	[LightValue] [INT],
	[HumValue] [INT],
);


ALTER TABLE [edw].[Fact_Measurement] ADD CONSTRAINT FK_Fact_Measurement_0 FOREIGN KEY (D_ID) REFERENCES [edw].[Dim_Date] (D_ID);
ALTER TABLE [edw].[Fact_Measurement] ADD CONSTRAINT FK_Fact_Measurement_1 FOREIGN KEY (M_ID) REFERENCES [edw].[Dim_Measurement] (M_ID);
ALTER TABLE [edw].[Fact_Measurement] ADD CONSTRAINT FK_Fact_Measurement_2 FOREIGN KEY (DV_ID) REFERENCES [edw].[Dim_Device] (DV_ID);
ALTER TABLE [edw].[Fact_Measurement] ADD CONSTRAINT FK_Fact_Measurement_3 FOREIGN KEY (T_ID) REFERENCES edw.Dim_Time (T_ID);
