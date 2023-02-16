package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

/**
 * This class is a GridPane used to display the cell states.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class CellGridView extends GridPane{

	/**
	 * This is a static string that represents the style class
	 */
	private static String STYLE_CLASS = "cell-grid-view";
	/**
	 * This is an array of CellViews
	 */
	CellView[][] cellViews;
	
	/**
	 * This constructor calls the GridPane constructor to construct a CellGridView
	 * @param numRows This is the number of rows given
	 * @param numCols This is the number of columns given
	 * @param cellLength This is the int representation of the cell length given
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		
		super();
		initCells(numRows, numCols, cellLength);
		this.getStyleClass().add(STYLE_CLASS);
	}
	
	/**
	 * This method is used to initialize the cells in the view
	 * @param numRows is the number of rows given
	 * @param numCols is the number of columns given
	 * @param cellLength is the length of the cell given
	 */
	public void initCells(int numRows, int numCols, int cellLength) {
		
		this.getChildren().clear();
		cellViews= new CellView[numRows][numCols];
		
		
		for(int i = 0; i < numRows; ++i) {
			
			for(int j = 0; j < numCols; ++j) {
				
				CellView cell = new CellView(cellLength);
				cellViews[i][j] = cell;
				cell.setSize(cellLength);				

			}
		}
		for(int i = 0; i < numRows; ++i) {
			this.addRow(i, cellViews[i]);

		}
	}
	
	/**
	 * This method returns a cell view at a specific row and column index
	 * @param rowIdx is the row index given
	 * @param colIdx is the column index given
	 * @return the CellView at a specific location
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellViews[rowIdx][colIdx];
	}
	
	/**
	 * This method sets the cell state at a specific row and column index
	 * @param rowIdx is the row index given
	 * @param colIdx is the column index given 
	 * @param state is the CellState we are changing to
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellViews[rowIdx][colIdx].setState(state);
	}
	
	
}
