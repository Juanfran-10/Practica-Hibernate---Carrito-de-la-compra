package Vistas;

import Modelos.Articulos;
import Modelos.Clientes;
import Modelos.Facturas;
import controladores.CtrlClientes;
import controladores.CtrlFacturas;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

public class JDFacturas extends java.awt.Dialog {

    private DefaultTableModel dtmFacturas;
    private CtrlFacturas ctrlFacturas;
    private CtrlClientes ctrlClientes;
    private List<Facturas> facturas;
    private ArrayList<Clientes> clientes;
    private ArrayList<Articulos> articulos;

    public JDFacturas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jbVolverAtras.setVisible(false);
        this.jdcFechaFactura.getDateEditor().setEnabled(false);
        this.jdcFechaFactura.setDateFormatString("yyyy-MM-dd");
        setLocationRelativeTo(null);
        jtFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ctrlFacturas = new CtrlFacturas();
        ctrlClientes = new CtrlClientes();
        this.dtmFacturas = (DefaultTableModel) jtFacturas.getModel();
        listarFacturas();
        listarClientes();

        listenerFacturas();

    }

    private void listarFacturas() {
        this.facturas = this.ctrlFacturas.obtenerListaFacturas();
        if (facturas == null) {
            JOptionPane.showMessageDialog(null, "Error al listar la tabla familias", "Error", JOptionPane.ERROR_MESSAGE);
            facturas = new ArrayList<>();
        }

        clientes = new ArrayList<>();

        for (Facturas factura : facturas) {
            this.dtmFacturas.addRow(new Object[]{factura.getNumfactura(), factura.getFechafactura(), factura.getClientes().getCodcliente()});
        }
    }

    private void listarClientes() {
        clientes = (ArrayList<Clientes>) this.ctrlClientes.obtenerListaClientes();
        for (Clientes cliente : clientes) {
            jcbCodCliente.addItem(cliente.getCodcliente());
        }
    }

    private void listarArticulos(Facturas f) {
        articulos = (ArrayList<Articulos>) f.getListArticulos();
        for (Articulos articulo : articulos) {
            System.out.println(articulo.toString());
        }
    }

    private void limpiarCampos() {
        jtfNumeroFactura.setText("");
        jdcFechaFactura.setDate(null);
    }

    private void listenerFacturas() {
        // Agregamos un ListSelectionListener al modelo de selección de la tabla de familias
        jtFacturas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Este código se ejecuta cuando cambia la selección en la tabla familias
                    // Obtenemos la fila seleccionada en la tabla
                    int selectedRow = jtFacturas.getSelectedRow();
                    if (selectedRow != -1) {
                        int numFactura = (int) dtmFacturas.getValueAt(selectedRow, 0);
                        Date fecha = (Date) dtmFacturas.getValueAt(selectedRow, 1);
                        String codCliente = (String) dtmFacturas.getValueAt(selectedRow, 2);
                        // Establecemos los valores en los JTextField correspondientes
                        jtfNumeroFactura.setText(String.valueOf(numFactura));
                        jdcFechaFactura.setDate(fecha);
                        jcbCodCliente.removeAllItems();
                        jcbCodCliente.addItem(codCliente);
                    } else {
                        jcbCodCliente.removeAllItems();
                        limpiarCampos();
                        for (Clientes cliente : clientes) {
                            jcbCodCliente.addItem(cliente.getCodcliente());
                        }
                    }
                }
            }
        });
    }

    private int comprobarCampos(String numFactura, Date fecha) {
        if (numFactura.isEmpty()) {
            jtfNumeroFactura.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el número de la factura o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfNumeroFactura.setBackground(Color.white);

        if (fecha == null) {
            jdcFechaFactura.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir la fecha para la factura o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jdcFechaFactura.setBackground(Color.white);
        return 0;
    }

    private void cambiarEstado(boolean estado) {
        jbDarAlta.setEnabled(estado);
        jtfNumeroFactura.setEnabled(estado);
        jbBuscar.setEnabled(estado);
        jbVolverAtras.setVisible(false);
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
        jtFacturas = new javax.swing.JTable();
        jlNumeroFactura = new javax.swing.JLabel();
        jtfNumeroFactura = new javax.swing.JTextField();
        jlFechaFactura = new javax.swing.JLabel();
        jdcFechaFactura = new com.toedter.calendar.JDateChooser();
        jlCodigoCliente = new javax.swing.JLabel();
        jcbCodCliente = new javax.swing.JComboBox<>();
        jbVolverAtras = new javax.swing.JButton();
        jbListarArticulos = new javax.swing.JButton();

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

        jtFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Factura", "Fecha Factura", "Código Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFacturas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtFacturas);
        if (jtFacturas.getColumnModel().getColumnCount() > 0) {
            jtFacturas.getColumnModel().getColumn(0).setResizable(false);
            jtFacturas.getColumnModel().getColumn(1).setResizable(false);
            jtFacturas.getColumnModel().getColumn(2).setResizable(false);
        }

        jlNumeroFactura.setText("Número de Factura");

        jlFechaFactura.setText("Fecha de la Factura");

        jlCodigoCliente.setText("Código de Cliente");

        jbVolverAtras.setText("Volver Atras");
        jbVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVolverAtrasActionPerformed(evt);
            }
        });

        jbListarArticulos.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jbListarArticulos.setText("Listar Lineas_Facturas");
        jbListarArticulos.setActionCommand("Listar");
        jbListarArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarArticulosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlNumeroFactura)
                                    .addComponent(jtfNumeroFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(jlFechaFactura)
                                    .addComponent(jdcFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlCodigoCliente)
                                    .addComponent(jcbCodCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jbListarArticulos)
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jlCodigoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlNumeroFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jlFechaFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jbListarArticulos))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jbDarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarAltaActionPerformed
        String numFactura = jtfNumeroFactura.getText();
        Date fecha = jdcFechaFactura.getDate();
        String codCliente = (String) jcbCodCliente.getSelectedItem();

        if (comprobarCampos(numFactura, fecha) == -1) {
            return;
        }

        int numeroFactura = Integer.parseInt(numFactura);

        for (Facturas factura : facturas) {
            if (numeroFactura == factura.getNumfactura()) {
                JOptionPane.showMessageDialog(this, "La factura que quieres introducir ya existe",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                return;
            }
        }

        if (numeroFactura <= 0) {
            JOptionPane.showMessageDialog(this, "La factura no puede tener un número de factura que sea negativo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Facturas f = new Facturas(numeroFactura, ctrlClientes.obtenCliente(codCliente), fecha, null);
        try {
            ctrlFacturas.guardaFactura(f);
            dtmFacturas.setRowCount(0);
            listarFacturas();
            limpiarCampos();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbDarAltaActionPerformed

    private void jbDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarBajaActionPerformed
        int respuesta = 0;
        Facturas f = new Facturas();
        String numFactura = jtfNumeroFactura.getText();
        Date fecha = jdcFechaFactura.getDate();
        String codCliente = (String) jcbCodCliente.getSelectedItem();

        if (comprobarCampos(numFactura, fecha) == -1) {
            return;
        }

        int numeroFactura = Integer.parseInt(numFactura);

        for (Facturas factura : facturas) {
            if (numeroFactura == factura.getNumfactura()) {
                f = new Facturas(numeroFactura, ctrlClientes.obtenCliente(codCliente), fecha, factura.getArticuloses());
            }
        }

        if (f.getNumfactura() == 0) {
            JOptionPane.showMessageDialog(this, "No existe la factura que quieres borrar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            return;
        }

        if (f.getArticuloses().isEmpty()) {
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar la factura con número -> " + f.getNumfactura() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "La factura que usted quiere borrar contiene Lineas de factura asociados",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar la factura con número -> " + f.getNumfactura() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        }

        // Verificar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                ctrlFacturas.eliminaFactura(f);
                dtmFacturas.setRowCount(0);
                listarFacturas();
                limpiarCampos();
                cambiarEstado(true);
            } catch (HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbDarBajaActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        String numFactura = jtfNumeroFactura.getText();
        Date fecha = jdcFechaFactura.getDate();
        String codCliente = (String) jcbCodCliente.getSelectedItem();

        if (comprobarCampos(numFactura, fecha) == -1) {
            return;
        }

        int numeroFactura = Integer.parseInt(numFactura);

        Facturas f = new Facturas();
        for (Facturas factura : facturas) {
            if (numeroFactura == factura.getNumfactura()) {
                f = new Facturas(numeroFactura, ctrlClientes.obtenCliente(codCliente), fecha, factura.getArticuloses());
            }
        }

        if (f.getNumfactura() == 0) {
            JOptionPane.showMessageDialog(this, "No existe la factura que quieres editar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
            return;
        }

        try {
            ctrlFacturas.actualizaFactura(f);
            dtmFacturas.setRowCount(0);
            listarFacturas();
            limpiarCampos();
            cambiarEstado(true);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVolverAtrasActionPerformed
        dtmFacturas.setRowCount(0);
        listarFacturas();
        listarClientes();
        limpiarCampos();
        cambiarEstado(true);
    }//GEN-LAST:event_jbVolverAtrasActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        String numFactura = jtfNumeroFactura.getText();

        if (numFactura.isEmpty()) {
            jtfNumeroFactura.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el número de la factura o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        jtfNumeroFactura.setBackground(Color.white);

        int numeroFactura = Integer.parseInt(numFactura);

        Facturas factura = ctrlFacturas.obtenFactura(numeroFactura);

        if (factura != null) {
            this.dtmFacturas.setRowCount(0);
            this.dtmFacturas.addRow(new Object[]{factura.getNumfactura(), factura.getFechafactura(), factura.getClientes().getCodcliente()});

            jtFacturas.setRowSelectionInterval(0, 0);
            listarArticulos(factura);
            cambiarEstado(false);
            this.jbVolverAtras.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No existe la factura que estás buscando",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfNumeroFactura.setText("");
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbListarArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarArticulosActionPerformed
        int selectedRow = jtFacturas.getSelectedRow();
        if (selectedRow != -1) {
            int numFactura = (int) dtmFacturas.getValueAt(selectedRow, 0);

            for (Facturas factura : facturas) {
                if (numFactura == factura.getNumfactura()) {
                    JDLineasFacturas dialogLineasFacturas = new JDLineasFacturas(null, true, factura);
                    dialogLineasFacturas.setVisible(true);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar la factura de la que quieres listar sus lineas de facturas",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbListarArticulosActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDFacturas dialog = new JDFacturas(new java.awt.Frame(), true);
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
    private javax.swing.JButton jbListarArticulos;
    private javax.swing.JButton jbVolverAtras;
    private javax.swing.JComboBox<String> jcbCodCliente;
    private com.toedter.calendar.JDateChooser jdcFechaFactura;
    private javax.swing.JLabel jlCodigoCliente;
    private javax.swing.JLabel jlFechaFactura;
    private javax.swing.JLabel jlNumeroFactura;
    private javax.swing.JTable jtFacturas;
    private javax.swing.JToolBar jtbBotones;
    private javax.swing.JTextField jtfNumeroFactura;
    // End of variables declaration//GEN-END:variables
}
