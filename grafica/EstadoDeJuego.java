package grafica;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import logica.ColorPieza;
import logica.Juego;

public class EstadoDeJuego extends Pane implements Dimensiones {
	
	
	private Canvas img;
	private Juego juego;

	
	public EstadoDeJuego(Juego juego){
		this.juego = juego;
		this.setPrefSize(EST_ANCHO, EST_ALTO);
		this.setTranslateX(DES_ESTADO_X);
		this.setTranslateY(DES_ESTADO_Y);	

		img = null;
		
		actualizarEstado();
	}
	
	public void actualizarEstado(){	
		if(juego.dameTurno() == ColorPieza.BLANCO){
			this.getChildren().removeAll(img);
			img = new MyCanvas(new Image("/assets/juegaBlancas.png"),0,0,EST_ANCHO,EST_ALTO);
			this.getChildren().addAll(img);
		}else{
			this.getChildren().removeAll(img);
			img = new MyCanvas(new Image("/assets/juegaNegras.png"),0,0,EST_ANCHO,EST_ALTO);
			this.getChildren().addAll(img);
		}
		if(juego.hayAlgoParaCoronar()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/coronacion.png"),0,0,EST_ANCHO,EST_ALTO);
			this.getChildren().add(img);
		}
		if(juego.hayJaque()){
			if(juego.dameTurno() == ColorPieza.BLANCO){
				this.getChildren().remove(img);
				img = new MyCanvas(new Image("/assets/jaqueBlancas.png"),0,0,EST_ANCHO,EST_ALTO);
				this.getChildren().add(img);
			}else{
				this.getChildren().remove(img);
				img = new MyCanvas(new Image("/assets/jaqueNegras.png"),0,0,EST_ANCHO,EST_ALTO);
				this.getChildren().add(img);
			}
		}
		if(juego.hayAhogado()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/ahogado.png"),0,0,EST_ANCHO,EST_ALTO);
			this.getChildren().add(img);
		}
		if(juego.hayJaqueMate()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/jaqueMate.png"),0,0,EST_ANCHO,EST_ALTO);
			this.getChildren().add(img);
		}
	}
	
}
