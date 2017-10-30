package control;

public class Constants {
	// list of units
	private static final String[] UNITS = { "kg", "hg", "dag", "g", "dg", "cg", "mg", 
					"kl", "hl", "dal", "l", "dl", "cl", "ml" };
	// serving Sizes
	private static final String[] SERVSIZES = { "1", "2", "3" };
	// minimum size of the window
	private static final int[] MIN_SIZES = { 529,737 };
	
	
	public Constants() {

	}

	public String[] getUnits() {
		return UNITS;
	}
	
	public String[] getServsizes() {
		return SERVSIZES;
	}
	
	public int[] getMinSizes() {
		return MIN_SIZES;
	}
}
