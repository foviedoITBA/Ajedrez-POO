package logica;

import ia.*;

public class Test {
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		Inteligencia ia = new Inteligencia(juego, Color.NEGRO);
		
		juego.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)4,'e'));
		ia.juega();

		juego.revertir();
		juego.revertir();

		juego.mover(new PosicionAjedrez((byte)2,'d'), new PosicionAjedrez((byte)4,'d'));
		ia.juega();

	}
}
