package logica;

import java.io.Serializable;

/**
 * Modelo de una pieza al cual tiene acceso al vista (en el sentido MVC).
 * Este encapsula el nombre de la pieza y el color de la misma.
 */
public class PiezaColor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private NombrePieza elNombre;
	private ColorPieza elColor;
	
	/**
	 * Creo una PiezaColor con su nombre y su color.
	 * @param nombre Par&aacute;metro donde recibe el @see NombrePieza de la pieza.
	 * @param color Par&aacute;metro donde recibe el @see ColorPieza de la pieza.
	 */
	public PiezaColor(NombrePieza nombre, ColorPieza color){
		elNombre = nombre;
		elColor = color;
	}
	
	/**
	 * Indica el nombre de la pieza.
	 * @return Devuelve el @see NombrePieza de la pieza.
	 */
	public NombrePieza dameNombre(){
		return elNombre;
	}
	
	/**
	 * Indica el color de la pieza. 
	 * @return Devuelve el @see ColorPieza de la pieza.
	 */
	public ColorPieza dameColor(){
		return elColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elColor == null) ? 0 : elColor.hashCode());
		result = prime * result + ((elNombre == null) ? 0 : elNombre.hashCode());
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
		PiezaColor other = (PiezaColor) obj;
		if (elColor != other.elColor)
			return false;
		if (elNombre != other.elNombre)
			return false;
		return true;
	}
	
}
