package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Alejandro Cencerrado
 */
public class PanelVoluntarios extends javax.swing.JPanel implements ActionListener{
    private OyenteVista oyenteVista;

    /**
     * Constructor
     * 
     * Se inicializan todos los componentes y se definen sus ActionCommand y listeners.
     * @param oyenteVista 
     */
    public PanelVoluntarios(OyenteVista oyenteVista) {
        this.oyenteVista = oyenteVista;
        initComponents();
        
        jButtonInsertar.addActionListener(this);
        jButtonInsertar.setActionCommand("Insertar Voluntario");
        
        jButtonModificar.addActionListener(this);
        jButtonModificar.setActionCommand("Modificar Voluntario");
        
        jButtonEliminar.addActionListener(this);
        jButtonEliminar.setActionCommand("Eliminar Voluntario");
        
        jButtonRecargar.addActionListener(this);
        jButtonRecargar.setActionCommand("Recargar Voluntarios");
        
        jTableDatos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                cambiaJTextFields();
            }
        });
    }
    
    /**
     * Método creado para modificar los jTextField cada vez que cambia la selección de la lista.
     * 
     * Al seleccionar un elemento, la id se vuelve ineditable y se activan los botones de editar y eliminar,
     * mientras que si no hay elemento seleccionado se permite insertar y no modificar ni elminar.
     * 
     */
    public void cambiaJTextFields(){
        int filaSeleccionada = jTableDatos.getSelectedRow();
        String id = jTableDatos.getValueAt(filaSeleccionada, 0).toString();
        jTextFieldId.setText(id);
        
        jTextFieldId.setEditable(id.equals(""));

        jTextFieldNombre.setText(jTableDatos.getValueAt(filaSeleccionada, 1).toString());
        jTextFieldTelefono.setText(jTableDatos.getValueAt(filaSeleccionada, 2).toString());
        jTextFieldCorreo.setText(jTableDatos.getValueAt(filaSeleccionada, 3).toString());
        jTextFieldCoordenadaX.setText(jTableDatos.getValueAt(filaSeleccionada, 4).toString());
        jTextFieldCoordenadaY.setText(jTableDatos.getValueAt(filaSeleccionada, 5).toString());
        jTextFieldEsConductor.setText(jTableDatos.getValueAt(filaSeleccionada, 6).toString());
        jTextFieldEstaDisponible.setText(jTableDatos.getValueAt(filaSeleccionada, 7).toString());
        
        jButtonInsertar.setEnabled(id.equals(""));
        jButtonModificar.setEnabled(!id.equals(""));
        jButtonEliminar.setEnabled(!id.equals(""));
    }
    

    @Override
     public void actionPerformed(ActionEvent e) {
        notificacionAControl(e.getActionCommand());
    }
     
    /**
     * Método que recibe envía la notificación a control
     * @param evento 
     */
    public void notificacionAControl(String evento){
       
      switch(evento) {
        
        case "Recargar Voluntarios":
           oyenteVista.notificacion(OyenteVista.Evento.VER_PANEL_VOLUNTARIOS, null);
           break;
        
      
        case "Insertar Voluntario":
            oyenteVista.notificacion(OyenteVista.Evento.INSERTAR_VOLUNTARIO, null);
            break;
            
        case "Eliminar Voluntario":
            oyenteVista.notificacion(OyenteVista.Evento.ELIMINAR_VOLUNTARIO, null);
            break;  
      
        case "Modificar Voluntario":
            oyenteVista.notificacion(OyenteVista.Evento.MODIFICAR_VOLUNTARIO, null);
            break;  
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jTextFieldCoordenadaX = new javax.swing.JTextField();
        jTextFieldCoordenadaY = new javax.swing.JTextField();
        jTextFieldEstaDisponible = new javax.swing.JTextField();
        jButtonRecargar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonInsertar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldEsConductor = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Voluntarios");

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDatosMouseClicked(evt);
            }
        });
        jTableDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableDatosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDatos);

        jLabel2.setText("ID:");

        jLabel3.setText("Correo:");

        jLabel4.setText("Coordenada X:");

        jLabel5.setText("Coordenada Y:");

        jLabel6.setText("Está Disponible:");

        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        jButtonRecargar.setText("Recargar");

        jButtonModificar.setText("Modificar");

        jButtonEliminar.setText("Eliminar");

        jButtonInsertar.setText("Insertar");
        jButtonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre:");

        jLabel8.setText("Teléfono:");

        jLabel9.setText("Es Conductor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(97, 97, 97)
                        .addComponent(jTextFieldId))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonInsertar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModificar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRecargar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCoordenadaX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldCoordenadaY, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldEsConductor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldEstaDisponible, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jTextFieldCorreo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldTelefono))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCoordenadaX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldCoordenadaY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldEsConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldEstaDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRecargar)
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonInsertar)
                    .addComponent(jButtonEliminar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdActionPerformed

    private void jButtonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsertarActionPerformed

    private void jTableDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDatosMouseClicked

    }//GEN-LAST:event_jTableDatosMouseClicked

    private void jTableDatosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDatosKeyPressed
        
    }//GEN-LAST:event_jTableDatosKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonEliminar;
    public javax.swing.JButton jButtonInsertar;
    public javax.swing.JButton jButtonModificar;
    public javax.swing.JButton jButtonRecargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableDatos;
    public javax.swing.JTextField jTextFieldCoordenadaX;
    public javax.swing.JTextField jTextFieldCoordenadaY;
    public javax.swing.JTextField jTextFieldCorreo;
    public javax.swing.JTextField jTextFieldEsConductor;
    public javax.swing.JTextField jTextFieldEstaDisponible;
    public javax.swing.JTextField jTextFieldId;
    public javax.swing.JTextField jTextFieldNombre;
    public javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}