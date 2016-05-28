package logica;

public class Test {
	
	public static void main(String[] args) {
		
		Jugador jugador1=new Jugador(Jugador.BLANCO);
		Posicion pos = new Posicion(0,7);
		
		Tablero tablero = new Tablero();
		tablero.agregoNegra();
		
		tablero.imprimirTablero();
		
		tablero.click(pos, jugador1);
		
	}
}
