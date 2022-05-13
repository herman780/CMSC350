/*
 * Author Name: Herman Mann
 * Project: # 2 - Involves the writing of a program which will have the ability to
 * examine a file of polynomials and will be able to determine whether the
 * polynomials in that specific file are in ascending order using two different
 * methods of comparison.
 * Filename: Test.java
 * Date: 02/06/2022
 * Description: This is a Test java class that contains the main method. 
 * The main method should allow the user to select the input file from the default 
 * directory by using an object of the JFileChooser class. It should then populate 
 * an ArrayList of objects of type Polynomial as it reads in the lines of the file. 
 * As the polynomials are read in, they should also be displayed in the format 
 * provided by the toString method. Should the InvalidPolynomialSyntax exception 
 * be thrown, it should be caught and a JOptionPane message should be displayed 
 * containing the reason for the invalid syntax. The list of polynomials should be 
 * then checked to see whether it is in sorted order according to two orderings. 
 * The first check is the one that uses the compareTo method of the Polynomial class. 
 * We refer to this first ordering as the strong order. The second results from
 * the use of a comparator. We refer to it as the weak order. It should display 
 * whether it fails to be sorted by either of both of the two orderings or if it is 
 * sorted according to both.
 */

//Package Name
package cmsc350_hermanmann_project2;

//Import files
import cmsc350_hermanmann_project2.Utility;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class contains the main method.The main method should allow the user to
 * select the input file from the default. directory by using an object of the
 * JFileChooser class. It should then populate an ArrayList of objects of type
 * Polynomial as it reads in the lines of the file. As the polynomials are read
 * in, they should also be displayed in the format provided by the toString
 * method. Should the InvalidPolynomialSyntax exception be thrown, it should be
 * caught and a JOptionPane message should be displayed containing the reason
 * for the invalid syntax. The list of polynomials should be then checked to see
 * whether it is in sorted order according to two orderings. The first check is
 * the one that uses the compareTo method of the Polynomial class. We refer to
 * this first ordering as the strong order. The second results from the use of a
 * comparator. We refer to it as the weak order. It should display whether it
 * fails to be sorted by either of both of the two orderings or if it is sorted
 * according to both.
 */
public class MainMethod {

    //Collection of polynomial list
    private List<Polynomial> polynomialList = new ArrayList<>();

    /**
     * To perform polynomial list data comparison and print whether polynomials
     * are strongly or weekly ordered
     */
    public void performPolynomialListComparison() {
        try {
            //iterator through each polynomial in the polynomial data line
            getPolynomialDataLines().stream().map((polynomialDataLine) -> new Polynomial(polynomialDataLine)).map((polynomial) -> {
                //print the polynomial
                printPolynomial(polynomial);
                return polynomial;
            }).forEach((polynomial) -> {
                polynomialList.add(polynomial);
            });
            //print whether polynomials are weakly ordered
            printWeaklyOrdered();
            //print whether polynomials are strongly ordered
            printStronglyOrdered();
        } catch (InvalidPolynomialSyntax ex) {
            //show message dialog
            JOptionPane.showMessageDialog(null, ex.getMessage(), Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * To get the polynomial data lines form the selected input file
     *
     * @return - an array list of string that contains the polynomial data lines
     */
    private ArrayList<String> getPolynomialDataLines() {
        //array list to hold polynomial data lines
        ArrayList<String> polynomialDataLines = new ArrayList<>();
        //file chooser
        JFileChooser fileChooser = new JFileChooser();
        //Select the files and directories
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //set the current directory
        fileChooser.setCurrentDirectory(new File(System.getProperty(Utility.STR_USER_HOME)));
        //dialog response result
        int dialogResponse = fileChooser.showOpenDialog(null);
        //check for the dialog response result
        if (dialogResponse == JFileChooser.APPROVE_OPTION) {
            //get selected file
            File file = fileChooser.getSelectedFile();
            try {
                //scan file
                Scanner fileScanner = new Scanner(file);
                if (file.isFile()) {
                    //Loop until has next line
                    while (fileScanner.hasNextLine()) {
                        String expressionLine = fileScanner.nextLine();
                        //add to polynomial data lines
                        polynomialDataLines.add(expressionLine);
                    }
                }
            } catch (NoSuchElementException ex) {
                //show message dialog
                JOptionPane.showMessageDialog(null, Utility.MSG_ERROR_EMPTY_FILE, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                //show message dialog
                JOptionPane.showMessageDialog(null, Utility.MSG_ERROR_FILE_NOT_EXIST, Utility.MSG_TITLE_ERROR, JOptionPane.ERROR_MESSAGE);
            }
        }
        return polynomialDataLines;
    }

    /**
     * To check whether a polynomial list has polynomials in the weak order or
     * not
     *
     * @return - true if weakly ordered else false
     */
    private boolean isWeaklyOrdered() {
        boolean isWeak = true;
        int length = polynomialList.size();
        Polynomial previousPolynomial = polynomialList.get(length - 1);
        //loop through for each polynomial
        for (int index = length - 2; index > 0; index--) {
            //compare for exponents of polynomials
            if (previousPolynomial.compareExponents(polynomialList.get(index)) < 0) {
                isWeak = false;
            }
        }
        return isWeak;
    }

    /**
     * To print a polynomial
     *
     * @param polynomial - polynomial to print
     */
    private void printPolynomial(Polynomial polynomial) {
        System.out.println(polynomial);
    }

    /**
     * To print whether polynomials are weekly ordered or not
     */
    private void printWeaklyOrdered() {
        System.out.println(Utility.MSG_WEAK_ORDER + isWeaklyOrdered());
    }

    /**
     * To print whether polynomials are strongly ordered or not
     */
    private void printStronglyOrdered() {
        System.out.println(Utility.MSG_STRONG_ORDER + OrderedList.checkSorted(polynomialList));
    }

    /**
     * Main method as an entry point for the program
     *
     * @param args as an array of command line arguments
     */
    public static void main(String[] args) {
        //Create an object of the test class        
    	MainMethod test = new MainMethod();
        //Call the perform polynomial list comparison method
        test.performPolynomialListComparison();
    }

	
	

}
