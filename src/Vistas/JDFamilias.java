package Vistas;

import controladores.CtrlFamilias;
import Modelos.Familias;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

public class JDFamilias extends java.awt.Dialog {

    private DefaultTableModel dtmFamilias;
    private CtrlFamilias ctrlFamilia;
    private List<Familias> familias;

    public JDFamilias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jbVolverAtras.setVisible(false);
        setLocationRelativeTo(null);
        jtFamilias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ctrlFamilia = new CtrlFamilias();
        this.dtmFamilias = (DefaultTableModel) jtFamilias.getModel();
        listarFamilias();

        listenerFamilias();
    }

    private void listarFamilias() {
        this.familias = this.ctrlFamilia.obtenerListaFamilias();
        if (familias == null) {
            JOptionPane.showMessageDialog(null, "Error al listar la tabla familias", "Error", JOptionPane.ERROR_MESSAGE);
            familias = new ArrayList<>();
        }

        for (Familias familia : familias) {
            this.dtmFamilias.addRow(new Object[]{familia.getCodfamilia(), familia.getNomfamilia()});
        }
    }

    private void listenerFamilias() {
        // Agregamos un ListSelectionListener al modelo de selección de la tabla de familias
        jtFamilias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Este código se ejecuta cuando cambia la selección en la tabla familias
                    // Obtenemos la fila seleccionada en la tabla
                    int selectedRow = jtFamilias.getSelectedRow();
                    if (selectedRow > -1) {
                        String codFamilia = (String) dtmFamilias.getValueAt(selectedRow, 0);
                        String nomFamilia = (String) dtmFamilias.getValueAt(selectedRow, 1);
                        // Establecemos los valores en los JTextField correspondientes
                        jtfCodigoFamilia.setText(codFamilia);
                        jtfNombreFamilia.setText(nomFamilia);
                    }
                }
            }
        });
    }

    private int comprobarCampos(String codFamilia, String nomFamilia) {
        if (codFamilia.isEmpty()) {
            jtfCodigoFamilia.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el código de la familia o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfCodigoFamilia.setBackground(Color.white);

        if (nomFamilia.isEmpty()) {
            jtfNombreFamilia.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el nombre de la familia o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
        jtfNombreFamilia.setBackground(Color.white);
        return 0;
    }

    private void cambiarEstado(boolean estado) {
        jbDarAlta.setEnabled(estado);
        jtfCodigoFamilia.setEnabled(estado);
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
        jtFamilias = new javax.swing.JTable();
        jlIntroduzcaCodigoFamilia = new javax.swing.JLabel();
        jtfCodigoFamilia = new javax.swing.JTextField();
        jlNombreFamilia = new javax.swing.JLabel();
        jtfNombreFamilia = new javax.swing.JTextField();
        jbListarArticulos = new javax.swing.JButton();
        jbVolverAtras = new javax.swing.JButton();

        setResizable(false);
        setTitle("Gestión de Familias");
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

        jtFamilias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Familia", "Nombre Familia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtFamilias.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtFamilias);
        if (jtFamilias.getColumnModel().getColumnCount() > 0) {
            jtFamilias.getColumnModel().getColumn(0).setResizable(false);
            jtFamilias.getColumnModel().getColumn(1).setResizable(false);
        }

        jlIntroduzcaCodigoFamilia.setText("Código de Familia");

        jlNombreFamilia.setText("Nombre de Familia");

        jbListarArticulos.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jbListarArticulos.setText("Listar Articulos");
        jbListarArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarArticulosActionPerformed(evt);
            }
        });

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
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNombreFamilia)
                            .addComponent(jtfNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlIntroduzcaCodigoFamilia)
                            .addComponent(jtfCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbListarArticulos)
                                .addGap(11, 11, 11))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbVolverAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jlIntroduzcaCodigoFamilia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addComponent(jlNombreFamilia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombreFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbListarArticulos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jbDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarBajaActionPerformed
        int respuesta = 0;
        Familias f = new Familias();
        String codFamilia = jtfCodigoFamilia.getText();
        String nomFamilia = jtfNombreFamilia.getText();

        if (comprobarCampos(codFamilia, nomFamilia) == -1) {
            return;
        }

        for (Familias familia : familias) {
            if (codFamilia.equalsIgnoreCase(familia.getCodfamilia())) {
                f = new Familias(codFamilia, nomFamilia, familia.getArticuloses());
            }
        }

        if (f.getCodfamilia() == null) {
            JOptionPane.showMessageDialog(this, "No existe la familia que quieres borrar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoFamilia.setText("");
            jtfNombreFamilia.setText("");
            return;
        }

        if (f.getArticuloses().isEmpty()) {
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar la familia con código -> " + f.getCodfamilia() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "La familia que usted quiere borrar contiene Artículos asociados",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            respuesta = JOptionPane.showConfirmDialog(this, "¿Está segura/o de que quiere borrar la familia con código -> " + f.getCodfamilia() + " ?",
                    "Petición", JOptionPane.YES_NO_OPTION);
        }

        // Verificar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                ctrlFamilia.eliminaFamilia(f);
                dtmFamilias.setRowCount(0);
                listarFamilias();
                jtfCodigoFamilia.setText("");
                jtfNombreFamilia.setText("");
                cambiarEstado(true);
            } catch (HibernateException he) {
                JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbDarBajaActionPerformed

    private void jbListarArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarArticulosActionPerformed
        int selectedRow = jtFamilias.getSelectedRow();
        if (selectedRow != -1) {
            String codFamilia = (String) dtmFamilias.getValueAt(selectedRow, 0);

            for (Familias familia : familias) {
                if (codFamilia.equals(familia.getCodfamilia())) {
                    JDArticulos dialogArticulos = new JDArticulos(null, true, familia);
                    dialogArticulos.setVisible(true);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar la familia de la que quieres listar sus artículos",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbListarArticulosActionPerformed

    private void jbDarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDarAltaActionPerformed
        String codFamilia = jtfCodigoFamilia.getText();
        String nomFamilia = jtfNombreFamilia.getText();

        if (comprobarCampos(codFamilia, nomFamilia) == -1) {
            return;
        }

        for (Familias familia : familias) {
            if (codFamilia.equals(familia.getCodfamilia())) {
                JOptionPane.showMessageDialog(this, "La familia que quieres introducir ya existe",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                jtfCodigoFamilia.setText("");
                jtfNombreFamilia.setText("");
                return;
            }
        }

        Familias f = new Familias(codFamilia, nomFamilia, null);
        try {
            ctrlFamilia.guardaFamilia(f);
            dtmFamilias.setRowCount(0);
            listarFamilias();
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbDarAltaActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarActionPerformed
        String codFamilia = jtfCodigoFamilia.getText();
        String nomFamilia = jtfNombreFamilia.getText();

        if (comprobarCampos(codFamilia, nomFamilia) == -1) {
            return;
        }

        Familias f = new Familias();
        for (Familias familia : familias) {
            if (codFamilia.equalsIgnoreCase(familia.getCodfamilia())) {
                f = new Familias(codFamilia, nomFamilia, familia.getArticuloses());
            }
        }

        if (f.getCodfamilia() == null) {
            JOptionPane.showMessageDialog(this, "No existe la familia que quieres modificar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoFamilia.setText("");
            jtfNombreFamilia.setText("");
            return;
        }

        try {
            ctrlFamilia.actualizaFamilia(f);
            dtmFamilias.setRowCount(0);
            listarFamilias();
            jtfCodigoFamilia.setText("");
            jtfNombreFamilia.setText("");
            cambiarEstado(true);
        } catch (HibernateException he) {
            JOptionPane.showMessageDialog(this, he.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarActionPerformed
        String codFamilia = jtfCodigoFamilia.getText();

        if (codFamilia.isEmpty()) {
            jtfCodigoFamilia.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Debes introducir el código de la familia o seleccionar un registro en la tabla",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        jtfCodigoFamilia.setBackground(Color.white);

        Familias familia = ctrlFamilia.obtenFamilia(codFamilia);

        if (familia != null) {
            this.dtmFamilias.setRowCount(0);
            this.dtmFamilias.addRow(new Object[]{familia.getCodfamilia(), familia.getNomfamilia()});
            cambiarEstado(false);
            this.jbVolverAtras.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No existe la familia que estás buscando",
                    "Error", JOptionPane.ERROR_MESSAGE);
            jtfCodigoFamilia.setText("");
        }
    }//GEN-LAST:event_jbBuscarActionPerformed

    private void jbVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVolverAtrasActionPerformed
        dtmFamilias.setRowCount(0);
        listarFamilias();
        jtfCodigoFamilia.setText("");
        jtfNombreFamilia.setText("");
        cambiarEstado(true);
    }//GEN-LAST:event_jbVolverAtrasActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDFamilias dialog = new JDFamilias(new java.awt.Frame(), true);
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
    private javax.swing.JLabel jlIntroduzcaCodigoFamilia;
    private javax.swing.JLabel jlNombreFamilia;
    private javax.swing.JTable jtFamilias;
    private javax.swing.JToolBar jtbBotones;
    private javax.swing.JTextField jtfCodigoFamilia;
    private javax.swing.JTextField jtfNombreFamilia;
    // End of variables declaration//GEN-END:variables
}
