package Tests;
import logica.*;
import static org.junit.Assert.*;

import java.util.List;

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
		//tendria que dar false pero da true porque el contador es negativo y cant!=0 le da true

	}
	//PEON,TORRE,CABALLO,ALFIL,DAMA,REY;
	@Test
	public void testPeon(){
		Pieza peon = new Peon(ColorPieza.BLANCO);
		assertEquals(peon.dameNombre(),NombrePieza.PEON);
		assertEquals(peon.dameMovimientos().size(),3);
		assertEquals(peon.puedoEnrocar(),false);
		assertEquals(peon.puedoCoronar(0),false);
		assertEquals(peon.puedoCoronar(8),true);
		peon.sacarSeMovio();
		assertEquals(peon.dameSeMovio(),false);
		peon.ponerSeMovio();
		peon.sacarSeMovio();
		assertEquals(peon.dameMovimientos().get(0).cantidadDeVeces(),2);
		//hago undo, lo muevo y vuelvo a hacer undo y no puedo moverlo dos casilleros
		peon.dameMovimientos().clear();
		//no tendria que poder acceder a la lista de movimientos de la pieza
		assertEquals(peon.dameMovimientos().size(),4);
	}
	
	@Test
	public void testTorre(){
		Pieza torre = new Torre(ColorPieza.NEGRO);
		assertEquals(torre.dameNombre(),NombrePieza.TORRE);
		torre.sacarSeMovio();
		assertEquals(torre.puedoEnrocar(),true);
		//por lo del contador devuelta
		
	}
	
	@Test
	public void testAfil(){
		Pieza pieza = new Alfil(ColorPieza.BLANCO);
		assertEquals(pieza.dameNombre(),NombrePieza.ALFIL);
		assertEquals(pieza.dameMovimientos().size(),4);
		assertEquals(pieza.puedoEnrocar(),false);
		assertEquals(pieza.puedoCoronar(0),false);
		pieza.dameMovimientos().clear();
		//no tendria que poder acceder a la lista de movimientos de la pieza
		assertEquals(pieza.dameMovimientos().size(),4);
	}


}
