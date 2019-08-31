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
import javax.swing.table.DefaultTableModel;
import modelo.Tipo;
import modelo.TipoCRUD;
import vista.frmTipo;
import vista.frmTipoTodo;

/**
 *
 * @author Chris
 */
public class ControlTipo implements ActionListener {

    private Tipo rTipo;
    private TipoCRUD rTipoCUD;
    private frmTipo frmTipoControl;
    private frmTipoTodo frmTipoTodos;

    public ControlTipo(Tipo rTipo, TipoCRUD rTipoCUD, frmTipo frmTipoControl, frmTipoTodo frmTipoTodos) {
        this.rTipo = rTipo;
        this.rTipoCUD = rTipoCUD;
        this.frmTipoControl = frmTipoControl;
        this.frmTipoTodos = frmTipoTodos;
        this.frmTipoControl.btnGuardarTipo.addActionListener(this);
        this.frmTipoControl.btnEditarTipo.addActionListener(this);
        this.frmTipoControl.btnValidarTipo.addActionListener(this);
        this.frmTipoControl.btnBuscarTipo.addActionListener(this);
        this.frmTipoControl.btnSalirTipo.addActionListener(this);
        frmTipoTodos.btnCerrarBusqTipo.addActionListener(this);
    }

    public void IniciarTipoTodos() {
        frmTipoTodos.setResizable(false);
        frmTipoTodos.setLocationRelativeTo(frmTipoControl);
        frmTipoTodos.setTitle("Buscar Tipos");
        frmTipoTodos.setResizable(false);
        frmTipoTodos.setVisible(true);
    }

    public void IniciarTipo() {
        frmTipoControl.setResizable(false);
        frmTipoControl.setLocationRelativeTo(null);
        frmTipoControl.setTitle("Gestión Tipo");
        frmTipoControl.setVisible(true);
    }

    String getEstadoTipo() {
        if (frmTipoControl.rbtActTipo.isSelected()) {
            return "A";
        } else if (frmTipoControl.rbtInaTipo.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }
    
    public void LimpiarTipo(){
        frmTipoControl.txtCodTipo.setText("");
        frmTipoControl.txtTipo.setText("");
        frmTipoControl.btgTipo.clearSelection();
        frmTipoControl.txtObsTipo.setText("");
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Guardar
        Icon guardar = new ImageIcon(getClass().getResource("/Recursos/icons8_ok_48px.png"));
        Icon error = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
        if (e.getSource() == frmTipoControl.btnGuardarTipo) {
            rTipo.setId_tipo(frmTipoControl.txtCodTipo.getText());
            rTipo.setNom_tipo(frmTipoControl.txtTipo.getText());
            rTipo.setEst_tipo(getEstadoTipo());
            rTipo.setObs_tipo(frmTipoControl.txtObsTipo.getText());

            try {
                if (rTipoCUD.GuardarTipo(rTipo)) {
                    JOptionPane.showMessageDialog(frmTipoControl, "Registro Guardado", "Guardar", JOptionPane.OK_OPTION, guardar);
                    LimpiarTipo();

                } else {
                    JOptionPane.showMessageDialog(frmTipoControl, "No es posible guardar el registro", "Guardar", JOptionPane.ERROR, error);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Editar
        if (e.getSource() == frmTipoControl.btnEditarTipo) {
            rTipo.setId_tipo(frmTipoControl.txtCodTipo.getText());
            rTipo.setNom_tipo(frmTipoControl.txtTipo.getText());
            rTipo.setEst_tipo(getEstadoTipo());
            rTipo.setObs_tipo(frmTipoControl.txtObsTipo.getText());
            Icon editar = new ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_48px.png"));

            try {
                if (rTipoCUD.EditarTipo(rTipo)) {
                    JOptionPane.showMessageDialog(frmTipoControl, "Registro Actualizado", "Editar", JOptionPane.OK_OPTION, editar);
                    LimpiarTipo();
                } else {
                    JOptionPane.showMessageDialog(frmTipoControl, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Validar
        if (e.getSource() == frmTipoControl.btnValidarTipo) {
            rTipo.setId_tipo(frmTipoControl.txtCodTipo.getText());
            Icon validar = new ImageIcon(getClass().getResource("/Recursos/icons8_info_squared_48px.png"));
            try {
                if (rTipoCUD.ValidarTipo(rTipo)) {
                    if (JOptionPane.showConfirmDialog(frmTipoControl, "Ya existen datos. Mostrar Datos?", "Validar",
                            JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, validar) == JOptionPane.YES_OPTION) {
                        frmTipoControl.txtCodTipo.setText(String.valueOf(rTipo.getId_tipo()));
                        frmTipoControl.txtTipo.setText(String.valueOf(rTipo.getNom_tipo()));

                        if (rTipo.getEst_tipo().equals("A")) {
                            frmTipoControl.rbtActTipo.doClick();
                        } else if (rTipo.getEst_tipo().equals("I")) {
                            frmTipoControl.rbtInaTipo.doClick();
                        } else {
                            frmTipoControl.rbtInaTipo.doClick();
                        }

                        frmTipoControl.txtObsTipo.setText(String.valueOf(rTipo.getObs_tipo()));
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Ver Todos
        if (e.getSource() == frmTipoControl.btnBuscarTipo) {
            LimpiarTipo();
            frmTipoTodos.tblTipo.setModel(rTipoCUD.TodosTipo());
            IniciarTipoTodos();
        }

        //
//        if(e.getSource()==frmTipoTodos.tblTipoMouseClicked){
//             int fila = frmTipoTodos.tblTipo;
//             frmTipoControl.txtCodTipo.setText(frmTipoTodos.tblTipo.getValueAt(fila, 0).toString());
//             frmTipoTodos.dispose();
//        }
        //Cerrar
        if (e.getSource() == frmTipoControl.btnSalirTipo) {
            Icon cerrar = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
            if (JOptionPane.showConfirmDialog(frmTipoControl, "¿Desea cerrar el formulario?", "Cerrar",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, cerrar) == JOptionPane.YES_OPTION) {
                frmTipoControl.dispose();
            }
        }

        //Cerrar Todos  
        if (e.getSource() == frmTipoTodos.btnCerrarBusqTipo) {
            frmTipoTodos.dispose();
        }
    }
}
