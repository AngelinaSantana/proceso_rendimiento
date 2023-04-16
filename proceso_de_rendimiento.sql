--  Creado con Kata Kuntur - Modelador de Datos
--  Versión: 2.9.1
--  Sitio Web: http://katakuntur.jeanmazuelos.com/
--  Si usted encuentra algún error le agradeceriamos lo reporte en:
--  http://pm.jeanmazuelos.com/katakuntur/issues/new

--  Administrador de Base de Datos: Microsoft SQLServer
--  Diagrama: software1
--  Autor: Angelina
--  Fecha y hora: 11/04/2023 20:59:03

-- GENERANDO TABLAS
CREATE TABLE [persona] (
	[idpersona] INT IDENTITY NOT NULL,
	[nompersona] VARCHAR(50) NOT NULL,
	[apepersona] VARCHAR(50) NOT NULL,
	[telefono] INT NOT NULL,
	[genero] VARCHAR(15) NOT NULL,
	[documento_id_doc] INT NOT NULL,
	[direccion_iddireccion] INT NOT NULL,
	PRIMARY KEY([idpersona])
);
CREATE TABLE [documento] (
	[id_doc] INT IDENTITY NOT NULL,
	[desc_doc] VARCHAR(20) NOT NULL,
	[tipo_documento_idtip_doc] INT NOT NULL,
	PRIMARY KEY([id_doc])
);
CREATE TABLE [tipo_documento] (
	[idtip_doc] INT IDENTITY NOT NULL,
	[desc_doc] VARCHAR(20) NOT NULL,
	PRIMARY KEY([idtip_doc])
);
CREATE TABLE [direccion] (
	[iddireccion] INT IDENTITY NOT NULL,
	[nomdireccion] VARCHAR(50) NOT NULL,
	[sector_idsector] INT NOT NULL,
	PRIMARY KEY([iddireccion])
);
CREATE TABLE [sector] (
	[idsector] INT IDENTITY NOT NULL,
	[nomsector] VARCHAR(100) NOT NULL,
	[ciudad_idciudad] INT NOT NULL,
	PRIMARY KEY([idsector])
);
CREATE TABLE [ciudad] (
	[idciudad] INT IDENTITY NOT NULL,
	[nomciudad] VARCHAR(100) NOT NULL,
	[provincia_idprov] INT NOT NULL,
	PRIMARY KEY([idciudad])
);
CREATE TABLE [provincia] (
	[idprov] INT IDENTITY NOT NULL,
	[nomprov] VARCHAR(100) NOT NULL,
	[region_idregion] INT NOT NULL,
	PRIMARY KEY([idprov])
);
CREATE TABLE [region] (
	[idregion] INT IDENTITY NOT NULL,
	[nomregion] VARCHAR(100) NOT NULL,
	[pais_idpais] INT NOT NULL,
	PRIMARY KEY([idregion])
);
CREATE TABLE [pais] (
	[idpais] INT IDENTITY NOT NULL,
	[nompais] VARCHAR(100) NOT NULL,
	PRIMARY KEY([idpais])
);
CREATE TABLE [suplidor] (
	[idsuplidor] INT IDENTITY NOT NULL,
	[razon_social] VARCHAR(100) NOT NULL,
	[procedencia] VARCHAR(50) NOT NULL,
	[persona_idpersona] INT NOT NULL,
	[documento_id_doc] INT NOT NULL,
	[status_idstatus] INT NOT NULL,
	PRIMARY KEY([idsuplidor])
);
CREATE TABLE [empleados] (
	[idempleados] INT IDENTITY NOT NULL,
	[cargo] VARCHAR(50) NOT NULL,
	[persona_idpersona] INT NOT NULL,
	[documento_id_doc] INT NOT NULL,
	[status_idstatus] INT NOT NULL,
	PRIMARY KEY([idempleados])
);
CREATE TABLE [prueba_rendimiento] (
	[idprueba] INT IDENTITY NOT NULL,
	[fecha_prueba] DATE NOT NULL,
	[empleados_idempleados] INT NOT NULL,
	PRIMARY KEY([idprueba])
);
CREATE TABLE [materia_prima] (
	[idmateria_prima] INT IDENTITY NOT NULL,
	[desc_materia_prima] VARCHAR(100) NOT NULL,
	[cantidad] FLOAT NOT NULL,
	[suplidor_idsuplidor] INT NOT NULL,
	[unidad_idunidad] INT NOT NULL,
	[status_idstatus] INT NOT NULL,
	[tipo_materia_prima_idtipo_materia_prima] INT NOT NULL,
	PRIMARY KEY([idmateria_prima])
);
CREATE TABLE [unidad] (
	[idunidad] INT IDENTITY NOT NULL,
	[desc_unidad] VARCHAR(50) NOT NULL,
	PRIMARY KEY([idunidad])
);
CREATE TABLE [tipo_materia_prima] (
	[idtipo_materia_prima] INT IDENTITY NOT NULL,
	[desc_tipo_materia_prima] VARCHAR(50) NOT NULL,
	[rango_inicial] INT NOT NULL,
	[rango_inicial] INT NOT NULL,
	[status_idstatus] INT NOT NULL,
	[textura_id_grosor] INT NOT NULL,
	PRIMARY KEY([idtipo_materia_prima])
);
CREATE TABLE [status] (
	[idstatus] INT IDENTITY NOT NULL,
	[desc_status] VARCHAR(50) NOT NULL,
	PRIMARY KEY([idstatus])
);
CREATE TABLE [tipo_prueba_rendimiento] (
	[idtipo_prueba] INT IDENTITY NOT NULL,
	[desc_prueba] VARCHAR(150) NOT NULL,
	[status_idstatus] INT NOT NULL,
	PRIMARY KEY([idtipo_prueba])
);
CREATE TABLE [detalle_prueba_rendimiento] (
	[textura] VARCHAR(50) NOT NULL,
	[rigidez] INT NOT NULL,
	[cantidad_libras] FLOAT NOT NULL,
	[calidad] VARCHAR(3) NOT NULL,
	[comentario_prueba] VARCHAR(100) NOT NULL,
	[prueba_rendimiento_idprueba] INT NOT NULL,
	[tipo_prueba_rendimiento_idtipo_prueba] INT NOT NULL,
	[materia_prima_idmateria_prima] INT NOT NULL,
	[suplidor_idsuplidor] INT NOT NULL
);
CREATE TABLE [tipo_tratamiento] (
	[idtipo_tratamiento] INT IDENTITY NOT NULL,
	[desc_tratamiento] VARCHAR(50) NOT NULL,
	[cant_dias_aprox] INT NOT NULL,
	[status_idstatus] INT NOT NULL,
	PRIMARY KEY([idtipo_tratamiento])
);
CREATE TABLE [tratamiento] (
	[idtratamiento] INT IDENTITY NOT NULL,
	[fecha_tratamiento] DATE NOT NULL,
	[comentario_tratamiento] VARCHAR(150) NOT NULL,
	[duracion] TIME NOT NULL,
	[materia_prima_idmateria_prima] INT NOT NULL,
	[tipo_tratamiento_idtipo_tratamiento] INT NOT NULL,
	PRIMARY KEY([idtratamiento])
);
CREATE TABLE [textura] (
	[id_grosor] INT IDENTITY NOT NULL,
	[desc_grosor] VARVHAR( 15) NOT NULL,
	PRIMARY KEY([id_grosor])
);

