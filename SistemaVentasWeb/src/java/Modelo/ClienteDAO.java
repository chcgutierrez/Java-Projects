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
public class ClienteDAO {

    //Variables
    Conexion strConex = new Conexion();
    Connection nCon;
    PreparedStatement senSQL;
    ResultSet rsSQL;
    int iRta;

    public Cliente validar(String strClienteDNI) {

        Cliente objCliente = new Cliente();
        String strSQL = "SELECT idCliente, Dni, Nombres, Direccion, Estado FROM Cliente WHERE Dni = ?";
        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, strClienteDNI);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                objCliente.setIdCliente(rsSQL.getInt("idCliente"));
                objCliente.setDniCliente(rsSQL.getString("Dni")); //Instancio la clase Pais para crear un nuevo objeto
                objCliente.setCliNombre(rsSQL.getString("Nombres"));
                objCliente.setCliDireccion(rsSQL.getString("Direccion"));
                objCliente.setCliEstado(rsSQL.getString("Estado"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return objCliente;
    }

    //Operaciones CRUD
    public List listar() {

        String strSQL = "SELECT idCliente, Dni, Nombres, Direccion, Estado FROM Cliente";
        List<Cliente> lstCliente = new ArrayList<>();

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                Cliente objCliente = new Cliente(); //Instancio la clase ClienteF para crear un nuevo objeto
                objCliente.setIdCliente(rsSQL.getInt("idCliente"));
                objCliente.setDniCliente(rsSQL.getString("Dni")); 
                objCliente.setCliNombre(rsSQL.getString("Nombres"));
                objCliente.setCliDireccion(rsSQL.getString("Direccion"));
                objCliente.setCliEstado(rsSQL.getString("Estado"));
                lstCliente.add(objCliente);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return lstCliente;
    }

    public int agregar(Cliente oCliente) {

        String strSQL = "INSERT INTO Cliente (Dni, Nombres, Direccion, Estado) VALUES (?, ?, ?, ?)";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oCliente.getDniCliente());
            senSQL.setString(2, oCliente.getCliNombre());
            senSQL.setString(3, oCliente.getCliDireccion());
            senSQL.setString(4, oCliente.getCliEstado());
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return iRta;
    }

    public Cliente listarID(int oID) {

        Cliente objCliente = new Cliente(); //Instancio la clase Pais para crear un nuevo objeto

        String strSQL = "SELECT idCliente, Dni, Nombres, Direccion, Estado FROM Cliente WHERE IdCliente = " + oID;

        try {

            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            rsSQL = senSQL.executeQuery();
            while (rsSQL.next()) {
                objCliente.setIdCliente(rsSQL.getInt("idCliente"));
                objCliente.setDniCliente(rsSQL.getString("Dni"));
                objCliente.setCliNombre(rsSQL.getString("Nombres"));
                objCliente.setCliDireccion(rsSQL.getString("Direccion"));
                objCliente.setCliEstado(rsSQL.getString("Estado"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return objCliente;
    }

    public int actualizar(Cliente oCliente) {

        String strSQL = "UPDATE Cliente SET Dni = ?, Nombres = ?, Direccion = ?, Estado = ? WHERE IdCliente = ?";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oCliente.getDniCliente());
            senSQL.setString(2, oCliente.getCliNombre());
            senSQL.setString(3, oCliente.getCliDireccion());
            senSQL.setString(4, oCliente.getCliEstado());
            senSQL.setInt(5, oCliente.getIdCliente());
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return iRta;

    }

    public void borrar(int oID) {

        String strSQL = "DELETE FROM Cliente WHERE IdCliente = " + oID;

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
