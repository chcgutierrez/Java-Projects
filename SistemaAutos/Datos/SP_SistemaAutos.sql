/*
================================================================================
Procedimiento: SP_GUARDAR_MARCA
Objetivo: Guardar datos en la tabla tb_marca
Autor: @chcgutierrez
Fecha: 28/09/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_MARCA(
	codMarca VARCHAR(20),
    nomMarca VARCHAR(100),
    estMarca VARCHAR(20),
    obsMarca VARCHAR(255)) 
    
    INSERT INTO tb_marca(cod_marca, nom_marca, estado_marca, fec_act, obs_marca)
		VALUES(codMarca, nomMarca, estMarca, NOW(), obsMarca);

CALL SP_GUARDAR_MARCA ('2002','RENAULT','A','PRUEBA_MYSQL');
CALL SP_GUARDAR_MARCA ('2003','FORD','A','PRUEBA_MYSQL');
CALL SP_GUARDAR_MARCA ('2004','PEUGEOT','A','PRUEBA_MYSQL');
CALL SP_GUARDAR_MARCA ('2005','MAZDA','A','PRUEBA_MYSQL');


/*
================================================================================
Procedimiento: SP_EDITAR_MARCA
Objetivo: Editar datos en la tabla tb_marca
Autor: @chcgutierrez
Fecha: 28/09/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_MARCA(
	codMarca VARCHAR(20),
    nomMarca VARCHAR(100),
    estMarca VARCHAR(20),
    obsMarca VARCHAR(255)) 
    
    UPDATE tb_marca SET nom_marca = nomMarca,
                        estado_marca = estMarca,
                        fec_act = NOW(),
                        obs_marca = obsMarca WHERE cod_marca = codMarca;
                        
CALL SP_EDITAR_MARCA ('2001','CHEVROLET','I','PRUEBA_MYSQL_UPD');

/*
================================================================================
Procedimiento: SP_BUSCAR_MARCA
Objetivo: Buscar datos en la tabla tb_marca segun criterio
Autor: @chcgutierrez
Fecha: 28/09/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_MARCA(
	codMarca VARCHAR(20)) 
    
    SELECT
        nom_marca,
        estado_marca,
        obs_marca
	FROM tb_marca WHERE cod_marca = codMarca;
                        
CALL SP_BUSCAR_MARCA ('2003');

/*
================================================================================
Procedimiento: SP_MOSTRAR_MARCA
Objetivo: Mostrar los datos en la tabla tb_marca
Autor: @chcgutierrez
Fecha: 28/09/2019
================================================================================
*/

CREATE PROCEDURE SP_MOSTRAR_MARCA()
    SELECT
		cod_marca,
        nom_marca,
        estado_marca,
        fec_act,
        obs_marca
	FROM tb_marca;

CALL SP_MOSTRAR_MARCA();

#select * from tb_marca where idMarca='10006'

/*
================================================================================
Procedimiento: SP_MOSTRAR_MARCA
Objetivo: Mostrar los datos en la tabla tb_marca
Autor: @chcgutierrez
Fecha: 28/09/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_MARCA(
	nomMarca VARCHAR(100)) 
    SELECT
		cod_marca,
        nom_marca
	FROM tb_marca WHERE estado_marca='A' AND nom_marca LIKE CONCAT('%', nomMarca, '%');

CALL SP_BUSQ_MARCA('H');

/*
================================================================================
Procedimiento: SP_CBO_MARCA
Objetivo: Llenar jComboBox con datos de la tabla tb_marca
Autor: @chcgutierrez
Fecha: 06/11/2019
================================================================================
*/

CREATE PROCEDURE SP_CBO_MARCA() 
    SELECT
		idMarca,
        nom_marca
	FROM tb_marca WHERE estado_marca='A';





















