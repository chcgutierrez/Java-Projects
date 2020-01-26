/*
================================================================================
Procedimiento: SP_GUARDAR_ORDEN
Objetivo: Guardar datos en la tabla tb_orden_trabajo
Autor: @chcgutierrez
Fecha: 29/12/2019
================================================================================
*/

DELIMITER //

CREATE PROCEDURE SP_GUARDAR_ORDEN(
	fechaOrden DATETIME,
    codCliente INTEGER(11),
    codMecanico INTEGER(11),
    codVehiculo INTEGER(11),
    valOrden DECIMAL(18,2),
    detOrden VARCHAR(255),    
    obsOrden VARCHAR(255),
    OUT codOrden INTEGER(11) #ID de la Orden que se crea
)

BEGIN

	DECLARE sqlError INT DEFAULT FALSE;

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		
        SET sqlError = TRUE;

	START TRANSACTION;

		INSERT INTO tb_orden_trabajo (fec_orden,id_cliente,id_mecanico,cod_vehiculo,valor_orden,estado_orden,detalle_orden,obs_orden)
        VALUES(fechaOrden, codCliente, codMecanico, codVehiculo, valOrden, 'A', detOrden, obsOrden);
        
        SELECT idOrden INTO codOrden FROM tb_orden_trabajo ORDER BY idOrden DESC LIMIT 1;
		
	IF sqlError = FALSE THEN
		COMMIT;
		SELECT 'Transaccion OK';
	ELSE
		ROLLBACK;
		SELECT 'Transaccion Error';
	END IF;
    
END //

select * from tb_orden_trabajo;

CALL SP_GUARDAR_ORDEN ('2019-12-20',1,2,8,239550,'ARREGLO DIRECCION Y VOLANTE','PRUEBA_SP')

/*
================================================================================
Procedimiento: SP_GUARDAR_ORDENREP
Objetivo: Guardar datos en la tabla tb_orden_repuesto
Autor: @chcgutierrez
Fecha: 29/12/2019
================================================================================
*/

DELIMITER //

CREATE PROCEDURE SP_GUARDAR_ORDENREP(
    codRepuesto INTEGER(11),
    valCantidad INTEGER(11)
)

BEGIN

	DECLARE sqlError INT DEFAULT FALSE;

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		
        SET sqlError = TRUE;

	START TRANSACTION;

		INSERT INTO tb_orden_repuesto (ordenTrabajo,repuesto,cantidad)
        VALUES((SELECT idOrden FROM tb_orden_trabajo ORDER BY idOrden DESC LIMIT 1),codRepuesto,valCantidad);
		
	IF sqlError = FALSE THEN
		COMMIT;
		SELECT 'Transaccion OK';
	ELSE
		ROLLBACK;
		SELECT 'Transaccion Error';
	END IF;
    
END //

/*
================================================================================
Procedimiento: SP_BUSCAR_CARRO
Objetivo: Buscar datos en la tabla tb_vehiculo segun criterio
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSCAR_CARRO_ORDEN(
    placaVehi VARCHAR(15))
    
	SELECT
		CONCAT(B.nom_cliente, ' ', B.ape_cliente) AS nomCliente,
		B.tel_cliente,
		C.nom_marca,
		A.num_motor,
		A.modelo_vehiculo        
	FROM tb_vehiculo AS A
    INNER JOIN tb_cliente AS B
		ON B.num_doc_cliente = A.cod_cliente
	INNER JOIN tb_marca AS C
		ON C.idMarca = A.marca_vehiculo
    WHERE A.placa_vehiculo = placaVehi;
                        
CALL SP_BUSCAR_CARRO_ORDEN ('ADU - 361');

select * from tb_orden_trabajo;


select * from tb_orden_repuesto;




