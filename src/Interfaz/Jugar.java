package Interfaz;

import damas.Jugador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;



public final class Jugar extends javax.swing.JFrame {

     private final Jugador[] jugadores;
    private boolean contraIA;
    
public Jugar(Jugador[] jugadores, boolean contraIA) {
    this.jugadores = jugadores;
    this.contraIA = contraIA;

    initComponents(); // generado por NetBeans

    // Limpiar todo lo generado
    getContentPane().removeAll();
    getContentPane().setLayout(new BorderLayout());

    // Panel izquierdo con info y botón
    JPanel panelIzquierdo = new JPanel();
    panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
    panelIzquierdo.setPreferredSize(new Dimension(200, 640));
    panelIzquierdo.setBackground(new java.awt.Color(230, 230, 250)); // lavanda claro

    // Textos de turno y fichas
    textTurno.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 16));
       fichasJugador1 = new JLabel("Fichas " + jugadores[0].getNombre() + ": 0");
    fichasJugador2 = new JLabel("Fichas " + jugadores[1].getNombre() + ": 0");

       

    //  Botón con estilo
    salir.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
    salir.setFocusPainted(false);
    salir.setBackground(Color.WHITE);
    salir.setAlignmentX(Component.CENTER_ALIGNMENT);
    salir.setPreferredSize(new Dimension(100, 40));
    salir.setMaximumSize(new Dimension(120, 40));
    salir.setMinimumSize(new Dimension(100, 30));

    // Agregar componentes al panel izquierdo
    panelIzquierdo.add(Box.createVerticalStrut(20));
    panelIzquierdo.add(textTurno);
    panelIzquierdo.add(Box.createVerticalStrut(10));
    panelIzquierdo.add(fichasJugador1);
    panelIzquierdo.add(fichasJugador2);
    panelIzquierdo.add(Box.createVerticalGlue());
    panelIzquierdo.add(Box.createVerticalStrut(10));
    panelIzquierdo.add(new JSeparator());
    panelIzquierdo.add(Box.createVerticalStrut(10));
    panelIzquierdo.add(salir);
    panelIzquierdo.add(Box.createVerticalStrut(20));

    // ️ Tablero
    tablero = new damas.Tablero(jugadores, this, contraIA);
    tablero.setPreferredSize(new Dimension(640, 640));
         actualizarContadores();


    // ?Ensamblar en el JFrame
    getContentPane().add(panelIzquierdo, BorderLayout.WEST);
    getContentPane().add(tablero, BorderLayout.CENTER);
    // getContentPane().add(relojPanel, BorderLayout.SOUTH); // omitido si no se usa

    // ️ Final
    obtenerTurno();
    iniciarReloj();
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
}







public void actualizarContadores() {
    int fichasJ1 = jugadores[0].getFichas().size() - 12;
    int fichasJ2 = jugadores[1].getFichas().size() - 12;

    fichasJugador1.setText("Fichas " + jugadores[0].getNombre() + ": " + Math.max(0, fichasJ1));
    fichasJugador2.setText("Fichas " + jugadores[1].getNombre() + ": " + Math.max(0, fichasJ2));
}


private javax.swing.JLabel fichasJugador1;
private javax.swing.JLabel fichasJugador2;


public void reiniciarPartida() {
    for (Jugador jugador : jugadores) {
        jugador.nuevasFichas();
    }
    this.dispose();
    new Jugar(jugadores, contraIA).setVisible(true); // Conserva el modo IA o no
}


public void volverAlMenu() {
    this.dispose(); // Cierra esta ventana
    new MenúDeInicio().setVisible(true); // Abre el menú principal (asegúrate de tener la clase Menu)
}



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    protected void initComponents() {

        reloj1 = new Interfaz.Reloj();
        jPanel1 = new javax.swing.JPanel();
        textTurno = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        tablero = new damas.Tablero(jugadores,this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textTurno.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        textTurno.setText("Turno de:");

        salir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(textTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(textTurno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(salir)
                .addGap(114, 114, 114))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloj1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablero, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloj1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(tablero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
        
    
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        for (Jugador jugadore : jugadores) 
            jugadore.nuevasFichas();  
        dispose();
        JOptionPane.showMessageDialog(this, "Para revancha ponga en iniciar partida","¿Revancha?",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_salirActionPerformed

    public void obtenerTurno() {
        textTurno.setText("Es el turno de: " + jugadores[tablero.getTurno()].getNombre());
    }
    

    public void detenerTiempo()  {
        reloj1.detenerTiempo();
    }
 
    public void iniciarReloj(){
        reloj1.iniciarReloj();
    }

    public String devolverReloj(){
        return reloj1.toString();
    }
    
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private Interfaz.Reloj reloj1;
    private javax.swing.JButton salir;
    private damas.Tablero tablero;
    private javax.swing.JLabel textTurno;
    // End of variables declaration//GEN-END:variables

    private void salir() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
