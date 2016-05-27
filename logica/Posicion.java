package logica;

public class Posicion {
	
	private int posX, posY;
	
	public Posicion(int posX, int posY){
		this.posX=posX;
		this.posY=posY;
	}
	
	public int getX(){
		return posX;
	}
	
	public int getY(){
		return posY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
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
		Posicion other = (Posicion) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	

}
