package modelo;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class RepuestoCRUD extends ConexionDB {

    //Guardar
    public boolean GuardarRepuesto(Repuesto nRepuesto) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tbl_repuestos(idRepuestos,tipo,nombre,descripcion,cantidad) VALUES(?,?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nRepuesto.getId_repuesto());
            prStat.setString(2, nRepuesto.getTipo_repuesto());
            prStat.setString(3, nRepuesto.getNom_repuesto());
            prStat.setString(4, nRepuesto.getDesc_repuesto());
            prStat.setInt(5, nRepuesto.getCant_repuesto());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Editar
    public boolean EditarRepuesto(Repuesto nRepuesto) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tbl_repuestos SET tipo=?,nombre=?,descripcion=?, cantidad=? WHERE idRepuestos=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nRepuesto.getTipo_repuesto());
            prStat.setString(2, nRepuesto.getNom_repuesto());
            prStat.setString(3, nRepuesto.getDesc_repuesto());
            prStat.setInt(4, nRepuesto.getCant_repuesto());
            prStat.setString(5, nRepuesto.getId_repuesto());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar_1
    public boolean ValidarRepuesto(Repuesto nRepuesto) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "SELECT idRepuestos,tipo,nombre,descripcion,cantidad FROM tbl_repuestos WHERE idRepuestos=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nRepuesto.getId_repuesto());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                nRepuesto.setId_repuesto(rslSet.getString("idRepuestos"));
                nRepuesto.setTipo_repuesto(rslSet.getString("tipo"));
                nRepuesto.setNom_repuesto(rslSet.getString("nombre"));
                nRepuesto.setDesc_repuesto(rslSet.getString("descripcion"));
                nRepuesto.setCant_repuesto(rslSet.getInt("cantidad"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Ver Todos
    public DefaultTableModel TodosRepuesto() {
        DefaultTableModel modeloRepuesto = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloRepuesto = new DefaultTableModel();
            modeloRepuesto.addColumn("Código");
            modeloRepuesto.addColumn("Tipo");
            modeloRepuesto.addColumn("Nombre");
            modeloRepuesto.addColumn("Descripción");
            modeloRepuesto.addColumn("Stock");

            String datosRepuesto[] = new String[5];

            String instrSQL = "SELECT idRepuestos,tipo,nombre,descripcion,cantidad FROM tbl_repuestos";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                datosRepuesto[0] = rslSet.getString("idRepuestos");
                datosRepuesto[1] = rslSet.getString("tipo");
                datosRepuesto[2] = rslSet.getString("nombre");
                datosRepuesto[3] = rslSet.getString("descripcion");
                datosRepuesto[4] = rslSet.getString("cantidad");
                modeloRepuesto.addRow(datosRepuesto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloRepuesto;
    }
    
    public DefaultComboBoxModel TodosRepuestoCom() {
        DefaultComboBoxModel modeloRepuesto = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloRepuesto = new DefaultComboBoxModel();

            String datosRepuesto[] = new String[1];

            String instrSQL = "SELECT nom_repuesto FROM tb_repuesto";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                //datosRepuesto[0] = rslSet.getString("nom_repuesto");
                modeloRepuesto.addElement(rslSet.getString("nom_repuesto"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloRepuesto;
    }
    
    public String codigoRepuesto(String repuesto) {
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        String codigo = null;
        Connection nConex = conectarDB();
        try {
            
            String instrSQL = "SELECT idRepuesto FROM tb_repuesto WHERE nom_repuesto = ?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, repuesto);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                codigo = rslSet.getString("idRepuesto");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigo;
    }
    
}
