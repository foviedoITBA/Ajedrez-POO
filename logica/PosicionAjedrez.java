package logica;

import excepcion.PosAjedrezInvalidaException;

public class PosicionAjedrez {
	private byte fila;
	private char columna;
	
	public PosicionAjedrez(byte fila, char columna) throws PosAjedrezInvalidaException {
		if(!esValido(fila,columna)){
			throw new PosAjedrezInvalidaException();
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columna;
		result = prime * result + fila;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicionAjedrez other = (PosicionAjedrez) obj;
		if (columna != other.columna)
			return false;
		if (fila != other.fila)
			return false;
		return true;
	}

	
	/** PARA TESTING **/
	public String toString(){
		return "fila: "+fila+" columna: "+columna;
	}
}
