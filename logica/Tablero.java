package logica;

import java.util.HashSet;
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
			
			System.out.println(losCasilleros[pos.getX()][pos.getY()].getPieza().esBlanca()==jugador.esBlanco());
			System.out.println(losCasilleros[pos.getX()][pos.getY()].getPieza().esBlanca());
			System.out.println(jugador.esBlanco());//problema con el ==
			
			if(losCasilleros[pos.getX()][pos.getY()].isEmpty() || losCasilleros[pos.getX()][pos.getY()].getPieza().esBlanca()==jugador.esBlanco()){
				return;//nada resp
			}
			
			seleccionado=pos;
			
			resp = analizoMovimientos(seleccionado, jugador); //una array de movimientos posibles?
			
			
		}else{
			if(esMovimientoPosible(pos, jugador)){
				if(!losCasilleros[pos.getX()][pos.getY()].isEmpty()){
					//aviso que mate
					resp= "Mate";
				}
				losCasilleros[pos.getX()][pos.getY()].addPieza(losCasilleros[seleccionado.getX()][seleccionado.getY()].getPieza());
				losCasilleros[seleccionado.getX()][seleccionado.getY()].removePieza();
				//no devuelvo nada
			}
			seleccionado=null;
		}

		System.out.println((Set<Posicion>)resp);
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
		
		losCasilleros[0][0]=new Casillero(new Torre(Pieza.NEGRA));
		losCasilleros[0][1]=new Casillero(new Caballo(Pieza.NEGRA));
		losCasilleros[0][2]=new Casillero(new Alfil(Pieza.NEGRA));
		losCasilleros[0][3]=new Casillero(new Reina(Pieza.NEGRA));
		losCasilleros[0][4]=new Casillero(new Rey(Pieza.NEGRA));
		losCasilleros[0][5]=new Casillero(new Alfil(Pieza.NEGRA));
		losCasilleros[0][6]=new Casillero(new Caballo(Pieza.NEGRA));
		losCasilleros[0][7]=new Casillero(new Torre(Pieza.NEGRA));
		
		losCasilleros[7][0]=new Casillero(new Torre(Pieza.BLANCA));
		losCasilleros[7][1]=new Casillero(new Caballo(Pieza.BLANCA));
		losCasilleros[7][2]=new Casillero(new Alfil(Pieza.BLANCA));
		losCasilleros[7][3]=new Casillero(new Reina(Pieza.BLANCA));
		losCasilleros[7][4]=new Casillero(new Rey(Pieza.BLANCA));
		losCasilleros[7][5]=new Casillero(new Alfil(Pieza.BLANCA));
		losCasilleros[7][6]=new Casillero(new Caballo(Pieza.BLANCA));
		losCasilleros[7][7]=new Casillero(new Torre(Pieza.BLANCA));
		
		for(int i=1;i<SIZE_TABLERO-1;i++){
			for(int j=0;j<SIZE_TABLERO;j++){
				if(i==1){
					losCasilleros[i][j]=new Casillero(new Peon(Pieza.NEGRA));
				}else if(i==SIZE_TABLERO-2){
					losCasilleros[i][j]=new Casillero(new Peon(Pieza.BLANCA));
				}else{
					losCasilleros[i][j]=new Casillero();
				}
				
			}
		}
	}
	
	
	private Set<Posicion> analizoMovimientos(Posicion pos, Jugador jugador){
		Casillero casillero=losCasilleros[pos.getX()][pos.getY()];
		Set<Posicion> list = new HashSet<>();
		
		Movimiento[] movPieza = casillero.getPieza().getMovimientos(); 
		
		
		for(int i=0; i<movPieza.length; i++){
			int incX=movPieza[i].getMovX();
			int incY=movPieza[i].getMovY();
			boolean cont=true;
			
			for(int posX=pos.getX()+incX, posY=pos.getY()+incY; cont && posX>=0 && posY>=0 && posX<SIZE_TABLERO && posY<SIZE_TABLERO; posX+=incX, posY+=incY){
				
				if(losCasilleros[posX][posY].isEmpty()){
					list.add(new Posicion(posX,posY));
				}else if(losCasilleros[posX][posY].getPieza().esBlanca()==jugador.esBlanco()){//problema con el ==
					list.add(new Posicion(posX,posY));
					cont=false;
				}else{//Pieza del jugador actual
					cont=false;
				}
				
			}
			
		}
			
		return list;
	}
	
	
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
		for(int i=0;i<SIZE_TABLERO;i++){
			for(int j=0;j<SIZE_TABLERO;j++){
				
				if(losCasilleros[i][j].isEmpty()){
					System.out.print("0\t");
				}else{
					if(losCasilleros[i][j].getPieza().esBlanca()){
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
		losCasilleros[6][0].addPieza(new Torre(Pieza.NEGRA));
	}
	
}
