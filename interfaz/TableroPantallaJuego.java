package interfaz;


import java.util.Set;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import logica.Color;
import logica.Juego;
import logica.PiezaColor;
import logica.PosicionAjedrez;

public class TableroPantallaJuego extends Pane {
	
	private static double TABLERO_ANCHO=500;
	private static double TABLERO_ALTO=500;
	
	private static double CASILLERO_ALTO=62.5;
	private static double CASILLERO_ANCHO=62.5;
	
	private static double DEFSAJE_X=100;
	private static double DEFSAJE_Y=150;
	private Canvas[][] tablero= new Canvas[8][8];
	
	Color turno;
	PosicionAjedrez seleccionado;
	Set<PosicionAjedrez> movimientosPosibles;
	Juego elJuego;
	
	public TableroPantallaJuego(Juego elJuego){
		super();
		this.setPrefSize(TABLERO_ANCHO, TABLERO_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);
		
		this.elJuego=elJuego;
		inicializarTablero();
		inicializarPiezas();
		this.setOnMouseClicked(e-> posicionTablero(e.getSceneX(),e.getSceneY()));
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		this.setId("tablero");
		
	}
	
	public void  dibujarImagen(Image img, int fila, int col){
		tablero[col][fila].getGraphicsContext2D().drawImage(img, 1.25, 1.25,60,60);//poner variables static
	}
	
	public void dibujarPieza(PiezaColor pieza,PosicionAjedrez pos){
		int fila=9-pos.dameFila();
		int col=pos.dameColumna()-'a'+1;
		System.out.println("Click en FilaAjedrez: "+fila+ "ColumnaTablero: "+col);
		dibujarImagen(new Image("/assets/peonBlanco.png"),fila,col);
	}
	
	public void borrarImagen(int fila, int col){
		this.getChildren().remove(tablero[col][fila]);
		tablero[col][fila]=new Canvas(CASILLERO_ANCHO,CASILLERO_ALTO);
		tablero[col][fila].setTranslateX(CASILLERO_ANCHO*col);
		tablero[col][fila].setTranslateY(CASILLERO_ALTO*fila);

		this.getChildren().add(tablero[col][fila]);
		
	}

	public void borrarPieza(PosicionAjedrez pos){
		int fila=9-pos.dameFila();
		int col=pos.dameColumna()-'a'+1;
		borrarImagen(fila, col);
	}
	
	public void posicionTablero(double x, double y){
		System.out.println("x:"+x+" y: "+y);
		int fila= (int) ((y-DEFSAJE_Y)/CASILLERO_ALTO)+1;
		int columna= (int) ((x-DEFSAJE_X)/CASILLERO_ANCHO)+1;
		
		byte filAjedrez=(byte)(9-fila);
		char colAjedrez=(char) ('a'+columna-1);
		System.out.println("Click en Fila: "+fila+ "Columna: "+columna);
		System.out.println("Click en FilaAjedrez: "+filAjedrez+ "ColumnaAjedrez: "+colAjedrez);
		clickTablero(new PosicionAjedrez(filAjedrez,colAjedrez));
		
		
	}
	
	public void clickTablero(PosicionAjedrez clickeado){
		turno=elJuego.dameTurno();
		if(seleccionado == null) {
			if(!elJuego.hayAlgo(clickeado)|| elJuego.queHay(clickeado).dameColor()!=turno) {
				return;
			}
			seleccionado=clickeado;
			movimientosPosibles=elJuego.dameMovimientos(seleccionado);
			//pintar casilleros
		}else{
			if(!elJuego.hayAlgo(clickeado)|| elJuego.queHay(clickeado).dameColor()!=turno){
				if(movimientosPosibles.contains(clickeado)){
					elJuego.mover(seleccionado, clickeado);
					dibujarPieza(elJuego.queHay(seleccionado),clickeado);
					borrarPieza(seleccionado);
					seleccionado=null;
				}
			}
			else{
				seleccionado=clickeado;
				movimientosPosibles=elJuego.dameMovimientos(seleccionado);
				//pintar casilleros
			}
		}
	}
	
	private void inicializarPiezas(){
		for(int i=0; i<8 ;i++){ 
			dibujarImagen(new Image("/assets/peonNegro.png"),1,i);
			dibujarImagen(new Image("/assets/peonBlanco.png"),6,i);
		}
			
		dibujarImagen(new Image("/assets/torreNegro.png"),0,0);
		dibujarImagen(new Image("/assets/torreNegro.png"),0,7);
		
		dibujarImagen(new Image("/assets/torreBlanco.png"),7,0);
		dibujarImagen(new Image("/assets/torreBlanco.png"),7,7);
		
		dibujarImagen(new Image("/assets/caballoBlanco.png"),7,1);
		dibujarImagen(new Image("/assets/caballoBlanco.png"),7,6);
		
		dibujarImagen(new Image("/assets/caballoNegro.png"),0,1);
		dibujarImagen(new Image("/assets/caballoNegro.png"),0,6);
		
		dibujarImagen(new Image("/assets/alfilNegro.png"),0,2);
		dibujarImagen(new Image("/assets/alfilNegro.png"),0,5);
		
		dibujarImagen(new Image("/assets/alfilBlanco.png"),7,2);
		dibujarImagen(new Image("/assets/alfilBlanco.png"),7,5);
		
		dibujarImagen(new Image("/assets/reyBlanco.png"),7,4);
		dibujarImagen(new Image("/assets/damaBlanco.png"),7,3);
		
		dibujarImagen(new Image("/assets/reyNegro.png"),0,4);
		dibujarImagen(new Image("/assets/damaNegro.png"),0,3);
		
		
	}

	private void inicializarTablero(){
		for(int i=0; i<8;i++){//hay que usar un enum o algo que diga ancho ya alto del tablero en vez de 8
			for(int j=0; j<8;j++){
				tablero[i][j]=new Canvas(CASILLERO_ANCHO,CASILLERO_ALTO);
				tablero[i][j].setTranslateX(CASILLERO_ANCHO*i);
				tablero[i][j].setTranslateY(CASILLERO_ALTO*j);

				this.getChildren().add(tablero[i][j]);
			}
		}
	}
}