package logica;

import ia.*;

public class Test {
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		Inteligencia ia1 = new Inteligencia(juego, ColorPieza.BLANCO);
		Inteligencia ia2 = new Inteligencia(juego, ColorPieza.NEGRO);
		while(!juego.hayJaqueMate()) {
			ia1.juega();
			ia2.juega();
		}
		

	}
}
