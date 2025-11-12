package com.mycompany.juegoajedrez;
import java.util.ArrayList;
import java.util.List;

public class Caballo extends Pieza {

    public Caballo(Coordenada pos, String color, Tablero tablero) {
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
        int[][] saltos = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        for (int[] s : saltos) {
            int x = pos.getX() + s[0];
            int y = pos.getY() + s[1];

            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Coordenada nPos = new Coordenada(x, y);

                if (tablero.isEmpty(nPos) ||
                    !tablero.getPieza(nPos).getColor().equals(color)) {
                    movimientos.add(nPos);
                }
            }
        }

        return movimientos;
    }

    public void eliminar() {
        tablero.setPieza(pos, null);
    }
}

