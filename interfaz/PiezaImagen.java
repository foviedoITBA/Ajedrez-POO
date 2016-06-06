package interfaz;

import java.util.HashMap;

import javafx.scene.image.Image;
import logica.Color;
import logica.NombrePieza;
import logica.PiezaColor;

public class PiezaImagen {
	HashMap<PiezaColor,Image> imagenes;
	public PiezaImagen(){
		imagenes=new HashMap<>();
		imagenes.put(new PiezaColor(NombrePieza.PEON,Color.BLANCO),new Image("/assets/peonBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.PEON,Color.NEGRO),new Image("/assets/peonNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.ALFIL,Color.BLANCO),new Image("/assets/alfilBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.ALFIL,Color.NEGRO),new Image("/assets/alfilNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.CABALLO,Color.BLANCO),new Image("/assets/caballoBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.CABALLO,Color.NEGRO),new Image("/assets/caballoNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.TORRE,Color.BLANCO),new Image("/assets/torreBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.TORRE,Color.NEGRO),new Image("/assets/torreNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.DAMA,Color.BLANCO),new Image("/assets/damaBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.DAMA,Color.NEGRO),new Image("/assets/damaNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.REY,Color.BLANCO),new Image("/assets/reyBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.REY,Color.NEGRO),new Image("/assets/reyNegro.png"));
			
	}
	public Image dameImagen(PiezaColor pieza){
		return imagenes.get(pieza);	
	}

}
