package logica;

public class Torre extends Pieza{
	
	private Movimiento[] movimientos = {new Movimiento(1,0), new Movimiento(0,1), new Movimiento(-1,0), new Movimiento(0,-1)};
	
	public Torre(boolean color){
		super(color);
	}

	@Override
	public Movimiento[] getMovimientos() {
		return movimientos;
	}
	
}
