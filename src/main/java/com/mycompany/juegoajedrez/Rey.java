package com.mycompany.juegoajedrez;
import java.util.ArrayList;
import java.util.List;

public class Rey extends Pieza {

    public Rey(Coordenada pos, String color, Tablero tablero) {
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

        // El rey puede moverse 1 casilla en cualquier dirección
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < dx.length; i++) {
            int x = pos.getX() + dx[i];
            int y = pos.getY() + dy[i];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Coordenada nueva = new Coordenada(x, y);
                if (tablero.isEmpty(nueva) ||
                    !tablero.getPieza(nueva).getColor().equals(color)) {
                    movimientos.add(nueva);
                }
            }
        }

        return movimientos;
    }

    public void eliminar() {
        tablero.setPieza(pos, null);
    }
}
