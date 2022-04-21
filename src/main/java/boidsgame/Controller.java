package boidsgame;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


public class Controller {
	private Stage stage;
	private Scene scene;


	protected void switchToScene(ActionEvent event, String filename) throws IOException {
		// stage = (Stage)(settingsButton.getSource()).getScrene().getWindow();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(FXMLLoader.load(GameApp.class.getResource(filename)));
		stage.setScene(scene);
		stage.show();
	}
}
