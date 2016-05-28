package logica;

public abstract class Pieza {
		
	private Color elColor;
	private boolean seMovio;
	/* Para poder enrocar, hace falta que no se hayan movido ni el rey ni la torre que enroca.
	 * Para que el peón pueda avanzar dos casilleros, es necesario que no se haya movido.
	 */

	public Pieza(Color unColor) {
		elColor = unColor;
		seMovio = false;
	}
	
	public Color dameColor() {
		return elColor;
	}

	public void ponerSeMovio() {
		seMovio = true;
	}
	
	public boolean dameSeMovio() {
		return seMovio;
	}

	public abstract List<Movimientos> dameMovimientos();
	
}
