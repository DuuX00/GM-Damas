package Interfaz;

import damas.Jugador;
import javax.swing.JOptionPane;


public class MenúDeInicio extends javax.swing.JFrame {


    private final Jugador[] jugadores;
    
    public MenúDeInicio() {
        initComponents();

        jugadores = new Jugador[2];
        jugadores[0] = new Jugador("0");
        jugadores[1] = new Jugador("1");

        iniciar.setVisible(false);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        iniciar = new javax.swing.JButton();
        reglas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Damas");

        jPanel1.setBackground(new java.awt.Color(193, 154, 107));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Juego de Damas");

        agregar.setText("Agregar Jugadores");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        jLabel2.setText("Se deben agregar jugadores");

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        iniciar.setText("Iniciar Partida local");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });

        reglas.setText("Reglas");
        reglas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reglasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(salir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reglas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iniciar)
                            .addComponent(agregar))
                        .addGap(7, 7, 7)))
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(agregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iniciar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(reglas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salir)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        JOptionPane.showMessageDialog(this, "Hasta luego","ADIOS",JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
         AgregarJugadores agrega = new AgregarJugadores(this, true,jugadores,this);
         
         agrega.setVisible(true);

    }//GEN-LAST:event_agregarActionPerformed

    private void reglasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reglasActionPerformed
        Reglas regla = new Reglas(this, true);
        regla.setVisible(true);
    }//GEN-LAST:event_reglasActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        
        
        String[] opciones = {"Jugador vs Jugador", "Jugador vs IA"};
    int modoSeleccionado = JOptionPane.showOptionDialog(this,
        "Selecciona el modo de juego:",
        "Modo de Juego",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        opciones,
        opciones[0]);

    if (modoSeleccionado == JOptionPane.CLOSED_OPTION) {
        return; // El usuario cerró el diálogo
    }

    boolean contraIA = (modoSeleccionado == 1);

    String nombre1 = jugadores[0].getNombre();
    if (nombre1 == null || nombre1.trim().isEmpty()) nombre1 = "Jugador 1";

    String nombre2;

    if (contraIA) {
        nombre2 = "IA";
    } else {
        nombre2 = jugadores[1].getNombre();
        if (nombre2 == null || nombre2.trim().isEmpty()) nombre2 = "Jugador 2";
    }

    Jugador[] nuevosJugadores = new Jugador[2];
    nuevosJugadores[0] = new Jugador(nombre1);
    nuevosJugadores[1] = new Jugador(nombre2);

    this.dispose(); // Cierra el menú

    // ✅ Aquí está la corrección clave
    Jugar juego = new Jugar(nuevosJugadores, contraIA);
    juego.setVisible(true);
    }//GEN-LAST:event_iniciarActionPerformed

    public void hacerInvisibleAgregar(){
        agregar.setVisible(false);
        jLabel2.setVisible(false);
        iniciar.setVisible(true);
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton reglas;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
