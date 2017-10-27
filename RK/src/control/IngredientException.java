package control;

public class IngredientException extends Exception {
	public IngredientException(String message) {
		AlertBox.display("Warning", "Illegal Input Type: " + message);
	}
}
