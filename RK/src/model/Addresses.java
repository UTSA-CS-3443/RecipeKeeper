package model;

import java.util.Stack;

/**
 * list of addresses for backward and forward buttons
 * contains 2 stacks: 1 for backward and 1 for forward
 * @author Hoa Pham
 *
 */
public class Addresses {
	
	/**
	 * backward and forward accessing history
	 */
	private Stack<String> backward;
	private Stack<String> forward;
	
	/**
	 * constructor
	 */
	public Addresses() {
		this.backward = new Stack<String>();
		this.forward = new Stack<String>();
	}

	/**
	 * constructor
	 * @param another
	 */
	public Addresses(Addresses another) {
		setBackward(another.getBackward());
		setForward(another.getForward());
	}
	
	/**
	 * @return backward history
	 */
	public Stack<String> getBackward() {
		return backward;
	}

	/**
	 * set backward history
	 * @param backward
	 */
	public void setBackward(Stack<String> backward) {
		this.backward = backward;
	}

	/**
	 * @return forward history
	 */
	public Stack<String> getForward() {
		return forward;
	}

	/**
	 * set forward history
	 * @param forward
	 */
	public void setForward(Stack<String> forward) {
		this.forward = forward;
	}
}
