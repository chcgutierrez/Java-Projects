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
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.*;
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
public class ControladorEmpleado implements ActionListener {

    private EmpleadoBD reuEmpleBD;//Objeto del tipo EmpleBD
    private EmpleadoDAO reuEmpleDAO;//Objeto del tipo EmpleDAO
    public static frmMecanico frmEmpleControl;//Objeto del tipo frmEmple
    byte flgAct = 0;

    public ControladorEmpleado(EmpleadoBD reuEmpleBD, EmpleadoDAO reuEmpleDAO, frmMecanico frmEmpleControl) {
        this.reuEmpleBD = reuEmpleBD;
        this.reuEmpleDAO = reuEmpleDAO;
        this.frmEmpleControl = frmEmpleControl;
        this.frmEmpleControl.btnNuevo.addActionListener(this);
        this.frmEmpleControl.btnEditar.addActionListener(this);
        this.frmEmpleControl.btnBuscar.addActionListener(this);
        this.frmEmpleControl.btnGuardar.addActionListener(this);
        this.frmEmpleControl.btnCargar.addActionListener(this);
        this.frmEmpleControl.btnCerrar.addActionListener(this);
        this.frmEmpleControl.btnValEmple.addActionListener(this);
        this.frmEmpleControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmEmpleControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmEmpleControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmEmpleControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmEmpleControl.btnGuardar) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            String strDocu = frmEmpleControl.cboDocEmp.getSelectedItem().toString();
            String strCiudad = frmEmpleControl.cboCiuEmp.getSelectedItem().toString();

