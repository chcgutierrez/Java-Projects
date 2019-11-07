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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.*;
import vista.*;

/**
 *
 * @author Chris
 */
public class ControlBusqMarca implements ActionListener {

    private MarcaEnt reuMarcaEnt;//Objeto del tipo MarcaEnt
    private MarcaDAO reuMarcaDAO;//Objeto del tipo MarcaDAO
    private frmBsqMarca frmBusqueda;
    public static String codigo = "";
    private MouseListener l;

    public ControlBusqMarca(MarcaEnt reuMarcaEnt, MarcaDAO reuMarcaDAO, frmBsqMarca frmBusqueda) {
        this.reuMarcaEnt = reuMarcaEnt;
        this.reuMarcaDAO = reuMarcaDAO;
        this.frmBusqueda = frmBusqueda;
        this.frmBusqueda.btnBusqMarca.addActionListener(this);
        this.frmBusqueda.btnBusqOK.addActionListener(this);
        frmBusqueda.jtbDatosMarcas.addMouseListener(l);

    }

    public void IniciarBusqMarca() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_search_16px.png"));
        Image Image = formaIcon.getImage();
        frmBusqueda.setIconImage(Image);
        //Iniciar propiedades del form
        frmBusqueda.setResizable(false);
        frmBusqueda.setLocationRelativeTo(null);
        frmBusqueda.setTitle("Búsqueda Marcas - MVC");
//        frmBusqueda.set
        frmBusqueda.setVisible(true);
        frmBusqueda.jtbDatosMarcas.setModel(reuMarcaDAO.BusqMarca(reuMarcaEnt));
        TableColumn colA = frmBusqueda.jtbDatosMarcas.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        //Celdas no editables
        for (int c = 0; c < frmBusqueda.jtbDatosMarcas.getColumnCount(); c++) {
            Class<?> col_class = frmBusqueda.jtbDatosMarcas.getColumnClass(c);
            frmBusqueda.jtbDatosMarcas.setDefaultEditor(col_class, null); //Retirar editor
        }
    }

    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Buscar
        if (e.getSource() == frmBusqueda.btnBusqMarca) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
            if (frmBusqueda.txtDescMarca.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmBusqueda, "Debe ingresar un criterio", "Búsqueda Marcas - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmBusqueda.txtDescMarca.requestFocusInWindow();
            } else {
                Busqueda();
                TableColumn colA = frmBusqueda.jtbDatosMarcas.getColumnModel().getColumn(0);
                colA.setPreferredWidth(90);
                colA.setMaxWidth(90);
                colA.setMinWidth(90);
            }
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmBusqueda.btnBusqOK) {
            BusqOK();
        }
    }

    //Metodo para Buscar X Descripcion
    public void Busqueda() {

        reuMarcaEnt.setNom_marca(frmBusqueda.txtDescMarca.getText());
        try {
            frmBusqueda.jtbDatosMarcas.setModel(reuMarcaDAO.BusqMarca(reuMarcaEnt));
            frmBusqueda.jtbDatosMarcas.setEnabled(true);
        } catch (HeadlessException hexc) {
            System.out.println(hexc);
        }
    }

    public void BusqOK() {
        int filaSel;

        try {
            filaSel = frmBusqueda.jtbDatosMarcas.getSelectedRow();
            if (filaSel == -1) {
                Icon Alerta = new ImageIcon(getClass().getResource("/img/icons8_attention_32px.png"));
                JOptionPane.showMessageDialog(frmBusqueda, "No se ha seleccionado ninguna fila","Búsqueda Marcas - MVC", JOptionPane.ERROR_MESSAGE, Alerta);
            } else {
                DefaultTableModel modTabla = (DefaultTableModel) frmBusqueda.jtbDatosMarcas.getModel();
                String codigo = modTabla.getValueAt(filaSel, 0).toString();
                frmBusqueda.dispose();
                ControladorMarca.frmMarcaControl.txtCodMarca.setText(codigo);
                if (String.valueOf(ControladorMarca.frmMarcaControl.txtCodMarca.getText()) != "") {
                    ControladorMarca.frmMarcaControl.btnVldMarca.doClick();
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }

}
