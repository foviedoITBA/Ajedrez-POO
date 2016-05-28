package logica;

public class Dama extends Pieza {

	private static List<Movimiento> movimientos;
	
	static {
		movimientos = new ArrayList(8);
		Movimiento arribaDerecha = new Movimiento(1, 1, true, true, false, false);
		Movimiento abajoDerecha = new Movimiento(1, -1, true, true, false, false);
		Movimiento arribaIzquierda = new Movimiento(-1, 1, true, true, false, false);
		Movimiento abajoIzquierda = new Movimiento(-1, -1, true, true, false, false);
		Movimiento arriba = new Movimiento(0, 1, true, true, false, false);
		Movimiento abajo = new Movimiento(0, -1, true, true, false, false);
		Movimiento izquierda = new Movimiento(-1, 0, true, true, false, false);
		Movimiento derecha = new Movimiento(1, 0, true, true, false, false);
		movimientos.add(arribaDerecha);
		movimientos.add(abajoDerecha);
		movimientos.add(arribaIzquierda);
		movimientos.add(abajoIzquierda);
		movimientos.add(arriba);
		movimientos.add(abajo);
		movimientos.add(izquierda);
		movimientos.add(derecha);
	}

	public Dama(Color color){
		super(color);
	}
	
	@Override
	public List<Movimiento> dameMovimiento() {
		return movimientos;
	}
}
