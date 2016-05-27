package logica;

public abstract class Pieza {
	public final static boolean BLANCA=true;
	public final static boolean NEGRA=false;
	
	private boolean esBlanca;
	
	public Pieza(boolean color){
		esBlanca=color;
	}
	
	public boolean esBlanca(){
		return esBlanca;
	}
	
	public abstract Movimiento damePosiciones();
	
}
