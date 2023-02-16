// Worked on this class with Guillermo, Keeton, and John

package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * This class is used to encapsulate the state and rules of the game, it uses arrays to store 
 * the rowClues, colClues, and cellStates
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class NonogramModel {

	/**
	 * A static final String representing the delimiter
	 */
	private static final String DELIMITER = " ";
	/**
	 * A static final column representing the number of columns index
	 */
	private static final int IDX_NUM_ROWS = 0;
	/**
	 * A static final int representing the number of columns index
	 */
	private static final int IDX_NUM_COLS = 1;

	/**
	 * A 2D int array consisting of the row clues.
	 */
	private int[][] rowClues;
	/**
	 * A 2D int array consisting of the column clues.
	 */
	private int[][] colClues;
	/**
	 * A 2D CellState array consisting of the cell's states.
	 */
	private CellState[][] cellStates;
	/**
	 * This constructor takes an array with rowClues and an array with colClues and uses them to initialize
	 * cellStates
	 * @param rowClues is an array with row clues
	 * @param colClues is an array with column clues
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {

		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}
	
	/**
	 * This constructor takes a file and uses it to initialize the rowClues, colClues, and cellStates arrays.
	 * @param file is the File given to initialize the model.
	 * @throws IOException is thrown if the file is not found.
	 */
	public NonogramModel(File file) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		cellStates = initCellStates(numRows, numCols);
		this.rowClues = readClueLines(reader, numRows);
		this.colClues = readClueLines(reader, numCols);

		
		reader.close();
	}

	/**
	 * This constructor takes a filename and uses it to initialize the rowClues, colClues, and cellStates array. 
	 * @param filename is the name of the file given.
	 * @throws IOException is thrown if the file is not found.
	 */
public NonogramModel(String filename) throws IOException {

	NonogramModel forNow = new NonogramModel(new File(filename));
	this.rowClues = deepCopy(forNow.getRowClues());
	this.colClues = deepCopy(forNow.getColClues());
	cellStates = initCellStates(getNumRows(), getNumCols());
}

/**
 * This method is used to return the number of rows in the rowClues array.
 * @return The number of rows in the rowClues array
 */
public int getNumRows() {
	return rowClues.length;
}

/**
 * This method is used to return the number of columns in the colClues array.
 * @return The number of columns in the colClues array
 */
public int getNumCols() {
	return colClues.length;
}

/**
 * This method is used to return a CellState at a given row index and column index. 
 * @param rowIdx the row index given
 * @param colIdx the column index given
 * @return The CellState at a given position. 
 */
public CellState getCellState(int rowIdx, int colIdx) {
	return cellStates[rowIdx][colIdx];
}

/**
 * This method is used to return a CellState as a boolean at a given row index and column index.
 * @param rowIdx the row index given
 * @param colIdx the column index given
 * @return The CellState as a boolean value at a given position.
 */
public boolean getCellStateAsBoolean(int rowIdx, int colIdx) {
	return CellState.toBoolean(cellStates[rowIdx][colIdx]);
}

/**
 * This method is used to set a given CellState at a given position with the row and column index.
 * @param rowIdx the row index given
 * @param colIdx the column index given
 * @param state the given CellState
 * @return A boolean value that determines whether the CellState was changes, true if changed false if not
 */
public boolean setCellState(int rowIdx, int colIdx, CellState state) {
	
	CellState temp = cellStates[rowIdx][colIdx];
	
	if(state != null && !isSolved()) {
		cellStates[rowIdx][colIdx] = state;
	}
	
	if(temp == cellStates[rowIdx][colIdx])
		return false;
	
	else
		return true;
	
}

/**
 * This method returns a deepCopy of the rowClues array.
 * @return a deepCopy of the rowClues array.
 */
public int[][] getRowClues(){
	return deepCopy(rowClues);
}

/**
 * This method returns a deepCopy of the colClues array.
 * @return a deepCopy of the colClues array.
 */
public int[][] getColClues(){
	return deepCopy(colClues);
}
/**
 * This method returns a single row of clues.
 * @param rowIdx The row index given
 * @return An array representing the clues in a row.
 */

public int[] getRowClue(int rowIdx) {
	return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length); 
}

/**
 * This method returns a single column of clues.
 * @param colIdx The column index given
 * @return An array representing the clues in a column.
 */
public int[] getColClue(int colIdx) {
	return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length); 
}

/**
 * This method takes a row index and checks if that row is solved, it returns true if solved false if not.
 * @param rowIdx The row index given
 * @return A boolean value that represents if a row is solved.
 */
public boolean isRowSolved(int rowIdx) {
	int[] row = getRowClue(rowIdx);
	if(row.length != projectCellStatesRow(rowIdx).length) {
		return false;
	}
	for(int i =0; i <row.length; ++i) {
		if(row[i] != projectCellStatesRow(rowIdx)[i]) {
			return false;
		}
	}
	return true;
	
}
/**
 * This method takes a column index and checks if that column is solved, it returns true if solves, false if not.
 * @param colIdx The column index given
 * @return A boolean value that represents if a column is solved.
 */
