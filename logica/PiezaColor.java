package logica;

public class PiezaColor {
	private NombrePieza elNombre;
	private Color elColor;
	
	public PiezaColor(NombrePieza nombre, Color color){
		elNombre = nombre;
		elColor = color;
	}
	
	public NombrePieza dameNombre(){
		return elNombre;
	}
	
	public Color dameColor(){
		return elColor;
	}
}
