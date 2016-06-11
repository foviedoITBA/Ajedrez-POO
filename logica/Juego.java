package logica;

import java.util.ArrayDeque;

import excepcion.CasilleroVacioException;
import excepcion.CoronacionPendienteException;
import excepcion.EmptyRegisterException;

import java.util.Set;

public class Juego {

	private Tablero elTablero; // Esta es una referencia al tablero
	
	private Jugador jugadorBlanco,jugadorNegro;
	private Jugador jugadorTurno;

	private  ArrayDeque<Jugada> registro; // Ésta es una pila donde se guardan las jugadas a medida que se hacen

	private boolean jaqueMate;	// Una variable que registra si hubo jaque mate o no
	private boolean ahogado;	// Una variable que registra si hubo ahogado o no
	
	private boolean hayCoronacionPendiente;

	public Juego() {
		elTablero = new Tablero();
		registro = new ArrayDeque<Jugada>();
		jaqueMate=false;
		ahogado=false;
		jugadorBlanco=new Jugador(Color.BLANCO);
		jugadorNegro=new Jugador(Color.NEGRO);
		jugadorTurno = jugadorBlanco;
		hayCoronacionPendiente = false;
		/**TEST**/
		elTablero.imprimirTablero();
	}
	
	//Agregar constructor para cargar una partida
	
	public boolean hayAlgo(PosicionAjedrez posicion) {
		return elTablero.hayAlgo(posicion);
	}
	
	public PiezaColor queHay(PosicionAjedrez posicion) throws CasilleroVacioException {
		return elTablero.queHay(posicion);
	}
	
	public Set<PosicionAjedrez> dameMovimientos(PosicionAjedrez posicion) throws CasilleroVacioException {
		return elTablero.damePosicionesPosibles(posicion);
	}
	
	public Jugada mover(PosicionAjedrez posInicial, PosicionAjedrez posFinal) throws CasilleroVacioException {
		if (hayCoronacionPendiente)
			throw new CoronacionPendienteException();
		if (jaqueMate || ahogado){
			return null;
		}
		if (!hayAlgo(posInicial)){
			return null;
		}
		if (queHay(posInicial).dameColor() != jugadorTurno.dameColor()){
			return null;
		}
		if(elTablero.esMovimientoPosible(posInicial,posFinal)){
			Jugada laJugada = elTablero.moverPieza(posInicial, posFinal);
			hayCoronacionPendiente = elTablero.hayAlgoParaCoronar(jugadorTurno.dameColor());
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
			return null;
		}
	}
		
	public Color dameTurno() {
		return jugadorTurno.dameColor();
	}
	
	// Revierte la última jugada hecha, sacándola de la pila del registro y
	//	mandándosela al tablero para que la deshaga 
	public void revertir() {
		if (registro.isEmpty()){
			return;
		}
		elTablero.revertir(registro.pop());
		cambiarTurno();
		jaqueMate = false;
		ahogado = false;
		
		/**TEST**/
		elTablero.imprimirTablero();
	}

	public boolean hayJaqueMate() {
		return jaqueMate;
	}

	public boolean hayAhogado() {
		return ahogado;
	}

	public boolean huboUnaJugada() {
		return !registro.isEmpty();
	}

	public Jugada dameUltimaJugada() {
		if (registro.isEmpty())
			throw new EmptyRegisterException();
		else
			return registro.peek();
	}

	public int cuantasJugadasVan() {
		return registro.size();
	}

	public boolean hayJaque() {
		return elTablero.hayJaque(jugadorTurno.dameColor());
	}

	public boolean hayAlgoParaCoronar() {
		return hayCoronacionPendiente;
	}

	public void coronar(NombrePieza laPieza) {
		elTablero.coronar(laPieza, jugadorTurno.dameColor());
		cambiarTurno();
		hayCoronacionPendiente=false;
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