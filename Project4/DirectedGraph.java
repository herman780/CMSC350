/*
 * Project: #4
 * Filename: DirectedGraph.java
 * Author:  Herman Mann
 * Date: 03/07/2022
 * Description: This is a generic class, whose generic parameter specifies 
 * the type of the labels that are associated with the vertices of the graph. 
 * It should contain a method that allows edges to be added to the graph, 
 * which is how the main method will initially build the graph. 
 * It should also contain a method that performs a depth-first search of that graph.
 * Another method in the DirectedGraph class should then allow the main method 
 * to display any unreachable classes by examining all the vertices of 
 * the graph to see which remain undiscovered.
 */
//Package name
package cmsc350_project4_hermanmann;

//Import files
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a generic class, whose generic parameter specifies the type of the
 * labels that are associated with the vertices of the graph. It should contain
 * a method that allows edges to be added to the graph, which is how the main
 * method will build the graph. It should also contain a method that performs a
 * depth-first search of that graph. Another method in the DirectedGraph class
 * should then allow the main method to display any unreachable classes by
 * examining all the vertices of the graph to see which remain undiscovered.
 */
public class DirectedGraph<V> {

    //Collection of adjacency items of the graph
    private Map<V, ArrayList<V>> adjacencyList = new HashMap<>();
    //Hierarchy object
    private Hierarchy hierarchy = new Hierarchy();
    //ParenthesizedList object
    private ParenthesizedList parenthesizedList = new ParenthesizedList();
    //Collection of vertices
    private Map<String, V> vertices = new HashMap<>();
    //Collection of vertices that are discovered
    private Set<V> discoveredVertices = new HashSet<>();
    //Collection of vertices that are visited
    private Set<V> visitedVertices = new HashSet<>();
    //Whether a cycle exist in a graph or not
    private boolean isCycleExist;
    //Starting vertex
    private V startingVertex = null;
    //Collection of unreachable vertices
    private List<String> unreachableVertices = new ArrayList<>();

    /**
     * To get the starting vertex
     *
     * @return - starting vertex of the graph
     */
    public V getStartingVertex() {
        return startingVertex;
    }

    /**
     * To set the starting vertex of the graph
     *
     * @param startingVertex - starting vertex of the graph
     */
    public void setStartingVertex(V startingVertex) {
        this.startingVertex = startingVertex;
    }

    /**
     * To add an edge from source vertex to destination vertex
     *
     * @param sourceVertex - source vertex
     * @param destinationVertex - destination vertex
     */
    public void addEdge(String sourceVertex, String destinationVertex) {

        //verify if source vertex has already any connected edge
        ArrayList<V> list = adjacencyList.get(getVertex(sourceVertex));
        //If source vertex not have any edge list then create new list to add an edge
        if (list == null) {
            list = new ArrayList<>();
        }
        //add an edge from source to destination
        list.add(getVertex(destinationVertex));
        //update the adjacency list
        adjacencyList.put(getVertex(sourceVertex), list);
    }

    /**
     * To get the vertex from a vertex string name
     *
     * @param vertexName - vertex string name
     * @return - a generic vertex
     */
    public V getVertex(String vertexName) {
        //new vertex then add to the vertices
        if (!vertices.containsKey(vertexName)) {
            vertices.put(vertexName, (V) new Vertex(vertexName));
        }
        //get the vertex for the given vertex name
        return vertices.get(vertexName);
    }

    /**
     * To perform depth first search with default values
     */
    public void depthFirstSearch() {

        //Set graph cycle as false
        isCycleExist = false;
        //Start depth first search with starting vertex
        depthFirstSearch(startingVertex);
    }

    /**
     * To search a vertex in the adjacency list using depth first order approach
     *
     * @param vertexToSearch - vertex to search in the adjacency list
     */
    private void depthFirstSearch(V vertexToSearch) {
        //Cast to vertex
        Vertex vertex = (Vertex) vertexToSearch;
        //Check if cycle exist for the vertex or not
        if (discoveredVertices.contains(vertexToSearch)) {
            isCycleExist = true;
            //call DFS action cycle detected
            hierarchy.cycleDetected();
            parenthesizedList.cycleDetected();
            return;
        }
        //call DFS action process vertex 
        hierarchy.processVertex(vertex);
        parenthesizedList.processVertex(vertex);
        //call DFS action descend vertex
        hierarchy.descendVertex(vertex);
        parenthesizedList.descendVertex(vertex);
        //add vertex to discovered vertices list
        discoveredVertices.add(vertexToSearch);
        //add vertex to visited vertices list
        visitedVertices.add(vertexToSearch);
        //Now, search for all its child vertex using recurrsion approach
        ArrayList<V> list = adjacencyList.get(vertexToSearch);
        if (list != null) {
            list.stream().forEach((u) -> {
                depthFirstSearch(u);
            });
        }
        //call DFS action ascend vertex
        hierarchy.ascendVertex(vertex);
        parenthesizedList.ascendVertex(vertex);
        //remove vertex from discovered vertices list
        discoveredVertices.remove(vertex);
    }

    /**
     * To update unreachable vertices
     */
    private void updateUnreachableVertices() {
        unreachableVertices.clear();
        //check for each item in the adjacency list for the unvisited or undiscovered vertex
        for (Map.Entry<V, ArrayList<V>> item : adjacencyList.entrySet()) {
            if (item.getValue().size() > 0) {
                //check for the item itself
                addUnreachableVertex(item.getKey());
                //check all of it's adjacent vertices
                for (V vertex : item.getValue()) {
                    addUnreachableVertex(vertex);
                }
            }
        }
    }

    /**
     * To add unreachable vertex if there any
     *
     * @param vertex - vertex to verify
     */
    private void addUnreachableVertex(V vertex) {
        //check for the item itself
        if (!visitedVertices.contains(vertex)) {
            unreachableVertices.add(vertex.toString());
            visitedVertices.add(vertex);
        }
    }
    
    /**
     * To print the hierarchy representation of the graph
     */
    public void printHierarchy() {
        System.out.print(hierarchy.toString());
    }

    /**
     * To print the parenthesized representation of the graph
     */
    public void printParenthesizedList() {
        System.out.println(parenthesizedList.toString());
    }

    /**
     * To print unreachable vertices
     */
    public void printUnreachableVertices() {
        updateUnreachableVertices();
        if (unreachableVertices.size() > 0) {
            for (String unreachableVertex : unreachableVertices) {
                System.out.println(unreachableVertex + Utility.STR_UNREACHABLE);
            }
        } else {
            System.out.println(Utility.STR_REACHABLE);
        }

    }
}