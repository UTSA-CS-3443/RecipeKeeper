package model;

/**
 * Constants contain:
 * - UNITS for edit mode
 * - SERVSIZE: serving size for read mode
 * - MIN_SIZES: Minimum sizes of windows
 * - Directories: fxml addresses of all of the views and css directory
 * @author Hoa Pham
 *
 */
public class Constants {
	// list of units
	private static final String[] UNITS = { "kg", "hg", "dag", "g", "dg", "cg", "mg", 
					"kl", "hl", "dal", "l", "dl", "cl", "ml" };
	
	// serving Sizes
	private static final String[] SERVSIZES = { "1", "2", "3" };
	// minimum size of the window
	private static final int[] MIN_SIZES = { 529,737 };
	
	// DIRECTORIES
	private final String WELCOME_DIRECTORY = "/view/Welcome.fxml";
	private final String EDIT_DIRECTORY = "/view/EditInterface.fxml";
	private final String READ_DIRECTORY = "/view/ReadInterface.fxml";
	private final String SEARCH_DIRECTORY = "/view/SearchInterface.fxml";
	private final String CSS_DIRECTORY = "/view/RecipeKeeper.css";
	
	/**
	 * default constructor
	 */
	public Constants() {  }

	/**
	 * get UNITS
	 * @return UNITS
	 */
	public String[] getUnits() {
		return UNITS;
	}
	
	/**
	 * get serving sizes
	 * @return SERVSIZES
	 */
	public String[] getServsizes() {
		return SERVSIZES;
	}
	
	/**
	 * Minimum size of a view (read or edit)
	 * @return MIN_SIZES
	 */
	public int[] getMinSizes() {
		return MIN_SIZES;
	}

	/**
	 * Getters for directories
	 */
	public String getWelcomeDirectory() {
		return WELCOME_DIRECTORY;
	}
	public String getEditDirectory() {
		return EDIT_DIRECTORY;
	}
	public String getReadDirectory() {
		return READ_DIRECTORY;
	}
	public String getSearchDirectory() {
		return SEARCH_DIRECTORY;
	}

	public String getCssDirectory() {
		return CSS_DIRECTORY;
	}
}
