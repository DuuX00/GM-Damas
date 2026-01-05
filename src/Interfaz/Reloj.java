package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Reloj extends javax.swing.JPanel implements ActionListener{

    private final Timer tiempo;

    private int horas;
    private int minutos;
    private int segundos;
            

    public Reloj() {
    initComponents();
    horas = 0;
    minutos = 0;
    segundos = 0;
    tiempo = new Timer(1000, this);

    // 👉 SOLUCIÓN para forzar que se vea bien
    setPreferredSize(new java.awt.Dimension(300, 60)); // tamaño visible
    setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)); // disposición clara
}


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        hrs = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        seg = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(193, 154, 107));

        hrs.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hrs.setText("00");

        min.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        min.setText(": 00");

        seg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        seg.setText(": 00");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Tiempo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(hrs, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(min, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(seg, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hrs)
                    .addComponent(min)
                    .addComponent(seg))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hrs;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel min;
    private javax.swing.JLabel seg;
    // End of variables declaration//GEN-END:variables
    

    private String menorQueDiez(int numero){
       if(numero < 10)
           return "0" + numero;
       else
           return "" + numero;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        segundos++;
        if (segundos == 60) {
            minutos++;
            segundos = 0;
        }
        if(minutos == 60){
            horas++;
            minutos = 0;
        }
        
        hrs.setText(menorQueDiez(horas) );
        min.setText(": " + menorQueDiez(minutos));
        seg.setText(": " + menorQueDiez(segundos));
    }

    public void detenerTiempo(){
        tiempo.stop();
    }
    /**
     * 
     */
    public void iniciarReloj(){
       tiempo.start();
    }

    @Override
    public String toString() {
        return menorQueDiez(horas) + ":" + menorQueDiez(minutos) + ":" + menorQueDiez(segundos);
    }
    
    
    
}
