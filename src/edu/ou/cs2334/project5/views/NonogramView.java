//Worked on this class with Guillermo, John, and Keeton

package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class is a BorderPane used to display the row clues 
 * in the left position, the column clues in the top position, and the cells in the middle position.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class NonogramView extends BorderPane {

	private final static String STYLE_CLASS = "nonogram-view";
	private final static String SOLVED_STYLE = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadBtn;
	private Button resetBtn;
	
	/**
	 * This is a constructor that calls the BorderPane class and adds a style class.
	 */
	public NonogramView(){
		super();
		this.getStyleClass().add(STYLE_CLASS);
		
	}
	
	/**
	 * This method is used to initialize the NonogramView
	 * @param rowClues an int array of row clues
	 * @param colClues an int array of column clues
	 * @param cellLength an int representation of the cell length
	 */
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		int rowCluesWidth = getWidth(rowClues);
		int colCluesWidth = getWidth(colClues);
		
		leftCluesView = new LeftCluesView(rowClues, cellLength, rowCluesWidth);
		topCluesView = new TopCluesView(colClues, cellLength, colCluesWidth);
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		
		this.setLeft(leftCluesView);
	
		this.setTop(topCluesView);
		setAlignment(getTop(), Pos.CENTER_RIGHT);
		this.setCenter(cellGridView);
		this.initBottomHbox();
		this.setBottom(bottomHBox);

	}
	
	/**
	 * This method takes an array of clues and returns the width 
	 * @param clues is an int array of clues
	 * @return the width of the array
	 */
	public int getWidth(int[][] clues) {
		
		int width = clues[0].length;
		for(int i = 0;i<clues.length;++i) {
			
			if(width < clues[i].length) {
				width = clues[i].length;
			}
			
		}
		return width;
	}
	
	/**
	 * This method is used to initialize the bottom hbox.
	 */
	public void initBottomHbox(){
		
		bottomHBox = new HBox();
		bottomHBox.setAlignment(Pos.CENTER);
		loadBtn = new Button("Load");
		resetBtn = new Button("Reset");
		bottomHBox.getChildren().add(loadBtn);
		bottomHBox.getChildren().add(resetBtn);
		
	}
	
	/**
	 * This method returns a CellView at a certain row and column index.
	 * @param rowIdx the row index given
	 * @param colIdx the column index given
	 * @return the CellView at a given position
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}
	
	/**
	 * This method takes a state and sets it to a CellView at a given row and column index
	 * @param rowIdx the row index given
	 * @param colIdx the column index given
	 * @param state the CellState given
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}
	
	/**
	 * This method sets the clue state for an entire row at a given row index
	 * @param rowIdx the row index given 
	 * @param solved the boolean value determining if its solved
	 */
	public void setRowClueState(int rowIdx, boolean solved) {
		leftCluesView.setState(rowIdx,solved);
	}
	
	/**
	 * This method sets the clue state for an entire column at a given row index
	 * @param colIdx the row index given
	 * @param solved the boolean value determining if its solved
	 */
	public void setColClueState(int colIdx, boolean solved) {
		topCluesView.setState(colIdx, solved);
	}
	
	/**
	 * This method takes a boolean value solved and sets it to the puzzle state
	 * @param solved boolean value determining if solved or not
	 */
	public void setPuzzleState(boolean solved) {
		if(solved) {
			this.getStyleClass().add(SOLVED_STYLE);
		}
		else
			this.getStyleClass().remove(SOLVED_STYLE);
	}
	
	/**
	 * This method returns the load Button object
	 * @return the Button loadBtn
	 */
	public Button getLoadButton() {
		return loadBtn;
	}
	
	/**
	 * This method returns the reset Button object
	 * @return the Button resetBtn
	 */
	public Button getResetButton() {
		return resetBtn;
	}
	
	/**
	 * This method is used to show the victory alert at the end of the puzzle
	 */
	public void showVictoryAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Puzzle Solved");
		ButtonType type = new ButtonType("OK",ButtonData.OK_DONE);
		alert.setHeaderText("Congratulations!");

		alert.setContentText("You Win!");
		alert.getButtonTypes().setAll(type);
		alert.show();
		
	}
}

