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
import modelo.RepuestoDAO;
import modelo.RepuestoBD;
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
public class ControladorRepuesto implements ActionListener {

    private RepuestoBD reuRepuestoBD;//Objeto del tipo RepuestoBD
    private RepuestoDAO reuRepuestoDAO;//Objeto del tipo RepuestoDAO
    public static frmRepuesto frmRepuestoControl;//Objeto del tipo frmRepuesto
    byte flgAct = 0;

    public ControladorRepuesto(RepuestoBD reuRepuestoBD, RepuestoDAO reuRepuestoDAO, frmRepuesto frmRepuestoControl) {
        this.reuRepuestoBD = reuRepuestoBD;
        this.reuRepuestoDAO = reuRepuestoDAO;
        this.frmRepuestoControl = frmRepuestoControl;
        this.frmRepuestoControl.btnNuevo.addActionListener(this);
        this.frmRepuestoControl.btnEditar.addActionListener(this);
        this.frmRepuestoControl.btnBuscar.addActionListener(this);
        this.frmRepuestoControl.btnGuardar.addActionListener(this);
        this.frmRepuestoControl.btnCargar.addActionListener(this);
        this.frmRepuestoControl.btnCerrar.addActionListener(this);
        this.frmRepuestoControl.btnValRepu.addActionListener(this);
        this.frmRepuestoControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmRepuestoControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmRepuestoControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmRepuestoControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmRepuestoControl.btnGuardar) {//Valida origen del evento
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmRepuestoControl.txtNomRepu.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmRepuestoControl, "Datos incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmRepuestoControl.txtNomRepu.requestFocusInWindow();
            } else {
                Guardar();
            }

        }

        //Pulsar boton Cargar
        if (e.getSource() == frmRepuestoControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmRepuestoControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmRepuestoControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmRepuestoControl.btnValRepu) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmRepuestoControl.txtCodRepu.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmRepuestoControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmRepuestoControl.txtCodRepu.requestFocusInWindow();
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
            reuRepuestoBD.setCodRepto(frmRepuestoControl.txtCodRepu.getText());//Pasa los parametros al objeto
            reuRepuestoBD.setTipoRepto(String.valueOf(frmRepuestoControl.cboTipoRepu.getSelectedItem()));
            reuRepuestoBD.setNomRepto(frmRepuestoControl.txtNomRepu.getText());
            reuRepuestoBD.setDesRepto(frmRepuestoControl.txtDesRepu.getText());
            reuRepuestoBD.setEstRepto(TraerEstadoRepuesto());
            reuRepuestoBD.setCantRepto(frmRepuestoControl.txtCantRepu.getText());
            reuRepuestoBD.setObsRepto(frmRepuestoControl.txaObsRepu.getText());
            try {
                if (reuRepuestoDAO.GuardarRepto(reuRepuestoBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmRepuestoControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                    frmRepuestoControl.jtbDataRepu.setModel(reuRepuestoDAO.VerRepto());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmRepuestoControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuRepuestoBD.setCodRepto(frmRepuestoControl.txtCodRepu.getText());//Pasa los parametros al objeto
            reuRepuestoBD.setTipoRepto(String.valueOf(frmRepuestoControl.cboTipoRepu.getSelectedItem()));
            reuRepuestoBD.setNomRepto(frmRepuestoControl.txtNomRepu.getText());
            reuRepuestoBD.setDesRepto(frmRepuestoControl.txtDesRepu.getText());
            reuRepuestoBD.setEstRepto(TraerEstadoRepuesto());
            reuRepuestoBD.setCantRepto(frmRepuestoControl.txtCantRepu.getText());
            reuRepuestoBD.setObsRepto(frmRepuestoControl.txaObsRepu.getText());
            try {
                if (reuRepuestoDAO.EditarRepto(reuRepuestoBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmRepuestoControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                    frmRepuestoControl.jtbDataRepu.setModel(reuRepuestoDAO.VerRepto());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmRepuestoControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuRepuestoBD.setCodRepto(frmRepuestoControl.txtCodRepu.getText());
        try {
            if (reuRepuestoDAO.ValidaRepto(reuRepuestoBD)) {
                if (JOptionPane.showConfirmDialog(frmRepuestoControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmRepuestoControl.cboTipoRepu.setSelectedItem(String.valueOf(reuRepuestoBD.getTipoRepto()));
                    frmRepuestoControl.txtNomRepu.setText(String.valueOf(reuRepuestoBD.getNomRepto()));
                    frmRepuestoControl.txtDesRepu.setText(String.valueOf(reuRepuestoBD.getDesRepto()));
                    if (reuRepuestoBD.getEstRepto().equals("A")) {
                        frmRepuestoControl.rdbRepuAct.setSelected(true);
                    } else if (reuRepuestoBD.getEstRepto().equals("I")) {
                        frmRepuestoControl.rdbRepuIna.setSelected(true);
                    } else {
                        frmRepuestoControl.rdbRepuIna.setSelected(true);
                    }
                    frmRepuestoControl.txtCantRepu.setText(String.valueOf(reuRepuestoBD.getCantRepto()));
                    frmRepuestoControl.txaObsRepu.setText(String.valueOf(reuRepuestoBD.getObsRepto()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarRepuesto() {//Metodo para iniciar el form
        //Menú inicial
        MenuNuevo();
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmRepuestoControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmRepuestoControl.setResizable(false);
        frmRepuestoControl.setLocationRelativeTo(null);
        frmRepuestoControl.setTitle("Gestión Repuesto - MVC");
        frmRepuestoControl.setVisible(true);
        frmRepuestoControl.jtbDataRepu.setModel(reuRepuestoDAO.VerRepto());
        frmRepuestoControl.cboTipoRepu.addItem("  - Seleccione -  ");
        frmRepuestoControl.cboTipoRepu.addItem("MECANICO");
        frmRepuestoControl.cboTipoRepu.addItem("ELECTRICO");
        //Celdas no editables
        for (int col = 0; col < frmRepuestoControl.jtbDataRepu.getColumnCount(); col++) {
            Class<?> col_class = frmRepuestoControl.jtbDataRepu.getColumnClass(col);
            frmRepuestoControl.jtbDataRepu.setDefaultEditor(col_class, null); //Retira editor
        }
    }

    String TraerEstadoRepuesto() {//Metodo tipo String para el estado
        if (frmRepuestoControl.rdbRepuAct.isSelected()) {
            return "A";
        } else if (frmRepuestoControl.rdbRepuIna.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmRepuBusq AppFrmBusqRepuesto = new frmRepuBusq();
        RepuestoBD AppRepuestoBD = new RepuestoBD();//Instancio y creo un nuevo objeto
        RepuestoDAO AppRepuestoDAO = new RepuestoDAO();//Instancio y creo un nuevo objeto
        ControlRepuBusq AppBusqRepuesto = new ControlRepuBusq(AppRepuestoBD, AppRepuestoDAO, AppFrmBusqRepuesto);
        AppBusqRepuesto.IniciarRepuestoBusq();
        AppFrmBusqRepuesto.setVisible(true);//Invoco el metodo
        AppFrmBusqRepuesto.setDefaultCloseOperation(2); //Cierro sin parar la aplicacion
        //codigoB = AppBusqMarca.BusqOK();
//        AppfrmBusqMarca.dispose();

    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmRepuestoControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmRepuestoControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmRepuestoControl);
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

    public void LimpiarRepuesto() {//Metodo para limpiar los controles
        frmRepuestoControl.txtCodRepu.setText("");
        frmRepuestoControl.cboTipoRepu.setSelectedItem("  - Seleccione -  ");
        frmRepuestoControl.txtNomRepu.setText("");
        frmRepuestoControl.txtDesRepu.setText("");
        frmRepuestoControl.btgRepu.clearSelection();
        frmRepuestoControl.txtCantRepu.setText("");
        frmRepuestoControl.txaObsRepu.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmRepuestoControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar los controles
        flgAct = 0;
        frmRepuestoControl.btnBuscar.setEnabled(true);
        frmRepuestoControl.btnEditar.setEnabled(false);
        frmRepuestoControl.btnBorrar.setEnabled(false);
        frmRepuestoControl.btnGuardar.setEnabled(false);
        frmRepuestoControl.txtCodRepu.setText("");
        frmRepuestoControl.txtCodRepu.setEnabled(true);
        frmRepuestoControl.txtCodRepu.requestFocusInWindow();
        frmRepuestoControl.btnValRepu.setEnabled(true);
        frmRepuestoControl.cboTipoRepu.setSelectedItem("  - Seleccione -  ");
        frmRepuestoControl.cboTipoRepu.setEnabled(false);
        frmRepuestoControl.txtNomRepu.setText("");
        frmRepuestoControl.txtNomRepu.setEnabled(false);
        frmRepuestoControl.txtDesRepu.setText("");
        frmRepuestoControl.txtDesRepu.setEnabled(false);
        frmRepuestoControl.btgRepu.clearSelection();
        frmRepuestoControl.rdbRepuAct.setEnabled(false);
        frmRepuestoControl.rdbRepuIna.setEnabled(false);
        frmRepuestoControl.txtCantRepu.setText("");
        frmRepuestoControl.txtCantRepu.setEnabled(false);
        frmRepuestoControl.txaObsRepu.setText("");
        frmRepuestoControl.txaObsRepu.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmRepuestoControl.btnEditar.setEnabled(false);
        frmRepuestoControl.btnBorrar.setEnabled(false);
        frmRepuestoControl.btnCargar.setEnabled(false);
        frmRepuestoControl.btnGuardar.setEnabled(true);
        frmRepuestoControl.txtCodRepu.setEnabled(false);//Activo e Inactivo los controles
        frmRepuestoControl.btnValRepu.setEnabled(false);
        frmRepuestoControl.cboTipoRepu.setEnabled(true);
        frmRepuestoControl.txtNomRepu.setEnabled(true);
        frmRepuestoControl.txtDesRepu.setEnabled(true);
        frmRepuestoControl.rdbRepuAct.setEnabled(true);
        frmRepuestoControl.rdbRepuIna.setEnabled(true);
        frmRepuestoControl.txtCantRepu.setEnabled(true);
        frmRepuestoControl.txaObsRepu.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmRepuestoControl.btnEditar.setEnabled(true);
        frmRepuestoControl.btnBorrar.setEnabled(false);
        frmRepuestoControl.btnCargar.setEnabled(false);
        frmRepuestoControl.btnCancelar.setEnabled(false);
        frmRepuestoControl.btnBuscar.setEnabled(false);
        frmRepuestoControl.btnImprimir.setEnabled(false);
        frmRepuestoControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmRepuestoControl.btnNuevo.setEnabled(true);
        frmRepuestoControl.btnGuardar.setEnabled(true);
        frmRepuestoControl.btnEditar.setEnabled(false);
        frmRepuestoControl.btnBorrar.setEnabled(false);
        frmRepuestoControl.btnCargar.setEnabled(false);
        frmRepuestoControl.btnCancelar.setEnabled(false);
        frmRepuestoControl.btnBuscar.setEnabled(false);
        frmRepuestoControl.btnImprimir.setEnabled(false);
        frmRepuestoControl.txtCodRepu.setEnabled(false);
        frmRepuestoControl.btnValRepu.setEnabled(false);
        frmRepuestoControl.cboTipoRepu.setEnabled(true);
        frmRepuestoControl.cboTipoRepu.setSelectedItem("  - Seleccione -  ");
        frmRepuestoControl.txtNomRepu.setText("");
        frmRepuestoControl.txtNomRepu.setEnabled(true);
        frmRepuestoControl.txtDesRepu.setText("");
        frmRepuestoControl.txtDesRepu.setEnabled(true);
        frmRepuestoControl.txtCantRepu.setText("");
        frmRepuestoControl.txtCantRepu.setEnabled(true);
        frmRepuestoControl.btgRepu.clearSelection();
        frmRepuestoControl.rdbRepuAct.setEnabled(true);
        frmRepuestoControl.rdbRepuIna.setEnabled(true);
        frmRepuestoControl.txaObsRepu.setText("");
        frmRepuestoControl.txaObsRepu.setEnabled(true);

    }
}
