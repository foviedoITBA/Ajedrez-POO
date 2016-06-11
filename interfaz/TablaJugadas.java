package interfaz;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import logica.Jugada;

public class TablaJugadas extends Pane{
	private static double JUGADAS_ANCHO=250;
	public static double JUGADS_ALTO=350;
	
	private static double DEFSAJE_X=100+500+5;
	private static double DEFSAJE_Y=150+500+50;
	private ArrayList<Jugada> jugadas;
	private ArrayList<MyLabel> tablaJugadas;
	
	
	public TablaJugadas(){
		this.setPrefSize(JUGADAS_ANCHO, JUGADS_ALTO);
		//this.setTranslateX(DEFSAJE_X);
		//this.setTranslateY(DEFSAJE_Y);
		tablaJugadas= new ArrayList<MyLabel>();	
		imprimirJugadas();
	}
	
	public void agregarJugada(Jugada jugada){
		jugadas.add(jugada);
		if(jugadas.size()==10){
			jugadas.remove(0);
		}
	}
	
	public void imprimirJugadas(){
		for (int i=0; i<10; i++){
			MyLabel jugada=new MyLabel("Jugada"+i,95,30*i,100,30);
			this.getChildren().add(jugada);
		}
	}
}
