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
	public void testJugadorWrite(){
		FileOutputStream file = null;
		ObjectOutputStream jugador = null;
		ObjectOutputStream jugador2 = null;
		try{
			file = new FileOutputStream("juego.txt",false);
			jugador = new ObjectOutputStream(file);
			jugador2 = new ObjectOutputStream(file);
			jugador.writeObject(new Jugador(ColorPieza.BLANCO));
			jugador2.writeObject(new Jugador(ColorPieza.NEGRO));
			jugador.close();
			jugador2.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testJugadorRead() throws FileNotFoundException, IOException, ClassNotFoundException {
		Jugador jugador = null;
		Jugador jugador2 = null;
		FileInputStream file = null;
		ObjectInputStream ois = null;
		try{
			file = new FileInputStream("juego.txt");
			ois = new ObjectInputStream(file);
			jugador = (Jugador) ois.readObject();
			jugador2 = (Jugador) ois.readObject();
			ois.close();
			file.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		assertEquals(jugador.dameColor(),ColorPieza.BLANCO);
		assertEquals(jugador2.dameColor(),ColorPieza.NEGRO);
	}
}
