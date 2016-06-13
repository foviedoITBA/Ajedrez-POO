package logica;


public class Alfil extends Pieza {
		
	public Alfil(ColorPieza color){
		super(color);
		nombre = NombrePieza.ALFIL;
		Movimiento arribaDerecha = new Movimiento(1, 1, true, true, false, -1);
		Movimiento abajoDerecha = new Movimiento(1, -1, true, true, false, -1);
		Movimiento arribaIzquierda = new Movimiento(-1, 1, true, true, false, -1);
		Movimiento abajoIzquierda = new Movimiento(-1, -1, true, true, false, -1);
		movimientos.add(arribaDerecha);
		movimientos.add(abajoDerecha);
		movimientos.add(arribaIzquierda);
		movimientos.add(abajoIzquierda);
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