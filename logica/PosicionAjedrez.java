package logica;

import java.io.Serializable;

import excepcion.PosAjedrezInvalidaException;

/**
 * Modelo de una posici&oacute;n ajedrez, compuesta por una letra y un numero.
 * Ejemplo: "b3".
 * Mediante esta se hace el manejo de informaci&oacute;n con la vista (en el sentido MVC).
 */
public class PosicionAjedrez implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private byte fila;
	private char columna;
	
	/**
	 * Crea una PosicionAjedrez con la fila y la columna ingresadas.
	 * @param fila Recibe el numero de fila.
	 * @param columna Recibe la letra de la columna.
	 * @throws PosAjedrezInvalidaException Lanza una excepci&oacute;n en caso de haberle pasado una posici&oacute;n invalida por par&aacute;metros. 
	 */
	public PosicionAjedrez(byte fila, char columna) throws PosAjedrezInvalidaException {
		if(!esValido(fila,columna)){
			throw new PosAjedrezInvalidaException();
		}
		this.columna = columna;
		this.fila = fila;
	}
	
	/**
	 * Valida si la fila y la columna son validas como posici&oacute;n de ajedrez.
	 * @param fila Recibe el numero de fila.
	 * @param columna Recibe la letra de la columna.
	 * @return Devuelve <tt>true</tt> si es valida, sino devuelve <tt>false</tt>.
	 */
	private boolean esValido(byte fila, char columna){
		return (fila < 1 || fila > 8 || columna < 'a' || columna > 'h')?false:true;
	}
	
	/**
	 * Indica la fila.
	 * @return Devuelve el numero de fila.
	 */
	public byte dameFila(){
		return fila;
	}
	
	/**
	 * Indica la columna.
	 * @return Devuelve la letra de la columna.
	 */
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
}
