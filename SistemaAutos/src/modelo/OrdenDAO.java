/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static controlador.ControlOrdenTra.frmOrdenControl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import org.codehaus.groovy.syntax.Types;

/**
 *
 * @author Chris
 */
public class OrdenDAO extends ConectaBD {

    //Metodo Validar
    public boolean ValidarAuto(CarroBD objCarro) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection
        ResultSet rslSet = null;
        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Validar = reuConex.prepareCall("CALL SP_BUSCAR_CARRO_ORDEN(?)");
            Validar.setString(1, objCarro.getPlacaCarro());
            rslSet = Validar.executeQuery();
            if (rslSet.next()) {
                objCarro.setNomCliCarro(rslSet.getString("nomCliente"));
                objCarro.setTelCliCarro(rslSet.getString("tel_cliente"));
                objCarro.setMarcaCarro(rslSet.getString("nom_marca"));
                objCarro.setMotorCarro(rslSet.getString("modelo_vehiculo"));
                objCarro.setModCarro(rslSet.getString("sexo_cliente"));
                return true;//Si todo está OK retorna true
            }
            return false;//Si no hay datos
        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    public boolean GuardarOrden(OrdenBD objOrden) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_ORDEN(?,?,?,?,?,?,?,?)");
            Guardar.setString(1, objOrden.getFecOrden());
            Guardar.setString(2, objOrden.getCodCliOrden());
            Guardar.setString(3, objOrden.getCodEmpleOrden());
            Guardar.setString(4, objOrden.getCodVehiculo());
            Guardar.setString(5, objOrden.getValOrden());
            Guardar.setString(6, objOrden.getDetOrden());
            Guardar.setString(7, objOrden.getObsOrden());
            Guardar.registerOutParameter("codOrden", Types.INTEGER_NUMBER);//Recupero el parametro de salida
            int numOrden = Guardar.getInt("codOrden");//Guardo el id en la variable
            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    public boolean GuardarOrdenREP(OrdenRepBD objOrdenREP) {//Recibe objeto tipo CiudadBD

        Connection reuConex = conectarDB();//Variable de tipo connection

        try {
            //Invoca el SP y pasa los parametros
            CallableStatement Guardar = reuConex.prepareCall("CALL SP_GUARDAR_ORDENREP(?,?)");
            Guardar.setString(1, objOrdenREP.getCodRepuesto());
            Guardar.setString(2, objOrdenREP.getValCantidad());

            Guardar.execute();//Ejecta el SP
            return true;//Si todo está OK retorna true

        } catch (SQLException e) {//Captura el error
            e.printStackTrace();//Muestra el error
            return false;//Retorna false xq algo salió mal
        }
    }

    //MOdelo tabla Repuestos
    public DefaultTableModel tabRepuestos() {
        DefaultTableModel modTabRep = null;//Modelo para la tabla

        modTabRep = new DefaultTableModel();//Encabezados de las columnas
        modTabRep.addColumn("Código");
        modTabRep.addColumn("Descripción");
        modTabRep.addColumn("Cantidad");

//        String datGrid[] = new String[3];
//        datGrid[0] = String.valueOf(frmOrdenControl.txtCodRepuesto.getText());
//        datGrid[1] = String.valueOf(frmOrdenControl.txtDescRepuesto.getText());
//        datGrid[2] = String.valueOf(frmOrdenControl.spnCantRep.getValue().toString());
//        modTabRep.addRow(datGrid);
        return modTabRep;//Devuelve el modelo con los datos
    }

}
