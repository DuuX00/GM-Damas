package damas;

import Interfaz.Jugar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;




public class Tablero extends javax.swing.JPanel implements ActionListener {


    private final Jugar juego;
    private final Cuadro[][] cuadros;
    private final Jugador[] jugadores;
    private Cuadro cuadroSeleccionado;
    private HashMap<Cuadro, Cuadro> fichaComer;
    private int turno;
    private boolean finPartida;
   private boolean capturaEnCadena = false; // 🔁 Bandera para controlar captura doble o múltiple
   private boolean contraIA;
    
    
    
  public Tablero(Jugador[] jugadores, Jugar juego, boolean contraIA) {
    this.jugadores = jugadores;
    this.juego = juego;
    this.contraIA = contraIA;

    initComponents();

    // Asegura tamaño y distribución del tablero
    this.setPreferredSize(new Dimension(640, 640)); 
    this.setLayout(new GridLayout(8, 8));

    finPartida = false;
    cuadros = new Cuadro[8][8];
    cuadroSeleccionado = null;
    fichaComer = new HashMap<>();
    turno = 0;

    dibujarTablero();
    juego.iniciarReloj();
}

// Constructor reducido para jugador vs jugador (usa el anterior)
public Tablero(Jugador[] jugadores, Jugar juego) {
    this(jugadores, juego, false); // delega al constructor principal
}

// PARA DIBUJA el tablero

    private void dibujarTablero() {
    Color claro = new Color(240, 217, 181); // tono madera claro
    Color oscuro = new Color(181, 136, 99); // tono madera oscuro

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {

            Ficha ficha = null;

            boolean esOscuro = (i + j) % 2 != 0;
            Color colorCasilla = esOscuro ? oscuro : claro;

            if (i < 3 && esOscuro) {
                ficha = new Ficha(j, i, Color.DARK_GRAY);
                jugadores[0].agregarFicha(ficha);
            } else if (i > 4 && esOscuro) {
                ficha = new Ficha(j, i, Color.ORANGE);
                jugadores[1].agregarFicha(ficha);
            }

            Cuadro cuadro = new Cuadro(colorCasilla, j, i, ficha);
            cuadro.addActionListener(this);
            cuadro.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(120, 72, 35))); // borde tipo madera

            if (!esOscuro) {
                cuadro.setEnabled(false); // los cuadros claros no se usan
            }

            cuadros[i][j] = cuadro;
            this.add(cuadro);
            cuadro.visualizar();
        }
    }

    System.out.println(verFichas());
}
//PARA EL JUGAR CON LA IA
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(50, 50));
        setLayout(new java.awt.GridLayout(8, 8));
    }// </editor-fold>//GEN-END:initComponents

@Override
public void actionPerformed(ActionEvent e) {
    Cuadro cuadro = (Cuadro) e.getSource();

    if (cuadro.getColor().equals(Color.YELLOW)) {
        moverFicha(cuadro); // Solo se mueve si es destino válido
    } else {
        accionDespuesDePresionar(cuadro); // Marca posibles movimientos
    }
}


