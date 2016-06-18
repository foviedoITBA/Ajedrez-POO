package logica;

import java.io.Serializable;

import excepcion.JugadaInvalidaException;

public class PosicionTablero implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posX, posY;
	
	public PosicionTablero(int posX, int posY){
		if(!esValido(posX,posY)){
			throw new JugadaInvalidaException();
		}
		this.posX=posX;
		this.posY=posY;
	}
	
	private boolean esValido(int posX, int posY){
		return (posX < 0 || posX > 7 || posY < 0 || posY > 7)?false:true;
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

}
