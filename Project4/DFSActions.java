/*
 * Project: #4
 * Filename: DFSActions.java
 * Author:  Herman Mann
 * Date: 03/07/2022
 * Description: This is a generic interface named DFSActions,
 * whose generic parameter again specifies the type of the labels that are 
 * associated with the vertices of the graph. It should contain four method 
 * signatures that correspond to the four actions performed in the 
 * depth first search: cycle detected, process vertex, descend and ascend.
 */
//Package name
package cmsc350_project4_hermanmann;

/**
 * This is a generic interface named DFSActions,
 * whose generic parameter again specifies the type of the labels that are 
 * associated with the vertices of the graph. It should contain four method 
 * signatures that correspond to the four actions performed in the 
 * depth first search: cycle detected, process vertex, descend and ascend.
 * 
 * @param <V> - vertex of the graph
 */
public interface DFSActions<V> {
    
    /**
     * To process a vertex of the graph
     * 
     * @param vertex - vertex of the graph
     */
    public void processVertex(V vertex);
    
     /**
     * To descend a vertex of the graph
     * 
     * @param vertex - vertex of the graph
     */
    public void descendVertex(V vertex);
    
     /**
     * To ascend a vertex of the graph
     * 
     * @param vertex - vertex of the graph
     */
    public void ascendVertex(V vertex);
    
     /**
     * To detect cycle in the graph
     */
    public void cycleDetected();
}