private void moverIA() {
    SwingUtilities.invokeLater(() -> {
        boolean hizoCaptura = false;

        // 1️⃣ Buscar capturas primero
        for (int i = 0; i < 8 && !hizoCaptura; i++) {
            for (int j = 0; j < 8 && !hizoCaptura; j++) {
                Cuadro origen = cuadros[i][j];
                if (!origen.estaVacio() && origen.getFicha().getColor().equals(Color.ORANGE)) {
                    accionDespuesDePresionar(origen);

                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            Cuadro destino = cuadros[y][x];
                            if (fichaComer.containsKey(destino)) { //  Es captura
                                moverFicha(destino);

                                if (!fichaComer.isEmpty()) {
                                    Timer timer = new Timer(300, e -> moverIA()); // Cadena de captura
                                    timer.setRepeats(false);
                                    timer.start();
                                }

                                hizoCaptura = true;
                                return;
                            }
                        }
                    }
                }
            }
        }

        //️⃣ Si no hay capturas, hace movimiento normal
        if (!hizoCaptura) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Cuadro origen = cuadros[i][j];
                    if (!origen.estaVacio() && origen.getFicha().getColor().equals(Color.ORANGE)) {
                        accionDespuesDePresionar(origen);
                        for (int y = 0; y < 8; y++) {
                            for (int x = 0; x < 8; x++) {
                                Cuadro destino = cuadros[y][x];
                                if (destino.getColor().equals(Color.YELLOW)) {
                                    moverFicha(destino);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    });
}



    
private void accionDespuesDePresionar(Cuadro cuadro) {
    if (cuadro.getFicha() != null &&
    ((turno == 0 && cuadro.getFicha().getColor().equals(Color.DARK_GRAY)) ||
     (turno == 1 && cuadro.getFicha().getColor().equals(Color.ORANGE)))) {

    int x = cuadro.getFicha().getxPos();
    int y = cuadro.getFicha().getyPos();

    fichaComer.clear();
    buscarCuadrosAmarillos();

    if (cuadro.getFicha().isEsReina()) {
        IndicarReina(x, y, cuadro);
    } else {
        verificarCapturas(cuadro, x, y);
    }

    cuadroSeleccionado = cuadro; // ✅ esto es clave
}
}

private void IndicarReina(int x, int y, Cuadro cuadro) {
    int[][] direcciones = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
    
    // 1️⃣ Verificamos si hay alguna captura posible en cualquier dirección
    boolean capturaDisponible = false;
    for (int[] dir : direcciones) {
        int dx = dir[0];
        int dy = dir[1];
        int nx = x + dx;
        int ny = y + dy;
        boolean fichaEncontrada = false;

        while (esDentroDelTablero(nx, ny)) {
            Cuadro actual = cuadros[ny][nx];

            if (!fichaEncontrada) {
                if (actual.estaVacio()) {
                    nx += dx;
                    ny += dy;
                } else if (!actual.getFicha().getColor().equals(cuadro.getFicha().getColor())) {
                    fichaEncontrada = true;
                    nx += dx;
                    ny += dy;
                } else {
                    break;
                }
            } else {
                if (actual.estaVacio()) {
                    capturaDisponible = true;
                    break;
                } else {
                    break;
                }
            }
        }
        if (capturaDisponible) break;
    }

    // 2️⃣ Recorremos nuevamente para marcar movimientos válidos
    for (int[] dir : direcciones) {
        int dx = dir[0];
        int dy = dir[1];
        boolean fichaEncontrada = false;
        Cuadro fichaAComer = null;
        int nx = x + dx;
        int ny = y + dy;

        while (esDentroDelTablero(nx, ny)) {
            Cuadro actual = cuadros[ny][nx];

            if (!fichaEncontrada) {
                if (actual.estaVacio()) {
                    if (!capturaDisponible && !capturaEnCadena) {
                        cuadros[ny][nx].setColor(Color.YELLOW);
                    }
                    nx += dx;
                    ny += dy;
                } else if (!actual.getFicha().getColor().equals(cuadro.getFicha().getColor())) {
                    fichaEncontrada = true;
                    fichaAComer = actual; // ✅ Guardamos la ficha enemiga a comer
                    nx += dx;
                    ny += dy;
                } else {
                    break;
                }
            } else {
                if (actual.estaVacio()) {
                    // ✅ Asociamos el destino con la ficha a eliminar
                    fichaComer.put(cuadros[ny][nx], fichaAComer);
                    cuadros[ny][nx].setColor(Color.YELLOW);
                    nx += dx;
                    ny += dy;
                } else {
                    break;
                }
            }
        }
    }
}





     private void hacerReina(Cuadro cuadro) {
        
        if(!cuadro.getFicha().isEsReina()){
            if (cuadro.getFicha().getColor().equals(Color.ORANGE) && cuadro.getFicha().getyPos() == 0) {
                JOptionPane.showMessageDialog(this, "Se ha hecho reina tu ficha", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
                cuadro.getFicha().hacerReina();
            } else if (cuadro.getFicha().getColor().equals(Color.DARK_GRAY) && cuadro.getFicha().getyPos() == 7) {
                JOptionPane.showMessageDialog(this, "Se ha hecho reina tu ficha", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
                cuadro.getFicha().hacerReina();
            }
        }
    }


private void moverFicha(Cuadro cuadro) {
    if (cuadro.getColor().equals(Color.YELLOW)) {

        int x = cuadro.getXPos();
        int y = cuadro.getYPos();

        boolean comio = fichaComer.containsKey(cuadro); // ✅ Saber si fue una captura

        comer(cuadro);

        // 🔁 Animar antes de mover la ficha
        cuadro.animarMovimientoDesde(cuadroSeleccionado.getXPos(), cuadroSeleccionado.getYPos());

        // Mover ficha al nuevo cuadro
        cuadro.setFicha(cuadroSeleccionado.getFicha());
        cuadro.getFicha().setxPos(x);
        cuadro.getFicha().setyPos(y);
        Color nuevoColor = (cuadro.getXPos() + cuadro.getYPos()) % 2 == 0
        ? new Color(240, 217, 181)  // claro
        : new Color(181, 136, 99);  // oscuro

cuadro.setColor(nuevoColor);


        cuadroSeleccionado.setFicha(null);

        hacerReina(cuadro);

        fichaComer.clear(); // Limpia mapa de capturas posibles
        cuadroSeleccionado = cuadro;

        if (comio) {
            verificarCapturas(cuadro, x, y); // 🔁 Ver si puede capturar otra vez
            if (!fichaComer.isEmpty()) {
                buscarCuadrosAmarillos(); // 🟡 Marcar posibles capturas
                return; // 🚫 NO cambia de turno todavía
            }
        }

        // Si no comió o ya no puede seguir comiendo, termina turno
        cuadroSeleccionado = null;
        ganador();
        cambiarTurno(); // ✅ Turno cambia solo si no hay doble captura
        buscarCuadrosAmarillos(); // 🧼 Limpia los cuadros amarillos

        System.out.println(verFichas());

    } else {
        // Si clic fuera del cuadro amarillo
        buscarCuadrosAmarillos();
        cuadroSeleccionado = null;
    }
}




    private String verFichas() {
        String cad = "";
        for (Jugador jugador : jugadores) {
            cad += jugador.getNombre() + " " + jugador.getNumFichas() + "\n";
            for (Ficha ficha : jugador.getFichas()) {
                cad += ficha + "\n";
            }
        }
        return cad;
    }

private void ganador() {
    if (finPartida) {
        juego.detenerTiempo();

        int oponente = (turno + 1) % 2;
        String mensaje = "Ganó " + jugadores[turno].getNombre() + " porque "
                + jugadores[oponente].getNombre() + " no tiene más fichas o movimientos.\n"
                + "Tiempo: " + juego.devolverReloj();

        int opcion = JOptionPane.showOptionDialog(
            this,
            mensaje + "\n¿Quieres jugar otra vez o volver al menú?",
            "Fin de la partida",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new String[]{"Jugar de nuevo", "Volver al menú", "Salir"},
            "Jugar de nuevo"
        );

        if (opcion == 0) {
            juego.reiniciarPartida();
        } else if (opcion == 1) {
            juego.volverAlMenu();
        } else {
            System.exit(0);
        }

        // Desactivar el tablero por seguridad
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cuadros[i][j].setEnabled(false);
            }
        }
    }
}




  private boolean indicarMovimiento(int x, int y, Cuadro cuadro, int direccionY, int direccionX) {
    try {
        int newY = y + direccionY;
        int newX = x + direccionX;

        if (!esDentroDelTablero(newX, newY)) return false;

        if (cuadros[newY][newX].estaVacio()) {
            cuadros[newY][newX].setColor(Color.YELLOW);
            return true;

        } else if (!cuadros[newY][newX].getFicha().getColor().equals(cuadro.getFicha().getColor())) {
            int saltoY = y + direccionY * 2;
            int saltoX = x + direccionX * 2;

            if (esDentroDelTablero(saltoX, saltoY) && cuadros[saltoY][saltoX].estaVacio()) {
                indicarComer(cuadro, x, y, direccionY * 2, direccionX * 2, cuadros[newY][newX]);
            }
            return false;
        }
    } catch (Exception err) {
        System.err.println("Error al indicar movimiento: " + err.getMessage());
    }
    return false;
}





private void indicarComer(Cuadro cuadro, int x, int y, int direccionY, int direccionX, Cuadro fichaCuadro) {
    try {
        if (cuadros[y + direccionY][x + direccionX].estaVacio()) {
            fichaComer.put(cuadros[y + direccionY][x + direccionX], fichaCuadro);
            cuadros[y + direccionY][x + direccionX].setColor(Color.YELLOW);
        }
    } catch (Exception err) {
        System.err.println("Error en indicarComer: " + err.getMessage());
    }
}




private void comer(Cuadro destino) {
    // Captura simple (fichas normales)
    if (fichaComer.containsKey(destino)) {
        Cuadro capturado = fichaComer.get(destino);
        quitarFichaContrincante(capturado.getFicha());
        capturado.setFicha(null);
        return;
    }

    // Captura con reina
    if (cuadroSeleccionado.getFicha() != null && cuadroSeleccionado.getFicha().isEsReina()) {
        int x0 = cuadroSeleccionado.getXPos();
        int y0 = cuadroSeleccionado.getYPos();
        int x1 = destino.getXPos();
        int y1 = destino.getYPos();

        int dx = Integer.compare(x1 - x0, 0);  // dirección X
        int dy = Integer.compare(y1 - y0, 0);  // dirección Y

        int nx = x0 + dx;
        int ny = y0 + dy;
        boolean fichaEncontrada = false;

        while (nx != x1 && ny != y1 && esDentroDelTablero(nx, ny)) {
            Cuadro intermedio = cuadros[ny][nx];

            if (!intermedio.estaVacio()) {
                if (!intermedio.getFicha().getColor().equals(cuadroSeleccionado.getFicha().getColor())) {
                    if (!fichaEncontrada) {
                        fichaEncontrada = true; // ✅ primera ficha enemiga
                    } else {
                        // ❌ Más de una ficha enemiga en el camino, movimiento inválido
                        break;
                    }
                } else {
                    // ❌ Ficha del mismo color bloqueando
                    break;
                }
            }

            nx += dx;
            ny += dy;
        }

        // ✅ Si encontró solo una ficha enemiga en el camino y el destino era válido
        if (fichaEncontrada) {
            // Regresar al punto de inicio y buscar cuál fue la ficha capturada
            nx = x0 + dx;
            ny = y0 + dy;
            while (nx != x1 && ny != y1) {
                Cuadro posible = cuadros[ny][nx];
                if (!posible.estaVacio() &&
                    !posible.getFicha().getColor().equals(cuadroSeleccionado.getFicha().getColor())) {
                    quitarFichaContrincante(posible.getFicha());
                    posible.setFicha(null);
                    break;
                }
                nx += dx;
                ny += dy;
            }
        }
    }
}
private boolean puedeMover(Cuadro cuadro) {
    int x = cuadro.getXPos();
    int y = cuadro.getYPos();

    int[][] direcciones;
    if (cuadro.getFicha().isEsReina()) {
        direcciones = new int[][]{{1,1},{-1,1},{1,-1},{-1,-1}};
    } else {
        if (cuadro.getFicha().getColor().equals(Color.DARK_GRAY)) {
            direcciones = new int[][]{{1,1},{1,-1}};
        } else {
            direcciones = new int[][]{{-1,1},{-1,-1}};
        }
    }

    for (int[] d : direcciones) {
        int dx = d[0];
        int dy = d[1];
        int nx = x + dx;
        int ny = y + dy;

        if (esDentroDelTablero(nx, ny)) {
            Cuadro destino = cuadros[ny][nx];

            if (destino.estaVacio()) {
                return true; // puede moverse
            }

            // ¿Puede capturar?
            int saltoX = x + 2 * dx;
            int saltoY = y + 2 * dy;

            if (esDentroDelTablero(saltoX, saltoY) && cuadros[saltoY][saltoX].estaVacio()) {
                Cuadro intermedio = cuadros[ny][nx];
                if (!intermedio.estaVacio() &&
                    !intermedio.getFicha().getColor().equals(cuadro.getFicha().getColor())) {
                    return true; // puede capturar
                }
            }
        }
    }

    return false;
}


private boolean tieneMovimientosDisponibles(int turnoJugador) {
    Color colorJugador = (turnoJugador == 0) ? Color.DARK_GRAY : Color.ORANGE;

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            Cuadro cuadro = cuadros[i][j];
            if (!cuadro.estaVacio() && cuadro.getFicha().getColor().equals(colorJugador)) {
                if (puedeMover(cuadro)) {
                    return true; // tiene al menos un movimiento
                }
            }
        }
    }
    return false; // no tiene ningún movimiento
}




