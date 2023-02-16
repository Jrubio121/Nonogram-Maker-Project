package edu.ou.cs2334.project5.views.clues;
import javafx.geometry.Orientation;

/**
 * Represents a horizontal row of clues.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class HorizontalClueView extends AbstractOrientedClueView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "horizontal-clue-view";

	/**
	 * Constructs a HorizontalClueView using the given parameter values.
	 * 
	 * @param rowClue an array of row clues
	 * @param cellLength the length of a cell
	 * @param width the maximum number of numbered clues among all rows
	 */
	public HorizontalClueView(int[] rowClue, int cellLength, int width) {
		super(Orientation.HORIZONTAL, STYLE_CLASS, rowClue, cellLength, width);
		setMaxWidth(width * cellLength);
	}

}
