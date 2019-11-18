/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class ClienteDAO extends ConectaBD {

    //Metodo Guardar
    public boolean GuardarCliente(ClienteBD objCliente) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?)");
            Guardar.setString(1, objCliente.getTipdoCliente());
            Guardar.setString(2, objCliente.getNumdoCliente());
            Guardar.setString(3, objCliente.getNomCliente());
            Guardar.setString(4, objCliente.getApeCliente());
            Guardar.setString(5, objCliente.getSexCliente());
            Guardar.setString(6, objCliente.getDirCliente());
            Guardar.setString(7, objCliente.getTelCliente());
            Guardar.setString(8, objCliente.getMailCliente());
            Guardar.setString(9, objCliente.getEstCliente());
            Guardar.setString(10, objCliente.getFecnCliente());
            Guardar.setString(11, objCliente.getCiuCliente());
            Guardar.setString(12, objCliente.getObsCliente());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Editar
    public boolean EditarCliente(ClienteBD objCliente) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?)");
            Editar.setString(1, objCliente.getTipdoCliente());
            Editar.setString(2, objCliente.getNumdoCliente());
            Editar.setString(3, objCliente.getNomCliente());
            Editar.setString(4, objCliente.getApeCliente());
            Editar.setString(5, objCliente.getSexCliente());
            Editar.setString(6, objCliente.getDirCliente());
            Editar.setString(7, objCliente.getTelCliente());
            Editar.setString(8, objCliente.getMailCliente());
            Editar.setString(9, objCliente.getEstCliente());
            Editar.setString(10, objCliente.getFecnCliente());
            Editar.setString(11, objCliente.getCiuCliente());
            Editar.setString(12, objCliente.getObsCliente());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidaCliente(ClienteBD objCliente) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_CLIENTE(?)");
            Validar.setString(1, objCliente.getNumdoCliente());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objCliente.setTipdoCliente(rslSet.getString("tip_doc_cliente"));
                objCliente.setNumdoCliente(rslSet.getString("num_doc_cliente"));
                objCliente.setNomCliente(rslSet.getString("nom_cliente"));
                objCliente.setApeCliente(rslSet.getString("ape_cliente"));
                objCliente.setSexCliente(rslSet.getString("sexo_cliente"));
                objCliente.setDirCliente(rslSet.getString("direc_cliente"));
                objCliente.setTelCliente(rslSet.getString("tel_cliente"));
                objCliente.setMailCliente(rslSet.getString("email_cliente"));
                objCliente.setEstCliente(rslSet.getString("estado_cliente"));
                objCliente.setFecnCliente(rslSet.getString("fec_nac_cliente"));
                objCliente.setCiuCliente(rslSet.getString("ciudad_cliente"));
                objCliente.setObsCliente(rslSet.getString("obs_cliente"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Búsqueda
    public DefaultTableModel BusqCliente(ClienteBD objCliente) {//Recibe objeto tipo CiudadBD

        DefaultTableModel modBusqCliente = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {

            modBusqCliente = new DefaultTableModel();//Encabezados de las columnas
            modBusqCliente.addColumn("Código");
            modBusqCliente.addColumn("Nombre");
            modBusqCliente.addColumn("Telefono");

            String datosBusq[] = new String[3];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_CLIENTE(?)");
            Busqueda.setString(1, objCliente.getNomCliente());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("num_doc_cliente");
                datosBusq[1] = rslSet.getString("nom_cliente");
                datosBusq[2] = rslSet.getString("tel_cliente");
                modBusqCliente.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqCliente;//Devuelve el modelo con los datos
    }

    //Llenar combo Tipo Docum 
    public boolean CargaTipoDoc(JComboBox cboTipoDoc) {//Recibe objeto tipo CiudadBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_TIPODOC()");
            rslSet = Combo.executeQuery();
            cboTipoDoc.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboTipoDoc.addItem(rslSet.getString("tipo_doc"));
                cboTipoDoc.addItem(rslSet.getString("desc_tipodoc"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Llenar combo Ciudad
    public boolean CargaCiudad(JComboBox cboCiudad) {//Recibe objeto tipo CiudadBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_CIUDAD()");
            rslSet = Combo.executeQuery();
            cboCiudad.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboCiudad.addItem(rslSet.getString("idCiudad"));
                cboCiudad.addItem(rslSet.getString("nom_ciudad"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

}
