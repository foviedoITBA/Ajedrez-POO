package excepcion;

public class CoronacionPendienteException extends CoronacionException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoronacionPendienteException(){
		super("Hay una coronacion pendiente");
	}
	public CoronacionPendienteException(String msg){
		super(msg);
	}
}
