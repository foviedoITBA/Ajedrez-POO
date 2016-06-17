package logica;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* Los movimientos de los peones no son miembros de clase
	 * porque el peón se mueve distinto según si ya se movió o no
	 * y según el color, entonces es una característica de cada peón.
	 */
	private List<Movimiento> movimientos;
	private Movimiento adelante;
	private int avance; // Cambia según si es blanco o negro
	
	public Peon(ColorPieza color) {
		super(color);
		movimientos = new ArrayList<>(3);
		if(color == ColorPieza.BLANCO ) {
			avance = -1;
		} else {//si es negro
			avance = 1;
		}
		adelante = new Movimiento(avance, 0, false, true, false, 2);
		Movimiento comerDerecha = new Movimiento(avance, 1, true, false, false, 1);
		Movimiento comerIzquierda = new Movimiento(avance, -1, true, false, false, 1);
		movimientos.add(adelante);
		movimientos.add(comerDerecha);
		movimientos.add(comerIzquierda);
	}

	@Override
	public void ponerSeMovio() {
		super.ponerSeMovio();
		movimientos.remove(adelante);
		adelante = new Movimiento(avance, 0, false, true, false, 1);
		movimientos.add( adelante);
	}
	
	@Override
	public void sacarSeMovio() {
		super.sacarSeMovio();
		if (!dameSeMovio()) {
			movimientos.remove(adelante);
			adelante =  new Movimiento(avance, 0, false, true, false, 2);
			movimientos.add(adelante);
		}
	}
	
	@Override
	public NombrePieza dameNombre() {
		return NombrePieza.PEON;
	}
	
	@Override
	public List<Movimiento> dameMovimientos() {
		List<Movimiento> movimientosCopia = new ArrayList<>();
		for (Movimiento unMovimiento: this.movimientos) {
			movimientosCopia.add(new Movimiento(unMovimiento));
		}
		return movimientosCopia;
	}

	@Override
	public boolean puedoCoronar(int fila) {
		return ((dameColor() == ColorPieza.BLANCO && fila == 8) || (dameColor() == ColorPieza.NEGRO && fila == 1));
	}

	@Override
	public boolean puedoEnrocar() {
		return false;
	}
}