package logica;

import java.io.Serializable;

public class Jugador implements Serializable{

	private ColorPieza color;
	
	public Jugador(ColorPieza color){
		this.color=color;
	}
	
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
