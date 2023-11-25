package Vistas;

public class JFPrincipal extends javax.swing.JFrame {

    public JFPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbBotones = new javax.swing.JToolBar();
        jbFamilias = new javax.swing.JButton();
        jbClientes = new javax.swing.JButton();
        jbFacturas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Practica de Hibernate Juan Francisco Muñoz Perez");
        setResizable(false);

        jtbBotones.setRollover(true);

        jbFamilias.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbFamilias.setText("Familias");
        jbFamilias.setFocusable(false);
        jbFamilias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbFamilias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbFamilias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFamiliasActionPerformed(evt);
            }
        });
        jtbBotones.add(jbFamilias);

        jbClientes.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbClientes.setText("Clientes");
        jbClientes.setFocusable(false);
        jbClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClientesActionPerformed(evt);
            }
        });
        jtbBotones.add(jbClientes);

        jbFacturas.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jbFacturas.setText("Facturas");
        jbFacturas.setFocusable(false);
        jbFacturas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbFacturas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFacturasActionPerformed(evt);
            }
        });
        jtbBotones.add(jbFacturas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(460, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbFamiliasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFamiliasActionPerformed
        JDFamilias dialogFamilias = new JDFamilias(this, true);
        dialogFamilias.setVisible(true);
    }//GEN-LAST:event_jbFamiliasActionPerformed

    private void jbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbClientesActionPerformed
        JDClientes dialogClientes = new JDClientes(this, true);
        dialogClientes.setVisible(true);
    }//GEN-LAST:event_jbClientesActionPerformed

    private void jbFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFacturasActionPerformed
        JDFacturas dialogFacturas = new JDFacturas(this, true);
        dialogFacturas.setVisible(true);
    }//GEN-LAST:event_jbFacturasActionPerformed
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
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbClientes;
    private javax.swing.JButton jbFacturas;
    private javax.swing.JButton jbFamilias;
    private javax.swing.JToolBar jtbBotones;
    // End of variables declaration//GEN-END:variables
}
