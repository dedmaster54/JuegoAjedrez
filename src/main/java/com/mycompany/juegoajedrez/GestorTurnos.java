package com.mycompany.juegoajedrez;

public class GestorTurnos {

    private String turnoActual;

    public GestorTurnos() {
        this.turnoActual = "blanco";  // Como en el ajedrez real
    }

    public String getTurnoActual() {
        return turnoActual;
    }

    public void cambiarTurno() {
        turnoActual = turnoActual.equals("blanco") ? "negro" : "blanco";
    }

    public boolean esTurno(String colorPieza) {
        return turnoActual.equals(colorPieza);
    }

    public void reiniciar() {
        turnoActual = "blanco";
    }
}
