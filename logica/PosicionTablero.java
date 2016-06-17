package logica;

import java.io.Serializable;

public class PosicionTablero implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posX, posY;
	
	public PosicionTablero(int posX, int posY){
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
		PosicionTablero other = (PosicionTablero) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	
	/**PARA TESTING**/
	
	public String toString(){
		return "x: "+posX+"y: "+posY;
	}
	

}
