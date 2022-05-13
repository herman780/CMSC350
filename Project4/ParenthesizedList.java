/*
 * Project: #4
 * Filename: ParenthesizedList.java
 * Author:  Herman Mann
 * Date: 03/07/2022
 * Description: This is class implement the DFSActions interface. it should 
 * produce a parenthesized representation of the class dependencies. 
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
 * This is class implement the DFSActions interface. it should produce a
 * parenthesized representation of the class dependencies. Circular dependencies
 * should be flagged.It should override the toString method, which should return
 * a string that contains the vertices, after having performed the depth-first
 * search.
 */
public class ParenthesizedList implements DFSActions<Vertex> {

    //Contents of the ParenthesizedList    
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
     * To get graph as ParenthesizedList formatted string
     *
     * @return - graph as a formatted string
     */
    @Override
    public String toString() {
        String result = Utility.STR_EMPTY;
        result += Utility.STR_OPEN_PARENTHESIS + Utility.STR_SPACE;
        while (contents.size() > 0) {
            //Get peek content
            String content = contents.peek();
            //Remove head
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
            //add content to get string result
            result += content + Utility.STR_SPACE;
        }
        //add content to get string result
        result += Utility.STR_CLOSED_PARENTHESIS + Utility.STR_NEWLINE;
        return result;
    }
}