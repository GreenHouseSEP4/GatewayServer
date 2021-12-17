CREATE SCHEMA [dbo]

GO
CREATE TABLE [dbo].[sensor_data](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[humidity] [int] NULL,
	[temperature] [int] NULL,
	[light] [int] NULL,
	[co2] [int] NULL,
	[date] [datetime] NOT NULL,
	[eui] [nchar](16) NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[sensor_data]  WITH CHECK ADD  CONSTRAINT [FK_Sensor_Data_0] FOREIGN KEY([eui])
REFERENCES [dbo].[device] ([eui])
GO
ALTER TABLE [dbo].[sensor_data] CHECK CONSTRAINT [FK_Sensor_Data_0]


GO
CREATE TABLE [dbo].[device](
	[eui] [nchar](16) NOT NULL,
	[location] [nvarchar](30) NULL,
	[min_temperature] [int] NULL,
	[max_temperature] [int] NULL,
	[min_humidity] [int] NULL,
	[max_humidity] [int] NULL,
	[min_light] [int] NULL,
	[max_light] [int] NULL,
	[target_temperature] [int] NULL,
	[target_humidity] [int] NULL,
	[target_light] [int] NULL,
	[maxco2] [int] NULL,
	[minco2] [int] NULL,
	[targetco2] [int] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[device] ADD  CONSTRAINT [PK_device] PRIMARY KEY CLUSTERED 
(
	[eui] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO