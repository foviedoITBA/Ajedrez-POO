package logica;

public class PosicionAjedrez {
	private byte fila;
	private char columna;
	
	public PosicionAjedrez(byte fila, char columna) throws Exception{
		if(!esValido(fila,columna)){
			throw new Exception();
		}
		this.columna = columna;
		this.fila = fila;
	}
	
	private boolean esValido(byte fila, char columna){
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
