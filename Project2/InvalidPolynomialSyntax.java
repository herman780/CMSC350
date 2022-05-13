/*
 * Author Name: Herman Mann
 * Project:# 2 - Involves the writing of a program which will have the ability to
 * examine a file of polynomials and will be able to determine whether the
 * polynomials in that specific file are in ascending order using two different
 * methods of comparison. 
 * Filename: InvalidPolynomialSyntax.java
 * Date: 02/06/2022
 * Description: This is a Java class that defines 
 * an unchecked exception that contains a constructor that allows a message to be supplied.
 * It is thrown by the constructor of the Polynomial class  should the supplied 
 * string contain coefficients or exponents of an improper type or should the exponents fail to
 * be listed in strictly descending order
 */


//Package name
package cmsc350_hermanmann_project2;

/**
 * This is the class that defines an unchecked exception which will contain a constructor
 * which will allow a message to be supplied.
 * This unchecked exception is thrown by the constructor of the Polynomial 
 * class. This unchecked exception will serve as a message which is supplied for string
 * containing coefficiants or exponents in an improper type of form or should the exponents
 * fail to be listed overall in strictly descending order. 
 * @author Herman Mann 
 *
 */
@SuppressWarnings("serial")
public class InvalidPolynomialSyntax extends RuntimeException {
	
	//Constructor
	public InvalidPolynomialSyntax(String error_message_Supplied) {
		//super method call with error message 
		super(error_message_Supplied);
	}

}
