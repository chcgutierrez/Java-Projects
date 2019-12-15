/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.fHabitacion;

/**
 *
 * @author Chris
 */
public class frmVistaHab extends javax.swing.JFrame {

    /**
     * Creates new form frmVistaHab
     */
    public frmVistaHab() {
        initComponents();
        Mostrar("");
        this.setLocationRelativeTo(null);
    }
    
    void OcultarCols() {
        tblVistaHab.getColumnModel().getColumn(0).setMaxWidth(0);
        tblVistaHab.getColumnModel().getColumn(0).setMinWidth(0);
        tblVistaHab.getColumnModel().getColumn(0).setPreferredWidth(0);;
    }
    
    void Mostrar(String buscar) {
        try {
            DefaultTableModel fModelo;
            fHabitacion nFuncion = new fHabitacion();
            fModelo = nFuncion.VistaHab(buscar);
            tblVistaHab.setModel(fModelo);
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVistaHab = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblTotalReg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Habitaciones Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblVistaHab.setBackground(new java.awt.Color(255, 255, 255));
        tblVistaHab.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        tblVistaHab.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVistaHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVistaHabMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVistaHabMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblVistaHab);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, 471, 180));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel9.setText("Buscar:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 33, -1, -1));

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 31, 230, -1));

        btnBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icons8_search_more_16px_2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 28, 79, 30));

        lblTotalReg.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lblTotalReg.setText("Registros:");
        jPanel2.add(lblTotalReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVistaHabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVistaHabMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblVistaHabMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (txtBuscar.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un criterio", "Habitación", JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
            return;
        } else {
            Mostrar(txtBuscar.getText());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblVistaHabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVistaHabMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount()==2) {
            
            int filaSel=tblVistaHab.getSelectedRow();
            String codHab, valorCod;
            codHab=tblVistaHab.getValueAt(filaSel, 0).toString();
            valorCod=tblVistaHab.getValueAt(filaSel, 1).toString();
            
            frmReserva.txtIDhabitacion.setText(codHab);
            frmReserva.txtNumHab.setText(valorCod);
            this.dispose();
        }
    }//GEN-LAST:event_tblVistaHabMousePressed

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
            java.util.logging.Logger.getLogger(frmVistaHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVistaHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVistaHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVistaHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVistaHab().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTotalReg;
    private javax.swing.JTable tblVistaHab;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}