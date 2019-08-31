/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.*;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Chris
 */
public class frmPago extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmPago
     */
    public frmPago() {
        this.
                initComponents();
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        Mostrar(codReserva);
        Inhabilitar();
        txtIDreserva.setText(codReserva);
        txtNomCliente.setText(cliente);
        txtIDhabitacion.setText(codHabitacion);
        txtHabitacion.setText(habitacion);
        txtTotRsva.setText(String.valueOf(totRsva));

        fConsumo nFunConsumo = new fConsumo();
        nFunConsumo.mostrar(codReserva);

        txtIVA.setText(String.valueOf(((totRsva + nFunConsumo.totConsumo) * 19) / 100));
        totIVA = Double.valueOf(txtIVA.getText());
        txtTotalPago.setText(String.valueOf(totRsva + nFunConsumo.totConsumo + totIVA));
    }

    private String Accion = "Guardar";
    public static String codReserva;
    public static String cliente;
    public static String codHabitacion;
    public static String habitacion;
    public static Double totIVA;
    public static Double totRsva;

    void OcultarColPagos() {
        tblListaPago.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListaPago.getColumnModel().getColumn(0).setMinWidth(0);
        tblListaPago.getColumnModel().getColumn(0).setPreferredWidth(0);

        tblListaPago.getColumnModel().getColumn(1).setMaxWidth(0);
        tblListaPago.getColumnModel().getColumn(1).setMinWidth(0);
        tblListaPago.getColumnModel().getColumn(1).setPreferredWidth(0);
    }

    void OcultarColConsu() {
        tblListaConsumo.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListaConsumo.getColumnModel().getColumn(0).setMinWidth(0);
        tblListaConsumo.getColumnModel().getColumn(0).setPreferredWidth(0);

        tblListaConsumo.getColumnModel().getColumn(1).setMaxWidth(0);
        tblListaConsumo.getColumnModel().getColumn(1).setMinWidth(0);
        tblListaConsumo.getColumnModel().getColumn(1).setPreferredWidth(0);

        tblListaConsumo.getColumnModel().getColumn(2).setMaxWidth(0);
        tblListaConsumo.getColumnModel().getColumn(2).setMinWidth(0);
        tblListaConsumo.getColumnModel().getColumn(2).setPreferredWidth(0);
    }

    void Inhabilitar() {
        txtIDpago.setVisible(false);
        txtIDreserva.setVisible(false);
        txtIDhabitacion.setVisible(false);

        txtNomCliente.setEnabled(false);
        cboTipCmprb.setEnabled(false);
        txtNumCmprb.setEnabled(false);
        txtIVA.setEnabled(false);
        txtTotalPago.setEnabled(false);
        txtTotRsva.setEnabled(false);
        jdcFecGenera.setEnabled(false);
        jdcFecPago.setEnabled(false);
        txtHabitacion.setEnabled(false);

        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(false);

        txtIDpago.setText("");
        txtNumCmprb.setText("");
        cboTipCmprb.setSelectedIndex(0);
        txtIVA.setText("");
        txtTotalPago.setText("");

    }

    void Habilitar() {
        txtIDpago.setVisible(false);
        txtIDreserva.setVisible(false);
        txtIDhabitacion.setVisible(false);

        txtNomCliente.setEnabled(true);
        cboTipCmprb.setEnabled(true);
        txtNumCmprb.setEnabled(true);
        txtIVA.setEnabled(false);
        txtTotalPago.setEnabled(true);
        txtTotRsva.setEnabled(true);
        jdcFecGenera.setEnabled(true);
        jdcFecPago.setEnabled(true);
        txtHabitacion.setEnabled(true);

        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnBorrar.setEnabled(true);

        txtIDpago.setText("");
        txtNumCmprb.setText("");
        cboTipCmprb.setSelectedIndex(0);
        //txtIVA.setText("");
        //txtTotalPago.setText(""); 

    }

    void Mostrar(String buscar) {
        try {
            DefaultTableModel fModelo;
            fPago nFuncion = new fPago();
            fModelo = nFuncion.mostrar(buscar);
            //Mostrar datos pagos
            tblListaPago.setModel(fModelo);
            OcultarColPagos();
            lblTotalReg.setText("Total Pago: " + Integer.toString(nFuncion.totFilas));
            //Mostrar datos consumos
            fConsumo nFunConsu = new fConsumo();
            fModelo = nFunConsu.mostrar(buscar);
            tblListaConsumo.setModel(fModelo);
            OcultarColConsu();
            lblRegConsumo.setText("Registros: " + Integer.toString(nFunConsu.totFilas));
            lblTotConsumo.setText("Total ($): " + Double.toString(nFunConsu.totConsumo));
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtIDpago = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIDreserva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumCmprb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboTipCmprb = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNomCliente = new javax.swing.JTextField();
        txtTotRsva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIDhabitacion = new javax.swing.JTextField();
        txtHabitacion = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalPago = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jdcFecGenera = new com.toedter.calendar.JDateChooser();
        jdcFecPago = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListaPago = new javax.swing.JTable();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnImpComprob = new javax.swing.JButton();
        lblTotalReg = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblListaConsumo = new javax.swing.JTable();
        lblRegConsumo = new javax.swing.JLabel();
        lblTotConsumo = new javax.swing.JLabel();
        btnImpConsu = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setText("Pagos");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDpago.setBackground(new java.awt.Color(255, 255, 255));
        txtIDpago.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jPanel1.add(txtIDpago, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 30, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel2.setText("Reserva:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 63, -1, -1));

        txtIDreserva.setBackground(new java.awt.Color(255, 255, 255));
        txtIDreserva.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtIDreserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDreservaActionPerformed(evt);
            }
        });
        jPanel1.add(txtIDreserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 30, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel5.setText("Habitación:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 93, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel6.setText("No. Comprobante:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        txtNumCmprb.setBackground(new java.awt.Color(255, 255, 255));
        txtNumCmprb.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtNumCmprb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumCmprbActionPerformed(evt);
            }
        });
        jPanel1.add(txtNumCmprb, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 126, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel7.setText("Tipo Comprobante:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 125, -1, -1));

        cboTipCmprb.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cboTipCmprb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "     --- Selecione ---", "BOLETA", "FACTURA", "TICKET", "OTRO" }));
        cboTipCmprb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipCmprbActionPerformed(evt);
            }
        });
        jPanel1.add(cboTipCmprb, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 125, 160, -1));

        btnNuevo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_plus_16px.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 195, 79, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_save_16px_1.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 195, 79, 30));

        btnCancelar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_close_window_16px_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 195, 79, 30));

        txtNomCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNomCliente.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtNomCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 63, 395, -1));

        txtTotRsva.setBackground(new java.awt.Color(255, 255, 255));
        txtTotRsva.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtTotRsva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotRsvaActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotRsva, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 93, 132, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel8.setText("Total Reserva:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 93, -1, -1));

        txtIDhabitacion.setBackground(new java.awt.Color(255, 255, 255));
        txtIDhabitacion.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtIDhabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDhabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtIDhabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 30, -1));

        txtHabitacion.setBackground(new java.awt.Color(255, 255, 255));
        txtHabitacion.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtHabitacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHabitacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 93, 120, -1));

        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIVAActionPerformed(evt);
            }
        });
        jPanel1.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 140, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setText("IVA (19%):");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 160, -1, -1));

        txtTotalPago.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalPago.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtTotalPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPagoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 160, 128, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel11.setText("Pago:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 125, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel12.setText("Total Pago:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel13.setText("Generado:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jdcFecGenera.setBackground(new java.awt.Color(255, 255, 255));
        jdcFecGenera.setDateFormatString("dd/MM/yyyy");
        jdcFecGenera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.add(jdcFecGenera, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 140, 25));

        jdcFecPago.setBackground(new java.awt.Color(255, 255, 255));
        jdcFecPago.setDateFormatString("dd/MM/yyyy");
        jdcFecPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.add(jdcFecPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 125, 140, 25));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado Pagos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblListaPago.setBackground(new java.awt.Color(255, 255, 255));
        tblListaPago.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblListaPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListaPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaPagoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListaPago);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 471, 110));

        btnBorrar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_delete_16px.png"))); // NOI18N
        btnBorrar.setText("Eliminar");
        btnBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 150, 79, 30));

        btnSalir.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_export_16px.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 150, 79, 30));

        btnImpComprob.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnImpComprob.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_print_16px.png"))); // NOI18N
        btnImpComprob.setText("Comprobante");
        btnImpComprob.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImpComprob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImpComprobActionPerformed(evt);
            }
        });
        jPanel2.add(btnImpComprob, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 120, 30));

        lblTotalReg.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lblTotalReg.setText("Registros:");
        jPanel2.add(lblTotalReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 90, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblListaConsumo.setBackground(new java.awt.Color(255, 255, 255));
        tblListaConsumo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblListaConsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblListaConsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaConsumoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblListaConsumo);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 25, 471, 100));

        lblRegConsumo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lblRegConsumo.setText("Registros:");
        jPanel3.add(lblRegConsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 135, 90, -1));

        lblTotConsumo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lblTotConsumo.setText("Total ($):");
        jPanel3.add(lblTotConsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 135, 140, -1));

        btnImpConsu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnImpConsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_print_16px.png"))); // NOI18N
        btnImpConsu.setText("Comprobante");
        btnImpConsu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImpConsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImpConsuActionPerformed(evt);
            }
        });
        jPanel3.add(btnImpConsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDreservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDreservaActionPerformed
        // TODO add your handling code here:
        txtIDreserva.transferFocus();
    }//GEN-LAST:event_txtIDreservaActionPerformed

    private void txtNumCmprbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumCmprbActionPerformed
        // TODO add your handling code here:
        txtNumCmprb.transferFocus();
    }//GEN-LAST:event_txtNumCmprbActionPerformed

    private void cboTipCmprbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipCmprbActionPerformed
        // TODO add your handling code here:
        cboTipCmprb.transferFocus();
    }//GEN-LAST:event_cboTipCmprbActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Habilitar();
        btnGuardar.setText("Guardar");
        Accion = "Guardar";
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtIVA.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Pagos", JOptionPane.WARNING_MESSAGE);
            txtIVA.requestFocus();
            return;
        }
        if (txtTotalPago.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Pagos", JOptionPane.WARNING_MESSAGE);
            txtTotalPago.requestFocus();
            return;
        }
        if (cboTipCmprb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un elemento", "Pagos", JOptionPane.WARNING_MESSAGE);
            cboTipCmprb.requestFocus();
            return;
        }
        if (txtNumCmprb.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Pagos", JOptionPane.WARNING_MESSAGE);
            txtNumCmprb.requestFocus();
            return;
        }

        vPago fDatosPago = new vPago();
        fPago nFuncPago = new fPago();

        fDatosPago.setIdRsvaPago(Integer.valueOf(txtIDreserva.getText()));

        fDatosPago.setTipComprPago(String.valueOf(cboTipCmprb.getSelectedItem()));
        fDatosPago.setNumComprPago(txtNumCmprb.getText());
        fDatosPago.setIvaPago(Double.valueOf(txtIVA.getText()));
        fDatosPago.setTotalPago(Double.valueOf(txtTotalPago.getText()));
        fDatosPago.setIvaPago(Double.valueOf(txtIVA.getText()));

        //Calendario para fechas
        Calendar pagosCal;
        int dia, mes, anno;

        pagosCal = jdcFecGenera.getCalendar();//fecha genera pago       
        dia = pagosCal.get(Calendar.DAY_OF_MONTH);
        mes = pagosCal.get(Calendar.MONTH);
        anno = pagosCal.get(Calendar.YEAR) - 1900;//para formato del año        
        fDatosPago.setFecGenCompr(new Date(anno, mes, dia));

        pagosCal = jdcFecPago.getCalendar();//fecha realiza pago        
        dia = pagosCal.get(Calendar.DAY_OF_MONTH);
        mes = pagosCal.get(Calendar.MONTH);
        anno = pagosCal.get(Calendar.YEAR) - 1900;//para formato del año        
        fDatosPago.setFecPago(new Date(anno, mes, dia));

        if (Accion.equals("Guardar")) {
            if (nFuncPago.GuardarPago(fDatosPago)) {
                JOptionPane.showMessageDialog(this, "Pago por: $ " + txtTotalPago.getText() + ". Abonado al cliente: "
                        + txtNomCliente.getText() + " correctamente", "Pago", JOptionPane.INFORMATION_MESSAGE);

                Mostrar(codReserva);
                Inhabilitar();

                //Liberar Habitacion
                fHabitacion FuncHab = new fHabitacion();
                vHabitacion DatosHab = new vHabitacion();
                DatosHab.setIdHab(Integer.valueOf(txtIDhabitacion.getText()));
                FuncHab.LiberarHab(DatosHab);

                //Pagar ó Cancelar Reserva
                fReserva FuncRsva = new fReserva();
                vReserva DatosRsva = new vReserva();
                DatosRsva.setIdReserva(Integer.valueOf(txtIDreserva.getText()));
                FuncRsva.PagarRsva(DatosRsva);

            }

        } else if (Accion.equals("Editar")) {
            fDatosPago.setIdPago(Integer.valueOf(txtIDpago.getText()));
            if (nFuncPago.EditarPago(fDatosPago)) {
                JOptionPane.showMessageDialog(this, "Pago No. " + txtIDpago.getText() + ". Editado Correctamente. Cliente: "
                        + txtNomCliente.getText(), "Pago", JOptionPane.INFORMATION_MESSAGE);
                Mostrar(codReserva);
                Inhabilitar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblListaPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaPagoMouseClicked
        // TODO add your handling code here:
        btnGuardar.setText("Editar");
        Habilitar();
        btnBorrar.setEnabled(true);
        Accion = "Editar";

        int tFila = tblListaPago.rowAtPoint(evt.getPoint());
        txtIDpago.setText(tblListaPago.getValueAt(tFila, 0).toString());
        //txtIDreserva.setText(tblListaPago.getValueAt(tFila, 1).toString());
        cboTipCmprb.setSelectedItem(tblListaPago.getValueAt(tFila, 2).toString());
        txtNumCmprb.setText(tblListaPago.getValueAt(tFila, 3).toString());
        txtIVA.setText(tblListaPago.getValueAt(tFila, 4).toString());
        txtTotalPago.setText(tblListaPago.getValueAt(tFila, 5).toString());
        jdcFecGenera.setDate(Date.valueOf(tblListaPago.getValueAt(tFila, 6).toString()));
        jdcFecPago.setDate(Date.valueOf(tblListaPago.getValueAt(tFila, 7).toString()));

    }//GEN-LAST:event_tblListaPagoMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        if (!txtIDpago.getText().equals("")) {
            int Confirmar = JOptionPane.showConfirmDialog(this, "¿Eliminar el pago seleccionado?", "Confirmar", 2);
            if (Confirmar == 0) {
                fPago nFuncionPago = new fPago();
                vPago fDatosPago = new vPago();

                fDatosPago.setIdPago(Integer.parseInt(txtIDpago.getText()));
                nFuncionPago.BorrarPago(fDatosPago);
                JOptionPane.showMessageDialog(this, "Registro Borrado", "Pago", JOptionPane.INFORMATION_MESSAGE);
                Mostrar(codReserva);
                Inhabilitar();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Pago", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "¿Desea cerrar el formulario?", "Pago",
                JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }

    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNomClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomClienteActionPerformed

    private void txtTotRsvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotRsvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotRsvaActionPerformed

    private void txtIDhabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDhabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDhabitacionActionPerformed

    private void txtHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHabitacionActionPerformed

    private void txtIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAActionPerformed

    private void txtTotalPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPagoActionPerformed

    private void tblListaConsumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaConsumoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblListaConsumoMouseClicked

    private Connection repConnection = new Conexion().ConectarBD();

    private void btnImpComprobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImpComprobActionPerformed
        // TODO add your handling code here:
        if (!txtIDpago.getText().equals("")) {

            Map repMP = new HashMap();
            repMP.put("codPago", txtIDpago.getText());//nombre y origen parametro
            JasperReport nReporte;
            JasperPrint impReporte;

            try {
                nReporte = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                        + //recupero la Ruta del proyecto
                        "/src/reportes/rptComprobante.jrxml");//recupero la Ruta del reporte
                impReporte = JasperFillManager.fillReport(nReporte, repMP, repConnection);//paso los parametros

                JasperViewer verReporte = new JasperViewer(impReporte, false);//paso los parametros
                verReporte.setTitle("Comprobante de Pago");
                verReporte.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Parametro no asignado", "Pagos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnImpComprobActionPerformed

    private void btnImpConsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImpConsuActionPerformed
        // TODO add your handling code here:
        if (!txtIDpago.getText().equals("")) {

            Map repMP = new HashMap();
            repMP.put("codPago", txtIDpago.getText());//nombre y origen parametro
            JasperReport nReporte;
            JasperPrint impReporte;

            try {
                nReporte = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                        + //recupero la Ruta del proyecto
                        "/src/reportes/rptCompConsu.jrxml");//recupero la Ruta del reporte
                impReporte = JasperFillManager.fillReport(nReporte, repMP, repConnection);//paso los parametros

                JasperViewer verReporte = new JasperViewer(impReporte, false);//paso los parametros
                verReporte.setTitle("Comprobante de Pago");
                verReporte.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Parametro no asignado", "Pagos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnImpConsuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImpComprob;
    private javax.swing.JButton btnImpConsu;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboTipCmprb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.toedter.calendar.JDateChooser jdcFecGenera;
    private com.toedter.calendar.JDateChooser jdcFecPago;
    private javax.swing.JLabel lblRegConsumo;
    private javax.swing.JLabel lblTotConsumo;
    private javax.swing.JLabel lblTotalReg;
    private javax.swing.JTable tblListaConsumo;
    private javax.swing.JTable tblListaPago;
    private javax.swing.JTextField txtHabitacion;
    private javax.swing.JTextField txtIDhabitacion;
    private javax.swing.JTextField txtIDpago;
    private javax.swing.JTextField txtIDreserva;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtNomCliente;
    private javax.swing.JTextField txtNumCmprb;
    private javax.swing.JTextField txtTotRsva;
    private javax.swing.JTextField txtTotalPago;
    // End of variables declaration//GEN-END:variables
}
