package grafica;

import java.util.Set;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logica.Juego;
import logica.PiezaColor;
import logica.PosicionAjedrez;

public class Tablero extends Pane implements Dimensiones {
	private Canvas[][] tablero;
	private PiezaImagen imagenes;
	private Juego elJuego;
	
	public Tablero(Juego elJuego){
		
		this.setPrefSize(TABLERO_ANCHO, TABLERO_ALTO);
		this.setTranslateX(DES_TABLERO_X);
		this.setTranslateY(DES_TABLERO_Y);
		
		this.elJuego=elJuego;
		tablero= new Canvas[TABLERO_COLUMNAS][TABLERO_FILAS];
		imagenes=new PiezaImagen();
		imprimirTablero();


		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		this.setId("tablero");
	}
	
	public void pintarCasilleros(Set<PosicionAjedrez> movimientosPosibles){
		for(PosicionAjedrez pos: movimientosPosibles){
			pintarCasilleros(transformar(pos));
		}
	}

	private void pintarCasilleros(PosicionTablero pos){
		GraphicsContext gc;
		gc = tablero[pos.getColumna()][pos.getFila()].getGraphicsContext2D();
		if(elJuego.hayAlgo(transformar(pos.getFila(),pos.getColumna()))){
			gc.setStroke(Color.RED);
		}else{
			gc.setStroke(Color.GREEN);
		}
		gc.setLineWidth(8);
		gc.strokeRect(0,0,CASILLERO_ANCHO,CASILLERO_ALTO);
	}
	
	public void imprimirTablero() {
		for (byte i = 1; i <= TABLERO_FILAS; i++) {
			for (char j = 'a'; j <= 'h'; j++) {
				PosicionAjedrez pos = new PosicionAjedrez(i, j);
				borrarPieza(pos);
				if(elJuego.hayAlgo(pos)){
					dibujarPieza(elJuego.queHay(pos),pos);
				}
			}
		}
	}
	
	private PosicionAjedrez transformar(int fila, int columna){
		byte laFila = (byte) (TABLERO_FILAS - fila);
		char laColumna = (char) (columna + 'a');
		return new PosicionAjedrez(laFila, laColumna);
	}

	private PosicionTablero transformar(PosicionAjedrez posAjedrez) {
		byte fila = posAjedrez.dameFila();
		char columna = posAjedrez.dameColumna();
		int laFila = TABLERO_FILAS - fila;
		int laColumna = columna - 'a';
		return new PosicionTablero(laFila, laColumna);
	}
	
	private void  dibujarImagen(Image img,PosicionTablero pos){
		tablero[pos.getColumna()][pos.getFila()].getGraphicsContext2D().drawImage(img, 1.25, 1.25,60,60);
	}

	private void dibujarPieza(PiezaColor pieza,PosicionAjedrez pos){
		dibujarImagen(imagenes.dameImagen(pieza),transformar(pos));
	}

	private void borrarImagen(PosicionTablero pos){
		int fila= pos.getFila();
		int col= pos.getColumna();

		this.getChildren().remove(tablero[col][fila]);
		tablero[col][fila]=new Canvas(CASILLERO_ANCHO,CASILLERO_ALTO);
		tablero[col][fila].setTranslateX(CASILLERO_ANCHO*col);
		tablero[col][fila].setTranslateY(CASILLERO_ALTO*fila);

		this.getChildren().add(tablero[col][fila]);
	}

	private void borrarPieza(PosicionAjedrez pos){
		borrarImagen(transformar(pos));
	}
	

}
