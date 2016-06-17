package excepcion;

public class CoronacionInvalidaException extends CoronacionException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoronacionInvalidaException(){
		super("No se puede corona a esta pieza");
	}
	public CoronacionInvalidaException(String msg){
		super(msg);
	}
}
