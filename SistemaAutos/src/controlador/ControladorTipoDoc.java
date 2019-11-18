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
import modelo.TipoDocDAO;
import modelo.TipoDocBD;
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
public class ControladorTipoDoc implements ActionListener {

    private TipoDocBD reuTipoDocBD;//Objeto del tipo TipoDocBD
    private TipoDocDAO reuTipoDocDAO;//Objeto del tipo TipoDocDAO
    public static frmTipoDoc frmTipoDocControl;//Objeto del tipo frmTipoDoc
    byte flgAct = 0;

    public ControladorTipoDoc(TipoDocBD reuTipoDocBD, TipoDocDAO reuTipoDocDAO, frmTipoDoc frmTipoDocControl) {
        this.reuTipoDocBD = reuTipoDocBD;
        this.reuTipoDocDAO = reuTipoDocDAO;
        this.frmTipoDocControl = frmTipoDocControl;
        this.frmTipoDocControl.btnNuevo.addActionListener(this);
        this.frmTipoDocControl.btnEditar.addActionListener(this);
        this.frmTipoDocControl.btnBuscar.addActionListener(this);
        this.frmTipoDocControl.btnGuardar.addActionListener(this);
        this.frmTipoDocControl.btnCargar.addActionListener(this);
        this.frmTipoDocControl.btnCerrar.addActionListener(this);
        this.frmTipoDocControl.btnValTipoDoc.addActionListener(this);
        this.frmTipoDocControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmTipoDocControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmTipoDocControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmTipoDocControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmTipoDocControl.btnGuardar) {//Valida origen del evento
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmTipoDocControl.txtNomTipoDoc.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmTipoDocControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmTipoDocControl.txtNomTipoDoc.requestFocusInWindow();
            } else {
                Guardar();
            }
            
        }

        //Pulsar boton Cargar
        if (e.getSource() == frmTipoDocControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmTipoDocControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmTipoDocControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmTipoDocControl.btnValTipoDoc) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmTipoDocControl.txtCodTipoDoc.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmTipoDocControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmTipoDocControl.txtCodTipoDoc.requestFocusInWindow();
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
            reuTipoDocBD.setTipoDoc(frmTipoDocControl.txtCodTipoDoc.getText());//Pasa los parametros al objeto
            reuTipoDocBD.setDescTipoDoc(frmTipoDocControl.txtNomTipoDoc.getText());
            reuTipoDocBD.setEstTipoDoc(TraerEstadoTipo());
            reuTipoDocBD.setObsTipoDoc(frmTipoDocControl.txaObsTipoDoc.getText());
            try {
                if (reuTipoDocDAO.GuardarTipoDoc(reuTipoDocBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmTipoDocControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                    frmTipoDocControl.jtbDataTipoDoc.setModel(reuTipoDocDAO.VerTipoDoc());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmTipoDocControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuTipoDocBD.setTipoDoc(frmTipoDocControl.txtCodTipoDoc.getText());//Pasa los parametros al objeto
            reuTipoDocBD.setDescTipoDoc(frmTipoDocControl.txtNomTipoDoc.getText());
            reuTipoDocBD.setEstTipoDoc(TraerEstadoTipo());
            reuTipoDocBD.setObsTipoDoc(frmTipoDocControl.txaObsTipoDoc.getText());
            try {
                if (reuTipoDocDAO.EditarTipoDoc(reuTipoDocBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmTipoDocControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                    frmTipoDocControl.jtbDataTipoDoc.setModel(reuTipoDocDAO.VerTipoDoc());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmTipoDocControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuTipoDocBD.setTipoDoc(frmTipoDocControl.txtCodTipoDoc.getText());
        try {
            if (reuTipoDocDAO.ValidaTipoDoc(reuTipoDocBD)) {
                if (JOptionPane.showConfirmDialog(frmTipoDocControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmTipoDocControl.txtNomTipoDoc.setText(String.valueOf(reuTipoDocBD.getDescTipoDoc()));
                    if (reuTipoDocBD.getEstTipoDoc().equals("A")) {
                        frmTipoDocControl.rdbTipoDocAct.setSelected(true);
                    } else if (reuTipoDocBD.getEstTipoDoc().equals("I")) {
                        frmTipoDocControl.rdbTipoDocIna.setSelected(true);
                    } else {
                        frmTipoDocControl.rdbTipoDocIna.setSelected(true);
                    }
                    frmTipoDocControl.txaObsTipoDoc.setText(String.valueOf(reuTipoDocBD.getObsTipoDoc()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarTipoDoc() {//Metodo para iniciar el form
        //Menú inicial
        MenuNuevo();
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmTipoDocControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmTipoDocControl.setResizable(false);
        frmTipoDocControl.setLocationRelativeTo(null);
        frmTipoDocControl.setTitle("Gestión Tipo - MVC");
        frmTipoDocControl.setVisible(true);
        frmTipoDocControl.jtbDataTipoDoc.setModel(reuTipoDocDAO.VerTipoDoc());
        //Celdas no editables
        for (int col = 0; col < frmTipoDocControl.jtbDataTipoDoc.getColumnCount(); col++) {
            Class<?> col_class = frmTipoDocControl.jtbDataTipoDoc.getColumnClass(col);
            frmTipoDocControl.jtbDataTipoDoc.setDefaultEditor(col_class, null); //Retira editor
        }
    }

    String TraerEstadoTipo() {//Metodo tipo String para el estado
        if (frmTipoDocControl.rdbTipoDocAct.isSelected()) {
            return "A";
        } else if (frmTipoDocControl.rdbTipoDocIna.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmTipoDocBusq AppFrmBusqTipoDoc = new frmTipoDocBusq();
        TipoDocBD AppTipoDocBD = new TipoDocBD();//Instancio y creo un nuevo objeto
        TipoDocDAO AppTipoDocDAO = new TipoDocDAO();//Instancio y creo un nuevo objeto
        ControlTipoDocBusq AppBusqTipoDoc = new ControlTipoDocBusq(AppTipoDocBD, AppTipoDocDAO, AppFrmBusqTipoDoc);
        AppBusqTipoDoc.IniciarTipoDocBusq();
        AppFrmBusqTipoDoc.setVisible(true);//Invoco el metodo
        AppFrmBusqTipoDoc.setDefaultCloseOperation(2);
        //codigoB = AppBusqMarca.BusqOK();
//        AppfrmBusqMarca.dispose();

    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmTipoDocControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmTipoDocControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmTipoDocControl);
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
        frmTipoDocControl.txtCodTipoDoc.setText("");
        frmTipoDocControl.txtNomTipoDoc.setText("");
        frmTipoDocControl.btgTipo.clearSelection();
        frmTipoDocControl.txaObsTipoDoc.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmTipoDocControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar los controles
        flgAct = 0;
        frmTipoDocControl.btnBuscar.setEnabled(true);
        frmTipoDocControl.btnEditar.setEnabled(false);
        frmTipoDocControl.btnBorrar.setEnabled(false);
        frmTipoDocControl.btnGuardar.setEnabled(false);
        frmTipoDocControl.txtCodTipoDoc.setText("");
        frmTipoDocControl.txtCodTipoDoc.setEnabled(true);
        frmTipoDocControl.txtCodTipoDoc.requestFocusInWindow();
        frmTipoDocControl.btnValTipoDoc.setEnabled(true);
        frmTipoDocControl.txtNomTipoDoc.setText("");
        frmTipoDocControl.txtNomTipoDoc.setEnabled(false);
        frmTipoDocControl.btgTipo.clearSelection();
        frmTipoDocControl.rdbTipoDocAct.setEnabled(false);
        frmTipoDocControl.rdbTipoDocIna.setEnabled(false);
        frmTipoDocControl.txaObsTipoDoc.setText("");
        frmTipoDocControl.txaObsTipoDoc.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmTipoDocControl.btnEditar.setEnabled(false);
        frmTipoDocControl.btnBorrar.setEnabled(false);
        frmTipoDocControl.btnCargar.setEnabled(false);
        frmTipoDocControl.btnGuardar.setEnabled(true);
        frmTipoDocControl.txtCodTipoDoc.setEnabled(false);//Activo e Inactivo los controles
        frmTipoDocControl.btnValTipoDoc.setEnabled(false);
        frmTipoDocControl.txtNomTipoDoc.setEnabled(true);
        frmTipoDocControl.rdbTipoDocAct.setEnabled(true);
        frmTipoDocControl.rdbTipoDocIna.setEnabled(true);
        frmTipoDocControl.txaObsTipoDoc.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmTipoDocControl.btnEditar.setEnabled(true);
        frmTipoDocControl.btnBorrar.setEnabled(false);
        frmTipoDocControl.btnCargar.setEnabled(false);
        frmTipoDocControl.btnCancelar.setEnabled(false);
        frmTipoDocControl.btnBuscar.setEnabled(false);
        frmTipoDocControl.btnImprimir.setEnabled(false);
        frmTipoDocControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmTipoDocControl.btnNuevo.setEnabled(true);
        frmTipoDocControl.btnGuardar.setEnabled(true);
        frmTipoDocControl.btnEditar.setEnabled(false);
        frmTipoDocControl.btnBorrar.setEnabled(false);
        frmTipoDocControl.btnCargar.setEnabled(false);
        frmTipoDocControl.btnCancelar.setEnabled(false);
        frmTipoDocControl.btnBuscar.setEnabled(false);
        frmTipoDocControl.btnImprimir.setEnabled(false);
        frmTipoDocControl.txtCodTipoDoc.setEnabled(false);
        frmTipoDocControl.btnValTipoDoc.setEnabled(false);
        frmTipoDocControl.txtNomTipoDoc.requestFocusInWindow();
        frmTipoDocControl.txtNomTipoDoc.setText("");
        frmTipoDocControl.txtNomTipoDoc.setEnabled(true);
        frmTipoDocControl.btgTipo.clearSelection();
        frmTipoDocControl.rdbTipoDocAct.setEnabled(true);
        frmTipoDocControl.rdbTipoDocIna.setEnabled(true);
        frmTipoDocControl.txaObsTipoDoc.setText("");
        frmTipoDocControl.txaObsTipoDoc.setEnabled(true);

    }
}
