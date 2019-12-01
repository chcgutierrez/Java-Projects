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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modelo.*;
import modelo.ClienteDAO;
import modelo.ClienteBD;
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
public class ControladorCliente implements ActionListener {

    private ClienteBD reuClienteBD;//Objeto del tipo ClienteBD
    private ClienteDAO reuClienteDAO;//Objeto del tipo ClienteDAO
    public static frmCliente frmClienteControl;//Objeto del tipo frmCliente
    byte flgAct = 0;

    public ControladorCliente(ClienteBD reuClienteBD, ClienteDAO reuClienteDAO, frmCliente frmClienteControl) {
        this.reuClienteBD = reuClienteBD;
        this.reuClienteDAO = reuClienteDAO;
        this.frmClienteControl = frmClienteControl;
        this.frmClienteControl.btnNuevo.addActionListener(this);
        this.frmClienteControl.btnEditar.addActionListener(this);
        this.frmClienteControl.btnBuscar.addActionListener(this);
        this.frmClienteControl.btnGuardar.addActionListener(this);
        this.frmClienteControl.btnCargar.addActionListener(this);
        this.frmClienteControl.btnCerrar.addActionListener(this);
        this.frmClienteControl.btnValCliente.addActionListener(this);
        this.frmClienteControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmClienteControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmClienteControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmClienteControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmClienteControl.btnGuardar) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            String strDocu = frmClienteControl.cboDocCliente.getSelectedItem().toString();
            String strCiudad = frmClienteControl.cboCiuCliente.getSelectedItem().toString();

