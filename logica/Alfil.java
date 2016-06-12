package logica;

import java.util.ArrayList;
import java.util.List;

public class Alfil extends Pieza {

	private static List<Movimiento> movimientos;

	static {
		movimientos = new ArrayList<>(4);
		Movimiento arribaDerecha = new Movimiento(1, 1, true, true, false, -1);
		Movimiento abajoDerecha = new Movimiento(1, -1, true, true, false, -1);
		Movimiento arribaIzquierda = new Movimiento(-1, 1, true, true, false, -1);
		Movimiento abajoIzquierda = new Movimiento(-1, -1, true, true, false, -1);
		movimientos.add(arribaDerecha);
		movimientos.add(abajoDerecha);
		movimientos.add(arribaIzquierda);
		movimientos.add(abajoIzquierda);
	}
		
	public Alfil(ColorPieza color){
		super(color);
	}

	@Override
	public List<Movimiento> dameMovimientos() {
		return movimientos;
	}

	@Override
	public NombrePieza dameNombre() {
		return NombrePieza.ALFIL;
	}

	@Override
	public boolean puedoCoronar(int fila) {
		return false;
	}
}