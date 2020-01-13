/*
================================================================================
Procedimiento: SP_GUARDAR_EMPLE
Objetivo: Guardar datos en la tabla tb_mecanico
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_EMPLE(
	tipDoEmple VARCHAR(100),
    numDoEmple VARCHAR(30),
    nomEmple VARCHAR(50),
    apeEmple VARCHAR(50),
    sexEmple VARCHAR(50),
    estEmple VARCHAR(10),
    ciuEmple INTEGER(11),
    obsEmple VARCHAR(255)
    ) 
    
    INSERT INTO tb_mecanico(tip_doc_mecanico, num_doc_mecanico, nom_mecanico, ape_mecanico, sexo_mecanico,
                            estado_mecanico, ciudad_mecanico, fec_act, obs_mecanico)
		VALUES(tipDoEmple, numDoEmple, nomEmple, apeEmple, sexEmple, estEmple, ciuEmple, NOW(), obsEmple);

CALL SP_GUARDAR_EMPLE ('3','4391499','FRANCISCO JAVIER','OSPINA PEREZ','M','A',2,'SP_GUARDAR');

SELECT * FROM tb_mecanico;

/*
================================================================================
Procedimiento: SP_EDITAR_EMPLE
Objetivo: Modificar datos en la tabla tb_mecanico
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_EMPLE(
	numDoEmple VARCHAR(30),
	tipDoEmple VARCHAR(100),   
    nomEmple VARCHAR(50),
    apeEmple VARCHAR(50),
    sexEmple VARCHAR(50),
    estEmple VARCHAR(10),
    ciuEmple INTEGER(11),
    obsEmple VARCHAR(255)
    ) 
    
    UPDATE tb_mecanico SET tip_doc_mecanico = tipDoEmple,
                           nom_mecanico = nomEmple,
                           ape_mecanico = apeEmple,
                           sexo_mecanico = sexEmple,
                           estado_mecanico = estEmple,
                           ciudad_mecanico = ciuEmple,
                           fec_act = NOW(),
                           obs_mecanico = obsEmple
                           WHERE num_doc_mecanico = numDoEmple;

CALL SP_EDITAR_EMPLE ('4391499','4','FRANCISCO JAVIER','OSPINA PEREZ','F','I',3,'SP_EDITAR');

SELECT * FROM tb_mecanico;

/*
================================================================================
Procedimiento: SP_BUSCAR_EMPLE
Objetivo: Buscar datos en la tabla tb_mecanico segun criterio 
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_EMPLE(
	numDoEmple VARCHAR(30)
    ) 
    
    SELECT tip_doc_mecanico, num_doc_mecanico, nom_mecanico, ape_mecanico, sexo_mecanico,
		    estado_mecanico, ciudad_mecanico, fec_act, obs_mecanico FROM tb_mecanico WHERE num_doc_mecanico = numDoEmple;

CALL SP_BUSCAR_EMPLE ('4391499');

/*
================================================================================
Procedimiento: SP_BUSQ_EMPLE
Objetivo: Mostrar los datos de la tabla tb_mecanico segun descripcion
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_EMPLE(
	nomEmple VARCHAR(150)) 
    SELECT
        
		num_doc_mecanico,
		CONCAT(nom_mecanico, ' ', ape_mecanico) AS nEmpleado
	FROM tb_mecanico WHERE nom_mecanico LIKE CONCAT('%', nomEmple, '%');

CALL SP_BUSQ_EMPLE('JAV');

/*
================================================================================
Procedimiento: SP_BUSCAR_CLIENTECARRO
Objetivo: Buscar datos en la tabla tb_vehiculo segun criterio
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

CREATE PROCEDURE SP_BUSCAR_MECAN_CARRO(
	codMecan VARCHAR(15))
    
    SELECT
		idMecanico,
        num_doc_mecanico,
        CONCAT(nom_mecanico, ' ', ape_mecanico) AS carMecanico
	FROM tb_mecanico WHERE estado_mecanico = 'A' AND num_doc_mecanico = codMecan;
    
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;

SELECT * FROM tb_mecanico;




















































#Hacer un ALTER en SP

USE `sysautos`;
DROP procedure IF EXISTS `SP_BUSCAR_EMPLE`;

DELIMITER $$
USE `sysautos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_BUSCAR_EMPLE`(
	numDoEmple VARCHAR(30)
    ) 
    
    SELECT tip_doc_mecanico, num_doc_mecanico, nom_mecanico, ape_mecanico, sexo_mecanico,
		    estado_mecanico, ciudad_mecanico, fec_act, obs_mecanico FROM tb_mecanico WHERE num_doc_mecanico = numDoEmple;$$

DELIMITER ;
