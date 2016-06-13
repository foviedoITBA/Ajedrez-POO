package logica;

import java.util.ArrayList;
import java.util.List;

public class Dama extends Pieza {

	private static List<Movimiento> movimientos;
	
	static {
		movimientos = new ArrayList<>(8);
		Movimiento arribaDerecha = new Movimiento(1, 1, true, true, false, -1);
		Movimiento abajoDerecha = new Movimiento(1, -1, true, true, false, -1);
		Movimiento arribaIzquierda = new Movimiento(-1, 1, true, true, false, -1);
		Movimiento abajoIzquierda = new Movimiento(-1, -1, true, true, false, -1);
		Movimiento arriba = new Movimiento(0, 1, true, true, false, -1);
		Movimiento abajo = new Movimiento(0, -1, true, true, false, -1);
		Movimiento izquierda = new Movimiento(-1, 0, true, true, false, -1);
		Movimiento derecha = new Movimiento(1, 0, true, true, false, -1);
		movimientos.add(arribaDerecha);
		movimientos.add(abajoDerecha);
		movimientos.add(arribaIzquierda);
		movimientos.add(abajoIzquierda);
		movimientos.add(arriba);
		movimientos.add(abajo);
		movimientos.add(izquierda);
		movimientos.add(derecha);
	}

	public Dama(ColorPieza color){
		super(color);
	}
	
	@Override
	public List<Movimiento> dameMovimientos() {
		return movimientos;
	}
	
	@Override
	public NombrePieza dameNombre() {
		return NombrePieza.DAMA;
	}

	@Override
	public boolean puedoCoronar(int fila) {
		return false;
	}

	@Override
	public boolean puedoEnrocar() {
		return false;
	}
}
