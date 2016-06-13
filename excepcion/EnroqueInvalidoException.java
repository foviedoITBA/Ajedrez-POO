package excepcion;

public class EnroqueInvalidoException extends RuntimeException {
	public EnroqueInvalidoException() {
		super();
	}
	public EnroqueInvalidoException(String msg) {
		super(msg);
	}
}