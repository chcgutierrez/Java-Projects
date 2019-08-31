/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.*;
import vista.*;
import controlador.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Chris
 */
public class ControlPrincipal implements ActionListener {
    
    private frmPrincipal frmIniciarTodos;
    
    public ControlPrincipal(frmPrincipal frmIniciarTodos) {
        this.frmIniciarTodos = frmIniciarTodos;
        this.frmIniciarTodos.mnuCliente.addActionListener(this);
        this.frmIniciarTodos.mnuVehiculo.addActionListener(this);
        this.frmIniciarTodos.mnuOrdenT.addActionListener(this);
        this.frmIniciarTodos.mnuMarca.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmIniciarTodos.mnuCliente) {
            CargarCliente();
        }
        
        if (e.getSource() == frmIniciarTodos.mnuVehiculo) {
            CargarCliente();
        }
        
        if (e.getSource() == frmIniciarTodos.mnuOrdenT) {
            CargarMtto();
        }
        
        if (e.getSource() == frmIniciarTodos.mnuMarca) {
            CargarMarca();
        }
    }
    
    public void CargarPrincipal() {
        frmIniciarTodos.setVisible(true);
    }
    
    public void CargarMtto() {
        Cliente appCliente = new Cliente();
        Vehiculo appvehiculo = new Vehiculo();
        Marca appMarca = new Marca();
        Tipo appTipo = new Tipo();
        Color appColor = new Color();
        Repuesto appRepuesto = new Repuesto();
        RepuestoCRUD appRepuestoCRUD = new RepuestoCRUD();
        OrdenTrab appOrdenTrab = new OrdenTrab();
        OrdenTrabCRUD appOrdenTrabCRUD = new OrdenTrabCRUD();
        frmMantenimiento appFormaMtto = new frmMantenimiento();
        frmRepuTodo appRepuestoTodos = new frmRepuTodo(appFormaMtto, true);
        ControlOrdenTrab appControlOrdenTrab = new ControlOrdenTrab(appCliente,
                appvehiculo,
                appMarca,
                appTipo,
                appColor,
                appRepuesto,
                appRepuestoCRUD,
                appOrdenTrab,
                appOrdenTrabCRUD,
                appFormaMtto,
                appRepuestoTodos);
        appControlOrdenTrab.IniciarMtto();
        appFormaMtto.setVisible(true);
    }
    
    public void CargarCliente() {
        Cliente appCliente = new Cliente();
        Vehiculo appvehiculo = new Vehiculo();
        Marca appMarca = new Marca();
        Tipo appTipo = new Tipo();
        Color appColor = new Color();
        Repuesto appRepuesto = new Repuesto();
        RepuestoCRUD appRepuestoCRUD = new RepuestoCRUD();
        OrdenTrab appOrdenTrab = new OrdenTrab();
        OrdenTrabCRUD appOrdenTrabCRUD = new OrdenTrabCRUD();
        frmMantenimiento appFormaMtto = new frmMantenimiento();
        frmRepuTodo appRepuestoTodos = new frmRepuTodo(appFormaMtto, true);
        ControlOrdenTrab appControlOrdenTrab = new ControlOrdenTrab(appCliente,
                appvehiculo,
                appMarca,
                appTipo,
                appColor,
                appRepuesto,
                appRepuestoCRUD,
                appOrdenTrab,
                appOrdenTrabCRUD,
                appFormaMtto,
                appRepuestoTodos);
        appControlOrdenTrab.IniciarMtto();
        appFormaMtto.setVisible(true);
    }
    
    public void CargarRepuesto() {
        Repuesto appRepuesto = new Repuesto();
        RepuestoCRUD appRepuestoCRUD = new RepuestoCRUD();
        frmRepuesto appFormaRepuesto = new frmRepuesto();
        frmRepuTodo appRepuestoTodos = new frmRepuTodo(appFormaRepuesto, true);
        ControlRepuesto appControlRepuesto = new ControlRepuesto(appRepuesto, appRepuestoCRUD, appFormaRepuesto, appRepuestoTodos);
        appControlRepuesto.IniciarRepuesto();
        appFormaRepuesto.setVisible(true);
    }
    
    public void CargarCiudad() {
        Ciudad appCiudad = new Ciudad();
        CiudadCRUD appCiudadCRUD = new CiudadCRUD();
        frmCiudad appFormaCiudad = new frmCiudad();
        frmCiudadTodo appCiudadTodos = new frmCiudadTodo(appFormaCiudad, true);
        ControlCiudad appControlCiudad = new ControlCiudad(appCiudad, appCiudadCRUD, appFormaCiudad, appCiudadTodos);
        appControlCiudad.IniciarCiudad();
        appFormaCiudad.setVisible(true);
    }
    
    public void CargarTipo() {
        Tipo appTipo = new Tipo();
        TipoCRUD appTipoCRUD = new TipoCRUD();
        frmTipo appFormaTipo = new frmTipo();
        frmTipoTodo appTipoTodos = new frmTipoTodo(appFormaTipo, true);
        ControlTipo appControlTipo = new ControlTipo(appTipo, appTipoCRUD, appFormaTipo, appTipoTodos);
        appControlTipo.IniciarTipo();
        appFormaTipo.setVisible(true);
    }
    
    public void CargarMarca() {
        Marca appMarca = new Marca();
        MarcaCRUD appMarcaCRUD = new MarcaCRUD();
        frmMarca appFormaMarca = new frmMarca();
        frmMarcaTodo appFormaTodos = new frmMarcaTodo(appFormaMarca, true);
        ControlMarca appControlMarca = new ControlMarca(appMarca, appMarcaCRUD, appFormaMarca, appFormaTodos);
        appControlMarca.IniciarMarca();
        appFormaMarca.setVisible(true);
    }
    
    public void CargarColor() {
        Color appColor = new Color();
        ColorCRUD appColorCRUD = new ColorCRUD();
        frmColor appFormaColor = new frmColor();
        frmColorTodo appColorTodos = new frmColorTodo(appFormaColor, true);
        ControlColor appControlColor = new ControlColor(appColor, appColorCRUD, appFormaColor, appColorTodos);
        appControlColor.IniciarColor();
        appFormaColor.setVisible(true);
    }
    
}
