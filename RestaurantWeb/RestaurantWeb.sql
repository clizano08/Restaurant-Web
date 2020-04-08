use master;
create database RestaurantWeb;
go
use RestaurantWeb;
go
---Tablas
Create Table Usuario(
    ID int NOT NULL,
	IDDireccion int NOT NULL,
	Nombre nvarchar(100) NULL,
	Clave varbinary(100) NOT NULL,
	TipoUsuario nvarchar(100) NOT NULL,
	Telefono nvarchar(100) NULL,
	Activo bit NULL,
	Consecutivo int Identity 
)
Create Table Direccion(
	ID int NOT NULL,
	IDUsuario int NOT NULL,
	Consecutivo int Identity,
	Direccion nvarchar(100),
	
)
Create Table Producto(
	ID int NOT NULL,
	Nombre nvarchar(100) NULL,
	Precio money NULL,
	Cantidad int NULL,
	CantidadMinimaVenta int NULL,
	Link nvarchar(2000) NULL,
	Categoria nvarchar(50) NULL,
	Consecutivo int Identity
)
Create Table EncPedido(
	ID int NOT NULL,
	IDUsuario int NOT NULL,
	FecEntrega smalldatetime NULL,
	HorarioEntrega nvarchar(200) NULL,
	Consecutivo int Identity   
)
Create Table DetPedido(
	IDEncPedido int NOT NULL,
	IDProducto int NOT NULL,
	Cantidad int NULL,
	Obsevaciones nvarchar(100) NULL,
)
Create Table EncFactura(
	ID int NOT NULL,
	IDUsuario int NOT NULL,
	IDEncPedido int NOT NULL,
	TipoPago nvarchar(100) NULL,
	FecCreacion smalldatetime NULL,
	Consecutivo int Identity
)
Create Table DetFactura(
	IDEncFactura int NOT NULL,
	IDProducto int NOT NULL,
	Cantidad int NULL,
	Subtotal money NULL,
	Iva money NULL,
	Descuentos money NULL,
	Total money NULL
)
Create Table EncDespacho(
	ID int NOT NULL,
	IDEncFactura int NOT NULL,
	IDUsuario int NOT NULL,
	IDDireccion int NOT NULL,
	FechaCreacion smalldatetime NULL,
)
Create Table DetDespacho(
	IDEncDespacho int NOT NULL,
	FechaSalida smalldatetime NULL,
	Envio nvarchar NULL,
	Obsevaciones nvarchar(100) NULL
)
---PK
Alter Table Usuario add constraint PK_Usuario Primary Key(ID)
Alter Table Direccion add constraint PK_Direccion Primary Key(ID)
Alter Table Producto add constraint PK_Producto Primary Key(ID)
Alter Table EncPedido add constraint PK_EncPedido Primary Key(ID)
Alter Table DetPedido add constraint PK_DetPedido Primary Key(IDEncPedido,IDProducto)
Alter Table EncFactura add constraint PK_EncFactura Primary Key(ID)
Alter Table DetFactura add constraint PK_DetFcatura Primary Key(IDEncFactura)
Alter Table EncDespacho add constraint PK_EncDespacho Primary Key(ID)
Alter Table DetDespacho add constraint PK_DetDespacho Primary Key(IDEncDespacho)

---FK
Alter Table Usuario add constraint FK_UsuarioDireccion Foreign Key(IDDireccion) References Direccion(ID)
Alter Table EncPedido add constraint FK_EncPedidoUsuario Foreign Key(IDUsuario) References Usuario(ID)
Alter Table DetPedido add constraint FK_DetPedidoEnctPedido Foreign Key(IDEncPedido) References EncPedido(ID)
Alter Table DetPedido add constraint FK_DetPedidoProducto Foreign Key(IDProducto) References Producto(ID)
Alter Table EncFactura add constraint FK_EncFacturaUsuario Foreign Key(IDUsuario) References Usuario(ID)
Alter Table DetFactura add constraint FK_DetFacturaEncFactura Foreign Key(IDEncFactura) References EncFactura(ID)
Alter Table DetFactura add constraint FK_DetFacturaProducto Foreign Key(IDProducto) References Producto(ID)
Alter Table EncDespacho add constraint FK_EncDespachoUsuario Foreign Key(IDUsuario) References Usuario(ID)
Alter Table DetDespacho add constraint FK_DetDespachoEncDespacho Foreign Key(IDEncDespacho) References EncDespacho(ID)
Alter Table EncFactura add constraint FK_EncFacturaEncPedido Foreign Key(IDEncPedido) References EncPedido(ID)
Alter Table EncDespacho add constraint FK_EncDespachoEncFactura Foreign Key(IDEncFactura) References EncFactura(ID)
Alter Table EncDespacho add constraint FK_EncDespachoDireccion Foreign Key(IDDireccion) References Direccion(ID)

