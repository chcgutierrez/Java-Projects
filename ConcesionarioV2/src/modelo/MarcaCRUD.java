package modelo;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class MarcaCRUD extends ConexionDB {

    //Guardar
    public boolean GuardarMarca(Marca nMarca) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tb_marca(idMarca,nom_marca,estado_marca,obs_marca) VALUES(?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nMarca.getId_marca());
            prStat.setString(2, nMarca.getNom_marca());
            prStat.setString(3, nMarca.getEst_marca());
            prStat.setString(4, nMarca.getObs_marca());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Editar
    public boolean EditarMarca(Marca nMarca) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tb_marca SET nom_marca=?,estado_marca=?,obs_marca=? WHERE idMarca=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nMarca.getNom_marca());
            prStat.setString(2, nMarca.getEst_marca());
            prStat.setString(3, nMarca.getObs_marca());
            prStat.setString(4, nMarca.getId_marca());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar
    public boolean ValidarMarca(Marca nMarca) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "SELECT idMarca,nom_marca,estado_marca,obs_marca FROM tb_marca WHERE idMarca=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nMarca.getId_marca());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                nMarca.setId_marca(rslSet.getString("idMarca"));
                nMarca.setNom_marca(rslSet.getString("nom_marca"));
                nMarca.setEst_marca(rslSet.getString("estado_marca"));
                nMarca.setObs_marca(rslSet.getString("obs_marca"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Ver Todos
    public DefaultTableModel TodosMarca() {
        DefaultTableModel modeloMarca = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloMarca = new DefaultTableModel();
            modeloMarca.addColumn("Código");
            modeloMarca.addColumn("Descripción");
            modeloMarca.addColumn("Estado");
            modeloMarca.addColumn("Observaciones");

            String datosMarca[] = new String[4];

            String instrSQL = "SELECT idMarca,nom_marca,estado_marca,obs_marca FROM tb_marca";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                datosMarca[0] = rslSet.getString("idMarca");
                datosMarca[1] = rslSet.getString("nom_marca");
                datosMarca[2] = rslSet.getString("estado_marca");
                datosMarca[3] = rslSet.getString("obs_marca");
                modeloMarca.addRow(datosMarca);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloMarca;
    }
    
    public DefaultComboBoxModel TodosMarcaCom() {
        DefaultComboBoxModel modeloMarca = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloMarca = new DefaultComboBoxModel();

            String instrSQL = "SELECT nom_marca FROM tb_marca";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                modeloMarca.addElement(rslSet.getString("nom_marca"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloMarca;
    }
    public String codigoMarca(String marca) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        String idMarca = null;
        try {
            String instrSQL = "SELECT idMarca FROM tb_marca WHERE nom_marca=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, marca);
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                idMarca = rslSet.getString("idMarca");
                return idMarca;
            }
            return idMarca;

        } catch (SQLException e) {
            e.printStackTrace();
            return idMarca;
        }
    }
}
