CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_log`(
 llave varchar(1024))
BEGIN 
insert into log(keyss)
values(llave);

 END