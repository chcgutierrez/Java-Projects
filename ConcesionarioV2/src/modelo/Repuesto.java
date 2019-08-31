/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ccgutierez
 */
public class Repuesto {

    private String id_repuesto;
    private String tipo_repuesto;
    private String nom_repuesto;
    private String desc_repuesto;
    private Integer cant_repuesto;

    public String getId_repuesto() {
        return id_repuesto;
    }

    public void setId_repuesto(String id_repuesto) {
        this.id_repuesto = id_repuesto;
    }

    public String getTipo_repuesto() {
        return tipo_repuesto;
    }

    public void setTipo_repuesto(String tipo_repuesto) {
        this.tipo_repuesto = tipo_repuesto;
    }

    public String getNom_repuesto() {
        return nom_repuesto;
    }

    public void setNom_repuesto(String nom_repuesto) {
        this.nom_repuesto = nom_repuesto;
    }

    public String getDesc_repuesto() {
        return desc_repuesto;
    }

    public void setDesc_repuesto(String desc_repuesto) {
        this.desc_repuesto = desc_repuesto;
    }

    public Integer getCant_repuesto() {
        return cant_repuesto;
    }

    public void setCant_repuesto(Integer cant_repuesto) {
        this.cant_repuesto = cant_repuesto;
    }

}
