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
public class ControlBusCarro implements ActionListener {

    private CarroBD reuCarroBD;//Objeto del tipo CarroBD
    private CarroDAO reuCarroDAO;//Objeto del tipo CarroDAO
    private frmBusVehiculo frmBusCarro;
    public static String codigo = "";
    private MouseListener l;

    public ControlBusCarro(CarroBD reuCarroBD, CarroDAO reuCarroDAO, frmBusVehiculo frmBusCarro) {
        this.reuCarroBD = reuCarroBD;
        this.reuCarroDAO = reuCarroDAO;
        this.frmBusCarro = frmBusCarro;
        this.frmBusCarro.btnBusCarro.addActionListener(this);
        this.frmBusCarro.btnAcpCarro.addActionListener(this);
        frmBusCarro.jtbDatosCarro.addMouseListener(l);

    }

    public void IniciarCarroBus() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusCarro.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusCarro.setResizable(false);
        frmBusCarro.setLocationRelativeTo(null);
        frmBusCarro.setTitle("Búsqueda Carro - MVC");
//        frmBusCarro.set
        frmBusCarro.setVisible(true);
        frmBusCarro.jtbDatosCarro.setModel(reuCarroDAO.BusqCarro(reuCarroBD));
        TableColumn colA = frmBusCarro.jtbDatosCarro.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusCarro.jtbDatosCarro.getColumnCount(); c++) {
            Class<?> col_class = frmBusCarro.jtbDatosCarro.getColumnClass(c);
            frmBusCarro.jtbDatosCarro.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusCarro.btnBusCarro) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusCarro.txtDesPlaca.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusCarro, "Debe ingresar un criterio", "Búsqueda Carro - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusCarro.txtDesPlaca.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusCarro.jtbDatosCarro.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusCarro.btnAcpCarro) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {
        reuCarroBD.setPlacaCarro(frmBusCarro.txtDesPlaca.getText());
        try {
            frmBusCarro.jtbDatosCarro.setModel(reuCarroDAO.BusqCarro(reuCarroBD));
            frmBusCarro.jtbDatosCarro.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusCarro.jtbDatosCarro.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusCarro, "No se ha seleccionado ninguna fila", "Búsqueda Carro - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusCarro.jtbDatosCarro.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmBusCarro.dispose();
                ControladorCarro.frmCarroControl.txtPlaca.setText(codigo);
                if (String.valueOf(ControladorCarro.frmCarroControl.txtPlaca.getText()) != "") {
                    ControladorCarro.frmCarroControl.btnValCarro.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
