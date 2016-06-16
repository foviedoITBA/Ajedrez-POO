package interfaz;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import logica.Jugada;

public class TablaJugadas extends Pane{
	private static double JUGADAS_ANCHO=250;
	public static double JUGADS_ALTO=150;

	private static double DEFSAJE_X=100+500+20;
	private static double DEFSAJE_Y=150+150+20;
	private ArrayList<Jugada> jugadas;
	//	private ArrayList<MyLabel> label,number;
	//	private PiezaImagen imagenes;
	//	private ArrayList<MyCanvas> canvas,comidas;
	private MyLabel label[],number[];
	private PiezaImagen imagenes;
	private MyCanvas canvas[],comidas[];
	private int cantJugadas;

	public TablaJugadas(){
		this.setPrefSize(JUGADAS_ANCHO, JUGADS_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);
		this.getStyleClass().add("tablaJugadas");
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		jugadas= new ArrayList<>();
		imagenes= new PiezaImagen();
		//		canvas = new ArrayList<MyCanvas>();
		//		comidas = new ArrayList<MyCanvas>();
		//		label= new ArrayList< MyLabel>();
		//		number = new ArrayList<MyLabel>();
		canvas = new MyCanvas[10];
		comidas = new MyCanvas[10];
		label= new MyLabel[10];
		number = new MyLabel[10];
		cantJugadas = 0;
	}

	public void agregarJugada(Jugada jugada){
		jugadas.add(jugada);
		cantJugadas++;
		imprimirJugadas();
	}

	public void removerJugada(){
		if(cantJugadas>0){
			int i;
			System.out.println(cantJugadas);
			if(cantJugadas<11){
				i=cantJugadas-1;
			}else{
				i=cantJugadas-11;
			}
			this.getChildren().removeAll(number[i],label[i],canvas[i],comidas[i]);		
			cantJugadas--;
			jugadas.remove(cantJugadas-1);
		}
		//this.getChildren().removeAll(number.get(i),label.get(i),canvas.get(i),comidas.get(i),number.get(i-1),label.get(i-1),canvas.get(i-1),comidas.get(i-1));
		//			jugadas.remove(jugadas.size()-1);
		//			imprimirJugadas();
	}
	//imprimirJugadas();


	public void imprimirJugadas(){
		int j;
		if(cantJugadas>10){
			j=cantJugadas-10;
		}else{
			j=0;
		}
		for (int i=0,z=j; i<cantJugadas; i++,z++){
			int desfasajeX = 0, desfasajeY = i/2;
			if(i%2 == 0){
				desfasajeY = i/2;
			}
			if(i%2 != 0){//agrego en segunda columna
				desfasajeX = 120;
				desfasajeY = (i-1)/2;
			}

			Jugada jugada=jugadas.get(z);
			this.getChildren().removeAll(number[i],label[i],canvas[i],comidas[i]);
			canvas[i]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorMovida()),28+desfasajeX,30*desfasajeY + 5,18,18);
			if(jugada.hayPiezaComida()){
				comidas[i]= new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorComida()),100+desfasajeX,30*desfasajeY + 5,18,18);
				this.getChildren().add(comidas[i]);
			}
			//number[i]=new MyLabel((cantJugadas-i)+".",10+desfasajeX,30*desfasajeY + 5,20,20);
			label[i]=new MyLabel(jugada.damePosicionOrigen()+" - "+jugada.damePosicionDestino(),50+desfasajeX,30*desfasajeY + 5,80,20);
			this.getChildren().addAll(/*number[i],*/label[i],canvas[i]);

			//this.getChildren().removeAll(number.get(i),label.get(i),canvas.get(i),comidas.get(i));
			//			canvas.set(z, new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorMovida()),28+desfasajeX,30*desfasajeY + 5,18,18)); 
			//			if(jugada.hayPiezaComida()){
			//				comidas.set(z, new MyCanvas(imagenes.dameImagen(jugada.damePiezaColorComida()),100+desfasajeX,30*desfasajeY + 5,18,18)); 
			//				this.getChildren().add(comidas.get(z));
			//			}
			//			label.set(z, new MyLabel(jugada.damePosicionOrigen()+" - "+jugada.damePosicionDestino(),50+desfasajeX,30*desfasajeY + 5,80,20));
			//			this.getChildren().addAll(label.get(z),canvas.get(z));


		}
	}
}
