package logica;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tablero {
	
	private final static int SIZE_TABLERO=8;
	
	private Casillero[][] losCasilleros;	// Una matriz de casilleros, uno por cada escaque

	private Posicion seleccionado;	// Una referencia a la posicion del casillero seleccionado (lo cual se hace haciéndole click)
	
	public Tablero(){
		losCasilleros = new Casillero[SIZE_TABLERO][SIZE_TABLERO];
		initTablero();
		seleccionado=null;
	}
	
	/*
	 * recibo una posicion
	 * veo si seleccionado esta en null
	 * si esta en null significa que aprete para ver los movimientos de la pieza -> pido movimientos a la pieza y devuelvo los que estan vacios o tienen pieza del oponente
	 * si no esta en null es porque quiero mover la pieza ahi -> comparo si es uno de los movimientos posibles
	 * 		si es posible miro si esta vacio -> muevo a esa posicion
	 * 		si esta lleno significa que tiene una pieza del oponente -> mato y guardo la jugada en Juego
	 * */
	public void click(Posicion pos, Jugador jugador){ // cambiar a qeu devueleve una jugada
		
		Object resp=null;//que es?
		
		if(seleccionado==null){
			
			if(losCasilleros[pos.getY()][pos.getX()].isEmpty() || !losCasilleros[pos.getY()][pos.getX()].getPieza().dameColor().equals(jugador.dameColor())){
				return;//nada resp
			}
			
			seleccionado=pos;
			
			resp = analizoMovimientos(seleccionado, jugador); //una array de movimientos posibles?
			
			
		}else{
			if(esMovimientoPosible(pos, jugador)){
				if(!losCasilleros[pos.getY()][pos.getX()].isEmpty()){
					//aviso que mate
					resp= "Mate";
				}
				losCasilleros[pos.getY()][pos.getX()].addPieza(losCasilleros[seleccionado.getY()][seleccionado.getX()].getPieza());
				losCasilleros[seleccionado.getY()][seleccionado.getX()].removePieza();
				//no devuelvo nada
			}
			seleccionado=null;
		}

		System.out.println(resp);
		
		return; //resp
		
	}
	
	
	// Este método lo usa el juego para decirle qué casillero fue clickeado
										// Entonces el tablero decide en base al casillero clickeado y al seleccionado
										// (anteriormente) si debe hacerse una jugada o no. Si se hace, se devuelve en el
										// método y se pone seleccionado en null; si no se hace, se pone seleccionado en el que // se clickeó ((pos_x, pos_y) sería) y se devuelve null.

	
	
	
//	void revertir(Jugada laJugada);		// A ḿétodo lo llama 'revertir()' de la clase Juego. Le pasa la jugada para que la
										// deshaga (fíjense que el tablero no sabe cuál fue la última jugada, entonces hay que
										// decírselo)

	/* Otros métodos y miembros que hagan falta */
	
	private void initTablero(){ //coloco el tablero con las piezas en la posicion inicial
		
		losCasilleros[0][0]=new Casillero(new Torre(Color.NEGRO));
		losCasilleros[0][1]=new Casillero(new Caballo(Color.NEGRO));
		losCasilleros[0][2]=new Casillero(new Alfil(Color.NEGRO));
		losCasilleros[0][3]=new Casillero(new Dama(Color.NEGRO));
		losCasilleros[0][4]=new Casillero(new Rey(Color.NEGRO));
		losCasilleros[0][5]=new Casillero(new Alfil(Color.NEGRO));
		losCasilleros[0][6]=new Casillero(new Caballo(Color.NEGRO));
		losCasilleros[0][7]=new Casillero(new Torre(Color.NEGRO));
		
		losCasilleros[7][0]=new Casillero(new Torre(Color.BLANCO));
		losCasilleros[7][1]=new Casillero(new Caballo(Color.BLANCO));
		losCasilleros[7][2]=new Casillero(new Alfil(Color.BLANCO));
		losCasilleros[7][3]=new Casillero(new Dama(Color.BLANCO));
		losCasilleros[7][4]=new Casillero(new Rey(Color.BLANCO));
		losCasilleros[7][5]=new Casillero(new Alfil(Color.BLANCO));
		losCasilleros[7][6]=new Casillero(new Caballo(Color.BLANCO));
		losCasilleros[7][7]=new Casillero(new Torre(Color.BLANCO));
		
		for(int i=1;i<SIZE_TABLERO-1;i++){
			for(int j=0;j<SIZE_TABLERO;j++){
				if(i==1){
					losCasilleros[i][j]=new Casillero(new Peon(Color.NEGRO));
				}else if(i==SIZE_TABLERO-2){
					losCasilleros[i][j]=new Casillero(new Peon(Color.BLANCO));
				}else{
					losCasilleros[i][j]=new Casillero();
				}
				
			}
		}
	}
	
	/*
	 * Recive una posicion y un jugador, sabiendo que en la posicion esa hay una pieza,
	 * Devuelve un set de Posiciones a las cuales se pude mover la pieza
	 * */
	private Set<Posicion> analizoMovimientos(Posicion pos, Jugador jugador){
		
		Casillero casillero=losCasilleros[pos.getY()][pos.getX()];
		Set<Posicion> movPosibles = new HashSet<>();
		List<Movimiento> movPieza = casillero.getPieza().dameMovimientos(); 
		
		
		for(int i=0; i<movPieza.size(); i++){
			int incX=movPieza.get(i).dameMovX();
			int incY=movPieza.get(i).dameMovY();
			boolean cont=true;
			boolean unaVez = movPieza.get(i).esUnaVez();
			
			for(int posX=pos.getX()+incX, posY=pos.getY()+incY; cont && posX>=0 && posY>=0 && posX<SIZE_TABLERO && posY<SIZE_TABLERO; posX+=incX, posY+=incY){
				
				if(losCasilleros[posY][posX].isEmpty()){
					movPosibles.add(new Posicion(posX,posY));
				}else if(!losCasilleros[posY][posX].getPieza().dameColor().equals(jugador.dameColor())){
					movPosibles.add(new Posicion(posX,posY));
					cont=false;
				}else{//Pieza del jugador actual
					cont=false;
				}
				
				if(unaVez){
					cont=false;
				}
				
			}
			
		}
		
//		if(losCasilleros[pos.getY()][pos.getX()].getPieza().getClass() == Peon.class){
//			return movimientosPeon(pos, jugador);
//		}else{
//			return movimientosGeneral(pos, jugador);
//		}
			
		return movPosibles;
	}
	
//	
	
	private boolean esMovimientoPosible(Posicion pos, Jugador jugador){
		
		Set<Posicion> movPosibles = analizoMovimientos(seleccionado, jugador);
		
		if(movPosibles.contains(pos)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
	
	
	
	/** SOLO PARA TESTEAR **/
	public void imprimirTablero(){
		System.out.print("\n\n*************************************************************\n\n");
		for(int i=0;i<SIZE_TABLERO;i++){
			for(int j=0;j<SIZE_TABLERO;j++){
				
				if(losCasilleros[i][j].isEmpty()){
					System.out.print("0\t");
				}else{
					if(losCasilleros[i][j].getPieza().dameColor().equals(Color.BLANCO)){
						System.out.print("1(B)\t");
					}else{
						System.out.print("1(N)\t");
					}
				}
				
			}
			System.out.print("\n");
		}
		
	}
	
	public void agregoNegra(){
		losCasilleros[6][0].addPieza(new Torre(Color.NEGRO));
	}
	
}
