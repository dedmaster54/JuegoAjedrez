package com.mycompany.juegoajedrez;

public class Tablero {
    private Pieza[][] casillas = new Pieza[8][8];

    public boolean isEmpty(Coordenada c) {
        return casillas[c.getX()][c.getY()] == null;
    }

    public Pieza getPieza(Coordenada c) {
        return casillas[c.getX()][c.getY()];
    }

    // 🔹 Esta es la versión que TableroGUI necesita:
    public Pieza getPieza(int fila, int col) {
        return casillas[fila][col];
    }

    public void setPieza(Coordenada c, Pieza p) {
        casillas[c.getX()][c.getY()] = p;
    }

    public void moverPieza(Coordenada origen, Coordenada destino) {
        Pieza pieza = getPieza(origen);
        if (pieza == null) return;

        pieza.mover(destino);
        setPieza(destino, pieza);
        setPieza(origen, null);
        pieza.setPos(destino);
    }

    public void inicializar() {
        // Peones blancos
        for (int col = 0; col < 8; col++) {
            setPieza(new Coordenada(6, col), new Peon(new Coordenada(6, col), "blanco", this));
        }

        // Peones negros
        for (int col = 0; col < 8; col++) {
            setPieza(new Coordenada(1, col), new Peon(new Coordenada(1, col), "negro", this));
        }

        // Piezas mayores blancas
        setPieza(new Coordenada(7, 0), new Torre(new Coordenada(7, 0), "blanco", this));
        setPieza(new Coordenada(7, 1), new Caballo(new Coordenada(7, 1), "blanco", this));
        setPieza(new Coordenada(7, 2), new Alfil(new Coordenada(7, 2), "blanco", this));
        setPieza(new Coordenada(7, 3), new Reyna(new Coordenada(7, 3), "blanco", this));
        setPieza(new Coordenada(7, 4), new Rey(new Coordenada(7, 4), "blanco", this));
        setPieza(new Coordenada(7, 5), new Alfil(new Coordenada(7, 5), "blanco", this));
        setPieza(new Coordenada(7, 6), new Caballo(new Coordenada(7, 6), "blanco", this));
        setPieza(new Coordenada(7, 7), new Torre(new Coordenada(7, 7), "blanco", this));

        // Piezas mayores negras
        setPieza(new Coordenada(0, 0), new Torre(new Coordenada(0, 0), "negro", this));
        setPieza(new Coordenada(0, 1), new Caballo(new Coordenada(0, 1), "negro", this));
        setPieza(new Coordenada(0, 2), new Alfil(new Coordenada(0, 2), "negro", this));
        setPieza(new Coordenada(0, 3), new Reyna(new Coordenada(0, 3), "negro", this));
        setPieza(new Coordenada(0, 4), new Rey(new Coordenada(0, 4), "negro", this));
        setPieza(new Coordenada(0, 5), new Alfil(new Coordenada(0, 5), "negro", this));
        setPieza(new Coordenada(0, 6), new Caballo(new Coordenada(0, 6), "negro", this));
        setPieza(new Coordenada(0, 7), new Torre(new Coordenada(0, 7), "negro", this));
    }
}
