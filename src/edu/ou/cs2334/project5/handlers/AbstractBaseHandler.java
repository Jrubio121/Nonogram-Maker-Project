package edu.ou.cs2334.project5.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The AbstractBaseHandler class is an abstract class that represents a general handler
 * involving a file selection. 
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public abstract class AbstractBaseHandler {

	protected Window window;
	protected FileChooser fileChooser;
	
	/**
	 * This method constructs an AbstractBaseHandler given a Window and FileChooser.
	 * @param window is the given Window.
	 * @param fileChooser is the given FileChooser.
	 */
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
		this.window = window;
		this.fileChooser = fileChooser;
	}
}