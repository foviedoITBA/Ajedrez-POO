package logica;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Tablero {

	private final static int SIZE_TABLERO=8;

	private Casillero[][] losCasilleros;	// Una matriz de casilleros, uno por cada escaque

	//private PosicionTablero seleccionado;	// Una referencia a la posicion del casillero seleccionado (lo cual se hace haciéndole click)

	public Tablero(){
		losCasilleros = new Casillero[SIZE_TABLERO][SIZE_TABLERO];
		initTablero();
		//seleccionado=null;
	}

	/**
	 * recibo una posicion
	 * veo si seleccionado esta en null
	 * si esta en null significa que aprete para ver los movimientos de la pieza -> pido movimientos a la pieza y devuelvo los que estan vacios o tienen pieza del oponente
	 * si no esta en null es porque quiero mover la pieza ahi -> comparo si es uno de los movimientos posibles
	 * 		si es posible miro si esta vacio -> muevo a esa posicion
	 * 		si esta lleno significa que tiene una pieza del oponente -> mato y guardo la jugada en Juego
	 * */
	
	/*
	@Deprecated
	public void click(PosicionTablero pos, Jugador jugador){ // cambiar a qeu devueleve una jugada

		Object resp = null;//que es?
		Casillero casClickeado = losCasilleros[pos.getX()][pos.getY()];
		
		if(seleccionado == null) {

			if(casClickeado.isEmpty() || casClickeado.getPieza().dameColor() != jugador.dameColor()) {
				return;//nada resp
			}

			seleccionado=pos;

			resp = posicionesPosibles(seleccionado, jugador); //una array de movimientos posibles?


		} else {
			Casillero casSelecc = losCasilleros[seleccionado.getX()][seleccionado.getY()];
			if (casClickeado.isEmpty() || casClickeado.getPieza().dameColor() != jugador.dameColor()) {
				if(esMovimientoPosible(pos, jugador)){
					if(!casClickeado.isEmpty()){
						//aviso que comi
						resp= "Comi";
					}
					casClickeado.addPieza(casSelecc.getPieza());
					casSelecc.getPieza().ponerSeMovio();
					casSelecc.removePieza();

					//no devuelvo nada o devuelvo una jugada

					seleccionado=null;
				}
			} else {
				seleccionado = pos;
				resp = posicionesPosibles(seleccionado, jugador);
			}

		}

		System.out.println(resp);

		return; //resp

	}
	*/

	public boolean hayAlgo(PosicionAjedrez posAjedrez) {
		PosicionTablero posTablero = transformarPosicion(posAjedrez);
		return !losCasilleros[posTablero.getX()][posTablero.getY()].isEmpty();
	}
	
	public PiezaColor queHay(PosicionAjedrez posAjedrez) throws Exception {
		PosicionTablero posTablero = transformarPosicion(posAjedrez);
		Pieza laPieza = losCasilleros[posTablero.getX()][posTablero.getY()].getPieza();
		if (laPieza == null){
			throw new Exception();
		}
		NombrePieza elNombre = laPieza.dameNombre();
		Color elColor = laPieza.dameColor();
		return new PiezaColor(elNombre, elColor);
		
	}

	public Set<PosicionAjedrez> damePosicionesPosibles(PosicionAjedrez posAjedrez) throws Exception {
		PosicionTablero posTablero = transformarPosicion(posAjedrez);
		Pieza laPieza = losCasilleros[posTablero.getX()][posTablero.getY()].getPieza();
		if (laPieza == null)
			throw new Exception();
		Set<PosicionTablero> lasPosicionesTablero = posicionesPosibles(posTablero);
		Set<PosicionAjedrez> lasPosicionesAjedrez = new HashSet<>();
		for (PosicionTablero unaPosTablero: lasPosicionesTablero)
			lasPosicionesAjedrez.add(transformarPosicion(unaPosTablero));
		return lasPosicionesAjedrez;
	}

	public boolean esMovimientoPosible(PosicionAjedrez posOrigen, PosicionAjedrez posDestino) {
		PosicionTablero posOrigenT = transformarPosicion(posOrigen);
		PosicionTablero posDestinoT = transformarPosicion(posDestino);
		Pieza piezaMoviendo = losCasilleros[posOrigenT.getX()][posOrigenT.getY()].getPieza();
		if (piezaMoviendo == null)
			return false;
		Set<PosicionTablero> posicionesPosibles = null;
		try { // Ya sé que no va a tirar excepción porque me acabo de fijar que posOrigen no sea null
			posicionesPosibles = posicionesPosibles(posOrigenT);
		}
		catch(Exception e){}
		if (posicionesPosibles.contains(posDestinoT))
			return true;
		return false;
	}
	
	public Jugada moverPieza(PosicionAjedrez posOrigen, PosicionAjedrez posDestino){
		PosicionTablero posOrigenT = transformarPosicion(posOrigen);
		PosicionTablero posDestinoT = transformarPosicion(posDestino);
		Casillero casOrigen = losCasilleros[posOrigenT.getX()][posOrigenT.getY()];
		Casillero casDestino = losCasilleros[posDestinoT.getX()][posDestinoT.getY()];
		Pieza piezaMoviendo = casOrigen.getPieza();
		casDestino.addPieza(piezaMoviendo);
		piezaMoviendo.ponerSeMovio();
		casOrigen.removePieza();
		return new Jugada(posOrigen,posDestino);
	}
	
	private PosicionTablero transformarPosicion(PosicionAjedrez posAjedrez) {
		byte fila = posAjedrez.dameFila();
		char columna = posAjedrez.dameColumna();
		int laFila = 8 - fila;
		int laColumna = columna - 'a';
		return new PosicionTablero(laFila, laColumna);
	}

	private PosicionAjedrez transformarPosicion(PosicionTablero posTablero) {
		int fila = posTablero.getX();
		int columna = posTablero.getY();
		byte laFila = (byte) (8 - fila);
		char laColumna = (char) (columna + 'a');
		PosicionAjedrez posAjedrez = null;
		try { // Seguro que posTablero está bien construida, si no, habría lanzado una excepción
			posAjedrez = new PosicionAjedrez(laFila, laColumna);
		}
		catch(Exception e){}
		return posAjedrez;
	}
	
	private void initTablero() { //coloco el tablero con las piezas en la posicion inicial

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

	/* Devuelve todos las posiciones posibles de la pieza en pos considerando que no debe quedar el rey en jaque */
	private Set<PosicionTablero> posicionesPosibles(PosicionTablero pos) throws Exception {
		// Obtengo todos los lugares a los que podría potencialmente ir la pieza
		Casillero casilleroFuente = losCasilleros[pos.getX()][pos.getY()];		
		Pieza piezaMoviendo = casilleroFuente.getPieza();
		if (piezaMoviendo == null)
			throw new Exception();
		Set<PosicionTablero> posicionesPosibles = analizoMovimientos(pos, false);
		// Ahora hay que ver cuáles de esas jugadas dejan al rey en jaque y sacarlas de posicionesPosibles
		// Para eso primero busco la posición del rey del jugador que tiene el turno
		PosicionTablero posRey = null; // Hay que darle un valor sí o sí para que compile
		boolean encontreAlRey = false;
		for (int i = 0; i < SIZE_TABLERO && !encontreAlRey; i++) {
			for (int j = 0; j < SIZE_TABLERO && !encontreAlRey; j++) {
				if (!losCasilleros[i][j].isEmpty() && losCasilleros[i][j].getPieza().dameColor() == piezaMoviendo.dameColor() && losCasilleros[i][j].getPieza() instanceof Rey) {
					posRey = new PosicionTablero(i,j);
					encontreAlRey = true;
				}
			}
		}
		// Luego, recorro todas las jugadas posibles de la pieza seleccionada y para cada una de ellas me fijo
		// en todos los movimientos posibles de todas las piezas del adversario que sean comiendo para ver que
		// no esté el rey entre las posiciones posibles (o sea, me fijo que el rey no quede en jaque)
		Stack<PosicionTablero> posicionesParaBorrar = new Stack<>();
		// Recorro las posiciones posibles para la pieza que se quiere mover
		for (PosicionTablero posicionPosible: posicionesPosibles) {
			boolean esPosible = true;
			Casillero casilleroDestino = losCasilleros[posicionPosible.getX()][posicionPosible.getY()];
			// "Hago" la jugada
			Pieza piezaRespaldo = casilleroDestino.getPieza();
			casilleroDestino.addPieza(piezaMoviendo);
			casilleroFuente.removePieza();
			// Si estoy tratando de mover al rey, tengo que cambiar posRey
			if (piezaMoviendo instanceof Rey)
				posRey = posicionPosible;
			// Recorro el tablero buscando las piezas del oponente que puedan estar dejando en jaque al rey
			for (int i = 0; i < SIZE_TABLERO && esPosible; i++) {
				for (int j = 0; j < SIZE_TABLERO && esPosible; j++) {
					// Si en el casillero no hay una pieza del adversario, sigo de largo
					if (losCasilleros[i][j].isEmpty() || losCasilleros[i][j].getPieza().dameColor() == piezaMoviendo.dameColor())
						continue;
					// Si hay una del adversario, me fijo que no pueda "comerse" al rey del que mueve
					PosicionTablero estaPos = new PosicionTablero(i,j);
					Set<PosicionTablero> posicionesComiendo = analizoMovimientos(estaPos, true);
					if (posicionesComiendo.contains(posRey)) {
						// Acá querría sacarlo del set de posiciones posibles,
						// pero no lo puedo hacer porque lo estoy iterando.
						// Entonces me lo guardo en un stack y después lo saco
						posicionesParaBorrar.push(posicionPosible);
						esPosible = false;
					}

				}
			}

			// Restauro el tablero a como estaba
			casilleroFuente.addPieza(piezaMoviendo);
			casilleroDestino.addPieza(piezaRespaldo);

		}
		// Ahora saco las posiciones que no son válidas por dejar al rey en jaque del set de posiciones posibles
		while(!posicionesParaBorrar.empty())
			posicionesPosibles.remove(posicionesParaBorrar.pop());

		return posicionesPosibles;
	}

	/* Devuelve todos los movimientos posibles de la pieza en pos sin considerar que el rey pueda quedar en jaque */
	private Set<PosicionTablero> analizoMovimientos(PosicionTablero pos, boolean soloComiendo) throws Exception {

		Casillero casillero=losCasilleros[pos.getX()][pos.getY()];
		Pieza piezaMoviendo = casillero.getPieza();
		if (piezaMoviendo == null)
			throw new Exception();
		Set<PosicionTablero> movPosibles = new HashSet<>();
		List<Movimiento> movPieza = casillero.getPieza().dameMovimientos(); 

		for(Movimiento elMovimiento: movPieza) {
			int incX=elMovimiento.dameMovX();
			int incY=elMovimiento.dameMovY();
			boolean cont=true;
			int cantidadDeVeces = elMovimiento.cantidadDeVeces();

			for(int posX=pos.getX()+incX, posY=pos.getY()+incY; cont && dentroTablero(posX,posY) && cantidadDeVeces!=0; posX+=incX, posY+=incY, cantidadDeVeces--){

				if(losCasilleros[posX][posY].isEmpty() && elMovimiento.esSinComer()){
					if (!soloComiendo)
						movPosibles.add(new PosicionTablero(posX,posY));
				}else if(!losCasilleros[posX][posY].isEmpty() && losCasilleros[posX][posY].getPieza().dameColor() != piezaMoviendo.dameColor() && elMovimiento.esComiendo()){
					movPosibles.add(new PosicionTablero(posX,posY));
					cont=false;
				}else{
					cont=false;
				}

			}

		}

		return movPosibles;
	}

	private static boolean dentroTablero(int posX, int posY){
		return posX>=0 && posY>=0 && posX<SIZE_TABLERO && posY<SIZE_TABLERO;
	}


	//	void revertir(Jugada laJugada);		// A ḿétodo lo llama 'revertir()' de la clase Juego. Le pasa la jugada para que la
	// deshaga (fíjense que el tablero no sabe cuál fue la última jugada, entonces hay que
	// decírselo)

	/* Otros métodos y miembros que hagan falta */

	/** SOLO PARA TESTEAR **/
/*	public void imprimirTablero(){
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
	public void agregoPeon(){
		losCasilleros[2][0].addPieza(new Torre(Color.BLANCO));
	}
*/
}
