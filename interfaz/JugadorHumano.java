package interfaz;

import logica.Juego;
import logica.ColorPieza;

class JugadorHumano implements Jugador {

	private Juego elJuego;
	private ColorPieza elColor;
	
	JugadorHumano(Juego elJuego, ColorPieza elColor) {
		this.elJuego = elJuego;
		this.elColor = elColor;
	}
	
	@Override
	public void jugar() {
		
	}

}
