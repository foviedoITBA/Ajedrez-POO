package excepcion;

public class PosAjedrezInvalidaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PosAjedrezInvalidaException(){
		super("Posicion invalida");
	}
	
	public PosAjedrezInvalidaException(String msg){
		super(msg);
	}
}
