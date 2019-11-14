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
public class ColorDAO extends ConectaBD { //La clase hereda de la Conexion a BD
    
    //Metodo Guardar
    public boolean GuardarColor(ColorBD objColor) {//Recibe objeto tipo ColorBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_COLOR(?,?,?,?)");
            Guardar.setString(1, objColor.getCodColor());
            Guardar.setString(2, objColor.getNomColor());
            Guardar.setString(3, objColor.getEstColor());
            Guardar.setString(4, objColor.getObsColor());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Editar
    public boolean EditarColor(ColorBD objColor) {//Recibe objeto tipo ColorBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_COLOR(?,?,?,?)");
            Editar.setString(1, objColor.getCodColor());
            Editar.setString(2, objColor.getNomColor());
            Editar.setString(3, objColor.getEstColor());
            Editar.setString(4, objColor.getObsColor());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaColor(ColorBD objColor) {//Recibe objeto tipo ColorBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_COLOR(?)");
            Validar.setString(1, objColor.getCodColor());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objColor.setNomColor(rslSet.getString("nom_color"));
                objColor.setEstColor(rslSet.getString("estado_color"));
                objColor.setObsColor(rslSet.getString("obs_color"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Ver Todos
    public DefaultTableModel VerColor() {
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
            CallableStatement Mostrar = reuConex.prepareCall("CALL SP_MOSTRAR_COLOR");
            rslSet = Mostrar.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosMarca[0] = rslSet.getString("cod_color");
                datosMarca[1] = rslSet.getString("nom_color");
                datosMarca[2] = rslSet.getString("estado_color");
                datosMarca[3] = rslSet.getString("fec_act");
                datosMarca[4] = rslSet.getString("obs_color");
                modMarca.addRow(datosMarca);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modMarca;//Devuelve el modelo con los datos
    }
    
    //Metodo Búsqueda
    public DefaultTableModel BusqColor(ColorBD objColor) {//Recibe objeto tipo ColorBD

        DefaultTableModel modBusqColor = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modBusqColor = new DefaultTableModel();//Encabezados de las columnas
            modBusqColor.addColumn("Código");
            modBusqColor.addColumn("Descripción");

            String datosBusq[] = new String[2];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_COLOR(?)");
            Busqueda.setString(1, objColor.getNomColor());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("cod_color");
                datosBusq[1] = rslSet.getString("nom_color");
                modBusqColor.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqColor;//Devuelve el modelo con los datos
    }
}
