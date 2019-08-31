//Contiene el CRUD de la tabla Habitación
package logica;

import datos.vProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fProducto {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    public int totFilas = 0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Nombre", "Descripción", "Und. Medida", "Val. Venta"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[5];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL
        senSQL = "SELECT id_Producto, nombre_prod, descr_prod, und_med_prod, val_venta_prod FROM tbproducto WHERE nombre_prod LIKE '%" + buscar + "%' ORDER BY id_Producto";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("id_Producto");
                regFilas[1] = rSLS.getString("nombre_prod");
                regFilas[2] = rSLS.getString("descr_prod");
                regFilas[3] = rSLS.getString("und_med_prod");
                regFilas[4] = rSLS.getString("val_venta_prod");

                totFilas = totFilas + 1;//Cuento las filas existentes
                nModelo.addRow(regFilas);
            }
            return nModelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //Insertar Producto
    public boolean GuardarProd(vProducto DatosProd) {
        senSQL = "INSERT INTO tbproducto (nombre_prod, descr_prod, und_med_prod, val_venta_prod)"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setString(1, DatosProd.getNomProd());
            prSTM.setString(2, DatosProd.getDescProd());
            prSTM.setString(3, DatosProd.getUndmProd());
            prSTM.setDouble(4, DatosProd.getValvProd());
            
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

    //Editar Producto
    public boolean EditarProd(vProducto DatosProd) {
        senSQL = "UPDATE tbproducto SET nombre_prod=?, descr_prod=?, und_med_prod=?, val_venta_prod=? "
                + "WHERE id_Producto=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setString(1, DatosProd.getNomProd());
            prSTM.setString(2, DatosProd.getDescProd());
            prSTM.setString(3, DatosProd.getUndmProd());
            prSTM.setDouble(4, DatosProd.getValvProd());
            prSTM.setInt(5, DatosProd.getIdProd());
            
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
    public boolean BorrarProd(vProducto DatosProd) {
        senSQL = "DELETE FROM tbproducto WHERE id_Producto=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosProd.getIdProd());

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
