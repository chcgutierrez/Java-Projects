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
public class OrdenRepBD {

    private String codOrdenRep;
    private String codOrdenTrab;
    private String codRepuesto;
    private String valCantidad;

    public String getCodOrdenRep() {
        return codOrdenRep;
    }

    public void setCodOrdenRep(String codOrdenRep) {
        this.codOrdenRep = codOrdenRep;
    }

    public String getCodOrdenTrab() {
        return codOrdenTrab;
    }

    public void setCodOrdenTrab(String codOrdenTrab) {
        this.codOrdenTrab = codOrdenTrab;
    }

    public String getCodRepuesto() {
        return codRepuesto;
    }

    public void setCodRepuesto(String codRepuesto) {
        this.codRepuesto = codRepuesto;
    }

    public String getValCantidad() {
        return valCantidad;
    }

    public void setValCantidad(String valCantidad) {
        this.valCantidad = valCantidad;
    }

}
