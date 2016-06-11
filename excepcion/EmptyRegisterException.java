package excepcion;

public class EmptyRegisterException extends RuntimeException {
	public EmptyRegisterException() {
		super();
	}
	
	public EmptyRegisterException(String msg){
		super(msg);
	}
}
