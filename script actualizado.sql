

select * from tipo_tratamiento

insert into tipo_tratamiento  (desc_tratamiento,status_idstatus) values ('Troja o pilon',1);
insert into tipo_tratamiento  (desc_tratamiento,status_idstatus) values ('Almacenamiento',1);
insert into tipo_tratamiento  (desc_tratamiento,status_idstatus) values ('Vapor Caliente',1);

Select * from tipo_tratamiento tt 

create table grosor(
id_grosor int primary key identity(1,1)not null,
desc_grosor varchar(20)not null);

insert into color (desc_color) values ('Marron oscuro');
insert into color (desc_color) values ('Amarillo claro');

SELECT * from clasificacion c  

create table color(
color int primary key identity(1,1)not null,
desc_color varchar(20)not null);

alter table color 
alter column 

alter table tipo_tratamiento add duracion_inicial int,
duracion_final int;

ALTER TABLE clasificacion ADD CONSTRAINT fK_color FOREIGN KEY (id_color) REFERENCES color (id_color);

EXEC proceso_tratamiento.sys.sp_rename N'proceso_tratamiento.dbo.color.color' , N'id_color', 'COLUMN';

insert into clasificacion (desc_clasificacion,status_idstatus,id_grosor,id_color,tamano_inicial,tamano_final) values ('Tabaco Pensilvania',1,2,1,10.0,15.0);
insert into clasificacion (desc_clasificacion,status_idstatus,id_grosor,id_color,tamano_inicial,tamano_final) values ('Tabaco Habano',1,1,1,5.1,10.0);

INSERT into materia_prima (suplidor_idsuplidor,unidad_idunidad,clasificacion_idclasificacion,status_idstatus) values (2,1,1,1,1);
INSERT into materia_prima (suplidor_idsuplidor,unidad_idunidad,clasificacion_idclasificacion,status_idstatus) values (1,1,2,1);

SELECT * from clasificacion c

select * from tipo_tratamiento tt  

select * from grosor g 

select * FROM materia_prima mp 	

select * from suplidor s 

select m.idmateria_prima, s.idsuplidor  ,s.razon_social, u.desc_unidad,c.desc_clasificacion, st.desc_status from materia_prima m inner join suplidor s on s.idsuplidor = m.suplidor_idsuplidor 
inner join unidad uon u.idunidad = m.unidad_idunidad inner join clasificacion con c.idclasificacion = m.clasificacion_idclasificacion inner join status st on st.idstatus = s.status_idstatus

select m.idmateria_prima, s.razon_social from materia_prima m 
inner join suplidor s 
on s.idsuplidor = m.suplidor_idsuplidor 

select c.tamano_inicial, c.tamano_final from materia_prima m inner join clasificacion con c.idclasificacion = m.clasificacion_idclasificacion 
WHERE m.idmateria_prima =2

 SELECT * from clasificacion c 
 
 
 select co.desc_color from clasificacion c  inner join color co on co.id_color = c.id_color WHERE c.desc_clasificacion = 'Tabaco Pensilvania';

 select co.id_color, co.desc_color from clasificacion c  right join color co on co.id_color = c.id_color WHERE co.id_color  = 1
 
  select co.id_color, co.desc_color from clasificacion c  inner join color co on co.id_color = c.id_color WHERE co.id_color = 3
  
 TRUNCATE table tipo_tratamiento ;
 
INSERT into tipo_tratamiento(name_tratamiento, desc_tratamiento,status_idstatus,duracion_inicial,duracion_final) values ('Troja o Pilon','Dura 30 dias, cada semana se debe voltear el tabaco durante el tiempo de duracion' ,1,20,30);
INSERT into tipo_tratamiento(name_tratamiento, desc_tratamiento,status_idstatus,duracion_inicial,duracion_final) values ('Almacenado','Dura de 45 a 120 Dias, se almacena en temperatura  de 20 grado durante el rango de tiempo establecido',1,45,120);
INSERT into tipo_tratamiento(name_tratamiento, desc_tratamiento,status_idstatus,duracion_inicial,duracion_final) values ('Vapor Caliente','Dura 10 dias, se debe sacar unos 15 minutos diaramente durante esos dias para que el tabaco tome aire y seque',1,5,10);

select * from tipo_tratamiento tt 

select g.desc_grosor, c.* from clasificacion c  inner join grosor g  on g.id_grosor = c.id_grosor 
 --WHERE c.desc_clasificacion = 

SELECT * FROM prueba_rendimiento pr 

select * from detalle_prueba_rendimiento dpr 

alter table tipo_tratamiento add name_tratamiento varchar(15)