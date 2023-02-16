package edu.ou.cs2334.project5.views;
import edu.ou.cs2334.project5.models.CellState;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * This class is used to represent a single nonogram cell that can be filled, marked, or empty.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since May 1, 2022
 */
public class CellView extends StackPane {

	/**
	 * A final static String of the style class
	 */
	private static final String STYLE_CLASS = "cell-view";
	/**
	 * A final static string of an empty style class
	 */
	private static final String EMPTY_STYLE_CLASS = "cell-view-empty";
	/**
	 * A final static string of a filled style class
	 */
	private static final String FILLED_STYLE_CLASS = "cell-view-filled";
	/**
	 * A final static string of a marked style class
	 */
	private static final String MARKED_STYLE_CLASS = "cell-view-marked";
	/**
	 * A final static double used to scale the length
	 */
	private static final double X_LENGTH_SCALE = 1.0 / 2.0;

	/**
	 * A Rectangle object used for the background
	 */
	private Rectangle background = new Rectangle();
	/**
	 * A Line object that is used as the xLeftLeg
	 */
	private Line xLeftLeg = new Line();
	/**
	 * A Line object that is used as the xRightLegt
	 */
	private Line xRightLeg = new Line();

	/**
	 * This constructor takes a side length and constructs a CellView
	 * @param sideLength is the int representation of the length of the side
	 */
	public CellView(int sideLength) {
		getStyleClass().add(STYLE_CLASS);
		setState(CellState.EMPTY);
		setSize(sideLength);
		getChildren().addAll(background, xLeftLeg, xRightLeg);
	}

	/**
	 * This method takes a CellState and uses it to set a cell state.
	 * @param state is the CellState given to change to
	 */
	public void setState(CellState state) {
		ObservableList<String> styleClasses = getStyleClass();
		styleClasses.removeAll(
				EMPTY_STYLE_CLASS, FILLED_STYLE_CLASS, MARKED_STYLE_CLASS);
		switch (state) {
			case EMPTY:
				styleClasses.add(EMPTY_STYLE_CLASS);
				break;
			case FILLED:
				styleClasses.add(FILLED_STYLE_CLASS);
				break;
			case MARKED:
				styleClasses.add(MARKED_STYLE_CLASS);
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	/**
	 * This method takes a sideLenght and uses it to set the size of the CellView.
	 * @param sideLength is the int representation of the length of the side.
	 */
	public void setSize(int sideLength) {
		background.setWidth(sideLength);
		background.setHeight(sideLength);

		// Set the size of the X.
		double legLength = X_LENGTH_SCALE * sideLength;
		double xWidth = legLength / Math.sqrt(2);
		double xHeight = xWidth;
		xLeftLeg.setStartX(0);
		xLeftLeg.setStartY(0);
		xLeftLeg.setEndX(xWidth);
		xLeftLeg.setEndY(xHeight);
		xRightLeg.setStartX(0);
		xRightLeg.setStartY(xHeight);
		xRightLeg.setEndX(xWidth);
		xRightLeg.setEndY(0);
	}
}
