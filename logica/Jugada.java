package logica;

public class Jugada {
	
	private PosicionAjedrez posOrigen, posDestino;
	private Pieza laPiezaMovida, laPiezaComida;
	
	public Jugada(PosicionAjedrez posOrigen, PosicionAjedrez posDestino, Pieza laPiezaMovida, Pieza laPiezaComida) {
		this.posOrigen = posOrigen;
		this.posDestino = posDestino;
		this.laPiezaMovida = laPiezaMovida;
		this.laPiezaComida = laPiezaComida;
	}
	
	public NombrePieza dameNobrePiezaMovida() {
		return laPiezaMovida.dameNombre();
	}

	public NombrePieza dameNombrePiezaComida() {
		return laPiezaComida.dameNombre();
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
}