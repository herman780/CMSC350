/*
 * Project: #4
 * Filename: Test.java
 * Author:  Herman Mann
 * Date: 03/07/2022
 * Description: This is a Test java class that contains the main method.
 * It should allow user select the input file from the default directory by 
 * using the JFileChooser class. It should then add the edges to a directed graph 
 * that defines these class dependencies.
 */
//Package name
package cmsc350_project4_hermanmann;

//Import files
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * This is a Test java class that contains the main method.
 * It should allow user select the input file from the default directory by 
 * using the JFileChooser class. It should then add the edges to a directed graph 
 * that defines these class dependencies.
 */
public class Test {

    //Directed graph object
    private DirectedGraph directedGraph = null;

    /**
     * Constructor to initialize the directed graph
     */
    public Test() {
        directedGraph = new DirectedGraph();
    }

    /**
     * To read the the input file and then build the graph
     *
     * @return - collection of graph expression lines
     */
    public List<String> readInputData() {

        List<String> expressionLines = new ArrayList<>();
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
                        //add to expression lines
                        expressionLines.add(expressionLine);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return expressionLines;
    }

    /**
     * To build the directed graph from the input expression line data
     *
     * @param expressionLine - input expression line data
     */
    private void buildDirectedGraph(String expressionLine) {
        //Collection of edges
        String[] edges = expressionLine.split(Utility.STR_SPACE);
        //Set the starting vertex of the graph
        if (directedGraph.getStartingVertex() == null) {
            directedGraph.setStartingVertex(directedGraph.getVertex(edges[0]));
        }
        //Loop through and add edges to the directed graph
        for (int index = 1; index < edges.length; index++) {
            directedGraph.addEdge(edges[0], edges[index]);
        }
    }

    /**
     * To perform the directed graph operations
     */
    public void performGraphOperations() {
        //perfrom the depth first search
        directedGraph.depthFirstSearch();
        //print the graph in the hierarchy format after depth first search
        System.out.println(Utility.STR_HIERARCHY_FORMAT);
        directedGraph.printHierarchy();
        //print the graph in the parenthesized list format after depth first search
        System.out.println(Utility.STR_PARENTHESIZED_FORMAT);
        directedGraph.printParenthesizedList();
        //print all the unreachable vertices
        System.out.println(Utility.STR_UNREACHABLE_FORMAT);
        directedGraph.printUnreachableVertices();
    }

    /**
     * Main method as an entry point for the program
     *
     * @param args as an array of command line arguments
     */
    public static void main(String[] args) {
        //Create an object of the test
        Test test = new Test();
        //read input file data for graph
        List<String> expressionLines = test.readInputData();
        //build directed graph
        expressionLines.stream().forEach((expressionLine) -> {
            test.buildDirectedGraph(expressionLine);
        });
        //perform graph operations
        test.performGraphOperations();
    }
}
