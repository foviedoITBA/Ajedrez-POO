package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logica.*;


public class PosicionTest {
	
	@Test
	public void testPosicionAjedrez(){
		PosicionAjedrez posicion = new PosicionAjedrez((byte) 1, 'a');
		assertEquals(posicion.dameFila(),(byte)1);
		assertEquals(posicion.dameColumna(),'a');	
	}
	
	@Test
	public void TestPosicionTablero(){
		PosicionTablero p1 = new PosicionTablero(10,-1);//no se deberia poder hacer esto
	}
	
}
