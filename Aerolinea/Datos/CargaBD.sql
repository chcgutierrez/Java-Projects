use aerolinea;

insert into tbEmpresa (emp_tip_doc,emp_num_doc,emp_nombre,emp_dir,emp_telefono,emp_estado,emp_obs)
	values('NIT','800233659-7','AEROANDINA','CLL 26 # 35 -77','6466060','A','CARGA_MYSQL');
    
insert into tbEmpresa (emp_tip_doc,emp_num_doc,emp_nombre,emp_dir,emp_telefono,emp_estado,emp_obs)
	values('NIT','900655239-6','GO FLIGHT','CLL 26 # 84 - 98','4136011','A','CARGA_MYSQL');
    
insert into tbEmpresa (emp_tip_doc,emp_num_doc,emp_nombre,emp_dir,emp_telefono,emp_estado,emp_obs)
	values('NIT','900440239-1','AVIANCA','CLL 76 # 18 -20','8129015','A','CARGA_MYSQL');
    
insert into tbEmpresa (emp_tip_doc,emp_num_doc,emp_nombre,emp_dir,emp_telefono,emp_estado,emp_obs)
	values('DNI','444444518','BRAZIL AIR','RUA 23 CENQUINHA 77','325-989556','A','CARGA_MYSQL');
    
insert into tbEmpresa (emp_tip_doc,emp_num_doc,emp_nombre,emp_dir,emp_telefono,emp_estado,emp_obs)
	values('FUA','000-399-252','USA FLIGHTS','ST. 25 SAMNSON. 88','555-87932-01','A','CARGA_MYSQL');

select * from tbEmpresa;

##--***************************************************************

insert into tborigen (nom_arpuerto_ori,nom_ciudad_ori,idCiudad_ori,ori_obs)
	values('ARTURO MERINO BENITEZ','SANTIAGO','SCL','CARGA_MYSQL');
    
insert into tborigen (nom_arpuerto_ori,nom_ciudad_ori,idCiudad_ori,ori_obs)
	values('EL DORADO','COLOMBIA','COL','CARGA_MYSQL');
    
insert into tborigen (nom_arpuerto_ori,nom_ciudad_ori,idCiudad_ori,ori_obs)
	values('JORGE NEWBERY','BUENOS AIRES','AEP','CARGA_MYSQL');
    
insert into tborigen (nom_arpuerto_ori,nom_ciudad_ori,idCiudad_ori,ori_obs)
	values('JORGE CHAVEZ','LIMA','LIM','CARGA_MYSQL');
    
insert into tborigen (nom_arpuerto_ori,nom_ciudad_ori,idCiudad_ori,ori_obs)
	values('GUARULHOS','SAO PAUO','GRU','CARGA_MYSQL');
    
select * from tborigen;
    
##--***************************************************************

insert into tbdestino (nom_arpuerto_des,nom_ciudad_des,idCiudad_des,des_obs)
	values('ARTURO MERINO BENITEZ','SANTIAGO','SCL','CARGA_MYSQL');
    
insert into tbdestino (nom_arpuerto_des,nom_ciudad_des,idCiudad_des,des_obs)
	values('EL DORADO','COLOMBIA','COL','CARGA_MYSQL');
    
insert into tbdestino (nom_arpuerto_des,nom_ciudad_des,idCiudad_des,des_obs)
	values('JORGE NEWBERY','BUENOS AIRES','AEP','CARGA_MYSQL');
    
insert into tbdestino (nom_arpuerto_des,nom_ciudad_des,idCiudad_des,des_obs)
	values('JORGE CHAVEZ','LIMA','LIM','CARGA_MYSQL');
    
insert into tbdestino (nom_arpuerto_des,nom_ciudad_des,idCiudad_des,des_obs)
	values('GUARULHOS','SAO PAUO','GRU','CARGA_MYSQL');
    
select * from tbdestino;

##--***************************************************************

