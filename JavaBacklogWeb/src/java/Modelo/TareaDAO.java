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
import javax.swing.JOptionPane;

/**
 *
 * @author Chris
 */
public class TareaDAO {

    //Variables
    Conexion strConex = new Conexion();
    Connection nCon;
    PreparedStatement senSQL;
    ResultSet rsSQL;
    int iRta;

    public int Guardar(Tarea oTarea) {

        String strSQL = "INSERT INTO tarea (titulo,detalle,fecha_abre,fecha_estima_cierre,fecha_edicion,idCategoria,idPrioridad,idEstado,idUsuarioCrea,idUsuarioResuelve,idProyecto)\n"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            nCon = strConex.conectarDB();
            senSQL = nCon.prepareStatement(strSQL);
            senSQL.setString(1, oTarea.getTitulo());
            senSQL.setString(2, oTarea.getDetalle());
            senSQL.setString(3, oTarea.getFecha_abre());
            senSQL.setString(4, oTarea.getFecha_estima_cierre());
            senSQL.setString(5, oTarea.getFecha_edicion());
            senSQL.setInt(6, oTarea.getIdCategoria());
            senSQL.setInt(7, oTarea.getIdPrioridad());
            senSQL.setInt(8, oTarea.getIdEstado());
            senSQL.setInt(9, oTarea.getIdUsuarioCrea());
            senSQL.setInt(10, oTarea.getIdUsuarioResuelve());
            senSQL.setInt(11, oTarea.getIdProyecto());
            senSQL.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return iRta;
    }

}
