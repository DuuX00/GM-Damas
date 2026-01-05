package damas;

import java.awt.Color;
import java.util.ArrayList;

public class Jugador {
    

    private String nombre;

    private ArrayList<Ficha> fichas;

    private int numFichas;
    

    public Jugador(String nombre) {
        this.nombre = nombre;
        numFichas = 0;
        fichas = new ArrayList<>(numFichas);
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public int getNumFichas() {
        return numFichas;
    }

    public void agregarFicha(Ficha ficha){
        fichas.add(ficha);
        numFichas++;
    }

    public void nuevasFichas(){
        numFichas = 0;
        fichas.clear();
    }

    public void quitarFicha(Ficha ficha) {
        fichas.remove(ficha);
        numFichas--;
    }
    

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", fichas=" + fichas + ", numFichas=" + numFichas + '}';
    }

    Color getColor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
