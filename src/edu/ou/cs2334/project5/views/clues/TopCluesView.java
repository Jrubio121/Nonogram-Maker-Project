package edu.ou.cs2334.project5.views.clues;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;

/**
 * Represents a view containing all column clues displayed above the grid.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class TopCluesView extends AbstractGroupCluesView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "top-clues-view";
	
	/**
	 * Constructs a LeftCluesView given a set of clues, cell length, and height.
	 * 
	 * @param colClues a set of column clues
	 * @param cellLength the length of a cell
	 * @param height the maximum number of numbered clues among all columns
	 */
	public TopCluesView(int[][] colClues, int cellLength, int height) {
		super(Orientation.HORIZONTAL, STYLE_CLASS, colClues, cellLength, height);
		setMaxWidth(colClues.length * cellLength);

		
	}

	@Override
	/**
	 * This method is used to make a new clue for the associated class, in this case a vertical clue
	 * 
	 * @param clue an int array of clues
	 * @param cellLength an int representation of the length of the cell
	 * @param numClueUnits is the number of clue units
	 * @return an AbstractOrientedClue view object depicting the vertical clues view.
	 */
	protected AbstractOrientedClueView makeClue(int[] clue, int cellLength, int numClueUnits) {

		VerticalClueView vClue = new VerticalClueView(clue, cellLength, numClueUnits); 
		return vClue;
	}

}
