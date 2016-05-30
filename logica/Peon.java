package logica;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {

	/* Los movimientos de los peones no son miembros de clase
	 * porque el peón se mueve distinto según si ya se movió o no
	 * y según el color, eontonces es una característica de cada peón.
	 */
	private List<Movimiento> movimientos;
	private Movimiento dosAdelante;

	public Peon(Color color) {
		super(color);
		movimientos = new ArrayList<>(4);
		int avance;
		if(color == Color.BLANCO ) {
			avance = -1;
		} else {//si es negro
			avance = 1;
		}
		Movimiento adelante = new Movimiento(0, avance, false, true, false, true);
		Movimiento comerDerecha = new Movimiento(1, avance, true, false, false, true);
		Movimiento comerIzquierda = new Movimiento(-1, avance, true, false, false, true);
		dosAdelante = new Movimiento(0, 2*avance, false, true, false, true);
		movimientos.add(adelante);
		movimientos.add(comerDerecha);
		movimientos.add(comerIzquierda);
		movimientos.add(dosAdelante);
	}

	@Override
	public void ponerSeMovio() {
		super.ponerSeMovio();
		movimientos.remove(dosAdelante);
	}

	@Override
	@Deprecated
	public List<Movimiento> dameMovimientos() {
		return movimientos;
	}

}