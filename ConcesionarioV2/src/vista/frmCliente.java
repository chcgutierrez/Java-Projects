/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JOptionPane;
import modelo.CiudadCRUD;
import modelo.Cliente;
import modelo.ClienteCRUD;

/**
 *
 * @author Chris
 */
public class frmCliente extends javax.swing.JFrame {

    /**
     * Creates new form frmMarca
     */
    CiudadCRUD controlCiudad = new CiudadCRUD();
    Cliente cliente = new Cliente();
    ClienteCRUD controlCliente = new ClienteCRUD();

    public frmCliente() {
        initComponents();
        jComCiudad.setModel(controlCiudad.TodosCiudadCom());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnValidarCliente = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnGuardarCliente = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComCiudad = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComSexo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComTipo1 = new javax.swing.JComboBox<>();
        btnGuardarCliente1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Tipo Documento");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        txtDocumento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 109, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Nombres");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 295, -1));

        btnValidarCliente.setBackground(new java.awt.Color(102, 102, 102));
        btnValidarCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnValidarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnValidarCliente.setText(">>>");
        btnValidarCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnValidarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(btnValidarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 109, 46, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setText("Gestión Clientes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 18, -1, 55));

        btnGuardarCliente.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuardarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/coche.png"))); // NOI18N
        btnGuardarCliente.setText("Vehiculo");
        btnGuardarCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnGuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 116, -1));

        btnSalir.setBackground(new java.awt.Color(102, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_close_window_16px.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 116, -1));

        btnEditarCliente.setBackground(new java.awt.Color(102, 102, 102));
        btnEditarCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEditarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_16px.png"))); // NOI18N
        btnEditarCliente.setText("Modificar");
        btnEditarCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 154, 116, -1));

        btnBuscarCliente.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscarCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_search_more_16px.png"))); // NOI18N
        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 197, 116, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icon_client.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 6, -1, 88));

        getContentPane().add(jComCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 170, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Documento");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Apellidos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txtApellido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 295, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Sexo");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jComSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        getContentPane().add(jComSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 50, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Dirección");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 295, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Teléfono");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 295, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Email");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 295, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Ciudad");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        txtFecha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 295, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Fecha Nacimiento");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jComTipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CC", "TI", "RC", "PASAPORTE", "CE", "RUT", "NIT" }));
        getContentPane().add(jComTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 130, 30));

        btnGuardarCliente1.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarCliente1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuardarCliente1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_save_16px.png"))); // NOI18N
        btnGuardarCliente1.setText("Guardar");
        btnGuardarCliente1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardarCliente1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCliente1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 111, 116, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCliente1ActionPerformed
        cliente.setNum_doc_cliente(txtDocumento.getText());
        cliente.setNom_cliente(txtNombre.getText());
        int seleccionado = jComTipo1.getSelectedIndex();
        cliente.setTipo_doc_cliente((String) jComTipo1.getItemAt(seleccionado));
        cliente.setApe_cliente(txtApellido.getText());
        seleccionado = jComSexo.getSelectedIndex();
        cliente.setSexo_cliente((String) jComSexo.getItemAt(seleccionado));
        cliente.setDirec_cliente(txtDireccion.getText());
        cliente.setTel_cliente(txtTelefono.getText());
        cliente.setEmail_cliente(txtEmail.getText());
        cliente.setEstado_cliente("A");
        cliente.setFec_nac_cliente(txtFecha.getText());
        seleccionado = jComCiudad.getSelectedIndex();
        String ciudad = (String) jComCiudad.getItemAt(seleccionado);
        cliente.setCiudad_cliente(controlCiudad.codigoCiudad(ciudad));
        cliente.setObs_cliente("");
        boolean valid = controlCliente.GuardarCliente(cliente);
        if (valid) {
            JOptionPane.showMessageDialog(rootPane, "El cliente fue registrado con éxito");
        } else {
            JOptionPane.showMessageDialog(rootPane, "El cliente no se pudo registrar");
        }

    }//GEN-LAST:event_btnGuardarCliente1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarCliente;
    public javax.swing.JButton btnEditarCliente;
    public javax.swing.JButton btnGuardarCliente;
    public javax.swing.JButton btnGuardarCliente1;
    public javax.swing.JButton btnSalir;
    public javax.swing.JButton btnValidarCliente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComCiudad;
    private javax.swing.JComboBox<String> jComSexo;
    private javax.swing.JComboBox<String> jComTipo1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtDocumento;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
