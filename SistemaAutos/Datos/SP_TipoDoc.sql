/*
================================================================================
Procedimiento: SP_GUARDAR_TIPODOC
Objetivo: Guardar datos en la tabla tb_tipo_doc
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_TIPODOC(
	codTipoDoc VARCHAR(20),
    nomTipoDoc VARCHAR(100),
    estTipoDoc VARCHAR(10),
    obsTipoDoc VARCHAR(255)) 
    
    INSERT INTO tb_tipo_doc(tipo_doc, desc_tipodoc, estado_tipodoc, fec_act, obs_tipodoc)
		VALUES(codTipoDoc, nomTipoDoc, estTipoDoc, NOW(), obsTipoDoc);

CALL SP_GUARDAR_TIPODOC ('CC','CEDULA DE CIUDADANIA','A','GUARDAR_MYSQL');

select * from tb_tipo_doc;

/*
================================================================================
Procedimiento: SP_EDITAR_TIPODOC
Objetivo: Modificar datos en la tabla tb_tipo_doc
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_TIPODOC(
	codTipoDoc VARCHAR(20),
    nomTipoDoc VARCHAR(100),
    estTipoDoc VARCHAR(10),
    obsTipoDoc VARCHAR(255)) 
    
    UPDATE tb_tipo_doc SET desc_tipodoc = nomTipoDoc,
                           estado_tipodoc = estTipoDoc,
                           fec_act = NOW(),
                           obs_tipodoc = obsTipoDoc
                           WHERE tipo_doc = codTipoDoc;

CALL SP_EDITAR_TIPODOC ('CC','CEDULA DE CIUDADANIA','A','EDITAR_MYSQL');

select * from tb_tipo_doc;

/*
================================================================================
Procedimiento: SP_BUSCAR_TIPODOC
Objetivo: Buscar datos en la tabla tb_tipo_doc segun criterio
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_TIPODOC(
	codTipoDoc VARCHAR(20))
    
     SELECT
        desc_tipodoc,
        estado_tipodoc,
        obs_tipodoc
	FROM tb_tipo_doc WHERE tipo_doc = codTipoDoc;
                        
CALL SP_BUSCAR_TIPODOC ('CC');

/*
================================================================================
Procedimiento: SP_MOSTRAR_TIPODOC
Objetivo: Mostrar los datos en la tabla tb_tipo_doc
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_MOSTRAR_TIPODOC()
    SELECT
		tipo_doc,
        desc_tipodoc,
        estado_tipodoc,
        fec_act,
        obs_tipodoc
	FROM tb_tipo_doc;

CALL SP_MOSTRAR_TIPODOC();

/*
================================================================================
Procedimiento: SP_BUSQ_TIPODOC
Objetivo: Mostrar los datos de la tabla tb_tipo_doc segun descripcion
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_TIPODOC(
	nomTipoDoc VARCHAR(100)) 
    SELECT
		tipo_doc,
        desc_tipodoc
	FROM tb_tipo_doc WHERE estado_tipodoc='A' AND desc_tipodoc LIKE CONCAT('%', nomTipoDoc, '%');

CALL SP_BUSQ_TIPODOC('CED');

select * from tb_tipo_doc;

/*
================================================================================
Procedimiento: SP_CBO_TIPODOC
Objetivo: Llenar jComboBox con datos de la tabla tipo_doc
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_CBO_TIPODOC() 
    SELECT
		idTipoDoc,
        desc_tipodoc
	FROM tb_tipo_doc WHERE estado_tipodoc='A';

CALL SP_CBO_TIPODOC();

#Hacer un ALTER en SP

USE `sysautos`;
DROP procedure IF EXISTS `SP_CBO_TIPODOC`;

DELIMITER $$
USE `sysautos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CBO_TIPODOC`()
	SELECT
		idTipoDoc,
        desc_tipodoc
	FROM tb_tipo_doc WHERE estado_tipodoc='A';$$

DELIMITER ;









