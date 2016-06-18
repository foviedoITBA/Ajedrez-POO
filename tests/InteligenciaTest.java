package tests;
import org.junit.Test;

import ia.*;
import logica.*;
/**
 * Esta clase es la encargada de testear la clase inteligencia, para una comprension total de su funcionamiento
 * consultar {@link JuegoTest}, {@link logica.Juego} y {@link logica.Inteligencia}}
 */
public class InteligenciaTest {

	@Test
	public void testInteligencia(){
		Juego juego = new Juego();
		Juego juegoNull = null;
		Inteligencia ia = null;
		Inteligencia iaNull = null;
		/*
		 * creo dos instancias de juego, una de ellas la incializado en null y 
		 * en la otra realizo tres movimientos como minimo para representar un posible juego
		 * creo dos instancias de inteligencia, una con el juego posible y otra con el juego null
		 * para comprobar si cada metodo tira las excepciones deseadas o no al recibir disitintos 
		 * paremetros (instancias correctas o null)
		 */
		juego.mover(new PosicionAjedrez((byte)2,'d'), new PosicionAjedrez((byte)4,'d'));
		juego.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)5,'e'));
		juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)5,'e'));
		try{
			juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)5,'e'));
			try{
				ia = new Inteligencia(juego,ColorPieza.NEGRO);
				ia = new Inteligencia(juegoNull,ColorPieza.NEGRO);
				iaNull = new Inteligencia(juego,ColorPieza.NEGRO);
				iaNull = new Inteligencia(juegoNull,ColorPieza.NEGRO);
				ia.juega();
				iaNull.juega();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
