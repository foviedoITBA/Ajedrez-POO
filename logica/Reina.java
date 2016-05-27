package logica;

public class Reina extends Pieza{

	private Movimiento elMovimiento;
	
	public Reina(boolean color){
		super(color);
	}
	
	@Override
	public Movimiento damePosiciones() {
		return elMovimiento;
	}
}
