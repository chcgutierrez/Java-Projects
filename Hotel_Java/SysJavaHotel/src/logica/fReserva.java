//Contiene el CRUD de la tabla Reserva
package logica;

import datos.vReserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fReserva {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    public int totFilas = 0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Cod. Habitacion", "Número", "Cod. Cliente", "Cliente", "Cod. Empleado", "Empleado", "Tipo Rsva",
            "Fecha Rsva", "Rsva. Inicia", "Rsva. Fin", "Costo Rsva", "Estado Rsva"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[13];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL
        senSQL = "SELECT\n"
                + "R.id_Reserva,\n"
                + "R.id_habitacion,\n"
                + "H.numero_hab,\n"
                + "R.id_cliente,\n"
                + "(SELECT nombre_per FROM tbpersona WHERE id_Persona=R.id_cliente) AS Nom_Cliente,\n"
                + "(SELECT prim_ape_per FROM tbpersona WHERE id_Persona=R.id_cliente) AS Ape_Cliente,\n"
                + "R.id_trabajador,\n"
                + "(SELECT nombre_per FROM tbpersona WHERE id_Persona=R.id_trabajador) AS Nom_Empleado,\n"
                + "(SELECT prim_ape_per FROM tbpersona WHERE id_Persona=R.id_trabajador) AS Ape_Empleado,\n"
                + "R.tipo_reserva,\n"
                + "R.fecha_reserva,\n"
                + "R.fec_ing_cliente,\n"
                + "R.fec_sale_cliente,\n"
                + "R.costo_tot_aloj,\n"
                + "R.estado_reserva\n"
                + "FROM tbreserva R INNER JOIN tbhabitacion H\n"
                + "ON R.id_habitacion=H.id_Habitacion WHERE\n"
                + "R.fecha_reserva LIKE '%" + buscar + "%' ORDER BY R.id_Reserva DESC";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("R.id_Reserva");
                regFilas[1] = rSLS.getString("R.id_habitacion");
                regFilas[2] = rSLS.getString("H.numero_hab");
                regFilas[3] = rSLS.getString("R.id_cliente");
                regFilas[4] = rSLS.getString("Nom_Cliente") + " " + rSLS.getString("Ape_Cliente");
                regFilas[5] = rSLS.getString("R.id_trabajador");
                regFilas[6] = rSLS.getString("Nom_Empleado") + " " + rSLS.getString("Ape_Empleado");
                regFilas[7] = rSLS.getString("R.tipo_reserva");
                regFilas[8] = rSLS.getString("R.fecha_reserva");
                regFilas[9] = rSLS.getString("R.fec_ing_cliente");
                regFilas[10] = rSLS.getString("R.fec_sale_cliente");
                regFilas[11] = rSLS.getString("R.costo_tot_aloj");
                regFilas[12] = rSLS.getString("R.estado_reserva");

                totFilas = totFilas + 1;//Cuento las filas existentes
                nModelo.addRow(regFilas);
            }
            return nModelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //Insertar Reserva
    public boolean GuardarRsva(vReserva DatosRsva) {
        senSQL = "INSERT INTO tbreserva (id_habitacion,\n"
                + "id_cliente,\n"
                + "id_trabajador,\n"
                + "tipo_reserva,\n"
                + "fecha_reserva,\n"
                + "fec_ing_cliente,\n"
                + "fec_sale_cliente,\n"
                + "costo_tot_aloj,\n"
                + "estado_reserva) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosRsva.getCodigoHab());
            prSTM.setInt(2, DatosRsva.getCodigoCli());
            prSTM.setInt(3, DatosRsva.getCodigoEmp());
            prSTM.setString(4, DatosRsva.getTipoRsva());
            prSTM.setDate(5, DatosRsva.getFechaRsva());
            prSTM.setDate(6, DatosRsva.getFecIniRsva());
            prSTM.setDate(7, DatosRsva.getFecFinRsva());
            prSTM.setDouble(8, DatosRsva.getCostoRsva());
            prSTM.setString(9, DatosRsva.getEstRsva());

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

    //Editar Reserva
    public boolean EditarRsva(vReserva DatosRsva) {
        senSQL = "UPDATE tbreserva SET id_habitacion=?, "
                + "id_cliente=?, "
                + "id_trabajador=?, "
                + "tipo_reserva=?, "
                + "fecha_reserva=?, "
                + "fec_ing_cliente=?, "
                + "fec_sale_cliente=?, "
                + "costo_tot_aloj=?, "
                + "estado_reserva=? "
                + "WHERE id_Reserva=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosRsva.getCodigoHab());
            prSTM.setInt(2, DatosRsva.getCodigoCli());
            prSTM.setInt(3, DatosRsva.getCodigoEmp());
            prSTM.setString(4, DatosRsva.getTipoRsva());
            prSTM.setDate(5, DatosRsva.getFechaRsva());
            prSTM.setDate(6, DatosRsva.getFecIniRsva());
            prSTM.setDate(7, DatosRsva.getFecFinRsva());
            prSTM.setDouble(8, DatosRsva.getCostoRsva());
            prSTM.setString(9, DatosRsva.getEstRsva());
            prSTM.setInt(10, DatosRsva.getIdReserva());

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
    
    //Pagar Reserva
    public boolean PagarRsva(vReserva DatosRsva) {
        senSQL = "UPDATE tbreserva SET estado_reserva='PAGADA' "
                + "WHERE id_Reserva=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosRsva.getIdReserva());

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

    //Borrar Reserva
    public boolean BorrarRsva(vReserva DatosRsva) {
        senSQL = "DELETE FROM tbreserva WHERE id_Reserva=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosRsva.getIdReserva());

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
