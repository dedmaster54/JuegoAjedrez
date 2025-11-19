package com.mycompany.juegoajedrez;

import javax.swing.SwingUtilities;

public class JuegoAjedrez {

    public static void main(String[] args) {

        final Tablero tablero = new Tablero();
        tablero.inicializar();

        try {
            if (args != null && args.length > 0 && "server".equalsIgnoreCase(args[0])) {
                // Modo servidor
                final ServidorAjedrez servidor = new ServidorAjedrez(5000); // puede bloquear hasta que un cliente se conecte
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TableroGUI(tablero, servidor).setVisible(true);
                    }
                });

            } else if (args != null && args.length > 0 && "client".equalsIgnoreCase(args[0]) && args.length > 1) {
                // Modo cliente: java -jar TuJar client 192.168.0.5
                final String ip = args[1];
                final ClienteAjedrez cliente = new ClienteAjedrez(ip, 5000);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TableroGUI(tablero, cliente).setVisible(true);
                    }
                });

            } else {
                // Modo local (sin red)
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TableroGUI(tablero).setVisible(true);
                    }
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

