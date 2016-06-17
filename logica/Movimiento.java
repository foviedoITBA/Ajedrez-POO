package logica;

import java.io.Serializable;

public class Movimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int movX, movY;
	/* comiendo: indica si se puede mover capturando en esa dirección
	 * sinComer: indica si se puede mover sin capturar en esa dirección
	 * (Los peones se mueven sin comer hacia adelante pero comiendo en diagonal)
	 * saltando: indica si necesita tener casilleros libres en el medio o no
	 * (El caballo puede saltar, mientras que el alfil no)
	 * unaVez: indica cuántas veces se puede mover en esa dirección
	 * (El Rey tiene el mismo movimiento que la dama, salvo que el rey lo hace una vez y la dama todas las que quiera
	*/
	private boolean comiendo, sinComer, saltando;
	private int cantidad;

	public Movimiento(int movX, int movY, boolean comiendo, boolean sinComer, boolean saltando, int cantidad){
		this.movX = movX;
		this.movY = movY;
		this.comiendo = comiendo;
		this.sinComer = sinComer;
		this.saltando = saltando;
		this.cantidad = cantidad;
	}

	public Movimiento(Movimiento otroMovimiento) {
		this(otroMovimiento.movX,otroMovimiento.movY,otroMovimiento.comiendo,otroMovimiento.sinComer,otroMovimiento.saltando,otroMovimiento.cantidad);
	}
	
	public int dameMovX(){
		return movX;
	}
	
	public int dameMovY(){
		return movY;
	}
	
	public boolean esComiendo() {
		return comiendo;
	}

	public boolean esSinComer() {
		return sinComer;
	}

	public boolean esSaltando() {
		return saltando;
	}

	public boolean esUnaVez() {
		return 1==cantidad;
	}

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