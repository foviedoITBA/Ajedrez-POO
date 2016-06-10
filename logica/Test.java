package logica;

import ia.*;

public class Test {
	
	public static void main(String[] args) {
		
		
		int jugadas = 0;
		Juego juego = new Juego();
		Inteligencia ia1 = new Inteligencia(juego, Color.BLANCO);
		Inteligencia ia2 = new Inteligencia(juego, Color.NEGRO);
		while(!juego.hayJaqueMate()) {
			ia1.juega();
			ia2.juega();
			jugadas++;
		}
		

	}
}
