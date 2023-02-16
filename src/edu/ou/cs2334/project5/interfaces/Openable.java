package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.IOException;

/**
 * The Openable interface is used to directly specify that a class has a special method to handle
 * opening a file. It allows the OpenHandler class to handle file opening.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public interface Openable {

	/**
	 * This method specifies that a class has a special method to handle opening a file.
	 * @param file is the given file.
	 * @throws IOException is thrown if the file contains any unreadable information.
	 */
	 void open(File file) throws IOException;
}