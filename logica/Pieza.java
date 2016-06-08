package logica;

import java.util.List;

public abstract class Pieza {
		
	private Color elColor;
	private int cantMov;
	/* Para poder enrocar, hace falta que no se hayan movido ni el rey ni la torre que enroca.
	 * Para que el peón pueda avanzar dos casilleros, es necesario que no se haya movido.
	 */

	public Pieza(Color unColor) {
		elColor = unColor;
		cantMov = 0;
	}
	
	public Color dameColor() {
		return elColor;
	}
	
	public void ponerSeMovio() {
		cantMov++;
	}
	
	public boolean dameSeMovio() {
		return cantMov == 0;
	}

	public void sacarSeMovio() {
		cantMov--;
	}

	public abstract NombrePieza dameNombre();
	public abstract List<Movimiento> dameMovimientos();
	
}