-- GENERANDO RELACIONES
ALTER TABLE [persona] ADD CONSTRAINT [persona_documento_documento_id_doc] FOREIGN KEY ([documento_id_doc]) REFERENCES [documento]([id_doc]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [persona] ADD CONSTRAINT [persona_direccion_direccion_iddireccion] FOREIGN KEY ([direccion_iddireccion]) REFERENCES [direccion]([iddireccion]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [documento] ADD CONSTRAINT [documento_tipo_documento_tipo_documento_idtip_doc] FOREIGN KEY ([tipo_documento_idtip_doc]) REFERENCES [tipo_documento]([idtip_doc]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [direccion] ADD CONSTRAINT [direccion_sector_sector_idsector] FOREIGN KEY ([sector_idsector]) REFERENCES [sector]([idsector]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [sector] ADD CONSTRAINT [sector_ciudad_ciudad_idciudad] FOREIGN KEY ([ciudad_idciudad]) REFERENCES [ciudad]([idciudad]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [ciudad] ADD CONSTRAINT [ciudad_provincia_provincia_idprov] FOREIGN KEY ([provincia_idprov]) REFERENCES [provincia]([idprov]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [provincia] ADD CONSTRAINT [provincia_region_region_idregion] FOREIGN KEY ([region_idregion]) REFERENCES [region]([idregion]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [region] ADD CONSTRAINT [region_pais_pais_idpais] FOREIGN KEY ([pais_idpais]) REFERENCES [pais]([idpais]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [suplidor] ADD CONSTRAINT [suplidor_persona_persona_idpersona] FOREIGN KEY ([persona_idpersona]) REFERENCES [persona]([idpersona]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [suplidor] ADD CONSTRAINT [suplidor_documento_documento_id_doc] FOREIGN KEY ([documento_id_doc]) REFERENCES [documento]([id_doc]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [suplidor] ADD CONSTRAINT [suplidor_status_status_idstatus] FOREIGN KEY ([status_idstatus]) REFERENCES [status]([idstatus]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [empleados] ADD CONSTRAINT [empleados_persona_persona_idpersona] FOREIGN KEY ([persona_idpersona]) REFERENCES [persona]([idpersona]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [empleados] ADD CONSTRAINT [empleados_documento_documento_id_doc] FOREIGN KEY ([documento_id_doc]) REFERENCES [documento]([id_doc]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [empleados] ADD CONSTRAINT [empleados_status_status_idstatus] FOREIGN KEY ([status_idstatus]) REFERENCES [status]([idstatus]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [prueba_rendimiento] ADD CONSTRAINT [prueba_rendimiento_empleados_empleados_idempleados] FOREIGN KEY ([empleados_idempleados]) REFERENCES [empleados]([idempleados]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [materia_prima] ADD CONSTRAINT [materia_prima_suplidor_suplidor_idsuplidor] FOREIGN KEY ([suplidor_idsuplidor]) REFERENCES [suplidor]([idsuplidor]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [materia_prima] ADD CONSTRAINT [materia_prima_unidad_unidad_idunidad] FOREIGN KEY ([unidad_idunidad]) REFERENCES [unidad]([idunidad]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [materia_prima] ADD CONSTRAINT [materia_prima_status_status_idstatus] FOREIGN KEY ([status_idstatus]) REFERENCES [status]([idstatus]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [materia_prima] ADD CONSTRAINT [materia_prima_tipo_materia_prima_tipo_materia_prima_idtipo_materia_prima] FOREIGN KEY ([tipo_materia_prima_idtipo_materia_prima]) REFERENCES [tipo_materia_prima]([idtipo_materia_prima]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [tipo_materia_prima] ADD CONSTRAINT [tipo_materia_prima_status_status_idstatus] FOREIGN KEY ([status_idstatus]) REFERENCES [status]([idstatus]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [tipo_materia_prima] ADD CONSTRAINT [tipo_materia_prima_textura_textura_id_grosor] FOREIGN KEY ([textura_id_grosor]) REFERENCES [textura]([id_grosor]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [tipo_prueba_rendimiento] ADD CONSTRAINT [tipo_prueba_rendimiento_status_status_idstatus] FOREIGN KEY ([status_idstatus]) REFERENCES [status]([idstatus]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [detalle_prueba_rendimiento] ADD CONSTRAINT [detalle_prueba_rendimiento_prueba_rendimiento_prueba_rendimiento_idprueba] FOREIGN KEY ([prueba_rendimiento_idprueba]) REFERENCES [prueba_rendimiento]([idprueba]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [detalle_prueba_rendimiento] ADD CONSTRAINT [detalle_prueba_rendimiento_tipo_prueba_rendimiento_tipo_prueba_rendimiento_idtipo_prueba] FOREIGN KEY ([tipo_prueba_rendimiento_idtipo_prueba]) REFERENCES [tipo_prueba_rendimiento]([idtipo_prueba]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [detalle_prueba_rendimiento] ADD CONSTRAINT [detalle_prueba_rendimiento_materia_prima_materia_prima_idmateria_prima] FOREIGN KEY ([materia_prima_idmateria_prima]) REFERENCES [materia_prima]([idmateria_prima]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [detalle_prueba_rendimiento] ADD CONSTRAINT [detalle_prueba_rendimiento_suplidor_suplidor_idsuplidor] FOREIGN KEY ([suplidor_idsuplidor]) REFERENCES [suplidor]([idsuplidor]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [tipo_tratamiento] ADD CONSTRAINT [tipo_tratamiento_status_status_idstatus] FOREIGN KEY ([status_idstatus]) REFERENCES [status]([idstatus]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [tratamiento] ADD CONSTRAINT [tratamiento_materia_prima_materia_prima_idmateria_prima] FOREIGN KEY ([materia_prima_idmateria_prima]) REFERENCES [materia_prima]([idmateria_prima]) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE [tratamiento] ADD CONSTRAINT [tratamiento_tipo_tratamiento_tipo_tratamiento_idtipo_tratamiento] FOREIGN KEY ([tipo_tratamiento_idtipo_tratamiento]) REFERENCES [tipo_tratamiento]([idtipo_tratamiento]) ON DELETE NO ACTION ON UPDATE CASCADE;