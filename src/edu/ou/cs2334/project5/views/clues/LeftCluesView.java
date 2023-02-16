package edu.ou.cs2334.project5.views.clues;

import javafx.geometry.Orientation;

/**
 * Represents a view containing all row clues displayed to the left of the grid.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class LeftCluesView extends AbstractGroupCluesView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "left-clues-view";
	
	/**
	 * Constructs a LeftCluesView given a set of clues, cell length, and width.
	 * 
	 * @param rowClues a set of horizontal row clues
	 * @param cellLength the length of a cell
	 * @param width the maximum number of numbered clues among all rows
	 */
	public LeftCluesView(int[][] rowClues, int cellLength, int width) {
		super(Orientation.VERTICAL, STYLE_CLASS, rowClues, cellLength, width);
		setMaxHeight(rowClues.length * cellLength);
		
		
	}

	
	/**
	 * This method is used to make a new clue for the associated class, in this case a horizontal clue
	 * 
	 * @param clue an int array of clues
	 * @param cellLength an int representation of the length of the cell
	 * @param numClueUnits is the number of clue units
	 * @return an AbstractOrientedClue view object depicting the horizontal clues view.
	 */
	protected AbstractOrientedClueView makeClue(int[] clue, int cellLength, int numClueUnits) {

		HorizontalClueView hClue = new HorizontalClueView(clue,cellLength, numClueUnits);
		return hClue;
	}

}
