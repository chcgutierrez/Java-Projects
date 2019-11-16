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
public class ControlCiudadBusq implements ActionListener {

    private CiudadBD reuCiudadBD;//Objeto del tipo CiudadBD
    private CiudadDAO reuCiudadDAO;//Objeto del tipo CiudadDAO
    private frmBusCiudad frmBusqCiudad;
    public static String codigo = "";
    private MouseListener l;

    public ControlCiudadBusq(CiudadBD reuCiudadBD, CiudadDAO reuCiudadDAO, frmBusCiudad frmBusqCiudad) {
        this.reuCiudadBD = reuCiudadBD;
        this.reuCiudadDAO = reuCiudadDAO;
        this.frmBusqCiudad = frmBusqCiudad;
        this.frmBusqCiudad.btnBusCiudad.addActionListener(this);
        this.frmBusqCiudad.btnAcpCiudad.addActionListener(this);
        frmBusqCiudad.jtbDatosCiudad.addMouseListener(l);

    }

    public void IniciarCiudadBusq() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusqCiudad.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusqCiudad.setResizable(false);
        frmBusqCiudad.setLocationRelativeTo(null);
        frmBusqCiudad.setTitle("Búsqueda Ciudad - MVC");
//        frmBusqCiudad.set
        frmBusqCiudad.setVisible(true);
        frmBusqCiudad.jtbDatosCiudad.setModel(reuCiudadDAO.BusqCiudad(reuCiudadBD));
        TableColumn colA = frmBusqCiudad.jtbDatosCiudad.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusqCiudad.jtbDatosCiudad.getColumnCount(); c++) {
            Class<?> col_class = frmBusqCiudad.jtbDatosCiudad.getColumnClass(c);
            frmBusqCiudad.jtbDatosCiudad.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusqCiudad.btnBusCiudad) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusqCiudad.txtDesCiudad.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusqCiudad, "Debe ingresar un criterio", "Búsqueda Ciudad - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusqCiudad.txtDesCiudad.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusqCiudad.jtbDatosCiudad.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusqCiudad.btnAcpCiudad) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuCiudadBD.setNomCiudad(frmBusqCiudad.txtDesCiudad.getText());
        try {
            frmBusqCiudad.jtbDatosCiudad.setModel(reuCiudadDAO.BusqCiudad(reuCiudadBD));
            frmBusqCiudad.jtbDatosCiudad.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusqCiudad.jtbDatosCiudad.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusqCiudad, "No se ha seleccionado ninguna fila","Búsqueda Ciudad - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusqCiudad.jtbDatosCiudad.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmBusqCiudad.dispose();
                ControladorCiudad.frmCiudadControl.txtCodCiudad.setText(codigo);
                if (String.valueOf(ControladorCiudad.frmCiudadControl.txtCodCiudad.getText()) != "") {
                    ControladorCiudad.frmCiudadControl.btnVldCiudad.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
