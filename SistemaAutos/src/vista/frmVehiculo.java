/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JFileChooser;

/**
 *
 * @author Chris
 */
public class frmVehiculo extends javax.swing.JFrame {

    /**
     * Creates new form frmMarca
     */
    public frmVehiculo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgEst = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btgSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnValCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rdbEstA = new javax.swing.JRadioButton();
        rdbEstI = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaObsCliente = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtNomCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboDocCliente = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtApeCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDirCliente = new javax.swing.JTextField();
        txtTelCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboCiuCliente = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        cboDocCliente1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboDocCliente2 = new javax.swing.JComboBox<>();
        jToolBar1 = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Placa");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 10, -1, -1));

        btnValCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnValCliente.setText(">>>");
        btnValCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnValCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 60, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Marca");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 48, -1, -1));

        rdbEstA.setBackground(new java.awt.Color(204, 204, 255));
        btgEst.add(rdbEstA);
        rdbEstA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdbEstA.setText("Activo");
        rdbEstA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbEstAActionPerformed(evt);
            }
        });
        jPanel1.add(rdbEstA, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 135, -1, 20));

        rdbEstI.setBackground(new java.awt.Color(204, 204, 255));
        btgEst.add(rdbEstI);
        rdbEstI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdbEstI.setText("Inactivo");
        jPanel1.add(rdbEstI, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 135, -1, 20));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txaObsCliente.setColumns(20);
        txaObsCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txaObsCliente.setRows(5);
        txaObsCliente.setPreferredSize(new java.awt.Dimension(320, 104));
        jScrollPane1.setViewportView(txaObsCliente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 263, 480, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Observaciones");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        txtNomCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomCliente.setText("1987");
        txtNomCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 83, 60, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Modelo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 85, -1, -1));

        cboDocCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboDocCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDocClienteActionPerformed(evt);
            }
        });
        jPanel1.add(cboDocCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 45, 180, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Motor");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 85, -1, -1));

        txtApeCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtApeCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApeCliente.setText("2JU98ADS-9R");
        txtApeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApeClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtApeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 83, 120, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Propietario");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 175, -1, -1));

        txtDirCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDirCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDirCliente.setText("1030538949");
        txtDirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtDirCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 173, 100, -1));

        txtTelCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtTelCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTelCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTelCliente.setText("CHRISTIAN CAMILO GUTIERREZ MONTOYA");
        txtTelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtTelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 173, 300, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ciudad");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 210, -1, -1));

        cboCiuCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboCiuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCiuClienteActionPerformed(evt);
            }
        });
        jPanel1.add(cboCiuCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 207, 210, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 115, 163, 50));

        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.setText("SFB - 594");
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jFormattedTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 90, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tipo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 50, -1, -1));

        cboDocCliente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboDocCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDocCliente1ActionPerformed(evt);
            }
        });
        jPanel1.add(cboDocCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 45, 180, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Color");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 130, -1, -1));

        cboDocCliente2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboDocCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDocCliente2ActionPerformed(evt);
            }
        });
        jPanel1.add(cboDocCliente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 127, 180, -1));

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);

        btnNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\new_file_28px.png")); // NOI18N
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnNuevo);

        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\edit_file_28px.png")); // NOI18N
        btnEditar.setToolTipText("Modificar");
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnEditar);

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\undo_28px.png")); // NOI18N
        btnCancelar.setToolTipText("Deshacer");
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCancelar);

        btnBorrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\delete_file_28px.png")); // NOI18N
        btnBorrar.setToolTipText("Eliminar");
        btnBorrar.setFocusable(false);
        btnBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBorrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnBorrar);

        btnBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\view_file_28px.png")); // NOI18N
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnBuscar);

        btnGuardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\save_28px.png")); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnGuardar);

        btnCargar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\circled_up_2_28px.png")); // NOI18N
        btnCargar.setToolTipText("Cargar");
        btnCargar.setFocusable(false);
        btnCargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCargar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCargar);

        btnImprimir.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\print_28px.png")); // NOI18N
        btnImprimir.setToolTipText("Imprimir");
        btnImprimir.setFocusable(false);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnImprimir);

        btnCerrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\img\\shutdown_28px.png")); // NOI18N
        btnCerrar.setToolTipText("Salir");
        btnCerrar.setFocusable(false);
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCerrar);

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edición");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Opciones");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Herramientas");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ayuda");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbEstAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbEstAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbEstAActionPerformed

    private void txtNomClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomClienteActionPerformed

    private void btnValClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnValClienteActionPerformed

    private void cboDocClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDocClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDocClienteActionPerformed

    private void txtApeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeClienteActionPerformed

    private void txtDirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirClienteActionPerformed

    private void txtTelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelClienteActionPerformed

    private void cboCiuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCiuClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCiuClienteActionPerformed

    private void cboDocCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDocCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDocCliente1ActionPerformed

    private void cboDocCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDocCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDocCliente2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup btgEst;
    public javax.swing.ButtonGroup btgSexo;
    public javax.swing.JButton btnBorrar;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnCargar;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnImprimir;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JButton btnValCliente;
    public javax.swing.JComboBox<String> cboCiuCliente;
    public javax.swing.JComboBox<String> cboDocCliente;
    public javax.swing.JComboBox<String> cboDocCliente1;
    public javax.swing.JComboBox<String> cboDocCliente2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public javax.swing.JToolBar jToolBar1;
    public javax.swing.JRadioButton rdbEstA;
    public javax.swing.JRadioButton rdbEstI;
    public javax.swing.JTextArea txaObsCliente;
    public javax.swing.JTextField txtApeCliente;
    public javax.swing.JTextField txtDirCliente;
    public javax.swing.JTextField txtNomCliente;
    public javax.swing.JTextField txtTelCliente;
    // End of variables declaration//GEN-END:variables

}
