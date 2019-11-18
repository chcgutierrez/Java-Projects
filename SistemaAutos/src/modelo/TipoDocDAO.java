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
public class TipoDocDAO extends ConectaBD { //La clase hereda de la Conexion a BD
    
    //Metodo Guardar
    public boolean GuardarTipoDoc(TipoDocBD objTipoDoc) {//Recibe objeto tipo TipoDocBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_TIPODOC(?,?,?,?)");
            Guardar.setString(1, objTipoDoc.getTipoDoc());
            Guardar.setString(2, objTipoDoc.getDescTipoDoc());
            Guardar.setString(3, objTipoDoc.getEstTipoDoc());
            Guardar.setString(4, objTipoDoc.getObsTipoDoc());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Editar
    public boolean EditarTipoDoc(TipoDocBD objTipoDoc) {//Recibe objeto tipo TipoDocBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_TIPODOC(?,?,?,?)");
            Editar.setString(1, objTipoDoc.getTipoDoc());
            Editar.setString(2, objTipoDoc.getDescTipoDoc());
            Editar.setString(3, objTipoDoc.getEstTipoDoc());
            Editar.setString(4, objTipoDoc.getObsTipoDoc());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaTipoDoc(TipoDocBD objTipoDoc) {//Recibe objeto tipo TipoDocBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_TIPODOC(?)");
            Validar.setString(1, objTipoDoc.getTipoDoc());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objTipoDoc.setDescTipoDoc(rslSet.getString("desc_tipodoc"));
                objTipoDoc.setEstTipoDoc(rslSet.getString("estado_tipodoc"));
                objTipoDoc.setObsTipoDoc(rslSet.getString("obs_tipodoc"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Ver Todos
    public DefaultTableModel VerTipoDoc() {
        DefaultTableModel modTipoDoc = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modTipoDoc = new DefaultTableModel();//Encabezados de las columnas
            modTipoDoc.addColumn("Tipo");
            modTipoDoc.addColumn("Descripción");
            modTipoDoc.addColumn("Estado");
            modTipoDoc.addColumn("Fecha Act");
            modTipoDoc.addColumn("Observaciones");

            String datosTipoDoc[] = new String[5];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Mostrar = reuConex.prepareCall("CALL SP_MOSTRAR_TIPODOC");
            rslSet = Mostrar.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosTipoDoc[0] = rslSet.getString("tipo_doc");
                datosTipoDoc[1] = rslSet.getString("desc_tipodoc");
                datosTipoDoc[2] = rslSet.getString("estado_tipodoc");
                datosTipoDoc[3] = rslSet.getString("fec_act");
                datosTipoDoc[4] = rslSet.getString("obs_tipodoc");
                modTipoDoc.addRow(datosTipoDoc);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modTipoDoc;//Devuelve el modelo con los datos
    }
    
    //Metodo Búsqueda
    public DefaultTableModel BusqTipoDoc(TipoDocBD objTipoDoc) {//Recibe objeto tipo TipoDocBD

        DefaultTableModel modBusqTipoDoc = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modBusqTipoDoc = new DefaultTableModel();//Encabezados de las columnas
            modBusqTipoDoc.addColumn("Tipo");
            modBusqTipoDoc.addColumn("Descripción");

            String datosBusq[] = new String[2];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_TIPODOC(?)");
            Busqueda.setString(1, objTipoDoc.getDescTipoDoc());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("tipo_doc");
                datosBusq[1] = rslSet.getString("desc_tipodoc");
                modBusqTipoDoc.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqTipoDoc;//Devuelve el modelo con los datos
    }
}
