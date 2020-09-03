/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.ControladorCliente.frmClienteControl;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modelo.*;
import vista.*;

/**
 *
 * @author Chris
 */
public class ControlOrdenTra implements ActionListener {

    private OrdenBD reuOrdenBD;//Objeto del tipo ColorBD    
    private OrdenDAO reuOrdenDAO;//Objeto del tipo ColorDAO

    private OrdenRepBD reuOrdenRepBD;//Objeto del tipo ColorBD
    private ClienteBD reuClieBD;//Objeto del tipo ColorBD

    private CarroBD reuCarroBD;//Objeto del tipo ColorBD
    private CarroDAO reuCarroDAO;//Objeto del tipo ColorBD

    public static frmOrdenTra frmOrdenControl;//Objeto del tipo frmColor
    byte flgAct = 0;
    public static String strFrmOrden = "S";

    private EmpleadoBD reuEmpleBD;//Objeto campos Tabla Empleado
    private EmpleadoDAO reuEmpleDAO;//Objeto para metodos CRUD Empleado

    private RepuestoBD reuRepuestoBD;//Objeto campos Tabla Repuesto
    private RepuestoDAO reuRepuestoDAO;//Objeto para metodos CRUD Repuesto

    public ControlOrdenTra(CarroBD reuCarroBD, CarroDAO reuCarroDAO, EmpleadoBD reuEmpleBD, EmpleadoDAO reuEmpleDAO,
            RepuestoBD reuRepuestoBD, RepuestoDAO reuRepuestoDAO,
            OrdenBD reuOrdenBD, OrdenDAO reuOrdenDAO, frmOrdenTra frmOrdenControl) {

        this.reuCarroBD = reuCarroBD;
        this.reuCarroDAO = reuCarroDAO;

//        this.reuEmpleBD = reuEmpleBD;//Objeto campos Tabla Empleado
//        this.reuEmpleDAO = reuEmpleDAO;//Objeto para metodos CRUD Empleado
//        
//        this.reuRepuestoBD = reuRepuestoBD;
//        this.reuRepuestoDAO = reuRepuestoDAO;
        this.reuOrdenBD = reuOrdenBD;
        this.reuOrdenDAO = reuOrdenDAO;
        this.frmOrdenControl = frmOrdenControl;

        this.frmOrdenControl.btnNuevo.addActionListener(this);
        this.frmOrdenControl.btnEditar.addActionListener(this);
        this.frmOrdenControl.btnBuscar.addActionListener(this);
        this.frmOrdenControl.btnGuardar.addActionListener(this);
        this.frmOrdenControl.btnCargar.addActionListener(this);
        this.frmOrdenControl.btnCerrar.addActionListener(this);
        this.frmOrdenControl.btnValPlaca.addActionListener(this);
        this.frmOrdenControl.btnImprimir.addActionListener(this);
        this.frmOrdenControl.btnAgrRep.addActionListener(this);

        //Busca el empleado al tabular el código
        if (frmOrdenControl.txtCodEmple.getText().length() > 0) {
            this.frmOrdenControl.txtCodEmple.addFocusListener(new FocusAdapter() {

                public void focusLost(FocusEvent e) {
                    //textField1_focusGained(e);
                    Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
                    reuEmpleBD.setNumdoEmple(frmOrdenControl.txtCodEmple.getText());
                    try {
                        //
                        if (reuEmpleDAO.mecaOrden(reuEmpleBD)) {
                            int codEmple = Integer.valueOf(String.valueOf(reuEmpleBD.getCodEmple()));
                            frmOrdenControl.txtNomEmple.setText(String.valueOf(reuEmpleBD.getNomEmple()));
                            System.out.println(codEmple);
                        } else {
                            frmOrdenControl.txtNomEmple.setText("");
                            JOptionPane.showMessageDialog(frmOrdenControl, "El cliente no existe o está inactivo", "Cliente - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                        }
                        //
                    } catch (HeadlessException hexc) {
                        hexc.printStackTrace();
                    }
                }
            }
            );
        }

        //Busca el Repuesto al tabular el código
        if (frmOrdenControl.txtCodRepuesto.getText().length() > 0) {
            this.frmOrdenControl.txtCodRepuesto.addFocusListener(new FocusAdapter() {

                public void focusLost(FocusEvent e) {
                    //textField1_focusGained(e);
                    Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));
                    reuRepuestoBD.setCodRepto(frmOrdenControl.txtCodRepuesto.getText());
                    try {
                        //
                        if (reuRepuestoDAO.repuOrden(reuRepuestoBD)) {
                            int codRepu = Integer.valueOf(String.valueOf(reuRepuestoBD.getIdRepto()));
                            frmOrdenControl.txtDescRepuesto.setText(String.valueOf(reuRepuestoBD.getNomRepto()));
                            frmOrdenControl.txtTipoRep.setText(String.valueOf(reuRepuestoBD.getTipoRepto()));
                            frmOrdenControl.txtUndRep.setText(String.valueOf(reuRepuestoBD.getCantRepto()));
                            System.out.println(codRepu);
                        } else {
                            frmOrdenControl.txtDescRepuesto.setText("");
                            frmOrdenControl.txtTipoRep.setText("");
                            frmOrdenControl.txtUndRep.setText("");
                            JOptionPane.showMessageDialog(frmOrdenControl, "El repuesto no existe o está inactivo", "Repuesto - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                        }
                        //
                    } catch (HeadlessException hexc) {
                        hexc.printStackTrace();
                    }
                }
            }
            );
        }

        //Abre buscador de Empleado con Doble Click en el Código
        this.frmOrdenControl.txtCodEmple.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TraerEmpleado();
                }
            }
        });

        //Abre buscador de Repuesto con Doble Click en el Código
        this.frmOrdenControl.txtCodRepuesto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TraerRepuesto();
                }
            }
        });

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

    //Eventos Botones
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmOrdenControl.btnAgrRep) {
            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmOrdenControl.txtCodRepuesto.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmOrdenControl, "Ingrese el repuesto", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmOrdenControl.txtCodRepuesto.requestFocusInWindow();
            } else if (frmOrdenControl.txtDescRepuesto.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmOrdenControl, "Datos incompletos", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmOrdenControl.txtDescRepuesto.requestFocusInWindow();
            } else if (frmOrdenControl.spnCantRep.getValue().equals(0)) {
                JOptionPane.showMessageDialog(frmOrdenControl, "Cantidad inválida", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmOrdenControl.spnCantRep.transferFocus();
            } else {
                repFilas();
            }

        }

        //Pulsar boton Validar
        if (e.getSource() == frmOrdenControl.btnValPlaca) {//Valida origen del evento

            Icon Vacio = new ImageIcon(getClass().getResource("/img/icons8_close_window_32px.png"));

            if (frmOrdenControl.txtPlaca.getText().length() == 0) {
                JOptionPane.showMessageDialog(frmOrdenControl, "Debe ingresar un criterio", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Vacio);
                frmOrdenControl.txtPlaca.requestFocusInWindow();
            } else {
                Validar();
            }
        }

    }

