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
import modelo.Marca;
import modelo.MarcaCRUD;
import vista.frmMarca;
import vista.frmMarcaTodo;

/**
 *
 * @author Chris
 */
public class ControlMarca implements ActionListener {

    private Marca rMarca;
    private MarcaCRUD rMarcaCUD;
    private frmMarca frmMarcaControl;
    private frmMarcaTodo frmMarcaTodos;

    public ControlMarca(Marca rMarca, MarcaCRUD rMarcaCUD, frmMarca frmMarcaControl, frmMarcaTodo frmMarcaTodos) {
        this.rMarca = rMarca;
        this.rMarcaCUD = rMarcaCUD;
        this.frmMarcaControl = frmMarcaControl;
        this.frmMarcaControl.btnMarcaGuardar.addActionListener(this);
        this.frmMarcaControl.btnMarcaEditar.addActionListener(this);
        this.frmMarcaControl.btnMarcaValidar.addActionListener(this);
        this.frmMarcaControl.btnMarcaBuscar.addActionListener(this);
        this.frmMarcaControl.btnMarcaSalir.addActionListener(this);

        this.frmMarcaTodos = frmMarcaTodos;
        this.frmMarcaTodos.btnCerrarBusqMarca.addActionListener(this);

    }

    public void IniciarMarcaTodo() {
        frmMarcaTodos.setResizable(false);
        frmMarcaTodos.setLocationRelativeTo(frmMarcaControl);
        frmMarcaTodos.setTitle("Buscar Marcas");
        frmMarcaTodos.setResizable(false);
        frmMarcaTodos.setVisible(true);
    }

    public void IniciarMarca() {
        frmMarcaControl.setResizable(false);
        frmMarcaControl.setLocationRelativeTo(null);
        frmMarcaControl.setTitle("Gestión Marcas");
        frmMarcaControl.setVisible(true);
    }

    String getEstadoMarca() {
        if (frmMarcaControl.rbtMarcaActivo.isSelected()) {
            return "A";
        } else if (frmMarcaControl.rbtMarcaInactivo.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }
    
    public void LimpiarMarca(){
        frmMarcaControl.txtCodMarca.setText("");
        frmMarcaControl.txtMarca.setText("");
        frmMarcaControl.btgMarca.clearSelection();
        frmMarcaControl.txtObsMarca.setText("");
    }

    //Eventos botones
    public void actionPerformed(ActionEvent e) {
        //Guardar
        Icon guardar = new ImageIcon(getClass().getResource("/Recursos/icons8_ok_48px.png"));
        Icon error = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
        if (e.getSource() == frmMarcaControl.btnMarcaGuardar) {
            rMarca.setId_marca(frmMarcaControl.txtCodMarca.getText());
            rMarca.setNom_marca(frmMarcaControl.txtMarca.getText());
            rMarca.setEst_marca(getEstadoMarca());
            rMarca.setObs_marca(frmMarcaControl.txtObsMarca.getText());

            try {
                if (rMarcaCUD.GuardarMarca(rMarca)) {
                    JOptionPane.showMessageDialog(frmMarcaControl, "Registro Guardado", "Guardar", JOptionPane.OK_OPTION, guardar);
                    LimpiarMarca();
                } else {
                    JOptionPane.showMessageDialog(frmMarcaControl, "No es posible guardar el registro", "Guardar", JOptionPane.ERROR, error);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Editar
        if (e.getSource() == frmMarcaControl.btnMarcaEditar) {
            rMarca.setId_marca(frmMarcaControl.txtCodMarca.getText());
            rMarca.setNom_marca(frmMarcaControl.txtMarca.getText());
            rMarca.setEst_marca(getEstadoMarca());
            rMarca.setObs_marca(frmMarcaControl.txtObsMarca.getText());
            Icon editar = new ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_48px.png"));

            try {
                if (rMarcaCUD.EditarMarca(rMarca)) {
                    JOptionPane.showMessageDialog(frmMarcaControl, "Registro Actualizado", "Editar", JOptionPane.OK_OPTION, editar);
                    LimpiarMarca();
                } else {
                    JOptionPane.showMessageDialog(frmMarcaControl, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Validar
        if (e.getSource() == frmMarcaControl.btnMarcaValidar) {
            rMarca.setId_marca(frmMarcaControl.txtCodMarca.getText());
            Icon validar = new ImageIcon(getClass().getResource("/Recursos/icons8_info_squared_48px.png"));
            try {
                if (rMarcaCUD.ValidarMarca(rMarca)) {
                    if (JOptionPane.showConfirmDialog(frmMarcaControl, "Ya existen datos. Mostrar Datos?", "Validar",
                            JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, validar) == JOptionPane.YES_OPTION) {
                        frmMarcaControl.txtCodMarca.setText(String.valueOf(rMarca.getId_marca()));
                        frmMarcaControl.txtMarca.setText(String.valueOf(rMarca.getNom_marca()));

                        if (rMarca.getEst_marca().equals("A")) {
                            frmMarcaControl.rbtMarcaActivo.doClick();
                        } else if (rMarca.getEst_marca().equals("I")) {
                            frmMarcaControl.rbtMarcaInactivo.doClick();
                        } else {
                            frmMarcaControl.rbtMarcaInactivo.doClick();
                        }

                        frmMarcaControl.txtObsMarca.setText(String.valueOf(rMarca.getObs_marca()));
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "No es posible editar el registro", "Editar", JOptionPane.ERROR);
                }
            } catch (HeadlessException e1) {
                e1.printStackTrace();
            }
        }

        //Ver Todos
        if (e.getSource() == frmMarcaControl.btnMarcaBuscar) {
            LimpiarMarca();
            frmMarcaTodos.tblMarca.setModel(rMarcaCUD.TodosMarca());
            IniciarMarcaTodo();

        }
        
        if (frmMarcaTodos.tblMarca.isRowSelected(0)) {
            PasarFila();
            frmMarcaTodos.dispose();
        }
        
        //Cerrar Padre
        if (e.getSource() == frmMarcaControl.btnMarcaSalir) {
            Icon cerrar = new ImageIcon(getClass().getResource("/Recursos/icons8_attention_48px.png"));
            if (JOptionPane.showConfirmDialog(frmMarcaControl, "¿Desea cerrar el formulario?", "Cerrar",
                    JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, cerrar) == JOptionPane.YES_OPTION) {
                frmMarcaControl.dispose();
            }
        }

        //Cerrar Hijo
        if (e.getSource() == frmMarcaTodos.btnCerrarBusqMarca) {
            frmMarcaTodos.dispose();
        }
    }
    public void PasarFila() {
        int selFila = frmMarcaTodos.tblMarca.getSelectedRow();
        frmMarcaControl.txtCodMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 0).toString());
        frmMarcaControl.txtCodMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 1).toString());
        frmMarcaControl.txtCodMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 2).toString());
        frmMarcaControl.txtCodMarca.setText(frmMarcaTodos.tblMarca.getValueAt(selFila, 3).toString());
        frmMarcaTodos.dispose();
    }

}
