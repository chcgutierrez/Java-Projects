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
public class ControlTipoBusq implements ActionListener {

    private TipoBD reuTipoBD;//Objeto del tipo TipoBD
    private TipoDAO reuTipoDAO;//Objeto del tipo TipoDAO
    private frmTipoBusq frmBusqTipo;
    public static String codigo = "";
    private MouseListener l;

    public ControlTipoBusq(TipoBD reuTipoBD, TipoDAO reuTipoDAO, frmTipoBusq frmBusqTipo) {
        this.reuTipoBD = reuTipoBD;
        this.reuTipoDAO = reuTipoDAO;
        this.frmBusqTipo = frmBusqTipo;
        this.frmBusqTipo.btnTipoBusq.addActionListener(this);
        this.frmBusqTipo.btnTipoOK.addActionListener(this);
        frmBusqTipo.jtbTipoDatos.addMouseListener(l);

    }

    public void IniciarTipoBusq() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusqTipo.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusqTipo.setResizable(false);
        frmBusqTipo.setLocationRelativeTo(null);
        frmBusqTipo.setTitle("Búsqueda Tipo - MVC");
//        frmBusqTipo.set
        frmBusqTipo.setVisible(true);
        frmBusqTipo.jtbTipoDatos.setModel(reuTipoDAO.BusqTipo(reuTipoBD));
        TableColumn colA = frmBusqTipo.jtbTipoDatos.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusqTipo.jtbTipoDatos.getColumnCount(); c++) {
            Class<?> col_class = frmBusqTipo.jtbTipoDatos.getColumnClass(c);
            frmBusqTipo.jtbTipoDatos.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusqTipo.btnTipoBusq) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusqTipo.txtDescTipo.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusqTipo, "Debe ingresar un criterio", "Búsqueda Tipo - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusqTipo.txtDescTipo.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusqTipo.jtbTipoDatos.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusqTipo.btnTipoOK) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuTipoBD.setNomTipo(frmBusqTipo.txtDescTipo.getText());
        try {
            frmBusqTipo.jtbTipoDatos.setModel(reuTipoDAO.BusqTipo(reuTipoBD));
            frmBusqTipo.jtbTipoDatos.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusqTipo.jtbTipoDatos.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusqTipo, "No se ha seleccionado ninguna fila","Búsqueda Tipo - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusqTipo.jtbTipoDatos.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmBusqTipo.dispose();
                ControladorTipo.frmTipoControl.txtCodTipo.setText(codigo);
                if (String.valueOf(ControladorTipo.frmTipoControl.txtCodTipo.getText()) != "") {
                    ControladorTipo.frmTipoControl.btnValTipo.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
