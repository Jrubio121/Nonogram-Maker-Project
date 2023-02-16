package edu.ou.cs2334.project5.models;

/**
 * This enumeration represents whether the cell is empty, filled, or marked, therefore the CellState
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public enum CellState {
	EMPTY, FILLED, MARKED;
	/**
	 * This method checks the given cellState and returns what type of boolean
	 * @param state this represents the state of the enumerator 
	 * @return boolean this represents whether the cell is empty or filled using true or false
	 */
	public static boolean toBoolean(CellState state) {

		return state.equals(FILLED);
	}
}
