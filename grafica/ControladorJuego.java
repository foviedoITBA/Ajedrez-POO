package grafica;

import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;

public class ControladorJuego {
	private Jugador jugadorBlanco, jugadorNegro, jugadorTurno;
	private Juego elJuego;
	private final boolean esUnJugador;
	private Tablero elTablero;
	EstadoDeJuego estado;
	
	
	public ControladorJuego (int cantJugadores,ColorPieza colorElegido, Tablero tablero,Juego elJuego,EstadoDeJuego estado){
		this.elJuego=elJuego;
		esUnJugador = (cantJugadores == 1);
		elTablero = tablero;
		this.estado=estado;
		
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
		
		//elTablero.imprimirTablero();
		
	}
	
	
	private void actualizoJugadorTurno(){
		if(elJuego.dameTurno() == ColorPieza.BLANCO){
			jugadorTurno = jugadorBlanco;
		}else{
			jugadorTurno = jugadorNegro;
		}
	}
	
	
	public void deshacerJugada(){
		
	}
	
	public void enroqueLargo(){
		
	}
	
	public void enroqueCorto(){
		
	}
	
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
		//imprimo tabla jugadas
		
	}
	
	
	
}
