package com.mycompany.juegoajedrez;
public class Celda {
	private String color;
	private Pieza pieza;
	
	public Celda(String color){
		
		this.color = color;
		this.pieza = null;
	
	}
	
	public String getColor(){
		return color;
	}
	public Pieza getPieza(){
		return pieza;
	}
	public void setColor(String color){
	this.color=color;
	}
	public void setPieza(Pieza pieza){
		this.pieza = pieza;
	}
	public boolean estaVacia(){
		return pieza == null;
	}
	
}