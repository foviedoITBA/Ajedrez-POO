package excepcion;

public class PosAjedrezInvalidaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PosAjedrezInvalidaException(){
		super();
	}
	
	public PosAjedrezInvalidaException(String msg){
		super(msg);
	}
}
