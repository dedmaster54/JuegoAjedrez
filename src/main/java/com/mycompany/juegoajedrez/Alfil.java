package com.mycompany.juegoajedrez;
import java.util.ArrayList;
import java.util.List;

public class Alfil extends Pieza {

    public Alfil(Coordenada pos, String color, Tablero tablero) {
        super(pos, color, tablero);
    }

    
    public void mover(Coordenada destino) {
        // Mueve el alfil al destino si es válido
        if (obtenerMovimientosPosibles().contains(destino)) {
            tablero.setPieza(pos, null);
            pos = destino;
            tablero.setPieza(pos, this);
        }
    }

    public List<Coordenada> obtenerMovimientosPosibles() {
        List<Coordenada> movimientos = new ArrayList<>();

        int[] dx = {1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1};

        for (int i = 0; i < 4; i++) {
            int x = pos.getX() + dx[i];
            int y = pos.getY() + dy[i];

            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Coordenada nueva = new Coordenada(x, y);
                if (tablero.isEmpty(nueva)) {
                    movimientos.add(nueva);
                } else {
                    // puede comer si es diferente color
                    if (!tablero.getPieza(nueva).getColor().equals(color)) {
                        movimientos.add(nueva);
                    }
                    break; // no puede saltar piezas
                }
                x += dx[i];
                y += dy[i];
            }
        }
        return movimientos;
    }

    public void eliminar() {
        tablero.setPieza(pos, null);
    }
}
