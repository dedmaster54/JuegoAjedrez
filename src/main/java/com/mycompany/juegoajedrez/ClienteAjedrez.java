package com.mycompany.juegoajedrez;

import java.io.*;
import java.net.*;

public class ClienteAjedrez {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClienteAjedrez(String ip, int puerto) throws Exception {
        socket = new Socket(ip, puerto);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Conectado al servidor");
    }

    public MovimientoRed recibirMovimiento() throws IOException {
        return MovimientoRed.fromJson(input.readLine());
    }

    public void enviarMovimiento(MovimientoRed mov) {
        output.println(mov.toJson());
    }
}
