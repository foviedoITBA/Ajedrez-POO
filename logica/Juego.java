package logica;

import java.util.Stack;
import java.util.Set;

public class Juego {

	private Tablero elTablero; // Esta es una referencia al tablero
	
	private Jugador jugadorBlanco,jugadorNegro;
	private Jugador jugadorTurno;

	private  Stack<Jugada> registro; // Ésta es una pila donde se guardan las jugadas a medida que se hacen

	boolean jaqueMate;	// Una variable que registra si hubo jaque mate o no
	boolean ahogado;	// Una variable que registra si hubo ahogado o no
	
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
	
	public PiezaColor queHay(PosicionAjedrez posicion) throws Exception {
		return elTablero.queHay(posicion);
	}
	
	public Set<PosicionAjedrez> dameMovimientos(PosicionAjedrez posicion) throws Exception {
		return elTablero.damePosicionesPosibles(posicion);
	}
	
	public Jugada mover(PosicionAjedrez posInicial, PosicionAjedrez posFinal) throws Exception {
		if (!hayAlgo(posInicial))
			return null;
		if (queHay(posInicial).dameColor() != jugadorTurno.dameColor())
			return null;
		if(elTablero.esMovimientoPosible(posInicial,posFinal)){
			Jugada laJugada = elTablero.moverPieza(posInicial, posFinal);
			registro.push(laJugada);
			cambiarTurno();
			
			/**TEST**/
			elTablero.imprimirTablero();
			
			return laJugada;
		} else
			return null;
	}
		
	public Jugador getTurno() {
		return jugadorTurno;
	}
	
	// Revierte la última jugada hecha, sacándola de la pila del registro y mandándosela al tablero para que la deshaga 
	public void revertir() {
		if (registro.isEmpty())
			return;
		elTablero.revertir(registro.pop());
		cambiarTurno();
		/**TEST**/
		elTablero.imprimirTablero();
	}
	
	private void cambiarTurno() {
		if (jugadorTurno.equals(jugadorBlanco))
			jugadorTurno = jugadorNegro;
		else
			jugadorTurno = jugadorBlanco;
	}


	/* Otros métodos y miembros que hagan falta */
}
