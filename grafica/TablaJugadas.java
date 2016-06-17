package grafica;

import java.util.ArrayList;

import interfaz.MyCanvas;
import interfaz.MyLabel;
import interfaz.PiezaImagen;
import javafx.scene.layout.Pane;
import logica.ColorPieza;
import logica.Jugada;

public class TablaJugadas extends Pane implements Dimensiones{
	
	private ArrayList<Jugada> jugadas;
	private MyLabel label[],number[];
	private PiezaImagen imagenes;
	private MyCanvas canvas[],comidas[];
	private int fila;
	
	public TablaJugadas(){
		this.setPrefSize(JUGADAS_ANCHO, JUGADS_ALTO);
		this.setTranslateX(DES_JUGADAS_X);
		this.setTranslateY(DES_JUGADAS_Y);
		this.getStyleClass().add("tablaJugadas");
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		jugadas= new ArrayList<>();
		imagenes= new PiezaImagen();
		canvas = new MyCanvas[10];
		comidas = new MyCanvas[10];
		label= new MyLabel[10];
		number = new MyLabel[10];
		fila=0;
	}
	
	public void agregarJugada(Jugada jugada){
		jugadas.add(jugada);
		imprimrJugada();
	}
	
	public void removerJugada(){
		if(!jugadas.isEmpty()){
			jugadas.remove(jugadas.size()-1);
			fila--;
			this.getChildren().removeAll(number[fila],label[fila],canvas[fila],comidas[fila]);
			imprimrJugada();
		}
		
	}
	
	public void imprimrJugada(){
		int index;
		if(jugadas.size()<10){
			index=0;
		}else{
			index=jugadas.size()-10;
		}
		
		for(fila=0; fila<jugadas.size() && fila<10;fila++, index++){
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
