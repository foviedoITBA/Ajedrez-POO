package logica;

import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		
		try {
			System.out.println(juego.hayAlgo(new PosicionAjedrez((byte)2,'a')));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//System.out.println(juego.dameMovimientos(new PosicionAjedrez((byte)7,'a')));
			for(PosicionAjedrez i: juego.dameMovimientos(new PosicionAjedrez((byte)2,'a'))){
				System.out.println(String.valueOf(i.dameColumna())+i.dameFila());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			juego.mover(new PosicionAjedrez((byte)2,'a'), new PosicionAjedrez((byte)4,'a'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			System.out.println(juego.hayAlgo(new PosicionAjedrez((byte)8,'c')));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			for(PosicionAjedrez i: juego.dameMovimientos(new PosicionAjedrez((byte)8,'c'))){
				System.out.println(String.valueOf(i.dameColumna())+i.dameFila());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			juego.mover(new PosicionAjedrez((byte)8,'c'), new PosicionAjedrez((byte),'a'));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		/*Jugador jugador1=new Jugador(Color.NEGRO);
		Jugador jugador2=new Jugador(Color.BLANCO);
		PosicionTablero pos = new PosicionTablero(6,0);
		
		Tablero tablero = new Tablero();
		tablero.imprimirTablero();
		// e4
		tablero.click(new PosicionTablero(6,4), jugador2);
		tablero.click(new PosicionTablero(4,4), jugador2);
		tablero.imprimirTablero();
		// d5
		tablero.click(new PosicionTablero(1,3), jugador1);
		tablero.click(new PosicionTablero(3,3), jugador1);
		tablero.imprimirTablero();
		// e4xd5
		tablero.click(new PosicionTablero(4,4), jugador2);
		tablero.click(new PosicionTablero(3,3), jugador2);
		tablero.imprimirTablero();
		// Dxd5
		tablero.click(new PosicionTablero(0,3), jugador1);
		tablero.click(new PosicionTablero(3,3), jugador1);
		tablero.imprimirTablero();
		// Cc3
		tablero.click(new PosicionTablero(7, 1), jugador2);
		tablero.click(new PosicionTablero(5, 2), jugador2);
		tablero.imprimirTablero();
		// De5+
		tablero.click(new PosicionTablero(3,3), jugador1);
		tablero.click(new PosicionTablero(3,4), jugador1);
		tablero.imprimirTablero();


		// Trato de hacer Re2, no debería poder porque sigo en jaque
		tablero.click(new PosicionTablero(7,4), jugador2);
		tablero.click(new PosicionTablero(6,4), jugador2);
		tablero.imprimirTablero();
		
		// Trato de tapar el jaque con el alfil de f (Ae2) y eso sí lo puedo hacer
		tablero.click(new PosicionTablero(7,5), jugador2);
		tablero.click(new PosicionTablero(6,4), jugador2);
		tablero.imprimirTablero();

		/*
		tablero.agregoNegra();
		tablero.agregoPeon();
		
		tablero.imprimirTablero();
		
		tablero.click(pos, jugador1);
		
		tablero.click(new Posicion(4,0), jugador1);
		
		tablero.imprimirTablero();
		
		tablero.click(new Posicion(7,0), jugador2);
		
		tablero.imprimirTablero();
		
//		tablero.click(new Posicion(4,0), jugador2);
//		
//		tablero.imprimirTablero();
		
//		tablero.click(new Posicion(6,1), jugador2);
		
		tablero.click(new Posicion(4,0), jugador1);
		tablero.click(new Posicion(5,0), jugador1);
		
		tablero.click(new Posicion(6,1), jugador2);
		
		tablero.imprimirTablero();
		
		tablero.click(new Posicion(5,0), jugador2);
		
		tablero.imprimirTablero();
		
		tablero.click(new Posicion(1,0), jugador1);
		
		tablero.click(new Posicion(5,0), jugador2);
	*/
	}
}
