//Worked on this class with Guillermo, John, and Keeton

package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * The NonogramPresenter is the actual brain of the Nonogram puzzle maker, it connects the 
 * model data with the graphical interface to be able to use the Nonogram puzzle maker itself.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class NonogramPresenter implements Openable{

	/**
	 * This is a NonogramView object
	 */
	private NonogramView view;
	/**
	 * This is a NonogramModel object
	 */
	private NonogramModel model;
	/**
	 * This is an int of the length of the cell
	 */
	private int cellLength;
	/**
	 * This is final static string the represents the default puzzle used
	 */
	private final static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	/**
	 * This constructor takes the length of a cell and constructs a new Nonogram Presenter
	 * @param cellLength the int representation of the length of the cell
	 * @throws IOException if a file is not found
	 */
	public NonogramPresenter(int cellLength) throws IOException {
		this.cellLength = cellLength;
		model = new NonogramModel(DEFAULT_PUZZLE);
		view = new NonogramView();
		initializePresenter();
	}
	
	/**
	 * This method is used to initialize the NonogramPresenter
	 */
	private void initializePresenter() {
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
	}
	
	/**
	 * This method is used to initialize the NonogramView
	 */
	public void initializeView() {
		view.initialize(model.getRowClues(),model.getColClues(),cellLength);
		if(this.getWindow() != null) {
			this.getWindow().sizeToScene();
		}
	}
	
	/**
	 * This method is used to bind the cellViews based on the clicks read from a mouse.
	 */
	public void bindCellViews() {
		for(int row = 0; row < model.getNumRows(); ++row) {
			
			for(int col =0; col <model.getNumCols(); ++col) {
				final var rowI = row;
				final var colI = col;

				view.getCellView(row, col).setOnMouseClicked((MouseEvent event)->{
					if(event.getButton() == MouseButton.PRIMARY) {
						handleLeftClick(rowI, colI);
					}
					else if(event.getButton() == MouseButton.SECONDARY) {
						handleRightClick(rowI, colI);
					}
				});
				
			}
		}
	}
	
	/**
	 * This method takes a row and column index and determines a CellState to change
	 * it to a different CellState when left click is detected
	 * @param rowIdx The row index given
	 * @param colIdx The column index given
	 */
	public void handleLeftClick(int rowIdx, int colIdx) {
		
		if(model.getCellState(rowIdx, colIdx) == CellState.FILLED) {
			
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		}
			else if(model.getCellState(rowIdx, colIdx) == CellState.EMPTY || 
				model.getCellState(rowIdx, colIdx) == CellState.MARKED) {
				
				updateCellState(rowIdx, colIdx, CellState.FILLED);
		}
		
	}
	
	
	/**
	 * This method takes a row and column index and determines a CellState to change
	 * it to a different CellState when right click is detected
	 * @param rowIdx The row index given
	 * @param colIdx The column index given
	 */
	public void handleRightClick(int rowIdx, int colIdx) {
		if(model.getCellState(rowIdx, colIdx) == CellState.MARKED) {
			
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		}
			else if(model.getCellState(rowIdx, colIdx) == CellState.EMPTY || 
				model.getCellState(rowIdx, colIdx) == CellState.FILLED) {
				
	
				updateCellState(rowIdx, colIdx, CellState.MARKED);
		}
	}
	
	/**
	 * This method is used to update the model's CellState and processes the victory if it's solved
	 * @param rowIdx is the row index given
	 * @param colIdx is the column index given
	 * @param state is the CellState given
	 */
	public void updateCellState(int rowIdx, int colIdx, CellState state) {
		
		if(model.setCellState(rowIdx, colIdx, state)) {
			view.setCellState(rowIdx, colIdx, state);
			view.setRowClueState(rowIdx, model.isRowSolved(rowIdx));
			view.setColClueState(colIdx, model.isColSolved(colIdx));
		}
		if(model.isSolved()) {
			processVictory();
		}
		
	}
	/**
	 * This method ensures that the view is synchronized with the model itself.
	 */
	public void synchronize() {
		for(int i =0; i < model.getNumRows(); ++i) {
			for(int j = 0; j < model.getNumCols(); ++j) {
				view.setCellState(i, j, model.getCellState(i, j));
				view.setRowClueState(i, model.getCellStateAsBoolean(i, j));
				view.setColClueState(i, model.getCellStateAsBoolean(i, j));
				view.setPuzzleState(model.isSolved());
				if(model.isSolved()) {
					processVictory();
				}
			}
		}
		
		
	}
	
	/**
	 * This method removes the cell view marks and shows the victory alert.
	 */
	public void processVictory() {
		
		removeCellViewMarks();
		view.showVictoryAlert();
	}
	
	/**
	 * This method removes the cell marks in a view.
	 */
	public void removeCellViewMarks() {
		
		for(int i = 0; i < model.getNumRows(); ++i) {
			for(int j = 0; j < model.getNumCols(); ++j) {
				if(model.getCellState(i, j) == CellState.MARKED) {
					view.setCellState(i, j, CellState.EMPTY);
				}
			}
		}
	}
	
	/**
	 * This method is used to configure my Load and Reset buttons.
	 */
	public void configureButtons() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		view.getResetButton().setOnAction((ActionEvent event)->{
			resetPuzzle();
		});	
			
	}
	
	/**
	 * This method is used to reset the entire puzzle.
	 */
	public void resetPuzzle() {
		
		model.resetCells();
		synchronize();
	}
	
	/**
	 * This method returns the pane aka the view.
	 * @return the pane associated
	 */
	public Pane getPane() {
		return view;
		
		}
	/**
	 * This method returns the window from the view pane.
	 * @return the Window associated with the view scene and window.
	 */
	public Window getWindow() {
		 try {
			return view.getScene().getWindow();
		 }
		catch(NullPointerException e) {
			return null;
		}
	}
	/**
	 * This method is used to open a file by constructing a NonogramModel with the file.
	 * @throws IOException if the file is not found.
	 */
	public void open(File file) throws IOException {
		model = new NonogramModel(file);
		initializePresenter();
	}
}