private void quitarFichaContrincante(Ficha ficha) {
    int oponente = (turno + 1) % 2;
    jugadores[oponente].quitarFicha(ficha);

    juego.actualizarContadores(); // ✅ Actualiza visualmente los contadores

    if (jugadores[oponente].getNumFichas() == 0) {
        finPartida = true;
    }
}




private void buscarCuadrosAmarillos() {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            if (cuadros[i][j].getColor().equals(Color.YELLOW)) {
                // Restaurar color original según posición
                Color original = (i + j) % 2 == 0
                        ? new Color(240, 217, 181) // color claro
                        : new Color(181, 136, 99); // color oscuro
                cuadros[i][j].setColor(original);
            }
        }
    }
}


private void cambiarTurno() {
    turno = (turno + 1) % 2;

    if (!tieneMovimientosDisponibles(turno)) {
        finPartida = true;
        ganador();
        return;
    }

    juego.obtenerTurno(); // Actualiza el texto

    if (contraIA && turno == 1) {
        moverIA(); 
    }
}





 



    public int getTurno() {
        return turno;
    }
    
    private boolean esDentroDelTablero(int x, int y) {
    return x >= 0 && x < 8 && y >= 0 && y < 8;
}

private void verificarCapturas(Cuadro cuadro, int x, int y) {
    int[][] direcciones;

    if (cuadro.getFicha().isEsReina()) {
        // Si es reina, todas las direcciones
        direcciones = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    } else {
        // Si no es reina, solo hacia adelante según el color
        if (cuadro.getFicha().getColor().equals(Color.DARK_GRAY)) {
            direcciones = new int[][]{{1, 1}, {1, -1}}; // baja
        } else {
            direcciones = new int[][]{{-1, 1}, {-1, -1}}; // sube
        }
    }

    for (int[] d : direcciones) {
        indicarMovimiento(x, y, cuadro, d[0], d[1]);
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}