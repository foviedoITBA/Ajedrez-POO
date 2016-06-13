package logica;


public class Caballo extends Pieza{

	public Caballo(ColorPieza color){
		super(color);
		nombre = NombrePieza.CABALLO;
		Movimiento dosArribaUnoDerecha = new Movimiento(1, 2, true, true, true, 1);
		Movimiento unoArribaDosDerecha = new Movimiento(2, 1, true, true, true, 1);
		Movimiento unoAbajoDosDerecha = new Movimiento(2, -1, true, true, true, 1);
		Movimiento dosAbajoUnoDerecha = new Movimiento(1, -2, true, true, true, 1);
		Movimiento dosAbajoUnoIzquierda = new Movimiento(-1, -2, true, true, true, 1);
		Movimiento unoAbajoDosIzquierda = new Movimiento(-2, -1, true, true, true, 1);
		Movimiento unoArribaDosIzquierda = new Movimiento(-2, 1, true, true, true, 1);
		Movimiento dosArribaUnoIzquierda = new Movimiento(-1, 2, true, true, true, 1);
		movimientos.add(dosArribaUnoDerecha);
		movimientos.add(unoArribaDosDerecha);
		movimientos.add(unoAbajoDosDerecha);
		movimientos.add(dosAbajoUnoDerecha);
		movimientos.add(dosAbajoUnoIzquierda);
		movimientos.add(unoAbajoDosIzquierda);
		movimientos.add(unoArribaDosIzquierda);
		movimientos.add(dosArribaUnoIzquierda);
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
