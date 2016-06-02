package interfaz;
import javafx.scene.Group;
import javafx.scene.control.Button;


public class MenuPantallaJuego extends Group{

	public MenuPantallaJuego(){
		super();
		Button buttonMenu = new MyButton("Menu",20,20,100,50);
		Button buttonUndo = new MyButton("Undo",140,20,100,50);
		TableroPantallaJuego tablero = new TableroPantallaJuego();
		
		this.setId("tablero");
		
		this.getChildren().addAll(buttonMenu,buttonUndo,tablero);
		
	//	buttonMenu.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene());
	}
}
