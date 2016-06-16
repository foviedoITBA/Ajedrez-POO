package logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayDeque;

import excepcion.CasilleroVacioException;
import excepcion.CoronacionPendienteException;
import excepcion.CoronacionInvalidaException;
import excepcion.JugadaInvalidaException;
import excepcion.EmptyRegisterException;
import excepcion.EnroqueInvalidoException;

import java.util.Set;

/** Esta clase funciona como controlador (en el sentido MVC) del programa de ajedrez. Sus funciones son:
	proveer una API amplia para cualquier interfaz que conforme la vista; mantener control del estado del
	juego (quién tiene el turno, si hubo jaque mate, qué jugadas se hicieron, entre otras); manejar la
	comunicación con el modelo para conocer o alterar el estado del juego.
 */
public class Juego implements Serializable{

	private Tablero elTablero; // Esta es una referencia al tablero

	private Jugador jugadorBlanco,jugadorNegro;
	private Jugador jugadorTurno;

	private boolean hayTiempo;

	private  ArrayDeque<Jugada> registro; // Ésta es una pila donde se guardan las jugadas a medida que se hacen

	private boolean jaqueMate;	// Una variable que registra si hubo jaque mate o no
	private boolean ahogado;	// Una variable que registra si hubo ahogado o no

	private boolean hayCoronacionPendiente;

	/** Instancia una nueva partida lista para ser jugada
	 */
	public Juego() {
		this(false);
	}
	
	public Juego(boolean hayTiempo){
		elTablero = new Tablero();
		registro = new ArrayDeque<Jugada>();
		jaqueMate=false;
		ahogado=false;
		jugadorBlanco=new Jugador(ColorPieza.BLANCO);
		jugadorNegro=new Jugador(ColorPieza.NEGRO);
		jugadorTurno = jugadorBlanco;
		this.hayTiempo = hayTiempo;
		jugadorTurno.iniciarTiempo();
		hayCoronacionPendiente = false;
	}

	//Agregar constructor para cargar una partida

	/** Permite conocer si hay alguna pieza en una determinana posición del tablero
	@param posicion La posicion del tablero a inspeccionar
	@return Devuelve si la posición está ocupada (true) o no (false)
	 */
	public boolean hayAlgo(PosicionAjedrez posicion) {
		return elTablero.hayAlgo(posicion);
	}

	/** Permite inspeccionar una determinada posición del tablero
		@param posicion La posición a inspeccionar
		@return Un objeto PiezaColor, con el color y el nombre de la pieza en el casillero
		@throws CasilleroVacioException Se lanza en caso de estar vacío el casillero; debe censarse previamente para prevenirlo
	 */
	public PiezaColor queHay(PosicionAjedrez posicion) throws CasilleroVacioException {
		return elTablero.queHay(posicion);
	}

	/** Permite obtener las posiciones posibles a las que puede ir una pieza en un determinado casillero
	@param posicion La posicion en la que se encuentra la pieza cuyos casilleros posibles se quiere obtener
	@return Un Set de objetos PosicionAjedrez con las posiciones a las que puede ir la pieza en cuestión
	@throws CasilleroVacioException Se lanza si el casillero se encuentra vacío; debe censarse previamente para prevenirlo
	 */
	public Set<PosicionAjedrez> dameMovimientos(PosicionAjedrez posicion) throws CasilleroVacioException {
		return elTablero.damePosicionesPosibles(posicion);
	}

	/** Realiza una jugada en el tablero del juego
	@param posInicial Un objeto PosicionAjedrez con la posición de origen
	@param posFinal Un objeto PosicionAjedrez con la posición de destino
	@return Un objeto Jugada con la jugada hecha
	@throws CasilleroVacioException Se lanza si el casillero de posInicial está vacío
	@throws CoronacionPendienteException Se lanza si se intenta jugar cuando todavía falta a un jugador elegir una pieza para coronar
	@throws JugadaInvalidaException Se lanza si la jugada solicitada es inválida, ya sea por haber jaque mate, ahogado o por la imposibilidad de la pieza de moverse
	 */
	public Jugada mover(PosicionAjedrez posInicial, PosicionAjedrez posFinal) throws CasilleroVacioException, CoronacionPendienteException, JugadaInvalidaException {
		if (hayCoronacionPendiente)
			throw new CoronacionPendienteException();
		if (jaqueMate || ahogado){
			throw new JugadaInvalidaException();
		}
		if (!hayAlgo(posInicial)){
			throw new CasilleroVacioException();
		}
		if (queHay(posInicial).dameColor() != jugadorTurno.dameColor()){
			throw new JugadaInvalidaException();
		}
		if(elTablero.esMovimientoPosible(posInicial,posFinal)){
			Jugada laJugada = elTablero.moverPieza(posInicial, posFinal);
			hayCoronacionPendiente = elTablero.hayAlgoParaCoronar();
			registro.push(laJugada);
			if (!hayCoronacionPendiente) {
				cambiarTurno();
				jaqueMate = elTablero.hayJaqueMate(jugadorTurno.dameColor());
				if (!jaqueMate) {
					ahogado = elTablero.hayAhogado(jugadorTurno.dameColor());
				}
			}

			/**TEST**/
			elTablero.imprimirTablero();

			return laJugada;
		} else {
			throw new JugadaInvalidaException();
		}
	}

	/** Permite saber de quién es el turno
		@return Devuelve el color del jugador del turno como un objeto ColorPieza
	 */
	public ColorPieza dameTurno() {
		return jugadorTurno.dameColor();
	}

