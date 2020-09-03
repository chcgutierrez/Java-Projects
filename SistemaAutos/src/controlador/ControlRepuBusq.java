/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.*;
import vista.*;

/**
 *
 * @author Chris
 */
public class ControlRepuBusq implements ActionListener {

    private RepuestoBD reuRepuestoBD;//Objeto del tipo RepuestoBD
    private RepuestoDAO reuRepuestoDAO;//Objeto del tipo RepuestoDAO
    private frmRepuBusq frmBusqRepuesto;
    public static String codigo = "";
    private MouseListener l;

    public ControlRepuBusq(RepuestoBD reuRepuestoBD, RepuestoDAO reuRepuestoDAO, frmRepuBusq frmBusqRepuesto) {
        this.reuRepuestoBD = reuRepuestoBD;
        this.reuRepuestoDAO = reuRepuestoDAO;
        this.frmBusqRepuesto = frmBusqRepuesto;
        this.frmBusqRepuesto.btnRepuBusq.addActionListener(this);
        this.frmBusqRepuesto.btnRepuOK.addActionListener(this);
        frmBusqRepuesto.jtbRepuDatos.addMouseListener(l);

    }

    public void IniciarRepuestoBusq() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusqRepuesto.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusqRepuesto.setResizable(false);
        frmBusqRepuesto.setLocationRelativeTo(null);
        frmBusqRepuesto.setTitle("Búsqueda Repuesto - MVC");
//        frmBusqRepuesto.set
        frmBusqRepuesto.setVisible(true);
        frmBusqRepuesto.jtbRepuDatos.setModel(reuRepuestoDAO.BusqRepto(reuRepuestoBD));
        TableColumn colA = frmBusqRepuesto.jtbRepuDatos.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusqRepuesto.jtbRepuDatos.getColumnCount(); c++) {
            Class<?> col_class = frmBusqRepuesto.jtbRepuDatos.getColumnClass(c);
            frmBusqRepuesto.jtbRepuDatos.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusqRepuesto.btnRepuBusq) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusqRepuesto.txtDescRepu.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusqRepuesto, "Debe ingresar un criterio", "Búsqueda Repuesto - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusqRepuesto.txtDescRepu.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusqRepuesto.jtbRepuDatos.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusqRepuesto.btnRepuOK) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuRepuestoBD.setNomRepto(frmBusqRepuesto.txtDescRepu.getText());
        try {
            frmBusqRepuesto.jtbRepuDatos.setModel(reuRepuestoDAO.BusqRepto(reuRepuestoBD));
            frmBusqRepuesto.jtbRepuDatos.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusqRepuesto.jtbRepuDatos.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusqRepuesto, "No se ha seleccionado ninguna fila", "Búsqueda Repuesto - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusqRepuesto.jtbRepuDatos.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                String strTipoRep = modTabla.getValueAt(filaSel, 1).toString();
                String descRepuesto = modTabla.getValueAt(filaSel, 2).toString();
                String strUnDisRep = modTabla.getValueAt(filaSel, 3).toString();
                frmBusqRepuesto.dispose();
                //Si se llama desde OrdenTrabajo
                if (ControlOrdenTra.strFrmOrden == "S") {
                    ControlOrdenTra.frmOrdenControl.txtCodRepuesto.setText(codigo);
                    ControlOrdenTra.frmOrdenControl.txtTipoRep.setText(strTipoRep);
                    ControlOrdenTra.frmOrdenControl.txtDescRepuesto.setText(descRepuesto);
                    ControlOrdenTra.frmOrdenControl.txtUndRep.setText(strUnDisRep);
                } else {
                    ControladorRepuesto.frmRepuestoControl.txtCodRepu.setText(codigo);
                    if (String.valueOf(ControladorRepuesto.frmRepuestoControl.txtCodRepu.getText()) != "") {
                        ControladorRepuesto.frmRepuestoControl.btnValRepu.doClick();
                    }
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
