package logica;

public class Test {
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		
		
		juego.mover(new PosicionAjedrez((byte)2,'e'), new PosicionAjedrez((byte)4,'e'));	
		juego.mover(new PosicionAjedrez((byte)7,'d'), new PosicionAjedrez((byte)5,'d'));
		juego.mover(new PosicionAjedrez((byte)4,'e'), new PosicionAjedrez((byte)5,'d'));
		juego.mover(new PosicionAjedrez((byte)8,'d'), new PosicionAjedrez((byte)5,'d'));
		juego.mover(new PosicionAjedrez((byte)1,'b'), new PosicionAjedrez((byte)3,'c'));
		juego.mover(new PosicionAjedrez((byte)5,'d'), new PosicionAjedrez((byte)5,'e'));
		juego.mover(new PosicionAjedrez((byte)1,'f'), new PosicionAjedrez((byte)2,'e'));
		juego.revertir();

	}
}
