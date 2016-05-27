package logica;

import java.util.HashSet;
import java.util.Set;

public class Alfil extends Pieza {

	private Movimiento elMovimiento;
	
	
	
	public Alfil(boolean color){
		super(color);
	}
	
	@Override
	public Movimiento damePosiciones() {
		return elMovimiento;
	}
	
	
}
