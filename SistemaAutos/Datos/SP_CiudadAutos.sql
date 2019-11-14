/*
================================================================================
Procedimiento: SP_GUARDAR_CIUDAD
Objetivo: Guardar datos en la tabla tb_ciudad
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================

--Hacer un ALTER en SP

USE `sysautos`;
DROP procedure IF EXISTS `SP_GUARDAR_CIUDAD`;

DELIMITER $$
USE `sysautos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_GUARDAR_CIUDAD`(
	codCiudad VARCHAR(20),
    nomCiudad VARCHAR(100),
    estCiudad VARCHAR(20),
    obsCiudad VARCHAR(255))
INSERT INTO tb_ciudad(cod_ciudad, nom_ciudad, estado_ciudad, fec_act, obs_ciudad)
		VALUES(codCiudad, nomCiudad, estCiudad, NOW(), obsCiudad)$$

DELIMITER ;
*/

CREATE PROCEDURE SP_GUARDAR_CIUDAD(
	codCiudad VARCHAR(20),
    nomCiudad VARCHAR(100),
    estCiudad VARCHAR(20),
    obsCiudad VARCHAR(255)) 
    
    INSERT INTO tb_ciudad(cod_ciudad, nom_ciudad, estado_ciudad, fec_act, obs_ciudad)
		VALUES(codCiudad, nomCiudad, estCiudad, NOW(), obsCiudad);

CALL SP_GUARDAR_CIUDAD ('4001','BOGOTA','A','SP_MYSQL');

select * from tb_ciudad;

/*
================================================================================
Procedimiento: SP_EDITAR_CIUDAD
Objetivo: Editar datos en la tabla tb_ciudad
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_CIUDAD(
	codCiudad VARCHAR(20),
    nomCiudad VARCHAR(100),
    estCiudad VARCHAR(20),
    obsCiudad VARCHAR(255)) 
    
    UPDATE tb_ciudad SET nom_ciudad = nomCiudad,
                        estado_ciudad = estCiudad,
                        fec_act = NOW(),
                        obs_ciudad = obsCiudad WHERE cod_ciudad = codCiudad;
                        
CALL SP_EDITAR_CIUDAD ('4001','BOGOTÁ','A','SP_UPD');
select * from tb_ciudad;

/*
================================================================================
Procedimiento: SP_BUSCAR_CIUDAD
Objetivo: Buscar datos en la tabla tb_ciudad segun criterio
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_CIUDAD(
	codCiudad VARCHAR(20)) 
    
    SELECT
        nom_ciudad,
        estado_ciudad,
        obs_ciudad
	FROM tb_ciudad WHERE cod_ciudad = codCiudad;
                        
CALL SP_BUSCAR_CIUDAD ('4001');

/*
================================================================================
Procedimiento: SP_MOSTRAR_CIUDAD
Objetivo: Mostrar los datos en la tabla tb_ciudad
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_MOSTRAR_CIUDAD()
    SELECT
		cod_ciudad,
        nom_ciudad,
        estado_ciudad,
        fec_act,
        obs_ciudad
	FROM tb_ciudad;

CALL SP_MOSTRAR_CIUDAD();

/*
================================================================================
Procedimiento: SP_BUSQ_CIUDAD
Objetivo: Mostrar los datos de la tabla tb_ciudad segun descripcion
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_CIUDAD(
	nomCiudad VARCHAR(100)) 
    SELECT
		cod_ciudad,
        nom_ciudad
	FROM tb_ciudad WHERE estado_ciudad='A' AND nom_ciudad LIKE CONCAT('%', nomCiudad, '%');

CALL SP_BUSQ_CIUDAD('T');

select * from tb_ciudad;








