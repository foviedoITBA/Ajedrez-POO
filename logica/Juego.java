package logica;

import java.util.ArrayDeque;
import java.util.Set;

public class Juego {

	private Tablero elTablero; // Esta es una referencia al tablero
	
	private Jugador jugador1,jugador2;
	private Jugador ultimoJugador=jugador2;

	private  ArrayDeque<Jugada> registro; // Ésta es una pila donde se guardan las jugadas a medida que se hacen

	boolean jaqueMate;	// Una variable que registra si hubo jaque mate o no
	boolean ahogado;	// Una variable que registra si hubo ahogado o no
	
	public Juego() {
		elTablero = new Tablero();
		registro=new ArrayDeque<Jugada>();
		jaqueMate=false;
		ahogado=false;
		jugador1=new Jugador(Color.BLANCO);
		jugador2=new Jugador(Color.NEGRO);
	}
	
	//Agregar constructor para cargar una partida
	
	public boolean hayAlgo(PosicionAjedrez posicion) {
		return elTablero.hayAlgo(posicion);
	}
	
	public PiezaColor queHay(PosicionAjedrez posicion) throws Exception {
		return elTablero.queHay(posicion);
	}
	
	public Set<PosicionAjedrez> dameMovimientos(PosicionAjedrez posicion){
		return elTablero.dameMovimientos(posicion);
	}
	
	public boolean mover(PosicionAjedrez posInicial, PosicionAjedrez posFinal){
		return elTablero.mover(posInicial, posFinal);
	}
	
	
	
	public Jugador getTurno(){
		if(!ultimoJugador.equals(jugador1)){
			return jugador1;
		}else{
			return jugador2;
		}
	}
	
	
	/**
	 * Con este método se le dice al juego qué posición del tablero fue clickeada.
	 *  Devuelve una Jugada a la interfaz con la jugada, si es que hubo (ej: "7. Cb3")
	 *  Si no hubo jugada, devuelve null.
	 * @param pos_x
	 * @param pos_y
	 * @return un string con la jugada
	 */
	@Deprecated
	public Jugada clickTablero(int posX,int posY){//esta mal no puede devolver una jugada. hay problemas con que guardaria mas cosas de las necesarias en el stack
		Jugada resp=null;
		PosicionTablero pos = new PosicionTablero(posX,posY);
		
		resp=elTablero.click(pos, getTurno());
		
		
		if(resp != null){
			registro.add(resp);
			
			ultimoJugador=getTurno();
		}
		
		return resp;
		
	};					

	//public void revertir();	// Revierte la última jugada hecha, sacándola de la pila del registro y mandándosela al tablero para
							// que la deshaga
	
	/* Falta algo que diga de quién es el turno, pero todavía no se me ocurrió si implementar clases 'Jugador' o simplemente decir 'blancas' o 'negras' */

	/* Otros métodos y miembros que hagan falta */
}
