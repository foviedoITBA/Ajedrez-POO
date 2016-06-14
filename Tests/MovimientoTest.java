package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import logica.*;

public class MovimientoTest {
	@Test
	public void testMovimiento() {
		
		Movimiento m = new Movimiento(10,10,true,true,false,10);//no tendria que poderse hacer
		System.out.println("EsComiendo:"+m.esComiendo()+"	EsSinComer:"+m.esSinComer());
		new Movimiento(null);//podria validar que no sea null
	}
}
