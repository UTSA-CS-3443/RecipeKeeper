package model;

/**
 * Category class save name of a category
 * @author Hoa Pham
 *
 */
public class Category {
	String name;

	/**
	 * constructor
	 * @param name
	 */
	public Category(String name) {
		super();
		this.name = name;
	}

	/**
	 * get the name of the Category
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the Category
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
