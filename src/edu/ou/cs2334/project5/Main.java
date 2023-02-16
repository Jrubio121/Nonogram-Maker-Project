package edu.ou.cs2334.project5;
import java.util.ArrayList;
import java.util.List;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class is used to set the content of the application window
 * It takes a NonogramPresenter and displays it.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class Main extends Application {

	/**
	 * A final static int that represents the index of the cell size
	 */
	private final static int IDX_CELL_SIZE =0;
	/**
	 * A final static int that represents the default cell size
	 */
	private final static int DEFAULT_CELL_SIZE = 30;
	
	/**
	 * This method is used to launch the application.
	 * @param args a string array of arguments
	 */
	public static void main(String[] args) {
		
		launch(args);
	}

	/**
	 * This method is used to start the application with the primary stage.
	 * @param primaryStage is the Stage object that we are setting to the screen
	 * @throws Exception if anything in the method is null
	 */
	public void start(Stage primaryStage) throws Exception {

		NonogramPresenter present;
		List<String> cell = getParameters().getUnnamed();
		if(cell != null) {
			int length = Integer.parseInt(cell.get(IDX_CELL_SIZE));
			present = new NonogramPresenter(length);
		}
		else {
			present = new NonogramPresenter(DEFAULT_CELL_SIZE);
		}

		Scene scene = new Scene(present.getPane());
		primaryStage.setScene(scene);
		primaryStage.getScene().getStylesheets().add("style.css");
		primaryStage.setTitle("Nonogram Puzzle");
		primaryStage.setResizable(true);
		primaryStage.show();
		
	}

}
