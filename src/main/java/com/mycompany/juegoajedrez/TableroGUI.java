package com.mycompany.juegoajedrez;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TableroGUI extends JFrame {

        
    private ServidorAjedrez servidor;
    private ClienteAjedrez cliente;
    private boolean soyServidor = false;


    private GestorTurnos gestorTurnos = new GestorTurnos();
    private JPanel panelTablero;
    private JButton[][] casillas = new JButton[8][8];
    private Tablero tablero;
    private void escucharMovimientos() {
    new Thread(() -> {
        while (true) {
            try {
                MovimientoRed mov = null;

                if (soyServidor && servidor != null) {
                    mov = servidor.recibirMovimiento();
                } else if (!soyServidor && cliente != null) {
                    mov = cliente.recibirMovimiento();
                }

                if (mov == null) continue;

                Coordenada origen = new Coordenada(mov.origenX, mov.origenY);
                Coordenada destino = new Coordenada(mov.destinoX, mov.destinoY);

                tablero.moverPieza(origen, destino);

                SwingUtilities.invokeLater(() -> actualizarVista());

            } catch (Exception e) {
                System.out.println("Error recibiendo movimiento: " + e.getMessage());
                break;
            }
                }
            }).start();
        }


    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;
    private void configurarVentana() {
    setTitle("Ajedrez en red");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
}


    public TableroGUI(Tablero tablero, ServidorAjedrez servidor) {
    this.tablero = tablero;
    this.servidor = servidor;
    soyServidor = true;

    configurarVentana();
    crearTablero();
    escucharMovimientos();
    actualizarVista();
                            }

    public TableroGUI(Tablero tablero, ClienteAjedrez cliente) {
    this.tablero = tablero;
    this.cliente = cliente;
    soyServidor = false;


    configurarVentana();
    crearTablero();
    escucharMovimientos();
    actualizarVista();
                            }

    
    public TableroGUI(Tablero tablero) {
        this.tablero = tablero;

        setTitle("Ajedrez");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        crearTablero();
        actualizarVista();
    }

    private void crearTablero() {
        panelTablero = new JPanel(new GridLayout(8, 8));

        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(70, 70));

                if ((fila + col) % 2 == 0)
                    boton.setBackground(new Color(240, 217, 181));
                else
                    boton.setBackground(new Color(181, 136, 99));

                final int f = fila;
                final int c = col;
                boton.addActionListener(e -> manejarClick(f, c));

                casillas[fila][col] = boton;
                panelTablero.add(boton);
            }
        }

        add(panelTablero);
    }

    private void manejarClick(int fila, int col) {
        if (filaSeleccionada == -1) {

    Pieza p = tablero.getPieza(fila, col);

    if (p != null) {

        if (!gestorTurnos.esTurno(p.getColor())) {
            System.out.println("No es turno de " + p.getColor());
            return;
        }

        filaSeleccionada = fila;
        colSeleccionada = col;
        casillas[fila][col].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
    }
        else {
            // Intentamos mover la pieza
            Coordenada origen = new Coordenada(filaSeleccionada, colSeleccionada);
            Coordenada destino = new Coordenada(fila, col);

           Pieza piezaAMover = tablero.getPieza(origen);

            if (piezaAMover != null) {

                
                if (!gestorTurnos.esTurno(piezaAMover.getColor())) {
                    System.out.println("No es tu turno.");
                    return;
                }

                tablero.moverPieza(origen, destino);
                // Enviar movimiento por red
                    MovimientoRed mov = new MovimientoRed(
                        origen.getX(), origen.getY(),
                        destino.getX(), destino.getY()
                    );

                    if (soyServidor && servidor != null) {
                        servidor.enviarMovimiento(mov);
                    }
                    if (!soyServidor && cliente != null) {
                        cliente.enviarMovimiento(mov);
                    }

                
                gestorTurnos.cambiarTurno();
            }

            // Quita borde y refresca vista
            casillas[filaSeleccionada][colSeleccionada].setBorder(null);
            filaSeleccionada = -1;
            colSeleccionada = -1;

            actualizarVista();
        }
    }

    public void actualizarVista() {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = tablero.getPieza(fila, col);
                if (pieza != null) {
                    String nombreImg = pieza.getClass().getSimpleName().toLowerCase() + "_" + pieza.getColor() + ".png";
                    setIcono(fila, col, nombreImg);
                } else {
                    casillas[fila][col].setIcon(null);
                }
            }
        }
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
    
}

