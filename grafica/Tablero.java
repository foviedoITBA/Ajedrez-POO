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
/**
 * La clase Tablero es la encargada de represntar el tabelero de ajedrez graficamente.
 * Consulta con el juego que hay en cada casillero y grafica una pieza cuando corresponda.
 * Tiene una matriz de el tamaño de un tablero de ajedrez (8x8), en el cual se dibjan todas
 * las pieza. Una {@link PiezaImagen} para poder transformar una {@link PiezaColor} a su
 * imagen. Una referencia al {@link Juego} para poder consultar que hay en cada casillero.
 */
public class Tablero extends Pane implements Dimensiones {
	private final Canvas[][] tablero;
	private final PiezaImagen imagenes;
	private final Juego elJuego;
	
	/**
	 *  Constructor de tablero que recibe como parametro al Juego e inicializa
	 *  el Juego la matriz y la {@link PiezaImagen}
	 * @param elJuego inicializa la variable local elJuego
	 */
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
	
	/**
	 * Metodo encargado de colorear los casilleros a los cuales se puede mover una pieza
	 * caundo es seleccionada 
	 * @param movimientosPosibles Set con las posiciones del tablero que se deben colorear
	 */
	public void pintarCasilleros(Set<PosicionAjedrez> movimientosPosibles){
		for(PosicionAjedrez pos: movimientosPosibles){
			pintarCasilleros(transformar(pos));
		}
	}
	
	/**
	 * Metodo que recibe una posicon y colorea un rectangulo rojo si hay una pieza
	 * o uno verde sino.
	 * @param pos {@link PosicionTablero} que se debe pintar
	 */

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
	
	/**
	 * Metodo encargado  de consultar con el {@link Juego} que hay en cada
	 * casillero, borra la pieza que hay actualmente en el casillero y 
	 * dibuja la que el Juego le dice que hay.
	 */
	
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
	
	/**
	 * transforma de una {@PosicionTablero} a una {PosicionAjedrez}
	 * @param fila del tablero
	 * @param columna del tablero
	 * @return Posicion de tablero equivalente a la posicion de entrada
	 */
	private PosicionAjedrez transformar(int fila, int columna){
		byte laFila = (byte) (TABLERO_FILAS - fila);
		char laColumna = (char) (columna + 'a');
		return new PosicionAjedrez(laFila, laColumna);
	}
	
	/**
	 * transforma de una {@link PosicionAjedrez} a una {@link PosicionTablero}
	 * @param posAjedrez
	 * @return
	 */
	private PosicionTablero transformar(PosicionAjedrez posAjedrez) {
		byte fila = posAjedrez.dameFila();
		char columna = posAjedrez.dameColumna();
		int laFila = TABLERO_FILAS - fila;
		int laColumna = columna - 'a';
		return new PosicionTablero(laFila, laColumna);
	}
	
	
	/**
	 * Dada una pieza y una posicion dibuja en el canvas correspondiente al tablero
	 * su la imagen de la pieza 
	 * @param pieza a dibujar
	 * @param posicion donde dibujar
	 */
	private void dibujarPieza(PiezaColor pieza,PosicionAjedrez pos){
		Image img=imagenes.dameImagen(pieza);
		PosicionTablero posicion=transformar(pos);
		tablero[posicion.getColumna()][posicion.getFila()].getGraphicsContext2D().drawImage(img, 1.25, 1.25,60,60);

	}
	
	/**
	 * Borra de la posicion dada la pieza que tenga
	 * @param posicion a borrar pieza
	 */

	private void borrarPieza(PosicionAjedrez pos){
		int fila= transformar(pos).getFila();
		int col= transformar(pos).getColumna();

		this.getChildren().remove(tablero[col][fila]);
		tablero[col][fila]=new Canvas(CASILLERO_ANCHO,CASILLERO_ALTO);
		tablero[col][fila].setTranslateX(CASILLERO_ANCHO*col);
		tablero[col][fila].setTranslateY(CASILLERO_ALTO*fila);

		this.getChildren().add(tablero[col][fila]);
	}
	

}
