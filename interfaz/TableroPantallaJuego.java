package interfaz;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class TableroPantallaJuego extends GridPane{

	private static final int columnCount = 8;
	private static final int rowCount = 8;


	public TableroPantallaJuego(){
		super();
		this.setAlignment(Pos.CENTER);
		GridPane grid = new GridPane();
		
		grid.setPrefSize(500, 500);
		grid.setTranslateX(100);
		grid.setTranslateY(100);
		
		grid.setHgap(10);
		grid.setVgap(10);
//		grid.prefWidthProperty().bind(primaryStage.widthProperty());
//		grid.prefHeightProperty().bind(primaryStage.heightProperty());
//		grid.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));

		ColumnConstraints cc = new ColumnConstraints();
		cc.setFillWidth(true);
		cc.setHgrow(Priority.ALWAYS);

		for (int i = 0; i < columnCount; i++) {
			grid.getColumnConstraints().add(cc);
		}

		RowConstraints rc = new RowConstraints();
		rc.setFillHeight(true);
		rc.setVgrow(Priority.ALWAYS);

		for (int i = 0; i < rowCount; i++) {
			grid.getRowConstraints().add(rc);
		}
		grid.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		grid.setId("tablero");
		this.getChildren().add(grid);
	}
}
