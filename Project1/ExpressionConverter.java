/*
 * Project: # 1 - Converting Prefix expressions to postfix and postfix to prefix. 
 * Filename: ExpressionConverter.java
 * Author: Herman Mann
 * Date: 01/24/2022
 * Description: This is a Java program that consists of the Project #1 that contains 
 * the main method. It generates the GUI and allows the user
 * to input an expression in either prefix or postfix and convert 
 * it to postfix or prefix, respectively.
 * 
 */

//Package name
package cmsc350_project1_hermanmann;

//Import files
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.IOException;

/**
 * ExpressionConverter Class that display GUI form by extending JFrame and
 * implements actionListener
 */
public class ExpressionConverter extends JFrame implements ActionListener {

    //Declare variables for lables 
    JLabel labelUserInput;
    JLabel labelOutputDisplay;

    //Declare variables for text fields 
    JTextField textFieldUserInput;
    JTextField textFieldOutputDisplay;

    //Declare variables for buttons 
    JButton buttonPrefixToPostfix;
    JButton buttonPostfixToPrefix;

    /**
     * Constructor to initialize GUI components and controls
     */
    public ExpressionConverter() {

        //Initialize and add user input label
        labelUserInput = new JLabel(Utility.STR_ENTER_EXPRESSION);
        labelUserInput.setBounds(30, 10, 100, 20);
        add(labelUserInput);

        //Initialize and add text field for user input
        textFieldUserInput = new JTextField(Utility.STR_EMPTY);
        textFieldUserInput.setBounds(150, 10, 300, 20);
        add(textFieldUserInput);

        //Initialize and add button for prefix to postfix conversion  
        buttonPrefixToPostfix = new JButton(Utility.STR_PREFIX_TO_POSTFIX);
        buttonPrefixToPostfix.setBounds(100, 50, 150, 30);
        buttonPrefixToPostfix.addActionListener(this);
        add(buttonPrefixToPostfix);

        //Initialize and add button for postfix to prefix conversion        
        buttonPostfixToPrefix = new JButton(Utility.STR_POSTFIX_TO_PERFIX);
        buttonPostfixToPrefix.setBounds(270, 50, 150, 30);
        buttonPostfixToPrefix.addActionListener(this);
        add(buttonPostfixToPrefix);

        //Initialize and add output display label
        labelOutputDisplay = new JLabel(Utility.STR_RESULT);
        labelOutputDisplay.setBounds(100, 100, 100, 20);
        add(labelOutputDisplay);

        //Initialize and add output display text field
        textFieldOutputDisplay = new JTextField(Utility.STR_EMPTY);
        textFieldOutputDisplay.setEditable(false);
        textFieldOutputDisplay.setBounds(150, 100, 300, 20);
        add(textFieldOutputDisplay);

        //Set title, layout and bounds
        this.setTitle(Utility.STR_TITLE);
        this.setLayout(null);
        this.setBounds(300, 200, 500, 170);

    }

    /**
     * To perform an action when a specific event is triggered on the register
     * controls
     *
     * @param actionEvent - an action event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Get the source of the action event
        Object source = actionEvent.getSource();
        try {
            //Set empty text
            textFieldOutputDisplay.setText(Utility.STR_EMPTY);
            //Get user input expression
            String expression = textFieldUserInput.getText();
            if (expression.equals(Utility.STR_EMPTY)) {
                //Show error dialog message for invalid input
                JOptionPane.showMessageDialog(this, Utility.MSG_EMPTY_EXPRESSION, Utility.MSG_TITLE_ERROR + Utility.MSG_EMPTY_EXPRESSION, JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Check for source
            if (source == buttonPrefixToPostfix) {
                //Declare and initialize an instance of the class
                PrefixToPostfix prefixToPostfix = new PrefixToPostfix();
                //Call convert method to convert an expression from prefix to postfix.
                textFieldOutputDisplay.setText(prefixToPostfix.Convert(expression));
            } else {
                //Declare and initialize an instance of the class                
                PostfixToPrefix postfixToPrefix = new PostfixToPrefix();
                //Call convert method to convert an expression from postfix to prefix.                
                textFieldOutputDisplay.setText(postfixToPrefix.Convert(expression));
            }
        } catch (IOException | NullPointerException | SyntaxError action_ex) {
            String errorMessage = action_ex.getMessage();
            //Show error dialog message for invalid input
            JOptionPane.showMessageDialog(this, errorMessage, Utility.MSG_TITLE_ERROR + errorMessage, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method as an entry point for the program
     *
     * @param args as an array of command line arguments
     */
    public static void main(String[] args) {
        //Create an object of the expressionConverter
        ExpressionConverter expressionConverter = new ExpressionConverter();
        //Set expressionConverter from to visible
        expressionConverter.setVisible(true);
        //Set close operation
        expressionConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
