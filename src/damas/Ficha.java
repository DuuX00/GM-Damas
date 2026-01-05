package damas;

import java.awt.Color;

public class Ficha {
    
    private int xPos;
    private int yPos;
    private boolean esReina;
    private final Color color; 


    public Ficha(int xPos, int yPos,Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
        esReina = false;
    }

    public Color getColor() {
        return color;
    }

    
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isEsReina() {
        return esReina;
    }
    
    public void setEsReina(boolean esReina) {
        this.esReina = esReina;
    }

    public void hacerReina() {
        esReina = true;
    }   
    
    @Override
    public String toString() {
        return "Ficha{" + "xPos=" + xPos + ", yPos=" + yPos + ", esReina=" + esReina + ", color=" + color + '}';
    }
    
    
}
