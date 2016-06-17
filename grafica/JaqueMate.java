package grafica;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class JaqueMate {
	public static void display(){
		Stage stage= new Stage();
		File f= new File("src/assets/jaqueMate.mp4");
		
		Media m =new Media(f.toURI().toString());
		MediaPlayer mp = new MediaPlayer(m);
		MediaView mv= new MediaView(mp);
		
		StackPane root= new StackPane();
		root.getChildren().add(mv);
		
		stage.setScene(new Scene(root,960,540));
		stage.show();
		stage.setResizable(false);
		
		mp.play();
		mp.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				mp.stop();
				stage.close();
			}
			
		});
		
		//stage.close();
		
	}

}
