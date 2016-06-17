package excepcion;

public class JugadaInvalidaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JugadaInvalidaException() {
		super("Jugada invalida");
	}
	public JugadaInvalidaException(String msg) {
		super(msg);
	}
}