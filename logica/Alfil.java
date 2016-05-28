package logica;

public class Alfil extends Pieza {

	private Movimiento[] movimientos;
	
	
	
	public Alfil(boolean color){
		super(color);
	}

	@Override
	public Movimiento[] getMovimientos() {
		return movimientos;
	}
	
	
}
