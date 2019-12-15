/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.vEmpleado;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.fEmpleado;

/**
 *
 * @author Chris
 */
public class frmEmpleado extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmProducto
     */
    public frmEmpleado() {
        initComponents();
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setTitle("Empleados");
        Mostrar("");
        Inhabilitar();
    }

    private String Accion = "Guardar";

    void OcultarCols() {
        tblListaEmp.getColumnModel().getColumn(0).setMaxWidth(0);
        tblListaEmp.getColumnModel().getColumn(0).setMinWidth(0);
        tblListaEmp.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    void Inhabilitar() {
        txtNomEmp.setEnabled(false);
        txtPrimApeEmp.setEnabled(false);
        txtSegApeEmp.setEnabled(false);
        cboTipDocEmp.setEnabled(false);
        txtNumDocEmp.setEnabled(false);
        txtDirEmp.setEnabled(false);
        txtTelEmp.setEnabled(false);
        txtMailEmp.setEnabled(false);
        txtSalarioEmp.setEnabled(false);
        cboAccEmp.setEnabled(false);
        txtLoginEmp.setEnabled(false);
        txtClaveEmp.setEnabled(false);
        cboEstadoEmp.setEnabled(false);
        txtIDempleado.setVisible(false);
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(false);

        txtNomEmp.setText("");
        txtPrimApeEmp.setText("");
        txtSegApeEmp.setText("");
        cboTipDocEmp.setSelectedIndex(0);
        txtNumDocEmp.setText("");
        txtDirEmp.setText("");
        txtTelEmp.setText("");
        txtMailEmp.setText("");
        txtSalarioEmp.setText("");
        cboAccEmp.setSelectedIndex(0);
        txtLoginEmp.setText("");
        txtClaveEmp.setText("");
        cboEstadoEmp.setSelectedIndex(0);
        txtIDempleado.setText("");

    }

    void Habilitar() {
        txtNomEmp.setEnabled(true);
        txtPrimApeEmp.setEnabled(true);
        txtSegApeEmp.setEnabled(true);
        cboTipDocEmp.setEnabled(true);
        txtNumDocEmp.setEnabled(true);
        txtDirEmp.setEnabled(true);
        txtTelEmp.setEnabled(true);
        txtMailEmp.setEnabled(true);
        txtSalarioEmp.setEnabled(true);
        cboAccEmp.setEnabled(true);
        txtLoginEmp.setEnabled(true);
        txtClaveEmp.setEnabled(true);
        cboEstadoEmp.setEnabled(true);
        txtIDempleado.setVisible(false);

        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnBorrar.setEnabled(true);

        txtNomEmp.setText("");
        txtPrimApeEmp.setText("");
        txtSegApeEmp.setText("");
        cboTipDocEmp.setSelectedIndex(0);
        txtNumDocEmp.setText("");
        txtDirEmp.setText("");
        txtTelEmp.setText("");
        txtMailEmp.setText("");
        txtSalarioEmp.setText("");
        txtIDempleado.setText("");
        cboAccEmp.setSelectedIndex(0);
        txtLoginEmp.setText("");
        txtClaveEmp.setText("");
        cboEstadoEmp.setSelectedIndex(0);
        txtIDempleado.setText("");

    }
    
    void Mostrar(String buscar) {
        try {
            DefaultTableModel fModelo;
            fEmpleado nFuncion = new fEmpleado();
            fModelo = nFuncion.mostrar(buscar);
            tblListaEmp.setModel(fModelo);
            OcultarCols();
            lblTotalReg.setText("Total Filas: " + Integer.toString(nFuncion.totFilas));
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
        txtIDempleado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTelEmp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumDocEmp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboAccEmp = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMailEmp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSalarioEmp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDirEmp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSegApeEmp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNomEmp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPrimApeEmp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cboTipDocEmp = new javax.swing.JComboBox<>();
        txtLoginEmp = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtClaveEmp = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cboEstadoEmp = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListaEmp = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblTotalReg = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setText("Empleado");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDempleado.setBackground(new java.awt.Color(255, 255, 255));
        txtIDempleado.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jPanel1.add(txtIDempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 90, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 30, -1, -1));

        txtTelEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtTelEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtTelEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtTelEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 160, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel6.setText("Número:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        txtNumDocEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtNumDocEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtNumDocEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumDocEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtNumDocEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 160, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel7.setText("Acceso:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 180, -1, -1));

        cboAccEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cboAccEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "          --- Selecione ---", "ADMINISTRADOR", "DIGITADOR" }));
        cboAccEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAccEmpActionPerformed(evt);
            }
        });
        jPanel1.add(cboAccEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 200, -1));

        btnNuevo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_plus_16px.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 275, 79, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_save_16px_1.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 275, 79, 30));

        btnCancelar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_close_window_16px_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 275, 79, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel3.setText("Primer Apellido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 60, -1, -1));

        txtMailEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtMailEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtMailEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtMailEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 150, 447, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setText("Segundo Apellido:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        txtSalarioEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtSalarioEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtSalarioEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtSalarioEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 180, 180, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel5.setText("Dirección:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 120, -1, -1));

        txtDirEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtDirEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtDirEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtDirEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 120, 210, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel8.setText("Telefono:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 120, -1, -1));

        txtSegApeEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtSegApeEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtSegApeEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSegApeEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtSegApeEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 160, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setText("Email:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        txtNomEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtNomEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtNomEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 30, 447, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel11.setText("Salario:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 180, -1, -1));

        txtPrimApeEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtPrimApeEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtPrimApeEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimApeEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtPrimApeEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 60, 160, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel12.setText("Tipo Documento:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        cboTipDocEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cboTipDocEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "          --- Selecione ---", "CEDULA DE CIUDADANIA", "PASAPORTE", "CEDULA EXTRANJERIA", "CEDULA MILITAR" }));
        cboTipDocEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipDocEmpActionPerformed(evt);
            }
        });
        jPanel1.add(cboTipDocEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 90, 200, -1));

        txtLoginEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtLoginEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        txtLoginEmp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLoginEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtLoginEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 210, 180, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel14.setText("Clave:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 210, -1, -1));

        txtClaveEmp.setBackground(new java.awt.Color(255, 255, 255));
        txtClaveEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtClaveEmp.setText("jPasswordField1");
        txtClaveEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveEmpActionPerformed(evt);
            }
        });
        jPanel1.add(txtClaveEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 200, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel15.setText("Login:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel16.setText("Estado:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 240, -1, -1));

        cboEstadoEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        cboEstadoEmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "          --- Selecione ---", "A", "I" }));
        cboEstadoEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoEmpActionPerformed(evt);
            }
        });
        jPanel1.add(cboEstadoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 240, 200, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Listado Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblListaEmp.setBackground(new java.awt.Color(255, 255, 255));
        tblListaEmp.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblListaEmp.setModel(new javax.swing.table.DefaultTableModel(
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
        tblListaEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaEmpMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListaEmp);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, 471, 205));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel9.setText("Buscar:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 33, -1, -1));

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 31, 136, -1));

        btnBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_search_more_16px_2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 28, 79, 30));

        btnBorrar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_delete_16px.png"))); // NOI18N
        btnBorrar.setText("Eliminar");
        btnBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 28, 79, 30));

        btnSalir.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_export_16px.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 28, 79, 30));

        lblTotalReg.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lblTotalReg.setText("Registros:");
        jPanel2.add(lblTotalReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelEmpActionPerformed
        // TODO add your handling code here:
        txtTelEmp.transferFocus();
    }//GEN-LAST:event_txtTelEmpActionPerformed

    private void txtNumDocEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumDocEmpActionPerformed
        // TODO add your handling code here:
        txtNumDocEmp.transferFocus();
    }//GEN-LAST:event_txtNumDocEmpActionPerformed

    private void cboAccEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAccEmpActionPerformed
        // TODO add your handling code here:
        cboAccEmp.transferFocus();
    }//GEN-LAST:event_cboAccEmpActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Habilitar();
        btnGuardar.setText("Guardar");
        Accion = "Guardar";
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtNomEmp.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            txtNomEmp.requestFocus();
            return;
        }
        if (txtPrimApeEmp.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            txtPrimApeEmp.requestFocus();
            return;
        }
        if (txtSegApeEmp.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            txtSegApeEmp.requestFocus();
            return;
        }
        if (cboAccEmp.getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            cboAccEmp.requestFocus();
            return;
        }
        if (txtNumDocEmp.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            txtNumDocEmp.requestFocus();
            return;
        }
        if (txtTelEmp.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            txtTelEmp.requestFocus();
            return;
        }
        if (txtSalarioEmp.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Datos Incompletos", "Cliente", JOptionPane.WARNING_MESSAGE);
            txtSalarioEmp.requestFocus();
            return;
        }
        

        vEmpleado fDatosEmp = new vEmpleado();
        fEmpleado nFuncEmp = new fEmpleado();

        fDatosEmp.setNomPers(txtNomEmp.getText());
        fDatosEmp.setPrapePers(txtPrimApeEmp.getText());
        fDatosEmp.setSegapePers(txtSegApeEmp.getText());
        fDatosEmp.setTipdocPers(String.valueOf(cboTipDocEmp.getSelectedItem()));
        fDatosEmp.setNumdocPers(txtNumDocEmp.getText());
        fDatosEmp.setDirePers(txtDirEmp.getText());
        fDatosEmp.setTelPers(txtTelEmp.getText());
        fDatosEmp.setEmailPers(txtMailEmp.getText());
        fDatosEmp.setSueldoEmp(Double.valueOf(txtSalarioEmp.getText()));
        fDatosEmp.setAccesoEmp(String.valueOf(cboAccEmp.getSelectedItem()));
        fDatosEmp.setLoginEmp(txtLoginEmp.getText());
        fDatosEmp.setClaveEmp(txtClaveEmp.getText());
        fDatosEmp.setEstEmp(String.valueOf(cboEstadoEmp.getSelectedItem()));
        
        if (Accion.equals("Guardar")) {
            if (nFuncEmp.GuardarEmp(fDatosEmp)) {
                JOptionPane.showMessageDialog(this, "Empleado Guardado Correctamente", "Empleado", JOptionPane.INFORMATION_MESSAGE);
                Mostrar("");
                Inhabilitar();
            }

        } else if (Accion.equals("Editar")) {
            fDatosEmp.setIdPers(Integer.valueOf(txtIDempleado.getText()));
            if (nFuncEmp.EditarEmp(fDatosEmp)) {
                JOptionPane.showMessageDialog(this, "Edición Realizada Correctamente", "Empleado", JOptionPane.INFORMATION_MESSAGE);
                Mostrar("");
                Inhabilitar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblListaEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaEmpMouseClicked
        // TODO add your handling code here:
        btnGuardar.setText("Editar");
        Habilitar();
        btnBorrar.setEnabled(true);
        Accion = "Editar";

        int tFila = tblListaEmp.rowAtPoint(evt.getPoint());
        txtIDempleado.setText(tblListaEmp.getValueAt(tFila, 0).toString());
        txtNomEmp.setText(tblListaEmp.getValueAt(tFila, 1).toString());
        txtPrimApeEmp.setText(tblListaEmp.getValueAt(tFila, 2).toString());
        txtSegApeEmp.setText(tblListaEmp.getValueAt(tFila, 3).toString());
        cboTipDocEmp.setSelectedItem(tblListaEmp.getValueAt(tFila, 4).toString());
        txtNumDocEmp.setText(tblListaEmp.getValueAt(tFila, 5).toString());
        txtDirEmp.setText(tblListaEmp.getValueAt(tFila, 6).toString());
        txtTelEmp.setText(tblListaEmp.getValueAt(tFila, 7).toString());        
        txtMailEmp.setText(tblListaEmp.getValueAt(tFila, 8).toString());
        txtSalarioEmp.setText(tblListaEmp.getValueAt(tFila, 9).toString());
        cboAccEmp.setSelectedItem(tblListaEmp.getValueAt(tFila, 10).toString());
        txtLoginEmp.setText(tblListaEmp.getValueAt(tFila, 11).toString());
        txtClaveEmp.setText(tblListaEmp.getValueAt(tFila, 12).toString());
        cboEstadoEmp.setSelectedItem(tblListaEmp.getValueAt(tFila, 13).toString());
    }//GEN-LAST:event_tblListaEmpMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (txtBuscar.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un criterio", "Empleado", JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
            return;
        } else {
            Mostrar(txtBuscar.getText());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        if (!txtIDempleado.getText().equals("")) {
            int Confirmar = JOptionPane.showConfirmDialog(this, "¿Eliminar el Empleado?", "Confirmar", 2);
            if (Confirmar == 0) {
                fEmpleado nFuncionEmp = new fEmpleado();
                vEmpleado fDatosEmp = new vEmpleado();

                fDatosEmp.setIdPers(Integer.parseInt(txtIDempleado.getText()));
                nFuncionEmp.BorrarEmp(fDatosEmp);
                JOptionPane.showMessageDialog(this, "Registro Borrado", "Empleado", JOptionPane.INFORMATION_MESSAGE);
                Mostrar("");
                Inhabilitar();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro", "Empleado", JOptionPane.WARNING_MESSAGE);
            tblListaEmp.requestFocus();
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "¿Desea cerrar el formulario?", "Empleado",
                    JOptionPane.ERROR_MESSAGE,JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.dispose();
            }
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtMailEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailEmpActionPerformed
        // TODO add your handling code here:
        txtMailEmp.transferFocus();
    }//GEN-LAST:event_txtMailEmpActionPerformed

    private void txtSalarioEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioEmpActionPerformed
        // TODO add your handling code here:
        txtSalarioEmp.transferFocus();
    }//GEN-LAST:event_txtSalarioEmpActionPerformed

    private void txtDirEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirEmpActionPerformed
        // TODO add your handling code here:
        txtDirEmp.transferFocus();
    }//GEN-LAST:event_txtDirEmpActionPerformed

    private void txtSegApeEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSegApeEmpActionPerformed
        // TODO add your handling code here:
        txtSegApeEmp.transferFocus();
    }//GEN-LAST:event_txtSegApeEmpActionPerformed

    private void txtNomEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomEmpActionPerformed
        // TODO add your handling code here:
        txtNomEmp.transferFocus();
    }//GEN-LAST:event_txtNomEmpActionPerformed

    private void txtPrimApeEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimApeEmpActionPerformed
        // TODO add your handling code here:
        txtPrimApeEmp.transferFocus();
    }//GEN-LAST:event_txtPrimApeEmpActionPerformed

    private void cboTipDocEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipDocEmpActionPerformed
        // TODO add your handling code here:
        cboTipDocEmp.transferFocus();
    }//GEN-LAST:event_cboTipDocEmpActionPerformed

    private void txtLoginEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginEmpActionPerformed
        // TODO add your handling code here:
        txtLoginEmp.transferFocus();
    }//GEN-LAST:event_txtLoginEmpActionPerformed

    private void cboEstadoEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoEmpActionPerformed

    private void txtClaveEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveEmpActionPerformed
        // TODO add your handling code here:
        txtClaveEmp.transferFocus();
    }//GEN-LAST:event_txtClaveEmpActionPerformed

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
            java.util.logging.Logger.getLogger(frmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboAccEmp;
    private javax.swing.JComboBox<String> cboEstadoEmp;
    private javax.swing.JComboBox<String> cboTipDocEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTotalReg;
    private javax.swing.JTable tblListaEmp;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JPasswordField txtClaveEmp;
    private javax.swing.JTextField txtDirEmp;
    private javax.swing.JTextField txtIDempleado;
    private javax.swing.JTextField txtLoginEmp;
    private javax.swing.JTextField txtMailEmp;
    private javax.swing.JTextField txtNomEmp;
    private javax.swing.JTextField txtNumDocEmp;
    private javax.swing.JTextField txtPrimApeEmp;
    private javax.swing.JTextField txtSalarioEmp;
    private javax.swing.JTextField txtSegApeEmp;
    private javax.swing.JTextField txtTelEmp;
    // End of variables declaration//GEN-END:variables
}