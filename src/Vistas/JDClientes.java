package Vistas;

import controladores.CtrlClientes;
import Modelos.Clientes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

public class JDClientes extends java.awt.Dialog {

    private DefaultTableModel dtmClientes;
    private CtrlClientes ctrlClientes;
    private List<Clientes> clientes;
    
    public JDClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
        jbVolverAtras.setVisible(false);
        setLocationRelativeTo(null);
        jtClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ctrlClientes = new CtrlClientes();
        this.dtmClientes = (DefaultTableModel) jtClientes.getModel();
        listarClientes();

        listenerFamilias();
    }
    
    private void listarClientes() {
        this.clientes = this.ctrlClientes.obtenerListaClientes();
        if (clientes == null) {
            JOptionPane.showMessageDialog(null, "Error al listar la tabla clientes", "Error", JOptionPane.ERROR_MESSAGE);
            clientes = new ArrayList<>();
        }

        for (Clientes cliente : clientes) {
            this.dtmClientes.addRow(new Object[]{cliente.getCodcliente(), cliente.getNomcliente()});
        }
    }
    
    private void listenerFamilias() {
        // Agregamos un ListSelectionListener al modelo de selección de la tabla de familias
        jtClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Este código se ejecuta cuando cambia la selección en la tabla familias
                    // Obtenemos la fila seleccionada en la tabla
                    int selectedRow = jtClientes.getSelectedRow();
                    if (selectedRow > -1) {
                        String codCliente = (String) dtmClientes.getValueAt(selectedRow, 0);
                        String nomCliente = (String) dtmClientes.getValueAt(selectedRow, 1);
                        // Establecemos los valores en los JTextField correspondientes
                        jtfCodigoCliente.setText(codCliente);
                        jtfNombreCliente.setText(nomCliente);
                    }
                }
            }
        });
    }
    
    private int comprobarCampos(String codCliente, String nomCliente) {
        if (codCliente.isEmpty()) {
            jtfCodigoCliente.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el código del cliente o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfCodigoCliente.setBackground(Color.white);

        if (nomCliente.isEmpty()) {
            jtfNombreCliente.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el nombre del cliente o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfNombreCliente.setBackground(Color.white);
        return 0;
    }
    
    private void cambiarEstado(boolean estado) {
        jbDarAlta.setEnabled(estado);
        jtfCodigoCliente.setEnabled(estado);
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
        jtClientes = new javax.swing.JTable();
        jlCodigoCliente = new javax.swing.JLabel();
        jtfCodigoCliente = new javax.swing.JTextField();
        jlNombreCliente = new javax.swing.JLabel();
        jtfNombreCliente = new javax.swing.JTextField();
        jbVolverAtras = new javax.swing.JButton();

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

        jtClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Cliente", "Nombre Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtClientes);
        if (jtClientes.getColumnModel().getColumnCount() > 0) {
            jtClientes.getColumnModel().getColumn(0).setResizable(false);
            jtClientes.getColumnModel().getColumn(1).setResizable(false);
        }

        jlCodigoCliente.setText("Código de Cliente");

        jlNombreCliente.setText("Nombre de Cliente");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(223, Short.MAX_VALUE)
                .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlNombreCliente)
                        .addComponent(jtfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlCodigoCliente)
                        .addComponent(jtfCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(jlCodigoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlNombreCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jbVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVolverAtrasActionPerformed
        dtmClientes.setRowCount(0);
        listarClientes();
        jtfCodigoCliente.setText("");
        jtfNombreCliente.setText("");
        cambiarEstado(true);
    }//GEN-LAST:event_jbVolverAtrasActionPerformed

    private void jbDarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarAltaActionPerformed
        String codCliente = jtfCodigoCliente.getText();
        String nomCliente = jtfNombreCliente.getText();

        if (comprobarCampos(codCliente, nomCliente) == -1) {
            return;
        }

        for (Clientes cliente : this.clientes) {
            if (codCliente.equals(cliente.getCodcliente())) {
                JOptionPane.showMessageDialog(this, "El cliente que quieres introducir ya existe",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                jtfCodigoCliente.setText("");
                jtfNombreCliente.setText("");
                return;
            }
        }

        Clientes c = new Clientes(codCliente, codCliente, null);
        try {
            ctrlClientes.guardaCliente(c);
            dtmClientes.setRowCount(0);
            listarClientes();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbDarAltaActionPerformed

    private void jbDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarBajaActionPerformed
        int respuesta = 0;
        Clientes c = new Clientes();
        String codCliente = jtfCodigoCliente.getText();
        String nomCliente = jtfNombreCliente.getText();

        if (comprobarCampos(codCliente, nomCliente) == -1) {
            return;
        }

        for (Clientes cliente : this.clientes) {
            if (codCliente.equalsIgnoreCase(cliente.getCodcliente())) {
                c = new Clientes(codCliente, nomCliente, cliente.getFacturases());
            }
        }

        if (c.getCodcliente() == null) {
            JOptionPane.showMessageDialog(this, "No existe el cliente que quieres borrar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoCliente.setText("");
            jtfNombreCliente.setText("");
            return;
        }

        if (c.getFacturases().isEmpty()) {
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar el cliente con código -> " + c.getCodcliente() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "El cliente que usted quiere borrar contiene Facturas asociadas",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar el cliente con código -> " + c.getCodcliente() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        }

        // Verificar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                ctrlClientes.eliminaCliente(c);
                dtmClientes.setRowCount(0);
                listarClientes();
                jtfCodigoCliente.setText("");
                jtfNombreCliente.setText("");
                cambiarEstado(true);
            } catch (HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbDarBajaActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        String codCliente = jtfCodigoCliente.getText();
        String nomCliente = jtfNombreCliente.getText();

        if (comprobarCampos(codCliente, nomCliente) == -1) {
            return;
        }

        Clientes c = new Clientes();
        for (Clientes cliente : this.clientes) {
            if (codCliente.equalsIgnoreCase(cliente.getCodcliente())) {
                c = new Clientes(codCliente, nomCliente, cliente.getFacturases());
            }
        }

        if (c.getCodcliente() == null) {
            JOptionPane.showMessageDialog(this, "No existe el cliente que quieres modificar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoCliente.setText("");
            jtfNombreCliente.setText("");
            return;
        }

        try {
            ctrlClientes.actualizaCliente(c);
            dtmClientes.setRowCount(0);
            listarClientes();
            jtfCodigoCliente.setText("");
            jtfNombreCliente.setText("");
            cambiarEstado(true);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        String codCliente = jtfCodigoCliente.getText();

        if (codCliente.isEmpty()) {
            jtfCodigoCliente.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el código del cliente o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        jtfCodigoCliente.setBackground(Color.white);

        Clientes cliente = ctrlClientes.obtenCliente(codCliente);

        if (cliente != null) {
            this.dtmClientes.setRowCount(0);
            this.dtmClientes.addRow(new Object[]{cliente.getCodcliente(), cliente.getNomcliente()});
            cambiarEstado(false);
            this.jbVolverAtras.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No existe el cliente que estás buscando",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoCliente.setText("");
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDClientes dialog = new JDClientes(new java.awt.Frame(), true);
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
    private javax.swing.JLabel jlCodigoCliente;
    private javax.swing.JLabel jlNombreCliente;
    private javax.swing.JTable jtClientes;
    private javax.swing.JToolBar jtbBotones;
    private javax.swing.JTextField jtfCodigoCliente;
    private javax.swing.JTextField jtfNombreCliente;
    // End of variables declaration//GEN-END:variables
}
