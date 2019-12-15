/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Chris
 */
public class frmColor extends javax.swing.JFrame {

    /**
     * Creates new form frmMarca
     */
    public frmColor() {
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

        btgColor = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtCodColor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        rbtActColor = new javax.swing.JRadioButton();
        rbtInaColor = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObsColor = new javax.swing.JTextArea();
        btnValidarColor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnGuardarColor = new javax.swing.JButton();
        btnSalirColor = new javax.swing.JButton();
        btnEditarColor = new javax.swing.JButton();
        btnBuscarColor = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cod. Color");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        txtCodColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCodColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtCodColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 109, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Descr. Color");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 150, -1, -1));

        txtColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 146, 295, -1));

        btgColor.add(rbtActColor);
        rbtActColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rbtActColor.setText("Activo");
        getContentPane().add(rbtActColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 181, -1, 30));

        btgColor.add(rbtInaColor);
        rbtInaColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rbtInaColor.setText("Inactivo");
        getContentPane().add(rbtInaColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 181, -1, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Estado");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 184, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Observaciones");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 217, -1, -1));

        txtObsColor.setColumns(20);
        txtObsColor.setRows(5);
        jScrollPane1.setViewportView(txtObsColor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 217, 292, -1));

        btnValidarColor.setBackground(new java.awt.Color(102, 102, 102));
        btnValidarColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnValidarColor.setForeground(new java.awt.Color(255, 255, 255));
        btnValidarColor.setText(">>>");
        btnValidarColor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnValidarColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(btnValidarColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 109, 46, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setText("Gestión Color");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, 55));

        btnGuardarColor.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardarColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuardarColor.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_save_16px.png"))); // NOI18N
        btnGuardarColor.setText("Guardar");
        btnGuardarColor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardarColor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnGuardarColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 111, 116, -1));

        btnSalirColor.setBackground(new java.awt.Color(102, 102, 102));
        btnSalirColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSalirColor.setForeground(new java.awt.Color(255, 255, 255));
        btnSalirColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_close_window_16px.png"))); // NOI18N
        btnSalirColor.setText("Salir");
        btnSalirColor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalirColor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalirColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirColorActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalirColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 240, 116, -1));

        btnEditarColor.setBackground(new java.awt.Color(102, 102, 102));
        btnEditarColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEditarColor.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_edit_property_16px.png"))); // NOI18N
        btnEditarColor.setText("Modificar");
        btnEditarColor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditarColor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnEditarColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 154, 116, -1));

        btnBuscarColor.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscarColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBuscarColor.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_search_more_16px.png"))); // NOI18N
        btnBuscarColor.setText("Buscar");
        btnBuscarColor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarColor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(btnBuscarColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 197, 116, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_paint_palette_96px.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 88));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirColorActionPerformed

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
            java.util.logging.Logger.getLogger(frmColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmColor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup btgColor;
    public javax.swing.JButton btnBuscarColor;
    public javax.swing.JButton btnEditarColor;
    public javax.swing.JButton btnGuardarColor;
    public javax.swing.JButton btnSalirColor;
    public javax.swing.JButton btnValidarColor;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JRadioButton rbtActColor;
    public javax.swing.JRadioButton rbtInaColor;
    public javax.swing.JTextField txtCodColor;
    public javax.swing.JTextField txtColor;
    public javax.swing.JTextArea txtObsColor;
    // End of variables declaration//GEN-END:variables
}