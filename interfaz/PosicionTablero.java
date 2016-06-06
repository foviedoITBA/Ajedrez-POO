package interfaz;


public class PosicionTablero {

	private int fila, columna;
	
	public PosicionTablero(int posX, int posY){
		this.fila=posX;
		this.columna=posY;
	}
	
	public int getFila(){
		return fila;
	}
	
	public int getColumna(){
		return columna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fila;
		result = prime * result + columna;
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
		PosicionTablero other = (PosicionTablero) obj;
		if (fila != other.fila)
			return false;
		if (columna != other.columna)
			return false;
		return true;
	}
	
}
