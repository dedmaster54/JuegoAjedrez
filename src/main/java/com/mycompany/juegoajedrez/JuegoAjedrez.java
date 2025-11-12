package com.mycompany.juegoajedrez;

import javax.swing.SwingUtilities;

public class JuegoAjedrez {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tablero tablero = new Tablero();
            tablero.inicializar();  
            new TableroGUI(tablero).setVisible(true);
        });
    }
}
