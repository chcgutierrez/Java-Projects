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
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
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
public class ControladorCarro implements ActionListener {

    private CarroBD reuCarroBD;//Objeto del tipo EmpleBD
    private CarroDAO reuCarroDAO;//Objeto del tipo EmpleDAO
    public static frmVehiculo frmCarroControl;//Objeto del tipo frmEmple
    byte flgAct = 0;
    public static String strFrmCarro = "S";

    //Objetos Externos
    private ClienteBD reuClienteBD;//Objeto del tipo EmpleBD
    private ClienteDAO reuClienteDAO;//Objeto del tipo EmpleDAO

    public ControladorCarro(CarroBD reuCarroBD, CarroDAO reuCarroDAO, frmVehiculo frmCarroControl,
            ClienteBD reuClienteBD, ClienteDAO reuClienteDAO) {

        this.reuCarroBD = reuCarroBD;
        this.reuCarroDAO = reuCarroDAO;
        this.frmCarroControl = frmCarroControl;
        this.frmCarroControl.btnNuevo.addActionListener(this);
        this.frmCarroControl.btnEditar.addActionListener(this);
        this.frmCarroControl.btnBuscar.addActionListener(this);
        this.frmCarroControl.btnGuardar.addActionListener(this);
        this.frmCarroControl.btnCargar.addActionListener(this);
        this.frmCarroControl.btnCerrar.addActionListener(this);
        this.frmCarroControl.btnImprimir.addActionListener(this);
        this.frmCarroControl.btnValCarro.addActionListener(this);

        //Busca el cliente al tabular el código
        if (frmCarroControl.txtCodCliente.getText().length() > 0) {
            this.frmCarroControl.txtCodCliente.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    //textField1_focusGained(e);
                    Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
                    reuCarroBD.setClienteCarro(frmCarroControl.txtCodCliente.getText());
                    try {
                        //
                        if (reuCarroDAO.ClienteCarro(reuCarroBD)) {
                            frmCarroControl.txtNomCliCarro.setText(String.valueOf(reuCarroBD.getNomCliCarro()));

                        } else {
                            frmCarroControl.txtNomCliCarro.setText("");
                            JOptionPane.showMessageDialog(frmCarroControl, "El cliente no existe o está inactivo", "Cliente - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                        }
                        //
                    } catch (HeadlessException hexc) {
                        hexc.printStackTrace();
                    }
                }
            }
            );
        }

