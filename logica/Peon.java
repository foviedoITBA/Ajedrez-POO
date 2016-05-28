package logica;

public class Peon extends Pieza{

	/* Los movimientos de los peones no son miembros de clase
	 * porque el peón se mueve distinto según si ya se movió o no
	 * y según el color, eontonces es una característica de cada peón.
	 */
	private List<Movimiento> movimientos;

	public Peon(Color color) {
		super(color);
		movimientos = new ArrayList(4);
		int avance;
		switch(color) {
			case Color.BLANCO:
				avance = 1;
			case Color.NEGRO:
				avance = -1;
		}
		Movimiento adelante = new Movimiento(0, avance, false, true, false, true);
		Movimiento comerDerecha = new Movimiento(1, avance, true, false, false, true);
		Movimiento comerIzquierda = new Movimiento(-1, avance, true, false, false, true);
		Movimiento dosAdelante = new Movimiento(0, 2*avance, false, true, false, true);
	}

	@Override

	public void ponerSeMovio() {
		super.ponerSeMovio();
		// Acá tiene que sacar la posibilidad de avanzar dos casilleros
	}

	@Override
	@Deprecated
	public List<Movimiento> dameMovimiento() {
		
		return movimientos;
	}

}