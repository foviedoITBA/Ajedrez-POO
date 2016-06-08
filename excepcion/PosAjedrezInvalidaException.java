package excepcion;

public class PosAjedrezInvalidaException extends RuntimeException{
	
	public PosAjedrezInvalidaException(){
		super();
	}
	
	public PosAjedrezInvalidaException(String msg){
		super(msg);
	}
}
