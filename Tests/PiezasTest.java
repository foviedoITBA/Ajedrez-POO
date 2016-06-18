
/**
 * Esta clase comprueba el buen funcionamiento de la clase Pieza y todos sus descendientes al igual que sus respectivos metodos. 
 */
package tests;
import logica.*;
import static org.junit.Assert.*;
import org.junit.Test;
public class PiezasTest {

	@Test
	public void testPieza() {
		/* instanciamos un alfil ya que la clase Pieza es abstracta
		 */
		Pieza pieza = new Alfil(ColorPieza.BLANCO);
		assertTrue(pieza.dameColor()==ColorPieza.BLANCO);
		assertTrue(pieza.dameColor()!=ColorPieza.NEGRO);
		pieza.ponerSeMovio();
		pieza.ponerSeMovio();
		assertEquals(pieza.dameSeMovio(),true);
		pieza.sacarSeMovio();
		pieza.sacarSeMovio();
		assertEquals(pieza.dameSeMovio(),false);
		pieza.sacarSeMovio();
		assertEquals(pieza.dameSeMovio(),false);
	}
	
	@Test
	public void testPeon() throws CloneNotSupportedException{
		Pieza peon = new Peon(ColorPieza.BLANCO);
		assertEquals(peon.dameNombre(),NombrePieza.PEON);
		assertEquals(peon.dameMovimientos().size(),3);
		assertEquals(peon.puedoEnrocar(),false);
		assertEquals(peon.puedoCoronar(0),false);
		assertEquals(peon.puedoCoronar(8),true);
		peon.sacarSeMovio();
		assertEquals(peon.dameSeMovio(),false);
		peon.ponerSeMovio();		
		peon.ponerSeMovio();
		peon.sacarSeMovio();
		System.out.println(peon.dameMovimientos().size());
		assertEquals(peon.dameMovimientos().size(),3);
		assertEquals(peon.dameMovimientos().get(0).cantidadDeVeces(),1);
		peon.sacarSeMovio();
		assertEquals(peon.dameMovimientos().get(2).cantidadDeVeces(),2);
		peon.dameMovimientos().clear();
		assertEquals(peon.dameMovimientos().size(),3);
	}
	
	@Test
	public void testTorre(){
		Pieza torre = new Torre(ColorPieza.NEGRO);
		assertEquals(torre.dameNombre(),NombrePieza.TORRE);
		torre.sacarSeMovio();
		assertEquals(torre.puedoEnrocar(),true);
	}
	
	@Test
	public void testCaballo(){
		Pieza caballo = new Caballo(ColorPieza.NEGRO);
		caballo.ponerSeMovio();
		caballo.ponerSeMovio();
		for(Movimiento mov: caballo.dameMovimientos()){
			assertEquals(mov.cantidadDeVeces(),1);
			assertEquals(mov.esComiendo(),true);
			assertEquals(mov.esSaltando(),true);
		}
	}
	
	@Test
	public void testAfil() throws CloneNotSupportedException{
		Pieza pieza = new Alfil(ColorPieza.BLANCO);
		assertEquals(pieza.dameNombre(),NombrePieza.ALFIL);
		assertEquals(pieza.dameMovimientos().size(),4);
		assertEquals(pieza.puedoEnrocar(),false);
		assertEquals(pieza.puedoCoronar(0),false);
		pieza.dameMovimientos().clear();
		assertEquals(pieza.dameMovimientos().size(),4);
	}

	@Test
	public void testDama() throws CloneNotSupportedException{
		Pieza dama = new Dama(ColorPieza.BLANCO);
		assertEquals(dama.dameNombre(),NombrePieza.DAMA);
		assertEquals(dama.dameMovimientos().size(),8);
		assertEquals(dama.puedoEnrocar(),false);
		assertEquals(dama.puedoCoronar(0),false);
		dama.dameMovimientos().clear();
		assertEquals(dama.dameMovimientos().size(),8);
	}
	
	@Test
	public void testRey() throws CloneNotSupportedException{
		Pieza rey = new Rey(ColorPieza.BLANCO);
		assertEquals(rey.dameNombre(),NombrePieza.REY);
		assertEquals(rey.dameMovimientos().size(),8);
		assertEquals(rey.puedoEnrocar(),true);
		rey.ponerSeMovio();
		assertEquals(rey.puedoEnrocar(),false);
		assertEquals(rey.puedoCoronar(0),false);
		rey.dameMovimientos().clear();
		assertEquals(rey.dameMovimientos().size(),8);
	}
	
}
