package logica;

import java.util.ArrayList;
import java.util.List;

public abstract class Pieza{

	private ColorPieza elColor;
	private int cantMov;
	protected NombrePieza nombre;
	protected List<Movimiento> movimientos = new ArrayList<Movimiento>();
	/* Para poder enrocar, hace falta que no se hayan movido ni el rey ni la torre que enroca.
	 * Para que el peón pueda avanzar dos casilleros, es necesario que no se haya movido.
	 */

	public Pieza(ColorPieza unColor) {
		elColor = unColor;
		cantMov = 0;
	}

	public ColorPieza dameColor() {
		return elColor;
	}

	public void ponerSeMovio() {
		cantMov++;
	}

	public boolean dameSeMovio() {
		return cantMov != 0;
	}

	public void sacarSeMovio() {
		if(cantMov > 0)
			cantMov--;
	}

	public List<Movimiento> dameMovimientos(){
		try{
			List<Movimiento> copia = new ArrayList<Movimiento>();
			for(Movimiento mov: movimientos){
				copia.add((Movimiento) mov.clone());
			}
			return copia;
		}catch(Exception e){
			return null;
		}
	}
	
	public NombrePieza dameNombre(){
		return nombre;
	}

	public abstract boolean puedoCoronar(int fila);
	public abstract boolean puedoEnrocar();
	


}
