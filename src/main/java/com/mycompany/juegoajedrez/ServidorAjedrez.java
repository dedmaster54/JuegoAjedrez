package com.mycompany.juegoajedrez;

import java.io.*;
import java.net.*;

public class ServidorAjedrez {

    private ServerSocket server;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ServidorAjedrez(int puerto) throws Exception {
        server = new ServerSocket(puerto);
        System.out.println("Esperando conexión...");
        socket = server.accept();

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Jugador conectado!");
    }

    // Recibir movimiento
    public MovimientoRed recibirMovimiento() throws IOException {
        String linea = input.readLine();
        return MovimientoRed.fromJson(linea);
    }

    // Enviar movimiento
    public void enviarMovimiento(MovimientoRed mov) {
        output.println(mov.toJson());
    }
}
