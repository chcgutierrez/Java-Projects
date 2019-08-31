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
import modelo.Ciudad;
import modelo.CiudadCRUD;
import vista.frmCiudad;
import vista.frmCiudadTodo;

/**
 *
 * @author Chris
 */
public class ControlCiudad implements ActionListener {

    private Ciudad rCiudad;
    private CiudadCRUD rCiudadCUD;
    private frmCiudad frmCiudadControl;
    private frmCiudadTodo frmCiudadTodos;

    public ControlCiudad(Ciudad rCiudad, CiudadCRUD rCiudadCUD, frmCiudad frmCiudadControl, frmCiudadTodo frmCiudadTodos) {
        this.rCiudad = rCiudad;
        this.rCiudadCUD = rCiudadCUD;
        this.frmCiudadControl = frmCiudadControl;
        this.frmCiudadTodos = frmCiudadTodos;
        this.frmCiudadControl.btnGuardarCiudad.addActionListener(this);
        this.frmCiudadControl.btnEditarCiudad.addActionListener(this);
        this.frmCiudadControl.btnValidarCiudad.addActionListener(this);
        this.frmCiudadControl.btnBuscarCiudad.addActionListener(this);
        this.frmCiudadControl.btnSalirCiudad.addActionListener(this);
        frmCiudadTodos.btnCerrarBusqCiudad.addActionListener(this);
    }

    public void IniciarCiudadTodos() {
        frmCiudadTodos.setResizable(false);
        frmCiudadTodos.setLocationRelativeTo(frmCiudadControl);
        frmCiudadTodos.setTitle("Buscar Ciudads");
        frmCiudadTodos.setResizable(false);
        frmCiudadTodos.setVisible(true);
    }

    public void IniciarCiudad() {
        frmCiudadControl.setResizable(false);
        frmCiudadControl.setLocationRelativeTo(null);
        frmCiudadControl.setTitle("Gestión Ciudad");
        frmCiudadControl.setVisible(true);
    }

    String getEstadoCiudad() {
        if (frmCiudadControl.rbtActCiudad.isSelected()) {
            return "A";
        } else if (frmCiudadControl.rbtInaCiudad.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }
    
    public void LimpiarCiudad(){
        frmCiudadControl.txtCodCiudad.setText("");
        frmCiudadControl.txtCiudad.setText("");
        frmCiudadControl.btgCiudad.clearSelection();
        frmCiudadControl.txtObsCiudad.setText("");
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Guardar
        Icon guardar = new ImageIcon(getClass().getResource("/Recursos/icons8_ok_48px.png"));
        Icon error = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
        if (e.getSource() == frmCiudadControl.btnGuardarCiudad) {
            rCiudad.setId_ciudad(frmCiudadControl.txtCodCiudad.getText());
            rCiudad.setNom_ciudad(frmCiudadControl.txtCiudad.getText());
            rCiudad.setEst_ciudad(getEstadoCiudad());
            rCiudad.setObs_ciudad(frmCiudadControl.txtObsCiudad.getText());

            try {
                if (rCiudadCUD.GuardarCiudad(rCiudad)) {
                    JOptionPane.showMessageDialog(frmCiudadControl, "Registro Guardado", "Guardar", JOptionPane.OK_OPTION, guardar);
                    LimpiarCiudad();

                } else {
                    JOptionPane.showMessageDialog(frmCiudadControl, "No es posible guardar el registro", "Guardar", JOptionPane.ERROR, error);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Editar
        if (e.getSource() == frmCiudadControl.btnEditarCiudad) {
            rCiudad.setId_ciudad(frmCiudadControl.txtCodCiudad.getText());
            rCiudad.setNom_ciudad(frmCiudadControl.txtCiudad.getText());
            rCiudad.setEst_ciudad(getEstadoCiudad());
            rCiudad.setObs_ciudad(frmCiudadControl.txtObsCiudad.getText());
            Icon editar = new ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_48px.png"));

            try {
                if (rCiudadCUD.EditarCiudad(rCiudad)) {
                    JOptionPane.showMessageDialog(frmCiudadControl, "Registro Actualizado", "Editar", JOptionPane.OK_OPTION, editar);
                    LimpiarCiudad();
                } else {
                    JOptionPane.showMessageDialog(frmCiudadControl, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Validar
        if (e.getSource() == frmCiudadControl.btnValidarCiudad) {
            rCiudad.setId_ciudad(frmCiudadControl.txtCodCiudad.getText());
            Icon validar = new ImageIcon(getClass().getResource("/Recursos/icons8_info_squared_48px.png"));
            try {
                if (rCiudadCUD.ValidarCiudad(rCiudad)) {
                    if (JOptionPane.showConfirmDialog(frmCiudadControl, "Ya existen datos. Mostrar Datos?", "Validar",
                            JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, validar) == JOptionPane.YES_OPTION) {
                        frmCiudadControl.txtCodCiudad.setText(String.valueOf(rCiudad.getId_ciudad()));
                        frmCiudadControl.txtCiudad.setText(String.valueOf(rCiudad.getNom_ciudad()));

                        if (rCiudad.getEst_ciudad().equals("A")) {
                            frmCiudadControl.rbtActCiudad.doClick();
                        } else if (rCiudad.getEst_ciudad().equals("I")) {
                            frmCiudadControl.rbtInaCiudad.doClick();
                        } else {
                            frmCiudadControl.rbtInaCiudad.doClick();
                        }

                        frmCiudadControl.txtObsCiudad.setText(String.valueOf(rCiudad.getObs_ciudad()));
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Ver Todos
        if (e.getSource() == frmCiudadControl.btnBuscarCiudad) {
            LimpiarCiudad();
            frmCiudadTodos.tblCiudad.setModel(rCiudadCUD.TodosCiudad());
            IniciarCiudadTodos();
        }

        //
//        if(e.getSource()==frmCiudadTodos.tblCiudadMouseClicked){
//             int fila = frmCiudadTodos.tblCiudad;
//             frmCiudadControl.txtCodCiudad.setText(frmCiudadTodos.tblCiudad.getValueAt(fila, 0).toString());
//             frmCiudadTodos.dispose();
//        }
        //Cerrar
        if (e.getSource() == frmCiudadControl.btnSalirCiudad) {
            Icon cerrar = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
            if (JOptionPane.showConfirmDialog(frmCiudadControl, "¿Desea cerrar el formulario?", "Cerrar",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, cerrar) == JOptionPane.YES_OPTION) {
                frmCiudadControl.dispose();
            }
        }

        //Cerrar Todos  
        if (e.getSource() == frmCiudadTodos.btnCerrarBusqCiudad) {
            frmCiudadTodos.dispose();
        }
    }
}
