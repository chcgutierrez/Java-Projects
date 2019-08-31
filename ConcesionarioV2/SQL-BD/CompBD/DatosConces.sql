use concesionario2;

##-----------------  Tabla Ciudad   -----------------##

insert into tb_ciudad(idCiudad,nom_ciudad,estado_ciudad,obs_ciudad)
	values('6001','BOGOTA','A','CRGA_INICIAL_BD');

insert into tb_ciudad(idCiudad,nom_ciudad,estado_ciudad,obs_ciudad)
	values('6002','MEDELLIN','A','CRGA_INICIAL_BD');
    
insert into tb_ciudad(idCiudad,nom_ciudad,estado_ciudad,obs_ciudad)
	values('6003','BARRANQUILLA','A','CRGA_INICIAL_BD');
    
insert into tb_ciudad(idCiudad,nom_ciudad,estado_ciudad,obs_ciudad)
	values('6004','PEREIRA','A','CRGA_INICIAL_BD');
    
insert into tb_ciudad(idCiudad,nom_ciudad,estado_ciudad,obs_ciudad)
	values('6005','POPAYAN','A','CRGA_INICIAL_BD');
    
SELECT * FROM tb_ciudad;

##-----------------  Tabla Color   -----------------##

insert into tb_color(idColor,nom_color,estado_color,obs_color)
	values('7001','AMARILLO','A','CRGA_INICIAL_BD');
    
insert into tb_color(idColor,nom_color,estado_color,obs_color)
	values('7002','ROJO','A','CRGA_INICIAL_BD');
    
insert into tb_color(idColor,nom_color,estado_color,obs_color)
	values('7003','NEGRO','A','CRGA_INICIAL_BD');
    
insert into tb_color(idColor,nom_color,estado_color,obs_color)
	values('7004','VERDE','A','CRGA_INICIAL_BD');
    
insert into tb_color(idColor,nom_color,estado_color,obs_color)
	values('7005','PLATEADO','A','CRGA_INICIAL_BD');
    
select * from tb_color;

##-----------------  Tabla Marca   -----------------##

insert into tb_marca(idMarca,nom_marca,estado_marca,obs_marca)
	values('8001','MAZDA','A','CRGA_INICIAL_BD');
    
insert into tb_marca(idMarca,nom_marca,estado_marca,obs_marca)
	values('8002','CHEVROLET','A','CRGA_INICIAL_BD');
    
insert into tb_marca(idMarca,nom_marca,estado_marca,obs_marca)
	values('8003','FIAT','A','CRGA_INICIAL_BD');
    
insert into tb_marca(idMarca,nom_marca,estado_marca,obs_marca)
	values('8004','RENAULT','A','CRGA_INICIAL_BD');
    
insert into tb_marca(idMarca,nom_marca,estado_marca,obs_marca)
	values('8005','FORD','A','CRGA_INICIAL_BD');
    
select * from tb_marca;

##-----------------  Tabla Tipo -----------------##

insert into tb_tipo(idTipo,nom_tipo,estado_tipo,obs_tipo)
	values('5001','SEDAN','A','CARGA_INICIAL_BD');
    
insert into tb_tipo(idTipo,nom_tipo,estado_tipo,obs_tipo)
	values('5002','COUPÉ','A','CARGA_INICIAL_BD');
    
insert into tb_tipo(idTipo,nom_tipo,estado_tipo,obs_tipo)
	values('5003','HATCHBACK','A','CARGA_INICIAL_BD');
    
insert into tb_tipo(idTipo,nom_tipo,estado_tipo,obs_tipo)
	values('5004','PICKUP','A','CARGA_INICIAL_BD');
    
insert into tb_tipo(idTipo,nom_tipo,estado_tipo,obs_tipo)
	values('5005','SUV','A','CARGA_INICIAL_BD');
    
select * from tb_tipo;

##-----------------  Tabla Repuesto -----------------##

insert into tbl_repuestos (idRepuestos,tipo,nombre,descripcion,cantidad)
	values('9001','ELECTRICO','BOBINA 6 V','BOBINA 6 V. GENERICO','15');
    
