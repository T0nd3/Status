package faeuster.pro.status;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Status status = new Status();
		status.init(primaryStage);
	}
}
