package control;

/**
 *
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
}
