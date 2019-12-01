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
public class ControlBusCliente implements ActionListener {

    private ClienteBD reuClienteBD;//Objeto del tipo ClienteBD
    private ClienteDAO reuClienteDAO;//Objeto del tipo ClienteDAO
    private frmBusCliente frmClienteBus;
    public static String codigo = "";
    private MouseListener l;

    public ControlBusCliente(ClienteBD reuClienteBD, ClienteDAO reuClienteDAO, frmBusCliente frmClienteBus) {
        this.reuClienteBD = reuClienteBD;
        this.reuClienteDAO = reuClienteDAO;
        this.frmClienteBus = frmClienteBus;
        this.frmClienteBus.btnBusCliente.addActionListener(this);
        this.frmClienteBus.btnAcpCliente.addActionListener(this);
        frmClienteBus.jtbCliente.addMouseListener(l);

    }

    public void IniciarClienteBus() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmClienteBus.setIconImage(Image);
        //Iniciar propiedades del form
        frmClienteBus.setResizable(false);
        frmClienteBus.setLocationRelativeTo(null);
        frmClienteBus.setTitle("Búsqueda Cliente - MVC");
//        frmClienteBus.set
        frmClienteBus.setVisible(true);
        frmClienteBus.jtbCliente.setModel(reuClienteDAO.BusqCliente(reuClienteBD));
        TableColumn colA = frmClienteBus.jtbCliente.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmClienteBus.jtbCliente.getColumnCount(); c++) {
            Class<?> col_class = frmClienteBus.jtbCliente.getColumnClass(c);
            frmClienteBus.jtbCliente.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmClienteBus.btnBusCliente) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmClienteBus.txtCliente.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmClienteBus, "Debe ingresar un criterio", "Búsqueda Cliente - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmClienteBus.txtCliente.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmClienteBus.jtbCliente.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmClienteBus.btnAcpCliente) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuClienteBD.setNomCliente(frmClienteBus.txtCliente.getText());
        try {
            frmClienteBus.jtbCliente.setModel(reuClienteDAO.BusqCliente(reuClienteBD));
            frmClienteBus.jtbCliente.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmClienteBus.jtbCliente.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmClienteBus, "No se ha seleccionado ninguna fila", "Búsqueda Cliente - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmClienteBus.jtbCliente.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmClienteBus.dispose();
                ControladorCliente.frmClienteControl.txtCodCliente.setText(codigo);
                if (String.valueOf(ControladorCliente.frmClienteControl.txtCodCliente.getText()) != "") {
                    ControladorCliente.frmClienteControl.btnValCliente.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
