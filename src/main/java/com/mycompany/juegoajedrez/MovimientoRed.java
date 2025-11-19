package com.mycompany.juegoajedrez;

public class MovimientoRed {

    public int origenX, origenY;
    public int destinoX, destinoY;

    public MovimientoRed(int ox, int oy, int dx, int dy) {
        this.origenX = ox;
        this.origenY = oy;
        this.destinoX = dx;
        this.destinoY = dy;
    }

    public String toJson() {
        return origenX + "," + origenY + ";" + destinoX + "," + destinoY;
    }

    public static MovimientoRed fromJson(String s) {
        try {
            String[] parts = s.split(";");
            String[] o = parts[0].split(",");
            String[] d = parts[1].split(",");

            return new MovimientoRed(
                Integer.parseInt(o[0]),
                Integer.parseInt(o[1]),
                Integer.parseInt(d[0]),
                Integer.parseInt(d[1])
            );
        } catch (Exception e) {
            return null;
        }
    }
}
    