//    public void Guardar() {//Metodo para Guardar y Editar
//        //Iconos para los mensajes
//        Icon Editar = new ImageIcon(getClass().getResource("/img/icons8_edit_file_32px.png"));
//        Icon Guardar = new ImageIcon(getClass().getResource("/img/ok_32px.png"));
//        Icon Error = new ImageIcon(getClass().getResource("/img/high_priority_32px.png"));
//
//        if (flgAct == 0) {
//            reuOrdenBD.setFecOrden(fechaBD(frmOrdenControl.txtCodEmple.getText()));//Pasa la fecha a formato de BD
//
//            reuOrdenBD.setCodCliOrden(String.valueOf(reuClieBD.getNumdoCliente()));
//            reuOrdenBD.setCodEmpleOrden(String.valueOf(reuEmpleBD.getCodEmple()));
//            reuOrdenBD.setCodVehiculo(String.valueOf(reuCarroBD.getCodCarro()));
//            reuOrdenBD.setValOrden(frmOrdenControl.txtModeloCarro.getText());
//            reuOrdenBD.setDetOrden(frmOrdenControl.txaDetOrden.getText());
//            reuOrdenBD.setObsOrden(frmOrdenControl.txaObsOrden.getText());
//            try {
//                if (reuOrdenDAO.GuardarOrden(reuOrdenBD)) {//Invoca la clase con el metodo para Guardar
//                    //Muestro el mensaje de exito
//                    JOptionPane.showMessageDialog(frmColorControl, "Nuevo registro guardado", "Guardar - MVC", JOptionPane.OK_OPTION, Guardar);
//                    //MenuNuevo();//Limpio los controles
//                    //frmOrdenControl.jtbDataColor.setModel(reuColorDAO.VerColor());//Actualiza la tabla con la fila que se agrega
//                } else {
//                    JOptionPane.showMessageDialog(frmColorControl, "No es posible guardar el registro", "Guardar - MVC", JOptionPane.ERROR, Error);
//                }
//            } catch (HeadlessException hexc) {
//                hexc.printStackTrace();
//            }
//        }
//    }
    
    //Metodo para Validar
    public void Validar() {
        Icon Validar = new ImageIcon(getClass().getResource("/img/icons8_help_32px.png"));
        reuCarroBD.setPlacaCarro(frmOrdenControl.txtPlaca.getText());
        try {
            if (reuCarroDAO.OrdenAuto(reuCarroBD)) {
                if (JOptionPane.showConfirmDialog(frmClienteControl, "Ya existen datos. Mostrar Datos?", "Validar - MVC",
                        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, Validar) == JOptionPane.YES_OPTION) {
                    //MenuValidar();
                    frmOrdenControl.txtNomCliente.setText(String.valueOf(reuCarroBD.getNomCliCarro()));
                    frmOrdenControl.txtTelCliente.setText(String.valueOf(reuCarroBD.getTelCliCarro()));
                    frmOrdenControl.txtMarcaCarro.setText(String.valueOf(reuCarroBD.getMarcaCarro()));
                    frmOrdenControl.txtMotorCarro.setText(String.valueOf(reuCarroBD.getMotorCarro()));
                    frmOrdenControl.txtModeloCarro.setText(String.valueOf(reuCarroBD.getModCarro()));
                }
            } else {
                //MenuGuardar();
            }
        } catch (HeadlessException hexc) {
            hexc.printStackTrace();
        }
    }

    //Inicializar el form
    public void IniciarOrden() throws ParseException {//Metodo para iniciar el form
        //Poner icono al form
        ImageIcon formaIcon = new ImageIcon(getClass().getResource("/img/icons8_services_48px.png"));
        Image Image = formaIcon.getImage();
        frmOrdenControl.setIconImage(Image);
        //Iniciar propiedades del form
        frmOrdenControl.setResizable(false);
        frmOrdenControl.setLocationRelativeTo(null);
        frmOrdenControl.setTitle("Gestión Orden Trabajo - MVC");
        frmOrdenControl.setVisible(true);
        frmOrdenControl.jtbOrdenRep.setModel(reuOrdenDAO.tabRepuestos());
        //Pongo máscara de entrada
        frmOrdenControl.txtPlaca.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("UUU - AAA")));
        frmOrdenControl.jftFecOrden.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        frmOrdenControl.jftFecOrden.setText(getFechaActual());
        //Menú inicial
        TableColumn colA = frmOrdenControl.jtbOrdenRep.getColumnModel().getColumn(0);
        colA.setPreferredWidth(90);
        colA.setMaxWidth(90);
        colA.setMinWidth(90);
        TableColumn colC = frmOrdenControl.jtbOrdenRep.getColumnModel().getColumn(2);
        colC.setPreferredWidth(90);
        colC.setMaxWidth(90);
        colC.setMinWidth(90);
        //Retira editor
        for (int col = 0; col < frmOrdenControl.jtbOrdenRep.getColumnCount(); col++) {
            Class<?> col_class = frmOrdenControl.jtbOrdenRep.getColumnClass(col);
            frmOrdenControl.jtbOrdenRep.setDefaultEditor(col_class, null);
        }
        //Menú inicial
        //MenuNuevo();
    }

    //Recuperar fecha actual
    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(ahora);        
    }

    public void gridRepuestos() {

        DefaultTableModel modRep = (DefaultTableModel) frmOrdenControl.jtbOrdenRep.getModel();
        Object datGrid[] = new Object[3];
        datGrid[0] = String.valueOf(frmOrdenControl.txtCodRepuesto.getText());
        datGrid[1] = String.valueOf(frmOrdenControl.txtDescRepuesto.getText());
        datGrid[2] = String.valueOf(frmOrdenControl.spnCantRep.getValue().toString());
        modRep.addRow(datGrid);
        frmOrdenControl.jtbOrdenRep.setModel(modRep);
        frmOrdenControl.txtCodRepuesto.setText("");
        frmOrdenControl.txtDescRepuesto.setText("");
        frmOrdenControl.spnCantRep.setValue(0);
        frmOrdenControl.txtTipoRep.setText("");
        frmOrdenControl.txtUndRep.setText("");
        frmOrdenControl.txtTipoRep.setText("");
        frmOrdenControl.txtUndRep.setText("");
    }

    public void repFilas() {
        Icon Error = new ImageIcon(getClass().getResource("/img/high_priority_32px.png"));
        boolean prodExiste = false;
        String codTabla = "";
        try {
            for (int i = 0; i < (frmOrdenControl.jtbOrdenRep.getRowCount()); i++) {
                codTabla = frmOrdenControl.jtbOrdenRep.getValueAt(i, 0).toString().trim();

                if (frmOrdenControl.txtCodRepuesto.getText().equals("")) {
                    JOptionPane.showMessageDialog(frmOrdenControl, "Ingrese el repuesto", "Validación - MVC", JOptionPane.ERROR_MESSAGE, Error);
                    frmOrdenControl.txtCodRepuesto.requestFocusInWindow();
                    break;
                } else if (codTabla.equals(frmOrdenControl.txtCodRepuesto.getText())) {
                    prodExiste = true;
                    frmOrdenControl.jtbOrdenRep.changeSelection(i, 0, false, false);
                    break;
                }
            }
            if (!prodExiste) {
                gridRepuestos();
            } else {
                JOptionPane.showMessageDialog(frmOrdenControl, "Repuesto repetido", "Agregar - MVC", JOptionPane.ERROR_MESSAGE, Error);
                //frmOrdenControl.jtbOrdenRep.changeSelection(i, 0, false, false);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    //Llamo al Empleado al hacer Doble Click
    public void TraerEmpleado() {
        frmBusEmpleado AppFormaEmpleado = new frmBusEmpleado();
        EmpleadoBD AppEmpleadoBD = new EmpleadoBD();//Instancio y creo un nuevo objeto
        EmpleadoDAO AppEmpleadoDAO = new EmpleadoDAO();//Instancio y creo un nuevo objeto
        ControlBusEmpleado AppBusEmpleado = new ControlBusEmpleado(AppEmpleadoBD, AppEmpleadoDAO, AppFormaEmpleado);
        AppBusEmpleado.IniciarEmpleBus();
        AppFormaEmpleado.setVisible(true);//Invoco el metodo
        AppFormaEmpleado.setDefaultCloseOperation(2); //Cierro sin parar la aplicacion
    }

    //Llamo al Repuesto al hacer Doble Click
    public void TraerRepuesto() {
        frmRepuBusq AppFormBusqRepu = new frmRepuBusq();
        RepuestoBD AppRepuestoBD = new RepuestoBD();//Instancio y creo un nuevo objeto
        RepuestoDAO AppRepuestoDAO = new RepuestoDAO();//Instancio y creo un nuevo objeto
        ControlRepuBusq AppBusRepuesto = new ControlRepuBusq(AppRepuestoBD, AppRepuestoDAO, AppFormBusqRepu);
        AppBusRepuesto.IniciarRepuestoBusq();
        AppFormBusqRepu.setVisible(true);//Invoco el metodo
        AppFormBusqRepu.setDefaultCloseOperation(2); //Cierro sin parar la aplicacion
    }

}
