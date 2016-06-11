package interfaz;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import logica.Color;
import logica.Juego;

public class EstadoDeJuego extends Pane {
	
	private static double IMAGEN_ANCHO=200;
	public static double IMAGEN_ALTO=137;
	
	private static double DEFSAJE_X=100+500+50;
	private static double DEFSAJE_Y=150;
	
	private Canvas img;
	private Juego juego;
	
	public EstadoDeJuego(Juego juego){
		this.juego = juego;
		this.setPrefSize(IMAGEN_ANCHO, IMAGEN_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);	

		img = null;
		
		actualizarEstado();
	}
	
	public void actualizarEstado(){
		
		System.out.println("aca");
		if(juego.dameTurno() == Color.BLANCO){
			System.out.println("aca2");
			this.getChildren().removeAll(img);
			img = new MyCanvas(new Image("/assets/juegaBlancas.png"),0,0,IMAGEN_ANCHO,IMAGEN_ALTO);
			this.getChildren().addAll(img);
		}else{
			System.out.println("aca3");
			this.getChildren().removeAll(img);
			img = new MyCanvas(new Image("/assets/juegaNegras.png"),0,0,IMAGEN_ANCHO,IMAGEN_ALTO);
			this.getChildren().addAll(img);
		}
		if(juego.hayAlgoParaCoronar()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/juegaBlancas.png"),0,0,IMAGEN_ANCHO,IMAGEN_ALTO);
			this.getChildren().add(img);
		}
		if(juego.hayJaque()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/juegaBlancas.png"),0,0,IMAGEN_ANCHO,IMAGEN_ALTO);
			this.getChildren().add(img);
		}
		if(juego.hayAhogado()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/juegaBlancas.png"),0,0,IMAGEN_ANCHO,IMAGEN_ALTO);
			this.getChildren().add(img);
		}
		if(juego.hayJaqueMate()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/juegaBlancas.png"),0,0,IMAGEN_ANCHO,IMAGEN_ALTO);
			this.getChildren().add(img);
		}
	}
	
}
