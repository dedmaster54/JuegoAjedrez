package com.mycompany.juegoajedrez;
import java.util.ArrayList;
import java.util.List;

public class Reyna extends Pieza {

    public Reyna(Coordenada pos, String color, Tablero tablero) {
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

        // Reina = movimientos de Torre + Alfil
        int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
        int[] dy = {1, -1, 1, -1, 1, -1, 0, 0};

        for (int i = 0; i < 8; i++) {
            int x = pos.getX() + dx[i];
            int y = pos.getY() + dy[i];
            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Coordenada nueva = new Coordenada(x, y);
                if (tablero.isEmpty(nueva)) {
                    movimientos.add(nueva);
                } else {
                    if (!tablero.getPieza(nueva).getColor().equals(color)) {
                        movimientos.add(nueva);
                    }
                    break;
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