describe tbcliente;
    
insert into tbcliente (tipo_doc,num_doc,cli_nom,cli_ape,cli_sexo,cli_dire,cli_tel,cli_mail,cli_est,cli_edad,cli_fec_nac,cli_obs)
	values('CC','1012376311','LUISA FERNANDA','PARRA RODRIGUEZ','F','CLL 45 # 76 - 20',
           '7161591','lparrar@mymail.com','A','28','1991-05-01','INICIO_MYSQL');
           
insert into tbcliente (tipo_doc,num_doc,cli_nom,cli_ape,cli_sexo,cli_dire,cli_tel,cli_mail,cli_est,cli_edad,cli_fec_nac,cli_obs)
	values('CC','1020528919','DAVID ANDRES','CAICEDO GUTIERREZ','M','CLL 100 # 15 - 84',
           '8219063','dacaicedo@mymail.com','A','38','1981-04-29','INICIO_MYSQL');
           
insert into tbcliente (tipo_doc,num_doc,cli_nom,cli_ape,cli_sexo,cli_dire,cli_tel,cli_mail,cli_est,cli_edad,cli_fec_nac,cli_obs)
	values('CE','52987653','AURA CRISTINA','DAVILA HOYOS','F','CRA 7 # 122 - 16',
           '2140850','acdavila@mymail.com','A','20','1999-06-09','INICIO_MYSQL');
           
insert into tbcliente (tipo_doc,num_doc,cli_nom,cli_ape,cli_sexo,cli_dire,cli_tel,cli_mail,cli_est,cli_edad,cli_fec_nac,cli_obs)
	values('PA','NHD1200K3','RAMON ANTONIO','VALDES CASTILLO','M','AV. 19 # 6 - 21',
           '4462030','ravaldes@mymail.com','A','60','1959-09-02','INICIO_MYSQL');
           
insert into tbcliente (tipo_doc,num_doc,cli_nom,cli_ape,cli_sexo,cli_dire,cli_tel,cli_mail,cli_est,cli_edad,cli_fec_nac,cli_obs)
	values('PA','DUJ30069-4','MATT ANDREW','BOYD LONDON','M','ST. MAPLE 25. 85',
           '555-0943-20','maboyd@mymail.com','A','35','1984-02-15','INICIO_MYSQL');
    
SELECT * FROM tbcliente;

##--***************************************************************

insert into tbitinerario (iti_hora,iti_fecha,tbOrigen_idOrigen,tbDestino_idDestino,iti_obs)
	values('22:15','2019-10-03',1,2,'CARGA_MYSQL');
    
insert into tbitinerario (iti_hora,iti_fecha,tbOrigen_idOrigen,tbDestino_idDestino,iti_obs)
	values('21:45','2019-10-03',3,5,'CARGA_MYSQL');
    
insert into tbitinerario (iti_hora,iti_fecha,tbOrigen_idOrigen,tbDestino_idDestino,iti_obs)
	values('21:15','2019-10-03',4,1,'CARGA_MYSQL');
    
insert into tbitinerario (iti_hora,iti_fecha,tbOrigen_idOrigen,tbDestino_idDestino,iti_obs)
	values('20:45','2019-10-03',2,4,'CARGA_MYSQL');
    
insert into tbitinerario (iti_hora,iti_fecha,tbOrigen_idOrigen,tbDestino_idDestino,iti_obs)
	values('20:15','2019-10-03',1,3,'CARGA_MYSQL');
    
select * from tbitinerario;

##--***************************************************************

insert into tbvuelo (num_vuelo,cap_vuelo,mod_avion,tbEmprsa_idEmprsa,tbItinerario_idItinerario,vuelo_obs)
	values('2001','230','BOEING - 727',3,1,'CARGA_MYSQL');
    
insert into tbvuelo (num_vuelo,cap_vuelo,mod_avion,tbEmprsa_idEmprsa,tbItinerario_idItinerario,vuelo_obs)
	values('2002','320','AIRBUS - A230',1,2,'CARGA_MYSQL');
    
