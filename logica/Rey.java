package logica;

public class Rey extends Pieza {

	private static List<Movimiento> movimientos;

	static {
		movimientos = new ArrayList(8);
		Movimiento arribaDerecha = new Movimiento(1, 1, true, true, false, true);
		Movimiento abajoDerecha = new Movimiento(1, -1, true, true, false, true);
		Movimiento arribaIzquierda = new Movimiento(-1, 1, true, true, false, true);
		Movimiento abajoIzquierda = new Movimiento(-1, -1, true, true, false, true);
		Movimiento arriba = new Movimiento(0, 1, true, true, false, true);
		Movimiento abajo = new Movimiento(0, -1, true, true, false, true);
		Movimiento izquierda = new Movimiento(-1, 0, true, true, false, true);
		Movimiento derecha = new Movimiento(1, 0, true, true, false, true);
		movimientos.add(arribaDerecha);
		movimientos.add(abajoDerecha);
		movimientos.add(arribaIzquierda);
		movimientos.add(abajoIzquierda);
		movimientos.add(arriba);
		movimientos.add(abajo);
		movimientos.add(izquierda);
		movimientos.add(derecha);
		// Faltaría implementar la posibilidad de enroque
	}
	
	public Rey(Color color){
		super(color);
	}
	
	@Override
	public List<Movimiento> dameMovimientos() {
		return movimientos;
	}
}
