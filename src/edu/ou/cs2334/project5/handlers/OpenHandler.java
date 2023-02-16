
package edu.ou.cs2334.project5.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The OpenHandler class is an extension of the AbstractBaseHandler class that implements a handle method 
 * used to handle the opening of a file.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{

	private Openable opener;
	
	/**
	 * This method constructs an OpenHandler given a Window, FileChooser, and Openable.
	 * @param window is the given Window.
	 * @param fileChooser is the given FileChooser.
	 * @param opener is the given Openable from the openable interface.
	 */
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;
	}
	
	/**
	 * This method is used to handle opening a file and assigning an AcitonEvent.
	 * @param event is the given ActionEvent
	 */
	public void handle (ActionEvent event) {
		
		File file = fileChooser.showOpenDialog(window);
		
		if(file != null) {
			try {
				opener.open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}	
}
