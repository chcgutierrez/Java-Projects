//Contiene el CRUD de la tabla Consumo
package logica;

import datos.vConsumo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fConsumo {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    public int totFilas = 0;
    public double totConsumo = 0.0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Cod. Consumo", "Cod. Reserva", "Cod. Producto", "Producto", "Cantidad", "Precio", "Estado"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[7];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL
        senSQL = "SELECT\n"
                + "C.id_Consumo,\n"
                + "C.id_Reserva,\n"
                + "C.id_Producto,\n"
                + "P.nombre_prod,\n"
                + "C.cantidad,\n"
                + "C.precio_venta,\n"
                + "C.estado_cons\n"
                + "FROM tbconsumo C INNER JOIN tbproducto P ON\n"
                + "C.id_Producto=P.id_Producto WHERE\n"
                + "C.id_Reserva= '" + buscar + "' ORDER BY C.id_Consumo DESC";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("C.id_Consumo");
                regFilas[1] = rSLS.getString("C.id_Reserva");
                regFilas[2] = rSLS.getString("C.id_Producto");
                regFilas[3] = rSLS.getString("P.nombre_prod");
                regFilas[4] = rSLS.getString("C.cantidad");
                regFilas[5] = rSLS.getString("C.precio_venta");
                regFilas[6] = rSLS.getString("C.estado_cons");

                totFilas = totFilas + 1;//Cuento las filas existentes
                totConsumo= totConsumo+( rSLS.getInt("C.cantidad")*rSLS.getDouble("C.precio_venta") );//Totalizo Precio * Cantidad
                nModelo.addRow(regFilas);
            }
            return nModelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //Insertar Consumo
    public boolean GuardarConsu(vConsumo DatoConsu) {
        senSQL = "INSERT INTO tbconsumo (id_Reserva, id_Producto, cantidad, precio_venta, estado_cons)"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatoConsu.getId_RsvaConsu());
            prSTM.setInt(2, DatoConsu.getId_ProdConsu());
            prSTM.setInt(3, DatoConsu.getCantConsu());
            prSTM.setDouble(4, DatoConsu.getPreVenConsu());
            prSTM.setString(5, DatoConsu.getEstConsu());

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
    public boolean EditarConsu(vConsumo DatoConsu) {
        senSQL = "UPDATE tbconsumo SET id_Reserva=?, id_Producto=?, cantidad=?, precio_venta=?, estado_cons=? "
                + "WHERE id_Consumo=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatoConsu.getId_RsvaConsu());
            prSTM.setInt(2, DatoConsu.getId_ProdConsu());
            prSTM.setInt(3, DatoConsu.getCantConsu());
            prSTM.setDouble(4, DatoConsu.getPreVenConsu());
            prSTM.setString(5, DatoConsu.getEstConsu());
            prSTM.setInt(6, DatoConsu.getIdConsu());

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

    //Borrar Consumo
    public boolean BorrarConsu(vConsumo DatoConsu) {
        senSQL = "DELETE FROM tbconsumo WHERE id_Consumo=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatoConsu.getIdConsu());

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
