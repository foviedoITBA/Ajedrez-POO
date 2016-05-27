package logica;

public class Rey extends Pieza {

	private Movimiento elMovimiento;
	
	public Rey(boolean color){
		super(color);
	}
	
	@Override
	public Movimiento damePosiciones() {
		return elMovimiento;
	}
}
