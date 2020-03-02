Create database Pedido

USE [Pedido]

CREATE TABLE [dbo].[tbl_pedido](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[data_entrega] [date] NOT NULL,
	[produto] [nvarchar](50) NOT NULL,
	[endereco] [nvarchar](50) NOT NULL,
	[uf] [nvarchar](2) NOT NULL,
	[municipio] [nvarchar](50) NOT NULL,
	[cep] [nvarchar](10) NOT NULL
) ON [PRIMARY]
