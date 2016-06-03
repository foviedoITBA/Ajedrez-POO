package logica;

public class Test {
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		
		try {
			juego.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)4,'e'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			juego.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)5,'d'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			juego.mover(new PosicionAjedrez((byte)4,'e'), new PosicionAjedrez((byte)5,'d'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			juego.mover(new PosicionAjedrez((byte)8,'d'), new PosicionAjedrez((byte)5,'d'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			juego.mover(new PosicionAjedrez((byte)1,'b'), new PosicionAjedrez((byte)3,'c'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			juego.mover(new PosicionAjedrez((byte)5,'d'), new PosicionAjedrez((byte)5,'e'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			juego.mover(new PosicionAjedrez((byte)1,'f'), new PosicionAjedrez((byte)2,'e'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
				juego.revertir();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}		

	}
}
