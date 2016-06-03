package logica;

public class Jugada {
	
	private PosicionAjedrez posOrigen,posDestino;
	
	public Jugada(PosicionAjedrez posOrigen, PosicionAjedrez posDestino){
		this.posOrigen = posOrigen;
		this.posDestino = posDestino;
	}
	
	public String toString(){
		return posOrigen.dameColumna()+posOrigen.dameFila()+" - "+posDestino.dameColumna()+posDestino.dameFila();
	}
	
	
}
