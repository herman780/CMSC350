/*
 * Project: # 1 - Converting Prefix expressions to postfix and postfix to prefix. 
 * Filename: SyntaxError.java
 * Author: Herman Mann
 * Date: 01/24/2022
 * Description: This is a Java program that consists of the SyntaxError Checked
 * Exception class which would throw a new syntax error depending on the error
 * catched by the program and would display to the screen within the JOptionPane
 * window. The error would either be if an empty stack is ever popped or the 
 * stack is not empty once the conversion is totally complete. 
 * The SyntaxError exception would first be caught in the ExpressionConverter (the main class)
 * which should contain the error message on a JOptionPane window itself as said above.
 */


//Package name
package cmsc350_project1_hermanmann;

public class SyntaxError extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SyntaxError() {
		super();
	}
	
	public SyntaxError(String message_Displayed) {
		super(message_Displayed);
	}
	

}
