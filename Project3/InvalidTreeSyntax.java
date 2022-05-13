/*
 * Project: # 3
 * Filename: InvalidTreeSyntax.java
 * Author:  Herman Mann
 * Date: 02/21/2022
 * Description: This is a InvalidTreeSyntax java class which defines a checked exception.
 * It should be thrown when a invalid string is supplied and the Make Tree button is clicked.
 * It should be caught in the main class and a JOptionPane window should be displayed 
 * that describes the reason for the invalid syntax.
 */

//Package name
package cmsc350_project3_hermanmann;

/**
 * this class defines a checked exception. It should be thrown when a invalid 
 * string is supplied and the Make Tree button is clicked.
 * It should be caught in the main class and a JOptionPane window should be displayed 
 * that describes the reason for the invalid syntax.
 */
public class InvalidTreeSyntax extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * constructor to create and initialize super exception class
     */
    public InvalidTreeSyntax() {
        super();
    }

    /**
     * constructor to create and initialize super exception class and pass exception message
     *
     * @param message - an exception message
     */
    public InvalidTreeSyntax(String message) {
        super(message);
    }
}
