package logica;

import java.io.Serializable;
import java.util.List;


/**
 * Esta clase brinda una estructura general de como ser&aacute;n moldeadas cada una de las piezas individuales.
 * Cada pieza especifica del juego debe heredar de esta.
 * Es importante destacar que las piezas no saben la ubicaci&oacute;n en el tablero (@see Tablero).
 */
public abstract class Pieza implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private ColorPieza elColor;
	private int cantMov;

	/**
	 * Construye una pieza general del color especificado. 
	 * @param unColor - es el color del que se&aacute; la pieza;
	 */
	public Pieza(ColorPieza unColor) {
		elColor = unColor;
		cantMov = 0;
	}
	
	/**
	 * Indica el color de la Pieza.
	 * @return - el color.
	 */
	public ColorPieza dameColor() {
		return elColor;
	}
	
	/**
	 * Permite indicarle a la pieza que realizo un movimiento.
	 */
	public void ponerSeMovio() {
		cantMov++;
	}
	
	/**
	 * Indica si la pieza se ha movido desde que comenz&oacute; el juego.
	 * @return Devuelve true si la pieza se movi&oacute;, false si nunca se ha movido.
	 */
	public boolean dameSeMovio() {
		return cantMov != 0;
	}
	
	/**
	 * Permite indicarle a la pieza que se deshizo la jugada.
	 */
	public void sacarSeMovio() {
		if(cantMov > 0)
			cantMov--;
	}

	/**
	 * Indica si la Pieza permite la coronaci&oacute;n en esa fila.
	 * @param fila - Fila del tablero en la que se encuentra la pieza.
	 * @return Devuelve true si la pieza puede coronar.
	 */
	public abstract boolean puedoCoronar(int fila);
	
	/**
	 * Indica si la Pieza permite enrocar.
	 * @return Devuelve true si la pieza puede enrocar.
	 */
	public abstract boolean puedoEnrocar();
	
	/**
	 * Indica que pieza es devolviendo su nombre.
	 * @return Devuelve el nombre de la pieza @see NombrePieza
	 */
	public abstract NombrePieza dameNombre();
	
	/**
	 * Indica los movimientos que puede realizar la pieza.
	 * @return Devuelve una lista de @see Movimientos. Indicando las direcciones en las que se puede mover.
	 */
	public abstract List<Movimiento> dameMovimientos();
	
}
