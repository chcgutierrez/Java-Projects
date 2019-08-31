package modelo;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ClienteCRUD extends ConexionDB {

    //Guardar
    public boolean GuardarCliente (Cliente nCliente) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tb_cliente(num_doc_cliente,tip_doc_cliente,nom_cliente,ape_cliente,sexo_cliente,direc_cliente,tel_cliente,email_cliente,estado_cliente,fec_nac_cliente,ciudad_cliente,obs_cliente) "
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nCliente.getNum_doc_cliente());
            prStat.setString(2, nCliente.getTipo_doc_cliente());
            prStat.setString(3, nCliente.getNom_cliente());
            prStat.setString(4, nCliente.getApe_cliente());
            prStat.setString(5, nCliente.getSexo_cliente());
            prStat.setString(6, nCliente.getDirec_cliente());
            prStat.setString(7, nCliente.getTel_cliente());
            prStat.setString(8, nCliente.getEmail_cliente());
            prStat.setString(9, nCliente.getEstado_cliente());
            prStat.setString(10, nCliente.getFec_nac_cliente());
            prStat.setString(11, nCliente.getCiudad_cliente());
            prStat.setString(12, nCliente.getObs_cliente());            
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Editar
    public boolean EditarCliente(Cliente nCliente) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tb_cliente SET "
                                + "num_doc_cliente=?,"
                                + "tip_doc_cliente=?,"
                                + "nom_cliente=? "
                                + "ape_cliente=? "
                                + "sexo_cliente=? "
                                + "direc_cliente=? "
                                + "tel_cliente=? "
                                + "email_cliente=? "
                                + "estado_cliente=? "
                                + "fec_nac_cliente=? "
                                + "ciudad_cliente=? "
                                + "obs_cliente=? "
                                + "WHERE num_doc_cliente=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nCliente.getNum_doc_cliente());
            prStat.setString(2, nCliente.getTipo_doc_cliente());
            prStat.setString(3, nCliente.getNom_cliente());
            prStat.setString(4, nCliente.getApe_cliente());
            prStat.setString(5, nCliente.getSexo_cliente());
            prStat.setString(6, nCliente.getDirec_cliente());
            prStat.setString(7, nCliente.getTel_cliente());
            prStat.setString(8, nCliente.getEmail_cliente());
            prStat.setString(9, nCliente.getEstado_cliente());
            prStat.setString(10, nCliente.getFec_nac_cliente());
            prStat.setString(11, nCliente.getCiudad_cliente());
            prStat.setString(12, nCliente.getObs_cliente());   
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar
    public boolean ValidarCliente(Cliente nCliente) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "SELECT count(*) FROM tb_cliente WHERE num_doc_cliente=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nCliente.getNum_doc_cliente());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Ver Todos
    public DefaultTableModel TodosClientes() {
        DefaultTableModel modeloClientes = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloClientes = new DefaultTableModel();
            modeloClientes.addColumn("Documento");
            modeloClientes.addColumn("Tipo");
            modeloClientes.addColumn("Nombres");
            modeloClientes.addColumn("Apellidos");
            modeloClientes.addColumn("Sexo");
            modeloClientes.addColumn("Dirección");
            modeloClientes.addColumn("Télefono");
            modeloClientes.addColumn("Email");
            modeloClientes.addColumn("Fecha Nacimiento");
            modeloClientes.addColumn("Ciudad");


            String datosCliente[] = new String[12];
            
            String instrSQL = "SELECT num_doc_cliente,tip_doc_cliente,nom_cliente,ape_cliente,sexo_cliente,direc_cliente,tel_cliente,email_cliente,estado_cliente,fec_nac_cliente,ciudad_cliente,obs_cliente"
                                + "FROM tb_cliente";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                datosCliente[0] = rslSet.getString("num_doc_cliente");
                datosCliente[1] = rslSet.getString("tip_doc_cliente");
                datosCliente[2] = rslSet.getString("nom_cliente");
                datosCliente[3] = rslSet.getString("ape_cliente");
                datosCliente[4] = rslSet.getString("sexo_cliente");
                datosCliente[5] = rslSet.getString("direc_cliente");
                datosCliente[6] = rslSet.getString("tel_cliente");
                datosCliente[7] = rslSet.getString("email_cliente");
                datosCliente[8] = rslSet.getString("estado_cliente");
                datosCliente[9] = rslSet.getString("fec_nac_cliente");
                datosCliente[10] = rslSet.getString("ciudad_cliente");
                datosCliente[11] = rslSet.getString("obs_cliente");               
                modeloClientes.addRow(datosCliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloClientes;
    }

}
