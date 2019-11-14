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
import modelo.CiudadDAO;
import modelo.CiudadBD;
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
public class ControladorCiudad implements ActionListener {

    private CiudadBD reuCiudadBD;//Objeto del tipo CiudadBD
    private CiudadDAO reuCiudadDAO;//Objeto del tipo CiudadDAO
    public static frmCiudad frmCiudadControl;//Objeto del tipo frmCiudad
    byte flgAct = 0;

    public ControladorCiudad(CiudadBD reuCiudadBD, CiudadDAO reuCiudadDAO, frmCiudad frmCiudadControl) {
        this.reuCiudadBD = reuCiudadBD;
        this.reuCiudadDAO = reuCiudadDAO;
        this.frmCiudadControl = frmCiudadControl;
        this.frmCiudadControl.btnNuevo.addActionListener(this);
        this.frmCiudadControl.btnEditar.addActionListener(this);
        this.frmCiudadControl.btnBuscar.addActionListener(this);
        this.frmCiudadControl.btnGuardar.addActionListener(this);
        this.frmCiudadControl.btnCargar.addActionListener(this);
        this.frmCiudadControl.btnCerrar.addActionListener(this);
        this.frmCiudadControl.btnVldCiudad.addActionListener(this);
        this.frmCiudadControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmCiudadControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmCiudadControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmCiudadControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmCiudadControl.btnGuardar) {//Valida origen del evento
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmCiudadControl.txtNomCiudad.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmCiudadControl, "Datos incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCiudadControl.txtNomCiudad.requestFocusInWindow();
            } else {
                Guardar();
            }
            
        }

        //Pulsar boton Cargar
        if (e.getSource() == frmCiudadControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmCiudadControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmCiudadControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmCiudadControl.btnVldCiudad) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmCiudadControl.txtCodCiudad.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmCiudadControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCiudadControl.txtCodCiudad.requestFocusInWindow();
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
            reuCiudadBD.setCodCiudad(frmCiudadControl.txtCodCiudad.getText());//Pasa los parametros al objeto
            reuCiudadBD.setNomCiudad(frmCiudadControl.txtNomCiudad.getText());
            reuCiudadBD.setEstCiudad(TraerEstadoCiudad());
            reuCiudadBD.setObsCiudad(frmCiudadControl.txaObsCiudad.getText());
            try {
                if (reuCiudadDAO.GuardarCiudad(reuCiudadBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmCiudadControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                    frmCiudadControl.jtbDataCiudad.setModel(reuCiudadDAO.VerCiudad());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmCiudadControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuCiudadBD.setCodCiudad(frmCiudadControl.txtCodCiudad.getText());//Pasa los parametros al objeto
            reuCiudadBD.setNomCiudad(frmCiudadControl.txtNomCiudad.getText());
            reuCiudadBD.setEstCiudad(TraerEstadoCiudad());
            reuCiudadBD.setObsCiudad(frmCiudadControl.txaObsCiudad.getText());
            try {
                if (reuCiudadDAO.EditarCiudad(reuCiudadBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmCiudadControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                    frmCiudadControl.jtbDataCiudad.setModel(reuCiudadDAO.VerCiudad());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmCiudadControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuCiudadBD.setCodCiudad(frmCiudadControl.txtCodCiudad.getText());
        try {
            if (reuCiudadDAO.ValidaCiudad(reuCiudadBD)) {
                if (JOptionPane.showConfirmDialog(frmCiudadControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmCiudadControl.txtNomCiudad.setText(String.valueOf(reuCiudadBD.getNomCiudad()));
                    if (reuCiudadBD.getEstCiudad().equals("A")) {
                        frmCiudadControl.rdbActCiudad.setSelected(true);
                    } else if (reuCiudadBD.getEstCiudad().equals("I")) {
                        frmCiudadControl.rdbInaCiudad.setSelected(true);
                    } else {
                        frmCiudadControl.rdbInaCiudad.setSelected(true);
                    }
                    frmCiudadControl.txaObsCiudad.setText(String.valueOf(reuCiudadBD.getObsCiudad()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarCiudad() {//Metodo para iniciar el form
        //Menú inicial
        MenuNuevo();
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmCiudadControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmCiudadControl.setResizable(false);
        frmCiudadControl.setLocationRelativeTo(null);
        frmCiudadControl.setTitle("Gestión Marcas - MVC");
        frmCiudadControl.setVisible(true);
        frmCiudadControl.jtbDataCiudad.setModel(reuCiudadDAO.VerCiudad());
        //Celdas no editables
        for (int col = 0; col < frmCiudadControl.jtbDataCiudad.getColumnCount(); col++) {
            Class<?> col_class = frmCiudadControl.jtbDataCiudad.getColumnClass(col);
            frmCiudadControl.jtbDataCiudad.setDefaultEditor(col_class, null); //Retira editor
        }
    }

    String TraerEstadoCiudad() {//Metodo tipo String para el estado
        if (frmCiudadControl.rdbActCiudad.isSelected()) {
            return "A";
        } else if (frmCiudadControl.rdbInaCiudad.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmBusCiudad AppFrmBusqCiudad = new frmBusCiudad();
        CiudadBD AppCiudadBD = new CiudadBD();//Instancio y creo un nuevo objeto
        CiudadDAO AppCiudadDAO = new CiudadDAO();//Instancio y creo un nuevo objeto
        ControlCiudadBusq AppBusqCiudad = new ControlCiudadBusq(AppCiudadBD, AppCiudadDAO, AppFrmBusqCiudad);
        AppBusqCiudad.IniciarCiudadBusq();
        AppFrmBusqCiudad.setVisible(true);//Invoco el metodo
        AppFrmBusqCiudad.setDefaultCloseOperation(2);
        //codigoB = AppBusqMarca.BusqOK();
//        AppfrmBusqMarca.dispose();

    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmCiudadControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmCiudadControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmCiudadControl);
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

    public void LimpiarCiudad() {//Metodo para limpiar los controles
        frmCiudadControl.txtCodCiudad.setText("");
        frmCiudadControl.txtNomCiudad.setText("");
        frmCiudadControl.btgCiudad.clearSelection();
        frmCiudadControl.txaObsCiudad.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmCiudadControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar los controles
        flgAct = 0;
        frmCiudadControl.btnBuscar.setEnabled(true);
        frmCiudadControl.btnEditar.setEnabled(false);
        frmCiudadControl.btnBorrar.setEnabled(false);
        frmCiudadControl.btnGuardar.setEnabled(false);
        frmCiudadControl.txtCodCiudad.setText("");
        frmCiudadControl.txtCodCiudad.setEnabled(true);
        frmCiudadControl.txtCodCiudad.requestFocusInWindow();
        frmCiudadControl.btnVldCiudad.setEnabled(true);
        frmCiudadControl.txtNomCiudad.setText("");
        frmCiudadControl.txtNomCiudad.setEnabled(false);
        frmCiudadControl.btgCiudad.clearSelection();
        frmCiudadControl.rdbActCiudad.setEnabled(false);
        frmCiudadControl.rdbInaCiudad.setEnabled(false);
        frmCiudadControl.txaObsCiudad.setText("");
        frmCiudadControl.txaObsCiudad.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmCiudadControl.btnEditar.setEnabled(false);
        frmCiudadControl.btnBorrar.setEnabled(false);
        frmCiudadControl.btnCargar.setEnabled(false);
        frmCiudadControl.btnGuardar.setEnabled(true);
        frmCiudadControl.txtCodCiudad.setEnabled(false);//Activo e Inactivo los controles
        frmCiudadControl.btnVldCiudad.setEnabled(false);
        frmCiudadControl.txtNomCiudad.setEnabled(true);
        frmCiudadControl.rdbActCiudad.setEnabled(true);
        frmCiudadControl.rdbInaCiudad.setEnabled(true);
        frmCiudadControl.txaObsCiudad.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmCiudadControl.btnEditar.setEnabled(true);
        frmCiudadControl.btnBorrar.setEnabled(false);
        frmCiudadControl.btnCargar.setEnabled(false);
        frmCiudadControl.btnCancelar.setEnabled(false);
        frmCiudadControl.btnBuscar.setEnabled(false);
        frmCiudadControl.btnImprimir.setEnabled(false);
        frmCiudadControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmCiudadControl.btnNuevo.setEnabled(true);
        frmCiudadControl.btnGuardar.setEnabled(true);
        frmCiudadControl.btnEditar.setEnabled(false);
        frmCiudadControl.btnBorrar.setEnabled(false);
        frmCiudadControl.btnCargar.setEnabled(false);
        frmCiudadControl.btnCancelar.setEnabled(false);
        frmCiudadControl.btnBuscar.setEnabled(false);
        frmCiudadControl.btnImprimir.setEnabled(false);
        frmCiudadControl.txtCodCiudad.setEnabled(false);
        frmCiudadControl.btnVldCiudad.setEnabled(false);
        frmCiudadControl.txtNomCiudad.requestFocusInWindow();
        frmCiudadControl.txtNomCiudad.setText("");
        frmCiudadControl.txtNomCiudad.setEnabled(true);
        frmCiudadControl.btgCiudad.clearSelection();
        frmCiudadControl.rdbActCiudad.setEnabled(true);
        frmCiudadControl.rdbInaCiudad.setEnabled(true);
        frmCiudadControl.txaObsCiudad.setText("");
        frmCiudadControl.txaObsCiudad.setEnabled(true);

    }
}
