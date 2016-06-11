package interfaz;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import logica.Jugada;

public class TablaJugadas extends Pane{
	private static double JUGADAS_ANCHO=250;
	public static double JUGADS_ALTO=350;
	
	private static double DEFSAJE_X=100+500+50;
	private static double DEFSAJE_Y=150+150;
	private ArrayList<Jugada> jugadas;
	private MyLabel label[];
	private PiezaImagen imagenes;
	private MyCanvas canvas[];
	private MyCanvas comidas[];
	
	public TablaJugadas(){
		this.setPrefSize(JUGADAS_ANCHO, JUGADS_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);	
		jugadas= new ArrayList<>();
		imagenes= new PiezaImagen();
		canvas = new MyCanvas[10];
		comidas = new MyCanvas[10];
		label= new MyLabel[10];
	}
	
	public void agregarJugada(Jugada jugada){
		jugadas.add(jugada);
		if(jugadas.size()==11){
			jugadas.remove(0);
		}
		imprimirJugadas();
	}
	
	
	public void imprimirJugadas(){
		for (int i=0; i<jugadas.size(); i++){
			Jugada jugada=jugadas.get(i);
			this.getChildren().removeAll(label[i],canvas[i],comidas[i]);
			canvas[i]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorMovida()),55,30*i,30,30);
			if(jugada.hayPiezaComida()){
				comidas[i]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorComida()),180,30*i,30,30);
				this.getChildren().add(comidas[i]);
			}
			label[i]=new MyLabel(jugada.damePosicionOrigen()+" - "+jugada.damePosicionDestino(),95,30*i,80,30);
			this.getChildren().addAll(label[i],canvas[i]);
		}
	}
}
