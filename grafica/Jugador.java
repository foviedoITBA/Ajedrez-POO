package grafica;

import logica.ColorPieza;
import logica.PosicionAjedrez;

interface Jugador {
	
	public void jugar(PosicionAjedrez pos);
	
	public ColorPieza dameColor();

	public boolean huboUnaJugada();
}
