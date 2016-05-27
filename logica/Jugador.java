package logica;

public class Jugador {

	public final static boolean BLANCO=true;
	public final static boolean NEGRO=false;
	
	private boolean esBlanco;
	
	public Jugador(boolean color){
		esBlanco=color;
	}
	
	public boolean esBlanco(){
		return esBlanco;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (esBlanco ? 1231 : 1237);
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
		if (esBlanco != other.esBlanco)
			return false;
		return true;
	}
	
}
