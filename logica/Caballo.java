package logica;

public class Caballo extends Pieza{
	
	private Movimiento elMovimiento;
	
	public Caballo(boolean color){
		super(color);
	}
	
	@Override
	public Movimiento damePosiciones() {
		return elMovimiento;
	}
}
