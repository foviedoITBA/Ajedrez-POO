package grafica;

import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;

public class ControladorJuego {
	private Jugador jugadorBlanco, jugadorNegro, jugadorTurno;
	private Juego elJuego;
	private final boolean esUnJugador;
	private Tablero elTablero;
	private EstadoDeJuego estado;
	private TablaJugadas tabla;
	
	
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
	
	
	private void actualizoJugadorTurno(){
		if(elJuego.dameTurno() == ColorPieza.BLANCO){
			jugadorTurno = jugadorBlanco;
		}else{
			jugadorTurno = jugadorNegro;
		}
	}
	
	
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
	
	public void enroqueLargo(){
		if(elJuego.sePuedeEnrocarLargo()){
			elJuego.enrocarLargo();
			tabla.agregarJugada(elJuego.dameUltimaJugada());
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
	}
	
	public void enroqueCorto(){
		if(elJuego.sePuedeEnrocarCorto()){
			elJuego.enrocarCorto();
			tabla.agregarJugada(elJuego.dameUltimaJugada());
			elTablero.imprimirTablero();
			estado.actualizarEstado();
		}
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
		if(jugadorTurno.huboUnaJugada()){
			tabla.agregarJugada(elJuego.dameUltimaJugada());
		}
	}
	
	
	
}
