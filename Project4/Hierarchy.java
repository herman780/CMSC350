/*
 * Project: #4
 * Filename: Hierarchy.java
 * Author:  Herman Mann
 * Date: 03/07/2022
 * Description: This is class implement the DFSActions interface. It should 
 * produce a hierarchical representation of the class dependencies. 
 * Circular dependencies should be flagged.It should override the toString method, 
 * which should return a string that contains the vertices, after having performed 
 * the depth-first search.
 */
//Package name
package cmsc350_project4_hermanmann;

//Import files
import java.util.LinkedList;
import java.util.Queue;

/**
 * This is class implement the DFSActions interface. It should produce a
 * hierarchical representation of the class dependencies. Circular dependencies
 * should be flagged.It should override the toString method, which should return
 * a string that contains the vertices, after having performed the depth-first
 * search.
 */
public class Hierarchy implements DFSActions<Vertex> {

    //Contents of the hierarcy
    Queue<String> contents = new LinkedList<>();

    /**
     * To add a vertex of the graph to the contents
     *
     * @param vertex - vertex of the graph
     */
    @Override
    public void processVertex(Vertex vertex) {
        contents.add(vertex.toString());
    }

    /**
     * To add descend to the contents
     *
     * @param vertex - vertex of the graph
     */
    @Override
    public void descendVertex(Vertex vertex) {
        contents.add(Utility.STR_OPEN_PARENTHESIS);
    }

    /**
     * To add ascend to the contents
     *
     * @param vertex - vertex of the graph
     */
    @Override
    public void ascendVertex(Vertex vertex) {
        contents.add(Utility.STR_CLOSED_PARENTHESIS);
    }

    /**
     * To add asterisk to the contents
     */
    @Override
    public void cycleDetected() {
        contents.add(Utility.STR_ASTERISK);
    }

    /**
     * To get graph as hierarchy formatted string
     *
     * @return - graph as a formatted string
     */
    @Override
    public String toString() {
        String result = Utility.STR_EMPTY;
        int numberOfTabs = 0;
        //Loop fro all the contents
        while (contents.size() > 0) {
            //Get peek content
            String content = contents.peek();
            //Remove contents head value
            contents.remove();
            //Check for content value
            if (Utility.STR_OPEN_PARENTHESIS.equals(content)) {
                //Check for peek value
                if (Utility.STR_CLOSED_PARENTHESIS.equals(contents.peek())) {
                    //Remove head
                    contents.remove();
                    continue;
                } else if (Utility.STR_ASTERISK.equals(contents.peek())) {
                    //get peek value and make string result
                    result += contents.peek() + Utility.STR_SPACE;
                    //Remove two heads
                    contents.remove();
                    contents.remove();
                    continue;
                }
            }
            //Check for content values
            if (Utility.STR_OPEN_PARENTHESIS.equals(content)) {
                numberOfTabs++;
            } else if (Utility.STR_CLOSED_PARENTHESIS.equals(content)) {
                --numberOfTabs;
            }
            //Check for content value
            if (Utility.STR_OPEN_PARENTHESIS.equals(content) || Utility.STR_CLOSED_PARENTHESIS.equals(content)) {
                continue;
            }
            //Check for content value
            if (!Utility.STR_ASTERISK.equals(content)) {
                result += Utility.STR_NEWLINE;
            }
            //Loop through to add tabs in string result
            for (int index = 0; index < numberOfTabs; index++) {
                result += Utility.STR_TAB;
            }
            //add content to get string result
            result += content + Utility.STR_SPACE;
        }
        //add content to get string result
        result += Utility.STR_NEWLINE;
        return result;
    }
}