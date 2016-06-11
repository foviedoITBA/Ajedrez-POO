package excepcion;

public class CoronacionPendienteException extends RuntimeException {
	public CoronacionPendienteException(){
		super();
	}
	public CoronacionPendienteException(String msg){
		super(msg);
	}
}
