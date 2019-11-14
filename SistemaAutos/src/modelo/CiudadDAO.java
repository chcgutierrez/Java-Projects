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
public class CiudadDAO extends ConectaBD { //La clase hereda de la Conexion a BD
    
    //Metodo Guardar
    public boolean GuardarCiudad(CiudadBD objCiudad) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_CIUDAD(?,?,?,?)");
            Guardar.setString(1, objCiudad.getCodCiudad());
            Guardar.setString(2, objCiudad.getNomCiudad());
            Guardar.setString(3, objCiudad.getEstCiudad());
            Guardar.setString(4, objCiudad.getObsCiudad());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Editar
    public boolean EditarCiudad(CiudadBD objCiudad) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_CIUDAD(?,?,?,?)");
            Editar.setString(1, objCiudad.getCodCiudad());
            Editar.setString(2, objCiudad.getNomCiudad());
            Editar.setString(3, objCiudad.getEstCiudad());
            Editar.setString(4, objCiudad.getObsCiudad());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaCiudad(CiudadBD objCiudad) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_CIUDAD(?)");
            Validar.setString(1, objCiudad.getCodCiudad());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objCiudad.setNomCiudad(rslSet.getString("nom_ciudad"));
                objCiudad.setEstCiudad(rslSet.getString("estado_ciudad"));
                objCiudad.setObsCiudad(rslSet.getString("obs_ciudad"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Ver Todos
    public DefaultTableModel VerCiudad() {
        DefaultTableModel modCiudad = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modCiudad = new DefaultTableModel();//Encabezados de las columnas
            modCiudad.addColumn("Código");
            modCiudad.addColumn("Descripción");
            modCiudad.addColumn("Estado");
            modCiudad.addColumn("Fecha Act");
            modCiudad.addColumn("Observaciones");

            String datosCiudad[] = new String[5];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Mostrar = reuConex.prepareCall("CALL SP_MOSTRAR_CIUDAD");
            rslSet = Mostrar.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosCiudad[0] = rslSet.getString("cod_ciudad");
                datosCiudad[1] = rslSet.getString("nom_ciudad");
                datosCiudad[2] = rslSet.getString("estado_ciudad");
                datosCiudad[3] = rslSet.getString("fec_act");
                datosCiudad[4] = rslSet.getString("obs_ciudad");
                modCiudad.addRow(datosCiudad);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modCiudad;//Devuelve el modelo con los datos
    }
    
    //Metodo Búsqueda
    public DefaultTableModel BusqCiudad(CiudadBD objCiudad) {//Recibe objeto tipo CiudadBD

        DefaultTableModel modBusqCiudad = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modBusqCiudad = new DefaultTableModel();//Encabezados de las columnas
            modBusqCiudad.addColumn("Código");
            modBusqCiudad.addColumn("Descripción");

            String datosBusq[] = new String[2];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_CIUDAD(?)");
            Busqueda.setString(1, objCiudad.getNomCiudad());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("cod_ciudad");
                datosBusq[1] = rslSet.getString("nom_ciudad");
                modBusqCiudad.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqCiudad;//Devuelve el modelo con los datos
    }
}