	// Revierte la última jugada hecha, sacándola de la pila del registro y
	//	mandándosela al tablero para que la deshaga 
	/** Revierte la última jugada hecha
	 */
	public void revertir() {
		if (registro.isEmpty()){
			throw new EmptyRegisterException();
		}
		elTablero.revertir(registro.pop());
		cambiarTurno();
		jaqueMate = false;
		ahogado = false;

		/**TEST**/
		elTablero.imprimirTablero();
	}

	/** Permite saber si hay jaque mate
	@return true si hay jaque mate
	 */
	public boolean hayJaqueMate() {
		return jaqueMate;
	}

	/** Permite saber si hay ahogado
	@return true si hay ahogado
	 */
	public boolean hayAhogado() {
		return ahogado;
	}

	/** Permite saber si hubo alguna jugada
	@return true si hubo alguna jugada
	 */
	public boolean huboUnaJugada() {
		return !registro.isEmpty();
	}

	/** Permite saber cuál fue la última jugada
	@return Un objeto Jugada con la Última jugada
	@throws EmptyRegisterException Se lanza si no hubo ninguna jugada; debe censarse previamente para prevenirlo
	 */
	public Jugada dameUltimaJugada() {
		if (registro.isEmpty())
			throw new EmptyRegisterException();
		else
			return registro.peek();
	}

	/** Permite saber cuántas medias jugadas se han hecho
	@return La cantidad de medias jugadas hechas
	 */
	public int cuantasJugadasVan() {
		return registro.size();
	}

	/** Permite saber si hay algún jugador en jaque
	@return true si hay jaque
	 */
	public boolean hayJaque() {
		return elTablero.hayJaque(jugadorTurno.dameColor());
	}

	/** Permite saber si hay alguna coronación pendiente, es decir, si algún jugador debe elegir qué pieza coronar
	@return true si hay coronación pendiente
	 */
	public boolean hayAlgoParaCoronar() {
		return hayCoronacionPendiente;
	}

	/** Realiza la coronación del peón que se encuentre al fondo con la pieza solicitada
	@param laPieza Un objeto NombrePieza con el nombre de la pieza que se solicita coronar
	@throws CoronacionInvalidaException Se lanza si la coronación solicitada no es válida, ya sea por pasar una pieza inválida (como un rey o un peón) o por no haber coronación pendiente
	 */
	public void coronar(NombrePieza laPieza) throws CoronacionInvalidaException {
		elTablero.coronar(laPieza, jugadorTurno.dameColor());
		cambiarTurno();
		hayCoronacionPendiente = false;
		jaqueMate = elTablero.hayJaqueMate(jugadorTurno.dameColor());
		if (!jaqueMate) {
			ahogado = elTablero.hayAhogado(jugadorTurno.dameColor());
		}
	}

	/** Informa si el jugador que tiene el turno puede enrocar corto
	@return true si se puede enrocar corto
	 */
	public boolean sePuedeEnrocarCorto() {
		return elTablero.puedeEnrocarCorto(jugadorTurno.dameColor());
	}

	/** Informa si el jugador que tiene el turno puede enrocar largo
	@return true si se puede enrocar largo
	 */
	public boolean sePuedeEnrocarLargo() {
		return elTablero.puedeEnrocarLargo(jugadorTurno.dameColor());
	}

	/** El jugador que tiene el turno enroca corto
	@throws CoronacionPendienteException Se lanza si todavía está pendiente coronar
	@throws EnroqueInvalidoException Se lanza si no es posible enrocar corto; se debe censar previamente para prevenirlo
	 */
	public void enrocarCorto() throws CoronacionPendienteException, EnroqueInvalidoException {
		enrocar(false);
	}

	/** El jugador que tiene el turno enroca largo
	@throws CoronacionPendienteException Se lanza si todavía está pendiente coronar
	@throws EnroqueInvalidoException Se lanza si no es posible enrocar largo; se debe censar previamente para prevenirlo
	 */
	public void enrocarLargo() throws CoronacionPendienteException, EnroqueInvalidoException {
		enrocar(true);		
	}

	/** Informa el tiempo de juego que ha consumido en jugador blanco
	@return cantidad de segundos consumidos
	 */
	public int dameTiempoBlancas(){
		return jugadorBlanco.dameTiempo();
	}

	/** Informa el tiempo de juego que ha consumido en jugador negro
	@return cantidad de segundos consumidos
	 */
	public int dameTiempoNegras(){
		return jugadorNegro.dameTiempo();
	}

	private void enrocar(boolean esLargo) throws CoronacionPendienteException, JugadaInvalidaException, EnroqueInvalidoException {
		if (hayCoronacionPendiente)
			throw new CoronacionPendienteException();
		if (jaqueMate || ahogado){
			throw new JugadaInvalidaException();
		}
		Jugada elEnroque = null;
		if (!esLargo)
			elEnroque = elTablero.enrocarCorto(jugadorTurno.dameColor());
		else
			elEnroque = elTablero.enrocarLargo(jugadorTurno.dameColor());
		registro.push(elEnroque);
		cambiarTurno();
		jaqueMate = elTablero.hayJaqueMate(jugadorTurno.dameColor());
		if (!jaqueMate) {
			ahogado = elTablero.hayAhogado(jugadorTurno.dameColor());
		}
	}

	private void cambiarTurno() {
		jugadorTurno.pausarTiempo();
		if (jugadorTurno.equals(jugadorBlanco)){
			jugadorTurno = jugadorNegro;
		} else {
			jugadorTurno = jugadorBlanco;
		}
		jugadorTurno.iniciarTiempo();
	}


	/***********************************************SÓLO PARA TEST*****************************************/
	public void imprimirTablero() {
		elTablero.imprimirTablero();
	}
}