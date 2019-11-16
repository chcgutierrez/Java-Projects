/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.*;
import modelo.ColorDAO;
import modelo.ColorBD;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import vista.*;

/**
 *
 * @author Chris
 */
public class ControladorColor implements ActionListener {

    private ColorBD reuColorBD;//Objeto del tipo ColorBD
    private ColorDAO reuColorDAO;//Objeto del tipo ColorDAO
    public static frmColor frmColorControl;//Objeto del tipo frmColor
    byte flgAct = 0;

    public ControladorColor(ColorBD reuColorBD, ColorDAO reuColorDAO, frmColor frmColorControl) {
        this.reuColorBD = reuColorBD;
        this.reuColorDAO = reuColorDAO;
        this.frmColorControl = frmColorControl;
        this.frmColorControl.btnNuevo.addActionListener(this);
        this.frmColorControl.btnEditar.addActionListener(this);
        this.frmColorControl.btnBuscar.addActionListener(this);
        this.frmColorControl.btnGuardar.addActionListener(this);
        this.frmColorControl.btnCargar.addActionListener(this);
        this.frmColorControl.btnCerrar.addActionListener(this);
        this.frmColorControl.btnVldColor.addActionListener(this);
        this.frmColorControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmColorControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmColorControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmColorControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmColorControl.btnGuardar) {//Valida origen del evento
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmColorControl.txtNomColor.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmColorControl, "Datos incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmColorControl.txtNomColor.requestFocusInWindow();
            } else {
                Guardar();
            }
            
        }

        //Pulsar boton Cargar
        if (e.getSource() == frmColorControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmColorControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmColorControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmColorControl.btnVldColor) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmColorControl.txtCodColor.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmColorControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmColorControl.txtCodColor.requestFocusInWindow();
            } else {
                Validar();
            }
        }
    }

    //Instancia de conexion a BD para el controlador
    private Connection multiConexBD = new ConectaBD().conectarDB();

    public void Imprimir() {//Metodo para mostrar el reporte
        Map P = new HashMap();
        JasperReport objReport;
        JasperPrint objImpReport;

        try {
            objReport = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + //Ruta del proyecto
                    "\\src\\reportes\\rptMarcas.jrxml"); //Ruta del reporte
            objImpReport = JasperFillManager.fillReport(objReport, P, multiConexBD);
            JasperViewer vistaReport = new JasperViewer(objImpReport, false);
            vistaReport.setVisible(true);
            vistaReport.setResizable(false);
            vistaReport.setTitle("Reporte de Marcas - MVC");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Guardar() {//Metodo para Guardar y Editar
        //Iconos para los mensajes
        Icon Editar = new ImageIcon(getClass().getResource("/img/icons8_edit_file_32px.png"));
        Icon Guardar = new ImageIcon(getClass().getResource("/img/ok_32px.png"));
        Icon Error = new ImageIcon(getClass().getResource("/img/high_priority_32px.png"));

        if (flgAct == 0) {
            reuColorBD.setCodColor(frmColorControl.txtCodColor.getText());//Pasa los parametros al objeto
            reuColorBD.setNomColor(frmColorControl.txtNomColor.getText());
            reuColorBD.setEstColor(TraerEstadoColor());
            reuColorBD.setObsColor(frmColorControl.txaObsColor.getText());
            try {
                if (reuColorDAO.GuardarColor(reuColorBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmColorControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                    frmColorControl.jtbDataColor.setModel(reuColorDAO.VerColor());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmColorControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuColorBD.setCodColor(frmColorControl.txtCodColor.getText());//Pasa los parametros al objeto
            reuColorBD.setNomColor(frmColorControl.txtNomColor.getText());
            reuColorBD.setEstColor(TraerEstadoColor());
            reuColorBD.setObsColor(frmColorControl.txaObsColor.getText());
            try {
                if (reuColorDAO.EditarColor(reuColorBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmColorControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                    frmColorControl.jtbDataColor.setModel(reuColorDAO.VerColor());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmColorControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuColorBD.setCodColor(frmColorControl.txtCodColor.getText());
        try {
            if (reuColorDAO.ValidaColor(reuColorBD)) {
                if (JOptionPane.showConfirmDialog(frmColorControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmColorControl.txtNomColor.setText(String.valueOf(reuColorBD.getNomColor()));
                    if (reuColorBD.getEstColor().equals("A")) {
                        frmColorControl.rdbColorAct.setSelected(true);
                    } else if (reuColorBD.getEstColor().equals("I")) {
                        frmColorControl.rdbColorInac.setSelected(true);
                    } else {
                        frmColorControl.rdbColorInac.setSelected(true);
                    }
                    frmColorControl.txaObsColor.setText(String.valueOf(reuColorBD.getObsColor()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarColor() {//Metodo para iniciar el form
        //Menú inicial
        MenuNuevo();
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmColorControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmColorControl.setResizable(false);
        frmColorControl.setLocationRelativeTo(null);
        frmColorControl.setTitle("Gestión Color - MVC");
        frmColorControl.setVisible(true);
        frmColorControl.jtbDataColor.setModel(reuColorDAO.VerColor());
        //Celdas no editables
        for (int col = 0; col < frmColorControl.jtbDataColor.getColumnCount(); col++) {
            Class<?> col_class = frmColorControl.jtbDataColor.getColumnClass(col);
            frmColorControl.jtbDataColor.setDefaultEditor(col_class, null); //Retira editor
        }
    }

    String TraerEstadoColor() {//Metodo tipo String para el estado
        if (frmColorControl.rdbColorAct.isSelected()) {
            return "A";
        } else if (frmColorControl.rdbColorInac.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmColorBusq AppFrmBusqColor = new frmColorBusq();
        ColorBD AppColorBD = new ColorBD();//Instancio y creo un nuevo objeto
        ColorDAO AppColorDAO = new ColorDAO();//Instancio y creo un nuevo objeto
        ControlColorBusq AppBusqColor = new ControlColorBusq(AppColorBD, AppColorDAO, AppFrmBusqColor);
        AppBusqColor.IniciarColorBusq();
        AppFrmBusqColor.setVisible(true);//Invoco el metodo
        AppFrmBusqColor.setDefaultCloseOperation(2);
        //codigoB = AppBusqMarca.BusqOK();
//        AppfrmBusqMarca.dispose();

    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmColorControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmColorControl.dispose();
        }
    }

    public void ReadF() {
        ArchivoMarca plantilla = new ArchivoMarca();
        try {
            plantilla.LeerPlantilla();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void LeerPlantilla() {//Metodo para Cerrar
        JFileChooser jfcSeleccion = new JFileChooser();
        jfcSeleccion.setDialogTitle("Cargar archivo");
        jfcSeleccion.setApproveButtonText("Cargar");
        jfcSeleccion.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter arcFiltro = new FileNameExtensionFilter("Archivos txt", "txt");
        jfcSeleccion.setFileFilter(arcFiltro);
        int selEstado = jfcSeleccion.showOpenDialog(frmColorControl);
        if (selEstado == JFileChooser.APPROVE_OPTION) {
            try {
                String arcRuta = jfcSeleccion.getSelectedFile().getAbsolutePath();
                FileInputStream nArchivo = new FileInputStream(arcRuta);
                DataInputStream datEntrada = new DataInputStream(nArchivo);
                BufferedReader bfrLectura = new BufferedReader(new InputStreamReader(datEntrada));
                String arcLineas;
                while ((arcLineas = bfrLectura.readLine()) != null) {
                    System.out.println(arcLineas);
                }
                datEntrada.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
//            Cargar();
        }
//        FileNameExtensionFilter jfcArcFiltro = new FileNameExtensionFilter("CSV & TXT", "csv", "txt");
//        jfcVentana.setFileFilter(jfcArcFiltro);
    }

    public void LimpiarColor() {//Metodo para limpiar los controles
        frmColorControl.txtCodColor.setText("");
        frmColorControl.txtNomColor.setText("");
        frmColorControl.btgColor.clearSelection();
        frmColorControl.txaObsColor.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmColorControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar los controles
        flgAct = 0;
        frmColorControl.btnBuscar.setEnabled(true);
        frmColorControl.btnEditar.setEnabled(false);
        frmColorControl.btnBorrar.setEnabled(false);
        frmColorControl.btnGuardar.setEnabled(false);
        frmColorControl.txtCodColor.setText("");
        frmColorControl.txtCodColor.setEnabled(true);
        frmColorControl.txtCodColor.requestFocusInWindow();
        frmColorControl.btnVldColor.setEnabled(true);
        frmColorControl.txtNomColor.setText("");
        frmColorControl.txtNomColor.setEnabled(false);
        frmColorControl.btgColor.clearSelection();
        frmColorControl.rdbColorAct.setEnabled(false);
        frmColorControl.rdbColorInac.setEnabled(false);
        frmColorControl.txaObsColor.setText("");
        frmColorControl.txaObsColor.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmColorControl.btnEditar.setEnabled(false);
        frmColorControl.btnBorrar.setEnabled(false);
        frmColorControl.btnCargar.setEnabled(false);
        frmColorControl.btnGuardar.setEnabled(true);
        frmColorControl.txtCodColor.setEnabled(false);//Activo e Inactivo los controles
        frmColorControl.btnVldColor.setEnabled(false);
        frmColorControl.txtNomColor.setEnabled(true);
        frmColorControl.rdbColorAct.setEnabled(true);
        frmColorControl.rdbColorInac.setEnabled(true);
        frmColorControl.txaObsColor.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmColorControl.btnEditar.setEnabled(true);
        frmColorControl.btnBorrar.setEnabled(false);
        frmColorControl.btnCargar.setEnabled(false);
        frmColorControl.btnCancelar.setEnabled(false);
        frmColorControl.btnBuscar.setEnabled(false);
        frmColorControl.btnImprimir.setEnabled(false);
        frmColorControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmColorControl.btnNuevo.setEnabled(true);
        frmColorControl.btnGuardar.setEnabled(true);
        frmColorControl.btnEditar.setEnabled(false);
        frmColorControl.btnBorrar.setEnabled(false);
        frmColorControl.btnCargar.setEnabled(false);
        frmColorControl.btnCancelar.setEnabled(false);
        frmColorControl.btnBuscar.setEnabled(false);
        frmColorControl.btnImprimir.setEnabled(false);
        frmColorControl.txtCodColor.setEnabled(false);
        frmColorControl.btnVldColor.setEnabled(false);
        frmColorControl.txtNomColor.requestFocusInWindow();
        frmColorControl.txtNomColor.setText("");
        frmColorControl.txtNomColor.setEnabled(true);
        frmColorControl.btgColor.clearSelection();
        frmColorControl.rdbColorAct.setEnabled(true);
        frmColorControl.rdbColorInac.setEnabled(true);
        frmColorControl.txaObsColor.setText("");
        frmColorControl.txaObsColor.setEnabled(true);

    }
}
