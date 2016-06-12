package interfaz;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import logica.Jugada;

public class TablaJugadas extends Pane{
	private static double JUGADAS_ANCHO=250;
	public static double JUGADS_ALTO=150;
	
	private static double DEFSAJE_X=100+500+20;
	private static double DEFSAJE_Y=150+150+40;
	private ArrayList<Jugada> jugadas;
	private MyLabel label[],number[];
	private PiezaImagen imagenes;
	private MyCanvas canvas[];
	private MyCanvas comidas[];
	private int cantJugadas;
	
	public TablaJugadas(){
		this.setPrefSize(JUGADAS_ANCHO, JUGADS_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);
		this.getStyleClass().add("tablaJugadas");
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		jugadas= new ArrayList<>();
		imagenes= new PiezaImagen();
		canvas = new MyCanvas[10];
		comidas = new MyCanvas[10];
		label= new MyLabel[10];
		number = new MyLabel[10];
		cantJugadas = 0;
	}
	
	public void agregarJugada(Jugada jugada){
		jugadas.add(jugada);
		cantJugadas++;
		if(jugadas.size()==11){
			jugadas.remove(0);
		}
		imprimirJugadas();
	}
	
	public void removerJugada(Jugada jugada){
		jugadas.remove(jugadas.size()-1);
	}
	
	public void imprimirJugadas(){
		for (int i=0; i<jugadas.size(); i++){
			int desfasajeX = 0, desfasajeY = i/2;
			if(i==0){
				desfasajeY = i/2;
			}
			if(i%2!=0){//agrego en segunda columna
				desfasajeX = 120;
				desfasajeY = (i-1)/2;
			}
			
			Jugada jugada=jugadas.get(i);
			this.getChildren().removeAll(number[i],label[i],canvas[i],comidas[i]);
			canvas[i]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorMovida()),28+desfasajeX,30*desfasajeY + 5,18,18);
			if(jugada.hayPiezaComida()){
				comidas[i]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorComida()),100+desfasajeX,30*desfasajeY + 5,18,18);
				this.getChildren().add(comidas[i]);
			}
			//number[i]=new MyLabel((cantJugadas-i)+".",10+desfasajeX,30*desfasajeY + 5,20,20);
			label[i]=new MyLabel(jugada.damePosicionOrigen()+" - "+jugada.damePosicionDestino(),50+desfasajeX,30*desfasajeY + 5,80,20);
			this.getChildren().addAll(/*number[i],*/label[i],canvas[i]);
		}
	}
}
