CREATE DATABASE [Entrega]

USE [Entrega]

CREATE TABLE [dbo].[tbl_entrega](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[uf] [nvarchar](2) NOT NULL,
	[sla] [int] NOT NULL
) ON [PRIMARY]