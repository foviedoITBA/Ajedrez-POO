package Tests;

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
	public void testJugadorWrite() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("juego.txt",true));
		oos.writeObject(new Jugador(ColorPieza.BLANCO));
		oos.close();
	}
	
	@Test
	public void testJugadorRead() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("juego.txt"));
		
		Jugador jugador = (Jugador) ois.readObject();
		ois.close();
		assertEquals(jugador.dameColor(),ColorPieza.BLANCO);
	}
}
