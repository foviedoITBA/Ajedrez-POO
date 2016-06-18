package logica;

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
	juego (qui&eacute;n tiene el turno, si hubo jaque mate, qu&eacute; jugadas se hicieron, entre otras); manejar la
	comunicaci&oacute;n con el modelo para conocer o alterar el estado del juego.
 */
public class Juego implements Serializable{

	private static final long serialVersionUID = 1L;

	private Tablero elTablero; // Esta es una referencia al tablero

	private Jugador jugadorBlanco,jugadorNegro;
	private Jugador jugadorTurno;


	private  ArrayDeque<Jugada> registro; // Ésta es una pila donde se guardan las jugadas a medida que se hacen

	private boolean jaqueMate;	// Una variable que registra si hubo jaque mate o no
	private boolean ahogado;	// Una variable que registra si hubo ahogado o no

	private boolean hayCoronacionPendiente;

	/** Instancia una nueva partida lista para ser jugada
	 */
	
	public Juego(){
		elTablero = new Tablero();
		registro = new ArrayDeque<Jugada>();
		jaqueMate=false;
		ahogado=false;
		jugadorBlanco=new Jugador(ColorPieza.BLANCO);
		jugadorNegro=new Jugador(ColorPieza.NEGRO);
		jugadorTurno = jugadorBlanco;
		hayCoronacionPendiente = false;
	}

	/** Permite conocer si hay alguna pieza en una determinana posici&oacute;n del tablero
	@param posicion La posicion del tablero a inspeccionar
	@return Devuelve si la posici&oacute;n est&aacute; ocupada (true) o no (false)
	 */
	public boolean hayAlgo(PosicionAjedrez posicion) {
		return elTablero.hayAlgo(posicion);
	}

	/** Permite inspeccionar una determinada posici&oacute;n del tablero
		@param posicion La posici&oacute;n a inspeccionar
		@return Un objeto PiezaColor, con el color y el nombre de la pieza en el casillero
		@throws CasilleroVacioException Se lanza en caso de estar vac&iacute;o el casillero; debe censarse previamente para prevenirlo
	 */
	public PiezaColor queHay(PosicionAjedrez posicion) throws CasilleroVacioException {
		return elTablero.queHay(posicion);
	}

	/** Permite obtener las posiciones posibles a las que puede ir una pieza en un determinado casillero
	@param posicion La posicion en la que se encuentra la pieza cuyos casilleros posibles se quiere obtener
	@return Un Set de objetos PosicionAjedrez con las posiciones a las que puede ir la pieza en cuesti&oacute;n
	@throws CasilleroVacioException Se lanza si el casillero se encuentra vac&iacute;o; debe censarse previamente para prevenirlo
	 */
	public Set<PosicionAjedrez> dameMovimientos(PosicionAjedrez posicion) throws CasilleroVacioException {
		return elTablero.damePosicionesPosibles(posicion);
	}

	/** Realiza una jugada en el tablero del juego
	@param posInicial Un objeto PosicionAjedrez con la posici&oacute;n de origen
	@param posFinal Un objeto PosicionAjedrez con la posici&oacute;n de destino
	@return Un objeto Jugada con la jugada hecha
	@throws CasilleroVacioException Se lanza si el casillero de posInicial est&aacute; vac&iacute;o
	@throws CoronacionPendienteException Se lanza si se intenta jugar cuando todav&iacute;a falta a un jugador elegir una pieza para coronar
	@throws JugadaInvalidaException Se lanza si la jugada solicitada es inv&aacute;lida, ya sea por haber jaque mate, ahogado o por la imposibilidad de la pieza de moverse
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

			return laJugada;
		} else {
			throw new JugadaInvalidaException();
		}
	}

	/** Permite saber de qui&eacute;n es el turno
		@return Devuelve el color del jugador del turno como un objeto ColorPieza
	 */
	public ColorPieza dameTurno() {
		return jugadorTurno.dameColor();
	}

	// Revierte la última jugada hecha, sacándola de la pila del registro y
	//	mandándosela al tablero para que la deshaga 
	/** Revierte la &uacute;ltima jugada hecha
	 */
	public void revertir() {
		if (registro.isEmpty()){
			throw new EmptyRegisterException();
		}
		elTablero.revertir(registro.pop());
		cambiarTurno();
		jaqueMate = false;
		ahogado = false;

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

	/** Permite saber cu&aacute;l fue la &uacute;ltima jugada
	@return Un objeto Jugada con la &uacute;ltima jugada
	@throws EmptyRegisterException Se lanza si no hubo ninguna jugada; debe censarse previamente para prevenirlo
	 */
	public Jugada dameUltimaJugada() {
		if (registro.isEmpty())
			throw new EmptyRegisterException();
		else
			return registro.peek();
	}

	/** Permite saber cu&aacute;ntas medias jugadas se han hecho
	@return La cantidad de medias jugadas hechas
	 */
	public int cuantasJugadasVan() {
		return registro.size();
	}

	/** Permite saber si hay alg&uacute;n jugador en jaque
	@return true si hay jaque
	 */
	public boolean hayJaque() {
		return elTablero.hayJaque(jugadorTurno.dameColor());
	}

	/** Permite saber si hay alguna coronaci&oacute;n pendiente, es decir, si alg&uacute;n jugador debe elegir qu&eacute; pieza coronar
	@return true si hay coronaci&oacute;n pendiente
	 */
	public boolean hayAlgoParaCoronar() {
		return hayCoronacionPendiente;
	}

	/** Realiza la coronaci&oacute;n del pe&oacute;n que se encuentre al fondo con la pieza solicitada
	@param laPieza Un objeto NombrePieza con el nombre de la pieza que se solicita coronar
	@throws CoronacionInvalidaException Se lanza si la coronaci&oacute;n solicitada no es v&aacute;lida, ya sea por pasar una pieza inv&aacute;lida (como un rey o un pe&oacute;n) o por no haber coronaci&oacute;n pendiente
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
	@throws CoronacionPendienteException Se lanza si todav&iacute;a est&aacute; pendiente coronar
	@throws EnroqueInvalidoException Se lanza si no es posible enrocar corto; se debe censar previamente para prevenirlo
	 */
	public void enrocarCorto() throws CoronacionPendienteException, EnroqueInvalidoException {
		enrocar(false);
	}

	/** El jugador que tiene el turno enroca largo
	@throws CoronacionPendienteException Se lanza si todav&iacute;a est&aacute; pendiente coronar
	@throws EnroqueInvalidoException Se lanza si no es posible enrocar largo; se debe censar previamente para prevenirlo
	 */
	public void enrocarLargo() throws CoronacionPendienteException, EnroqueInvalidoException {
		enrocar(true);		
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
		if (jugadorTurno.equals(jugadorBlanco)){
			jugadorTurno = jugadorNegro;
		} else {
			jugadorTurno = jugadorBlanco;
		}
	}


}