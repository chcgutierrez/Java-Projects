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
public class TipoDAO extends ConectaBD { //La clase hereda de la Conexion a BD
    
    //Metodo Guardar
    public boolean GuardarTipo(TipoBD objTipo) {//Recibe objeto tipo TipoBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_TIPO(?,?,?,?)");
            Guardar.setString(1, objTipo.getCodTipo());
            Guardar.setString(2, objTipo.getNomTipo());
            Guardar.setString(3, objTipo.getEstTipo());
            Guardar.setString(4, objTipo.getObsTipo());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Editar
    public boolean EditarTipo(TipoBD objTipo) {//Recibe objeto tipo TipoBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_TIPO(?,?,?,?)");
            Editar.setString(1, objTipo.getCodTipo());
            Editar.setString(2, objTipo.getNomTipo());
            Editar.setString(3, objTipo.getEstTipo());
            Editar.setString(4, objTipo.getObsTipo());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaTipo(TipoBD objTipo) {//Recibe objeto tipo TipoBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_TIPO(?)");
            Validar.setString(1, objTipo.getCodTipo());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objTipo.setNomTipo(rslSet.getString("nom_tipo"));
                objTipo.setEstTipo(rslSet.getString("estado_tipo"));
                objTipo.setObsTipo(rslSet.getString("obs_tipo"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Ver Todos
    public DefaultTableModel VerTipo() {
        DefaultTableModel modTipo = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modTipo = new DefaultTableModel();//Encabezados de las columnas
            modTipo.addColumn("Código");
            modTipo.addColumn("Descripción");
            modTipo.addColumn("Estado");
            modTipo.addColumn("Fecha Act");
            modTipo.addColumn("Observaciones");

            String datosTipo[] = new String[5];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Mostrar = reuConex.prepareCall("CALL SP_MOSTRAR_TIPO");
            rslSet = Mostrar.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosTipo[0] = rslSet.getString("cod_tipo");
                datosTipo[1] = rslSet.getString("nom_tipo");
                datosTipo[2] = rslSet.getString("estado_tipo");
                datosTipo[3] = rslSet.getString("fec_act");
                datosTipo[4] = rslSet.getString("obs_tipo");
                modTipo.addRow(datosTipo);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modTipo;//Devuelve el modelo con los datos
    }
    
    //Metodo Búsqueda
    public DefaultTableModel BusqTipo(TipoBD objTipo) {//Recibe objeto tipo TipoBD

        DefaultTableModel modBusqTipo = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modBusqTipo = new DefaultTableModel();//Encabezados de las columnas
            modBusqTipo.addColumn("Código");
            modBusqTipo.addColumn("Descripción");

            String datosBusq[] = new String[2];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_TIPO(?)");
            Busqueda.setString(1, objTipo.getNomTipo());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("cod_tipo");
                datosBusq[1] = rslSet.getString("nom_tipo");
                modBusqTipo.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqTipo;//Devuelve el modelo con los datos
    }
}
