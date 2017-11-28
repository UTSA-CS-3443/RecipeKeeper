package model;

import java.util.ArrayList;

/**
 * list of addresses for backward and forward buttons
 * @author Hoa Pham
 *
 */
public class Addresses {
	
	/**
	 * variables that stores accessing history of user 
	 */
	private ArrayList<String> backward;
	private ArrayList<String> forward;

	/**
	 * constructor
	 * @param aStack
	 */
	public Addresses(Addresses aStack) {
		this.backward = aStack.getBackward();
	}
	
	/**
	 * constructor
	 */
	public Addresses() {
		this.backward = new ArrayList<String>();
	}
	
	/**
	 * push an address to backward history
	 * @param anAddress
	 */
	public void pushBackList(String anAddress) {
		backward.add(anAddress);
	}
	
	/**
	 * delete one address when the user 
	 * clicks on backward button
	 */
	public boolean popBackList() {
		boolean result = false;
		if (!backward.isEmpty()) {
			backward.remove(backward.size()-1);
			result = true;
		}
		return result;
	}
	
	/**
	 * @return last added item of backward
	 */
	public String peekBackList() {
		String result = "";
		if (!backward.isEmpty()) {
			result = backward.get(backward.size()-1);
		}
		return result;
	}
	
	/**
	 * 
	 * @param anAddress
	 */
	public void pushForthList(String anAddress) {
		forward.add(anAddress);
	}
	
	/**
	 * delete one address when the user 
	 * clicks on forward button
	 */
	public boolean popForthList() {
		boolean result = false;
		if (!forward.isEmpty()) {
			forward.remove(forward.size()-1);
			result = true;
		}
		return result;
	}
	
	/**
	 * @return last added item of forward 
	 */
	public String peekFrontList() {
		String result = "";
		if (!forward.isEmpty()) {
			result = forward.get(forward.size()-1);
		}
		return result;
	}
	
	/**
	 * @return backward history
	 */
	public ArrayList<String> getBackward() {
		return backward;
	}

	/**
	 * backward history setter
	 * @param stack
	 */
	public void setBackward(ArrayList<String> stack) {
		this.backward = stack;
	}

	/**
	 * @return forward history
	 */
	public ArrayList<String> getForward() {
		return forward;
	}

	/**
	 * forward history setter
	 * @param forward
	 */
	public void setForward(ArrayList<String> forward) {
		this.forward = forward;
	}
	
}
