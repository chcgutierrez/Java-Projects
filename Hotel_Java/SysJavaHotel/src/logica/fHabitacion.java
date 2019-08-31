//Contiene el CRUD de la tabla Habitación

package logica;

import datos.vHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fHabitacion {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    public int totFilas = 0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Tipo", "Estado"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[8];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL
        senSQL = "SELECT id_Habitacion, numero_hab, piso_hab, descr_hab, caract_hab, precio_dia_hab, tipo_hab, estado_hab FROM tbhabitacion WHERE piso_hab LIKE '%" + buscar + "%' ORDER BY id_Habitacion";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("id_Habitacion");
                regFilas[1] = rSLS.getString("numero_hab");
                regFilas[2] = rSLS.getString("piso_hab");
                regFilas[3] = rSLS.getString("descr_hab");
                regFilas[4] = rSLS.getString("caract_hab");
                regFilas[5] = rSLS.getString("precio_dia_hab");
                regFilas[6] = rSLS.getString("tipo_hab");
                regFilas[7] = rSLS.getString("estado_hab");
                
                totFilas = totFilas + 1;//Cuento las filas existentes
                nModelo.addRow(regFilas);
            }
            return nModelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    //Insertar Habitacion
    public boolean GuardarHab(vHabitacion DatosHab) {
        senSQL = "INSERT INTO tbhabitacion (numero_hab, piso_hab, descr_hab, caract_hab, precio_dia_hab, tipo_hab, estado_hab)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setString(1, DatosHab.getNumHab());
            prSTM.setString(2, DatosHab.getPisoHab());
            prSTM.setString(3, DatosHab.getDescHab());
            prSTM.setString(4, DatosHab.getCaracHab());
            prSTM.setDouble(5, DatosHab.getPrediaHab());
            prSTM.setString(6, DatosHab.getTipoHab());
            prSTM.setString(7, DatosHab.getEstHab());
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

    //Editar Habitacion
    public boolean EditarHab(vHabitacion DatosHab) {
        senSQL="UPDATE tbhabitacion SET numero_hab=?, piso_hab=?, descr_hab=?, caract_hab=?, precio_dia_hab=?, tipo_hab=?, estado_hab=? "+
                "WHERE id_Habitacion=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setString(1, DatosHab.getNumHab());
            prSTM.setString(2, DatosHab.getPisoHab());
            prSTM.setString(3, DatosHab.getDescHab());
            prSTM.setString(4, DatosHab.getCaracHab());
            prSTM.setDouble(5, DatosHab.getPrediaHab());
            prSTM.setString(6, DatosHab.getTipoHab());
            prSTM.setString(7, DatosHab.getEstHab());
            prSTM.setInt(8, DatosHab.getIdHab());
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
    
    //Reservar Habitacion
    public boolean RsvarHab(vHabitacion DatosHab) {
        senSQL="UPDATE tbhabitacion SET estado_hab='RESERVADA' "+
                "WHERE id_Habitacion=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosHab.getIdHab());
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
    
    //Ocupar Habitacion
    public boolean OcuparHab(vHabitacion DatosHab) {
        senSQL="UPDATE tbhabitacion SET estado_hab='OCUPADA' "+
                "WHERE id_Habitacion=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosHab.getIdHab());
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
    
    //Liberar Habitacion
    public boolean LiberarHab(vHabitacion DatosHab) {
        senSQL="UPDATE tbhabitacion SET estado_hab='DISPONIBLE' "+
                "WHERE id_Habitacion=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosHab.getIdHab());
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
    
    //Mantenimiento Habitacion
    public boolean ManttoHab(vHabitacion DatosHab) {
        senSQL="UPDATE tbhabitacion SET estado_hab='MANTENIMIENTO' "+
                "WHERE id_Habitacion=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosHab.getIdHab());
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

    //Borrar Habitacion
    public boolean BorrarHab(vHabitacion DatosHab) {
        senSQL="DELETE FROM tbhabitacion WHERE id_Habitacion=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosHab.getIdHab());
            
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
    
    //Cargar todos los datos de la tabla
    public DefaultTableModel VistaHab(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Número", "Piso", "Descripción", "Caracteristicas", "Precio", "Tipo", "Estado"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[8];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL
        senSQL = "SELECT id_Habitacion, numero_hab, piso_hab, descr_hab, caract_hab, precio_dia_hab, tipo_hab, estado_hab "
                + "FROM tbhabitacion WHERE piso_hab LIKE '%" + buscar + "%' AND estado_hab='DISPONIBLE' ORDER BY id_Habitacion";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("id_Habitacion");
                regFilas[1] = rSLS.getString("numero_hab");
                regFilas[2] = rSLS.getString("piso_hab");
                regFilas[3] = rSLS.getString("descr_hab");
                regFilas[4] = rSLS.getString("caract_hab");
                regFilas[5] = rSLS.getString("precio_dia_hab");
                regFilas[6] = rSLS.getString("tipo_hab");
                regFilas[7] = rSLS.getString("estado_hab");
                
                totFilas = totFilas + 1;//Cuento las filas existentes
                nModelo.addRow(regFilas);
            }
            return nModelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
