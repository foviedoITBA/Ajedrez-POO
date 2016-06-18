package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 * La clase TableroTest es la encargada de testear las clases Movimiento, {@link logica.Movimiento} y Casillero {@link logica.Casillero}
 */
import logica.*;
public class TableroTest {
	
	
	@Test
	public void testMovimiento() {
		
		Movimiento m = new Movimiento(7,4,true,true,false,10);
		System.out.println("EsComiendo:"+m.esComiendo()+"	EsSinComer:"+m.esSinComer());
		assertEquals(m.dameMovX(),8);
		assertEquals(m.dameMovY(),6);
		assertEquals(m.esComiendo(),true);
	}
	
	@Test
	public void testCasillero() throws CloneNotSupportedException{
		Pieza dama = new Dama(ColorPieza.BLANCO);
		Pieza rey = new Rey(ColorPieza.NEGRO);
		Casillero c = new Casillero(dama);
		assertEquals(c.isEmpty(),false);
		c.removePieza();
		assertEquals(c.isEmpty(),true);
		c.addPieza(rey);
		assertEquals(c.getPieza(),rey);
	}
}
