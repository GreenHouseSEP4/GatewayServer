USE [TestDW]
GO


/****** Object:  Schema [edw]    Script Date: 12/15/2021 3:22:34 PM ******/
CREATE SCHEMA [edw]
GO


/****** Object:  Schema [stage]    Script Date: 12/15/2021 3:22:34 PM ******/
CREATE SCHEMA [stage]
GO
/****** Object:  Table [edw].[Dim_Date]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [edw].[Dim_Date](
	[D_ID] [int] IDENTITY(1,1) NOT NULL,
	[DateId] [date] NOT NULL,
	[CalendarDate] [datetime] NULL,
	[WeekdayName] [nvarchar](10) NULL,
	[MonthName] [nvarchar](10) NULL,
	[Quarter] [nvarchar](10) NULL,
 CONSTRAINT [PK_Dim_Date] PRIMARY KEY CLUSTERED 
(
	[D_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [edw].[Dim_Device]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [edw].[Dim_Device](
	[DV_ID] [int] IDENTITY(1,1) NOT NULL,
	[Eui] [nvarchar](16) NOT NULL,
	[DeviceLocation] [nvarchar](30) NULL,
	[MinThresholdTemp] [int] NULL,
	[MaxThresholdTemp] [int] NULL,
	[TargetTemp] [decimal](18, 0) NULL,
	[MinThresholdHum] [int] NULL,
	[MaxThresholdHum] [int] NULL,
	[TargetHum] [decimal](18, 0) NULL,
	[MinThresholdLight] [int] NULL,
	[MaxThresholdLight] [int] NULL,
	[TargetLight] [decimal](18, 0) NULL,
	[MinThresholdCo2] [int] NULL,
	[MaxThresholdCo2] [int] NULL,
	[TargetCo2] [decimal](18, 0) NULL,
	[ValidFrom] [date] NULL,
	[ValidTo] [date] NULL,
	[IsActive] [bit] NULL,
 CONSTRAINT [PK_Dim_Device] PRIMARY KEY CLUSTERED 
(
	[DV_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [edw].[Dim_Measurement]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [edw].[Dim_Measurement](
	[M_ID] [int] IDENTITY(1,1) NOT NULL,
	[MeasurementId] [int] NOT NULL,
	[MinTemp] [int] NULL,
	[MaxTemp] [int] NULL,
	[MedianTemp] [decimal](18, 0) NULL,
	[MinHum] [int] NULL,
	[MaxHum] [int] NULL,
	[MedianHum] [decimal](18, 0) NULL,
	[MinLight] [int] NULL,
	[MaxLight] [int] NULL,
	[MedianLight] [decimal](18, 0) NULL,
	[MinCo2] [int] NULL,
	[MaxCo2] [int] NULL,
	[MedianCo2] [decimal](18, 0) NULL,
 CONSTRAINT [PK_Dim_Measurement] PRIMARY KEY CLUSTERED 
(
	[M_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [edw].[Dim_Time]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [edw].[Dim_Time](
	[T_ID] [int] IDENTITY(1,1) NOT NULL,
	[TimeId] [time](7) NOT NULL,
	[Hour] [int] NULL,
	[Minute] [int] NULL,
	[Second] [int] NULL,
	[TimeOfDay] [nvarchar](20) NULL,
 CONSTRAINT [PK_Dim_Time] PRIMARY KEY CLUSTERED 
(
	[T_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [edw].[Fact_Measurement]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [edw].[Fact_Measurement](
	[D_ID] [int] NOT NULL,
	[M_ID] [int] NOT NULL,
	[DV_ID] [int] NOT NULL,
	[T_ID] [int] NOT NULL,
	[Co2Value] [int] NULL,
	[TempValue] [int] NULL,
	[LightValue] [int] NULL,
	[HumValue] [int] NULL
) ON [PRIMARY]
GO


/****** Object:  Table [stage].[Dim_Device]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [stage].[Dim_Device](
	[Eui] [nvarchar](16) NOT NULL,
	[DeviceLocation] [nvarchar](30) NULL,
	[MinThresholdTemp] [int] NULL,
	[MaxThresholdTemp] [int] NULL,
	[TargetTemp] [decimal](18, 0) NULL,
	[MinThresholdHum] [int] NULL,
	[MaxThresholdHum] [int] NULL,
	[TargetHum] [decimal](18, 0) NULL,
	[MinThresholdLight] [int] NULL,
	[MaxThresholdLight] [int] NULL,
	[TargetLight] [decimal](18, 0) NULL,
	[MinThresholdCo2] [int] NULL,
	[MaxThresholdCo2] [int] NULL,
	[TargetCo2] [decimal](18, 0) NULL,
 CONSTRAINT [PK_Dim_Device] PRIMARY KEY CLUSTERED 
(
	[Eui] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [stage].[Dim_Measurement]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [stage].[Dim_Measurement](
	[MeasurementId] [int] NOT NULL,
	[MinTemp] [int] NULL,
	[MaxTemp] [int] NULL,
	[MedianTemp] [decimal](18, 0) NULL,
	[MinHum] [int] NULL,
	[MaxHum] [int] NULL,
	[MedianHum] [decimal](18, 0) NULL,
	[MinLight] [int] NULL,
	[MaxLight] [int] NULL,
	[MedianLight] [decimal](18, 0) NULL,
	[MinCo2] [int] NULL,
	[MaxCo2] [int] NULL,
	[MedianCo2] [decimal](18, 0) NULL,
 CONSTRAINT [PK_Dim_Measurement] PRIMARY KEY CLUSTERED 
(
	[MeasurementId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [stage].[Fact_Measurement]    Script Date: 12/15/2021 3:22:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [stage].[Fact_Measurement](
	[DateId] [date] NULL,
	[MeasurementId] [int] NOT NULL,
	[Eui] [nvarchar](16) NOT NULL,
	[TimeId] [time](7) NULL,
	[Co2Value] [int] NULL,
	[TempValue] [int] NULL,
	[LightValue] [int] NULL,
	[HumValue] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [edw].[Fact_Measurement]  WITH CHECK ADD  CONSTRAINT [FK_Fact_Measurement_0] FOREIGN KEY([D_ID])
REFERENCES [edw].[Dim_Date] ([D_ID])
GO
ALTER TABLE [edw].[Fact_Measurement] CHECK CONSTRAINT [FK_Fact_Measurement_0]
GO
ALTER TABLE [edw].[Fact_Measurement]  WITH CHECK ADD  CONSTRAINT [FK_Fact_Measurement_1] FOREIGN KEY([M_ID])
REFERENCES [edw].[Dim_Measurement] ([M_ID])
GO
ALTER TABLE [edw].[Fact_Measurement] CHECK CONSTRAINT [FK_Fact_Measurement_1]
GO
ALTER TABLE [edw].[Fact_Measurement]  WITH CHECK ADD  CONSTRAINT [FK_Fact_Measurement_2] FOREIGN KEY([DV_ID])
REFERENCES [edw].[Dim_Device] ([DV_ID])
GO
ALTER TABLE [edw].[Fact_Measurement] CHECK CONSTRAINT [FK_Fact_Measurement_2]
GO
ALTER TABLE [edw].[Fact_Measurement]  WITH CHECK ADD  CONSTRAINT [FK_Fact_Measurement_3] FOREIGN KEY([T_ID])
REFERENCES [edw].[Dim_Time] ([T_ID])
GO
ALTER TABLE [edw].[Fact_Measurement] CHECK CONSTRAINT [FK_Fact_Measurement_3]
GO
ALTER TABLE [stage].[Fact_Measurement]  WITH CHECK ADD  CONSTRAINT [FK_Fact_Measurement_1] FOREIGN KEY([MeasurementId])
REFERENCES [stage].[Dim_Measurement] ([MeasurementId])
GO
ALTER TABLE [stage].[Fact_Measurement] CHECK CONSTRAINT [FK_Fact_Measurement_1]
GO
ALTER TABLE [stage].[Fact_Measurement]  WITH CHECK ADD  CONSTRAINT [FK_Fact_Measurement_2] FOREIGN KEY([Eui])
REFERENCES [stage].[Dim_Device] ([Eui])
GO
ALTER TABLE [stage].[Fact_Measurement] CHECK CONSTRAINT [FK_Fact_Measurement_2]
GO
