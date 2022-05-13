/*
 * Project: # 3
 * Filename: Utility.java
 * Author:  Herman Mann
 * Date: 02/21/2022
 * Description: This is a Java class contains the application string and message resources.
 * It also contains the CreateStringTokenizer method to generate token for an expression.
 */

//Package name
package cmsc350_project3_hermanmann;

//Import files
import java.util.StringTokenizer;

/**
 * Class that contains the application string and message resources. It also
 * contains the CreateStringTokenizer method to generate token for an
 * expression.
 */
public class Utility {

    //Declare and initialize constants for string and message resources
    public final static String STR_TITLE = "Binary Tree Categorizer";
    public final static String STR_USER_INPUT = "Enter Expression";
    public final static String STR_OUTPUT_DISPLAY = "Output";
    public final static String STR_MAKE_TREE = "Make Tree";
    public final static String STR_IS_BALANCED = "Is Balanced?";
    public final static String STR_IS_FULL = "Is Full?";
    public final static String STR_IS_PROPER = "Is Proper?";
    public final static String STR_HEIGHT = "Height";
    public final static String STR_NODES = "Nodes";
    public final static String STR_INORDER = "Inorder";
    public final static String MSG_TREE_NOT_MADE = "Tree is not made yet. Please first make tree";
    public final static String MSG_USER_INPUT_CHANGED = "User input changed. Please first make tree";
    public final static String MSG_EMPTY_INPUT = "No, input is provided. Please enter a valid tree expression";
    public final static String MSG_INCORRECT_SYNTAX = "Input has incorrect syntax. Please enter a valid tree expression";
    public final static String MSG_INVALID_PARENTHESIS = "Input has invalid parenthesis. Please enter a valid tree expression";
    public final static String MSG_INVALID_NODES_SYNTAX = "Binary tree can have only two child nodes";
    public final static String MSG_TITLE_ERROR = "ERROR ";
    public final static String STR_EMPTY = "";
    public final static String STR_SPACE = " ";
    public final static String STR_OPEN_PARENTHESIS = "(";
    public final static String STR_CLOSED_PARENTHESIS = ")";
    public final static String STR_TOKEN_SEPARATOR = "()";

    /**
     * To create string token for an expression
     *
     * @param expression - An expression to be token
     * @return - A string with tokens
     */
    public static StringTokenizer CreateStringTokenizer(String expression) {
        return new StringTokenizer(expression, STR_TOKEN_SEPARATOR, true);
    }

    /**
     * To get next valid token from the string tokenizer by eliminating the
     * space token
     *
     * @param stringTokenizer - string tokenizer
     * @return - a valid string token
     */
    public static String GetNextValidToken(StringTokenizer stringTokenizer) {
        String token = Utility.STR_EMPTY;
        //loop until the has more tokens
        while (stringTokenizer.hasMoreTokens()) {
            //get next token
            token = stringTokenizer.nextToken();
            //check for space and break the loop once get a valid token
            if (!token.equals(Utility.STR_SPACE)) {
                break;
            }
        }
        return token;
    }

}
