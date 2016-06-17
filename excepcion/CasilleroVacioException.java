package excepcion;

public class CasilleroVacioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CasilleroVacioException(){
		super();
	}
	
	public CasilleroVacioException(String msg){
		super(msg);
	}
}
