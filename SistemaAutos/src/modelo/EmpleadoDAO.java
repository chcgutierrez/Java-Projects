/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class EmpleadoDAO extends ConectaBD {

    //Metodo Guardar
    public boolean GuardarEmple(EmpleadoBD objEmple) {//Recibe objeto tipo EmpleadoBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_EMPLE(?,?,?,?,?,?,?,?)");
            Guardar.setString(1, objEmple.getTipdoEmple());
            Guardar.setString(2, objEmple.getNumdoEmple());
            Guardar.setString(3, objEmple.getNomEmple());
            Guardar.setString(4, objEmple.getApeEmple());
            Guardar.setString(5, objEmple.getSexEmple());
            Guardar.setString(6, objEmple.getEstEmple());
            Guardar.setString(7, objEmple.getCiuEmple());
            Guardar.setString(8, objEmple.getObsEmple());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
     //Metodo Guardar
    public boolean EditarEmple(EmpleadoBD objEmple) {//Recibe objeto tipo EmpleadoBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_EMPLE(?,?,?,?,?,?,?,?)");
            Editar.setString(1, objEmple.getNumdoEmple());
            Editar.setString(2, objEmple.getTipdoEmple());            
            Editar.setString(3, objEmple.getNomEmple());
            Editar.setString(4, objEmple.getApeEmple());
            Editar.setString(5, objEmple.getSexEmple());
            Editar.setString(6, objEmple.getEstEmple());
            Editar.setString(7, objEmple.getCiuEmple());
            Editar.setString(8, objEmple.getObsEmple());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Validar
    public boolean ValidarEmple(EmpleadoBD objEmple) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_EMPLE(?)");
            Validar.setString(1, objEmple.getNumdoEmple());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objEmple.setTipdoEmple(rslSet.getString("tip_doc_mecanico"));
                objEmple.setNumdoEmple(rslSet.getString("num_doc_mecanico"));
                objEmple.setNomEmple(rslSet.getString("nom_mecanico"));
                objEmple.setApeEmple(rslSet.getString("ape_mecanico"));
                objEmple.setSexEmple(rslSet.getString("sexo_mecanico"));
                objEmple.setEstEmple(rslSet.getString("estado_mecanico"));
                objEmple.setCiuEmple(rslSet.getString("ciudad_mecanico"));
                objEmple.setObsEmple(rslSet.getString("obs_mecanico"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }
    
    //Metodo Búsqueda
    public DefaultTableModel BusEmpleado(EmpleadoBD objEmple) {//Recibe objeto tipo CiudadBD

        DefaultTableModel modBusEmpleado = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {

            modBusEmpleado = new DefaultTableModel();//Encabezados de las columnas
            modBusEmpleado.addColumn("Código");
            modBusEmpleado.addColumn("Nombre");

            String datosBusq[] = new String[2];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_EMPLE(?)");
            Busqueda.setString(1, objEmple.getNomEmple());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("num_doc_mecanico");
                datosBusq[1] = rslSet.getString("nEmpleado"); //Alias del concatenado en BD
                modBusEmpleado.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusEmpleado;//Devuelve el modelo con los datos
    }
    
    //Llenar combo Tipo Documento
    public void CargaTipoDoc(JComboBox cboTipoDoc) {//Recibe objeto tipo CiudadBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_TIPODOC()");
            rslSet = Combo.executeQuery();
            cboTipoDoc.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboTipoDoc.addItem(rslSet.getString("desc_tipodoc"));
            }
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
        }
    }

    //Llenar combo Ciudad
    public void CargaCiudad(JComboBox cboCiudad) {//Recibe objeto tipo CiudadBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_CIUDAD()");
            rslSet = Combo.executeQuery();
            cboCiudad.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboCiudad.addItem(rslSet.getString("nom_ciudad"));
            }
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
        }
    }
}