        //Abre buscador con Doble Click
        this.frmCarroControl.txtCodCliente.addMouseListener(new MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    LlamaCliente();
                }
            }
        });

    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmCarroControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmCarroControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Buscar
        if (e.getSource() == frmCarroControl.btnBuscar) {//Valida origen del evento
            Buscar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmCarroControl.btnGuardar) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            String strMarca = frmCarroControl.cboMarcaCarro.getSelectedItem().toString();
            String strTipo = frmCarroControl.cboTipoCarro.getSelectedItem().toString();
            String strColor = frmCarroControl.cboColorCarro.getSelectedItem().toString();
            String strCiudad = frmCarroControl.cboCiuCarro.getSelectedItem().toString();

            if (frmCarroControl.txtPlaca.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmCarroControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.txtPlaca.requestFocusInWindow();
            } else if (strMarca.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmCarroControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.cboMarcaCarro.requestFocusInWindow();

            } else if (strTipo.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmCarroControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.cboTipoCarro.requestFocusInWindow();

            } else if (strColor.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmCarroControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.cboColorCarro.requestFocusInWindow();

            } else if (strCiudad.equals("    - Seleccione -    ")) {
                JOptionPane.showMessageDialog(frmCarroControl, "Seleccion Inválida", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.cboCiuCarro.requestFocusInWindow();

            } else if (frmCarroControl.txtCodCliente.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmCarroControl, "Datos Incompletos", "Guardar - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.txtCodCliente.requestFocusInWindow();

            } else {
                Guardar();
            }

        }

        //Pulsar boton Cargar
        if (e.getSource() == frmCarroControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmCarroControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmCarroControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Validar
        if (e.getSource() == frmCarroControl.btnValCarro) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmCarroControl.txtPlaca.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmCarroControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmCarroControl.txtPlaca.requestFocusInWindow();
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
            reuCarroBD.setCiuCarro(String.valueOf(frmCarroControl.cboCiuCarro.getSelectedIndex()));
            reuCarroBD.setMarcaCarro(String.valueOf(frmCarroControl.cboMarcaCarro.getSelectedIndex()));
            reuCarroBD.setTipoCarro(String.valueOf(frmCarroControl.cboTipoCarro.getSelectedIndex()));
            reuCarroBD.setColorCarro(String.valueOf(frmCarroControl.cboColorCarro.getSelectedIndex()));
            reuCarroBD.setModCarro(frmCarroControl.txtModCarro.getText());
            reuCarroBD.setMotorCarro(frmCarroControl.txtMotorCarro.getText());
            reuCarroBD.setEstCarro(TraerEstCarro());
            reuCarroBD.setClienteCarro(frmCarroControl.txtCodCliente.getText());
            reuCarroBD.setObsCarro(frmCarroControl.txaObsCarro.getText());
            try {
                if (reuCarroDAO.GuardarCarro(reuCarroBD)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmCarroControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                } else {
                    JOptionPane.showMessageDialog(frmCarroControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuCarroBD.setCiuCarro(String.valueOf(frmCarroControl.cboCiuCarro.getSelectedIndex()));
            reuCarroBD.setMarcaCarro(String.valueOf(frmCarroControl.cboMarcaCarro.getSelectedIndex()));
            reuCarroBD.setTipoCarro(String.valueOf(frmCarroControl.cboTipoCarro.getSelectedIndex()));
            reuCarroBD.setColorCarro(String.valueOf(frmCarroControl.cboColorCarro.getSelectedIndex()));
            reuCarroBD.setModCarro(frmCarroControl.txtModCarro.getText());
            reuCarroBD.setMotorCarro(frmCarroControl.txtMotorCarro.getText());
            reuCarroBD.setEstCarro(TraerEstCarro());
            reuCarroBD.setClienteCarro(frmCarroControl.txtCodCliente.getText());
            reuCarroBD.setObsCarro(frmCarroControl.txaObsCarro.getText());
            try {
                if (reuCarroDAO.EditarCarro(reuCarroBD)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmCarroControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                } else {
                    JOptionPane.showMessageDialog(frmCarroControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuCarroBD.setPlacaCarro(frmCarroControl.txtPlaca.getText());
        try {
            if (reuCarroDAO.ValidarCarro(reuCarroBD)) {
                if (JOptionPane.showConfirmDialog(frmCarroControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmCarroControl.cboMarcaCarro.setSelectedIndex(Integer.valueOf(reuCarroBD.getMarcaCarro()));
                    frmCarroControl.cboTipoCarro.setSelectedIndex(Integer.valueOf(reuCarroBD.getTipoCarro()));
                    frmCarroControl.txtMotorCarro.setText(String.valueOf(reuCarroBD.getMotorCarro()));
                    frmCarroControl.txtModCarro.setText(String.valueOf(reuCarroBD.getModCarro()));
                    if (reuCarroBD.getEstCarro().equals("A")) {
                        frmCarroControl.rdbEstA.setSelected(true);
                    } else if (reuCarroBD.getEstCarro().equals("I")) {
                        frmCarroControl.rdbEstI.setSelected(true);
                    } else {
                        frmCarroControl.btgEst.clearSelection();
                    }
                    frmCarroControl.cboColorCarro.setSelectedIndex(Integer.valueOf(reuCarroBD.getColorCarro()));
                    frmCarroControl.txtCodCliente.setText(String.valueOf(reuCarroBD.getClienteCarro()));
                    frmCarroControl.txtNomCliCarro.setText(String.valueOf(reuCarroBD.getNomCliCarro()));
                    frmCarroControl.cboCiuCarro.setSelectedIndex(Integer.valueOf(reuCarroBD.getCiuCarro()));
                    frmCarroControl.txaObsCarro.setText(String.valueOf(reuCarroBD.getObsCarro()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarCarro() throws ParseException {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmCarroControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmCarroControl.setResizable(false);
        frmCarroControl.setLocationRelativeTo(null);
        frmCarroControl.setTitle("Gestión Vehiculo - MVC");
        frmCarroControl.setVisible(true);
        reuCarroDAO.CargaMarca(frmCarroControl.cboMarcaCarro); //Cargo Combo Marca
        reuCarroDAO.CargaTipo(frmCarroControl.cboTipoCarro); //Cargo Combo Tipo
        reuCarroDAO.CargaColor(frmCarroControl.cboColorCarro); //Cargo Combo Color
        reuCarroDAO.CargaCiudad(frmCarroControl.cboCiuCarro); //Cargo Combo Ciudad
        frmCarroControl.txtPlaca.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("UUU - AAA")));
        //Menú inicial
        MenuNuevo();
    }

    //Recuperar Estado Activo - Inactivo
    String TraerEstCarro() {
        if (frmCarroControl.rdbEstA.isSelected()) {
            return "A";
        } else if (frmCarroControl.rdbEstI.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Buscar() {
        frmBusVehiculo AppFrmBusqCarro = new frmBusVehiculo();
        CarroBD AppCarroBD = new CarroBD();//Instancio y creo un nuevo objeto
        CarroDAO AppCarroDAO = new CarroDAO();//Instancio y creo un nuevo objeto
        ControlBusCarro AppBusCarro = new ControlBusCarro(AppCarroBD, AppCarroDAO, AppFrmBusqCarro);
        AppBusCarro.IniciarCarroBus();
        AppFrmBusqCarro.setVisible(true);//Invoco el metodo
        AppFrmBusqCarro.setDefaultCloseOperation(2); //Cierro sin parar la aplicacion
    }

    public void LlamaCliente() {
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
        if (JOptionPane.showConfirmDialog(frmCarroControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmCarroControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmCarroControl);
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

    public void LimpiarCarro() {//Metodo para limpiar los controles
        frmCarroControl.txtPlaca.setText("");
        frmCarroControl.cboMarcaCarro.setSelectedIndex(0);
        frmCarroControl.cboTipoCarro.setSelectedIndex(0);
        frmCarroControl.txtMotorCarro.setText("");
        frmCarroControl.txtModCarro.setText("");
        frmCarroControl.btgEst.clearSelection();
        frmCarroControl.cboColorCarro.setSelectedIndex(0);
        frmCarroControl.txtCodCliente.setText("");
        frmCarroControl.txtNomCliCarro.setText("");
        frmCarroControl.cboCiuCarro.setSelectedIndex(0);
        frmCarroControl.txaObsCarro.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmCarroControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar y apagar los controles
        flgAct = 0;
        frmCarroControl.btnBuscar.setEnabled(true);
        frmCarroControl.btnEditar.setEnabled(false);
        frmCarroControl.btnBorrar.setEnabled(false);
        frmCarroControl.btnGuardar.setEnabled(false);
        LimpiarCarro();
        frmCarroControl.txtPlaca.setEnabled(true);
        frmCarroControl.txtPlaca.requestFocusInWindow();
        frmCarroControl.btnValCarro.setEnabled(true);
        frmCarroControl.cboMarcaCarro.setEnabled(false);
        frmCarroControl.cboTipoCarro.setEnabled(false);
        frmCarroControl.txtMotorCarro.setEnabled(false);
        frmCarroControl.txtModCarro.setEnabled(false);
        frmCarroControl.rdbEstA.setEnabled(false);
        frmCarroControl.rdbEstI.setEnabled(false);
        frmCarroControl.cboColorCarro.setEnabled(false);
        frmCarroControl.txtCodCliente.setEnabled(false);
        frmCarroControl.txtNomCliCarro.setEditable(false);
        frmCarroControl.cboCiuCarro.setEnabled(false);
        frmCarroControl.txaObsCarro.setEnabled(false);

    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmCarroControl.btnEditar.setEnabled(false);
        frmCarroControl.btnBorrar.setEnabled(false);
        frmCarroControl.btnCargar.setEnabled(false);
        frmCarroControl.btnGuardar.setEnabled(true);
        frmCarroControl.txtPlaca.setEnabled(false);//Activo e Inactivo los controles
        frmCarroControl.btnValCarro.setEnabled(false);
        frmCarroControl.cboMarcaCarro.setEnabled(true);
        frmCarroControl.cboTipoCarro.setEnabled(true);
        frmCarroControl.txtMotorCarro.setEnabled(true);
        frmCarroControl.txtModCarro.setEnabled(true);
        frmCarroControl.rdbEstA.setEnabled(true);
        frmCarroControl.rdbEstI.setEnabled(true);
        frmCarroControl.cboColorCarro.setEnabled(true);
        frmCarroControl.txtCodCliente.setEnabled(true);
        frmCarroControl.cboCiuCarro.setEnabled(true);
        frmCarroControl.txaObsCarro.setEnabled(true);

    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmCarroControl.btnEditar.setEnabled(true);
        frmCarroControl.btnBorrar.setEnabled(false);
        frmCarroControl.btnCargar.setEnabled(false);
        frmCarroControl.btnCancelar.setEnabled(false);
        frmCarroControl.btnBuscar.setEnabled(false);
        frmCarroControl.btnImprimir.setEnabled(false);
        frmCarroControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmCarroControl.btnNuevo.setEnabled(true);
        frmCarroControl.btnGuardar.setEnabled(true);
        frmCarroControl.btnEditar.setEnabled(false);
        frmCarroControl.btnBorrar.setEnabled(false);
        frmCarroControl.btnCargar.setEnabled(false);
        frmCarroControl.btnCancelar.setEnabled(false);
        frmCarroControl.btnBuscar.setEnabled(false);
        frmCarroControl.btnImprimir.setEnabled(false);
        //LimpiarCarro();
        frmCarroControl.txtPlaca.setEnabled(false);
        frmCarroControl.btnValCarro.setEnabled(false);
        frmCarroControl.cboMarcaCarro.setEnabled(true);
        frmCarroControl.cboTipoCarro.setEnabled(true);
        frmCarroControl.txtMotorCarro.setEnabled(true);
        frmCarroControl.txtModCarro.setEnabled(true);
        frmCarroControl.rdbEstA.setEnabled(true);
        frmCarroControl.rdbEstI.setEnabled(true);
        frmCarroControl.cboColorCarro.setEnabled(true);
        frmCarroControl.txtCodCliente.setEnabled(true);
        frmCarroControl.cboCiuCarro.setEnabled(true);
        frmCarroControl.txaObsCarro.setEnabled(true);
    }
}
