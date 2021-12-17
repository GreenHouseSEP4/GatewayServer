USE [GreenHouseDW]
GO

DROP TABLE [stage].[Fact_Measurement]


TRUNCATE TABLE [stage].[Dim_Device]
TRUNCATE TABLE [stage].[Dim_Measurement]