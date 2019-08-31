/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *
 * @author ccgutierez
 */
public class ControlOrdenTrab implements ActionListener{

    private Cliente rCliente;
    private Vehiculo rVehiculo;
    private Marca rMarca;
    private Tipo rTipo;
    private Color rColor;
    private Repuesto rRepuesto;
    private RepuestoCRUD rRepuestoCRUD;
    private OrdenTrab rOrdenTrab;
    private OrdenTrabCRUD rOrdenTrabCRUD;
    private frmMantenimiento frmMttoControl;
    private frmRepuTodo frmRepuestoTodos;

    public ControlOrdenTrab(Cliente rCliente, Vehiculo rVehiculo, Marca rMarca,
            Tipo rTipo, Color rColor, Repuesto rRepuesto, RepuestoCRUD rRepuestoCRUD, OrdenTrab rOrdenTrab,
            OrdenTrabCRUD rOrdenTrabCRUD, frmMantenimiento frmMttoControl,
            frmRepuTodo frmRepuestoTodos) {
        this.rCliente = rCliente;
        this.rVehiculo = rVehiculo;
        this.rMarca = rMarca;
        this.rTipo = rTipo;
        this.rColor = rColor;
        this.rRepuesto = rRepuesto;
        this.rRepuestoCRUD = rRepuestoCRUD;
        this.rOrdenTrab = rOrdenTrab;
        this.rOrdenTrabCRUD = rOrdenTrabCRUD;
        this.frmMttoControl = frmMttoControl;
        this.frmMttoControl.btnValidarOrden.addActionListener(this);
        this.frmMttoControl.btnBuscarRepuesto.addActionListener(this);
        this.frmMttoControl.txtCodRepuesto.addActionListener(this);
        this.frmRepuestoTodos = frmRepuestoTodos;
        frmRepuestoTodos.btnCerrarBusqRepu.addActionListener(this);
    }
    
    public void IniciarRepuestoTodos() {
        frmRepuestoTodos.setResizable(false);
        frmRepuestoTodos.setLocationRelativeTo(frmMttoControl);
        frmRepuestoTodos.setTitle("Buscar Repuestos");
        frmRepuestoTodos.setResizable(false);
        frmRepuestoTodos.setVisible(true);
    }

    public void IniciarMtto() {
        frmMttoControl.setResizable(false);
        frmMttoControl.setLocationRelativeTo(null);
        frmMttoControl.setTitle("Orden de Trabajo");
        frmMttoControl.lblOrdenT.setVisible(false);
        frmMttoControl.txtOrdenT.setVisible(false);
        frmMttoControl.tblOrdenRepuestos.setModel(OrdenRepuesto());
        frmMttoControl.setVisible(true);
    }

    //Modelo Tabla
    public DefaultTableModel OrdenRepuesto() {
        DefaultTableModel cargaRep = null;
        cargaRep = new DefaultTableModel();
        cargaRep.addColumn("Código");
        cargaRep.addColumn("Descripción");
        cargaRep.addColumn("Cantidad");
        cargaRep.addColumn("Valor");
        return cargaRep;
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        
        //Ver Orden Repuestos
//        if (){
//            if (rRepuestoCRUD.ValidarRepuesto(rRepuesto)) {
//                //frmMttoControl.txtCodRepuesto.setText(String.valueOf(rRepuesto.getId_repuesto()));
//                frmMttoControl.txtNomRepuesto.setText(String.valueOf(rRepuesto.getNom_repuesto()));
//                frmMttoControl.txtTipoRepuesto.setText(String.valueOf(rRepuesto.getTipo_repuesto()));
//                frmMttoControl.txtStockRepuesto.setText(String.valueOf(rRepuesto.getCant_repuesto()));
//            }else{
//                
//            }
//        }

        //Ver Todos los Repuestos
        if (e.getSource() == frmMttoControl.btnBuscarRepuesto) {
            frmRepuestoTodos.tblRepu.setModel(rRepuestoCRUD.TodosRepuesto());
            IniciarRepuestoTodos();
        }

        //Cerrar Todos los Repuestos
        if (e.getSource() == frmRepuestoTodos.btnCerrarBusqRepu) {
            frmRepuestoTodos.dispose();
        }     

        //Validar Placa - Cliente
        if (e.getSource() == frmMttoControl.btnValidarOrden) {
            rVehiculo.setPlaca(frmMttoControl.txtPlacaVeh.getText());
            Icon validar = new ImageIcon(getClass().getResource("/Recursos/icons8_info_squared_48px.png"));
            try {
                if (rOrdenTrabCRUD.BuscarClientePlaca(rCliente, rVehiculo, rMarca, rTipo, rColor)) {
                    if (JOptionPane.showConfirmDialog(frmMttoControl, "Ya existen datos. Mostrar Datos?", "Validar",
                            JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, validar) == JOptionPane.YES_OPTION) {
                        frmMttoControl.txtPlacaVeh.setText(String.valueOf(rVehiculo.getPlaca()));
                        frmMttoControl.cboTipDoCli.addItem(String.valueOf(rCliente.getTipo_doc_cliente()));
                        frmMttoControl.txtNumDoCli.setText(String.valueOf(rCliente.getNum_doc_cliente()));
                        frmMttoControl.txtNomCli.setText(String.valueOf(rCliente.getNom_cliente()));
                        frmMttoControl.txtApeCli.setText(String.valueOf(rCliente.getApe_cliente()));
                        frmMttoControl.txtTelCli.setText(String.valueOf(rCliente.getTel_cliente()));
                        frmMttoControl.txtEmailCli.setText(String.valueOf(rCliente.getEmail_cliente()));
                        frmMttoControl.txtModeloVeh.setText(String.valueOf(rVehiculo.getModelo()));
                        frmMttoControl.txtMotorVeh.setText(String.valueOf(rVehiculo.getMotor()));
                        frmMttoControl.txtMarcaVeh.setText(String.valueOf(rMarca.getNom_marca()));
                        frmMttoControl.cboTipoVeh.addItem(String.valueOf(rTipo.getNom_tipo()));
                        frmMttoControl.txtColorVeh.setText(String.valueOf(rColor.getNom_color()));
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }
        
        //Cerrar
        if (e.getSource() == frmMttoControl.btnOrdenSalir) {
            Icon cerrar = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
            if (JOptionPane.showConfirmDialog(frmMttoControl, "¿Desea cerrar el formulario?", "Cerrar",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, cerrar) == JOptionPane.YES_OPTION) {
                frmMttoControl.dispose();
            }
        }

    }

}
