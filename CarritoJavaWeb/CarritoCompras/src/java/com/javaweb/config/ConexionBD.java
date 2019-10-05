/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaweb.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Chris
 */
public class ConexionBD {
    //Variable tipo connection
    Connection nConex;
    String url="jdbc:mysql://localhost:3306/db_carro_compras";
    String user="root";
    String pass="123456";
    //Metodo tipo Connection
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            nConex=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
        }
        
        return nConex;
    }
    
}
