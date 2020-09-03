/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris
 */
public class PaisDAO implements PaisCRUD {

    //Variables
    PreparedStatement senSQL;
    ResultSet rsSQL;
    Connection conBD;
    ConexBD conexBD = new ConexBD();

    @Override
    public List BuscarPais() {

        List<Pais> datosPais = new ArrayList<>();
        String strSQL = "SELECT * FROM maestrapais";

        try {
            conBD = conexBD.conectarDB();
            senSQL = conBD.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                Pais oPais = new Pais(); //Instancio la clase Pais para crear un nuevo objeto
                oPais.setIdPais(rsSQL.getInt("idMaestraPais"));
                oPais.setDescripcion(rsSQL.getString("Descripcion"));
                oPais.setFec_con(rsSQL.getString("fec_con"));
                oPais.setLogin(rsSQL.getString("login"));
                oPais.setObs(rsSQL.getString("obs"));
                datosPais.add(oPais); //lleno ar ArrayList con los valores del objeto
            }
        } catch (Exception e) {
        }
        
        return datosPais;
    }

    @Override
    public Pais BuscarPaisID(int idPais) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pais BuscarPaisNombre(String nomPais) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String crearPais(String strDescripcion, String strFecReg, String strLogin, String strObs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editarPais(Integer idPais, String strDescripcion, String strFecReg, String strLogin, String strObs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
