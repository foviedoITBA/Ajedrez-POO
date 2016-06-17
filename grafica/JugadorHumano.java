package grafica;

import logica.Juego;
import logica.NombrePieza;
import logica.PosicionAjedrez;
import java.util.Set;
import logica.ColorPieza;

class JugadorHumano implements Jugador {

	private Juego elJuego;
	private ColorPieza elColor;
	private PosicionAjedrez seleccionado;
	private Set<PosicionAjedrez> movimientosPosibles;
	private boolean huboJugada;
	
	JugadorHumano(Juego elJuego, ColorPieza elColor) {
		this.elJuego = elJuego;
		this.elColor = elColor;
		seleccionado=null;
		huboJugada=false;
	}
	
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

	
	public ColorPieza dameColor(){
		return elColor;
	}
	
	public boolean deboPintarCasilleros(){
		return seleccionado!=null;
	}
	
	public Set<PosicionAjedrez> dameCasillerrosPintar(){
		return movimientosPosibles;
	}

	@Override
	public boolean huboUnaJugada() {
		return huboJugada;
	}
	

}
