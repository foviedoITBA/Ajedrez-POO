package logica;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {

	/* Los movimientos de los peones no son miembros de clase
	 * porque el peón se mueve distinto según si ya se movió o no
	 * y según el color, entonces es una característica de cada peón.
	 */
	private List<Movimiento> movimientos;
	private Movimiento adelante;
	private int avance; // Cabia según si es blanco o negro
	
	public Peon(Color color) {
		super(color);
		movimientos = new ArrayList<>(4);
		if(color == Color.BLANCO ) {
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
		movimientos.add( new Movimiento(avance, 0, false, true, false, 1));
	}
	
	@Override
	public void sacarSeMovio() {
		super.sacarSeMovio();
		if (!dameSeMovio()) {
			movimientos.remove(adelante);
			movimientos.add( new Movimiento(avance, 0, false, true, false, 2));
		}
	}
	
	@Override
	public NombrePieza dameNombre() {
		return NombrePieza.PEON;
	}
	
	@Override
	public List<Movimiento> dameMovimientos() {
		return movimientos;
	}

}