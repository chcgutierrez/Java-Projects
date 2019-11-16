/*
================================================================================
Procedimiento: SP_GUARDAR_TIPO
Objetivo: Guardar datos en la tabla tb_tipo
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_TIPO(
	codTipo VARCHAR(20),
    nomTipo VARCHAR(100),
    estTipo VARCHAR(20),
    obsTipo VARCHAR(255)) 
    
    INSERT INTO tb_tipo(cod_tipo, nom_tipo, estado_tipo, fec_act, obs_tipo)
		VALUES(codTipo, nomTipo, estTipo, NOW(), obsTipo);

CALL SP_GUARDAR_tipo ('5001','SEDAN','A','SP_MYSQL');

select * from tb_tipo;

/*
================================================================================
Procedimiento: SP_EDITAR_TIPO
Objetivo: Editar datos en la tabla tb_tipo
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_TIPO(
	codTipo VARCHAR(20),
    nomTipo VARCHAR(100),
    estTipo VARCHAR(20),
    obsTipo VARCHAR(255))
    
    UPDATE tb_tipo SET nom_tipo = nomTipo,
                        estado_tipo = estTipo,
                        fec_act = NOW(),
                        obs_tipo = obsTipo WHERE cod_tipo = codTipo;
                        
CALL SP_EDITAR_TIPO ('5001','COUPÉ','A','SP_UPD');

select * from tb_tipo;

/*
================================================================================
Procedimiento: SP_BUSCAR_TIPO
Objetivo: Buscar datos en la tabla tb_tipo segun criterio
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_TIPO(
	codTipo VARCHAR(20)) 
    
    SELECT
        nom_tipo,
        estado_tipo,
        obs_tipo
	FROM tb_tipo WHERE cod_tipo = codTipo;
                        
CALL SP_BUSCAR_TIPO ('5001');

/*
================================================================================
Procedimiento: SP_MOSTRAR_TIPO
Objetivo: Mostrar los datos en la tabla tb_tipo
Autor: @chcgutierrez
Fecha: 15/11/2019
================================================================================
*/

CREATE PROCEDURE SP_MOSTRAR_TIPO()
    SELECT
		cod_tipo,
        nom_tipo,
        estado_tipo,
        fec_act,
        obs_tipo
	FROM tb_tipo;

CALL SP_MOSTRAR_TIPO();

/*
================================================================================
Procedimiento: SP_BUSQ_TIPO
Objetivo: Mostrar los datos de la tabla tb_tipo segun descripcion
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_TIPO(
	nomTipo VARCHAR(100)) 
    SELECT
		cod_tipo,
        nom_tipo
	FROM tb_tipo WHERE estado_tipo='A' AND nom_tipo LIKE CONCAT('%', nomTipo, '%');

CALL SP_BUSQ_TIPO('U');

select * from tb_tipo;








