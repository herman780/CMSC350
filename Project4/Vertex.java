/*
 * Project: #4
 * Filename: Vertex.java
 * Author:  Herman Mann
 * Date: 03/07/2022
 * Description: This is class that handles the vertex of the graph
 */
//Package name
package cmsc350_project4_hermanmann;

/**
 *This is class that handle the vertex of the graph
 */
public class Vertex {

    //Vertex name
    private String name;

    /**
     * Constructor to initialize vertex name
     * 
     * @param name- vertex name
     */
    public Vertex(String name) {
        this.name = name;
    }

    /**
     * To get vertex as a formatted string
     *
     * @return - vertex as a formatted string
     */
    @Override
    public String toString() {
        return name;
    }

}