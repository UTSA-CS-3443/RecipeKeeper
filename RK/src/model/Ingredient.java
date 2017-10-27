package model;
/**
 * Ingredient is an object that has 
 * name as the ingredient's name
 * quantity as the amount of the ingredient needed for the recipe
 * and unit as the quantity's unit
 * @author Hoa Pham
 * 
 */
public class Ingredient {
	
	String name;		// ingredient's name
	double quantity;	// ingredient's quantity
	String unit;		// ingredient's unit (how many, how much)
	
	/**
	 * Constructor, stores params for the ingredient
	 * @param name
	 * @param qty
	 * @param unit
	 */
	public Ingredient(String name, double qty, String unit) {
		setName(name);
		setQuantity(qty);
		setUnit(unit);
	}

	/**
	 * @return name of the ingredient
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the ingredient
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return quantity needed for the recipe
	 */
	public double getQuantity() {
		return quantity;
	}
	
	/**
	 * Set quantity needed for the recipe
	 * @param quantity
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the quantity's unit
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * Set the quantity's unit
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
