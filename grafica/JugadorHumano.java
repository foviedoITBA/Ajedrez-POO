package grafica;

import logica.Juego;
import logica.NombrePieza;
import logica.PosicionAjedrez;
import java.util.Set;
import logica.ColorPieza;

/**	Esta clase es la implementación de un jugador humano.
*/
class JugadorHumano implements Jugador {

	private Juego elJuego;
	private ColorPieza elColor;
	private PosicionAjedrez seleccionado;
	private Set<PosicionAjedrez> movimientosPosibles;
	private boolean huboJugada;
	
	/**	Crea una instancia de un jugador humano
	@param elJuego Una referencia a la instancia del juego
	@param elColor El color de las piezas que va a usar el jugador
	*/
	JugadorHumano(Juego elJuego, ColorPieza elColor) {
		this.elJuego = elJuego;
		this.elColor = elColor;
		seleccionado=null;
		huboJugada=false;
	}
	
	/**	Hace que el jugador humano juegue
	@param clickeado El casillero que fue clickeado
	*/
	@Override
	public void jugar(PosicionAjedrez clickeado) {
		huboJugada=false;
		if(seleccionado == null) {
			if(!elJuego.hayAlgo(clickeado)|| elJuego.queHay(clickeado).dameColor()!=elJuego.dameTurno()) {
				return;
			}
			seleccionado=clickeado;
			movimientosPosibles=elJuego.dameMovimientos(seleccionado);
			
		}else{
			if(!elJuego.hayAlgo(clickeado)|| elJuego.queHay(clickeado).dameColor()!=elJuego.dameTurno()){
				if(movimientosPosibles.contains(clickeado)){
					elJuego.mover(seleccionado, clickeado);
					seleccionado=null;
					huboJugada=true;

					if(elJuego.hayAlgoParaCoronar()){
						NombrePieza pieza = null;
						do{
							pieza = CoronacionPiezas.display(elJuego.dameTurno());
						}while(pieza==null);
						elJuego.coronar(pieza);
					}
				}
			}else{
				seleccionado=clickeado;
				movimientosPosibles=elJuego.dameMovimientos(seleccionado);
			}
		}
	}

	/**	Devuelve el color de las piezas que está usando el jugador
	@return El color de las piezas que está usando el jugador.
	*/
	public ColorPieza dameColor(){
		return elColor;
	}
	
	/**	Devuelve si debe o no pintar casilleros
	@return true si debe o no pintar casilleros
	*/
	public boolean deboPintarCasilleros(){
		return seleccionado!=null;
	}
	
	/**	Devuelve los casilleros para pintar
	@return Un Set de objetos PosicionAjedrez con los casilleros a pintar
	*/
	public Set<PosicionAjedrez> dameCasillerrosPintar(){
		return movimientosPosibles;
	}

	/**	Permite saber si hubo o no una jugada
	@return true si hubo una jugada
	*/
	@Override
	public boolean huboUnaJugada() {
		return huboJugada;
	}
	
}
