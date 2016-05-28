package logica;

public class Casillero {
	private Pieza pieza;

	public Casillero(Pieza pieza){
		this.pieza=pieza;
	}
	
	public Casillero(){
		this(null);
	}
	
	public boolean isEmpty(){
		if (pieza==null){
			return true;
		}else{
			return false;
		}
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
