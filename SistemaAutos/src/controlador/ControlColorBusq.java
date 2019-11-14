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
public class ControlColorBusq implements ActionListener {

    private ColorBD reuColorBD;//Objeto del tipo ColorBD
    private ColorDAO reuColorDAO;//Objeto del tipo ColorDAO
    private frmColorBusq frmBusqColor;
    public static String codigo = "";
    private MouseListener l;

    public ControlColorBusq(ColorBD reuColorBD, ColorDAO reuColorDAO, frmColorBusq frmBusqColor) {
        this.reuColorBD = reuColorBD;
        this.reuColorDAO = reuColorDAO;
        this.frmBusqColor = frmBusqColor;
        this.frmBusqColor.btnColorBusq.addActionListener(this);
        this.frmBusqColor.btnColorOK.addActionListener(this);
        frmBusqColor.jtbColorDatos.addMouseListener(l);

    }

    public void IniciarColorBusq() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusqColor.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusqColor.setResizable(false);
        frmBusqColor.setLocationRelativeTo(null);
        frmBusqColor.setTitle("Búsqueda Marcas - MVC");
//        frmBusqColor.set
        frmBusqColor.setVisible(true);
        frmBusqColor.jtbColorDatos.setModel(reuColorDAO.BusqColor(reuColorBD));
        TableColumn colA = frmBusqColor.jtbColorDatos.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusqColor.jtbColorDatos.getColumnCount(); c++) {
            Class<?> col_class = frmBusqColor.jtbColorDatos.getColumnClass(c);
            frmBusqColor.jtbColorDatos.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusqColor.btnColorBusq) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusqColor.txtDescColor.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusqColor, "Debe ingresar un criterio", "Búsqueda Color - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusqColor.txtDescColor.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusqColor.jtbColorDatos.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusqColor.btnColorOK) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuColorBD.setNomColor(frmBusqColor.txtDescColor.getText());
        try {
            frmBusqColor.jtbColorDatos.setModel(reuColorDAO.BusqColor(reuColorBD));
            frmBusqColor.jtbColorDatos.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusqColor.jtbColorDatos.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusqColor, "No se ha seleccionado ninguna fila","Búsqueda Color - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusqColor.jtbColorDatos.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmBusqColor.dispose();
                ControladorColor.frmColorControl.txtCodColor.setText(codigo);
                if (String.valueOf(ControladorColor.frmColorControl.txtCodColor.getText()) != "") {
                    ControladorColor.frmColorControl.btnVldColor.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
