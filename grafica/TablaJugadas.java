package grafica;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import logica.ColorPieza;
import logica.Jugada;

/**
 * TablaJugadas es el panel que muestra en pantalla las ultimas diez jugadas 
 * que se hicieronConsta de un array que guarda las {@link Jugada}s del Juego,
 * y vectores de Label y canvas para dibujar las imagenes;
 *
 */
public class TablaJugadas extends Pane implements Dimensiones{
	
	private ArrayList<Jugada> jugadas;
	private MyLabel label[],number[];
	private PiezaImagen imagenes;
	private MyCanvas canvas[],comidas[];
	private int fila;
	
	/**
	 * Constructor que incializa variables
	 */
	public TablaJugadas(){
		this.setPrefSize(JUGADAS_ANCHO, JUGADS_ALTO);
		this.setTranslateX(DES_JUGADAS_X);
		this.setTranslateY(DES_JUGADAS_Y);
		this.getStyleClass().add("tablaJugadas");
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		jugadas= new ArrayList<>();
		imagenes= new PiezaImagen();
		canvas = new MyCanvas[TABLA_JUGADAS];
		comidas = new MyCanvas[TABLA_JUGADAS];
		label= new MyLabel[TABLA_JUGADAS];
		number = new MyLabel[TABLA_JUGADAS];
		fila=0;
	}
	/**
	 * Guarda la jugada en el array e imprime la tabla
	 * @param jugada a guardar
	 */
	public void agregarJugada(Jugada jugada){
		jugadas.add(jugada);
		imprimrJugada();
	}
	
	/**
	 * Remueve la ultima jugada del array y borra la ultima jugada impresa
	 */
	public void removerJugada(){
		if(!jugadas.isEmpty()){
			jugadas.remove(jugadas.size()-1);
			fila--;
			this.getChildren().removeAll(number[fila],label[fila],canvas[fila],comidas[fila]);
			imprimrJugada();
		}
		
	}
	
	/**
	 *  Imprime las ultimas diez jugadas en dos columnas diferenciando en una columna
	 *  piezas negras y otra piezas blancas.
	 */
	public void imprimrJugada(){
		int index;
		if(jugadas.size()<TABLA_JUGADAS){
			index=0;
		}else{
			index=jugadas.size()-TABLA_JUGADAS;
		}
		
		for(fila=0; fila<jugadas.size() && fila<TABLA_JUGADAS;fila++, index++){
			Jugada jugada=jugadas.get(index);
			int desfasajeX = 0, desfasajeY = fila/2;
			if(jugada.damePiezaColorMovida().dameColor() == ColorPieza.NEGRO){
				desfasajeX = 120;
			}

			this.getChildren().removeAll(number[fila],label[fila],canvas[fila],comidas[fila]);
			canvas[fila]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorMovida()),28+desfasajeX,30*desfasajeY + 5,18,18);
			if(jugada.hayPiezaComida()){
				comidas[fila]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorComida()),100+desfasajeX,30*desfasajeY + 5,18,18);
				this.getChildren().add(comidas[fila]);
			}
			number[fila]=new MyLabel((index+1)+".",10+desfasajeX,30*desfasajeY + 5,20,20);
			label[fila]=new MyLabel(jugada.damePosicionOrigen()+" - "+jugada.damePosicionDestino(),50+desfasajeX,30*desfasajeY + 5,80,20);
			this.getChildren().addAll(number[fila],label[fila],canvas[fila]);
		}
	}
}
