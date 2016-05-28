package logica;

import java.util.ArrayList;
import java.util.List;

public class Alfil extends Pieza {

	private static List<Movimiento> movimientos;

	static {
		movimientos = new ArrayList<>(4);
		Movimiento arribaDerecha = new Movimiento(1, 1, true, true, false, false);
		Movimiento abajoDerecha = new Movimiento(1, -1, true, true, false, false);
		Movimiento arribaIzquierda = new Movimiento(-1, 1, true, true, false, false);
		Movimiento abajoIzquierda = new Movimiento(-1, -1, true, true, false, false);
		movimientos.add(arribaDerecha);
		movimientos.add(abajoDerecha);
		movimientos.add(arribaIzquierda);
		movimientos.add(abajoIzquierda);
	}
		
	public Alfil(Color color){
		super(color);
	}

	@Override
	public List<Movimiento> dameMovimientos() {
		return movimientos;
	}
	
}