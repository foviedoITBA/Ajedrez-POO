package excepcion;

public abstract class CoronacionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoronacionException(){
		super();
	}
	public CoronacionException(String msg){
		super(msg);
	}
}
