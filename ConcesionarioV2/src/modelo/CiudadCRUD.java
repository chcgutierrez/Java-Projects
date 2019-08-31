package modelo;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CiudadCRUD extends ConexionDB {

    //Guardar
    public boolean GuardarCiudad(Ciudad nCiudad) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "INSERT INTO tb_ciudad(idCiudad,nom_ciudad,estado_ciudad,obs_ciudad) VALUES(?,?,?,?)";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nCiudad.getId_ciudad());
            prStat.setString(2, nCiudad.getNom_ciudad());
            prStat.setString(3, nCiudad.getEst_ciudad());
            prStat.setString(4, nCiudad.getObs_ciudad());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Editar
    public boolean EditarCiudad(Ciudad nCiudad) {

        PreparedStatement prStat = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "UPDATE tb_ciudad SET nom_ciudad=?,estado_ciudad=?,obs_ciudad=? WHERE idCiudad=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nCiudad.getNom_ciudad());
            prStat.setString(2, nCiudad.getEst_ciudad());
            prStat.setString(3, nCiudad.getObs_ciudad());
            prStat.setString(4, nCiudad.getId_ciudad());
            prStat.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validar
    public boolean ValidarCiudad(Ciudad nCiudad) {

        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            String instrSQL = "SELECT idCiudad,nom_ciudad,estado_ciudad,obs_ciudad FROM tb_ciudad WHERE idCiudad=?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, nCiudad.getId_ciudad());
            rslSet = prStat.executeQuery();
            if (rslSet.next()) {
                nCiudad.setId_ciudad(rslSet.getString("idCiudad"));
                nCiudad.setNom_ciudad(rslSet.getString("nom_ciudad"));
                nCiudad.setEst_ciudad(rslSet.getString("estado_ciudad"));
                nCiudad.setObs_ciudad(rslSet.getString("obs_ciudad"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Ver Todos
    public DefaultTableModel TodosCiudad() {
        DefaultTableModel modeloCiudad = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloCiudad = new DefaultTableModel();
            modeloCiudad.addColumn("Código");
            modeloCiudad.addColumn("Descripción");
            modeloCiudad.addColumn("Estado");
            modeloCiudad.addColumn("Observaciones");

            String datosCiudad[] = new String[4];

            String instrSQL = "SELECT idCiudad,nom_ciudad,estado_ciudad,obs_ciudad FROM tb_ciudad";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                datosCiudad[0] = rslSet.getString("idCiudad");
                datosCiudad[1] = rslSet.getString("nom_ciudad");
                datosCiudad[2] = rslSet.getString("estado_ciudad");
                datosCiudad[3] = rslSet.getString("obs_ciudad");
                modeloCiudad.addRow(datosCiudad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloCiudad;
    }
    
    public DefaultComboBoxModel TodosCiudadCom() {
        DefaultComboBoxModel modeloCiudad = null;
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        Connection nConex = conectarDB();
        try {
            modeloCiudad = new DefaultComboBoxModel();

            String instrSQL = "SELECT nom_ciudad FROM tb_ciudad";
            prStat = nConex.prepareStatement(instrSQL);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                //datosCiudad[0] = rslSet.getString("nom_ciudad");
                modeloCiudad.addElement(rslSet.getString("nom_ciudad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeloCiudad;
    }
    
    public String codigoCiudad(String ciudad) {
        PreparedStatement prStat = null;
        ResultSet rslSet = null;
        String codigo = null;
        Connection nConex = conectarDB();
        try {
            
            String instrSQL = "SELECT idCiudad FROM tb_ciudad WHERE nom_ciudad = ?";
            prStat = nConex.prepareStatement(instrSQL);
            prStat.setString(1, ciudad);
            rslSet = prStat.executeQuery();
            while (rslSet.next()) {
                codigo = rslSet.getString("idCiudad");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigo;
    }
}
