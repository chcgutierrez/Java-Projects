package logica;

import datos.vEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fEmpleado {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    private String senSQL2 = "";
    public int totFilas = 0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Nombre", "Primer Apellido", "Segundo Apellido", "Documento", "Num. Documento", "Direccion", "Telefono", "Email", "Sueldo", "Acceso", "Login", "Clave", "Estado"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[14];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL que une las 2 tablas
        senSQL = "SELECT P.id_Persona, P.nombre_per, P.prim_ape_per, P.seg_ape_per, P.tip_doc_per, P.num_doc_per, P.direccion_per, P.telefono_per, P.email_per, E.sueldo_trabj, E.acceso_trabj, E.login_trabj, E.password_trabj, E.estado_trabj "
                + "FROM tbpersona P INNER JOIN tbtrabajador E ON P.id_Persona=E.id_Persona WHERE P.num_doc_per LIKE '%" + buscar + "%' ORDER BY P.id_Persona";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("P.id_Persona");
                regFilas[1] = rSLS.getString("P.nombre_per");
                regFilas[2] = rSLS.getString("P.prim_ape_per");
                regFilas[3] = rSLS.getString("P.seg_ape_per");
                regFilas[4] = rSLS.getString("P.tip_doc_per");
                regFilas[5] = rSLS.getString("P.num_doc_per");
                regFilas[6] = rSLS.getString("P.direccion_per");
                regFilas[7] = rSLS.getString("P.telefono_per");
                regFilas[8] = rSLS.getString("P.email_per");
                regFilas[9] = rSLS.getString("E.sueldo_trabj");
                regFilas[10] = rSLS.getString("E.acceso_trabj");
                regFilas[11] = rSLS.getString("E.login_trabj");
                regFilas[12] = rSLS.getString("E.password_trabj");
                regFilas[13] = rSLS.getString("E.estado_trabj");

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
    public boolean GuardarEmp(vEmpleado DatosEmp) {
        senSQL = "INSERT INTO tbpersona (nombre_per, prim_ape_per, seg_ape_per, tip_doc_per, num_doc_per, direccion_per, telefono_per, email_per)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        senSQL2 = "INSERT INTO tbtrabajador (id_Persona, sueldo_trabj, acceso_trabj, login_trabj, password_trabj, estado_trabj)"
                + "VALUES((SELECT id_Persona FROM tbpersona ORDER BY id_Persona DESC LIMIT 1),?,?,?,?,?)";//Inserto el ID de la tabla limitando la consulta al ultimo registro 
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);

            prSTM.setString(1, DatosEmp.getNomPers());
            prSTM.setString(2, DatosEmp.getPrapePers());
            prSTM.setString(3, DatosEmp.getSegapePers());
            prSTM.setString(4, DatosEmp.getTipdocPers());
            prSTM.setString(5, DatosEmp.getNumdocPers());
            prSTM.setString(6, DatosEmp.getDirePers());
            prSTM.setString(7, DatosEmp.getTelPers());
            prSTM.setString(8, DatosEmp.getEmailPers());

            PreparedStatement prSTM2 = nConex.prepareStatement(senSQL2);
            prSTM2.setDouble(1, DatosEmp.getSueldoEmp());
            prSTM2.setString(2, DatosEmp.getAccesoEmp());
            prSTM2.setString(3, DatosEmp.getLoginEmp());
            prSTM2.setString(4, DatosEmp.getClaveEmp());
            prSTM2.setString(5, DatosEmp.getEstEmp());

            int n = prSTM.executeUpdate();

            if (n != 0) {
                int n2 = prSTM2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    //Editar Producto
    public boolean EditarEmp(vEmpleado DatosEmp) {
        senSQL = "UPDATE tbpersona SET nombre_per=?, prim_ape_per=?, seg_ape_per=?, tip_doc_per=?, num_doc_per=?, direccion_per=?, telefono_per=?, email_per=? "
                + "WHERE id_Persona=?";
        senSQL2 = "UPDATE tbtrabajador SET sueldo_trabj=?, acceso_trabj=?, login_trabj=?, password_trabj=?, estado_trabj=? WHERE id_Persona=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);

            prSTM.setString(1, DatosEmp.getNomPers());
            prSTM.setString(2, DatosEmp.getPrapePers());
            prSTM.setString(3, DatosEmp.getSegapePers());
            prSTM.setString(4, DatosEmp.getTipdocPers());
            prSTM.setString(5, DatosEmp.getNumdocPers());
            prSTM.setString(6, DatosEmp.getDirePers());
            prSTM.setString(7, DatosEmp.getTelPers());
            prSTM.setString(8, DatosEmp.getEmailPers());
            prSTM.setInt(9, DatosEmp.getIdPers());

            PreparedStatement prSTM2 = nConex.prepareStatement(senSQL2);
            prSTM2.setDouble(1, DatosEmp.getSueldoEmp());
            prSTM2.setString(2, DatosEmp.getAccesoEmp());
            prSTM2.setString(3, DatosEmp.getLoginEmp());
            prSTM2.setString(4, DatosEmp.getClaveEmp());
            prSTM2.setString(5, DatosEmp.getEstEmp());
            prSTM2.setInt(6, DatosEmp.getIdPers());

            int n = prSTM.executeUpdate();

            if (n != 0) {
                int n2 = prSTM2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    //Borrar Producto
    public boolean BorrarEmp(vEmpleado DatosEmp) {
        senSQL = "DELETE FROM tbtrabajador WHERE id_Persona=?";
        senSQL2 = "DELETE FROM tbpersona WHERE id_Persona=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatosEmp.getIdPers());

            PreparedStatement prSTM2 = nConex.prepareStatement(senSQL2);
            prSTM2.setInt(1, DatosEmp.getIdPers());

            int n = prSTM.executeUpdate();

            if (n != 0) {
                int n2 = prSTM2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    //Funcion Login
    public DefaultTableModel Login(String usuLogin, String usuClave) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Nombre", "Primer Apellido", "Segundo Apellido", "Acceso", "Login", "Clave", "Estado"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[8];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL que une las 2 tablas
        senSQL = "SELECT P.id_Persona, P.nombre_per, P.prim_ape_per, P.seg_ape_per, E.acceso_trabj, E.login_trabj, E.password_trabj, E.estado_trabj "
                + "FROM tbpersona P INNER JOIN tbtrabajador E ON P.id_Persona=E.id_Persona "
                + "WHERE E.login_trabj= '" + usuLogin + "' AND E.password_trabj= '" + usuClave + "' AND E.estado_trabj='A'";
        try {
            Statement nSTM = nConex.createStatement();
            ResultSet rSLS = nSTM.executeQuery(senSQL);//Ejecuto el SQL
            //Recorro las filas con el While
            while (rSLS.next()) {
                regFilas[0] = rSLS.getString("P.id_Persona");
                regFilas[1] = rSLS.getString("P.nombre_per");
                regFilas[2] = rSLS.getString("P.prim_ape_per");
                regFilas[3] = rSLS.getString("P.seg_ape_per");
                regFilas[4] = rSLS.getString("E.acceso_trabj");
                regFilas[5] = rSLS.getString("E.login_trabj");
                regFilas[6] = rSLS.getString("E.password_trabj");
                regFilas[7] = rSLS.getString("E.estado_trabj");

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
