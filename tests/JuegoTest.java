package tests;

import static org.junit.Assert.*;
/**
 * La clase JuegoTest en la encargada de testear la clase Juego, {@link logica.Juego}
 */

import java.util.Set;

import org.junit.Test;

import logica.*;
public class JuegoTest {

	@Test
	public void TestJuego(){
		Juego juegoNull = null;
		try{
			juegoNull.cuantasJugadasVan();
			juegoNull.dameMovimientos(new PosicionAjedrez((byte)2,'d'));
			juegoNull.dameMovimientos(null);
			juegoNull.dameTurno();
			juegoNull.enrocarCorto();
			juegoNull.enrocarLargo();
			juegoNull.mover(new PosicionAjedrez((byte)2,'d'), new PosicionAjedrez((byte)4,'d'));
			juegoNull.mover(null, null);
			juegoNull.enrocarLargo();
			juegoNull.revertir();
			juegoNull.coronar(NombrePieza.DAMA);
			juegoNull.coronar(null);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		/*
		 * este try catch fue colocado para probar si alguno de estos metodos tira una excepcion
		 * al ejecutarse debido a que juegoNull es null. Todos los metodos devuelven null en este caso. 
		 */
		
		Juego juego = new Juego();
		assertTrue(juego.huboUnaJugada()==false);
		juego.mover(new PosicionAjedrez((byte)2,'d'), new PosicionAjedrez((byte)4,'d'));
		juego.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)5,'e'));
		juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)5,'e'));
		assertEquals(juego.cuantasJugadasVan(),3);
		assertEquals(juego.dameTurno(),ColorPieza.NEGRO);
		assertEquals(juego.hayAhogado(),false);
		assertEquals(juego.huboUnaJugada(),true);
		juego.revertir();
		assertEquals(juego.huboUnaJugada(),true);
		/*
		 * creo una nueva instancia de juego y juego tres veces para representar un juego posible
		 * testeo los metodos de juego a los cuales puedo acceder desde la instancia creada  para
		 * corroborar lo que devuelven cada una
		 */
		try{
			juego.mover(new PosicionAjedrez((byte)4,'d'), new PosicionAjedrez((byte)7,'d'));
			juego.queHay(new PosicionAjedrez((byte)4,'d'));
			assertEquals(new PosicionAjedrez((byte)4,'d'),ColorPieza.BLANCO);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		/*
		 * envuelvo en un try catch las acciones que podrian tirar excepciones para comprobar que
		 * son las excepciones adecuadas a cada caso.
		 * Realizo un movimiento invalido para corroborar el mensaje de la excepcion
		 */
		assertEquals(juego.hayAlgo(new PosicionAjedrez((byte)5,'e')),true);
		assertEquals(juego.hayAlgo(new PosicionAjedrez((byte)5,'d')),false);
		assertEquals(juego.hayAlgo(new PosicionAjedrez((byte)2,'d')),false);
		/*
		 * testeo si las posiciones estan o no vacias dependiendo de mis jugadas anteriores
		 */
		Set<PosicionAjedrez> posiciones = juego.dameMovimientos(new PosicionAjedrez((byte)5,'e'));
		for(PosicionAjedrez p:posiciones){
			System.out.println(p);
		}
		/*
		 * pido los posibles movimientos de un casillero con una pieza
		 */
		try{

			Set<PosicionAjedrez> posicionesVacias = juego.dameMovimientos(new PosicionAjedrez((byte)2,'d'));
			for(PosicionAjedrez p:posicionesVacias){
				System.out.println(p);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		/*
		 * pido los posibles movimientos de un casillero sin pieza
		 */
		try{

			Set<PosicionAjedrez> posicionesVacias = juego.dameMovimientos(new PosicionAjedrez((byte)2,'d'));
			for(PosicionAjedrez p:posicionesVacias){
				System.out.println(p);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Juego juegoAhogado = new Juego();
		juegoAhogado.mover(new PosicionAjedrez((byte)2,'c'), new PosicionAjedrez((byte)4,'c'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'h'), new PosicionAjedrez((byte)5,'h'));
		juegoAhogado.mover(new PosicionAjedrez((byte)2,'h'), new PosicionAjedrez((byte)4,'h'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'a'), new PosicionAjedrez((byte)5,'a'));
		juegoAhogado.mover(new PosicionAjedrez((byte)1,'d'), new PosicionAjedrez((byte)4,'a'));
		juegoAhogado.mover(new PosicionAjedrez((byte)8,'a'), new PosicionAjedrez((byte)6,'a'));
		juegoAhogado.mover(new PosicionAjedrez((byte)4,'a'), new PosicionAjedrez((byte)5,'a'));
		juegoAhogado.mover(new PosicionAjedrez((byte)6,'a'), new PosicionAjedrez((byte)6,'h'));
		juegoAhogado.mover(new PosicionAjedrez((byte)5,'a'), new PosicionAjedrez((byte)7,'c'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'f'), new PosicionAjedrez((byte)6,'f'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'c'), new PosicionAjedrez((byte)7,'d'));
		juegoAhogado.mover(new PosicionAjedrez((byte)8,'e'), new PosicionAjedrez((byte)7,'f'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)7,'b'));
		juegoAhogado.mover(new PosicionAjedrez((byte)8,'d'), new PosicionAjedrez((byte)3,'d'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'b'), new PosicionAjedrez((byte)8,'b'));
		juegoAhogado.mover(new PosicionAjedrez((byte)3,'d'), new PosicionAjedrez((byte)7,'h'));
		juegoAhogado.mover(new PosicionAjedrez((byte)8,'b'), new PosicionAjedrez((byte)8,'c'));
		juegoAhogado.mover(new PosicionAjedrez((byte)7,'f'), new PosicionAjedrez((byte)6,'g'));
		juegoAhogado.mover(new PosicionAjedrez((byte)8,'c'), new PosicionAjedrez((byte)6,'e'));
		assertEquals(juegoAhogado.hayAhogado(),true);
		juegoAhogado.revertir();
		assertEquals(juegoAhogado.hayAhogado(),false);
		/* 
		 * muevo las piezas de juegoAhogado de tal forma que haya ahogado
		 * pregunto
		 * deshago una partida entonces se que no hay ahogado
		 * pregunto 
		 */
		Juego juegoCoronar = new Juego();
		juegoCoronar.mover(new PosicionAjedrez((byte)2,'h'), new PosicionAjedrez((byte)4,'h'));
		juegoCoronar.mover(new PosicionAjedrez((byte)7,'g'), new PosicionAjedrez((byte)5,'g'));
		juegoCoronar.mover(new PosicionAjedrez((byte)4,'h'), new PosicionAjedrez((byte)5,'g'));
		juegoCoronar.mover(new PosicionAjedrez((byte)8,'g'), new PosicionAjedrez((byte)6,'f'));
		juegoCoronar.mover(new PosicionAjedrez((byte)5,'g'), new PosicionAjedrez((byte)6,'g'));
		juegoCoronar.mover(new PosicionAjedrez((byte)7,'h'), new PosicionAjedrez((byte)6,'h'));
		juegoCoronar.mover(new PosicionAjedrez((byte)6,'g'), new PosicionAjedrez((byte)7,'g'));
		juegoCoronar.mover(new PosicionAjedrez((byte)6,'h'), new PosicionAjedrez((byte)5,'h'));
		juegoCoronar.mover(new PosicionAjedrez((byte)7,'g'), new PosicionAjedrez((byte)8,'g'));
		assertEquals(juegoCoronar.cuantasJugadasVan(),9);
		assertEquals(juegoCoronar.hayAlgoParaCoronar(),true);
		juegoCoronar.coronar(NombrePieza.DAMA);
		assertEquals(juegoCoronar.hayAlgoParaCoronar(),false);
		/*
		 * muevo las piezas de juegoCoronar de tal forma que se corone a un peon
		 * pregunto
		 * corono, y no hay mas nada para coronar
		 * pregunto
		 */
		Juego juegoJaqueMate = new Juego();
		juegoJaqueMate.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)4,'e'));
		juegoJaqueMate.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)6,'d'));
		juegoJaqueMate.mover(new PosicionAjedrez((byte)1,'f'), new PosicionAjedrez((byte)4,'c'));
		juegoJaqueMate.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)5,'e'));
		juegoJaqueMate.mover(new PosicionAjedrez((byte)1,'d'), new PosicionAjedrez((byte)5,'h'));
		juegoJaqueMate.mover(new PosicionAjedrez((byte)8,'b'), new PosicionAjedrez((byte)6,'c'));
		juegoJaqueMate.mover(new PosicionAjedrez((byte)5,'h'), new PosicionAjedrez((byte)7,'f'));
		assertEquals(juegoJaqueMate.hayJaque(),true);
		assertEquals(juegoJaqueMate.hayJaqueMate(),true);
		juegoJaqueMate.revertir();
		assertEquals(juegoJaqueMate.hayJaqueMate(),false);
		assertEquals(juegoJaqueMate.hayJaque(),false);
		/*
		 * muevo las piezas de juegoJaqueMate de tal forma que haya jaqueMate
		 * pregunto
		 * revierto
		 * pregunto
		 */
		Juego juegoJaque = new Juego();
		juegoJaque.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)4,'e'));
		juegoJaque.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)6,'d'));
		juegoJaque.mover(new PosicionAjedrez((byte)1,'f'), new PosicionAjedrez((byte)4,'c'));
		juegoJaque.mover(new PosicionAjedrez((byte)8,'d'), new PosicionAjedrez((byte)7,'d'));
		juegoJaque.mover(new PosicionAjedrez((byte)2,'h'), new PosicionAjedrez((byte)4,'h'));
		juegoJaque.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)5,'e'));
		juegoJaque.mover(new PosicionAjedrez((byte)1,'d'), new PosicionAjedrez((byte)5,'h'));
		juegoJaque.mover(new PosicionAjedrez((byte)8,'b'), new PosicionAjedrez((byte)6,'c'));
		juegoJaque.mover(new PosicionAjedrez((byte)5,'h'), new PosicionAjedrez((byte)7,'f'));
		assertEquals(juegoJaque.hayJaque(),true);
		assertEquals(juegoJaque.hayJaqueMate(),false);
		juegoJaque.revertir();
		assertEquals(juegoJaque.hayJaqueMate(),false);
		assertEquals(juegoJaque.hayJaque(),false);
		/*
		 * muevo las piezas de juegoJaque de tal forma que haya jaque pero no jaqueMate
		 * pregunto
		 * revierto
		 * pregunto
		 */
		Juego juegoEnroqueCorto = new Juego();
		juegoEnroqueCorto.mover(new PosicionAjedrez((byte)1,'g'), new PosicionAjedrez((byte)3,'f'));
		juegoEnroqueCorto.mover(new PosicionAjedrez((byte)7,'a'), new PosicionAjedrez((byte)6,'a'));
		juegoEnroqueCorto.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)3,'e'));
		juegoEnroqueCorto.mover(new PosicionAjedrez((byte)7,'h'), new PosicionAjedrez((byte)6,'h'));
		juegoEnroqueCorto.mover(new PosicionAjedrez((byte)1,'f'), new PosicionAjedrez((byte)3,'d'));
		juegoEnroqueCorto.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)6,'d'));
		assertEquals(juegoEnroqueCorto.sePuedeEnrocarCorto(),true);
		assertEquals(juegoEnroqueCorto.sePuedeEnrocarLargo(),false);
		juegoEnroqueCorto.enrocarCorto();
		juegoEnroqueCorto.revertir();
		assertEquals(juegoEnroqueCorto.sePuedeEnrocarCorto(),true);
		assertEquals(juegoEnroqueCorto.sePuedeEnrocarLargo(),false);
		/*
		 * de la misma forma que los anteriores, pruebo el enroqueCorto
		 */
		Juego juegoEnroqueLargo = new Juego();
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)2,'b'), new PosicionAjedrez((byte)3,'b'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)7,'a'), new PosicionAjedrez((byte)6,'a'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)1,'c'), new PosicionAjedrez((byte)2,'b'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)7,'b'), new PosicionAjedrez((byte)6,'b'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)3,'e'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)7,'g'), new PosicionAjedrez((byte)6,'g'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)1,'d'), new PosicionAjedrez((byte)2,'e'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)7,'h'), new PosicionAjedrez((byte)6,'h'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)1,'b'), new PosicionAjedrez((byte)3,'c'));
		juegoEnroqueLargo.mover(new PosicionAjedrez((byte)6,'g'), new PosicionAjedrez((byte)5,'g'));
		assertEquals(juegoEnroqueLargo.sePuedeEnrocarCorto(),false);
		assertEquals(juegoEnroqueLargo.sePuedeEnrocarLargo(),true);
		juegoEnroqueLargo.enrocarLargo();
		juegoEnroqueLargo.revertir();
		assertEquals(juegoEnroqueLargo.sePuedeEnrocarCorto(),false);
		assertEquals(juegoEnroqueLargo.sePuedeEnrocarLargo(),true);
		/*
		 * de la misma forma que los anteriores, pruebo el enroqueLargo
		 */
		Juego juegoDosEnroques = new Juego();
		juegoDosEnroques.mover(new PosicionAjedrez((byte)1,'g'), new PosicionAjedrez((byte)3,'f'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'a'), new PosicionAjedrez((byte)6,'a'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)3,'e'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'b'), new PosicionAjedrez((byte)6,'b'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)1,'f'), new PosicionAjedrez((byte)3,'d'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'c'), new PosicionAjedrez((byte)6,'c'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)2,'b'), new PosicionAjedrez((byte)3,'b'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)6,'d'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)1,'c'), new PosicionAjedrez((byte)2,'b'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'e'), new PosicionAjedrez((byte)6,'e'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)2,'c'), new PosicionAjedrez((byte)4,'c'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'f'), new PosicionAjedrez((byte)6,'f'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)1,'d'), new PosicionAjedrez((byte)2,'e'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'g'), new PosicionAjedrez((byte)6,'g'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)1,'b'), new PosicionAjedrez((byte)3,'c'));
		juegoDosEnroques.mover(new PosicionAjedrez((byte)7,'h'), new PosicionAjedrez((byte)6,'h'));
		assertEquals(juegoDosEnroques.sePuedeEnrocarCorto(),true);
		assertEquals(juegoDosEnroques.sePuedeEnrocarLargo(),true);
		juegoDosEnroques.enrocarLargo();
		juegoDosEnroques.revertir();
		juegoDosEnroques.mover(new PosicionAjedrez((byte)2,'g'), new PosicionAjedrez((byte)4,'g'));
		juegoDosEnroques.revertir();
		juegoDosEnroques.enrocarCorto();
		juegoDosEnroques.revertir();
		assertEquals(juegoDosEnroques.sePuedeEnrocarCorto(),true);
		assertEquals(juegoDosEnroques.sePuedeEnrocarLargo(),true);
		/*
		 * pruebo el caso border en el que se puede hacer enroque largo y enroque corto
		 */
	}
}
