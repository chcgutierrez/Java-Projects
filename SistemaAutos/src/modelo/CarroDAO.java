/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class CarroDAO extends ConectaBD {

    //Metodo Guardar
    public boolean GuardarCarro(CarroBD objCarro) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_CARRO(?,?,?,?,?,?,?,?,?,?)");
            Guardar.setString(1, objCarro.getPlacaCarro());
            Guardar.setString(2, objCarro.getCiuCarro());
            Guardar.setString(3, objCarro.getMarcaCarro());
            Guardar.setString(4, objCarro.getTipoCarro());
            Guardar.setString(5, objCarro.getColorCarro());
            Guardar.setString(6, objCarro.getModCarro());
            Guardar.setString(7, objCarro.getMotorCarro());
            Guardar.setString(8, objCarro.getEstCarro());            
            Guardar.setString(9, objCarro.getObsCarro());
            Guardar.setString(10, objCarro.getClienteCarro());
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Editar
    public boolean EditarCarro(CarroBD objCarro) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Editar = reuConex.prepareCall("CALL SP_EDITAR_CARRO(?,?,?,?,?,?,?,?,?,?)");
            Editar.setString(1, objCarro.getPlacaCarro());
            Editar.setString(2, objCarro.getCiuCarro());
            Editar.setString(3, objCarro.getMarcaCarro());
            Editar.setString(4, objCarro.getTipoCarro());
            Editar.setString(5, objCarro.getColorCarro());
            Editar.setString(6, objCarro.getModCarro());
            Editar.setString(7, objCarro.getMotorCarro());
            Editar.setString(8, objCarro.getEstCarro());
            Editar.setString(9, objCarro.getObsCarro());
            Editar.setString(10, objCarro.getClienteCarro());
            Editar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Validar
    public boolean ValidarCarro(CarroBD objCarro) {//Recibe objeto tipo ColorBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_CARRO(?)");
            Validar.setString(1, objCarro.getPlacaCarro());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objCarro.setPlacaCarro(rslSet.getString("placa_vehiculo"));
                objCarro.setMarcaCarro(rslSet.getString("marca_vehiculo"));
                objCarro.setTipoCarro(rslSet.getString("tipo_vehiculo"));
                objCarro.setMotorCarro(rslSet.getString("num_motor"));
                objCarro.setModCarro(rslSet.getString("modelo_vehiculo"));
                objCarro.setEstCarro(rslSet.getString("estado_vehiculo"));
                objCarro.setColorCarro(rslSet.getString("color_vehiculo"));
                objCarro.setClienteCarro(rslSet.getString("num_doc_cliente"));
                objCarro.setNomCliCarro(rslSet.getString("nomCliente"));
                objCarro.setCiuCarro(rslSet.getString("ciudad_vehiculo"));
                objCarro.setObsCarro(rslSet.getString("obs_vehiculo"));                
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Metodo Búsqueda
    public DefaultTableModel BusqCarro(CarroBD objCarro) {//Recibe objeto tipo CiudadBD

        DefaultTableModel modBusqCarro = null;//Modelo para la tabla
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;//ResultSet para el conjunto de resultados
        try {

            modBusqCarro = new DefaultTableModel();//Encabezados de las columnas
            modBusqCarro.addColumn("Placa");
            modBusqCarro.addColumn("Motor");
            modBusqCarro.addColumn("Cliente");

            String datosBusq[] = new String[3];//Arreglo para las filas

            //Invoca el SP y pasa los parametros
            CallableStatement Busqueda = reuConex.prepareCall("CALL SP_BUSQ_CARRO(?)");
            Busqueda.setString(1, objCarro.getPlacaCarro());
            rslSet = Busqueda.executeQuery();
            while (rslSet.next()) {//Pone el valor en cada campo del arreglo
                datosBusq[0] = rslSet.getString("placa_vehiculo");
                datosBusq[1] = rslSet.getString("num_motor"); //Alias del concatenado en BD
                datosBusq[2] = rslSet.getString("nomCliente");
                modBusqCarro.addRow(datosBusq);//Agrega las filas al modelo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modBusqCarro;//Devuelve el modelo con los datos
    }
    
    //Metodo Cliente - Carro
    public boolean ClienteCarro(CarroBD objCarro) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement cliCarro = reuConex.prepareCall("CALL SP_BUSCAR_CLIENTECARRO(?)");
            cliCarro.setString(1, objCarro.getClienteCarro());
            rslSet = cliCarro.executeQuery();
            if (rslSet.next()) {
                objCarro.setNomCliCarro(rslSet.getString("carCliente"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //Llenar combo Marca
    public void CargaMarca(JComboBox cboMarca) {//Recibe objeto tipo MarcaBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_MARCA()");
            rslSet = Combo.executeQuery();
            cboMarca.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboMarca.addItem(rslSet.getString("nom_marca"));
            }
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
        }
    }

    //Llenar combo Tipo
    public void CargaTipo(JComboBox cboTipo) {//Recibe objeto tipo TipoBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_TIPO()");
            rslSet = Combo.executeQuery();
            cboTipo.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboTipo.addItem(rslSet.getString("nom_tipo"));
            }
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
        }
    }

    //Llenar combo Color
    public void CargaColor(JComboBox cboColor) {//Recibe objeto tipo ColorBD
        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Combo = reuConex.prepareCall("CALL SP_CBO_COLOR()");
            rslSet = Combo.executeQuery();
            cboColor.addItem("    - Seleccione -    ");
            while (rslSet.next()) {
                cboColor.addItem(rslSet.getString("nom_color"));
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
