package excepcion;

public class CasilleroVacioException extends RuntimeException {

	public CasilleroVacioException(){
		super();
	}
	
	public CasilleroVacioException(String msg){
		super(msg);
	}
}
