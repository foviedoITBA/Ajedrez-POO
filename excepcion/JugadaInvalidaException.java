package excepcion;

public class JugadaInvalidaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JugadaInvalidaException() {
		super();
	}
	public JugadaInvalidaException(String msg) {
		super(msg);
	}
}