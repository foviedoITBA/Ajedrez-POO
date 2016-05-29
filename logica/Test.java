package logica;

public class Test {
	
	public static void main(String[] args) {
		
		Jugador jugador1=new Jugador(Color.NEGRO);
		Jugador jugador2=new Jugador(Color.BLANCO);
		Posicion pos = new Posicion(6,0);
		
		Tablero tablero = new Tablero();
		tablero.agregoNegra();
		
		tablero.imprimirTablero();
		
		tablero.click(pos, jugador1);
		
		tablero.click(new Posicion(4,0), jugador1);
		
		tablero.imprimirTablero();
		
		tablero.click(new Posicion(7,0), jugador2);
		
		tablero.imprimirTablero();
		
		tablero.click(new Posicion(4,0), jugador2);
		
		tablero.imprimirTablero();
		
		tablero.click(new Posicion(7,1), jugador2);
		
		
		
	}
}
