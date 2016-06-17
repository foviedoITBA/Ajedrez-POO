package excepcion;

public class ImposibleCargarJugadasException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ImposibleCargarJugadasException(){
		super("Imposible cargar jugadas");
	}
	public ImposibleCargarJugadasException(String msg){
		super(msg);
	}
}
