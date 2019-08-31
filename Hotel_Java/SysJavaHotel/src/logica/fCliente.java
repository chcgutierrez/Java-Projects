package logica;

import datos.vCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fCliente {

    private Conexion mysqlCon = new Conexion();
    private Connection nConex = mysqlCon.ConectarBD();
    private String senSQL = "";
    private String senSQL2 = "";
    public int totFilas = 0;

    //Cargar todos los datos de la tabla
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel nModelo;//Modelo para el grid
        //Arreglo para las Columnas de la tabla
        String[] titCols = {"Código", "Nombre", "Primer Apellido", "Segundo Apellido", "Documento", "Num. Documento", "Direccion", "Telefono", "Email", "Cod. Cliente"};
        //Arreglo para las Filas de la tabla
        String[] regFilas = new String[10];
        //Inicio l modelo para la tabla
        nModelo = new DefaultTableModel(null, titCols);
        //Armo la consulta SQL que une las 2 tablas
        senSQL = "SELECT P.id_Persona, P.nombre_per, P.prim_ape_per, P.seg_ape_per, P.tip_doc_per, P.num_doc_per, P.direccion_per, P.telefono_per, P.email_per, C.cod_cliente "
                + "FROM tbpersona P INNER JOIN tbcliente C ON P.id_Persona=C.id_Persona WHERE P.num_doc_per LIKE '%" + buscar + "%' ORDER BY P.id_Persona";
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
                regFilas[9] = rSLS.getString("C.cod_cliente");

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
    public boolean GuardarCliente(vCliente DatCliente) {
        senSQL = "INSERT INTO tbpersona (nombre_per, prim_ape_per, seg_ape_per, tip_doc_per, num_doc_per, direccion_per, telefono_per, email_per)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        senSQL2 = "INSERT INTO tbcliente (id_Persona, cod_cliente)"
                + "VALUES((SELECT id_Persona FROM tbpersona ORDER BY id_Persona DESC LIMIT 1),?)";//Inserto el ID de la tabla limitando la consulta al ultimo registro 
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);

            prSTM.setString(1, DatCliente.getNomPers());
            prSTM.setString(2, DatCliente.getPrapePers());
            prSTM.setString(3, DatCliente.getSegapePers());
            prSTM.setString(4, DatCliente.getTipdocPers());
            prSTM.setString(5, DatCliente.getNumdocPers());
            prSTM.setString(6, DatCliente.getDirePers());
            prSTM.setString(7, DatCliente.getTelPers());
            prSTM.setString(8, DatCliente.getEmailPers());

            PreparedStatement prSTM2 = nConex.prepareStatement(senSQL2);
            prSTM2.setString(1, DatCliente.getCodCliente());

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
    public boolean EditarCliente(vCliente DatCliente) {
        senSQL = "UPDATE tbpersona SET nombre_per=?, prim_ape_per=?, seg_ape_per=?, tip_doc_per=?, num_doc_per=?, direccion_per=?, telefono_per=?, email_per=? "
                + "WHERE id_Persona=?";
        senSQL2 = "UPDATE tbcliente SET cod_cliente=? WHERE id_Persona=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);

            prSTM.setString(1, DatCliente.getNomPers());
            prSTM.setString(2, DatCliente.getPrapePers());
            prSTM.setString(3, DatCliente.getSegapePers());
            prSTM.setString(4, DatCliente.getTipdocPers());
            prSTM.setString(5, DatCliente.getNumdocPers());
            prSTM.setString(6, DatCliente.getDirePers());
            prSTM.setString(7, DatCliente.getTelPers());
            prSTM.setString(8, DatCliente.getEmailPers());
            prSTM.setInt(9, DatCliente.getIdPers());

            PreparedStatement prSTM2 = nConex.prepareStatement(senSQL2);
            prSTM2.setString(1, DatCliente.getCodCliente());
            prSTM2.setInt(2, DatCliente.getIdPers());

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
    public boolean BorrarCliente(vCliente DatCliente) {
        senSQL = "DELETE FROM tbcliente WHERE id_Persona=?";
        senSQL2 = "DELETE FROM tbpersona WHERE id_Persona=?";
        try {
            PreparedStatement prSTM = nConex.prepareStatement(senSQL);
            prSTM.setInt(1, DatCliente.getIdPers());

            PreparedStatement prSTM2 = nConex.prepareStatement(senSQL2);
            prSTM2.setInt(1, DatCliente.getIdPers());

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

}
