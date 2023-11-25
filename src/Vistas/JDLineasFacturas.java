package Vistas;

import Modelos.Articulos;
import Modelos.Facturas;
import controladores.CtrlArticulos;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

public class JDLineasFacturas extends java.awt.Dialog {

    private Facturas factura;
    private CtrlArticulos ctrlArticulos = new CtrlArticulos();
    private List<Articulos> articulos;
    private DefaultTableModel dtmLineasFacturas;

    public JDLineasFacturas(java.awt.Frame parent, boolean modal, Facturas factura) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.factura = factura;
        this.dtmLineasFacturas = (DefaultTableModel) jtLineasFacturas.getModel();

        listarLineasFacturas();
    }

    private void listarLineasFacturas() {
        this.articulos = this.ctrlArticulos.obtenerListaArticulos();
        if (articulos == null) {
            JOptionPane.showMessageDialog(null, "Error al listar la tabla familias", "Error", JOptionPane.ERROR_MESSAGE);
            articulos = new ArrayList<>();
        }
        for (Articulos articulo : articulos) {
            jcbArticulos.addItem(articulo.getCodarticulo());
            for (Facturas f : articulo.getListFacturas()) {
                if (factura.getNumfactura() == f.getNumfactura()) {
                    this.dtmLineasFacturas.addRow(new Object[]{factura.getNumfactura(), articulo.getCodarticulo()});
                }
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbBotones = new javax.swing.JToolBar();
        jbDarAlta = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jbDarBaja = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLineasFacturas = new javax.swing.JTable();
        jcbArticulos = new javax.swing.JComboBox<>();
        jlArticulos = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jtbBotones.setBackground(new java.awt.Color(204, 204, 204));
        jtbBotones.setFloatable(false);
        jtbBotones.setRollover(true);

        jbDarAlta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        jbDarAlta.setToolTipText("Pulse este botón para añadir un registro");
        jbDarAlta.setFocusable(false);
        jbDarAlta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbDarAlta.setInheritsPopupMenu(true);
        jbDarAlta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbDarAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDarAltaActionPerformed(evt);
            }
        });
        jtbBotones.add(jbDarAlta);
        jtbBotones.add(filler1);

        jbDarBaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/remove.png"))); // NOI18N
        jbDarBaja.setToolTipText("Pulse este botón para borrar un registro, debe ser previamente seleccionado");
        jbDarBaja.setFocusable(false);
        jbDarBaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbDarBaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbDarBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDarBajaActionPerformed(evt);
            }
        });
        jtbBotones.add(jbDarBaja);

        jtLineasFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Factura", "Código Artículo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLineasFacturas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtLineasFacturas);

        jlArticulos.setText("Artículo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbArticulos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlArticulos)
                                .addGap(0, 142, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlArticulos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jbDarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarAltaActionPerformed
        String codArticulo = (String) jcbArticulos.getSelectedItem();

        Articulos art = ctrlArticulos.obtenArticulo(codArticulo);
        try {
            this.factura.addArticulo(art);
            art.addFactura(factura);
            dtmLineasFacturas.setRowCount(0);
            listarLineasFacturas();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbDarAltaActionPerformed

    private void jbDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarBajaActionPerformed
    }//GEN-LAST:event_jbDarBajaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDLineasFacturas dialog = new JDLineasFacturas(new java.awt.Frame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbDarAlta;
    private javax.swing.JButton jbDarBaja;
    private javax.swing.JComboBox<String> jcbArticulos;
    private javax.swing.JLabel jlArticulos;
    private javax.swing.JTable jtLineasFacturas;
    private javax.swing.JToolBar jtbBotones;
    // End of variables declaration//GEN-END:variables
}
