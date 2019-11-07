/*
================================================================================
Procedimiento: SP_GUARDAR_COLOR
Objetivo: Guardar datos en la tabla tb_Color
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_COLOR(
	codColor VARCHAR(20),
    nomColor VARCHAR(100),
    estColor VARCHAR(20),
    obsColor VARCHAR(255)) 
    
    INSERT INTO tb_color(cod_color, nom_color, estado_color, fec_act, obs_color)
		VALUES(codColor, nomColor, estColor, NOW(), obsColor);

CALL SP_GUARDAR_COLOR ('3001','GIALLO MODENA','A','SP_MYSQL');

select * from tb_color;

/*
================================================================================
Procedimiento: SP_EDITAR_COLOR
Objetivo: Editar datos en la tabla tb_color
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_COLOR(
	codColor VARCHAR(20),
    nomColor VARCHAR(100),
    estColor VARCHAR(20),
    obsColor VARCHAR(255)) 
    
    UPDATE tb_color SET nom_color = nomColor,
                        estado_color = estColor,
                        fec_act = NOW(),
                        obs_color = obsColor WHERE cod_color = codColor;
                        
CALL SP_EDITAR_COLOR ('3001','ROSSO CORSA','A','SP_UPD');
select * from tb_color;

/*
================================================================================
Procedimiento: SP_BUSCAR_COLOR
Objetivo: Buscar datos en la tabla tb_color segun criterio
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_COLOR(
	codColor VARCHAR(20)) 
    
    SELECT
        nom_color,
        estado_color,
        obs_color
	FROM tb_color WHERE cod_color = codColor;
                        
CALL SP_BUSCAR_COLOR ('3001');

/*
================================================================================
Procedimiento: SP_MOSTRAR_COLOR
Objetivo: Mostrar los datos en la tabla tb_color
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_MOSTRAR_COLOR()
    SELECT
		cod_color,
        nom_color,
        estado_color,
        fec_act,
        obs_color
	FROM tb_color;

CALL SP_MOSTRAR_COLOR();

/*
================================================================================
Procedimiento: SP_BUSQ_COLOR
Objetivo: Mostrar los datos de la tabla tb_color segun descripcion
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_COLOR(
	nomColor VARCHAR(100)) 
    SELECT
		cod_color,
        nom_color
	FROM tb_color WHERE estado_color='A' AND nom_color LIKE CONCAT('%', nomColor, '%');

CALL SP_BUSQ_COLOR('R');

select * from tb_color;








