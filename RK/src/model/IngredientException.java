package model;

/**
 * 
 * @author Hoa Pham
 *
 */
public class IngredientException extends Exception {

	public IngredientException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientException(String message) {
		AlertBox.display("Warning", "Input Error: " + message);
	}
}
