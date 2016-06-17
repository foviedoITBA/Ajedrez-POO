package excepcion;

public class EmptyRegisterException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyRegisterException() {
		super("El registro esta vacio");
	}
	
	public EmptyRegisterException(String msg){
		super(msg);
	}
}
