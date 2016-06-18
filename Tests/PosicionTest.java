package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logica.*;
/**
 * La clase PosicionTest representa los test de las clase PosicionAjedrez y PosicionTablero
 * y prueba los metodos posibles de ambas clases
 */

public class PosicionTest {
	
	@Test
	public void testPosicionAjedrez(){
		PosicionAjedrez posicion = new PosicionAjedrez((byte) 1, 'a');
		assertEquals(posicion.dameFila(),(byte)1);
		assertEquals(posicion.dameColumna(),'a');
		try{
			PosicionAjedrez posicionNull = null;
			PosicionAjedrez posicionInvalida = new PosicionAjedrez((byte)-1,'z');
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void TestPosicionTablero(){
		PosicionTablero posicion = new PosicionTablero(1,5);
		assertEquals(posicion.getX(),1);
		assertEquals(posicion.getY(),5);
		try{
			PosicionTablero posicionNull = null;
			PosicionTablero posicionInvalida = new PosicionTablero(-1,20);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
