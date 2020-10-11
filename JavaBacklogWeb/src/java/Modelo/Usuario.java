/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Chris
 */
public class Usuario {
    
    int idUsuario;
    String usuNombre;
    String usuEmail;
    String usuPassword;
    int usuTipo;

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuNombre, String usuEmail, String usuPassword, int usuTipo) {
        this.idUsuario = idUsuario;
        this.usuNombre = usuNombre;
        this.usuEmail = usuEmail;
        this.usuPassword = usuPassword;
        this.usuTipo = usuTipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public int getUsuTipo() {
        return usuTipo;
    }

    public void setUsuTipo(int usuTipo) {
        this.usuTipo = usuTipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuNombre=" + usuNombre + ", usuEmail=" + usuEmail + ", usuPassword=" + usuPassword + ", usuTipo=" + usuTipo + '}';
    }  
    
}
