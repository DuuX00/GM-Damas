package damas;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.Timer;

public class Cuadro extends JButton{
       

    private Ficha ficha;
    private Color color;
    private final int x;
    private final int y;
    
    // Animación
private int animX = 0;
private int animY = 0;
private boolean enAnimacion = false;
private Timer timerAnimacion;
private int startX, startY, endX, endY;
private final int pasos = 10;
private final int totalFrames = 30;
private int pasoActual = 20;
    

    public Cuadro(Color color, int x, int y, Ficha ficha) {
        this.color = color;
        this.ficha = ficha;
        this.x = x;
        this.y = y;   
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        visualizar();
    }



    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public void setFicha(Ficha ficha){
        this.ficha = ficha;  
        repaint();
    }

    public Ficha getFicha() {
        return ficha;
    }

    public boolean estaVacio() {
        return ficha == null;
    }

    public void visualizar() {
        setText("");
        setBackground(color);    
        repaint();
    }    

    private void dibujarFicha(Graphics g){
        if(!estaVacio()) {
            g.setColor(ficha.getColor());
            g.fillOval(10, 10, 55, 55);

            //if (ficha.isEsReina()) {
                //g.setColor(Color.WHITE);
               // g.fillOval(20, 20, 30, 30);
            //}
        }
    }
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (ficha != null) {
        g.setColor(ficha.getColor());
        g.fillOval(10, 10, getWidth() - 20, getHeight() - 20);

        if (ficha.isEsReina()) {
            g.setColor(Color.BLACK);
            g.setFont(g.getFont().deriveFont(22f));
            int textWidth = g.getFontMetrics().stringWidth("👑");
            int textHeight = g.getFontMetrics().getAscent();
            int tx = (getWidth() - textWidth) / 2;
            int ty = (getHeight() + textHeight) / 2 - 6;
            g.drawString("👑", tx, ty);
        }
    }
}


    public void animarMovimientoDesde(int desdeX, int desdeY) {
        this.startX = desdeX;
        this.startY = desdeY;
        this.endX = getXPos();
        this.endY = getYPos();
        this.animX = 0;
        this.animY = 0;
        this.pasoActual = 0;
        this.enAnimacion = true;
        if (timerAnimacion != null) {
            timerAnimacion.stop();
        }
        

        timerAnimacion = new Timer(30, e -> {
            pasoActual++;
            repaint();

            if (pasoActual >= pasos) {
                enAnimacion = false;
                ((Timer) e.getSource()).stop();
                repaint();
            }
        });
        timerAnimacion.start();
    }

    @Override
    public String toString() {
        return "Cuadro{" + "ficha=" + ficha + ", color=" + color + ", x=" + x + ", y=" + y + '}';
    }
}
