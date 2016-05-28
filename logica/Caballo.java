package logica;

public class Caballo extends Pieza{
	
	private static List<Movimiento> movimientos;
	
	static {
		movimientos = new ArrayList(8);
		Movimiento dosArribaUnoDerecha = new Movimiento(1, 2, true, true, true, true);
		Movimiento unoArribaDosDerecha = new Movimiento(2, 1, true, true, true, true);
		Movimiento unoAbajoDosDerecha = new Movimiento(2, -1, true, true, true, true);
		Movimiento dosAbajoUnoDerecha = new Movimiento(1, -2, true, true, true, true);
		Movimiento dosAbajoUnoIzquierda = new Movimiento(-1, -2, true, true, true, true);
		Movimiento unoAbajoDosIzquierda = new Movimiento(-2, -1, true, true, true, true);
		Movimiento unoArribaDosIzquierda = new Movimiento(-2, 1, true, true, true, true);
		Movimiento dosArribaUnoIzquierda = new Movimiento(-1, 2, true, true, true, true);
		movimientos.add(dosArribaUnoDerecha);
		movimientos.add(unoArribaDosDerecha);
		movimientos.add(unoAbajoDosDerecha);
		movimientos.add(dosAbajoUnoDerecha);
		movimientos.add(dosAbajoUnoIzquierda);
		movimientos.add(unoAbajoDosIzquierda);
		movimientos.add(unoArribaDosIzquierda);
		movimientos.add(dosArribaUnoIzquierda);
	}

	public Caballo(Color color){
		super(color);
	}
	
	@Override
	public List<Movimiento> dameMovimientos() {
		return elMovimiento;
	}
}
