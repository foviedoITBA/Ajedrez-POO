package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logica.*;


public class CasilleroTest {


	@Test
	public void testCasillero() throws CloneNotSupportedException{
		Pieza dama = new Dama(ColorPieza.BLANCO);
		Casillero c = new Casillero(dama);
		assertEquals(c.isEmpty(),false);
		c.removePieza();
		assertEquals(c.isEmpty(),true);
	}
	
}
