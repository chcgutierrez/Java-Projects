/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Chris
 */
public class MarcaEnt {

    private String cod_marca;
    private String nom_marca;
    private String estado_marca;
    private String fec_act;
    private String obs_marca;

    public String getCod_marca() {
        return cod_marca;
    }

    public void setCod_marca(String cod_marca) {
        this.cod_marca = cod_marca;
    }

    public String getNom_marca() {
        return nom_marca;
    }

    public void setNom_marca(String nom_marca) {
        this.nom_marca = nom_marca;
    }

    public String getEstado_marca() {
        return estado_marca;
    }

    public void setEstado_marca(String estado_marca) {
        this.estado_marca = estado_marca;
    }

    public String getFec_act() {
        return fec_act;
    }

    public void setFec_act(String fec_act) {
        this.fec_act = fec_act;
    }

    public String getObs_marca() {
        return obs_marca;
    }

    public void setObs_marca(String obs_marca) {
        this.obs_marca = obs_marca;
    }

}
