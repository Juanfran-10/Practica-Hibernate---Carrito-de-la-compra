package Vistas;

import Modelos.Articulos;
import Modelos.Familias;
import controladores.CtrlArticulos;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

public class JDArticulos extends java.awt.Dialog {

    private DefaultTableModel dtmArticulos;
    private ArrayList<Articulos> articulos;
    private Familias familia;
    private CtrlArticulos ctrlArticulos = new CtrlArticulos();

    public JDArticulos(java.awt.Frame parent, boolean modal, Familias familia) {
        super(parent, modal);
        initComponents();
        jbVolverAtras.setVisible(false);
        setLocationRelativeTo(null);
        jtArticulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.familia = familia;
        this.dtmArticulos = (DefaultTableModel) jtArticulos.getModel();

        listarArticulos();

        listenerArticulos();
    }

    private void listarArticulos() {
        articulos = familia.getListArticulos();
        if (this.articulos == null) {
            JOptionPane.showMessageDialog(null, "Error al listar la tabla familias", "Error", JOptionPane.ERROR_MESSAGE);
            this.articulos = new ArrayList<>();
        }

        for (Articulos articulo : this.articulos) {
            this.dtmArticulos.addRow(new Object[]{articulo.getCodarticulo(), articulo.getNomarticulo(), articulo.getPrecio()});
        }
    }

