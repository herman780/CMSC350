/*
 * Project: # 1 - Converting Prefix expressions to postfix and postfix to prefix. 
 * Filename: PrefixToPostfix.java
 * Author: Herman Mann
 * Date: 01/24/2022
 * Description: This is a Java program consisted of the variables and methods 
 * that convert a prefix expression to the postfix expression
 */

//Package name
package cmsc350_project1_hermanmann;

import java.io.IOException;
//Import files
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class to convert a prefix expression to the postfix expression
 */
public class PrefixToPostfix {

    //Postfix expression
    public static String postfixExpression = "";
    //Reversal stack to store an expression
    public static Stack<String> reversalStack = null;
    //Operand stack to store operands
    public static Stack<String> operandStack = null;

    /**
     * Constructor to initialize variables and objects *
     */
    public PrefixToPostfix() {
        //Initialize variables and objects
        postfixExpression = Utility.STR_EMPTY;
        reversalStack = new Stack<>();
        operandStack = new Stack<>();
    }

    /**
     * To convert a prefix expression to the postfix expression
     *
     * @param expression - a prefix expression
     * @return - a postfix expression
     * @throws java.lang.Exception
     */
    public String Convert(String expression) throws IOException, SyntaxError {
        try {
            //tokenize the string containing the expression
            StringTokenizer stringTokenizer = Utility.CreateStringTokenizer(expression);
            //while there are more tokens
            while (stringTokenizer.hasMoreTokens()) {
                //get the next token
                String nextToken = stringTokenizer.nextToken();
                //push the token onto a reversal stack if it is not a space            
                if (!Utility.STR_SPACE.equals(nextToken)) {
                    reversalStack.push(nextToken);
                }
            }
            //while the reversal stack is not empty
            while (!reversalStack.empty()) {
                //pop the next token from the reversal stack
                String nextToken = reversalStack.pop();
                //if it is an operand, push it onto the operand stack
                if (!Utility.IsOperator(nextToken.charAt(0))) {
                    //push it onto the operand stack
                    operandStack.push(nextToken);
                } else {
                    //if it is an operator, pop two operands off of the operand stack
                    String operand1 = operandStack.pop();
                    String operand2 = operandStack.pop();
                    //create a string with the two operands followed the operator
                    String strPostfix1 = operand1 + Utility.STR_SPACE + operand2 + Utility.STR_SPACE + nextToken;
                    //push that string onto the operand stack
                    operandStack.push(strPostfix1);
                }
            }
            //pop the postfix expression off the stack
            postfixExpression = operandStack.pop();
            //Check if stack is empty or not. If not empty throw exception
            if (!operandStack.isEmpty()) {
                //throw an exception if stack is not empty
            	throw new SyntaxError(Utility.MSG_NON_EMPTY_STACK);
            }
            //retun postfix expression
            return postfixExpression;
        } catch (EmptyStackException ex) {
            //throw an exception if stack is empty
            throw new SyntaxError("Enter an expression, Stack is Empty.");
        }
        catch (Exception ex) {
            //throw an exception
            throw ex;
        }
    }

}
