package logica;

import ia.*;

public class Test {
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		/* Test IA vs IA
		Inteligencia ia1 = new Inteligencia(juego, ColorPieza.BLANCO);
		Inteligencia ia2 = new Inteligencia(juego, ColorPieza.NEGRO);
		while(!juego.hayJaqueMate()) {
			ia1.juega();
			ia2.juega();
		}
		*/

		/* Test enroque corto
		juego.mover(new PosicionAjedrez((byte) 2, 'e'), new PosicionAjedrez((byte) 4, 'e'));
		juego.mover(new PosicionAjedrez((byte) 7, 'e'), new PosicionAjedrez((byte) 5, 'e'));
		juego.mover(new PosicionAjedrez((byte) 1, 'g'), new PosicionAjedrez((byte) 3, 'f'));
		juego.mover(new PosicionAjedrez((byte) 8, 'b'), new PosicionAjedrez((byte) 6, 'c'));
		juego.mover(new PosicionAjedrez((byte) 1, 'f'), new PosicionAjedrez((byte) 4, 'c'));
		juego.mover(new PosicionAjedrez((byte) 8, 'g'), new PosicionAjedrez((byte) 6, 'f'));
		juego.enrocarCorto();
		juego.imprimirTablero();
		juego.mover(new PosicionAjedrez((byte) 8, 'f'), new PosicionAjedrez((byte) 7, 'e'));
		juego.mover(new PosicionAjedrez((byte) 2, 'd'), new PosicionAjedrez((byte) 3, 'd'));
		juego.enrocarCorto();
		juego.imprimirTablero();
		juego.revertir();
		*/

		/*
		juego.mover(new PosicionAjedrez((byte) 2, 'd'), new PosicionAjedrez((byte) 4, 'd'));
		juego.mover(new PosicionAjedrez((byte) 7, 'd'), new PosicionAjedrez((byte) 5, 'd'));
		juego.mover(new PosicionAjedrez((byte) 1, 'b'), new PosicionAjedrez((byte) 3, 'c'));
		juego.mover(new PosicionAjedrez((byte) 8, 'b'), new PosicionAjedrez((byte) 6, 'c'));
		juego.mover(new PosicionAjedrez((byte) 1, 'd'), new PosicionAjedrez((byte) 3, 'd'));
		juego.mover(new PosicionAjedrez((byte) 8, 'd'), new PosicionAjedrez((byte) 6, 'd'));
		juego.mover(new PosicionAjedrez((byte) 1, 'c'), new PosicionAjedrez((byte) 2, 'd'));
		juego.mover(new PosicionAjedrez((byte) 8, 'c'), new PosicionAjedrez((byte) 7, 'd'));
		juego.enrocarLargo();
		juego.imprimirTablero();
		juego.enrocarLargo();
		juego.imprimirTablero();
		juego.revertir();
		*/
	}
}
