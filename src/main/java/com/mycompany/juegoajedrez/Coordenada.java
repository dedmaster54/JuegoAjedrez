package com.mycompany.juegoajedrez;
public class Coordenada {
    private int x;
    private int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { 
	return x; 
	}
    public int getY() { 
	return y; 
	}

    public void setX(int x) { 
	this.x = x; 
	}
    public void setY(int y) { 
	this.y = y; 
	} 
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public int getFila() {
        throw new UnsupportedOperationException("Unimplemented method 'getFila'");
    }

    public int getColumna() {
        throw new UnsupportedOperationException("Unimplemented method 'getColumna'");
    }
}
