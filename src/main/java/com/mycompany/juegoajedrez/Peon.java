package com.mycompany.juegoajedrez;
import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {

    public Peon(Coordenada pos, String color, Tablero tablero) {
        super(pos, color, tablero);
    }

    public void mover(Coordenada destino) {
        if (obtenerMovimientosPosibles().contains(destino)) {
            tablero.setPieza(pos, null);
            pos = destino;
            tablero.setPieza(pos, this);
        }
    }

    public List<Coordenada> obtenerMovimientosPosibles() {
        List<Coordenada> movimientos = new ArrayList<>();

        int direccion = color.equals("blanco") ? -1 : 1; // arriba si blanco, abajo si negro
        int x = pos.getX();
        int y = pos.getY();

        // Movimiento hacia adelante
        Coordenada adelante = new Coordenada(x, y + direccion);
        if (y + direccion >= 0 && y + direccion < 8 && tablero.isEmpty(adelante)) {
            movimientos.add(adelante);
        }

        // Movimiento doble al inicio
        if ((color.equals("blanco") && y == 6) || (color.equals("negro") && y == 1)) {
            Coordenada doble = new Coordenada(x, y + 2 * direccion);
            if (tablero.isEmpty(doble)) {
                movimientos.add(doble);
            }
        }

        // Capturas diagonales
        int[] dx = {-1, 1};
        for (int d : dx) {
            int nx = x + d;
            int ny = y + direccion;
            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                Coordenada diag = new Coordenada(nx, ny);
                if (!tablero.isEmpty(diag) &&
                    !tablero.getPieza(diag).getColor().equals(color)) {
                    movimientos.add(diag);
                }
            }
        }

        return movimientos;
    }

    public void eliminar() {
        tablero.setPieza(pos, null);
    }
}
