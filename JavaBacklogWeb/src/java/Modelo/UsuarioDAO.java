/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Chris
 */
public class UsuarioDAO {

    //Variables
    Conexion strConex = new Conexion();
    Connection nCon;
    PreparedStatement senSQL;
    ResultSet rsSQL;
    int iRta;

    public Usuario Validar(String strUsuario, String strPassword) {

        Usuario objUsuario = new Usuario(); //Instancio la clase Pais para crear un nuevo objeto
        String strSQL = "SELECT idUsuario, nombre, email, password, idTipoUsuario FROM usuario WHERE email = ? AND password = ?";
        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, strUsuario);
            senSQL.setString(2, strPassword);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                objUsuario.setIdUsuario(rsSQL.getInt("idUsuario"));
                objUsuario.setUsuNombre(rsSQL.getString("nombre"));
                objUsuario.setUsuEmail(rsSQL.getString("email"));
                objUsuario.setUsuPassword(rsSQL.getString("password"));
                objUsuario.setUsuTipo(rsSQL.getInt("idTipoUsuario"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return objUsuario;
    }

    //Operaciones CRUD
    public List MostrarUsuario() {

        String strSQL = "SELECT idUsuario, nombre, email, password, idTipoUsuario FROM usuario";
        List<Usuario> lstUsuario = new ArrayList<>();

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                Usuario objUsuario = new Usuario();
                objUsuario.setIdUsuario(rsSQL.getInt("idUsuario"));
                objUsuario.setUsuNombre(rsSQL.getString("nombre"));
                objUsuario.setUsuEmail(rsSQL.getString("email"));
                objUsuario.setUsuPassword(rsSQL.getString("password"));
                objUsuario.setUsuTipo(rsSQL.getInt("idTipoUsuario"));
                lstUsuario.add(objUsuario);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return lstUsuario;
    }

    public int Guardar(Usuario oUsuario) {

        String strSQL = "INSERT INTO Usuario (nombre, email, password, idTipoUsuario) VALUES (?, ?, ?, ?)";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oUsuario.getUsuNombre());
            senSQL.setString(2, oUsuario.getUsuEmail());
            senSQL.setString(3, oUsuario.getUsuPassword());
            senSQL.setInt(4, oUsuario.getUsuTipo());
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return iRta;
    }

    public Usuario MostrarID(int oID) {

        Usuario objUsuario = new Usuario(); //Instancio la clase Pais para crear un nuevo objeto

        String strSQL = "SELECT idUsuario, nombre, email, password, idTipoUsuario FROM usuario WHERE idUsuario = " + oID;

        try {

            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                objUsuario.setIdUsuario(rsSQL.getInt("idUsuario"));
                objUsuario.setUsuNombre(rsSQL.getString("nombre"));
                objUsuario.setUsuEmail(rsSQL.getString("email"));
                objUsuario.setUsuPassword(rsSQL.getString("password"));
                objUsuario.setUsuTipo(rsSQL.getInt("idTipoUsuario"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return objUsuario;
    }
    
    public int Actualizar(Usuario oUsuario) {

        String strSQL = "UPDATE usuario SET nombre = ?, email = ?, password = ?, idTipoUsuario = ? WHERE idUsuario = ?";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oUsuario.getUsuNombre());
            senSQL.setString(2, oUsuario.getUsuEmail());
            senSQL.setString(3, oUsuario.getUsuPassword());
            senSQL.setInt(4, oUsuario.getUsuTipo());
            senSQL.setInt(5, oUsuario.getIdUsuario());
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return iRta;
    }
    
    public void borrar(int oID) {

        String strSQL = "DELETE FROM usuario WHERE idUsuario = " + oID;

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
