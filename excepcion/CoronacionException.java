package excepcion;

public abstract class CoronacionException extends RuntimeException {
	public CoronacionException(){
		super();
	}
	public CoronacionException(String msg){
		super(msg);
	}
}
