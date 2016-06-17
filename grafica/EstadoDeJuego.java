package grafica;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;

public class EstadoDeJuego extends Pane implements Dimensiones {
	
	
	private Canvas img;
	private Juego juego;
	private Button enroqueLargo;
	private Button enroqueCorto;

	
	public EstadoDeJuego(Juego juego,Button enroqueLargo, Button enroqueCorto){
		this.juego = juego;
		this.enroqueCorto = enroqueCorto;
		this.enroqueLargo = enroqueLargo;
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
			if(MensajeAhogado.display()){
				((Stage)(this.getScene().getWindow())).setScene(new Scene(new Inicio()));
			}
		}
		if(juego.hayJaqueMate()){
			this.getChildren().remove(img);
			img = new MyCanvas(new Image("/assets/jaqueMate.png"),0,0,EST_ANCHO,EST_ALTO);
			this.getChildren().add(img);
			if(MensajeGanaste.display(juego.dameTurno())){
				((Stage)(this.getScene().getWindow())).setScene(new Scene(new Inicio()));
			}
		}
		
		if(juego.sePuedeEnrocarCorto()){
			enroqueCorto.getStyleClass().add("show");
		}else{
			enroqueCorto.getStyleClass().remove("show");
		}
		
		if(juego.sePuedeEnrocarLargo()){
			enroqueLargo.getStyleClass().add("show");
		}else{
			enroqueLargo.getStyleClass().remove("show");
		}
		
	}
	
}
