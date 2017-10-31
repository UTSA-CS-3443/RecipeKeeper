package control;

public class Constants {
	// list of units
	private static final String[] UNITS = { "kg", "hg", "dag", "g", "dg", "cg", "mg", 
					"kl", "hl", "dal", "l", "dl", "cl", "ml" };
	
	private static final String[] LOWER_ALPHA = { "a", "b", "c", "d", "e", "f", 
												 "g", "h", "i", "j", "k", "l",
												 "m", "n", "o", "p", "q", "r", 
												 "t", "u", "v", "w", "x", "y", "z" };
	
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
	 * get Lower Alphabet
	 * @return LOWER_ALPHA
	 */
	public String[] getLowAlpha() {
		return LOWER_ALPHA;
	}
	
	/**
	 * get Upper Alphabet
	 * @return Upper version of LOWER_ALPHA
	 */
	public String[] getUpAlpha() {
		String[] uppers = {}; 
		int index = 0;
		for (String i : LOWER_ALPHA) {
			uppers[index] = i.toUpperCase();
		}
		return uppers;
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
