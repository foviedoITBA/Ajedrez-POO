package tests;
import org.junit.Test;

import ia.*;
import logica.*;

public class InteligenciaTest {

	@Test
	public void testInteligencia(){
		Juego juego = new Juego();
		Juego juegoNull = null;
		Inteligencia ia = null;
		Inteligencia iaNull = null;
		juego.mover(new PosicionAjedrez((byte)2,'d'), new PosicionAjedrez((byte)4,'d'));
		juego.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)5,'e'));
		juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)5,'e'));
		try{
			ia = new Inteligencia(juego,ColorPieza.NEGRO);
			ia = new Inteligencia(juegoNull,ColorPieza.NEGRO);
			iaNull = new Inteligencia(juego,ColorPieza.NEGRO);
			iaNull = new Inteligencia(juegoNull,ColorPieza.NEGRO);
			ia.juega();
			iaNull.juega();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		
	}
}
