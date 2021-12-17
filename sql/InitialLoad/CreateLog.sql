CREATE SCHEMA [etl]
GO


CREATE TABLE [etl].[LogUpdate](
	[Table] [NVARCHAR](50) NULL,
	[LastLoadDate] [DATE] NULL
) ON [PRIMARY]
GO



INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('Dim_Device', '2021-12-13T02:35:00')
INSERT INTO [etl].[LogUpdate] ([Table], [LastLoadDate]) VALUES ('Dim_Measurement', '2021-12-13T02:35:00')


ALTER TABLE [edw].[Dim_Device]
ADD [ValidFrom] [DATE], [ValidTo] [DATE], [IsActive] [BIT]



UPDATE [edw].[Dim_Device]
SET [ValidFrom] = '2015-01-01', [ValidTo] = '2023-12-31', [IsActive] = 1