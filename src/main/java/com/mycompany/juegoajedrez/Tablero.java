package com.mycompany.juegoajedrez;
public class Tablero {
    private Pieza[][] casillas = new Pieza[8][8];

    public boolean isEmpty(Coordenada c) {
        return casillas[c.getX()][c.getY()] == null;
    }

    public Pieza getPieza(Coordenada c) {
        return casillas[c.getX()][c.getY()];
    }

    public void setPieza(Coordenada c, Pieza p) {
        casillas[c.getX()][c.getY()] = p;
    }
}
