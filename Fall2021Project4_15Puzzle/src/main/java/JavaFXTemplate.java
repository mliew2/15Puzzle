import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		try {
			primaryStage.setTitle("Welcome");

			primaryStage.setOnCloseRequest(e->{
				Platform.exit();
				System.exit(0);
			});

			Parent root = FXMLLoader.load(getClass().getResource("/FXML/WelcomeScreen.fxml"));

			Scene welcome = new Scene(root, 700, 700);
			welcome.getStylesheets().add("/CSS/welcome.css");

			primaryStage.setScene(welcome);
			primaryStage.show();

			Parent root2 = FXMLLoader.load(getClass().getResource("/FXML/GameScreen.fxml"));
			Scene game = new Scene(root2, 900, 800);
			game.getStylesheets().add("/CSS/game.css");
			game.getStylesheets().add("/CSS/game.css");

			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			pause.setOnFinished(e->{
				primaryStage.setTitle("15 Puzzle");
				primaryStage.setScene(game);});
			pause.play();

		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
