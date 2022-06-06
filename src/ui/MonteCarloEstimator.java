package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonteCarloEstimator extends Application {
	private PiGui gui;

	public MonteCarloEstimator() {
		gui = new PiGui();
	}

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmll = new FXMLLoader(getClass().getResource("Calculator.fxml"));
		fxmll.setController(gui);
		Parent root = fxmll.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pi Calculator");
		primaryStage.setResizable(true);
		primaryStage.show();
	}

}
