package interfaz;


import java.util.Set;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import logica.Color;
import logica.Juego;
import logica.Jugada;
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
	PiezaImagen imagenes;
	
	public TableroPantallaJuego(Juego elJuego){
		super();
		this.setPrefSize(TABLERO_ANCHO, TABLERO_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);
		
		this.elJuego=elJuego;
		seleccionado=null;
		imagenes=new PiezaImagen();
		inicializarTablero();
		imprimirTablero();
		this.setOnMouseClicked(e-> posicionTablero(e.getSceneX(),e.getSceneY()));
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		this.setId("tablero");
		
	}
	
	public void deshacerJugada(){
		elJuego.revertir();
		imprimirTablero();
	}
	
	private void  dibujarImagen(Image img, int fila, int col){
		tablero[col][fila].getGraphicsContext2D().drawImage(img, 1.25, 1.25,60,60);//poner variables static
	}
	
	private void  dibujarImagen(Image img,PosicionTablero pos){
		dibujarImagen(img,pos.getFila(),pos.getColumna());
	}
	
	private void dibujarPieza(PiezaColor pieza,PosicionAjedrez pos){
		//System.out.println("Click en FilaAjedrez: "+fila+ "ColumnaTablero: "+col);
		dibujarImagen(imagenes.dameImagen(pieza),transformar(pos));//dibuja solo peones blancos
	}
	
	private void borrarImagen(int fila, int col){
		this.getChildren().remove(tablero[col][fila]);
		tablero[col][fila]=new Canvas(CASILLERO_ANCHO,CASILLERO_ALTO);
		tablero[col][fila].setTranslateX(CASILLERO_ANCHO*col);
		tablero[col][fila].setTranslateY(CASILLERO_ALTO*fila);

		this.getChildren().add(tablero[col][fila]);
		
	}
	
	private void borrarImagen(PosicionTablero pos){
		borrarImagen(pos.getFila(),pos.getColumna());
	}

	private void borrarPieza(PosicionAjedrez pos){
		borrarImagen(transformar(pos));
	}
	
	private void posicionTablero(double x, double y){
		System.out.println("");
		System.out.println("x:"+x+" y: "+y);
		int fila= (int) ((y-DEFSAJE_Y)/CASILLERO_ALTO);
		int columna= (int) ((x-DEFSAJE_X)/CASILLERO_ANCHO);
		System.out.println("Click en Fila: "+fila+ "Columna: "+columna);
		clickTablero(transformar(fila, columna));
		
		
	}
	
	private PosicionAjedrez transformar(int fila, int columna){
		byte laFila = (byte) (8 - fila);
		char laColumna = (char) (columna + 'a');
		return new PosicionAjedrez(laFila, laColumna);
	}
	
	private PosicionTablero transformar(PosicionAjedrez posAjedrez) {//anoto FUERTE que hay que cmabiar esto
		byte fila = posAjedrez.dameFila();
		char columna = posAjedrez.dameColumna();
		int laFila = 8 - fila;
		int laColumna = columna - 'a';
		return new PosicionTablero(laFila, laColumna);
	}
	
	private void clickTablero(PosicionAjedrez clickeado){
		turno=elJuego.dameTurno();
		System.out.println("turno de:"+turno);
		if(seleccionado == null) {
			if(!elJuego.hayAlgo(clickeado)|| elJuego.queHay(clickeado).dameColor()!=turno) {
				return;
			}
			seleccionado=clickeado;
			System.out.println("se selecciono un casillero "+seleccionado);//TESTTTT
			movimientosPosibles=elJuego.dameMovimientos(seleccionado);
			System.out.println(movimientosPosibles);//borrarrrrrr
			//pintar casilleros
		}else{
			if(!elJuego.hayAlgo(clickeado)|| elJuego.queHay(clickeado).dameColor()!=turno){
				if(movimientosPosibles.contains(clickeado)){
					Jugada laJugada = elJuego.mover(seleccionado, clickeado);
					imprimirTablero();
					seleccionado=null;
					if(elJuego.hayJaqueMate()){
						System.out.println("JAQUE MATE gana Jugador "+elJuego.dameTurno());
					}
					System.out.println("Se movio una pieza");///////borrrar
				}
			}else{
				seleccionado=clickeado;
				movimientosPosibles=elJuego.dameMovimientos(seleccionado);
				//pintar casilleros
			}
		}
	}
	
	private void imprimirTablero(){//poner variabler static
		for(byte i=1;i<=8;i++){
			for(char j='a';j<='h';j++){
				PosicionAjedrez pos= new PosicionAjedrez(i, j);
				borrarPieza(pos);
				if(elJuego.hayAlgo(pos)){
					dibujarPieza(elJuego.queHay(pos),pos);
				}
			}
		}
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