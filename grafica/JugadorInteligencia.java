package grafica;

import ia.Inteligencia;
import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;
/**
 * Clase que implementa Jugador y crea una inteligenia artificial y 
 * su metodo jugar hace que la ia juegue
 *
 */
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

	@Override
	public boolean huboUnaJugada() {
		return true;
	}

}