    private void listenerArticulos() {
        // Agregamos un ListSelectionListener al modelo de selección de la tabla de familias
        jtArticulos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Este código se ejecuta cuando cambia la selección en la tabla familias
                    // Obtenemos la fila seleccionada en la tabla
                    int selectedRow = jtArticulos.getSelectedRow();
                    if (selectedRow > -1) {
                        String codArticulo = (String) dtmArticulos.getValueAt(selectedRow, 0);
                        String nomArticulo = (String) dtmArticulos.getValueAt(selectedRow, 1);
                        double precio = (double) dtmArticulos.getValueAt(selectedRow, 2);

                        // Establecemos los valores en los JTextField correspondientes
                        jtfCodigoArticulo.setText(codArticulo);
                        jtfNombreArticulo.setText(nomArticulo);
                        jtfPrecio.setText(String.valueOf(precio));
                    }
                }
            }
        });
    }

    private int comprobarCampos(String codArticulo, String nomArticulo, String precio) {
        if (codArticulo.isEmpty()) {
            jtfCodigoArticulo.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el código del artículo o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfCodigoArticulo.setBackground(Color.white);

        if (nomArticulo.isEmpty()) {
            jtfNombreArticulo.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el nombre del artículo o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfNombreArticulo.setBackground(Color.white);

        if (precio.isEmpty()) {
            jtfPrecio.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el precio del artículo o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfPrecio.setBackground(Color.white);

        return 0;
    }

    private void cambiarEstado(boolean estado) {
        jbDarAlta.setEnabled(estado);
        jtfCodigoArticulo.setEnabled(estado);
        jbBuscar.setEnabled(estado);
        jbVolverAtras.setVisible(false);
    }

    private void limpiarCampos() {
        jtfCodigoArticulo.setText("");
        jtfNombreArticulo.setText("");
        jtfPrecio.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbBotones = new javax.swing.JToolBar();
        jbDarAlta = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jbDarBaja = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jbBuscar = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jbEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtArticulos = new javax.swing.JTable();
        jlCodigoArticulo = new javax.swing.JLabel();
        jtfCodigoArticulo = new javax.swing.JTextField();
        jlNombreArticulo = new javax.swing.JLabel();
        jtfNombreArticulo = new javax.swing.JTextField();
        jlPrecio = new javax.swing.JLabel();
        jtfPrecio = new javax.swing.JTextField();
        jbVolverAtras = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1032, 661));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jtbBotones.setBackground(new java.awt.Color(204, 204, 204));
        jtbBotones.setFloatable(false);
        jtbBotones.setRollover(true);
        jtbBotones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
        jtbBotones.add(filler3);

        jbBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/search.png"))); // NOI18N
        jbBuscar.setToolTipText("Pulse este botón para buscar un registro");
        jbBuscar.setFocusable(false);
        jbBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarActionPerformed(evt);
            }
        });
        jtbBotones.add(jbBuscar);
        jtbBotones.add(filler2);

        jbEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/edit.png"))); // NOI18N
        jbEditar.setToolTipText("Pulse este botón para editar un registro, debe ser previamente seleccionado");
        jbEditar.setFocusable(false);
        jbEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarActionPerformed(evt);
            }
        });
        jtbBotones.add(jbEditar);

        jtArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Articulo", "Nombre Articulo", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtArticulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtArticulos);
        if (jtArticulos.getColumnModel().getColumnCount() > 0) {
            jtArticulos.getColumnModel().getColumn(0).setResizable(false);
            jtArticulos.getColumnModel().getColumn(1).setResizable(false);
            jtArticulos.getColumnModel().getColumn(2).setResizable(false);
        }

        jlCodigoArticulo.setText("Código del Artículo");

        jlNombreArticulo.setText("Nombre del Artículo");

        jlPrecio.setText("Precio");

        jbVolverAtras.setText("Volver Atras");
        jbVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVolverAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(223, Short.MAX_VALUE)
                .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCodigoArticulo)
                            .addComponent(jtfCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNombreArticulo)
                            .addComponent(jtfNombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPrecio)
                            .addComponent(jtfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCodigoArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jlNombreArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jlPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jbDarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarAltaActionPerformed
        String codArticulo = jtfCodigoArticulo.getText();
        String nomArticulo = jtfNombreArticulo.getText();
        String precio = jtfPrecio.getText();

        if (comprobarCampos(codArticulo, nomArticulo, precio) == -1) {
            return;
        }

        for (Articulos articulo : articulos) {
            if (codArticulo.equals(articulo.getCodarticulo())) {
                JOptionPane.showMessageDialog(this, "El articulo que quieres introducir ya existe",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                return;
            }
        }

        double precioArt = Double.parseDouble(precio);
        Set facturases = new HashSet(0);

        Articulos a = new Articulos(codArticulo, familia, nomArticulo, precioArt, facturases);
        try {
            ctrlArticulos.guardaArticulo(a);
            dtmArticulos.setRowCount(0);
            familia.addArticulo(a);
            listarArticulos();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbDarAltaActionPerformed

    private void jbDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarBajaActionPerformed
        int respuesta = 0;
        Articulos a = new Articulos();
        String codArticulo = jtfCodigoArticulo.getText();
        String nomArticulo = jtfNombreArticulo.getText();
        String precio = jtfPrecio.getText();

        if (comprobarCampos(codArticulo, nomArticulo, precio) == -1) {
            return;
        }

        double precioArt = Double.parseDouble(precio);

        for (Articulos articulo : articulos) {
            if (codArticulo.equalsIgnoreCase(articulo.getCodarticulo())) {
                a = new Articulos(codArticulo, familia, nomArticulo, precioArt, articulo.getFacturases());
            }
        }

        if (a.getCodarticulo() == null) {
            JOptionPane.showMessageDialog(this, "No existe el articulo que quieres borrar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            return;
        }

        if (a.getFacturases().isEmpty()) {
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar el artículo con código -> " + a.getCodarticulo() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "El artículo que usted quiere borrar contiene Lineas de Factura asociadas",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar el artículo con código -> " + a.getCodarticulo() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        }

        // Verificar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                ctrlArticulos.eliminaArticulo(a);
                dtmArticulos.setRowCount(0);
                familia.removeArticulo(a);
                listarArticulos();
                limpiarCampos();
                cambiarEstado(true);
            } catch (HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbDarBajaActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        String codArticulo = jtfCodigoArticulo.getText();
        String nomArticulo = jtfNombreArticulo.getText();
        String precio = jtfPrecio.getText();

        if (comprobarCampos(codArticulo, nomArticulo, precio) == -1) {
            return;
        }

        Articulos a = new Articulos();
        Articulos artAntiguo = new Articulos();
        double precioArt = Double.parseDouble(precio);

        for (Articulos articulo : articulos) {
            if (codArticulo.equalsIgnoreCase(articulo.getCodarticulo())) {
                artAntiguo = articulo;
                a = new Articulos(codArticulo, familia, nomArticulo, precioArt, articulo.getFacturases());
            }
        }

        if (a.getCodarticulo() == null) {
            JOptionPane.showMessageDialog(this, "No existe el articulo que quieres modificar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            return;
        }

        try {
            ctrlArticulos.actualizaArticulo(a);
            dtmArticulos.setRowCount(0);
            familia.removeArticulo(artAntiguo);
            familia.addArticulo(a);
            listarArticulos();
            limpiarCampos();
            cambiarEstado(true);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        String codArticulo = jtfCodigoArticulo.getText();

        if (codArticulo.isEmpty()) {
            jtfCodigoArticulo.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el código del artículo o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
        jtfCodigoArticulo.setBackground(Color.white);

        Articulos articulo = ctrlArticulos.obtenArticulo(codArticulo);

        if (articulo != null) {
            this.dtmArticulos.setRowCount(0);
            this.dtmArticulos.addRow(new Object[]{articulo.getCodarticulo(), articulo.getNomarticulo(), articulo.getPrecio()});
            cambiarEstado(false);
            this.jbVolverAtras.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No existe la familia que estás buscando",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoArticulo.setText("");
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVolverAtrasActionPerformed
        dtmArticulos.setRowCount(0);
        listarArticulos();
        limpiarCampos();
        cambiarEstado(true);
    }//GEN-LAST:event_jbVolverAtrasActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDArticulos dialog = new JDArticulos(new java.awt.Frame(), true, null);
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
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbBuscar;
    private javax.swing.JButton jbDarAlta;
    private javax.swing.JButton jbDarBaja;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbVolverAtras;
    private javax.swing.JLabel jlCodigoArticulo;
    private javax.swing.JLabel jlNombreArticulo;
    private javax.swing.JLabel jlPrecio;
    private javax.swing.JTable jtArticulos;
    private javax.swing.JToolBar jtbBotones;
    private javax.swing.JTextField jtfCodigoArticulo;
    private javax.swing.JTextField jtfNombreArticulo;
    private javax.swing.JTextField jtfPrecio;
    // End of variables declaration//GEN-END:variables
}
