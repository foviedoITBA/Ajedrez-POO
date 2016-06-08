package logica;

import java.util.Stack;

import excepcion.CasilleroVacioException;

import java.util.Set;

public class Juego {

	private Tablero elTablero; // Esta es una referencia al tablero
	
	private Jugador jugadorBlanco,jugadorNegro;
	private Jugador jugadorTurno;

	private  Stack<Jugada> registro; // Ésta es una pila donde se guardan las jugadas a medida que se hacen

	private boolean jaqueMate;	// Una variable que registra si hubo jaque mate o no
	private boolean ahogado;	// Una variable que registra si hubo ahogado o no
	


	public Juego() {
		elTablero = new Tablero();
		registro = new Stack<Jugada>();
		jaqueMate=false;
		ahogado=false;
		jugadorBlanco=new Jugador(Color.BLANCO);
		jugadorNegro=new Jugador(Color.NEGRO);
		jugadorTurno = jugadorBlanco;

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
			registro.push(laJugada);
			cambiarTurno();
			jaqueMate = elTablero.hayJaqueMate(jugadorTurno.dameColor());
			if (!jaqueMate){
				ahogado = elTablero.hayAhogado(jugadorTurno.dameColor());
			}
			
			/**TEST**/
			elTablero.imprimirTablero();
			
			return laJugada;
		} else{
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

	private void cambiarTurno() {
		if (jugadorTurno.equals(jugadorBlanco)){
			jugadorTurno = jugadorNegro;
		}else{
			jugadorTurno = jugadorBlanco;
		}
	}
	
}
