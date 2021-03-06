/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JOptionPane;
import modelo.CiudadCRUD;
import modelo.ColorCRUD;
import modelo.MarcaCRUD;
import modelo.TipoCRUD;
import modelo.Vehiculo;
import modelo.VehiculoCRUD;

/**
 *
 * @author Chris
 */
public class frmVehiculo extends javax.swing.JFrame {

    /**
     * Creates new form frmMarca
     */
    ColorCRUD control_color = new ColorCRUD();
    Vehiculo vehiculo = null;
    VehiculoCRUD control_vehiculo = new VehiculoCRUD();
    CiudadCRUD control_ciudad = new CiudadCRUD();
    MarcaCRUD control_marca = new MarcaCRUD();
    TipoCRUD control_tipo = new TipoCRUD();
    
    public frmVehiculo() {
        initComponents();
        jComColor.setModel(control_color.TodosColorCom());
        jComMarca.setModel(control_marca.TodosMarcaCom());
        jComCiudad.setModel(control_ciudad.TodosCiudadCom());
        jComTipo.setModel(control_tipo.TodosTipoCom());
        btnEditarVehiculo.setVisible(false);
    }
    
    void limpiar(){
       txtPlaca.setText("");
       txtModelo.setText("");
       txtMotor.setText("");
       txtCliente.setText("");
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
        txtPlaca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnValidarVehiculo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnEditarVehiculo = new javax.swing.JButton();
        btnBuscarVehiculo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMotor = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComMarca = new javax.swing.JComboBox<>();
        btnGuardarVehiculo = new javax.swing.JButton();
        jComCiudad = new javax.swing.JComboBox<>();
        jComTipo = new javax.swing.JComboBox<>();
        jComColor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Ciudad");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        txtPlaca.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Marca");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        btnValidarVehiculo.setBackground(new java.awt.Color(102, 102, 102));
        btnValidarVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnValidarVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnValidarVehiculo.setText(">>>");
        btnValidarVehiculo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnValidarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnValidarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarVehiculoActionPerformed(evt);
            }
        });
        getContentPane().add(btnValidarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 46, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setText("Gestión Vehiculos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 18, -1, 55));

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
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 116, -1));

        btnEditarVehiculo.setBackground(new java.awt.Color(102, 102, 102));
        btnEditarVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEditarVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_16px.png"))); // NOI18N
        btnEditarVehiculo.setText("Modificar");
        btnEditarVehiculo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVehiculoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 154, 116, -1));

        btnBuscarVehiculo.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscarVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_search_more_16px.png"))); // NOI18N
        btnBuscarVehiculo.setText("Buscar");
        btnBuscarVehiculo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnBuscarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 197, 116, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/automovil.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, 88));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Placa");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Tipo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Modelo");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Color");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        txtModelo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 295, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Motor");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        txtMotor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtMotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 295, -1));

        txtCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 295, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Cliente");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jComMarca.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        getContentPane().add(jComMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 290, 30));

        btnGuardarVehiculo.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuardarVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_save_16px.png"))); // NOI18N
        btnGuardarVehiculo.setText("Guardar");
        btnGuardarVehiculo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardarVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVehiculoActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 111, 116, -1));

        getContentPane().add(jComCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 130, 30));

        jComTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Automovil", "Camioneta", "Campero" }));
        getContentPane().add(jComTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 290, 30));

        jComColor.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        getContentPane().add(jComColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 290, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnValidarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarVehiculoActionPerformed
        if(txtPlaca.getText() != ""){
            int valid = control_vehiculo.validarVehiculo(txtPlaca.getText());
            if(valid == 1){
            txtPlaca.setEnabled(false);    
            JOptionPane.showMessageDialog(rootPane, "Vehiculo encontrado");    
            vehiculo= control_vehiculo.consultarVehiculo(txtPlaca.getText());
            jComCiudad.setSelectedItem(vehiculo.getCityVeh());
            jComMarca.setSelectedItem(vehiculo.getMarca());
            jComTipo.setSelectedItem(vehiculo.getTipoVeh());
            txtModelo.setText(vehiculo.getModelo());
            jComColor.setSelectedItem(vehiculo.getColor());
            txtMotor.setText(vehiculo.getMotor());
            txtCliente.setText(vehiculo.getClienteVeh());
            btnEditarVehiculo.setVisible(true);
            }
            else if(valid >2){
                JOptionPane.showMessageDialog(rootPane, "Vehiculo se encuentra registrado mas de una vez");
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Vehiculo no se encuentra registrado");
            }
                
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "El campo placa no debe estar vacio");
        }
    }//GEN-LAST:event_btnValidarVehiculoActionPerformed

    private void btnGuardarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVehiculoActionPerformed
        
        vehiculo = new Vehiculo();
        vehiculo.setPlaca(txtPlaca.getText());
        int seleccionado = jComCiudad.getSelectedIndex();
        String ciudad = control_ciudad.codigoCiudad((String) jComCiudad.getItemAt(seleccionado));
        vehiculo.setCityVeh(ciudad);
        seleccionado = jComTipo.getSelectedIndex();
        String tipo = control_tipo.codigoTipo((String) jComTipo.getItemAt(seleccionado));
        vehiculo.setTipoVeh(tipo);
        vehiculo.setModelo(txtModelo.getText());
        seleccionado = jComMarca.getSelectedIndex();
        String marca = control_marca.codigoMarca((String)jComMarca.getItemAt(seleccionado));
        vehiculo.setMarca(marca);
        seleccionado = jComColor.getSelectedIndex();
        String color = control_color.codigoColor((String) jComColor.getItemAt(seleccionado));
        vehiculo.setColor(color);
        vehiculo.setMotor(txtMotor.getText());
        vehiculo.setClienteVeh(txtCliente.getText());
        boolean valid = control_vehiculo.GuardarVehiculo(vehiculo);
        
        if (valid) {
           JOptionPane.showMessageDialog(rootPane, "El vehiculo fue registrado con éxito");
           limpiar();
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "El vehiculo no se pudo registrar");
        }
    }//GEN-LAST:event_btnGuardarVehiculoActionPerformed

    private void btnEditarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVehiculoActionPerformed
        txtPlaca.setEnabled(false);
        vehiculo = new Vehiculo();
        vehiculo.setPlaca(txtPlaca.getText());
        int seleccionado = jComCiudad.getSelectedIndex();
        String ciudad = control_ciudad.codigoCiudad((String) jComCiudad.getItemAt(seleccionado));
        vehiculo.setCityVeh(ciudad);
        seleccionado = jComTipo.getSelectedIndex();
        String tipo = control_tipo.codigoTipo((String) jComTipo.getItemAt(seleccionado));
        vehiculo.setTipoVeh(tipo);
        vehiculo.setModelo(txtModelo.getText());
        seleccionado = jComMarca.getSelectedIndex();
        String marca = control_marca.codigoMarca((String)jComMarca.getItemAt(seleccionado));
        vehiculo.setMarca(marca);
        seleccionado = jComColor.getSelectedIndex();
        String color = control_color.codigoColor((String) jComColor.getItemAt(seleccionado));
        vehiculo.setColor(color);
        vehiculo.setMotor(txtMotor.getText());
        vehiculo.setClienteVeh(txtCliente.getText());
        vehiculo.setEstVeh("A");
        vehiculo.setObsVeh("");
        boolean valid = control_vehiculo.EditarVehiculo(vehiculo);
        if (valid){
           JOptionPane.showMessageDialog(rootPane, "El vehiculo fue actualizado con éxito");
           limpiar();
           txtPlaca.setEnabled(true);
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "El vehiculo no fue actualizado");
        }
    }//GEN-LAST:event_btnEditarVehiculoActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarVehiculo;
    public javax.swing.JButton btnEditarVehiculo;
    public javax.swing.JButton btnGuardarVehiculo;
    public javax.swing.JButton btnSalir;
    public javax.swing.JButton btnValidarVehiculo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComCiudad;
    private javax.swing.JComboBox<String> jComColor;
    private javax.swing.JComboBox<String> jComMarca;
    private javax.swing.JComboBox<String> jComTipo;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JTextField txtCliente;
    public javax.swing.JTextField txtModelo;
    public javax.swing.JTextField txtMotor;
    public javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
