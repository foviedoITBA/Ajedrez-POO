package logica;

public class PosicionAjedrez {
	private byte fila;
	private char columna;
	
	public PosicionAjedrez(char columna, byte fila) throws Exception{
		if(!esValido(columna,fila)){
			throw new Exception();
		}
		this.columna = columna;
		this.fila = fila;
	}
	
	private boolean esValido(char columna, byte fila){
		if(fila < 1 || fila > 8 || columna < 'a' || columna > 'h'){
			return false;
		}else{
			return true;
		}
	}
	
	public byte dameFila(){
		return fila;
	}
	public char dameColumna(){
		return columna;
	}
}
