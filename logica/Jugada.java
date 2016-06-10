package logica;

public class Jugada {
	
	private PosicionAjedrez posOrigen, posDestino;
	private Pieza laPiezaMovida, laPiezaComida;
	
	Jugada(PosicionAjedrez posOrigen, PosicionAjedrez posDestino, Pieza laPiezaMovida, Pieza laPiezaComida) {
		this.posOrigen = posOrigen;
		this.posDestino = posDestino;
		this.laPiezaMovida = laPiezaMovida;
		this.laPiezaComida = laPiezaComida;
	}
	
	public PiezaColor damePiezaColorMovida() {
		return new PiezaColor(laPiezaMovida.dameNombre(),laPiezaMovida.dameColor());
	}

	public PiezaColor damePiezaColorComida() {
		return new PiezaColor(laPiezaComida.dameNombre(),laPiezaComida.dameColor());
	}

	public PosicionAjedrez damePosicionOrigen() {
		return posOrigen;
	}

	public PosicionAjedrez damePosicionDestino() {
		return posDestino;
	}
	
	Pieza damePiezaMovida() {
		return laPiezaMovida;
	}

	Pieza damePiezaComida() {
		return laPiezaComida;
	}

	public String toString() {
		return "DE " + posOrigen.toString() + " A " + posDestino.toString();
	}
}