            if (frmClienteControl.txtNomCliente.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmClienteControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmClienteControl.txtNomCliente.requestFocusInWindow();
            } else if (strDocu.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmClienteControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmClienteControl.cboDocCliente.requestFocusInWindow();

            } else if (strCiudad.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmClienteControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmClienteControl.cboCiuCliente.requestFocusInWindow();

            } else if (frmClienteControl.txtNumDocCliente.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmClienteControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmClienteControl.txtNumDocCliente.requestFocusInWindow();

            } else {
                Guardar();
            }

        }

        //Pulsar boton Cargar
        if (e.getSource() == frmClienteControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmClienteControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmClienteControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmClienteControl.btnValCliente) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmClienteControl.txtCodCliente.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmClienteControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmClienteControl.txtCodCliente.requestFocusInWindow();
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
            reuClienteBD.setTipdoCliente(String.valueOf(frmClienteControl.cboDocCliente.getSelectedIndex()));
            reuClienteBD.setNumdoCliente(frmClienteControl.txtNumDocCliente.getText());
            reuClienteBD.setNomCliente(frmClienteControl.txtNomCliente.getText());
            reuClienteBD.setApeCliente(frmClienteControl.txtApeCliente.getText());
            reuClienteBD.setSexCliente(TraerSexoCliente());
            reuClienteBD.setDirCliente(frmClienteControl.txtDirCliente.getText());
            reuClienteBD.setTelCliente(frmClienteControl.txtTelCliente.getText());
            reuClienteBD.setMailCliente(frmClienteControl.txtMailCliente.getText());
            reuClienteBD.setEstCliente(TraerEstCliente());
            reuClienteBD.setFecnCliente(fechaBD(frmClienteControl.jftFecNac.getText()));
            reuClienteBD.setCiuCliente(String.valueOf(frmClienteControl.cboCiuCliente.getSelectedIndex()));
            reuClienteBD.setObsCliente(frmClienteControl.txaObsCliente.getText());
            try {
                if (reuClienteDAO.GuardarCliente(reuClienteBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmClienteControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                } else {
                    JOptionPane.showMessageDialog(frmClienteControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuClienteBD.setNumdoCliente(frmClienteControl.txtNumDocCliente.getText());
            reuClienteBD.setTipdoCliente(String.valueOf(frmClienteControl.cboDocCliente.getSelectedIndex()));
            reuClienteBD.setNomCliente(frmClienteControl.txtNomCliente.getText());
            reuClienteBD.setApeCliente(frmClienteControl.txtApeCliente.getText());
            reuClienteBD.setSexCliente(TraerSexoCliente());
            reuClienteBD.setDirCliente(frmClienteControl.txtDirCliente.getText());
            reuClienteBD.setTelCliente(frmClienteControl.txtTelCliente.getText());
            reuClienteBD.setMailCliente(frmClienteControl.txtMailCliente.getText());
            reuClienteBD.setEstCliente(TraerEstCliente());
            reuClienteBD.setFecnCliente(fechaBD(frmClienteControl.jftFecNac.getText()));
            reuClienteBD.setCiuCliente(String.valueOf(frmClienteControl.cboCiuCliente.getSelectedIndex()));
            reuClienteBD.setObsCliente(frmClienteControl.txaObsCliente.getText());
            try {
                if (reuClienteDAO.EditarCliente(reuClienteBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmClienteControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                } else {
                    JOptionPane.showMessageDialog(frmClienteControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuClienteBD.setNumdoCliente(frmClienteControl.txtCodCliente.getText());
        try {
            if (reuClienteDAO.ValidaCliente(reuClienteBD)) {
                if (JOptionPane.showConfirmDialog(frmClienteControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmClienteControl.cboDocCliente.setSelectedIndex(Integer.valueOf(reuClienteBD.getTipdoCliente()));
                    frmClienteControl.txtNumDocCliente.setText(String.valueOf(reuClienteBD.getNumdoCliente()));
                    frmClienteControl.txtNomCliente.setText(String.valueOf(reuClienteBD.getNomCliente()));
                    frmClienteControl.txtApeCliente.setText(String.valueOf(reuClienteBD.getApeCliente()));

                    if (reuClienteBD.getSexCliente().equals("M")) {
                        frmClienteControl.rdbSexM.setSelected(true);
                    } else if (reuClienteBD.getSexCliente().equals("F")) {
                        frmClienteControl.rdbSexF.setSelected(true);
                    } else {
                        frmClienteControl.btgSexo.clearSelection();
                    }

                    frmClienteControl.txtDirCliente.setText(String.valueOf(reuClienteBD.getDirCliente()));
                    frmClienteControl.txtTelCliente.setText(String.valueOf(reuClienteBD.getTelCliente()));
                    frmClienteControl.txtMailCliente.setText(String.valueOf(reuClienteBD.getMailCliente()));

                    if (reuClienteBD.getEstCliente().equals("A")) {
                        frmClienteControl.rdbEstA.setSelected(true);
                    } else if (reuClienteBD.getEstCliente().equals("I")) {
                        frmClienteControl.rdbEstI.setSelected(true);
                    } else {
                        frmClienteControl.btgEst.clearSelection();
                    }

                    frmClienteControl.jftFecNac.setText(fechaForma(reuClienteBD.getFecnCliente()));
                    frmClienteControl.cboCiuCliente.setSelectedIndex(Integer.valueOf(reuClienteBD.getCiuCliente()));
                    frmClienteControl.txaObsCliente.setText(String.valueOf(reuClienteBD.getObsCliente()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarCliente() throws ParseException {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmClienteControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmClienteControl.setResizable(false);
        frmClienteControl.setLocationRelativeTo(null);
        frmClienteControl.setTitle("Gestión Cliente - MVC");
        frmClienteControl.setVisible(true);
        reuClienteDAO.CargaTipoDoc(frmClienteControl.cboDocCliente); //Cargo Combo Documento
        reuClienteDAO.CargaCiudad(frmClienteControl.cboCiuCliente); //Cargo Combo Ciudad
        //Pongo máscara de entrada
        frmClienteControl.jftFecNac.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        //Menú inicial
        MenuNuevo();
    }

    public String fechaForma(String fecEntra) {
        SimpleDateFormat forEntrada = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat forSalida = new SimpleDateFormat("dd/MM/yyyy");

        Date xFecha = null;
        try {
            xFecha = forEntrada.parse(fecEntra);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return forSalida.format(xFecha);
    }

    public String fechaBD(String fecEntra) {
        SimpleDateFormat forEntrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat forSalida = new SimpleDateFormat("yyyy-MM-dd");
        Date xFecha = null;
        try {
            xFecha = forEntrada.parse(fecEntra);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return forSalida.format(xFecha);
    }

    String TraerSexoCliente() {//Metodo tipo String para el Estado y Sexo
        if (frmClienteControl.rdbSexM.isSelected()) {
            return "M";
        } else if (frmClienteControl.rdbSexF.isSelected()) {
            return "F";
        } else {
            return null;
        }
    }

    String TraerEstCliente() {//Metodo tipo String para el Estado y Sexo
        if (frmClienteControl.rdbEstA.isSelected()) {
            return "A";
        } else if (frmClienteControl.rdbEstI.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmBusCliente AppFrmBusqCliente = new frmBusCliente();
        ClienteBD AppClienteBD = new ClienteBD();//Instancio y creo un nuevo objeto
        ClienteDAO AppClienteDAO = new ClienteDAO();//Instancio y creo un nuevo objeto
        ControlBusCliente AppBusCliente = new ControlBusCliente(AppClienteBD, AppClienteDAO, AppFrmBusqCliente);
        AppBusCliente.IniciarClienteBus();
        AppFrmBusqCliente.setVisible(true);//Invoco el metodo
        AppFrmBusqCliente.setDefaultCloseOperation(2); //Cierro sin parar la aplicacion
    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmClienteControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmClienteControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmClienteControl);
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
        frmClienteControl.txtCodCliente.setText("");
        frmClienteControl.cboDocCliente.setSelectedIndex(0);
        frmClienteControl.txtNumDocCliente.setText("");
        frmClienteControl.txtNomCliente.setText("");
        frmClienteControl.txtApeCliente.setText("");
        frmClienteControl.btgSexo.clearSelection();
        frmClienteControl.txtDirCliente.setText("");
        frmClienteControl.txtTelCliente.setText("");
        frmClienteControl.txtMailCliente.setText("");
        frmClienteControl.btgEst.clearSelection();
        frmClienteControl.jftFecNac.setText("");
        frmClienteControl.cboCiuCliente.setSelectedIndex(0);
        frmClienteControl.txaObsCliente.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmClienteControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar y apagar los controles
        flgAct = 0;
        frmClienteControl.btnBuscar.setEnabled(true);
        frmClienteControl.btnEditar.setEnabled(false);
        frmClienteControl.btnBorrar.setEnabled(false);
        frmClienteControl.btnGuardar.setEnabled(false);
        frmClienteControl.txtCodCliente.setText("");
        frmClienteControl.txtCodCliente.setEnabled(true);
        frmClienteControl.txtCodCliente.requestFocusInWindow();
        frmClienteControl.btnValCliente.setEnabled(true);
        frmClienteControl.cboDocCliente.setSelectedIndex(0);
        frmClienteControl.cboDocCliente.setEnabled(false);
        frmClienteControl.txtNumDocCliente.setText("");
        frmClienteControl.txtNumDocCliente.setEnabled(false);
        frmClienteControl.txtNomCliente.setText("");
        frmClienteControl.txtNomCliente.setEnabled(false);
        frmClienteControl.txtApeCliente.setText("");
        frmClienteControl.txtApeCliente.setEnabled(false);
        frmClienteControl.btgSexo.clearSelection();
        frmClienteControl.rdbSexM.setEnabled(false);
        frmClienteControl.rdbSexF.setEnabled(false);
        frmClienteControl.txtDirCliente.setText("");
        frmClienteControl.txtDirCliente.setEnabled(false);
        frmClienteControl.txtTelCliente.setText("");
        frmClienteControl.txtTelCliente.setEnabled(false);
        frmClienteControl.txtMailCliente.setText("");
        frmClienteControl.txtMailCliente.setEnabled(false);
        frmClienteControl.btgEst.clearSelection();
        frmClienteControl.rdbEstA.setEnabled(false);
        frmClienteControl.rdbEstI.setEnabled(false);
        frmClienteControl.jftFecNac.setEnabled(false);
        frmClienteControl.jftFecNac.setText("");
        frmClienteControl.cboCiuCliente.setSelectedIndex(0);
        frmClienteControl.cboCiuCliente.setEnabled(false);
        frmClienteControl.txaObsCliente.setText("");
        frmClienteControl.txaObsCliente.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmClienteControl.btnEditar.setEnabled(false);
        frmClienteControl.btnBorrar.setEnabled(false);
        frmClienteControl.btnCargar.setEnabled(false);
        frmClienteControl.btnGuardar.setEnabled(true);
        frmClienteControl.txtCodCliente.setEnabled(false);//Activo e Inactivo los controles
        frmClienteControl.btnValCliente.setEnabled(false);
        frmClienteControl.cboDocCliente.setEnabled(true);
        frmClienteControl.txtNumDocCliente.setEnabled(true);
        frmClienteControl.txtNomCliente.setEnabled(true);
        frmClienteControl.txtApeCliente.setEnabled(true);
        frmClienteControl.rdbSexM.setEnabled(true);
        frmClienteControl.rdbSexF.setEnabled(true);
        frmClienteControl.txtDirCliente.setEnabled(true);
        frmClienteControl.txtTelCliente.setEnabled(true);
        frmClienteControl.txtMailCliente.setEnabled(true);
        frmClienteControl.rdbEstA.setEnabled(true);
        frmClienteControl.rdbEstI.setEnabled(true);
        frmClienteControl.jftFecNac.setEnabled(true);
        frmClienteControl.cboCiuCliente.setEnabled(true);
        frmClienteControl.txaObsCliente.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmClienteControl.btnEditar.setEnabled(true);
        frmClienteControl.btnBorrar.setEnabled(false);
        frmClienteControl.btnCargar.setEnabled(false);
        frmClienteControl.btnCancelar.setEnabled(false);
        frmClienteControl.btnBuscar.setEnabled(false);
        frmClienteControl.btnImprimir.setEnabled(false);
        frmClienteControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmClienteControl.btnNuevo.setEnabled(true);
        frmClienteControl.btnGuardar.setEnabled(true);
        frmClienteControl.btnEditar.setEnabled(false);
        frmClienteControl.btnBorrar.setEnabled(false);
        frmClienteControl.btnCargar.setEnabled(false);
        frmClienteControl.btnCancelar.setEnabled(false);
        frmClienteControl.btnBuscar.setEnabled(false);
        frmClienteControl.btnImprimir.setEnabled(false);
        frmClienteControl.txtCodCliente.setEnabled(false);
        frmClienteControl.btnValCliente.setEnabled(false);
        frmClienteControl.cboDocCliente.setEnabled(true);
        frmClienteControl.cboDocCliente.setSelectedIndex(0);
        frmClienteControl.txtNumDocCliente.setText("");
        frmClienteControl.txtNumDocCliente.setEnabled(true);
        frmClienteControl.txtNomCliente.setText("");
        frmClienteControl.txtNomCliente.setEnabled(true);
        frmClienteControl.txtApeCliente.setText("");
        frmClienteControl.txtApeCliente.setEnabled(true);
        frmClienteControl.btgSexo.clearSelection();
        frmClienteControl.rdbSexM.setEnabled(true);
        frmClienteControl.rdbSexF.setEnabled(true);
        frmClienteControl.txtDirCliente.setText("");
        frmClienteControl.txtDirCliente.setEnabled(true);
        frmClienteControl.txtTelCliente.setText("");
        frmClienteControl.txtTelCliente.setEnabled(true);
        frmClienteControl.txtMailCliente.setText("");
        frmClienteControl.txtMailCliente.setEnabled(true);
        frmClienteControl.btgEst.clearSelection();
        frmClienteControl.rdbEstA.setEnabled(true);
        frmClienteControl.rdbEstI.setEnabled(true);
        frmClienteControl.jftFecNac.setText("");
        frmClienteControl.jftFecNac.setEnabled(true);
        frmClienteControl.cboCiuCliente.setEnabled(true);
        frmClienteControl.cboCiuCliente.setSelectedIndex(0);
        frmClienteControl.txaObsCliente.setText("");
        frmClienteControl.txaObsCliente.setEnabled(true);

    }
}
