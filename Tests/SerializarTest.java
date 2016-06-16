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
		guardar(juego,"juego");
		Juego juego_recuperado = cargar("juego");
		System.out.println(juego_recuperado.dameUltimaJugada());
		System.out.println(juego.dameUltimaJugada());
		assertEquals(juego.cuantasJugadasVan(),juego_recuperado.cuantasJugadasVan());
		assertEquals(juego.dameTurno(),juego_recuperado.dameTurno());
		assertEquals(juego.dameUltimaJugada().toString(),juego_recuperado.dameUltimaJugada().toString());
	}
	
	private static void guardar(Juego juego,String nombreFile){
		nombreFile = nombreFile+".txt";
		try{
			FileOutputStream file = new FileOutputStream(nombreFile,false);
			ObjectOutputStream juegoGuardado = new ObjectOutputStream(file);
			juegoGuardado.writeObject(juego);
			juegoGuardado.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static Juego cargar(String nombreFile){
		nombreFile = nombreFile+".txt";
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFile));
			Juego juegoRecuperado = (Juego) ois.readObject();
			ois.close();
			return juegoRecuperado;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}	
	}

}
