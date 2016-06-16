package grafica;

import ia.Inteligencia;
import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;

class JugadorInteligencia implements Jugador {

	private Inteligencia ia;
	private ColorPieza elColor;
	
	JugadorInteligencia(Juego elJuego, ColorPieza elColor) {
		this.elColor = elColor;
		ia = new Inteligencia(elJuego, elColor);
	}
	
	@Override
	public void jugar(PosicionAjedrez pos) {
		ia.juega();
		
	}
	
	public ColorPieza dameColor(){
		return elColor;
	}

}
