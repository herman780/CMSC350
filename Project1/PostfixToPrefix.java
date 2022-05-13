/*
 * Project: # 1 - Converting Prefix expressions to postfix and postfix to prefix. 
 * Filename: PostfixToPrefix.java
 * Author: Herman Mann
 * Date: 01/24/2022
 * Description: This is a Java program consisted of the variables and methods 
 * that convert a postfix expression to the prefix expression
 */


//Package name
package cmsc350_project1_hermanmann;

import java.io.IOException;
//Import files
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * Class to convert a postfix expression to the prefix expression
 */
public class PostfixToPrefix {

    //Prefix expression
    private String prefixExpression = "";
    //Stack to store operands
    private Stack<String> operandStack = null;

    /**
     * Constructor to initialize variables and objects *
     */
    public PostfixToPrefix() {
        prefixExpression = Utility.STR_EMPTY;
        operandStack = new Stack<>();
    }

    /**
     * To convert a postfix expression to the prefix expression
     *
     * @param expression - a postfix expression
     * @return - a prefix expression
     * @throws java.lang.Exception
     */
    public String Convert(String expression) throws IOException, SyntaxError {
        try {
            //tokenize the string containing the prefix expression
            StringTokenizer stringTokenizer = Utility.CreateStringTokenizer(expression);
            //while there are more tokens
            while (stringTokenizer.hasMoreTokens()) {
                //get the next token
                String nextToken = stringTokenizer.nextToken();
                //if it is a space,skip it
                if (Utility.STR_SPACE.equals(nextToken)) {
                    //Skip for space token
                } else {
                    //if it is an operand, push it onto the operand stack
                    if (!Utility.IsOperator(nextToken.charAt(0))) {
                        operandStack.push(nextToken);
                    } else {
                        //it is an operator, pop two operands off of the operand stack
                        String operand1 = operandStack.pop();
                        String operand2 = operandStack.pop();
                        //create a prefix string with the operator followed by two operands
                        prefixExpression = nextToken + Utility.STR_SPACE + operand2 + Utility.STR_SPACE + operand1;
                        //push perfix string onto the operand stack
                        operandStack.push(prefixExpression);
                    }
                }
            }
            //pop the prefix expression off the stack
            prefixExpression = operandStack.pop();
            //Check if stack is empty or not. If not empty throw exception
            if (!operandStack.isEmpty()) {
                //throw an exception if stack is not empty
                throw new Exception(Utility.MSG_NON_EMPTY_STACK);
            }
            //retun perfix expression
            return prefixExpression;
        } catch (EmptyStackException ex) {
            //throw an exception if stack is empty
            throw new SyntaxError(Utility.MSG_EMPTY_STACK);
        } catch (Exception ex) {
            //throw an exception
            throw new SyntaxError("Enter something");
        }
    }
}
