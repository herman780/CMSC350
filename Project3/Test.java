/*
 * Project: # 3
 * Filename: Test.java
 * Author:  Herman Mann
 * Date: 02/21/2022
 * Description: This is a Test java class that contains the main method.
 * It should create a GUI that allows the user to input a tree in the above
 * described format and then construct the tree once the Make Tree button is clicked.
 */

//Package name
package cmsc350_project3_hermanmann;

//Import files
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * This is a Test java class that contains the main method.
 * It should create a GUI that allows the user to input a tree in the above
 * described format and then construct the tree once the Make Tree button is clicked.
 */
public class Test extends JFrame {

    //Declare variables for lables 
    private JLabel labelUserInput;
    private JLabel labelOutputDisplay;

    //Declare variables for text fields 
    private JTextField textFieldUserInput;
    private JTextField textFieldOutputDisplay;

    //Declare variables for buttons 
    private JButton buttonMakeTree;
    private JButton buttonIsBalanced;
    private JButton buttonIsFull;
    private JButton buttonIsProper;
    private JButton buttonHeight;
    private JButton buttonNodes;
    private JButton buttonInorder;

    private BinaryTree binaryTree;
    private boolean isUserInputChanged = false;

    /**
     * constructor to create and initialize the GUI
     */
    public Test() {
        super(Utility.STR_TITLE);
        setSize(630, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        labelUserInput = new JLabel(Utility.STR_USER_INPUT);
        labelOutputDisplay = new JLabel(Utility.STR_OUTPUT_DISPLAY);
        textFieldUserInput = new JTextField(30);
        textFieldOutputDisplay = new JTextField(30);
        buttonMakeTree = new JButton(Utility.STR_MAKE_TREE);
        buttonIsBalanced = new JButton(Utility.STR_IS_BALANCED);
        buttonIsFull = new JButton(Utility.STR_IS_FULL);
        buttonIsProper = new JButton(Utility.STR_IS_PROPER);
        buttonHeight = new JButton(Utility.STR_HEIGHT);
        buttonNodes = new JButton(Utility.STR_NODES);
        buttonInorder = new JButton(Utility.STR_INORDER);
        JComponent[] componentUserInputs = {
            labelUserInput,
            textFieldUserInput
        };
        JComponent[] componentOutputDisplays = {
            labelOutputDisplay,
            textFieldOutputDisplay
        };
        JButton[] buttons = {
            buttonMakeTree,
            buttonIsBalanced,
            buttonIsFull,
            buttonIsProper,
            buttonHeight,
            buttonNodes,
            buttonInorder
        };
        createPanel(componentUserInputs);
        createPanel(buttons);
        createPanel(componentOutputDisplays);
        //add action listener to all the button controls
        for (JButton button : buttons) {
            button.addActionListener(treeListener);
        }
        textFieldOutputDisplay.setEditable(false);
        //add key listener for the user input text box
        textFieldUserInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                isUserInputChanged = true;
            }
        });
        setResizable(false);
    }

    /**
     * to create panel for each component of the GUI
     *
     * @param components - collection of components
     */
    private void createPanel(JComponent[] components) {
        JPanel mainPanel = new JPanel(new FlowLayout());
        for (Component component : components) {
            mainPanel.add(component);
        }
        add(mainPanel);
    }

    /**
     * to perform the tree operations based on the button click action and set
     * the display output
     */
    public final ActionListener treeListener = event -> {
        try {
            if (!textFieldUserInput.getText().trim().equals(Utility.STR_EMPTY)) {
                switch ((event.getActionCommand())) {
                    case Utility.STR_MAKE_TREE:
                        binaryTree = new BinaryTree(textFieldUserInput.getText());
                        isUserInputChanged = false;
                        textFieldOutputDisplay.setText(binaryTree.toString());
                        break;
                    case Utility.STR_IS_BALANCED:
                        if (isTreeMade()) {
                            textFieldOutputDisplay.setText(String.valueOf(binaryTree.isBalanced()));
                        }
                        break;
                    case Utility.STR_IS_FULL:
                        if (isTreeMade()) {
                            textFieldOutputDisplay.setText(String.valueOf(binaryTree.isFull()));
                        }
                        break;
                    case Utility.STR_IS_PROPER:
                        if (isTreeMade()) {
                            textFieldOutputDisplay.setText(String.valueOf(binaryTree.isProper()));
                        }
                        break;
                    case Utility.STR_HEIGHT:
                        if (isTreeMade()) {
                            textFieldOutputDisplay.setText(String.valueOf(binaryTree.height()));
                        }
                        break;
                    case Utility.STR_NODES:
                        if (isTreeMade()) {
                            textFieldOutputDisplay.setText(String.valueOf(binaryTree.nodes()));
                        }
                        break;
                    case Utility.STR_INORDER:
                        if (isTreeMade()) {
                            textFieldOutputDisplay.setText(binaryTree.inOrder());
                        }
                        break;
                }
            } else {
                String errorMessage = Utility.MSG_EMPTY_INPUT;
                //Show error dialog message
                JOptionPane.showMessageDialog(this, errorMessage, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            }
        } catch (InvalidTreeSyntax ex) {
            String errorMessage = ex.getMessage();
            //Show error dialog message
            JOptionPane.showMessageDialog(this, errorMessage, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            binaryTree = null;
            setEmptyDisplayText();
        } catch (IndexOutOfBoundsException ex) {
            String errorMessage = Utility.MSG_EMPTY_INPUT;
            //Show error dialog message
            JOptionPane.showMessageDialog(this, errorMessage, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            setEmptyDisplayText();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            //Show error dialog message
            JOptionPane.showMessageDialog(this, errorMessage, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            setEmptyDisplayText();
        }
    };

    /**
     * to check whether tree is made or not
     *
     * @return - true if tree made else false
     */
    private boolean isTreeMade() {
        if (binaryTree == null || isUserInputChanged) {
            String errorMessage = isUserInputChanged ? Utility.MSG_USER_INPUT_CHANGED : Utility.MSG_TREE_NOT_MADE;
            //Show error dialog message
            JOptionPane.showMessageDialog(this, errorMessage, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            setEmptyDisplayText();
            return false;
        } else {
            return true;
        }
    }

    /**
     * to set display output empty
     */
    private void setEmptyDisplayText() {
        textFieldOutputDisplay.setText(Utility.STR_EMPTY);
    }

    /**
     * Main method as an entry point for the program
     *
     * @param args as an array of command line arguments
     */
    public static void main(String[] args) {
        //Create an object of the test
        Test test = new Test();
        //Set test from to visible
        test.setVisible(true);
        //Set close operation
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
