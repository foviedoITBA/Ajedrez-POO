package excepcion;

public class CoronacionInvalidaException extends CoronacionException {
	public CoronacionInvalidaException(){
		super();
	}
	public CoronacionInvalidaException(String msg){
		super(msg);
	}
}
