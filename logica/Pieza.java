package logica;

import java.util.List;

public abstract class Pieza {
		
	private ColorPieza elColor;
	private int cantMov;
	/* Para poder enrocar, hace falta que no se hayan movido ni el rey ni la torre que enroca.
	 * Para que el peón pueda avanzar dos casilleros, es necesario que no se haya movido.
	 */

	public Pieza(ColorPieza unColor) {
		elColor = unColor;
		cantMov = 0;
	}
	
	public ColorPieza dameColor() {
		return elColor;
	}
	
	public void ponerSeMovio() {
		cantMov++;
	}
	
	public boolean dameSeMovio() {
		return cantMov != 0;
	}

	public void sacarSeMovio() {
		cantMov--;
	}

	public abstract boolean puedoCoronar(int fila);
	public abstract boolean puedoEnrocar();
	public abstract NombrePieza dameNombre();
	public abstract List<Movimiento> dameMovimientos();
	
}
