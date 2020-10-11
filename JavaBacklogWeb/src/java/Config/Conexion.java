/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Chris
 */
public class Conexion {

    private final String url = "jdbc:mysql://localhost:3306/gestorback?useSSL=false";
    private String user = "root";
    private String pass = "123456";
    PreparedStatement psPrepararSentencia;
    Connection con = null;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Conectado a MySQL correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Imposible acceder a MySQL");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Recurso de acceso inválido");
            System.out.println(e);
        }
    }

    public Connection conectarDB() {
        return con;
    }

    public void desconectarDB() {
        con = null;
        System.out.println("Conexion a MySQL Terminada");

    }

}
