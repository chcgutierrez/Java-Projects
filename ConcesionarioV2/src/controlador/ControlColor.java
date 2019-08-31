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
import modelo.Color;
import modelo.ColorCRUD;
import vista.frmColor;
import vista.frmColorTodo;

/**
 *
 * @author Chris
 */
public class ControlColor implements ActionListener {

    private Color rColor;
    private ColorCRUD rColorCUD;
    private frmColor frmColorControl;
    private frmColorTodo frmColorTodos;

    public ControlColor(Color rColor, ColorCRUD rColorCUD, frmColor frmColorControl, frmColorTodo frmColorTodos) {
        this.rColor = rColor;
        this.rColorCUD = rColorCUD;
        this.frmColorControl = frmColorControl;
        this.frmColorTodos = frmColorTodos;
        this.frmColorControl.btnGuardarColor.addActionListener(this);
        this.frmColorControl.btnEditarColor.addActionListener(this);
        this.frmColorControl.btnValidarColor.addActionListener(this);
        this.frmColorControl.btnBuscarColor.addActionListener(this);
        this.frmColorControl.btnSalirColor.addActionListener(this);
        frmColorTodos.btnCerrarBusqColor.addActionListener(this);
    }

    public void IniciarColorTodos() {
        frmColorTodos.setResizable(false);
        frmColorTodos.setLocationRelativeTo(frmColorControl);
        frmColorTodos.setTitle("Buscar Colors");
        frmColorTodos.setResizable(false);
        frmColorTodos.setVisible(true);
    }

    public void IniciarColor() {
        frmColorControl.setResizable(false);
        frmColorControl.setLocationRelativeTo(null);
        frmColorControl.setTitle("Gestión Color");
        frmColorControl.setVisible(true);
    }

    String getEstadoColor() {
        if (frmColorControl.rbtActColor.isSelected()) {
            return "A";
        } else if (frmColorControl.rbtInaColor.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }
    
    public void LimpiarColor(){
        frmColorControl.txtCodColor.setText("");
        frmColorControl.txtColor.setText("");
        frmColorControl.btgColor.clearSelection();
        frmColorControl.txtObsColor.setText("");
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Guardar
        Icon guardar = new ImageIcon(getClass().getResource("/Recursos/icons8_ok_48px.png"));
        Icon error = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
        if (e.getSource() == frmColorControl.btnGuardarColor) {
            rColor.setId_color(frmColorControl.txtCodColor.getText());
            rColor.setNom_color(frmColorControl.txtColor.getText());
            rColor.setEst_color(getEstadoColor());
            rColor.setObs_color(frmColorControl.txtObsColor.getText());

            try {
                if (rColorCUD.GuardarColor(rColor)) {
                    JOptionPane.showMessageDialog(frmColorControl, "Registro Guardado", "Guardar", JOptionPane.OK_OPTION, guardar);
                    LimpiarColor();

                } else {
                    JOptionPane.showMessageDialog(frmColorControl, "No es posible guardar el registro", "Guardar", JOptionPane.ERROR, error);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Editar
        if (e.getSource() == frmColorControl.btnEditarColor) {
            rColor.setId_color(frmColorControl.txtCodColor.getText());
            rColor.setNom_color(frmColorControl.txtColor.getText());
            rColor.setEst_color(getEstadoColor());
            rColor.setObs_color(frmColorControl.txtObsColor.getText());
            Icon editar = new ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_48px.png"));

            try {
                if (rColorCUD.EditarColor(rColor)) {
                    JOptionPane.showMessageDialog(frmColorControl, "Registro Actualizado", "Editar", JOptionPane.OK_OPTION, editar);
                    LimpiarColor();
                } else {
                    JOptionPane.showMessageDialog(frmColorControl, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Validar
        if (e.getSource() == frmColorControl.btnValidarColor) {
            rColor.setId_color(frmColorControl.txtCodColor.getText());
            Icon validar = new ImageIcon(getClass().getResource("/Recursos/icons8_info_squared_48px.png"));
            try {
                if (rColorCUD.ValidarColor(rColor)) {
                    if (JOptionPane.showConfirmDialog(frmColorControl, "Ya existen datos. Mostrar Datos?", "Validar",
                            JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, validar) == JOptionPane.YES_OPTION) {
                        frmColorControl.txtCodColor.setText(String.valueOf(rColor.getId_color()));
                        frmColorControl.txtColor.setText(String.valueOf(rColor.getNom_color()));

                        if (rColor.getEst_color().equals("A")) {
                            frmColorControl.rbtActColor.doClick();
                        } else if (rColor.getEst_color().equals("I")) {
                            frmColorControl.rbtInaColor.doClick();
                        } else {
                            frmColorControl.rbtInaColor.doClick();
                        }

                        frmColorControl.txtObsColor.setText(String.valueOf(rColor.getObs_color()));
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Ver Todos
        if (e.getSource() == frmColorControl.btnBuscarColor) {
            LimpiarColor();
            frmColorTodos.tblColor.setModel(rColorCUD.TodosColor());
            IniciarColorTodos();
        }

        //
//        if(e.getSource()==frmColorTodos.tblColorMouseClicked){
//             int fila = frmColorTodos.tblColor;
//             frmColorControl.txtCodColor.setText(frmColorTodos.tblColor.getValueAt(fila, 0).toString());
//             frmColorTodos.dispose();
//        }
        //Cerrar
        if (e.getSource() == frmColorControl.btnSalirColor) {
            Icon cerrar = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
            if (JOptionPane.showConfirmDialog(frmColorControl, "¿Desea cerrar el formulario?", "Cerrar",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, cerrar) == JOptionPane.YES_OPTION) {
                frmColorControl.dispose();
            }
        }

        //Cerrar Todos  
        if (e.getSource() == frmColorTodos.btnCerrarBusqColor) {
            frmColorTodos.dispose();
        }
    }
}
