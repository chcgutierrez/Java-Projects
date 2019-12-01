/*
================================================================================
Procedimiento: SP_GUARDAR_CLIENTE
Objetivo: Guardar datos en la tabla tb_cliente
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_CLIENTE(
	tipDoCliente VARCHAR(100),
    numDoCliente VARCHAR(30),
    nomCliente VARCHAR(50),
    apeCliente VARCHAR(50),
    sexCliente VARCHAR(50),
    dirCliente VARCHAR(100),
    telCliente VARCHAR(30),
    mailCliente VARCHAR(255),
    estCliente VARCHAR(10),
    fecnCliente DATETIME,
    ciuCliente INTEGER(11),
    obsCliente VARCHAR(255)
    ) 
    
    INSERT INTO tb_cliente(tip_doc_cliente, num_doc_cliente, nom_cliente, ape_cliente, sexo_cliente, direc_cliente,
                           tel_cliente, email_cliente, estado_cliente, fec_nac_cliente, ciudad_cliente, obs_cliente)
		VALUES(tipDoCliente, numDoCliente, nomCliente, apeCliente, sexCliente, dirCliente, telCliente,
			   mailCliente, estCliente, fecnCliente, ciuCliente, obsCliente);

CALL SP_GUARDAR_CLIENTE ('1','12345','CHRISTIAN CAMILO','GUTIERREZ MONTOYA','M','CLL 100 # 123',
					    '4503376','ccgutierez@colsanitas.com','A','1987-07-26',2,'PRUEBA_SP');

select * from tb_cliente;

/*
================================================================================
Procedimiento: SP_BUSCAR_CLIENTE
Objetivo: Buscar datos en la tabla tb_cliente segun criterio
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_CLIENTE(
	numDoCliente VARCHAR(30)) 
    
    SELECT
        tip_doc_cliente, num_doc_cliente, nom_cliente, ape_cliente, sexo_cliente, direc_cliente,
		tel_cliente, email_cliente, estado_cliente, fec_nac_cliente, ciudad_cliente, obs_cliente
	FROM tb_cliente WHERE num_doc_cliente = numDoCliente;
                        
CALL SP_BUSCAR_CLIENTE ('2019');

update tb_cliente set tip_doc_cliente=2 where num_doc_cliente='12345';

/*
================================================================================
Procedimiento: SP_EDITAR_CLIENTE
Objetivo: Modificar datos en la tabla tb_cliente
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_CLIENTE(
	numDoCliente VARCHAR(30),
    tipDoCliente VARCHAR(100),    
    nomCliente VARCHAR(50),
    apeCliente VARCHAR(50),
    sexCliente VARCHAR(50),
    dirCliente VARCHAR(100),
    telCliente VARCHAR(30),
    mailCliente VARCHAR(255),
    estCliente VARCHAR(10),
    fecnCliente DATETIME,
    ciuCliente INTEGER(11),
    obsCliente VARCHAR(255)
)
	UPDATE tb_cliente SET tip_doc_cliente = tipDoCliente,
                          nom_cliente = nomCliente,
                          ape_cliente = apeCliente,
                          sexo_cliente = sexCliente,
                          direc_cliente = dirCliente,
		                  tel_cliente = telCliente,
                          email_cliente = mailCliente,
                          estado_cliente = estCliente,
                          fec_nac_cliente = fecnCliente,
                          ciudad_cliente = ciuCliente,
                          obs_cliente = obsCliente
                           WHERE num_doc_cliente = numDoCliente;

CALL SP_EDITAR_CLIENTE ('ASD123','5','ANA LUCIA','QUITIAN GUTIERREZ','F','CLL 93 # 29 - 15','7761591','anacia@aol.com','I','1973-05-03',5,'SP_EDIT');

select * from tb_CLIENTE;

/*
================================================================================
Procedimiento: SP_BUSQ_CLIENTE
Objetivo: Mostrar los datos de la tabla tb_cliente segun descripcion
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_CLIENTE(
	nomCliente VARCHAR(150)) 
    SELECT
		num_doc_cliente,
		CONCAT(nom_cliente, ' ', ape_cliente) AS nCliente,
        tel_cliente
	FROM tb_cliente WHERE nom_cliente LIKE CONCAT('%', nomCliente, '%');

CALL SP_BUSQ_CLIENTE('ANA');

select * from tb_cliente;

/************************************************************************************************************************************/


#Hacer un ALTER en SP

USE `sysautos`;
DROP procedure IF EXISTS `SP_BUSQ_CLIENTE`;

DELIMITER $$
USE `sysautos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_BUSQ_CLIENTE`(
    nomCliente VARCHAR(150)) 
    SELECT
		num_doc_cliente,
		CONCAT(nom_cliente, ' ', ape_cliente) AS nCliente,
        tel_cliente
	FROM tb_cliente WHERE nom_cliente LIKE CONCAT('%', nomCliente, '%');$$

DELIMITER ;





