/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Chris
 */
public class Conexion {
    
    public String DB="hoteljava";
    public String URL="jdbc:mysql://localhost/" + DB +"?useSSL=false";
    public String user="root";
    public String pass="123456";

    public Conexion() {
    }
    
    public Connection ConectarBD(){        
        Connection link=null;        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link=DriverManager.getConnection(this.URL, this.user, this.pass);
            if(link!=null){
                System.out.println("Conectado a MySQL.");  
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }
    
}
