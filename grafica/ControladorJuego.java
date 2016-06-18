package grafica;

import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;

/**
 * Clase que adimitstra las entradas del usurario y verifica con el {@link Juego} el flujo
 * del juego 
 * 
 *
 */
public class ControladorJuego {
	private Jugador jugadorBlanco, jugadorNegro, jugadorTurno;
	private Juego elJuego;
	private final boolean esUnJugador;
	private Tablero elTablero;
	private EstadoDeJuego estado;
	private TablaJugadas tabla;
	
	/**
	 * Constructor del Juego que si es un jugador 
	 * @param cantJugadores
	 * @param colorElegido
	 * @param tablero referencia al {@link Tablero}
	 * @param elJuego referencia al Juego
	 * @param estado referencia al {@link EstadoDeJuego}
	 * @param tabla refercnia a la {@link TablaJugadas}
	 */
	public ControladorJuego (int cantJugadores,ColorPieza colorElegido, Tablero tablero,Juego elJuego,EstadoDeJuego estado,TablaJugadas tabla){
		this.elJuego=elJuego;
		esUnJugador = (cantJugadores == 1);
		elTablero = tablero;
		this.estado=estado;
		this.tabla=tabla;
		
		if(esUnJugador){
			if(colorElegido == ColorPieza.BLANCO){
				jugadorBlanco= new JugadorHumano(elJuego,ColorPieza.BLANCO);
				jugadorNegro= new JugadorInteligencia(elJuego,ColorPieza.NEGRO);
			}else{
				jugadorBlanco= new JugadorInteligencia(elJuego,ColorPieza.BLANCO);
				jugadorNegro= new JugadorHumano(elJuego,ColorPieza.NEGRO);
			}
		}else{
			jugadorBlanco= new JugadorHumano(elJuego,ColorPieza.BLANCO);
			jugadorNegro= new JugadorHumano(elJuego,ColorPieza.NEGRO);
		}
		
	}
	
	/**
	 * actualiza el juagador con el turno actual
	 */
	private void actualizoJugadorTurno(){
		if(elJuego.dameTurno() == ColorPieza.BLANCO){
			jugadorTurno = jugadorBlanco;
		}else{
			jugadorTurno = jugadorNegro;
		}
	}
	
	/**
	 * Método que deshcae la jugada y en caso de jugar contra una inteligencia artificial lo
	 * hace dos veces.
	 */
	public void deshacerJugada(){
		if(elJuego.huboUnaJugada()){
			actualizoJugadorTurno();
			if(esUnJugador && jugadorTurno instanceof JugadorHumano){
				elJuego.revertir();
				tabla.removerJugada();
			}
			elJuego.revertir();
			tabla.removerJugada();
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	/**
	 * Metodo que si es posible hace el enroque largo
	 */
	public void enroqueLargo(){
		if(elJuego.sePuedeEnrocarLargo()){
			elJuego.enrocarLargo();
			tabla.agregarJugada(elJuego.dameUltimaJugada());
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	/**
	 * Metodo que si es posible hace el enroque corto
	 */
	public void enroqueCorto(){
		if(elJuego.sePuedeEnrocarCorto()){
			elJuego.enrocarCorto();
			tabla.agregarJugada(elJuego.dameUltimaJugada());
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	/**
	 * Hace que el jugador con el turno actual juegue y luego imprime la jugada
	 * @param pos
	 */
	public void jugar(PosicionAjedrez pos){
		actualizoJugadorTurno();

		
		jugadorTurno.jugar(pos);//hago la jugada
		elTablero.imprimirTablero();
		//si no juega la ia, o es multiplayer
		if( jugadorTurno instanceof JugadorHumano ){
			JugadorHumano jg = ((JugadorHumano) jugadorTurno);
			if( jg.deboPintarCasilleros() ){
				elTablero.pintarCasilleros(jg.dameCasillerrosPintar());
			}
		}
		estado.actualizarEstado();
		if(jugadorTurno.huboUnaJugada()){
			tabla.agregarJugada(elJuego.dameUltimaJugada());
		}
	}
	
	
	
}