insert into tbvuelo (num_vuelo,cap_vuelo,mod_avion,tbEmprsa_idEmprsa,tbItinerario_idItinerario,vuelo_obs)
	values('2003','400','BOEING - 767STS',2,5,'CARGA_MYSQL');
    
insert into tbvuelo (num_vuelo,cap_vuelo,mod_avion,tbEmprsa_idEmprsa,tbItinerario_idItinerario,vuelo_obs)
	values('2004','180','BOEING - 727',4,3,'CARGA_MYSQL');
    
insert into tbvuelo (num_vuelo,cap_vuelo,mod_avion,tbEmprsa_idEmprsa,tbItinerario_idItinerario,vuelo_obs)
	values('2005','420','AIRBUS - AC330',5,4,'CARGA_MYSQL');
    
 select * from tbvuelo;
 
 ##--***************************************************************

insert into tbpasaje (psj_clase,psj_silla,psj_valor,psj_obs,tbCliente_idCliente,tbVuelo_idVuelo)
	values('PRIMERA','101','545.300','CARGA_MYSQL',1,3);
    
insert into tbpasaje (psj_clase,psj_silla,psj_valor,psj_obs,tbCliente_idCliente,tbVuelo_idVuelo)
	values('PRIMERA','102','545.300','CARGA_MYSQL',2,3);
    
insert into tbpasaje (psj_clase,psj_silla,psj_valor,psj_obs,tbCliente_idCliente,tbVuelo_idVuelo)
	values('PRIMERA','103','545.300','CARGA_MYSQL',3,3);
    
insert into tbpasaje (psj_clase,psj_silla,psj_valor,psj_obs,tbCliente_idCliente,tbVuelo_idVuelo)
	values('ECONOMICA','201','328.425','CARGA_MYSQL',4,2);
    
insert into tbpasaje (psj_clase,psj_silla,psj_valor,psj_obs,tbCliente_idCliente,tbVuelo_idVuelo)
	values('ECONOMICA','202','328.425','CARGA_MYSQL',5,2);
    
 select * from tbpasaje;
 
 #---**************************************************************---#
 
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
	SELECT
		C.tipo_doc AS Documento,
		C.num_doc AS Num_Documento,
		CONCAT(C.cli_nom, ' ', C.cli_ape) AS Pasajero,
		C.cli_tel AS Telefono,
		C.cli_mail AS E_mail,
		V.num_vuelo AS Num_Vuelo,
		E.emp_nombre AS Empresa,
		V.mod_avion AS Avion,
		P.psj_silla AS Asiento,
		P.psj_clase AS Clase,
		CONCAT(I.iti_fecha, ' - ', I.iti_hora) AS Fecha_Hora_Vuelo,
		O.nom_arpuerto_ori AS Aeropuerto_Salida,
		O.nom_ciudad_ori AS Ciudad_Salida,
		D.nom_arpuerto_des AS Aeropuerto_Llegada,
		D.nom_ciudad_des AS Ciudad_Llegada
	FROM tbcliente AS C
			INNER JOIN tbpasaje AS P
				ON C.idCliente = P.tbCliente_idCliente
			INNER JOIN tbvuelo AS V
				ON P.tbVuelo_idVuelo = V.idVuelo
			INNER JOIN tbempresa AS E
				ON E.idEmpresa = V.tbEmprsa_idEmprsa
			INNER JOIN tbitinerario AS I
				ON I.idItinerario = V.tbItinerario_idItinerario
			INNER JOIN tborigen AS O
				ON O.idOrigen = I.tbOrigen_idOrigen
			INNER JOIN tbdestino AS D
				ON D.idDestino = I.tbDestino_idDestino
	WHERE C.tipo_doc='CC' AND
          C.num_doc='1020528919';
COMMIT;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    