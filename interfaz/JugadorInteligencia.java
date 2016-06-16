package interfaz;

import ia.Inteligencia;
import logica.ColorPieza;
import logica.Juego;

class JugadorInteligencia implements Jugador {

	private Inteligencia ia;
	
	JugadorInteligencia(Juego elJuego, ColorPieza elColor) {
		ia = new Inteligencia(elJuego, elColor);
	}
	
	@Override
	public void jugar() {
		ia.juega();
		
	}

}
