/*
 * Author: Herman Mann
 * Project: # 2 - Involves the writing of a program which will have the ability to
 * examine a file of polynomials and will be able to determine whether the
 * polynomials in that specific file are in ascending order using two different
 * methods of comparison. 
 * Filename: Utility.java
 * Date: 02/06/2022
 * Description: This is a Java class that contains the application of string and message resources.
 * It also contains the CreateStringTokenizer method to generate token for an expression.
 */


//Package name 
package cmsc350_hermanmann_project2;

//Import files
import java.text.DecimalFormat;
import java.util.StringTokenizer;


/**
 * Class that contains the application string and message resources. It also
 * contains the CreateStringTokenizer method to generate token for an
 * expression.
 */
public class Utility {

    //Declare and initialize constants for string and message resources
    public final static String MSG_INCORRECT_SYNTAX = "Incorrect Syntax, Please enter a valid polynomial";
    public final static String MSG_INVALID_EXPONENT = "Invalid exponent, Please enter a valid polynomial";    
    public final static String MSG_INVALID_EXPONENT_NEGATIVE = "Negative exponent is not allowed, Please enter a valid polynomial";   
    public final static String MSG_INVALID_EXPONENT_ORDER = "Exponents should be in decreasing order, Please enter a valid polynomial";   
    public final static String MSG_INVALID_DATA_TYPE = "Coefficients or exponents of an improper type, Please enter a valid polynomial";
    public final static String MSG_ERROR_EMPTY_FILE = "File is empty, Please choose a valid file";
    public final static String MSG_ERROR_FILE_NOT_EXIST = "File not found, Please choose a valid file";
    public final static String MSG_TITLE_ERROR = "ERROR ";
    public final static String MSG_STRONG_ORDER = "Polynomials are Strongly Ordered = ";
    public final static String MSG_WEAK_ORDER = "Polynomials are Weakly Ordered = ";

    public final static String STR_EMPTY = "";
    public final static String STR_SPACE = " ";
    public final static String STR_TOKEN_SEPARATOR = " ";

    public final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");
    public final static String STR_VARIABLE = "x";
    public final static String STR_VARIABLE_WITH_POWER = "x^";
    public final static String STR_PLUS_SIGN = " + ";
    public final static String STR_USER_HOME = "user.home";
    public final static int INT_NO_EXPONENT_NO_VARIABLE = 0;
    public final static int INT_NO_EXPONENT_ONLY_VARIABLE = 1;
    public final static int INT_NEGATIVE_RESULT = -1;
    public final static int INT_POSITIVE_RESULT = 1;
    public final static int INT_EQUAL_RESULT = 0;

    /**
     * To create string token for an expression
     *
     * @param expression - An expression to be token
     * @return - A string with tokens
     */
    public static StringTokenizer CreateStringTokenizer(String expression) {
        return new StringTokenizer(expression, STR_TOKEN_SEPARATOR, true);
    }
}
