package modelo;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TipoCRUD extends ConexionDB {

    //Guardar
    public boolean GuardarTipo(Tipo nTipo) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tb_tipo(idTipo,nom_tipo,estado_tipo,obs_tipo) VALUES(?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nTipo.getId_tipo());
            prStat.setString(2, nTipo.getNom_tipo());
            prStat.setString(3, nTipo.getEst_tipo());
            prStat.setString(4, nTipo.getObs_tipo());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Editar
    public boolean EditarTipo(Tipo nTipo) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tb_tipo SET nom_tipo=?,estado_tipo=?,obs_tipo=? WHERE idTipo=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nTipo.getNom_tipo());
            prStat.setString(2, nTipo.getEst_tipo());
            prStat.setString(3, nTipo.getObs_tipo());
            prStat.setString(4, nTipo.getId_tipo());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar
    public boolean ValidarTipo(Tipo nTipo) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "SELECT idTipo,nom_tipo,estado_tipo,obs_tipo FROM tb_tipo WHERE idTipo=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nTipo.getId_tipo());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                nTipo.setId_tipo(rslSet.getString("idTipo"));
                nTipo.setNom_tipo(rslSet.getString("nom_tipo"));
                nTipo.setEst_tipo(rslSet.getString("estado_tipo"));
                nTipo.setObs_tipo(rslSet.getString("obs_tipo"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Ver Todos
    public DefaultTableModel TodosTipo() {
        DefaultTableModel modeloTipo = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloTipo = new DefaultTableModel();
            modeloTipo.addColumn("Código");
            modeloTipo.addColumn("Descripción");
            modeloTipo.addColumn("Estado");
            modeloTipo.addColumn("Observaciones");

            String datosTipo[] = new String[4];

            String instrSQL = "SELECT idTipo,nom_tipo,estado_tipo,obs_tipo FROM tb_tipo";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                datosTipo[0] = rslSet.getString("idTipo");
                datosTipo[1] = rslSet.getString("nom_tipo");
                datosTipo[2] = rslSet.getString("estado_tipo");
                datosTipo[3] = rslSet.getString("obs_tipo");
                modeloTipo.addRow(datosTipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloTipo;
    }
    
    public DefaultComboBoxModel TodosTipoCom() {
        DefaultComboBoxModel modeloTipo = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloTipo = new DefaultComboBoxModel();

            String instrSQL = "SELECT nom_tipo FROM tb_tipo";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                modeloTipo.addElement(rslSet.getString("nom_tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloTipo;
    }
    
    public String codigoTipo(String tipo) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        String idTipo = null;
        try {
            String instrSQL = "SELECT idTipo FROM tb_tipo WHERE nom_tipo=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, tipo);
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                idTipo = rslSet.getString("idTipo");
                return idTipo;
            }
            return idTipo;

        } catch (SQLException e) {
            e.printStackTrace();
            return idTipo;
        }
    }

}
