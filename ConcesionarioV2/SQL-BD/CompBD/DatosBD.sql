USE concesionario2;

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
SELECT
C.tip_doc_cliente,
C.num_doc_cliente,
C.nom_cliente,
C.ape_cliente,
C.tel_cliente,
C.email_cliente,
V.modelo_vehiculo,
V.num_motor,
M.nom_marca,
T.nom_tipo,
P.nom_color
FROM tb_vehiculo AS V INNER JOIN tb_cliente AS C
ON C.num_doc_cliente=V.cliente
INNER JOIN tb_marca AS M
ON M.idMarca=V.marca_vehiculo
INNER JOIN tb_tipo AS T
ON T.idTipo=V.tipo_vehiculo
INNER JOIN tb_color AS P
ON P.idColor=V.color_vehiculo
WHERE
V.placa_vehiculo='VDX-365';
COMMIT;

select * from tb_vehiculo;