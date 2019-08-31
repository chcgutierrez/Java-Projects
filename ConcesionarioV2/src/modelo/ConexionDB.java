package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionDB {

    private final String url = "jdbc:mysql://localhost:3306/concesionario2?useSSL=false";
    private String user = "root";
    private String pass = "";
    PreparedStatement psPrepararSentencia;
    Connection con = null;

    public ConexionDB() {
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
        System.out.println("Conexion Terminada");

    }

}
