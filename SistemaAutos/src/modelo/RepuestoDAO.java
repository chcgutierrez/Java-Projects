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
public class RepuestoDAO extends ConectaBD { //La clase hereda de la Conexion a BD
    
    //Metodo Guardar
    public boolean GuardarRepto(RepuestoBD objRepto) {//Recibe objeto tipo RepuestoBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_REPTO(?,?,?,?,?,?,?)");
            Guardar.setString(1, objRepto.getCodRepto());
            Guardar.setString(2, objRepto.getTipoRepto());            
            Guardar.setString(3, objRepto.getNomRepto());
            Guardar.setString(4, objRepto.getDesRepto());
            Guardar.setString(5, objRepto.getEstRepto());
            Guardar.setString(6, objRepto.getCantRepto());
            Guardar.setString(7, objRepto.getObsRepto());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Editar
    public boolean EditarRepto(RepuestoBD objRepto) {//Recibe objeto tipo RepuestoBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_REPTO(?,?,?,?,?,?,?)");
            Editar.setString(1, objRepto.getCodRepto());
            Editar.setString(2, objRepto.getTipoRepto());            
            Editar.setString(3, objRepto.getNomRepto());
            Editar.setString(4, objRepto.getDesRepto());
            Editar.setString(5, objRepto.getEstRepto());
            Editar.setString(6, objRepto.getCantRepto());
            Editar.setString(7, objRepto.getObsRepto());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaRepto(RepuestoBD objRepto) {//Recibe objeto tipo RepuestoBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_REPTO(?)");
            Validar.setString(1, objRepto.getCodRepto());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objRepto.setTipoRepto(rslSet.getString("tipo_repu"));
                objRepto.setNomRepto(rslSet.getString("nom_repu"));
                objRepto.setDesRepto(rslSet.getString("desc_repu"));
                objRepto.setEstRepto(rslSet.getString("est_repuesto"));
                objRepto.setCantRepto(rslSet.getString("cant_repu"));
                objRepto.setObsRepto(rslSet.getString("obs_repu"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Ver Todos
    public DefaultTableModel VerRepto() {
        DefaultTableModel modRepto = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modRepto = new DefaultTableModel();//Encabezados de las columnas
            modRepto.addColumn("Código");
            modRepto.addColumn("Tipo");
            modRepto.addColumn("Nombre");
            modRepto.addColumn("Estado");
            modRepto.addColumn("Cantidad");
            modRepto.addColumn("Observaciones");

            String datosRepto[] = new String[6];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Mostrar = reuConex.prepareCall("CALL SP_MOSTRAR_REPTO");
            rslSet = Mostrar.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosRepto[0] = rslSet.getString("cod_repu");
                datosRepto[1] = rslSet.getString("tipo_repu");
                datosRepto[2] = rslSet.getString("nom_repu");
                datosRepto[3] = rslSet.getString("est_repuesto");
                datosRepto[4] = rslSet.getString("cant_repu");
                datosRepto[5] = rslSet.getString("obs_repu");
                modRepto.addRow(datosRepto);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modRepto;//Devuelve el modelo con los datos
    }
    
    //Metodo Búsqueda
    public DefaultTableModel BusqRepto(RepuestoBD objRepto) {//Recibe objeto tipo RepuestoBD

        DefaultTableModel modBusqRepto = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {
    
            modBusqRepto = new DefaultTableModel();//Encabezados de las columnas
            modBusqRepto.addColumn("Código");
            modBusqRepto.addColumn("Tipo");
            modBusqRepto.addColumn("Nombre");
            modBusqRepto.addColumn("Unidades");

            String datosBusq[] = new String[4];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_REPTO(?)");
            Busqueda.setString(1, objRepto.getNomRepto());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("cod_repu");
                datosBusq[1] = rslSet.getString("tipo_repu");
                datosBusq[2] = rslSet.getString("nom_repu");
                datosBusq[3] = rslSet.getString("cant_repu");
                modBusqRepto.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqRepto;//Devuelve el modelo con los datos
    }
    
    //Metodo Orden - Repuesto
    public boolean repuOrden(RepuestoBD objRepu) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement repuCarro = reuConex.prepareCall("CALL SP_BUSCAR_REPU_CARRO(?)");
            repuCarro.setString(1, objRepu.getCodRepto());
            rslSet = repuCarro.executeQuery();
            if (rslSet.next()) {
                objRepu.setIdRepto(rslSet.getString("idRepuestos"));
                objRepu.setNomRepto(rslSet.getString("nom_repu"));
                objRepu.setTipoRepto(rslSet.getString("tipo_repu"));
                objRepu.setCantRepto(rslSet.getString("cant_repu"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
}
