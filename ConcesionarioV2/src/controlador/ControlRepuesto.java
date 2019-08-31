/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.HeadlessException;
import java.awt.event.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 *
 * @author Chris
 */
public class ControlRepuesto implements ActionListener {

    private Repuesto rRepuesto;
    private RepuestoCRUD rRepuestoCUD;
    private frmRepuesto frmRepuestoControl;
    private frmRepuTodo frmRepuestoTodos;

    public ControlRepuesto(Repuesto rRepuesto, RepuestoCRUD rRepuestoCUD, frmRepuesto frmRepuestoControl, frmRepuTodo frmRepuestoTodos) {
        this.rRepuesto = rRepuesto;
        this.rRepuestoCUD = rRepuestoCUD;
        this.frmRepuestoControl = frmRepuestoControl;
        this.frmRepuestoTodos = frmRepuestoTodos;
        this.frmRepuestoControl.btnGuardarRepu.addActionListener(this);
        this.frmRepuestoControl.btnEditarRepu.addActionListener(this);
        this.frmRepuestoControl.btnValidarRepu.addActionListener(this);
        this.frmRepuestoControl.btnBuscarRepu.addActionListener(this);
        this.frmRepuestoControl.btnSalirRepu.addActionListener(this);
        frmRepuestoTodos.btnCerrarBusqRepu.addActionListener(this);
    }

    public void IniciarRepuestoTodos() {
        frmRepuestoTodos.setResizable(false);
        frmRepuestoTodos.setLocationRelativeTo(frmRepuestoControl);
        frmRepuestoTodos.setTitle("Buscar Repuestos");
        frmRepuestoTodos.setResizable(false);
        frmRepuestoTodos.setVisible(true);
    }

    public void IniciarRepuesto() {
        frmRepuestoControl.setResizable(false);
        frmRepuestoControl.setLocationRelativeTo(null);
        frmRepuestoControl.setTitle("Gestión Repuesto");
        frmRepuestoControl.setVisible(true);
    }

//    String getEstadoRepuesto() {
//        if (frmRepuestoControl.rbtActRepuesto.isSelected()) {
//            return "A";
//        } else if (frmRepuestoControl.rbtInaRepuesto.isSelected()) {
//            return "I";
//        } else {
//            return null;
//        }
//    }
    public void LimpiarRepuesto() {
        frmRepuestoControl.txtCodRepu.setText("");
        frmRepuestoControl.cboTipoRepu.setSelectedIndex(0);
        frmRepuestoControl.txtNomRepu.setText("");
        frmRepuestoControl.txtDescRepu.setText("");
        frmRepuestoControl.spnCantRepu.setValue(0);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Guardar
        Icon guardar = new ImageIcon(getClass().getResource("/Recursos/icons8_ok_48px.png"));
        Icon error = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
        if (e.getSource() == frmRepuestoControl.btnGuardarRepu) {
            rRepuesto.setId_repuesto(frmRepuestoControl.txtCodRepu.getText());
            rRepuesto.setTipo_repuesto(String.valueOf(frmRepuestoControl.cboTipoRepu.getSelectedItem()));
            rRepuesto.setNom_repuesto(frmRepuestoControl.txtNomRepu.getText());
            rRepuesto.setDesc_repuesto(frmRepuestoControl.txtDescRepu.getText());
            rRepuesto.setCant_repuesto(Integer.valueOf(String.valueOf(frmRepuestoControl.spnCantRepu.getValue())));

            try {
                if (rRepuestoCUD.GuardarRepuesto(rRepuesto)) {
                    JOptionPane.showMessageDialog(frmRepuestoControl, "Registro Guardado", "Guardar", JOptionPane.OK_OPTION, guardar);
                    LimpiarRepuesto();

                } else {
                    JOptionPane.showMessageDialog(frmRepuestoControl, "No es posible guardar el registro", "Guardar", JOptionPane.ERROR, error);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Editar
        if (e.getSource() == frmRepuestoControl.btnEditarRepu) {
            rRepuesto.setId_repuesto(frmRepuestoControl.txtCodRepu.getText());
            rRepuesto.setTipo_repuesto(String.valueOf(frmRepuestoControl.cboTipoRepu.getSelectedItem()));
            rRepuesto.setNom_repuesto(frmRepuestoControl.txtNomRepu.getText());
            rRepuesto.setDesc_repuesto(frmRepuestoControl.txtDescRepu.getText());
            rRepuesto.setCant_repuesto(Integer.valueOf(String.valueOf(frmRepuestoControl.spnCantRepu.getValue())));
            Icon editar = new ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_48px.png"));

            try {
                if (rRepuestoCUD.EditarRepuesto(rRepuesto)) {
                    JOptionPane.showMessageDialog(frmRepuestoControl, "Registro Actualizado", "Editar", JOptionPane.OK_OPTION, editar);
                    LimpiarRepuesto();
                } else {
                    JOptionPane.showMessageDialog(frmRepuestoControl, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Validar
        if (e.getSource() == frmRepuestoControl.btnValidarRepu) {
            rRepuesto.setId_repuesto(frmRepuestoControl.txtCodRepu.getText());
            Icon validar = new ImageIcon(getClass().getResource("/Recursos/icons8_info_squared_48px.png"));
            try {
                if (rRepuestoCUD.ValidarRepuesto(rRepuesto)) {
                    if (JOptionPane.showConfirmDialog(frmRepuestoControl, "Ya existen datos. Mostrar Datos?", "Validar",
                            JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, validar) == JOptionPane.YES_OPTION) {
                        frmRepuestoControl.txtCodRepu.setText(String.valueOf(rRepuesto.getId_repuesto()));
                        frmRepuestoControl.cboTipoRepu.setSelectedItem(String.valueOf(rRepuesto.getTipo_repuesto()));
                        frmRepuestoControl.txtNomRepu.setText(String.valueOf(rRepuesto.getNom_repuesto()));
                        frmRepuestoControl.txtDescRepu.setText(String.valueOf(rRepuesto.getDesc_repuesto()));
                        frmRepuestoControl.spnCantRepu.setValue(Integer.valueOf(String.valueOf(rRepuesto.getCant_repuesto())));

                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Ver Todos
        if (e.getSource() == frmRepuestoControl.btnBuscarRepu) {
            LimpiarRepuesto();
            frmRepuestoTodos.tblRepu.setModel(rRepuestoCUD.TodosRepuesto());
            IniciarRepuestoTodos();
        }

        //
//        if(e.getSource()==frmRepuestoTodos.tblRepuestoMouseClicked){
//             int fila = frmRepuestoTodos.tblRepuesto;
//             frmRepuestoControl.txtCodRepuesto.setText(frmRepuestoTodos.tblRepuesto.getValueAt(fila, 0).toString());
//             frmRepuestoTodos.dispose();
//        }
        //Cerrar
        if (e.getSource() == frmRepuestoControl.btnSalirRepu) {
            Icon cerrar = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
            if (JOptionPane.showConfirmDialog(frmRepuestoControl, "¿Desea cerrar el formulario?", "Cerrar",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, cerrar) == JOptionPane.YES_OPTION) {
                frmRepuestoControl.dispose();
            }
        }

        //Cerrar Todos  
        if (e.getSource() == frmRepuestoTodos.btnCerrarBusqRepu) {
            frmRepuestoTodos.dispose();
        }
    }
}