--Stored Procedures
--Usuario
IF OBJECT_ID('SP_InsertarUsuario') is NOT NULL 
DROP PROC  SP_InsertarUsuario;

CREATE PROCEDURE SP_InsertarUsuario
 (@ID int,@IDDireccion int,@Nombre nvarchar(100),@Clave varchar(100),@TipoUsuario nvarchar(100),@Telefono nvarchar(100),@Activo bit)
AS 
 insert into Usuario 
 values(@ID ,@IDDireccion,@Nombre ,EncryptByPassPhrase('Usuario',@Clave) ,@TipoUsuario ,@Telefono ,@Activo  );

IF OBJECT_ID('SP_EliminarUsuarioPorID') is NOT NULL 
Drop Proc  SP_EliminarUsuarioPorID;

CREATE PROCEDURE SP_EliminarUsuarioPorID 
(@ID int)
AS 
 	Delete from Usuario
	Where (ID =  @ID);

IF OBJECT_ID('SP_ActualizarUsuario') is NOT NULL 
Drop Proc  SP_ActualizarUsuario;

CREATE PROCEDURE SP_ActualizarUsuario
 (@ID int,@IDDireccion int,@Nombre nvarchar(100),@Clave varchar(100),@TipoUsuario nvarchar(100),@Telefono nvarchar(100),@Activo bit)
AS  
 	Update  Usuario
	Set 	Nombre =  @Nombre,
	IDDireccion = @IDDireccion,
	Clave =  ENCRYPTBYPASSPHRASE('Usuario' ,@Clave),
	TipoUsuario =  @TipoUsuario,
	Telefono =  @Telefono,
	Activo =  @Activo  
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarUsuarioPorID') is NOT NULL 
Drop Proc  SP_SeleccionarUsuarioPorID;

CREATE PROCEDURE SP_SeleccionarUsuarioPorID 
(@ID int)
AS 
 	Select ID ,IDDireccion, Nombre ,Convert(varchar(100) ,DecryptByPassPhrase('Usuario', Clave)) as 'Clave',TipoUsuario  ,Telefono ,Activo, Consecutivo  from Usuario
	Where (ID =  @ID);


