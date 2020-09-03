/*
================================================================================
Procedimiento: SP_GUARDAR_REPTO
Objetivo: Guardar datos en la tabla tb_repuesto
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_REPTO(
	codRepto VARCHAR(20),
    tipoRepto VARCHAR(45),
    nomRepto VARCHAR(45),
    desRepto VARCHAR(45),
    estRepto VARCHAR(5),
    cantRepto INTEGER,
    obsRepto VARCHAR(255)) 
    
    INSERT INTO tb_repuesto(cod_repu, tipo_repu, nom_repu, desc_repu, est_repuesto, cant_repu, fec_act, obs_repu)
		VALUES(codRepto, tipoRepto, nomRepto, desRepto, estRepto, cantRepto, NOW(), obsRepto);

CALL SP_GUARDAR_REPTO ('6001','ELECTRICO', 'BOBINA', 'BOBINA 12V BOSCH', 'A', 15, 'SP_MYSQL');

select * from tb_repuesto;

/*
================================================================================
Procedimiento: SP_EDITAR_REPTO
Objetivo: Modificar datos en la tabla tb_repuesto
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_REPTO(
	codRepto VARCHAR(20),
    tipoRepto VARCHAR(45),
    nomRepto VARCHAR(45),
    desRepto VARCHAR(45),
    estRepto VARCHAR(5),
    cantRepto INTEGER,
    obsRepto VARCHAR(255))
    
    UPDATE tb_repuesto SET tipo_repu = tipoRepto,
						   nom_repu = nomRepto,
                           desc_repu = desRepto,
                           est_repuesto = estRepto,
                           cant_repu = cantRepto,
                           fec_act = NOW(),
                           obs_repu = obsRepto WHERE cod_repu = codRepto;
                           
CALL SP_EDITAR_REPTO ('6001','ELECTRICO', 'BOBINA', 'BOBINA 12V BOSCH', 'A', 25, 'SP_EDIT');

select * from tb_repuesto;

/*
================================================================================
Procedimiento: SP_BUSCAR_REPTO
Objetivo: Buscar datos en la tabla tb_repuesto segun criterio
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_REPTO(
	codRepto VARCHAR(20))
    
    SELECT tipo_repu, nom_repu, desc_repu, est_repuesto,
           cant_repu, obs_repu FROM tb_repuesto WHERE cod_repu = codRepto;
           
CALL SP_BUSCAR_REPTO ('6001');

/*
================================================================================
Procedimiento: SP_MOSTRAR_REPTO
Objetivo: Mostrar loes registros de la tabla tb_repuesto
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_MOSTRAR_REPTO()

	SELECT
		cod_repu, tipo_repu, nom_repu, est_repuesto,
        cant_repu, obs_repu
	FROM tb_repuesto;
    
CALL SP_MOSTRAR_REPTO();

/*
================================================================================
Procedimiento: SP_BUSQ_REPTO
Objetivo: Mostrar loes registros de la tabla tb_repuesto segun descripcion
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_REPTO(
	nomRepto VARCHAR(45))
    
    SELECT
		cod_repu, tipo_repu, nom_repu
	FROM tb_repuesto WHERE est_repuesto='A' AND nom_repu LIKE CONCAT('%', nomRepto, '%');
    
CALL SP_BUSQ_REPTO ('N');

#Hacer un ALTER en SP

USE `sysautos`;
DROP procedure IF EXISTS `SP_BUSQ_REPTO`;

DELIMITER $$
USE `sysautos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_BUSQ_REPTO`(
    nomRepto VARCHAR(45)) 
    SELECT
		cod_repu, tipo_repu, nom_repu, cant_repu
	FROM tb_repuesto WHERE est_repuesto='A' AND nom_repu LIKE CONCAT('%', nomRepto, '%');$$

DELIMITER ;

/*
================================================================================
Procedimiento: SP_BUSCAR_REPU_CARRO
Objetivo: Busca el repuesto con el código y el estado activo
Autor: @chcgutierrez
Fecha: 04/01/2020
================================================================================
*/

SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

CREATE PROCEDURE SP_BUSCAR_REPU_CARRO(
	codRepu VARCHAR(20))
    
    SELECT
		idRepuestos,
        tipo_repu,
        desc_repu,
        cant_repu
	FROM tb_repuesto WHERE est_repuesto = 'A' AND cod_repu = codRepu;
    
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    
select * from tb_repuesto;    

CALL SP_BUSCAR_REPU_CARRO('6003');    
    
    
    
    
    
    
    
    
    
    
    
    
    
    