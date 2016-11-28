CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_almacen`(
Titulo VARCHAR(256),
Usuario VARCHAR(256),
Pass VARCHAR(1024),
Url VARCHAR(1024),
Expira VARCHAR(256) ,
IdKey VARCHAR(1024) )
BEGIN 
insert into almacen (titulo,usuario,pass,url,expira,idKey)
values(Titulo,Usuario,Pass,Url,Expira,IdKey);

 END