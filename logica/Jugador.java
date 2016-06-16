package logica;

import java.io.Serializable;

class Jugador implements Serializable{

	private ColorPieza color;
	private int tiempoAcumulado;
	private long tiempoInicio;
	
	public Jugador(ColorPieza color){
		this.color=color;
		tiempoAcumulado=0;
	}
	
	public ColorPieza dameColor() {
		return color;
	}
	
	public void iniciarTiempo(){
		tiempoInicio = System.nanoTime();
	}
	
	public void pausarTiempo(){
		long tiempoFinal = System.nanoTime();
		tiempoAcumulado += (tiempoFinal - tiempoInicio)/1000000000;//lo paso a segundos
	}
	
	public int dameTiempo(){
		return tiempoAcumulado;
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
