//Contiene el CRUD de la tabla Pago
package logica;

import datos.vPago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fPago {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    public int totFilas = 0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Reserva", "Tipo Compr.", "Número Compr.", "IVA", "Total", "Fecha Compr", "Fecha Pago"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[8];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL
        senSQL = "SELECT id_Pago,\n"
                + "id_Reserva,\n"
                + "tipo_comprobante,\n"
                + "num_comprobante,\n"
                + "iva_pago,\n"
                + "total_pago,\n"
                + "fec_gen_comprobante,\n"
                + "fec_pago FROM tbpago WHERE id_Reserva= '" + buscar + "' ORDER BY id_Pago";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("id_Pago");
                regFilas[1] = rSLS.getString("id_Reserva");
                regFilas[2] = rSLS.getString("tipo_comprobante");
                regFilas[3] = rSLS.getString("num_comprobante");
                regFilas[4] = rSLS.getString("iva_pago");
                regFilas[5] = rSLS.getString("total_pago");
                regFilas[6] = rSLS.getString("fec_gen_comprobante");
                regFilas[7] = rSLS.getString("fec_pago");

                totFilas = totFilas + 1;//Cuento las filas existentes
                nModelo.addRow(regFilas);
            }
            return nModelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //Insertar Pago
    public boolean GuardarPago(vPago DatosPago) {
        senSQL = "INSERT INTO tbpago (id_Reserva,\n"
                + "tipo_comprobante,\n"
                + "num_comprobante,\n"
                + "iva_pago,\n"
                + "total_pago,\n"
                + "fec_gen_comprobante,\n"
                + "fec_pago)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosPago.getIdRsvaPago());
            prSTM.setString(2, DatosPago.getTipComprPago());
            prSTM.setString(3, DatosPago.getNumComprPago());
            prSTM.setDouble(4, DatosPago.getIvaPago());
            prSTM.setDouble(5, DatosPago.getTotalPago());
            prSTM.setDate(6, DatosPago.getFecGenCompr());
            prSTM.setDate(7, DatosPago.getFecPago());

            int n = prSTM.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    //Editar Pago
    public boolean EditarPago(vPago DatosPago) {
        senSQL = "UPDATE tbpago SET id_Reserva=?, tipo_comprobante=?, num_comprobante=?, iva_pago=?, "
                + "total_pago=?, fec_gen_comprobante=?, fec_pago=? "
                + "WHERE id_Pago=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosPago.getIdRsvaPago());
            prSTM.setString(2, DatosPago.getTipComprPago());
            prSTM.setString(3, DatosPago.getNumComprPago());
            prSTM.setDouble(4, DatosPago.getIvaPago());
            prSTM.setDouble(5, DatosPago.getTotalPago());
            prSTM.setDate(6, DatosPago.getFecGenCompr());
            prSTM.setDate(7, DatosPago.getFecPago());
            prSTM.setInt(8, DatosPago.getIdPago());

            int n = prSTM.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    //Borrar Producto
    public boolean BorrarPago(vPago DatosPago) {
        senSQL = "DELETE FROM tbpago WHERE id_Pago=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosPago.getIdPago());

            int n = prSTM.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

}
