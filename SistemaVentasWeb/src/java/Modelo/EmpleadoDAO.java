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
public class EmpleadoDAO {

    //Variables
    Conexion strConex = new Conexion();
    Connection nCon;
    PreparedStatement senSQL;
    ResultSet rsSQL;
    int iRta;

    public Empleado validar(String strUser, String strDNI) {

        Empleado objEmpleado = new Empleado();
        String strSQL = "SELECT idEmpleado, Dni, Nombres, Telefono, Estado, User FROM Empleado WHERE User = ? AND Dni = ?";
        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, strUser);
            senSQL.setString(2, strDNI);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                objEmpleado.setId(rsSQL.getInt("idEmpleado"));
                objEmpleado.setUser(rsSQL.getString("User")); //Instancio la clase Pais para crear un nuevo objeto
                objEmpleado.setDni(rsSQL.getString("Dni"));
                objEmpleado.setEmpNombre(rsSQL.getString("Nombres"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return objEmpleado;
    }

    //Operaciones CRUD
    public List listar() {

        String strSQL = "SELECT idEmpleado, Dni, Nombres, Telefono, Estado, User FROM Empleado";
        List<Empleado> lstEmpleado = new ArrayList<>();

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                Empleado objEmpleado = new Empleado(); //Instancio la clase Empleado para crear un nuevo objeto
                objEmpleado.setId(rsSQL.getInt("idEmpleado"));
                objEmpleado.setDni(rsSQL.getString("Dni"));
                objEmpleado.setEmpNombre(rsSQL.getString("Nombres"));
                objEmpleado.setEmpTelefono(rsSQL.getString("Telefono"));
                objEmpleado.setEstado(rsSQL.getString("Estado"));
                objEmpleado.setUser(rsSQL.getString("User"));
                lstEmpleado.add(objEmpleado);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return lstEmpleado;
    }

    public int agregar(Empleado oEmpleado) {

        String strSQL = "INSERT INTO Empleado (Dni, Nombres, Telefono, Estado, User) VALUES (?, ?, ?, ?, ?)";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oEmpleado.getDni());
            senSQL.setString(2, oEmpleado.getEmpNombre());
            senSQL.setString(3, oEmpleado.getEmpTelefono());
            senSQL.setString(4, oEmpleado.getEstado());
            senSQL.setString(5, oEmpleado.getUser());
            senSQL.executeUpdate();
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
        }

        return iRta;
    }

    public Empleado listarID(int oID) {

        Empleado objEmpleado = new Empleado(); //Instancio la clase Pais para crear un nuevo objeto

        String strSQL = "SELECT idEmpleado, Dni, Nombres, Telefono, Estado, User FROM Empleado WHERE IdEmpleado = " + oID;

        try {

            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                objEmpleado.setId(rsSQL.getInt("idEmpleado"));
                objEmpleado.setDni(rsSQL.getString("Dni"));
                objEmpleado.setEmpNombre(rsSQL.getString("Nombres"));
                objEmpleado.setEmpTelefono(rsSQL.getString("Telefono"));
                objEmpleado.setEstado(rsSQL.getString("Estado"));
                objEmpleado.setUser(rsSQL.getString("User"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return objEmpleado;
    }

    public int actualizar(Empleado oEmpleado) {

        String strSQL = "UPDATE Empleado SET Dni = ?, Nombres = ?, Telefono = ?, Estado = ?, User = ? WHERE IdEmpleado = ?";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oEmpleado.getDni());
            senSQL.setString(2, oEmpleado.getEmpNombre());
            senSQL.setString(3, oEmpleado.getEmpTelefono());
            senSQL.setString(4, oEmpleado.getEstado());
            senSQL.setString(5, oEmpleado.getUser());
            senSQL.setInt(6, oEmpleado.getId());
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return iRta;

    }

    public void borrar(int oID) {

        String strSQL = "DELETE FROM Empleado WHERE IdEmpleado = " + oID;

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
