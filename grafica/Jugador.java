package grafica;

import logica.ColorPieza;
import logica.PosicionAjedrez;
/**
 * Interfaz que se implementa para poder jugar con un humano o una inteligencia
 * artificial
 *
 */
interface Jugador {
	
	public void jugar(PosicionAjedrez pos);
	
	public ColorPieza dameColor();

	public boolean huboUnaJugada();
}
