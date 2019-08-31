package modelo;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ColorCRUD extends ConexionDB {

    //Guardar
    public boolean GuardarColor(Color nColor) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tb_color(idColor,nom_color,estado_color,obs_color) VALUES(?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nColor.getId_color());
            prStat.setString(2, nColor.getNom_color());
            prStat.setString(3, nColor.getEst_color());
            prStat.setString(4, nColor.getObs_color());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Editar
    public boolean EditarColor(Color nColor) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tb_color SET nom_color=?,estado_color=?,obs_color=? WHERE idColor=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nColor.getId_color());
            prStat.setString(2, nColor.getNom_color());
            prStat.setString(3, nColor.getEst_color());
            prStat.setString(4, nColor.getObs_color());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar
    public boolean ValidarColor(Color nColor) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "SELECT idColor,nom_color,estado_color,obs_color FROM tb_color WHERE idColor=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nColor.getId_color());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                nColor.setId_color(rslSet.getString("idColor"));
                nColor.setNom_color(rslSet.getString("nom_color"));
                nColor.setEst_color(rslSet.getString("estado_color"));
                nColor.setObs_color(rslSet.getString("obs_color"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Ver Todos
    public DefaultTableModel TodosColor() {
        DefaultTableModel modeloColor = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloColor = new DefaultTableModel();
            modeloColor.addColumn("Código");
            modeloColor.addColumn("Descripción");
            modeloColor.addColumn("Estado");
            modeloColor.addColumn("Observaciones");

            String datosColor[] = new String[4];

            String instrSQL = "SELECT idColor,nom_color,estado_color,obs_color FROM tb_color";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                datosColor[0] = rslSet.getString("idColor");
                datosColor[1] = rslSet.getString("nom_color");
                datosColor[2] = rslSet.getString("estado_color");
                datosColor[3] = rslSet.getString("obs_color");
                modeloColor.addRow(datosColor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloColor;
    }
    
    public DefaultComboBoxModel TodosColorCom() {
        DefaultComboBoxModel modeloColor = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloColor = new DefaultComboBoxModel();
            String instrSQL = "SELECT nom_color FROM tb_color";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                modeloColor.addElement(rslSet.getString("nom_color"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloColor;
    }

    public String codigoColor(String color) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        String idColor = null;
        try {
            String instrSQL = "SELECT idColor FROM tb_color WHERE nom_color=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, color);
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                idColor = rslSet.getString("idColor");
                return idColor;
            }
            return idColor;

        } catch (SQLException e) {
            e.printStackTrace();
            return idColor;
        }
    }
}
