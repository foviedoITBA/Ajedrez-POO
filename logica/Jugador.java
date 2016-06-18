package logica;

import java.io.Serializable;

/**
 * Modelo de un jugador donde se indica el color de sus piezas.
 * 
 */
class Jugador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ColorPieza color;
	
	/**
	 * Crea un jugador con el color indicado.
	 * @param color El color de las piezas del jugador.
	 */
	public Jugador(ColorPieza color){
		this.color=color;
	}
	
	/**
	 * Devuelve el color de las piezas del jugador. 
	 * @return El @see ColorPieza del jugador.
	 */
	public ColorPieza dameColor() {
		return color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		Jugador other = (Jugador) obj;
		if (color != other.color)
			return false;
		return true;
	}	
}
