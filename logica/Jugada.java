package logica;

import java.io.Serializable;

/**
 * Modelo de una jugada, en la que se especifica la
 * pieza que se movi&oacute; y como fue el movimiento.
 * En caso de haber un enroque, se guarda la informaci&oacute;n de este.
 */
public class Jugada implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PosicionAjedrez posOrigen, posDestino;
	private Pieza laPiezaMovida, laPiezaComida;
	private PosicionAjedrez posOrigenExtra, posDestinoExtra;
	private Pieza piezaExtra;

	/**
	 * Crea una nueva jugada.
	 * @param posOrigen - Posici&oacute;n de origen de la Pieza.
	 * @param posDestino - Posici&oacute;n de destino de la Pieza. 
	 * @param laPiezaMovida - Que pieza es la que se movi&oacute; en la jugada.
	 * @param laPiezaComida - En caso de haber comido una pieza, cual era.
	 */
	Jugada(PosicionAjedrez posOrigen, PosicionAjedrez posDestino, Pieza laPiezaMovida, Pieza laPiezaComida) {
		this(posOrigen,posDestino,laPiezaMovida,laPiezaComida,null,null,null);
	}

	/**
	 * Crea una nueva jugada con enroque
	 * @param posOrigen - Posici&oacute;n de origen de la Pieza.
	 * @param posDestino - Posici&oacute;n de destino de la Pieza. 
	 * @param laPiezaMovida - Que pieza es la que se movi&oacute; en la jugada.
	 * @param laPiezaComida - En caso de haber comido una pieza, cual era.
	 * @param posOrigenExtra - Posici&oacute;n de origen de la Pieza enrocada.
	 * @param posDestinoExtra  Posici&oacute;n de destino de la Pieza enrocada.
	 * @param piezaExtra - La pieza que se enroc&oacute;.
	 */
	Jugada(PosicionAjedrez posOrigen, PosicionAjedrez posDestino, Pieza laPiezaMovida, Pieza laPiezaComida, PosicionAjedrez posOrigenExtra, PosicionAjedrez posDestinoExtra, Pieza piezaExtra) {
		this.posOrigen = posOrigen;
		this.posDestino = posDestino;
		this.laPiezaMovida = laPiezaMovida;
		this.laPiezaComida = laPiezaComida;
		this.posOrigenExtra = posOrigenExtra;
		this.posDestinoExtra = posDestinoExtra;
		this.piezaExtra = piezaExtra;
	}
	
	/**
	 * Indica que pieza se ha movido en la jugada.
	 * @return Devuelve la @see PiezaColor que se ha movido.
	 */
	public PiezaColor damePiezaColorMovida() {
		return new PiezaColor(laPiezaMovida.dameNombre(),laPiezaMovida.dameColor());
	}

	/**
	 * Indica que pieza se ha comido en la jugada.
	 * @return Devuelve la @see PiezaColor que se ha comido, o <tt>null</tt> en caso de no
	 * 		   haberse comido una pieza en la jugada. 
	 */
	public PiezaColor damePiezaColorComida() {
		if (laPiezaComida == null)
			return null;
		return new PiezaColor(laPiezaComida.dameNombre(),laPiezaComida.dameColor());
	}
	
	/**
	 * Indica si se ha comido una pieza en la jugada.
	 * @return Devuelve <tt>true</tt> si se ha comido una pieza,
	 * 		   en caso contrario devuelve <tt>false</tt>.
	 */
	public boolean hayPiezaComida(){
		if(laPiezaComida== null){
			return false;
		}
			return true;
	}

	/**
	 * Indica la posici&oacute;n de origen de la pieza. 
	 * @return Devuelve la @see PosicionAjedrez donde estaba la pieza.
	 */
	public PosicionAjedrez damePosicionOrigen() {
		return posOrigen;
	}

	/**
	 * Indica la posici&oacute;n de destino de la pieza. 
	 * @return Devuelve la @see PosicionAjedrez a la que se movio.
	 */
	public PosicionAjedrez damePosicionDestino() {
		return posDestino;
	}

	/**
	 * Indica la posici&oacute;n de origen de la pieza extra en el enroque. 
	 * @return Devuelve la @see PosicionAjedrez donde estaba la pieza.
	 */
	public PosicionAjedrez damePosicionOrigenExtra() {
		return posOrigenExtra;
	}
	
	/**
	 * Indica la posici&oacute;n de destino de la pieza extra en el enroque. 
	 * @return Devuelve la @see PosicionAjedrez a la que se movio.
	 */
	public PosicionAjedrez damePosicionDestinoExtra() {
		return posDestinoExtra;
	}

	/**
	 * Indica la pieza que se ha movido en la jugada. M&eacute;todo solo disponible para el paquete logica.
	 * @return Devuelve la @see Pieza que se movi&oacute;
	 */
	Pieza damePiezaMovida() {
		return laPiezaMovida;
	}

	/**
	 * Indica la pieza extra que se ha movido en el enroque. M&eacute;todo solo disponible para el paquete logica.
	 * @return Devuelve la @see Pieza que se movi&oacute; en el enroque;
	 */
	Pieza damePiezaMovidaExtra() {
		return piezaExtra;
	}

	/**
	 * Indica la pieza que se ha comido en la jugada. M&eacute;todo solo disponible para el paquete logica.
	 * @return Devuelve la @see Pieza que se comi&oacute;
	 */
	Pieza damePiezaComida() {
		return laPiezaComida;
	}
}