SELECT * FROM tb_cliente;

/*
================================================================================
Procedimiento: SP_GUARDAR_CARRO
Objetivo: Guardar datos en la tabla tb_vehiculo
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_GUARDAR_CARRO(
	placaVehi VARCHAR(15),
    ciuVehi INTEGER(11),
    marcaVehi INTEGER(11),
    tipoVehi INTEGER(11),
    colorVehi INTEGER(11),
    modVehi VARCHAR(10),
    motorVehi VARCHAR(20),
    estVehi VARCHAR(10),
    clienteVehi INTEGER(11),    
    obsVehi VARCHAR(255)
    ) 
    
    INSERT INTO tb_vehiculo(placa_vehiculo, ciudad_vehiculo, marca_vehiculo, tipo_vehiculo, color_vehiculo,
							modelo_vehiculo, num_motor, estado_vehiculo, fec_act, obs_vehiculo, cod_cliente)
		VALUES(placaVehi, ciuVehi, marcaVehi, tipoVehi, colorVehi, modVehi, motorVehi, estVehi, NOW(), obsVehi, clienteVehi);

CALL SP_GUARDAR_CARRO ('ADU - 361',2,3,2,3,'2000','2JT-R45800A','A',12345,'PRUEBA_SP');

truncate table tb_vehiculo;

/*
================================================================================
Procedimiento: SP_EDITAR_CARRO
Objetivo: Modificar datos en la tabla tb_vehiculo
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_EDITAR_CARRO(
	placaVehi VARCHAR(15),
    ciuVehi INTEGER(11),
    marcaVehi INTEGER(11),
    tipoVehi INTEGER(11),
    colorVehi INTEGER(11),
    modVehi VARCHAR(10),
    motorVehi VARCHAR(20),
    estVehi VARCHAR(10),
    obsVehi VARCHAR(255),
    clienteVehi INTEGER(11)
    ) 
    
    UPDATE tb_vehiculo SET ciudad_vehiculo = ciuVehi,
                           marca_vehiculo = marcaVehi,
                           tipo_vehiculo = tipoVehi,
                           color_vehiculo = colorVehi,
						   modelo_vehiculo = modVehi,
                           num_motor = motorVehi,
                           estado_vehiculo = estVehi,
                           fec_act = NOW(),                           
                           obs_vehiculo = obsVehi,
                           cliente = clienteVehi                           
                           WHERE placa_vehiculo = placaVehi;

CALL SP_EDITAR_CARRO ('ADU - 361', 3, 2, 1, 2, '2010', '2UJT-R54800A', 'A', 'PRUEBA_SP', 2);

select * from tb_vehiculo;

/*
================================================================================
Procedimiento: SP_BUSCAR_CARRO
Objetivo: Buscar datos en la tabla tb_vehiculo segun criterio
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

CREATE PROCEDURE SP_BUSCAR_CARRO(
	placaVehi VARCHAR(15))
    
    SELECT
        A.placa_vehiculo, A.num_motor,
        B.num_doc_cliente, CONCAT(B.nom_cliente, ' ', B.ape_cliente) AS nomCliente
	FROM tb_vehiculo AS A INNER JOIN tb_cliente AS B
		ON B.num_doc_cliente = A.cod_cliente
    WHERE A.placa_vehiculo LIKE CONCAT('%', placaVehi, '%')
    
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
                        
CALL SP_BUSCAR_CARRO ('ADU - 361');

/*
================================================================================
Procedimiento: SP_BUSQ_CARRO
Objetivo: Mostrar los datos de la tabla tb_vehiculo segun descripcion
Autor: @chcgutierrez
Fecha: 13/11/2019
================================================================================
*/

CREATE PROCEDURE SP_BUSQ_CARRO(
	placaVehi VARCHAR(15)) 
    
    SELECT
        A.placa_vehiculo, A.num_motor,
        B.num_doc_cliente, CONCAT(B.nom_cliente, ' ', B.ape_cliente) AS Cliente
	FROM tb_vehiculo AS A INNER JOIN tb_cliente AS B
		ON B.id_cliente = A.cliente
    WHERE A.placa_vehiculo LIKE CONCAT('%', placaVehi, '%');

CALL SP_BUSQ_CARRO('ADU');



                        

