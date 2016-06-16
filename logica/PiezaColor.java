package logica;

import java.io.Serializable;

public class PiezaColor implements Serializable{
	private NombrePieza elNombre;
	private ColorPieza elColor;
	
	public PiezaColor(NombrePieza nombre, ColorPieza color){
		elNombre = nombre;
		elColor = color;
	}
	
	public NombrePieza dameNombre(){
		return elNombre;
	}
	
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