insert into tbl_repuestos (idRepuestos,tipo,nombre,descripcion,cantidad)
	values('9002','MECANICO','CARBURADOR ST-24','CARBURADOR ST-24. GENERICO','10');
    
insert into tbl_repuestos (idRepuestos,tipo,nombre,descripcion,cantidad)
	values('9003','MECANICO','CAMPANA FRENO. R-13','CAMPANA FRENO. R-13. GENERICO','25');
    
insert into tbl_repuestos (idRepuestos,tipo,nombre,descripcion,cantidad)
	values('9004','MECANICO','CUGÜEÑAL SRV-7AF','CUGÜEÑAL SRV-7AF. CHEVROLET','15');
    
insert into tbl_repuestos (idRepuestos,tipo,nombre,descripcion,cantidad)
	values('9005','ELECTRICO','ALTERNADOR SRJ-70. DTA','ALTERNADOR. MAZDA, RENAULT','22');
    
select * from tbl_repuestos;

##-----------------  Tabla Vehiculo -----------------##

insert into tb_cliente(num_doc_cliente,tip_doc_cliente,nom_cliente,ape_cliente,sexo_cliente,direc_cliente,tel_cliente,email_cliente,estado_cliente,fec_nac_cliente,ciudad_cliente,obs_cliente)
	values('656355','CC','JUAN ALBERTO','RIVERA CAMPOS','M','CLL 45 # 39B-28','7248366','jarivera@mipanalocal.com','A','1975-03-18','6002','CARGA_BD');
    
insert into tb_cliente(num_doc_cliente,tip_doc_cliente,nom_cliente,ape_cliente,sexo_cliente,direc_cliente,tel_cliente,email_cliente,estado_cliente,fec_nac_cliente,ciudad_cliente,obs_cliente)
	values('1030568921','CC','CARLOS DANIEL','GALINDO MERCHAN','M','CRA 17A # 63-11 SUR','3235610288','cdgalindo21@yahoo.com','A','1990-05-01','6004','CARGA_BD');
    
insert into tb_cliente(num_doc_cliente,tip_doc_cliente,nom_cliente,ape_cliente,sexo_cliente,direc_cliente,tel_cliente,email_cliente,estado_cliente,fec_nac_cliente,ciudad_cliente,obs_cliente)
	values('52897651','CC','AMADA ROSA','PEREZ HIGUERA','F','CRA 7 # 98A-05','5418819','amperezh@mijava.com','A','1980-06-24','6001','CARGA_BD');
    
select * from tb_cliente;

##-----------------  Tabla Vehiculo -----------------##

insert into tb_vehiculo(placa_vehiculo,ciudad_vehiculo,marca_vehiculo,tipo_vehiculo,modelo_vehiculo,color_vehiculo,num_motor,estado_vehiculo,obs_vehiculo,cliente)
	values('SGL-802','6002','8004','5002','2018','7005','RNL2018890DJU-05','A','CARGA_INICIAL_BD','1030568921');

insert into tb_vehiculo(placa_vehiculo,ciudad_vehiculo,marca_vehiculo,tipo_vehiculo,modelo_vehiculo,color_vehiculo,num_motor,estado_vehiculo,obs_vehiculo,cliente)
	values('RDU-361','6003','8002','5003','2017','7003','AFT-JK6578LDT','A','CARGA_INICIAL_BD','52897651');
    
insert into tb_vehiculo(placa_vehiculo,ciudad_vehiculo,marca_vehiculo,tipo_vehiculo,modelo_vehiculo,color_vehiculo,num_motor,estado_vehiculo,obs_vehiculo,cliente)
	values('XJZ-011','6004','8005','5004','2017','7004','OSR4587621-ZDN','A','CARGA_INICIAL_BD','656355');
    
insert into tb_vehiculo(placa_vehiculo,ciudad_vehiculo,marca_vehiculo,tipo_vehiculo,modelo_vehiculo,color_vehiculo,num_motor,estado_vehiculo,obs_vehiculo,cliente)
	values('NFS-446','6005','8003','5001','2018','7001','GFN558902-ER','A','CARGA_INICIAL_BD','656355');
    
select * from tb_vehiculo;
    
    
    
    
    
    
    
    
    
    
    
    
    
    