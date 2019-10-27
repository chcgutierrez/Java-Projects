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
import modelo.MarcaDAO;
import modelo.MarcaEnt;
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
public class ControladorMarca implements ActionListener {

    private MarcaEnt reuMarcaEnt;//Objeto del tipo MarcaEnt
    private MarcaDAO reuMarcaDAO;//Objeto del tipo MarcaDAO
    private frmMarca frmMarcaControl;//Objeto del tipo frmMarca
    private frmBsqMarca frmBusqueda;

    byte flgAct = 0;

    public ControladorMarca(MarcaEnt reuMarcaEnt, MarcaDAO reuMarcaDAO, frmMarca frmMarcaControl, frmBsqMarca frmBusqueda) {
        this.reuMarcaEnt = reuMarcaEnt;
        this.reuMarcaDAO = reuMarcaDAO;
        this.frmMarcaControl = frmMarcaControl;
        this.frmMarcaControl.btnNuevo.addActionListener(this);
        this.frmMarcaControl.btnEditar.addActionListener(this);
        this.frmMarcaControl.btnGuardar.addActionListener(this);
        this.frmMarcaControl.btnCargar.addActionListener(this);
        this.frmMarcaControl.btnCerrar.addActionListener(this);
        this.frmMarcaControl.btnVldMarca.addActionListener(this);
        this.frmMarcaControl.btnImprimir.addActionListener(this);
    }

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {
        //Pulsar boton Nuevo
        if (e.getSource() == frmMarcaControl.btnNuevo) {
            MenuNuevo();
        }
        //Pulsar boton Editar
        if (e.getSource() == frmMarcaControl.btnEditar) {
            MenuEditar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmMarcaControl.btnGuardar) {//Valida origen del evento
            Guardar();
        }

        //Pulsar boton Cargar
        if (e.getSource() == frmMarcaControl.btnCargar) {//Valida origen del evento
            LeerPlantilla();
        }

        //Pulsar boton Imprimir
        if (e.getSource() == frmMarcaControl.btnImprimir) {//Valida origen del evento
            Imprimir();
        }

        //Pulsar boton Cerrar
        if (e.getSource() == frmMarcaControl.btnCerrar) {//Valida origen del evento
            Cerrar();
        }

        //Pulsar boton Guardar
        if (e.getSource() == frmMarcaControl.btnVldMarca) {//Valida origen del evento
            Validar();
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
            reuMarcaEnt.setCod_marca(frmMarcaControl.txtCodMarca.getText());//Pasa los parametros al objeto
            reuMarcaEnt.setNom_marca(frmMarcaControl.txtNomMarca.getText());
            reuMarcaEnt.setEstado_marca(TraerEstadoMarca());
            reuMarcaEnt.setObs_marca(frmMarcaControl.txaObsMarca.getText());
            try {
                if (reuMarcaDAO.GuardarMarca(reuMarcaEnt)) {//Invoca la clase con el metodo para Guardar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmMarcaControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
                    MenuNuevo();//Limpio los controles
                    frmMarcaControl.jtbDataMarca.setModel(reuMarcaDAO.VerMarca());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmMarcaControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        } else {
            reuMarcaEnt.setCod_marca(frmMarcaControl.txtCodMarca.getText());//Pasa los parametros al objeto
            reuMarcaEnt.setNom_marca(frmMarcaControl.txtNomMarca.getText());
            reuMarcaEnt.setEstado_marca(TraerEstadoMarca());
            reuMarcaEnt.setObs_marca(frmMarcaControl.txaObsMarca.getText());
            try {
                if (reuMarcaDAO.EditarMarca(reuMarcaEnt)) {//Invoca la clase con el metodo para Editar
                    //Muestro el mensaje de exito
                    JOptionPane.showMessageDialog(frmMarcaControl, "Registro existente editado", "Editar - MVC", JOptionPane.OK_OPTION, Editar);
                    MenuNuevo();//Limpio los controles
                    frmMarcaControl.jtbDataMarca.setModel(reuMarcaDAO.VerMarca());//Actualiza la tabla con la fila que se agrega
                } else {
                    JOptionPane.showMessageDialog(frmMarcaControl, "No es posible editar el registro", "Editar - MVC", JOptionPane.ERROR, Error);
                }
            } catch (HeadlessException hexc) {
                hexc.printStackTrace();
            }
        }
    }

    public void Validar() {//Metodo para Validar
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuMarcaEnt.setCod_marca(frmMarcaControl.txtCodMarca.getText());
        try {
            if (reuMarcaDAO.ValidaMarca(reuMarcaEnt)) {
                if (JOptionPane.showConfirmDialog(frmMarcaControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    MenuValidar();
                    frmMarcaControl.txtNomMarca.setText(String.valueOf(reuMarcaEnt.getNom_marca()));
                    if (reuMarcaEnt.getEstado_marca().equals("A")) {
                        frmMarcaControl.rdbMarcaAct.setSelected(true);
                    } else if (reuMarcaEnt.getEstado_marca().equals("I")) {
                        frmMarcaControl.rdbMarcaInac.setSelected(true);
                    } else {
                        frmMarcaControl.rdbMarcaInac.setSelected(true);
                    }
                    frmMarcaControl.txaObsMarca.setText(String.valueOf(reuMarcaEnt.getObs_marca()));
                }
            } else {
                MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    public void IniciarMarca() {//Metodo para iniciar el form
        //Menú inicial
        MenuNuevo();
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmMarcaControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmMarcaControl.setResizable(false);
        frmMarcaControl.setLocationRelativeTo(null);
        frmMarcaControl.setTitle("Gestión Marcas - MVC");
        frmMarcaControl.setVisible(true);
        frmMarcaControl.jtbDataMarca.setModel(reuMarcaDAO.VerMarca());
        frmMarcaControl.jtbDataMarca.setEnabled(false);
    }

    String TraerEstadoMarca() {//Metodo tipo String para el estado
        if (frmMarcaControl.rdbMarcaAct.isSelected()) {
            return "A";
        } else if (frmMarcaControl.rdbMarcaInac.isSelected()) {
            return "I";
        } else {
            return null;
        }
    }

    public void Cerrar() {//Metodo para Cerrar
        Icon Cerrar = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
        if (JOptionPane.showConfirmDialog(frmMarcaControl, "¿Desea cerrar el formulario?", "Cerrar - MVC",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Cerrar) == JOptionPane.YES_OPTION) {
            frmMarcaControl.dispose();
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
        int selEstado = jfcSeleccion.showOpenDialog(frmMarcaControl);
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

    private void BsqMarca() {

    }

    public void LimpiarMarca() {//Metodo para limpiar los controles
        frmMarcaControl.txtCodMarca.setText("");
        frmMarcaControl.txtNomMarca.setText("");
        frmMarcaControl.btgMarca.clearSelection();
        frmMarcaControl.txaObsMarca.setText("");
    }

    Byte FlagActualiza() {//Metodo tipo String para el estado
        if (frmMarcaControl.btnEditar.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void MenuNuevo() {//Metodo para limpiar los controles
        flgAct = 0;
        frmMarcaControl.btnEditar.setEnabled(false);
        frmMarcaControl.btnBorrar.setEnabled(false);
        frmMarcaControl.btnGuardar.setEnabled(false);
        frmMarcaControl.txtCodMarca.setText("");
        frmMarcaControl.txtCodMarca.setEnabled(true);
        frmMarcaControl.txtCodMarca.requestFocusInWindow();
        frmMarcaControl.btnVldMarca.setEnabled(true);
        frmMarcaControl.txtNomMarca.setText("");
        frmMarcaControl.txtNomMarca.setEnabled(false);
        frmMarcaControl.btgMarca.clearSelection();
        frmMarcaControl.rdbMarcaAct.setEnabled(false);
        frmMarcaControl.rdbMarcaInac.setEnabled(false);
        frmMarcaControl.txaObsMarca.setText("");
        frmMarcaControl.txaObsMarca.setEnabled(false);
    }

    public void MenuEditar() {//Metodo para limpiar los controles
        flgAct = 1;
        frmMarcaControl.btnEditar.setEnabled(false);
        frmMarcaControl.btnBorrar.setEnabled(false);
        frmMarcaControl.btnCargar.setEnabled(false);
        frmMarcaControl.btnGuardar.setEnabled(true);
        frmMarcaControl.txtCodMarca.setEnabled(false);//Activo e Inactivo los controles
        frmMarcaControl.btnVldMarca.setEnabled(false);
        frmMarcaControl.txtNomMarca.setEnabled(true);
        frmMarcaControl.rdbMarcaAct.setEnabled(true);
        frmMarcaControl.rdbMarcaInac.setEnabled(true);
        frmMarcaControl.txaObsMarca.setEnabled(true);
    }

    public void MenuValidar() {//Metodo para limpiar los controles
        frmMarcaControl.btnEditar.setEnabled(true);
        frmMarcaControl.btnBorrar.setEnabled(false);
        frmMarcaControl.btnCargar.setEnabled(false);
        frmMarcaControl.btnCancelar.setEnabled(false);
        frmMarcaControl.btnBuscar.setEnabled(false);
        frmMarcaControl.btnImprimir.setEnabled(false);
        frmMarcaControl.btnGuardar.setEnabled(true);
    }

    public void MenuGuardar() {//Metodo para limpiar los controles
        frmMarcaControl.btnNuevo.setEnabled(true);
        frmMarcaControl.btnGuardar.setEnabled(true);
        frmMarcaControl.btnEditar.setEnabled(false);
        frmMarcaControl.btnBorrar.setEnabled(false);
        frmMarcaControl.btnCargar.setEnabled(false);
        frmMarcaControl.btnCancelar.setEnabled(false);
        frmMarcaControl.btnBuscar.setEnabled(false);
        frmMarcaControl.btnImprimir.setEnabled(false);
        frmMarcaControl.txtCodMarca.setEnabled(false);
        frmMarcaControl.btnVldMarca.setEnabled(false);
        frmMarcaControl.txtNomMarca.requestFocusInWindow();
        frmMarcaControl.txtNomMarca.setText("");
        frmMarcaControl.txtNomMarca.setEnabled(true);
        frmMarcaControl.btgMarca.clearSelection();
        frmMarcaControl.rdbMarcaAct.setEnabled(true);
        frmMarcaControl.rdbMarcaInac.setEnabled(true);
        frmMarcaControl.txaObsMarca.setText("");
        frmMarcaControl.txaObsMarca.setEnabled(true);

    }

}
