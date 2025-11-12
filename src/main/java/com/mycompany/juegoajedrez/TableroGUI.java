package com.mycompany.juegoajedrez;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableroGUI extends JFrame {

    private JPanel panelTablero;
    private JButton[][] casillas = new JButton[8][8];

    // Variables para manejar los clics
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;

    public TableroGUI() {
        setTitle("Ajedrez");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearTablero();
        colocarPiezas();
    }

    private void crearTablero() {
        panelTablero = new JPanel(new GridLayout(8, 8));

        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(70, 70));

                // Colores de tablero
                if ((fila + col) % 2 == 0)
                    boton.setBackground(new Color(240, 217, 181)); // claro
                else
                    boton.setBackground(new Color(181, 136, 99));  // oscuro

                // Acción al hacer clic
                final int f = fila;
                final int c = col;
                boton.addActionListener(e -> manejarClick(f, c));

                casillas[fila][col] = boton;
                panelTablero.add(boton);
            }
        }

        add(panelTablero);
    }

    private void colocarPiezas() {
        // --- PIEZAS BLANCAS ---
        setIcono(7, 0, "torreB.png");
        setIcono(7, 1, "caballoB.png");
        setIcono(7, 2, "alfilB.png");
        setIcono(7, 3, "reinaB.png");
        setIcono(7, 4, "reyB.png");
        setIcono(7, 5, "alfilB.png");
        setIcono(7, 6, "caballoB.png");
        setIcono(7, 7, "torreB.png");
        for (int c = 0; c < 8; c++) setIcono(6, c, "peonB.png");

        // --- PIEZAS NEGRAS ---
        setIcono(0, 0, "torreN.png");
        setIcono(0, 1, "caballoN.png");
        setIcono(0, 2, "alfilN.png");
        setIcono(0, 3, "reinaN.png");
        setIcono(0, 4, "reyN.png");
        setIcono(0, 5, "alfilN.png");
        setIcono(0, 6, "caballoN.png");
        setIcono(0, 7, "torreN.png");
        for (int c = 0; c < 8; c++) setIcono(1, c, "peonN.png");
    }

    private void setIcono(int fila, int col, String nombreArchivo) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/" + nombreArchivo));
            Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            casillas[fila][col].setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("No se pudo cargar: " + nombreArchivo);
        }
    }

    private void manejarClick(int fila, int col) {
        JButton boton = casillas[fila][col];

        // Si no hay una pieza seleccionada
        if (filaSeleccionada == -1 && colSeleccionada == -1) {
            if (boton.getIcon() != null) { // Solo selecciona si hay pieza
                filaSeleccionada = fila;
                colSeleccionada = col;
                boton.setBorder(BorderFactory.createLineBorder(Color.RED, 3)); // marca selección
            }
        } else {
            // Mover pieza
            JButton origen = casillas[filaSeleccionada][colSeleccionada];
            Icon icono = origen.getIcon();

            // Quita borde de selección
            origen.setBorder(null);

            // Mueve la imagen
            boton.setIcon(icono);
            origen.setIcon(null);

            // Reinicia selección
            filaSeleccionada = -1;
            colSeleccionada = -1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableroGUI().setVisible(true);
        });
    }
}

