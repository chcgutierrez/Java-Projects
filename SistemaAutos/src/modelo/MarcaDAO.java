/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class MarcaDAO extends ConectaBD { //La clase hereda de la Conexion a BD
    
    //Metodo Guardar
    public boolean GuardarMarca(MarcaEnt objMarca) {//Recibe objeto tipo MarcaEnt

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_MARCA(?,?,?,?)");
            Guardar.setString(1, objMarca.getCod_marca());
            Guardar.setString(2, objMarca.getNom_marca());
            Guardar.setString(3, objMarca.getEstado_marca());
            Guardar.setString(4, objMarca.getObs_marca());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Editar
    public boolean EditarMarca(MarcaEnt objMarca) {//Recibe objeto tipo MarcaEnt

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_MARCA(?,?,?,?)");
            Editar.setString(1, objMarca.getCod_marca());
            Editar.setString(2, objMarca.getNom_marca());
            Editar.setString(3, objMarca.getEstado_marca());
            Editar.setString(4, objMarca.getObs_marca());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaMarca(MarcaEnt objMarca) {//Recibe objeto tipo MarcaEnt

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_MARCA(?)");
            Validar.setString(1, objMarca.getCod_marca());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objMarca.setNom_marca(rslSet.getString("nom_marca"));
                objMarca.setEstado_marca(rslSet.getString("estado_marca"));
                objMarca.setObs_marca(rslSet.getString("obs_marca"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Ver Todos
    public DefaultTableModel VerMarca() {
        DefaultTableModel modMarca = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modMarca = new DefaultTableModel();//Encabezados de las columnas
            modMarca.addColumn("Código");
            modMarca.addColumn("Descripción");
            modMarca.addColumn("Estado");
            modMarca.addColumn("Fecha Act");
            modMarca.addColumn("Observaciones");

            String datosMarca[] = new String[5];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Mostrar = reuConex.prepareCall("CALL SP_MOSTRAR_MARCA");
            rslSet = Mostrar.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosMarca[0] = rslSet.getString("cod_marca");
                datosMarca[1] = rslSet.getString("nom_marca");
                datosMarca[2] = rslSet.getString("estado_marca");
                datosMarca[3] = rslSet.getString("fec_act");
                datosMarca[4] = rslSet.getString("obs_marca");
                modMarca.addRow(datosMarca);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modMarca;//Devuelve el modelo con los datos
    }
}