public boolean isColSolved(int colIdx) {
	int[] col = getColClue(colIdx);
	if(col.length != projectCellStatesCol(colIdx).length) {
		return false;
	}
	for(int i =0; i <col.length; ++i) {
		if(col[i] != projectCellStatesCol(colIdx)[i]) {
			return false;
		}
	}
	return true;
}

/**
 * This method checks the entire puzzle and returns true if it is all solved and false if not.
 * @return A boolean value that determines if the entire puzzle is solved or not.
 */
public boolean isSolved() {
	
	for(int i = 0; i < rowClues.length; ++i) {
		if(!isRowSolved(i)) {
			return false;
		}
	}
	for(int i = 0; i < colClues.length; ++i) {
		if(!isColSolved(i)) {
			return false;
		}
	}
	return true;
}

/**
 * This method is used to reset the cells to an empty cell.
 */
public void resetCells() {
	for(int i = 0; i < cellStates.length; ++i) {
		for(int j = 0; j < cellStates[i].length;++j) {
			cellStates[i][j] = CellState.EMPTY;
		}
	}
}

/**
 * This method projects and array returning the number of Nonogram numbers in a given array of cell states.
 * @param cells is a boolean array that represents the cells in a model.
 * @return The Nonogram numbers of a given array of cell states.
 */
public static List<Integer> project(boolean[] cells){
	
	ArrayList<Integer> temp = new ArrayList<Integer>();
	int trueCount = 0;
	for(int i = 0; i < cells.length; ++i) {
		if(cells[i]) {
			++trueCount;
		}
		else {
			if(trueCount > 0) {
				temp.add(trueCount);
				trueCount = 0;
			}
		}
	}
	if(trueCount > 0) {
		temp.add(trueCount);
	}
	if(temp.size() == 0) {
		temp.add(0);
	}
		return temp;
}

/**
 * This method projects an array and returns the Nonogram numbers in a given row.
 * @param rowIdx the int value of the row index
 * @return The projection of the row with the given index.
 */
public int[] projectCellStatesRow(int rowIdx){
	
	boolean[] hi = new boolean [getNumCols()];
	for(int i =0; i < hi.length; ++i) {
		hi[i] = getCellStateAsBoolean(rowIdx,i);
	}
	List<Integer> temp = project(hi);
	int [] end = new int[temp.size()];
	
	for(int i = 0; i <end.length; ++i) {
		end[i] = temp.get(i);
	}
	return end;

}

/**
 * This method projects an array and returns the Nonogram numbers in a given column.
 * @param colIdx the int value of the column index
 * @return The projection of the column with the given index.
 */
public int[] projectCellStatesCol(int colIdx){
	
	boolean[] hi = new boolean [getNumRows()];
	for(int i =0; i < hi.length; ++i) {
		hi[i] = getCellStateAsBoolean(i,colIdx);
	}
	List<Integer> temp = project(hi);
	int [] end = new int[temp.size()];
	
	for(int i = 0; i <end.length; ++i) {
		end[i] = temp.get(i);
	}
	return end;
}
	
/**
 * This method is used the number of rows and columns to fully initialize the CellStates array.
 * @param numRows the number of rows
 * @param numCols the number of columns
 * @return the CellState array with the initialized cells
 */
private static CellState[][] initCellStates(int numRows, int numCols) {
	CellState[][] cellStates = new CellState[numRows][numCols];
	
	for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
		for (int colIdx = 0; colIdx < numCols; ++colIdx) {
			cellStates[rowIdx][colIdx] = CellState.EMPTY;
		}
	}
	
	return cellStates;
}

// Found guidance on stack overflow
/**
 * This method takes an int array and creates a deepCopy of it.
 * @param array an int array
 * @return an int array that is the deep copy of the parameter given.
 */
private static int[][] deepCopy(int[][] array) {
	
	if(array == null)
		return null;
	
	final int[][] copy = new int[array.length][];
	
	for(int i = 0; i < array.length; ++i) {
		copy[i] = Arrays.copyOf(array[i], array[i].length);
	}
	
	return copy;
}

/**
 * This method takes a reader and reads a file 
 * with the number of lines given to read the clue lines.
 * 
 * @param reader is the reader used to read the file
 * @param numLines is the number of lines
 * @return An int array that is the clue lines read from a file.
 * @throws IOException if a file is not found
 */
private static int[][] readClueLines(BufferedReader reader, int numLines)
		throws IOException {
	int[][] clueLines = new int[numLines][];
	
	for (int lineNum = 0; lineNum < numLines; ++lineNum) {
		String line = reader.readLine();
		
		String[] tokens = line.split(DELIMITER);
		
		int[] clues = new int[tokens.length];
		for (int idx = 0; idx < tokens.length; ++idx) {
			clues[idx] = Integer.parseInt(tokens[idx]);
		}
		
		clueLines[lineNum] = clues;
	}
	
	return clueLines;
}
	
}
