package excepcion;

public class JugadaInvalidaException extends RuntimeException {
	public JugadaInvalidaException() {
		super();
	}
	public JugadaInvalidaException(String msg) {
		super(msg);
	}
}