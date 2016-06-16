package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import logica.*;

public class JuegoTest {

	@Test
	public void TestJuego(){
		Juego juego = new Juego();
		juego.mover(new PosicionAjedrez((byte)2,'d'), new PosicionAjedrez((byte)4,'d'));
		juego.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)5,'e'));
		juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)5,'e'));
		/* 
		 * hago tres movimientos en el juego
		 */
		assertEquals(juego.cuantasJugadasVan(),3);
		assertEquals(juego.dameTurno(),ColorPieza.NEGRO);
		assertEquals(juego.hayAhogado(),false);
		juego.revertir();
		try{
			juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)7,'d'));
			juego.queHay(new PosicionAjedrez((byte)4,'d'));
			assertEquals(new PosicionAjedrez((byte)4,'d'),ColorPieza.BLANCO);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		/*
		 * trato de moverme aa una posicion invalida
		 */
		assertEquals(juego.hayAlgo(new PosicionAjedrez((byte)5,'e')),true);
		assertEquals(juego.hayAlgo(new PosicionAjedrez((byte)5,'d')),false);
		assertEquals(juego.hayAlgo(new PosicionAjedrez((byte)2,'d')),false);
		/*
		 * testeo si las posiciones estan o no vacias dependiendo de mis jugadas anteriores
		 */
		Set<PosicionAjedrez> posiciones = juego.dameMovimientos(new PosicionAjedrez((byte)5,'e'));
		for(PosicionAjedrez p:posiciones){
			System.out.println(p);
		}
		/*
		 * pido los posibles movimientos de un casillero con una pieza
		 */
		try{

			Set<PosicionAjedrez> posicionesVacias = juego.dameMovimientos(new PosicionAjedrez((byte)2,'d'));
			for(PosicionAjedrez p:posicionesVacias){
				System.out.println(p);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		/*
		 * pido los posibles movimientos de un casillero sin pieza
		 */
		try{

			Set<PosicionAjedrez> posicionesVacias = juego.dameMovimientos(new PosicionAjedrez((byte)2,'d'));
			for(PosicionAjedrez p:posicionesVacias){
				System.out.println(p);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
