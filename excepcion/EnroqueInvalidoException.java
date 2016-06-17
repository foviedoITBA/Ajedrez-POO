package excepcion;

public class EnroqueInvalidoException extends JugadaInvalidaException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EnroqueInvalidoException() {
		super("Enroque invalido");
	}
	public EnroqueInvalidoException(String msg) {
		super(msg);
	}
}