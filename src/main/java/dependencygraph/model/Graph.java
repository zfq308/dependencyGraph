package dependencygraph.model;

import java.util.List;

/**
 * Graph interface that defines list of operations available with the graph
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public interface Graph {

    /**
     * Adds a directed edge starting from the from vertex to the to graph
     *
     * @param from the from vertex for the edge
     * @param to   the to vertex for the edge
     * @return true or false based on if the addition was successful
     */
    boolean addEdge(Vertex from, Vertex to);

    /**
     * Add the given edge to the graph
     *
     * @param edge the edge to be added to the graph
     * @return true or false based on if the addition was successful
     */
    boolean addEdge(Edge edge);

    /**
     * Returns a list of reachable vertices from the given vertex. If no vertices
     * are reachable an empty list is returned
     *
     * @param from the from vertex
     * @return a list of vertices reachable from the given vertex
     */
    List<Vertex> getReachableVertices(Vertex from);


}
