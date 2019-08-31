/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import vista.frmMantenimiento;

/**
 *
 * @author ccgutierez
 */
public class OrdenTrabCRUD extends ConexionDB {

    //Validar Placa Cliente
    public boolean BuscarClientePlaca(Cliente nCliente, Vehiculo nVehiculo, Marca nMarca, Tipo nTipo, Color nColor) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL
                    = //"SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED\n"
                    "SELECT\n"
                    + "C.tip_doc_cliente,\n"
                    + "C.num_doc_cliente,\n"
                    + "C.nom_cliente,\n"
                    + "C.ape_cliente,\n"
                    + "C.tel_cliente,\n"
                    + "C.email_cliente,\n"
                    + "V.modelo_vehiculo,\n"
                    + "V.num_motor,\n"
                    + "M.nom_marca,\n"
                    + "T.nom_tipo,\n"
                    + "P.nom_color\n"
                    + "FROM tb_vehiculo V INNER JOIN tb_cliente C \n"
                    + "ON C.num_doc_cliente=V.cliente\n"
                    + "INNER JOIN tb_marca M\n"
                    + "ON M.idMarca=V.marca_vehiculo\n"
                    + "INNER JOIN tb_tipo T\n"
                    + "ON T.idTipo=V.tipo_vehiculo\n"
                    + "INNER JOIN tb_color P\n"
                    + "ON P.idColor=V.color_vehiculo\n"
                    + "WHERE\n"
                    + "V.placa_vehiculo=?;";
            //+ "COMMIT;";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nVehiculo.getPlaca());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                nCliente.setTipo_doc_cliente(rslSet.getString("C.tip_doc_cliente"));
                nCliente.setNum_doc_cliente(rslSet.getString("C.num_doc_cliente"));
                nCliente.setNom_cliente(rslSet.getString("C.nom_cliente"));
                nCliente.setApe_cliente(rslSet.getString("C.ape_cliente"));
                nCliente.setTel_cliente(rslSet.getString("C.tel_cliente"));
                nCliente.setEmail_cliente(rslSet.getString("C.email_cliente"));
                nVehiculo.setModelo(rslSet.getString("V.modelo_vehiculo"));
                nVehiculo.setMotor(rslSet.getString("V.num_motor"));
                nMarca.setNom_marca(rslSet.getString("M.nom_marca"));
                nTipo.setNom_tipo(rslSet.getString("T.nom_tipo"));
                nColor.setNom_color(rslSet.getString("P.nom_color"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
