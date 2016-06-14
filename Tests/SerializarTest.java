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
	public void testJugadorWrite(){
		FileOutputStream file = null;
		ObjectOutputStream oos = null;
		try{
			file = new FileOutputStream("juego.txt",false);
			oos = new ObjectOutputStream(file);
			oos.writeObject(new Jugador(ColorPieza.BLANCO));
			oos.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testJugadorRead() throws FileNotFoundException, IOException, ClassNotFoundException {
		Jugador jugador = null;
		FileInputStream file = null;
		ObjectInputStream ois = null;
		try{
			file = new FileInputStream("juego.txt");
			ois = new ObjectInputStream(file);
			jugador = (Jugador) ois.readObject();
			ois.close();
			file.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		assertEquals(jugador.dameColor(),ColorPieza.BLANCO);
	}
}
