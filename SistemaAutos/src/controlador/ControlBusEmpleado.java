/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.*;
import vista.*;

/**
 *
 * @author Chris
 */
public class ControlBusEmpleado implements ActionListener {

    private EmpleadoBD reuEmpleBD;//Objeto del tipo ClienteBD
    private EmpleadoDAO reuEmpleDAO;//Objeto del tipo ClienteDAO
    private frmBusEmpleado frmEmpleBus;
    public static String codigo = "";
    private MouseListener l;

    public ControlBusEmpleado(EmpleadoBD reuEmpleBD, EmpleadoDAO reuEmpleDAO, frmBusEmpleado frmEmpleBus) {
        this.reuEmpleBD = reuEmpleBD;
        this.reuEmpleDAO = reuEmpleDAO;
        this.frmEmpleBus = frmEmpleBus;
        this.frmEmpleBus.btnBusEmpleado.addActionListener(this);
        this.frmEmpleBus.btnAcpEmpleado.addActionListener(this);
        frmEmpleBus.jtbEmpleado.addMouseListener(l);

    }

    public void IniciarEmpleBus() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmEmpleBus.setIconImage(Image);
        //Iniciar propiedades del form
        frmEmpleBus.setResizable(false);
        frmEmpleBus.setLocationRelativeTo(null);
        frmEmpleBus.setTitle("Búsqueda Empleado - MVC");
//        frmEmpleBus.set
        frmEmpleBus.setVisible(true);
        frmEmpleBus.jtbEmpleado.setModel(reuEmpleDAO.BusEmpleado(reuEmpleBD));
        TableColumn colA = frmEmpleBus.jtbEmpleado.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmEmpleBus.jtbEmpleado.getColumnCount(); c++) {
            Class<?> col_class = frmEmpleBus.jtbEmpleado.getColumnClass(c);
            frmEmpleBus.jtbEmpleado.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmEmpleBus.btnBusEmpleado) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmEmpleBus.txtEmpleado.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmEmpleBus, "Debe ingresar un criterio", "Búsqueda Empleado - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmEmpleBus.txtEmpleado.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmEmpleBus.jtbEmpleado.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmEmpleBus.btnAcpEmpleado) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuEmpleBD.setNomEmple(frmEmpleBus.txtEmpleado.getText());
        try {
            frmEmpleBus.jtbEmpleado.setModel(reuEmpleDAO.BusEmpleado(reuEmpleBD));
            frmEmpleBus.jtbEmpleado.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmEmpleBus.jtbEmpleado.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmEmpleBus, "No se ha seleccionado ninguna fila", "Búsqueda Empleado - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmEmpleBus.jtbEmpleado.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                String nomEmple = modTabla.getValueAt(filaSel, 1).toString();
                frmEmpleBus.dispose();
                //
                if (ControlOrdenTra.strFrmOrden == "S") {
                    ControlOrdenTra.frmOrdenControl.txtCodEmple.setText(codigo);
                    ControlOrdenTra.frmOrdenControl.txtNomEmple.setText(nomEmple);
                } else {
                    ControladorEmpleado.frmEmpleControl.txtCodEmpleado.setText(codigo);
                    if (String.valueOf(ControladorEmpleado.frmEmpleControl.txtCodEmpleado.getText()) != "") {
                        ControladorEmpleado.frmEmpleControl.btnValEmple.doClick();
                    }
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
