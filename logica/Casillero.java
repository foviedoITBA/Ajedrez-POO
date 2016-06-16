package logica;

import java.io.Serializable;

public class Casillero implements Serializable{
	private Pieza pieza;

	public Casillero(Pieza pieza){
		this.pieza=pieza;
	}
	
	public Casillero(){
		this(null);
	}
	
	public boolean isEmpty(){
		return pieza == null;	
	}
	
	public void addPieza(Pieza p){
		pieza=p;
	}
	
	public void removePieza(){
		pieza=null;
	}
	
	public Pieza getPieza(){
		return pieza;
	}
	
	
	
}
