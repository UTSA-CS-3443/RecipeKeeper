package model;

/**
 * Customized Exception class, used in EditController
 * @author Hoa Pham
 *
 */
public class IngredientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IngredientException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientException(String message) {
		AlertBox.display("Warning", "Input Error: " + message);
	}
}
