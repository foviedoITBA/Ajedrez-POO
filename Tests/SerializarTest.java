package tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import logica.*;


public class SerializarTest {

	@Test
	public void testSerializable() throws Exception{
		Juego juego = new Juego();
		juego.mover(new PosicionAjedrez((byte)2,'a'), new PosicionAjedrez((byte)3,'a'));
		juego.guardar(juego,"juego");
		Juego juego_recuperado = Juego.cargar("juego");
		System.out.println(juego_recuperado.dameUltimaJugada());
		System.out.println(juego.dameUltimaJugada());
		assertEquals(juego.cuantasJugadasVan(),juego_recuperado.cuantasJugadasVan());
		assertEquals(juego.dameTurno(),juego_recuperado.dameTurno());
		assertEquals(juego.dameUltimaJugada().toString(),juego_recuperado.dameUltimaJugada().toString());
	}

}
