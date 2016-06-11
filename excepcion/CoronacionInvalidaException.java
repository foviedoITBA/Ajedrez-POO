package excepcion;

public class CoronacionInvalidaException extends RuntimeException{
	public CoronacionInvalidaException(){
		super();
	}
	public CoronacionInvalidaException(String msg){
		super(msg);
	}
}
