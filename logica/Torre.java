package logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de una Pieza del tipo Torre.
 */
public class Torre extends Pieza{
	
	private static final long serialVersionUID = 1L;
	
	private static List<Movimiento> movimientos;
	
	static {
		movimientos = new ArrayList<>(4);
		Movimiento arriba = new Movimiento(0, 1, true, true, false, -1);
		Movimiento abajo = new Movimiento(0, -1, true, true, false, -1);
		Movimiento izquierda = new Movimiento(-1, 0, true, true, false, -1);
		Movimiento derecha = new Movimiento(1, 0, true, true, false, -1);
		movimientos.add(arriba);
		movimientos.add(abajo);
		movimientos.add(izquierda);
		movimientos.add(derecha);
	}

	/**
	 * Crea una pieza del tipo Torre.
	 * @param color Recibe el color de la pieza.
	 */
	public Torre(ColorPieza color){
		super(color);
	}

	@Override
	public List<Movimiento> dameMovimientos() {
		List<Movimiento> movimientosCopia = new ArrayList<>();
		for (Movimiento unMovimiento: movimientos) {
			movimientosCopia.add(new Movimiento(unMovimiento));
		}
		return movimientosCopia;
	}
	
	@Override
	public NombrePieza dameNombre() {
		return NombrePieza.TORRE;
	}

	@Override
	public boolean puedoCoronar(int fila) {
		return false;
	}

	@Override
	public boolean puedoEnrocar() {
		return !dameSeMovio();
	}
}
