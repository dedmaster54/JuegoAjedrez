package com.mycompany.juegoajedrez;
import java.util.List;

public abstract class Pieza {
    protected Coordenada pos;
    protected String color;
    protected Tablero tablero;

    public Pieza(Coordenada pos, String color, Tablero tablero) {
        this.pos = pos;
        this.color = color;
        this.tablero = tablero;
        }
        

    public Coordenada getPos() { 
	return pos; 
	}
    public void setPos(Coordenada pos) { 
	this.pos = pos; 
	}
    public String getColor() { 
	return color; 
	}
    public Tablero getTablero() { 
	return tablero; 
	}

    public abstract void mover(Coordenada destino);
    public abstract List<Coordenada> obtenerMovimientosPosibles();
    public abstract void eliminar();
}
