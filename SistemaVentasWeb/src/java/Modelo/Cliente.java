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
public class Cliente {
    
    int idCliente;
    String dniCliente;
    String cliNombre;
    String cliDireccion;
    String cliEstado;

    public Cliente() {
    }

    public Cliente(int idCliente, String dniCliente, String cliNombre, String cliDireccion, String cliEstado) {
        this.idCliente = idCliente;
        this.dniCliente = dniCliente;
        this.cliNombre = cliNombre;
        this.cliDireccion = cliDireccion;
        this.cliEstado = cliEstado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliDireccion() {
        return cliDireccion;
    }

    public void setCliDireccion(String cliDireccion) {
        this.cliDireccion = cliDireccion;
    }

    public String getCliEstado() {
        return cliEstado;
    }

    public void setCliEstado(String cliEstado) {
        this.cliEstado = cliEstado;
    }    
    
}
