package logica;

import java.io.Serializable;

/**
 * Modelo de un movimiento que puede realizar cada pieza.
 * En este se almacena toda la informaci&oacute;n de como se desplazan las piezas por el tablero,
 * incluyendo direcci&oacute;n, y en que casos se puede mover hacia esa direcci&oacute;n.
 */
public class Movimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int movX, movY;

	private boolean comiendo, sinComer, saltando;
	private int cantidad;

	/**
	 * Crea un Movimiento en el que se especifica como y en que casos se puede mover una pieza.
	 * @param movX Indica el sentido en el que se puede mover en X.
	 * @param movY Indica el sentido en el que se puede mover en Y.
	 * @param comiendo Indica si se puede mover capturando en esa direcci&oacute;n
	 * @param sinComer Indica si se puede mover sin capturar en esa direcci&oacute;n
	 * @param saltando Indica si necesita tener casilleros libres en el medio o no.
	 * @param cantidad Indica cu&aacute;ntas veces se puede mover en esa direcci&oacute;n
	 */
	public Movimiento(int movX, int movY, boolean comiendo, boolean sinComer, boolean saltando, int cantidad){
		this.movX = movX;
		this.movY = movY;
		this.comiendo = comiendo;
		this.sinComer = sinComer;
		this.saltando = saltando;
		this.cantidad = cantidad;
	}

	/**
	 * Crea un Movimiento en base a un Movimiento.
	 * @param otroMovimiento Recibe un Movimiento.
	 */
	public Movimiento(Movimiento otroMovimiento) {
		this(otroMovimiento.movX,otroMovimiento.movY,otroMovimiento.comiendo,otroMovimiento.sinComer,otroMovimiento.saltando,otroMovimiento.cantidad);
	}
	
	/**
	 * Indica el Movimiento en el sentido X.
	 * @return Devuelve la direcci&oacute;n del movimiento.
	 */
	public int dameMovX(){
		return movX;
	}
	
	/**
	 * Indica el Movimiento en el sentido Y.
	 * @return Devuelve la direcci&oacute;n del movimiento.
	 */
	public int dameMovY(){
		return movY;
	}
	
	/**
	 * Indica si se mueve comiendo.
	 * @return Devuelve <tt>true</tt> si se mueve comiendo, sino devuelve <tt>false</tt>.
	 */
	public boolean esComiendo() {
		return comiendo;
	}

	/**
	 * Indica si se mueve sin comer.
	 * @return Devuelve <tt>true</tt> si se mueve sin comer, sino devuelve <tt>false</tt>.
	 */
	public boolean esSinComer() {
		return sinComer;
	}

	/**
	 * Indica si se mueve saltando.
	 * @return Devuelve <tt>true</tt> si se mueve saltando, sino devuelve <tt>false</tt>.
	 */
	public boolean esSaltando() {
		return saltando;
	}

	/**
	 * Indica si se mueve una sola vez en cada direcci&oacute;n.
	 * @return Devuelve <tt>true</tt> si se mueve una sola vez, sino devuelve <tt>false</tt>.
	 */
	public boolean esUnaVez() {
		return 1==cantidad;
	}

	/**
	 * Indica la cantidad de veces que se puede mover en una misma direcci&oacute;n.
	 * @return Devuelve la cantidad de veces que se puede mover.
	 */
	public int cantidadDeVeces(){
		return cantidad;
	}
	
	public boolean equals(Object otroObjeto) {
		if (this == otroObjeto)
			return true;
		if (otroObjeto == null || !this.getClass().equals(otroObjeto.getClass()))
			return false;
		if (this.movX == ((Movimiento)otroObjeto).movX && this.movY == ((Movimiento)otroObjeto).movY && this.comiendo == ((Movimiento)otroObjeto).comiendo && this.sinComer == ((Movimiento)otroObjeto).sinComer && this.saltando == ((Movimiento)otroObjeto).saltando && this.cantidad == ((Movimiento)otroObjeto).cantidad)
			return true;
		return false;
	}

}