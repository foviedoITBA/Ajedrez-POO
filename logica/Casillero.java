package logica;

import java.io.Serializable;

/**
 * Modelo de un casillero (escaque) del tablero de ajedrez.
 * Cada casillero contiene la inforamcion de si tiene una pieza en el.
 * Los casilleros vacios se inicializan en <tt>null</tt>
 * @author martin
 *
 */
public class Casillero implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Pieza pieza;

	/**
	 * Creo un casillero asign&aacute;ndole en el una Pieza.
	 * @param pieza Recibe la pieza que contiene el casillero.
	 */
	public Casillero(Pieza pieza){
		this.pieza=pieza;
	}
	
	/**
	 * Creo un casillero sin ninguna pieza contenida.
	 */
	public Casillero(){
		this(null);
	}
	
	/**
	 * Indica si el casillero contiene una pieza.
	 * @return Devuelve <tt>true</tt> si el casillero esta vacio, sino devuelve <tt>false</tt>.
	 */
	public boolean isEmpty(){
		return pieza == null;	
	}
	
	/**
	 * Agregar una pieza al casillero.
	 * @param p La pieza a agregar al casillero.
	 */
	public void addPieza(Pieza p){
		pieza=p;
	}
	
	/**
	 * Quita la pieza contenida en el casillero.
	 */
	public void removePieza(){
		pieza=null;
	}
	
	/**
	 * Indica que pieza tiene contenida en el casillero.
	 * @return Devuelve la @see Pieza que contiene.
	 */
	public Pieza getPieza(){
		return pieza;
	}
	
}
