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
public class Pais {
    
    int idPais;
    String descripcion;
    String fec_con;
    String login;
    String obs;

    public Pais() {
    }

    public Pais(int idPais, String descripcion, String fec_con, String login, String obs) {
        this.idPais = idPais;
        this.descripcion = descripcion;
        this.fec_con = fec_con;
        this.login = login;
        this.obs = obs;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFec_con() {
        return fec_con;
    }

    public void setFec_con(String fec_con) {
        this.fec_con = fec_con;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
    
}
