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
import modelo.TipoDAO;
import modelo.TipoBD;
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
public class ControladorTipo implements ActionListener {

    private TipoBD reuTipoBD;//Objeto del tipo TipoBD
    private TipoDAO reuTipoDAO;//Objeto del tipo TipoDAO
    public static frmTipo frmTipoControl;//Objeto del tipo frmTipo
    byte flgAct = 0;

    public ControladorTipo(TipoBD reuTipoBD, TipoDAO reuTipoDAO, frmTipo frmTipoControl) {
        this.reuTipoBD = reuTipoBD;
        this.reuTipoDAO = reuTipoDAO;
        this.frmTipoControl = frmTipoControl;
        this.frmTipoControl.btnNuevo.addActionListener(this);
        this.frmTipoControl.btnEditar.addActionListener(this);
        this.frmTipoControl.btnBuscar.addActionListener(this);
        this.frmTipoControl.btnGuardar.addActionListener(this);
        this.frmTipoControl.btnCargar.addActionListener(this);
        this.frmTipoControl.btnCerrar.addActionListener(this);
        this.frmTipoControl.btnValTipo.addActionListener(this);
        this.frmTipoControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmTipoControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmTipoControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmTipoControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmTipoControl.btnGuardar) {//Valida origen del evento
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmTipoControl.txtNomTipo.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmTipoControl, "Datos incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmTipoControl.txtNomTipo.requestFocusInWindow();
            } else {
                Guardar();
            }
            
        }

        //Pulsar boton Cargar
        if (e.getSource() == frmTipoControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmTipoControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmTipoControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmTipoControl.btnValTipo) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmTipoControl.txtCodTipo.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmTipoControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmTipoControl.txtCodTipo.requestFocusInWindow();
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
            vistaReport.setTitle("Reporte de Tipos - MVC");
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
            reuTipoBD.setCodTipo(frmTipoControl.txtCodTipo.getText());//Pasa los parametros al objeto
            reuTipoBD.setNomTipo(frmTipoControl.txtNomTipo.getText());
            reuTipoBD.setEstTipo(TraerEstadoTipo());
            reuTipoBD.setObsTipo(frmTipoControl.txaObsTipo.getText());
            try {
                if (reuTipoDAO.GuardarTipo(reuTipoBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmTipoControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                    frmTipoControl.jtbDataTipo.setModel(reuTipoDAO.VerTipo());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmTipoControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuTipoBD.setCodTipo(frmTipoControl.txtCodTipo.getText());//Pasa los parametros al objeto
            reuTipoBD.setNomTipo(frmTipoControl.txtNomTipo.getText());
            reuTipoBD.setEstTipo(TraerEstadoTipo());
            reuTipoBD.setObsTipo(frmTipoControl.txaObsTipo.getText());
            try {
                if (reuTipoDAO.EditarTipo(reuTipoBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmTipoControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                    frmTipoControl.jtbDataTipo.setModel(reuTipoDAO.VerTipo());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmTipoControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuTipoBD.setCodTipo(frmTipoControl.txtCodTipo.getText());
        try {
            if (reuTipoDAO.ValidaTipo(reuTipoBD)) {
                if (JOptionPane.showConfirmDialog(frmTipoControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmTipoControl.txtNomTipo.setText(String.valueOf(reuTipoBD.getNomTipo()));
                    if (reuTipoBD.getEstTipo().equals("A")) {
                        frmTipoControl.rdbTipoAct.setSelected(true);
                    } else if (reuTipoBD.getEstTipo().equals("I")) {
                        frmTipoControl.rdbTipoIna.setSelected(true);
                    } else {
                        frmTipoControl.rdbTipoIna.setSelected(true);
                    }
                    frmTipoControl.txaObsTipo.setText(String.valueOf(reuTipoBD.getObsTipo()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarTipo() {//Metodo para iniciar el form
        //Menú inicial
        MenuNuevo();
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmTipoControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmTipoControl.setResizable(false);
        frmTipoControl.setLocationRelativeTo(null);
        frmTipoControl.setTitle("Gestión Tipo - MVC");
        frmTipoControl.setVisible(true);
        frmTipoControl.jtbDataTipo.setModel(reuTipoDAO.VerTipo());
        //Celdas no editables
        for (int col = 0; col < frmTipoControl.jtbDataTipo.getColumnCount(); col++) {
            Class<?> col_class = frmTipoControl.jtbDataTipo.getColumnClass(col);
            frmTipoControl.jtbDataTipo.setDefaultEditor(col_class, null); //Retira editor
        }
    }

    String TraerEstadoTipo() {//Metodo tipo String para el estado
        if (frmTipoControl.rdbTipoAct.isSelected()) {
            return "A";
        } else if (frmTipoControl.rdbTipoIna.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmTipoBusq AppFrmBusqTipo = new frmTipoBusq();
        TipoBD AppTipoBD = new TipoBD();//Instancio y creo un nuevo objeto
        TipoDAO AppTipoDAO = new TipoDAO();//Instancio y creo un nuevo objeto
        ControlTipoBusq AppBusqTipo = new ControlTipoBusq(AppTipoBD, AppTipoDAO, AppFrmBusqTipo);
        AppBusqTipo.IniciarTipoBusq();
        AppFrmBusqTipo.setVisible(true);//Invoco el metodo
        AppFrmBusqTipo.setDefaultCloseOperation(2);
        //codigoB = AppBusqMarca.BusqOK();
//        AppfrmBusqMarca.dispose();

    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmTipoControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmTipoControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmTipoControl);
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

    public void LimpiarTipo() {//Metodo para limpiar los controles
        frmTipoControl.txtCodTipo.setText("");
        frmTipoControl.txtNomTipo.setText("");
        frmTipoControl.btgTipo.clearSelection();
        frmTipoControl.txaObsTipo.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmTipoControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar los controles
        flgAct = 0;
        frmTipoControl.btnBuscar.setEnabled(true);
        frmTipoControl.btnEditar.setEnabled(false);
        frmTipoControl.btnBorrar.setEnabled(false);
        frmTipoControl.btnGuardar.setEnabled(false);
        frmTipoControl.txtCodTipo.setText("");
        frmTipoControl.txtCodTipo.setEnabled(true);
        frmTipoControl.txtCodTipo.requestFocusInWindow();
        frmTipoControl.btnValTipo.setEnabled(true);
        frmTipoControl.txtNomTipo.setText("");
        frmTipoControl.txtNomTipo.setEnabled(false);
        frmTipoControl.btgTipo.clearSelection();
        frmTipoControl.rdbTipoAct.setEnabled(false);
        frmTipoControl.rdbTipoIna.setEnabled(false);
        frmTipoControl.txaObsTipo.setText("");
        frmTipoControl.txaObsTipo.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmTipoControl.btnEditar.setEnabled(false);
        frmTipoControl.btnBorrar.setEnabled(false);
        frmTipoControl.btnCargar.setEnabled(false);
        frmTipoControl.btnGuardar.setEnabled(true);
        frmTipoControl.txtCodTipo.setEnabled(false);//Activo e Inactivo los controles
        frmTipoControl.btnValTipo.setEnabled(false);
        frmTipoControl.txtNomTipo.setEnabled(true);
        frmTipoControl.rdbTipoAct.setEnabled(true);
        frmTipoControl.rdbTipoIna.setEnabled(true);
        frmTipoControl.txaObsTipo.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmTipoControl.btnEditar.setEnabled(true);
        frmTipoControl.btnBorrar.setEnabled(false);
        frmTipoControl.btnCargar.setEnabled(false);
        frmTipoControl.btnCancelar.setEnabled(false);
        frmTipoControl.btnBuscar.setEnabled(false);
        frmTipoControl.btnImprimir.setEnabled(false);
        frmTipoControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmTipoControl.btnNuevo.setEnabled(true);
        frmTipoControl.btnGuardar.setEnabled(true);
        frmTipoControl.btnEditar.setEnabled(false);
        frmTipoControl.btnBorrar.setEnabled(false);
        frmTipoControl.btnCargar.setEnabled(false);
        frmTipoControl.btnCancelar.setEnabled(false);
        frmTipoControl.btnBuscar.setEnabled(false);
        frmTipoControl.btnImprimir.setEnabled(false);
        frmTipoControl.txtCodTipo.setEnabled(false);
        frmTipoControl.btnValTipo.setEnabled(false);
        frmTipoControl.txtNomTipo.requestFocusInWindow();
        frmTipoControl.txtNomTipo.setText("");
        frmTipoControl.txtNomTipo.setEnabled(true);
        frmTipoControl.btgTipo.clearSelection();
        frmTipoControl.rdbTipoAct.setEnabled(true);
        frmTipoControl.rdbTipoIna.setEnabled(true);
        frmTipoControl.txaObsTipo.setText("");
        frmTipoControl.txaObsTipo.setEnabled(true);

    }
}
