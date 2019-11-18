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
public class ControlTipoDocBusq implements ActionListener {

    private TipoDocBD reuTipoDocBD;//Objeto del tipo TipoDocBD
    private TipoDocDAO reuTipoDocDAO;//Objeto del tipo TipoDocDAO
    private frmTipoDocBusq frmBusqTipoDoc;
    public static String codigo = "";
    private MouseListener l;

    public ControlTipoDocBusq(TipoDocBD reuTipoDocBD, TipoDocDAO reuTipoDocDAO, frmTipoDocBusq frmBusqTipoDoc) {
        this.reuTipoDocBD = reuTipoDocBD;
        this.reuTipoDocDAO = reuTipoDocDAO;
        this.frmBusqTipoDoc = frmBusqTipoDoc;
        this.frmBusqTipoDoc.btnTipoDocBusq.addActionListener(this);
        this.frmBusqTipoDoc.btnTipoDocOK.addActionListener(this);
        frmBusqTipoDoc.jtbTipoDocDatos.addMouseListener(l);

    }

    public void IniciarTipoDocBusq() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusqTipoDoc.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusqTipoDoc.setResizable(false);
        frmBusqTipoDoc.setLocationRelativeTo(null);
        frmBusqTipoDoc.setTitle("Búsqueda Tipo - MVC");
//        frmBusqTipoDoc.set
        frmBusqTipoDoc.setVisible(true);
        frmBusqTipoDoc.jtbTipoDocDatos.setModel(reuTipoDocDAO.BusqTipoDoc(reuTipoDocBD));
        TableColumn colA = frmBusqTipoDoc.jtbTipoDocDatos.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusqTipoDoc.jtbTipoDocDatos.getColumnCount(); c++) {
            Class<?> col_class = frmBusqTipoDoc.jtbTipoDocDatos.getColumnClass(c);
            frmBusqTipoDoc.jtbTipoDocDatos.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusqTipoDoc.btnTipoDocBusq) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusqTipoDoc.txtDescTipoDoc.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusqTipoDoc, "Debe ingresar un criterio", "Búsqueda Tipo - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusqTipoDoc.txtDescTipoDoc.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusqTipoDoc.jtbTipoDocDatos.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusqTipoDoc.btnTipoDocOK) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuTipoDocBD.setDescTipoDoc(frmBusqTipoDoc.txtDescTipoDoc.getText());
        try {
            frmBusqTipoDoc.jtbTipoDocDatos.setModel(reuTipoDocDAO.BusqTipoDoc(reuTipoDocBD));
            frmBusqTipoDoc.jtbTipoDocDatos.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusqTipoDoc.jtbTipoDocDatos.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusqTipoDoc, "No se ha seleccionado ninguna fila","Búsqueda Tipo - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusqTipoDoc.jtbTipoDocDatos.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmBusqTipoDoc.dispose();
                ControladorTipoDoc.frmTipoDocControl.txtCodTipoDoc.setText(codigo);
                if (String.valueOf(ControladorTipoDoc.frmTipoDocControl.txtCodTipoDoc.getText()) != "") {
                    ControladorTipoDoc.frmTipoDocControl.btnValTipoDoc.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
