package excepcion;

public class ImposibleCargarJugadasException extends RuntimeException {
	public ImposibleCargarJugadasException(){
		super();
	}
	public ImposibleCargarJugadasException(String msg){
		super(msg);
	}
}
