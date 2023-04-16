ALTER TABLE "tratamiento" 
   ADD CONSTRAINT fk_ttratamiento
   FOREIGN KEY (idtipo_tratamiento) 
   REFERENCES "tipo_tratamiento"(idtipo_tratamiento);

INSERT INTO estatus(desc_status)
VALUES ('desactivado');

INSERT INTO tipo_tratamiento (desc_tratamiento, cant_dias_aprox, idstatus)
VALUES ('almacenado',60,1)

SELECT * from sector;

INSERT INTO unidad(desc_unidad)
VALUES ('unidad');

INSERT INTO direccion(nomdireccion, sector_idsector)
VALUES ('Carretera tamboril Km 4/2 #421', 3);

INSERT INTO textura(desc_grosor)
VALUES ('grueso');

ALTER TABLE "detalle_prueba_rendimiento"
   add  rigidez_final int,
   libras_tayo float,
   libras_picadura float;

   select * from detalle_prueba_rendimiento