            if (frmEmpleControl.txtNomEmp.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmEmpleControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmEmpleControl.txtNomEmp.requestFocusInWindow();
            } else if (strDocu.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmEmpleControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmEmpleControl.cboDocEmp.requestFocusInWindow();

            } else if (strCiudad.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmEmpleControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmEmpleControl.cboCiuEmp.requestFocusInWindow();

            } else if (frmEmpleControl.txtNumDocEmp.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmEmpleControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmEmpleControl.txtNumDocEmp.requestFocusInWindow();

            } else {
                Guardar();
            }

        }

        //Pulsar boton Cargar
        if (e.getSource() == frmEmpleControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmEmpleControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmEmpleControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmEmpleControl.btnValEmple) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmEmpleControl.txtCodEmpleado.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmEmpleControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmEmpleControl.txtCodEmpleado.requestFocusInWindow();
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

        if (flgAct == 0) { //Pasa los parametros al objeto
            reuEmpleBD.setTipdoEmple(String.valueOf(frmEmpleControl.cboDocEmp.getSelectedIndex()));
            reuEmpleBD.setNumdoEmple(frmEmpleControl.txtNumDocEmp.getText());
            reuEmpleBD.setNomEmple(frmEmpleControl.txtNomEmp.getText());
            reuEmpleBD.setApeEmple(frmEmpleControl.txtApeEmp.getText());
            reuEmpleBD.setSexEmple(TraerSexoEmple());
            reuEmpleBD.setEstEmple(TraerEstEmple());
            reuEmpleBD.setCiuEmple(String.valueOf(frmEmpleControl.cboCiuEmp.getSelectedIndex()));
            reuEmpleBD.setObsEmple(frmEmpleControl.txaObsEmp.getText());
            try {
                if (reuEmpleDAO.GuardarEmple(reuEmpleBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmEmpleControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                } else {
                    JOptionPane.showMessageDialog(frmEmpleControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuEmpleBD.setNumdoEmple(frmEmpleControl.txtNumDocEmp.getText());
            reuEmpleBD.setTipdoEmple(String.valueOf(frmEmpleControl.cboDocEmp.getSelectedIndex()));
            reuEmpleBD.setNomEmple(frmEmpleControl.txtNomEmp.getText());
            reuEmpleBD.setApeEmple(frmEmpleControl.txtApeEmp.getText());
            reuEmpleBD.setSexEmple(TraerSexoEmple());
            reuEmpleBD.setEstEmple(TraerEstEmple());
            reuEmpleBD.setCiuEmple(String.valueOf(frmEmpleControl.cboCiuEmp.getSelectedIndex()));
            reuEmpleBD.setObsEmple(frmEmpleControl.txaObsEmp.getText());
            try {
                if (reuEmpleDAO.EditarEmple(reuEmpleBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmEmpleControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                } else {
                    JOptionPane.showMessageDialog(frmEmpleControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuEmpleBD.setNumdoEmple(frmEmpleControl.txtCodEmpleado.getText());
        try {
            if (reuEmpleDAO.ValidarEmple(reuEmpleBD)) {
                if (JOptionPane.showConfirmDialog(frmEmpleControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmEmpleControl.cboDocEmp.setSelectedIndex(Integer.valueOf(reuEmpleBD.getTipdoEmple()));
                    frmEmpleControl.txtNumDocEmp.setText(String.valueOf(reuEmpleBD.getNumdoEmple()));
                    frmEmpleControl.txtNomEmp.setText(String.valueOf(reuEmpleBD.getNomEmple()));
                    frmEmpleControl.txtApeEmp.setText(String.valueOf(reuEmpleBD.getApeEmple()));

                    if (reuEmpleBD.getSexEmple().equals("M")) {
                        frmEmpleControl.rdbSexM.setSelected(true);
                    } else if (reuEmpleBD.getSexEmple().equals("F")) {
                        frmEmpleControl.rdbSexF.setSelected(true);
                    } else {
                        frmEmpleControl.btgSexo.clearSelection();
                    }

                    if (reuEmpleBD.getEstEmple().equals("A")) {
                        frmEmpleControl.rdbEstA.setSelected(true);
                    } else if (reuEmpleBD.getEstEmple().equals("I")) {
                        frmEmpleControl.rdbEstI.setSelected(true);
                    } else {
                        frmEmpleControl.btgEst.clearSelection();
                    }

                    frmEmpleControl.cboCiuEmp.setSelectedIndex(Integer.valueOf(reuEmpleBD.getCiuEmple()));
                    frmEmpleControl.txaObsEmp.setText(String.valueOf(reuEmpleBD.getObsEmple()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarEmple() {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmEmpleControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmEmpleControl.setResizable(false);
        frmEmpleControl.setLocationRelativeTo(null);
        frmEmpleControl.setTitle("Gestión Emple - MVC");
        frmEmpleControl.setVisible(true);
        reuEmpleDAO.CargaTipoDoc(frmEmpleControl.cboDocEmp); //Cargo Combo Documento
        reuEmpleDAO.CargaCiudad(frmEmpleControl.cboCiuEmp); //Cargo Combo Ciudad
        //Menú inicial
        MenuNuevo();
    }

    String TraerSexoEmple() {//Metodo tipo String para el Estado y Sexo
        if (frmEmpleControl.rdbSexM.isSelected()) {
            return "M";
        } else if (frmEmpleControl.rdbSexF.isSelected()) {
            return "F";
        } else {
            return null;
        }
    }

    String TraerEstEmple() {//Metodo tipo String para el Estado y Sexo
        if (frmEmpleControl.rdbEstA.isSelected()) {
            return "A";
        } else if (frmEmpleControl.rdbEstI.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmBusEmpleado AppFrmBusqEmple = new frmBusEmpleado();
        EmpleadoBD AppEmpleBD = new EmpleadoBD();//Instancio y creo un nuevo objeto
        EmpleadoDAO AppEmpleDAO = new EmpleadoDAO();//Instancio y creo un nuevo objeto
        ControlBusEmpleado AppBusEmple = new ControlBusEmpleado(AppEmpleBD, AppEmpleDAO, AppFrmBusqEmple);
        AppBusEmple.IniciarEmpleBus();
        AppFrmBusqEmple.setVisible(true);//Invoco el metodo
        AppFrmBusqEmple.setDefaultCloseOperation(2); //Cierro sin parar la aplicacion
    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmEmpleControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmEmpleControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmEmpleControl);
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

    public void LimpiarEmpleado() {//Metodo para limpiar los controles
        frmEmpleControl.txtCodEmpleado.setText("");
        frmEmpleControl.cboDocEmp.setSelectedIndex(0);
        frmEmpleControl.txtNumDocEmp.setText("");
        frmEmpleControl.txtNomEmp.setText("");
        frmEmpleControl.txtApeEmp.setText("");
        frmEmpleControl.btgSexo.clearSelection();
        frmEmpleControl.btgEst.clearSelection();
        frmEmpleControl.cboCiuEmp.setSelectedIndex(0);
        frmEmpleControl.txaObsEmp.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmEmpleControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar y apagar los controles
        flgAct = 0;
        frmEmpleControl.btnBuscar.setEnabled(true);
        frmEmpleControl.btnEditar.setEnabled(false);
        frmEmpleControl.btnBorrar.setEnabled(false);
        frmEmpleControl.btnGuardar.setEnabled(false);
        frmEmpleControl.txtCodEmpleado.setText("");
        frmEmpleControl.txtCodEmpleado.setEnabled(true);
        frmEmpleControl.txtCodEmpleado.requestFocusInWindow();
        frmEmpleControl.btnValEmple.setEnabled(true);
        frmEmpleControl.cboDocEmp.setSelectedIndex(0);
        frmEmpleControl.cboDocEmp.setEnabled(false);
        frmEmpleControl.txtNumDocEmp.setText("");
        frmEmpleControl.txtNumDocEmp.setEnabled(false);
        frmEmpleControl.txtNomEmp.setText("");
        frmEmpleControl.txtNomEmp.setEnabled(false);
        frmEmpleControl.txtApeEmp.setText("");
        frmEmpleControl.txtApeEmp.setEnabled(false);
        frmEmpleControl.btgSexo.clearSelection();
        frmEmpleControl.rdbSexM.setEnabled(false);
        frmEmpleControl.rdbSexF.setEnabled(false);
        frmEmpleControl.btgEst.clearSelection();
        frmEmpleControl.rdbEstA.setEnabled(false);
        frmEmpleControl.rdbEstI.setEnabled(false);
        frmEmpleControl.cboCiuEmp.setSelectedIndex(0);
        frmEmpleControl.cboCiuEmp.setEnabled(false);
        frmEmpleControl.txaObsEmp.setText("");
        frmEmpleControl.txaObsEmp.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmEmpleControl.btnEditar.setEnabled(false);
        frmEmpleControl.btnBorrar.setEnabled(false);
        frmEmpleControl.btnCargar.setEnabled(false);
        frmEmpleControl.btnGuardar.setEnabled(true);
        frmEmpleControl.txtCodEmpleado.setEnabled(false);//Activo e Inactivo los controles
        frmEmpleControl.btnValEmple.setEnabled(false);
        frmEmpleControl.cboDocEmp.setEnabled(true);
        frmEmpleControl.txtNumDocEmp.setEnabled(true);
        frmEmpleControl.txtNomEmp.setEnabled(true);
        frmEmpleControl.txtApeEmp.setEnabled(true);
        frmEmpleControl.rdbSexM.setEnabled(true);
        frmEmpleControl.rdbSexF.setEnabled(true);
        frmEmpleControl.rdbEstA.setEnabled(true);
        frmEmpleControl.rdbEstI.setEnabled(true);
        frmEmpleControl.cboCiuEmp.setEnabled(true);
        frmEmpleControl.txaObsEmp.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmEmpleControl.btnEditar.setEnabled(true);
        frmEmpleControl.btnBorrar.setEnabled(false);
        frmEmpleControl.btnCargar.setEnabled(false);
        frmEmpleControl.btnCancelar.setEnabled(false);
        frmEmpleControl.btnBuscar.setEnabled(false);
        frmEmpleControl.btnImprimir.setEnabled(false);
        frmEmpleControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmEmpleControl.btnNuevo.setEnabled(true);
        frmEmpleControl.btnGuardar.setEnabled(true);
        frmEmpleControl.btnEditar.setEnabled(false);
        frmEmpleControl.btnBorrar.setEnabled(false);
        frmEmpleControl.btnCargar.setEnabled(false);
        frmEmpleControl.btnCancelar.setEnabled(false);
        frmEmpleControl.btnBuscar.setEnabled(false);
        frmEmpleControl.btnImprimir.setEnabled(false);
        frmEmpleControl.txtCodEmpleado.setEnabled(false);
        frmEmpleControl.btnValEmple.setEnabled(false);
        frmEmpleControl.cboDocEmp.setEnabled(true);
        frmEmpleControl.cboDocEmp.setSelectedIndex(0);
        frmEmpleControl.txtNumDocEmp.setText("");
        frmEmpleControl.txtNumDocEmp.setEnabled(true);
        frmEmpleControl.txtNomEmp.setText("");
        frmEmpleControl.txtNomEmp.setEnabled(true);
        frmEmpleControl.txtApeEmp.setText("");
        frmEmpleControl.txtApeEmp.setEnabled(true);
        frmEmpleControl.btgSexo.clearSelection();
        frmEmpleControl.rdbSexM.setEnabled(true);
        frmEmpleControl.rdbSexF.setEnabled(true);
        frmEmpleControl.btgEst.clearSelection();
        frmEmpleControl.rdbEstA.setEnabled(true);
        frmEmpleControl.rdbEstI.setEnabled(true);
        frmEmpleControl.cboCiuEmp.setEnabled(true);
        frmEmpleControl.cboCiuEmp.setSelectedIndex(0);
        frmEmpleControl.txaObsEmp.setText("");
        frmEmpleControl.txaObsEmp.setEnabled(true);

    }
}
