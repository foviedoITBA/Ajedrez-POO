package logica;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import excepcion.CasilleroVacioException;
import excepcion.CoronacionInvalidaException;
import excepcion.EnroqueInvalidoException;


/**	Esta clase es el componente fundamental del modelo (en el sentido MVC) del programa de ajedrez.
Es la representación del tablero físico y es la única que sabe dónde están ubicadas las piezas.
Por esto, es la única clase que puede interactuar directamente con ellas, y debe consecuentemente
administrar toda la información que éstas puedan proveerle para validar jugadas, hacerlas, revertirlas, coronar,
saber si hay jaque, etc. El controlador (la clase juego) es el único que invoca directamente métodos de esta clase.
*/
class Tablero implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static int SIZE_TABLERO=8;

	private Casillero[][] losCasilleros;	// Una matriz de casilleros, uno por cada escaque

	/**	Crea un nuevo tablero con todas las piezas en las posiciones adecuadas
	*/
	Tablero(){
		losCasilleros = new Casillero[SIZE_TABLERO][SIZE_TABLERO];
		initTablero();
	}

	/**	Permite saber si una posición está ocupada
	@param posAjedrez La posición a inspeccionar
	@return true si está ocupada
	*/
	boolean hayAlgo(PosicionAjedrez posAjedrez) {
		PosicionTablero posTablero = transformarPosicion(posAjedrez);
		return !losCasilleros[posTablero.getX()][posTablero.getY()].isEmpty();
	}
	
	/**	Permite saber qué pieza y de qué color se encuentra ocupando una determinada posición
	@param posAjedrez La posición a inspeccionar
	@return Un objeto PiezaColor con el nombre de la pieza y el color
	@throws CasilleroVacioException Se lanza si el casillero está vacío; debe censarse previamente para evitarlo
	*/
	PiezaColor queHay(PosicionAjedrez posAjedrez) throws CasilleroVacioException {
		PosicionTablero posTablero = transformarPosicion(posAjedrez);
		Pieza laPieza = losCasilleros[posTablero.getX()][posTablero.getY()].getPieza();
		if (laPieza == null){
			throw new CasilleroVacioException();
		}
		NombrePieza elNombre = laPieza.dameNombre();
		ColorPieza elColor = laPieza.dameColor();
		return new PiezaColor(elNombre, elColor);
	}

	/**	Permite saber a qué posiciones puede ir la pieza que se encuentre en la posición solicitada
	@param posAjedrez La posición a inspeccionar
	@return Un Set de objetos PosicionAjedrez con todas las posiciones a las que puede ir la pieza
	@throws CasilleroVacioException Se lanza si el casillero está vacío; debe censarse previamente para evitarlo
	*/
	Set<PosicionAjedrez> damePosicionesPosibles(PosicionAjedrez posAjedrez) throws CasilleroVacioException {
		PosicionTablero posTablero = transformarPosicion(posAjedrez);
		Pieza laPieza = losCasilleros[posTablero.getX()][posTablero.getY()].getPieza();
		if (laPieza == null){
			throw new CasilleroVacioException();
		}
		Set<PosicionTablero> lasPosicionesTablero = posicionesPosibles(posTablero);
		Set<PosicionAjedrez> lasPosicionesAjedrez = new HashSet<>();
		for (PosicionTablero unaPosTablero: lasPosicionesTablero){
			lasPosicionesAjedrez.add(transformarPosicion(unaPosTablero));
		}
		return lasPosicionesAjedrez;
	}

	/**	Permite saber si es posible que la pieza en una determinada posición vaya a otra
	@param posOrigen La posición en la que se encuentra la pieza que se desearía mover
	@param posDestino La posición a la que se quiere saber si puede ir
	@return true si el movimiento es posible
	@throws CasilleroVacioException Se lanza si el casillero de posOrigen está vacío; debe censarse previamente para evitarlo
	*/
	boolean esMovimientoPosible(PosicionAjedrez posOrigen, PosicionAjedrez posDestino) {
		PosicionTablero posOrigenT = transformarPosicion(posOrigen);
		PosicionTablero posDestinoT = transformarPosicion(posDestino);
		Pieza piezaMoviendo = losCasilleros[posOrigenT.getX()][posOrigenT.getY()].getPieza();
		if (piezaMoviendo == null){
			throw new CasilleroVacioException();
		}
		Set<PosicionTablero> posicionesPosibles = null;
		posicionesPosibles = posicionesPosibles(posOrigenT);
		if (posicionesPosibles.contains(posDestinoT)){
			return true;
		}
		return false;
	}
	
	/**	Realiza el movimiento de la pieza en una determinada posición a otra
	@param posOrigen La posición de la pieza que se desea mover
	@param posDestino La posición a la que se desea que vaya la pieza
	@return Un objeto Jugada con la jugada realizada
	*/
	Jugada moverPieza(PosicionAjedrez posOrigen, PosicionAjedrez posDestino) {
		PosicionTablero posOrigenT = transformarPosicion(posOrigen);
		PosicionTablero posDestinoT = transformarPosicion(posDestino);
		Casillero casOrigen = losCasilleros[posOrigenT.getX()][posOrigenT.getY()];
		Casillero casDestino = losCasilleros[posDestinoT.getX()][posDestinoT.getY()];
		Pieza piezaMoviendo = casOrigen.getPieza();
		Pieza piezaComida = casDestino.getPieza();
		casDestino.addPieza(piezaMoviendo);
		piezaMoviendo.ponerSeMovio();
		casOrigen.removePieza();
		return new Jugada(posOrigen, posDestino, piezaMoviendo, piezaComida);
	}

	/**	Permite revertir una determinada jugada
	@param laJugada La jugada a revertir
	*/
	void revertir(Jugada laJugada) {
		PosicionTablero posOrigenT = transformarPosicion(laJugada.damePosicionOrigen());
		PosicionTablero posDestinoT = transformarPosicion(laJugada.damePosicionDestino());
		Casillero casOrigen = losCasilleros[posOrigenT.getX()][posOrigenT.getY()];
		Casillero casDestino = losCasilleros[posDestinoT.getX()][posDestinoT.getY()];
		casOrigen.addPieza(laJugada.damePiezaMovida());
		casDestino.addPieza(laJugada.damePiezaComida());
		laJugada.damePiezaMovida().sacarSeMovio();
		if (laJugada.damePiezaMovidaExtra() != null) {
			PosicionTablero posOrigenExtraT = transformarPosicion(laJugada.damePosicionOrigenExtra());
			PosicionTablero posDestinoExtraT = transformarPosicion(laJugada.damePosicionDestinoExtra());
			Casillero casOrigenExtra = losCasilleros[posOrigenExtraT.getX()][posOrigenExtraT.getY()];
			Casillero casDestinoExtra = losCasilleros[posDestinoExtraT.getX()][posDestinoExtraT.getY()];
			casOrigenExtra.addPieza(laJugada.damePiezaMovidaExtra());
			laJugada.damePiezaMovidaExtra().sacarSeMovio();
			casDestinoExtra.removePieza();
		}
	}
	
	/**	Permite saber si un jugador está en jaque mate
	@param perdedor El color de las piezas del jugador que se desea saber si está en jaque mate
	@return true si está en jaque mate
	*/
	boolean hayJaqueMate(ColorPieza perdedor) {
		return (hayJaque(perdedor) && !hayMovimientosPosibles(perdedor));
	}

	/**	Permite saber si un jugador está ahogado
	@param ahogado El color de las piezas del jugador que se desea saber si está ahogado
	@return true si está ahogado
	*/
	boolean hayAhogado(ColorPieza ahogado) {
		return (!hayJaque(ahogado) && !hayMovimientosPosibles(ahogado));
	}

	/** Permite saber si un determinado jugador está en jaque
	@param jaqueado El color de las piezas del jugador que se desea saber si está en jaque
	@return true si está en jaque
	*/
	boolean hayJaque(ColorPieza jaqueado) {
		PosicionTablero posRey = buscoAlRey(jaqueado);
		boolean estaEnJaque = false;
		for (int i = 0; i < SIZE_TABLERO && !estaEnJaque; i++) {
			for (int j = 0; j < SIZE_TABLERO && !estaEnJaque; j++) {
				PosicionAjedrez laPosicion = transformarPosicion(new PosicionTablero(i, j));
				if (hayAlgo(laPosicion) && queHay(laPosicion).dameColor() != jaqueado) {
					if (analizoMovimientos(transformarPosicion(laPosicion), true).contains(posRey)) {
						estaEnJaque = true;
					}
				}
			}
		}
		return estaEnJaque;
	}

	/**	Permite saber si hay alguna coronación pendiente por hacer (es decir, si se debe elegir una pieza)
	@return true si hay coronación pendiente
	*/
	boolean hayAlgoParaCoronar() {
		boolean hayAlgo = false;
		for (int i = 0; i < SIZE_TABLERO && !hayAlgo; i++) {
			for (int j = 0; j < SIZE_TABLERO && !hayAlgo; j++) {
				if (!losCasilleros[i][j].isEmpty()){
					hayAlgo = losCasilleros[i][j].getPieza().puedoCoronar(8 - i);
				}
			}
		}
		return hayAlgo;
	}

	/**	Permite elegir el color y la pieza a coronar
	@param laPieza El nombre de la pieza
	@param elColor El color de la pieza
	@throws CoronacionInvalidaException Se lanza si la coronación solicitada no es válida, ya sea por no haber coronación pendiente para ese jugador o por elegir una pieza inválida como un rey
	*/
	void coronar(NombrePieza laPieza, ColorPieza elColor) throws CoronacionInvalidaException {
		boolean encontrado = false;
		for (int i = 0; i < SIZE_TABLERO && !encontrado; i++) {
			for (int j = 0; j < SIZE_TABLERO && !encontrado; j++) {
				if (!losCasilleros[i][j].isEmpty() && losCasilleros[i][j].getPieza().puedoCoronar(8 - i)) {
					Pieza nuevaPieza = null;
					switch(laPieza)
					{
						case TORRE:
							nuevaPieza = new Torre(elColor);
							break;
						case CABALLO:
							nuevaPieza = new Caballo(elColor);
							break;
						case ALFIL:
							nuevaPieza = new Alfil(elColor);
							break;
						case DAMA:
							nuevaPieza = new Dama(elColor);
							break;
						default:
							throw new CoronacionInvalidaException();
					}
					losCasilleros[i][j].addPieza(nuevaPieza);
					encontrado = true;
				}
			}
		}
		if (!encontrado)
			throw new CoronacionInvalidaException();
	}

	/** Informa si el jugador cutas piezas son de un dado color enrocar corto
	@param elColor El color de las piezas que se desea saber si pueden enrocar corto
	@return true si se puede enrocar corto
	*/
	boolean puedeEnrocarCorto(ColorPieza elColor) {
		int fila = (elColor == ColorPieza.BLANCO ? 7 : 0);
		int colTorre = 7;
		PosicionTablero posRey = buscoAlRey(elColor);
		Rey elRey = (Rey) losCasilleros[posRey.getX()][posRey.getY()].getPieza();
		PosicionTablero posIntermedia = new PosicionTablero(posRey.getX(), posRey.getY()+1);
		PosicionTablero posFinal = new PosicionTablero(posRey.getX(), posRey.getY() + 2);
		if (!elRey.puedoEnrocar())
			return false;	// Si el rey dice que no puede enrocar, no se puede enrocar
		if (losCasilleros[fila][colTorre].isEmpty() || !losCasilleros[fila][colTorre].getPieza().puedoEnrocar())
			return false;	// Si la torre dice que no puede enrocar, no se puede enrocar
		if (!losCasilleros[posIntermedia.getX()][posIntermedia.getY()].isEmpty() || !losCasilleros[posFinal.getX()][posFinal.getY()].isEmpty())
			return false;	// Si las posiciones entre el rey y la torre están ocupadas, no se puede enrocar
		if (hayJaque(elColor))
			return false;	// El rey no puede enrocar para salir del jaque
		losCasilleros[posIntermedia.getX()][posIntermedia.getY()].addPieza(elRey);
		losCasilleros[posRey.getX()][posRey.getY()].removePieza();
		if (hayJaque(elColor))
		{
			losCasilleros[posIntermedia.getX()][posIntermedia.getY()].removePieza();
			losCasilleros[posRey.getX()][posRey.getY()].addPieza(elRey);
			return false;	// El rey no puede pasar por jaque
		}
		losCasilleros[posIntermedia.getX()][posIntermedia.getY()].removePieza();
		losCasilleros[posFinal.getX()][posFinal.getY()].addPieza(elRey);
		if (hayJaque(elColor))
		{
			losCasilleros[posFinal.getX()][posFinal.getY()].removePieza();
			losCasilleros[posRey.getX()][posRey.getY()].addPieza(elRey);
			return false;	// El rey no puede quedar en jaque al enrocar
		}
		losCasilleros[posFinal.getX()][posFinal.getY()].removePieza();
		losCasilleros[posRey.getX()][posRey.getY()].addPieza(elRey);
		return true;		// Si todo se cumple, se puede enrocar
	}

	/** Informa si el jugador cutas piezas son de un dado color enrocar largo
	@param elColor El color de las piezas que se desea saber si pueden enrocar largo
	@return true si se puede enrocar largo
	*/
	boolean puedeEnrocarLargo(ColorPieza elColor) {
		int fila = (elColor == ColorPieza.BLANCO ? 7 : 0);
		int colTorre = 0;
		PosicionTablero posDelRey = buscoAlRey(elColor);
		Rey elRey = (Rey) losCasilleros[posDelRey.getX()][posDelRey.getY()].getPieza();
		PosicionTablero posIntermediaRey = new PosicionTablero(posDelRey.getX(), posDelRey.getY()-1);
		PosicionTablero posFinal = new PosicionTablero(posDelRey.getX(), posDelRey.getY() - 2);
		PosicionTablero posIntermediaTorre = new PosicionTablero(fila, colTorre+1);
		if (!elRey.puedoEnrocar())
			return false;	// Si el rey dice que no puede enrocar, no se puede enrocar
		if (losCasilleros[fila][colTorre].isEmpty() || !losCasilleros[fila][colTorre].getPieza().puedoEnrocar())
			return false;	// Si la torre dice que no puede enrocar, no se puede enrocar
		if (!losCasilleros[posIntermediaRey.getX()][posIntermediaRey.getY()].isEmpty() || !losCasilleros[posFinal.getX()][posFinal.getY()].isEmpty() || !losCasilleros[posIntermediaTorre.getX()][posIntermediaTorre.getY()].isEmpty())
			return false;	// Si las posiciones entre el rey y la torre están ocupadas, no se puede enrocar
		if (hayJaque(elColor))
			return false;	// El rey no puede enrocar para salir del jaque
		losCasilleros[posIntermediaRey.getX()][posIntermediaRey.getY()].addPieza(elRey);
		losCasilleros[posDelRey.getX()][posDelRey.getY()].removePieza();
		if (hayJaque(elColor))
		{
			losCasilleros[posIntermediaRey.getX()][posIntermediaRey.getY()].removePieza();
			losCasilleros[posDelRey.getX()][posDelRey.getY()].addPieza(elRey);
			return false;	// El rey no puede pasar por jaque
		}
		losCasilleros[posIntermediaRey.getX()][posIntermediaRey.getY()].removePieza();
		losCasilleros[posFinal.getX()][posFinal.getY()].addPieza(elRey);
		if (hayJaque(elColor))
		{
			losCasilleros[posFinal.getX()][posFinal.getY()].removePieza();
			losCasilleros[posDelRey.getX()][posDelRey.getY()].addPieza(elRey);
			return false;	// El rey no puede quedar en jaque al enrocar
		}
		losCasilleros[posFinal.getX()][posFinal.getY()].removePieza();
		losCasilleros[posDelRey.getX()][posDelRey.getY()].addPieza(elRey);
		return true;	// Si todo se cumple, se puede enrocar
	}

	/** Las piezas del color dado enrocan corto
	@throws CoronacionPendienteException Se lanza si todavía está pendiente coronar
	@throws EnroqueInvalidoException Se lanza si no es posible enrocar corto; se debe censar previamente para prevenirlo
	*/
	Jugada enrocarCorto(ColorPieza elColor) throws EnroqueInvalidoException {
		if (!puedeEnrocarCorto(elColor))
			throw new EnroqueInvalidoException();
		return enrocar(elColor, false);
	}

	/** Las piezas del color dado enrocan corto
	@throws CoronacionPendienteException Se lanza si todavía está pendiente coronar
	@throws EnroqueInvalidoException Se lanza si no es posible enrocar corto; se debe censar previamente para prevenirlo
	*/
	Jugada enrocarLargo(ColorPieza elColor) throws EnroqueInvalidoException {
		if (!puedeEnrocarLargo(elColor))	
			throw new EnroqueInvalidoException();
		return enrocar(elColor, true);
	}

	private Jugada enrocar(ColorPieza elColor, boolean esLargo) {
		int fila = (elColor == ColorPieza.BLANCO ? 7 : 0);
		int colOrigenRey = 4;
		int colOrigenTorre = (esLargo ? 0 : 7);
		int colDestinoRey = (esLargo ? 2 : 6);
		int colDestinoTorre = (esLargo ? 3 : 5);
		Rey elRey = (Rey) losCasilleros[fila][colOrigenRey].getPieza();
		Torre laTorre = (Torre) losCasilleros[fila][colOrigenTorre].getPieza();
		losCasilleros[fila][colOrigenRey].removePieza();
		losCasilleros[fila][colOrigenTorre].removePieza();
		losCasilleros[fila][colDestinoRey].addPieza(elRey);
		losCasilleros[fila][colDestinoTorre].addPieza(laTorre);
		elRey.ponerSeMovio();
		laTorre.ponerSeMovio();
		PosicionAjedrez posOrigenRey = transformarPosicion(new PosicionTablero(fila, colOrigenRey));
		PosicionAjedrez posDestinoRey = transformarPosicion(new PosicionTablero(fila, colDestinoRey));
		PosicionAjedrez posOrigenTorre = transformarPosicion(new PosicionTablero(fila, colOrigenTorre));
		PosicionAjedrez posDestinoTorre = transformarPosicion(new PosicionTablero(fila, colDestinoTorre));
		return new Jugada(posOrigenRey, posDestinoRey, elRey, null, posOrigenTorre, posDestinoTorre, laTorre);
	}

	private boolean hayMovimientosPosibles(ColorPieza perdedor) {
		
		// Busco todas las piezas del jugador y me fijo si se pueden mover
		boolean hayJugadas = false;
		for (int i = 0; i < SIZE_TABLERO && !hayJugadas; i++) {
			for (int j = 0; j < SIZE_TABLERO && !hayJugadas; j++) {
				if (!losCasilleros[i][j].isEmpty() && losCasilleros[i][j].getPieza().dameColor() == perdedor) {
					if (!posicionesPosibles(new PosicionTablero(i,j)).isEmpty()){
						hayJugadas = true;
					}
				}
			}
		}

		return hayJugadas;
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
		return new PosicionAjedrez(laFila, laColumna);
	}
	
	private void initTablero() {

		losCasilleros[0][0]=new Casillero(new Torre(ColorPieza.NEGRO));
		losCasilleros[0][1]=new Casillero(new Caballo(ColorPieza.NEGRO));
		losCasilleros[0][2]=new Casillero(new Alfil(ColorPieza.NEGRO));
		losCasilleros[0][3]=new Casillero(new Dama(ColorPieza.NEGRO));
		losCasilleros[0][4]=new Casillero(new Rey(ColorPieza.NEGRO));
		losCasilleros[0][5]=new Casillero(new Alfil(ColorPieza.NEGRO));
		losCasilleros[0][6]=new Casillero(new Caballo(ColorPieza.NEGRO));
		losCasilleros[0][7]=new Casillero(new Torre(ColorPieza.NEGRO));

		losCasilleros[7][0]=new Casillero(new Torre(ColorPieza.BLANCO));
		losCasilleros[7][1]=new Casillero(new Caballo(ColorPieza.BLANCO));
		losCasilleros[7][2]=new Casillero(new Alfil(ColorPieza.BLANCO));
		losCasilleros[7][3]=new Casillero(new Dama(ColorPieza.BLANCO));
		losCasilleros[7][4]=new Casillero(new Rey(ColorPieza.BLANCO));
		losCasilleros[7][5]=new Casillero(new Alfil(ColorPieza.BLANCO));
		losCasilleros[7][6]=new Casillero(new Caballo(ColorPieza.BLANCO));
		losCasilleros[7][7]=new Casillero(new Torre(ColorPieza.BLANCO));

		for(int i=1;i<SIZE_TABLERO-1;i++){
			for(int j=0;j<SIZE_TABLERO;j++){
				if(i==1){
					losCasilleros[i][j]=new Casillero(new Peon(ColorPieza.NEGRO));
				}else if(i==SIZE_TABLERO-2){
					losCasilleros[i][j]=new Casillero(new Peon(ColorPieza.BLANCO));
				}else{
					losCasilleros[i][j]=new Casillero();
				}
			}
		}
		
	}

	/* Devuelve todos las posiciones posibles de la pieza en pos considerando que no debe quedar el rey en jaque */
	private Set<PosicionTablero> posicionesPosibles(PosicionTablero pos) throws CasilleroVacioException {
		// Obtengo todos los lugares a los que podría potencialmente ir la pieza
		Casillero casilleroFuente = losCasilleros[pos.getX()][pos.getY()];		
		Pieza piezaMoviendo = casilleroFuente.getPieza();
		if (piezaMoviendo == null)
			throw new CasilleroVacioException();
		Set<PosicionTablero> posicionesPosibles = analizoMovimientos(pos, false);
		// Ahora hay que ver cuáles de esas jugadas dejan al rey en jaque y sacarlas de posicionesPosibles
		// Para eso primero busco la posición del rey del jugador que tiene el turno
		PosicionTablero posRey = buscoAlRey(piezaMoviendo.dameColor());
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
	private Set<PosicionTablero> analizoMovimientos(PosicionTablero pos, boolean soloComiendo) throws CasilleroVacioException {

		Casillero casillero=losCasilleros[pos.getX()][pos.getY()];
		Pieza piezaMoviendo = casillero.getPieza();
		if (piezaMoviendo == null)
			throw new CasilleroVacioException();
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

	private PosicionTablero buscoAlRey(ColorPieza colorDelRey) {
		PosicionTablero posRey = null;
		boolean encontreAlRey = false;
		for (int i = 0; i < SIZE_TABLERO && !encontreAlRey; i++) {
			for (int j = 0; j < SIZE_TABLERO && !encontreAlRey; j++) {
				if (!losCasilleros[i][j].isEmpty() && losCasilleros[i][j].getPieza().dameColor() == colorDelRey && losCasilleros[i][j].getPieza() instanceof Rey) {
					posRey = new PosicionTablero(i,j);
					encontreAlRey = true;
				}
			}
		}
		return posRey;
	}

	/***************SÓLO PARA TESTEAR**********************/
	public void imprimirTablero(){
		System.out.print("\n\n*************************************************************\n\n");
		for(int i=0;i<SIZE_TABLERO;i++) {
			for(int j=0;j<SIZE_TABLERO;j++) {

				if(losCasilleros[i][j].isEmpty()) {
					System.out.print("0\t");
				} else {
					if (losCasilleros[i][j].getPieza() instanceof Torre)
						System.out.print("T");
					else if (losCasilleros[i][j].getPieza() instanceof Caballo)
						System.out.print("C");
					else if (losCasilleros[i][j].getPieza() instanceof Alfil)
						System.out.print("A");
					else if (losCasilleros[i][j].getPieza() instanceof Dama)
						System.out.print("D");
					else if (losCasilleros[i][j].getPieza() instanceof Rey)
						System.out.print("R");
					else
						System.out.print("P");
					if(losCasilleros[i][j].getPieza().dameColor().equals(ColorPieza.BLANCO)){
						System.out.print("(B)\t");
					} else {
						System.out.print("(N)\t");
					}
				}
			}
			System.out.print("\n");
		}
	}
}
