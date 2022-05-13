/*
 * Project: # 1 - Converting Prefix expressions to postfix and postfix to prefix. 
 * Filename: Utility.java
 * Author: Herman Mann
 * Date: 01/24/2022
 * Description: This is a Java program consist of the Project #1 that contains 
 * the application string and message resources.
 * It also contains the CreateStringTokenizer method to generate token for an expression.
 * Another method isOperator is also used to check whether a char is operator or not.
 */

//Package name
package cmsc350_project1_hermanmann;


//Import files
import java.util.StringTokenizer;

/**
 * Class that contains the application string and message resources.
 * It also contains the CreateStringTokenizer method to generate token for an expression.
 * Another method isOperator is also used to check whether a char is operator or not.
 */
public class Utility {

    //Declare and initialize constants for string and message resources
    public final static String STR_TITLE="Expression Converter";
    public final static String STR_ENTER_EXPRESSION="Enter Expression";
    public final static String STR_PREFIX_TO_POSTFIX="Prefix to Postfix";
    public final static String STR_POSTFIX_TO_PERFIX="Postfix to Prefix";
    public final static String STR_RESULT="Result";
    
    public final static String MSG_TITLE_ERROR = "ERROR - "; 
    public final static String MSG_EMPTY_STACK = "Empty Stack"; 
    public final static String MSG_NON_EMPTY_STACK = "Non Empty Stack";    
    public final static String MSG_EMPTY_EXPRESSION = "Please enter a valid expression";
    
    public final static String STR_EMPTY = "";
    public final static String STR_SPACE = " ";
    public final static String STR_TOKEN_SEPARATOR = "/*+-^ ";

    /**
     * To create string token for an expression
     * 
     * @param expression - An expression to be token
     * @return - A string with tokens
     */
    public static StringTokenizer CreateStringTokenizer(String expression) {
        return new StringTokenizer(expression,STR_TOKEN_SEPARATOR, true);
    }

    /**
     * To check whether the given token is a operator or not
     * 
     * @param token - token to be verified
     * @return - True if token is an operator else false
     */
        public static boolean IsOperator(char token) {
        switch (token) {
            case '-':
            case '+':
            case '/':
            case '*':
            case '^':
                return true;
        }
        return false;
    }

}
