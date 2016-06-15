package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import excepcion.CoronacionInvalidaException;
import logica.*;
public class TableroTest {
	
	
	@Test
	public void testMovimiento() {
		
		Movimiento m = new Movimiento(10,10,true,true,false,10);//no tendria que poderse hacer
		System.out.println("EsComiendo:"+m.esComiendo()+"	EsSinComer:"+m.esSinComer());
		new Movimiento(null);//podria validar que no sea null
		assertEquals(m.dameMovX(),10);
		assertEquals(m.dameMovY(),10);
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
	
	@Test
	public void testTablero(){
		Tablero tablero = new Tablero();
		Pieza peon = new Peon(ColorPieza.BLANCO);
		assertEquals(peon.puedoCoronar(8),true);
		tablero.moverPieza(new PosicionAjedrez((byte)2,'a'), new PosicionAjedrez((byte)8,'a'));
		
		tablero.coronar(NombrePieza.PEON,ColorPieza.BLANCO);//porque no puedo coronar??
		assertEquals(tablero.moverPieza(new PosicionAjedrez((byte)-1, 'a'), new PosicionAjedrez((byte)3,'c')),null);
	}
}