--Usuario activo Por ID
IF OBJECT_ID('SP_SeleccionarUsuarioActivoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarUsuarioActivoPorID;

CREATE PROCEDURE SP_SeleccionarUsuarioActivoPorID 
(@ID int)
AS 
 	Select ID , IDDireccion,Nombre ,Convert(varchar(100) ,DecryptByPassPhrase('Usuario', Clave)) as 'Clave',TipoUsuario , Telefono ,Activo,Consecutivo  from Usuario
	Where (ID =  @ID) and Activo='True';

--Usuario Inactivo Por ID
IF OBJECT_ID('SP_SeleccionarUsuarioInactivoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarUsuarioInactivoPorID;

CREATE PROCEDURE SP_SeleccionarUsuarioInactivoPorID 
(@ID int)
AS 
 	Select ID ,IDDireccion ,Nombre ,Convert(varchar(100) ,DecryptByPassPhrase('Usuario', Clave)) as 'Clave',TipoUsuario ,Telefono ,Activo,Consecutivo  from Usuario
	Where (ID =  @ID) and Activo='False';


IF OBJECT_ID('SP_SeleccionarTodosUsuario') is NOT NULL 
Drop Proc  SP_SeleccionarTodosUsuario;

CREATE PROCEDURE SP_SeleccionarTodosUsuario
 AS 
 	Select ID ,IDDireccion,Nombre ,Convert(varchar(100) ,DecryptByPassPhrase('Usuario', Clave)) as 'Clave',TipoUsuario ,Telefono ,Activo,Consecutivo  from Usuario;
--Activar Usuario
IF OBJECT_ID('SP_ActivarUsuarioPorID') is NOT NULL 
Drop Proc  SP_ActivarUsuarioPorID;

CREATE PROCEDURE SP_ActivarUsuarioPorID
 (@ID int)
AS  
 	Update  Usuario
	Set Activo =  'True'  
	Where (ID =  @ID);

--Todos los Activos
IF OBJECT_ID('SP_SeleccionarTodosUsuarioActivo') is NOT NULL 
Drop Proc  SP_SeleccionarTodosUsuarioActivo;

CREATE PROCEDURE SP_SeleccionarTodosUsuarioActivo
 AS 
 	Select ID ,IDDireccion,Nombre ,Convert(varchar(100) ,DecryptByPassPhrase('Usuario', Clave)) as 'Clave',TipoUsuario ,Telefono ,Activo,Consecutivo  from Usuario 
	Where Activo='True' Order by TipoUsuario;


--Todos los Inactivos
IF OBJECT_ID('SP_SeleccionarTodosUsuarioInactivo') is NOT NULL 
Drop Proc  SP_SeleccionarTodosUsuarioInactivo;

CREATE PROCEDURE SP_SeleccionarTodosUsuarioInactivo
 AS 
 	Select ID,IDDireccion ,Nombre ,Convert(varchar(100) ,DecryptByPassPhrase('Usuario', Clave)) as 'Clave',TipoUsuario ,Telefono ,Activo,Consecutivo  from Usuario 
	Where Activo='False' Order by TipoUsuario;

--Direccion
IF OBJECT_ID('SP_InsertarDireccion') is NOT NULL 
DROP PROC  SP_InsertarDireccion;

CREATE PROCEDURE SP_InsertarDireccion
 (@ID int,@IDUsuario int,@Direccion nvarchar(200))
AS 
 insert into Direccion(ID ,IDUsuario ,Direccion ) 
 values(@ID ,@IDUsuario ,@Direccion  );

IF OBJECT_ID('SP_EliminarDireccionPorID') is NOT NULL 
Drop Proc  SP_EliminarDireccionPorID;

CREATE PROCEDURE SP_EliminarDireccionPorID 
(@ID int)
AS 
 	Delete from Direccion
	Where (ID =  @ID);

IF OBJECT_ID('SP_ActualizarDireccion') is NOT NULL 
Drop Proc  SP_ActualizarDireccion;

CREATE PROCEDURE SP_ActualizarDireccion
 (@ID int,@IDUsuario int,@Direccion nvarchar(200))
AS 
 	Update  Direccion
	Set 	IDUsuario =  @IDUsuario  ,
	Direccion =  @Direccion  	
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarDireccionPorID') is NOT NULL 
Drop Proc  SP_SeleccionarDireccionPorID;

CREATE PROCEDURE SP_SeleccionarDireccionPorID
(@ID int)
AS 
 	Select ID ,IDUsuario ,Direccion   from Direccion
	Where (ID =  @ID);
--Direccion por Usuario ID
IF OBJECT_ID('SP_SeleccionarTodosDireccionPorUsuarioID') is NOT NULL 
Drop Proc  SP_SeleccionarTodosDireccionPorUsuarioID;

CREATE PROCEDURE SP_SeleccionarTodosDireccionPorUsuarioID
(@IDUsuario int)
AS 
 	Select ID ,IDUsuario ,Direccion   from Direccion
	Where (IDUsuario =  @IDUsuario);

IF OBJECT_ID('SP_SeleccionarTodosDireccion') is NOT NULL 
Drop Proc  SP_SeleccionarTodosDireccion;

CREATE PROCEDURE SP_SeleccionarTodosDireccion
 AS 
 	Select ID ,IDUsuario ,Direccion  from Direccion;

--Producto
IF OBJECT_ID('SP_InsertarProducto') is NOT NULL 
DROP PROC  SP_InsertarProducto;

CREATE PROCEDURE SP_InsertarProducto
 (@ID int,@Nombre nvarchar(100),@Precio money,@Cantidad int,@CantidadMinimaVenta int, @Link nvarchar(2000), @Categoria nvarchar(50))
AS 
 insert into Producto(ID ,Nombre ,Precio ,Cantidad ,CantidadMinimaVenta,Link, Categoria ) 
 values(@ID ,@Nombre ,@Precio ,@Cantidad ,@CantidadMinimaVenta,@Link, @Categoria );

IF OBJECT_ID('SP_EliminarProductoPorID') is NOT NULL 
Drop Proc  SP_EliminarProductoPorID;

CREATE PROCEDURE SP_EliminarProductoPorID 
(@ID int)
AS 
 	Delete from Producto
	Where (ID =  @ID);

IF OBJECT_ID('SP_ActualizarProducto') is NOT NULL 
Drop Proc  SP_ActualizarProducto;

CREATE PROCEDURE SP_ActualizarProducto
 (@ID int,@Nombre nvarchar(100),@Precio money,@Cantidad int,@CantidadMinimaVenta int,@Link nvarchar(2000), @Categoria nvarchar(50))
AS 
 	Update  Producto
	Set 	Nombre =  @Nombre,
	Precio =  @Precio,
	Cantidad =  @Cantidad  ,
	CantidadMinimaVenta =  @CantidadMinimaVenta  ,
	Link = @Link,
	Categoria = @Categoria
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarProductoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarProductoPorID;

CREATE PROCEDURE SP_SeleccionarProductoPorID 
(@ID int)
AS  
 	Select ID ,Nombre ,Precio ,Cantidad ,CantidadMinimaVenta , Link , Categoria from Producto
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarTodosProducto') is NOT NULL 
Drop Proc  SP_SeleccionarTodosProducto;

CREATE PROCEDURE SP_SeleccionarTodosProducto
 AS  
 	Select ID ,Nombre ,Precio ,Cantidad ,CantidadMinimaVenta, Link , Categoria   from Producto Order by  ID ASC;

--EncPedido
IF OBJECT_ID('SP_InsertarEncPedido') is NOT NULL 
DROP PROC  SP_InsertarEncPedido;

CREATE PROCEDURE SP_InsertarEncPedido
 (@ID int,@IDUsuario int,@FecEntrega smalldatetime,@HorarioEntrega nvarchar(200))
AS 
 insert into EncPedido(ID ,IDUsuario ,FecEntrega ,HorarioEntrega ) 
 values(@ID ,@IDUsuario ,@FecEntrega,@HorarioEntrega  );

IF OBJECT_ID('SP_EliminarEncPedidoPorID') is NOT NULL 
Drop Proc  SP_EliminarEncPedidoPorID;

CREATE PROCEDURE SP_EliminarEncPedidoPorID 
(@ID int)
AS  
 	Delete from EncPedido
	Where (ID =  @ID);

IF OBJECT_ID('SP_ActualizarEncPedido') is NOT NULL 
Drop Proc  SP_ActualizarEncPedido;

CREATE PROCEDURE SP_ActualizarEncPedido
 (@ID int,@IDUsuario int,@FecEntrega smalldatetime,@HorarioEntrega nvarchar(100))
AS 
 	Update  EncPedido
	Set 	IDUsuario =  @IDUsuario,
	FecEntrega =  @FecEntrega,
	HorarioEntrega =  @HorarioEntrega 
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarEncPedidoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarEncPedidoPorID;

CREATE PROCEDURE SP_SeleccionarEncPedidoPorID 
(@ID int)
AS 
 	Select ID ,IDUsuario ,FecEntrega ,HorarioEntrega ,Consecutivo from EncPedido
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarTodosEncPedido') is NOT NULL 
Drop Proc  SP_SeleccionarTodosEncPedido;

CREATE PROCEDURE SP_SeleccionarTodosEncPedido
 AS 
 	Select ID ,IDUsuario ,FecEntrega ,HorarioEntrega, Consecutivo  from EncPedido;

--DetPedido
IF OBJECT_ID('SP_InsertarDetPedido') is NOT NULL 
DROP PROC  SP_InsertarDetPedido;

CREATE PROCEDURE SP_InsertarDetPedido
 (@IDEncPedido int,@IDProducto int,@Cantidad int,@Obsevaciones nvarchar(200))
AS 
 insert into DetPedido(IDEncPedido ,IDProducto ,Cantidad ,Obsevaciones ) 
 values(@IDEncPedido ,@IDProducto ,@Cantidad ,@Obsevaciones  );

IF OBJECT_ID('SP_EliminarDetPedidoPorID') is NOT NULL 
Drop Proc  SP_EliminarDetPedidoPorID;

CREATE PROCEDURE SP_EliminarDetPedidoPorID 
(@IDEncPedido int, @IDProducto int)
AS  
 	Delete from DetPedido
	Where (IDEncPedido =  @IDEncPedido and IDProducto = @IDProducto);

IF OBJECT_ID('SP_ActualizarDetPedido') is NOT NULL 
Drop Proc  SP_ActualizarDetPedido;

CREATE PROCEDURE SP_ActualizarDetPedido
 (@IDEncPedido int,@IDProducto int,@Cantidad int,@Obsevaciones nvarchar(200))
AS 
 	Update  DetPedido
	Set 	IDProducto =  @IDProducto,
	Cantidad =  @Cantidad,
	Obsevaciones =  @Obsevaciones  
	Where (IDEncPedido =  @IDEncPedido);

IF OBJECT_ID('SP_SeleccionarDetPedidoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarDetPedidoPorID;

CREATE PROCEDURE SP_SeleccionarDetPedidoPorID 
(@IDEncPedido int)
AS 
 	Select IDEncPedido ,IDProducto ,Cantidad ,Obsevaciones  from DetPedido
	Where (IDEncPedido =  @IDEncPedido);

IF OBJECT_ID('SP_SeleccionarTodosDetPedido') is NOT NULL 
Drop Proc  SP_SeleccionarTodosDetPedido;

CREATE PROCEDURE SP_SeleccionarTodosDetPedido
 AS 
 	Select IDEncPedido ,IDProducto ,Cantidad ,Obsevaciones  from DetPedido;


--EncFactura
IF OBJECT_ID('SP_InsertarEncFactura') is NOT NULL 
DROP PROC  SP_InsertarEncFactura;

CREATE PROCEDURE SP_InsertarEncFactura
 (@ID int,@IDUsuario int,@IDEncPedido int,@TipoPago nvarchar(200),@FecCreacion smalldatetime)
AS 
 insert into EncFactura(ID ,IDUsuario ,IDEncPedido ,TipoPago ,FecCreacion) 
 values(@ID ,@IDUsuario ,@IDEncPedido ,@TipoPago ,@FecCreacion  );

IF OBJECT_ID('SP_EliminarEncFacturaPorID') is NOT NULL 
Drop Proc  SP_EliminarEncFacturaPorID;

CREATE PROCEDURE SP_EliminarEncFacturaPorID 
(@ID int)
AS 
 	Delete from EncFactura
	Where (ID =  @ID);

IF OBJECT_ID('SP_ActualizarEncFactura') is NOT NULL 
Drop Proc  SP_ActualizarEncFactura;

CREATE PROCEDURE SP_ActualizarEncFactura
 (@ID int,@IDUsuario int,@IDEncPedido int,@TipoPago nvarchar(200),@FecCreacion smalldatetime)
AS 
 	Update  EncFactura
	Set 	IDUsuario =  @IDUsuario  ,
	IDEncPedido =  @IDEncPedido  ,
	TipoPago =  @TipoPago  ,
	FecCreacion =  @FecCreacion  
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarEncFacturaPorID') is NOT NULL 
Drop Proc  SP_SeleccionarEncFacturaPorID;

CREATE PROCEDURE SP_SeleccionarEncFacturaPorID 
(@ID int)
AS 
 	Select ID ,IDUsuario ,IDEncPedido ,TipoPago ,FecCreacion, Consecutivo  from EncFactura
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarTodosEncFactura') is NOT NULL 
Drop Proc  SP_SeleccionarTodosEncFactura;

CREATE PROCEDURE SP_SeleccionarTodosEncFactura
 AS 
 	Select ID ,IDUsuario ,IDEncPedido ,TipoPago ,FecCreacion, Consecutivo  from EncFactura;

--DetFactura
IF OBJECT_ID('SP_InsertarDetFactura') is NOT NULL 
DROP PROC  SP_InsertarDetFactura;

CREATE PROCEDURE SP_InsertarDetFactura
 (@IDEncFactura int,@IDProducto int,@Cantidad int,@Subtotal money,@Iva money,@Descuentos money,@Total money)
AS 
 insert into DetFactura(IDEncFactura,IDProducto,Cantidad ,Subtotal ,Iva ,Descuentos ,Total ) 
 values(@IDEncFactura,@IDProducto,@Cantidad ,@Subtotal ,@Iva ,@Descuentos ,@Total  );

IF OBJECT_ID('SP_EliminarDetFacturaPorID') is NOT NULL 
Drop Proc  SP_EliminarDetFacturaPorID;

CREATE PROCEDURE SP_EliminarDetFacturaPorID 
(@IDEncFactura int)
AS 
 	Delete from DetFactura
	Where (IDEncFactura =  @IDEncFactura);

IF OBJECT_ID('SP_ActualizarDetFactura') is NOT NULL 
Drop Proc  SP_ActualizarDetFactura;

CREATE PROCEDURE SP_ActualizarDetFactura
 (@IDEncFactura int,@IDProducto int,@Cantidad int,@Subtotal money,@Iva money,@Descuentos money,@Total money)
AS 
 	Update  DetFactura
	Set IDProducto = @IDProducto, 	
	Cantidad = @Cantidad,
	Subtotal =  @Subtotal,
	Iva =  @Iva,
	Descuentos =  @Descuentos,
	Total =  @Total  
	Where (IDEncFactura =  @IDEncFactura);

IF OBJECT_ID('SP_SeleccionarDetFacturaPorID') is NOT NULL 
Drop Proc  SP_SeleccionarDetFacturaPorID;

CREATE PROCEDURE SP_SeleccionarDetFacturaPorID 
(@IDEncFactura int)
AS 
 	Select IDEncFactura,IDProducto,Cantidad ,Subtotal ,Iva ,Descuentos ,Total  from DetFactura
	Where (IDEncFactura =  @IDEncFactura);

IF OBJECT_ID('SP_SeleccionarTodosDetFactura') is NOT NULL 
Drop Proc  SP_SeleccionarTodosDetFactura;

CREATE PROCEDURE SP_SeleccionarTodosDetFactura
 AS  
 	Select IDEncFactura,IDProducto,Cantidad ,Subtotal ,Iva ,Descuentos ,Total  from DetFactura;

--EncDespacho
IF OBJECT_ID('SP_InsertarEncDespacho') is NOT NULL 
DROP PROC  SP_InsertarEncDespacho;

CREATE PROCEDURE SP_InsertarEncDespacho
 (@ID int,@IDEncFactura int,@IDUsuario int,@IDDireccion int,@FechaCreacion smalldatetime)
AS 
 insert into EncDespacho(ID ,IDEncFactura ,IDUsuario,IDDireccion ,FechaCreacion ) 
 values(@ID ,@IDEncFactura ,@IDUsuario ,@IDDireccion ,@FechaCreacion  );

IF OBJECT_ID('SP_EliminarEncDespachoPorID') is NOT NULL 
Drop Proc  SP_EliminarEncDespachoPorID;

CREATE PROCEDURE SP_EliminarEncDespachoPorID 
(@ID int)
AS 
 	Delete from EncDespacho
	Where (ID =  @ID);

IF OBJECT_ID('SP_ActualizarEncDespacho') is NOT NULL 
Drop Proc  SP_ActualizarEncDespacho;

CREATE PROCEDURE SP_ActualizarEncDespacho
 (@ID int,@IDEncFactura int,@IDUsuario int,@IDireccion int,@FechaCreacion smalldatetime)
AS 
 	Update  EncDespacho
	Set 	IDEncFactura =  @IDEncFactura,
	IDUsuario =  @IDUsuario,
	IDDireccion = @IDireccion,
	FechaCreacion =  @FechaCreacion  
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarEncDespachoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarEncDespachoPorID;

CREATE PROCEDURE SP_SeleccionarEncDespachoPorID 
(@ID int)
AS  
 	Select ID ,IDEncFactura ,IDUsuario,IDDireccion  ,FechaCreacion  from EncDespacho
	Where (ID =  @ID);

IF OBJECT_ID('SP_SeleccionarTodosEncDespacho') is NOT NULL 
Drop Proc  SP_SeleccionarTodosEncDespacho;

CREATE PROCEDURE SP_SeleccionarTodosEncDespacho
 AS  
 	Select ID ,IDEncFactura ,IDUsuario ,IDDireccion ,FechaCreacion  from EncDespacho;

--DetDespacho
IF OBJECT_ID('SP_InsertarDetDespacho') is NOT NULL 
DROP PROC  SP_InsertarDetDespacho;

CREATE PROCEDURE SP_InsertarDetDespacho
 (@IDEncDespacho int,@FechaSalida smalldatetime,@Envio nvarchar(1),@Obsevaciones nvarchar(200))
AS 
 insert into DetDespacho(IDEncDespacho ,FechaSalida ,Envio,Obsevaciones ) 
 values(@IDEncDespacho ,@FechaSalida ,@Envio,@Obsevaciones  );

IF OBJECT_ID('SP_EliminarDetDespachoPorID') is NOT NULL 
Drop Proc  SP_EliminarDetDespachoPorID;

CREATE PROCEDURE SP_EliminarDetDespachoPorID 
(@IDEncDespacho int)
AS  
 	Delete from DetDespacho
	Where (IDEncDespacho =  @IDEncDespacho);

IF OBJECT_ID('SP_ActualizarDetDespacho') is NOT NULL 
Drop Proc  SP_ActualizarDetDespacho;

CREATE PROCEDURE SP_ActualizarDetDespacho
 (@IDEncDespacho int,@FechaSalida smalldatetime,@Envio nvarchar(1),@Obsevaciones nvarchar(200))
AS 
 	Update  DetDespacho
	Set 	FechaSalida =  @FechaSalida,
	Envio = @Envio,
	Obsevaciones =  @Obsevaciones  
	Where (IDEncDespacho =  @IDEncDespacho);

IF OBJECT_ID('SP_SeleccionarDetDespachoPorID') is NOT NULL 
Drop Proc  SP_SeleccionarDetDespachoPorID;

CREATE PROCEDURE SP_SeleccionarDetDespachoPorID 
(@IDEncDespacho int)
AS 
 	Select IDEncDespacho ,FechaSalida ,Envio,Obsevaciones  from DetDespacho
	Where (IDEncDespacho =  @IDEncDespacho);

IF OBJECT_ID('SP_SeleccionarTodosDetDespacho') is NOT NULL 
Drop Proc  SP_SeleccionarTodosDetDespacho;

CREATE PROCEDURE SP_SeleccionarTodosDetDespacho
 AS 
 	Select IDEncDespacho ,FechaSalida ,Envio,Obsevaciones  from DetDespacho;

---Pruebas
--Direccion
exec SP_InsertarDireccion 1,1,'la luisa';
exec SP_InsertarDireccion 2,1,'Mementos';
exec SP_SeleccionarTodosDireccion
exec SP_SeleccionarTodosDireccionPorUsuarioID 1;
exec SP_EliminarDireccionPorID 1
--Usuario
exec SP_InsertarUsuario 1,1,'admin','123','Administrador','888',true;
exec SP_InsertarUsuario 2,1,'maria','1234','Cliente','777',false; 
exec SP_ActualizarUsuario 1,1,'Carlos','admin','Administrador','87806817',true;
exec SP_EliminarUsuarioPorID ;
exec SP_ActivarUsuarioPorID 2
exec SP_SeleccionarTodosUsuario
exec SP_SeleccionarTodosUsuarioActivo
exec SP_SeleccionarTodosUsuarioInactivo
delete from Usuario where ID=1; 
--Producto
exec SP_InsertarProducto 1,'Pollo Asado',6000,5,1,'https://placeralplato.com/files/2019/11/receta-de-pollo-asado-con-hierbas-aromticas-y-limn.jpg','Comida_rapida';
exec SP_SeleccionarTodosProducto
exec SP_ActualizarProducto 1,'Slide Pizza',1500,6,2,'https://www.pngitem.com/pimgs/m/434-4345697_slide-de-pizza-png-transparent-png.png','Comida_rapida';
exec SP_EliminarProductoPorID 1








































































