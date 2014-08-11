package dependencygraph.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class that represents a graph as an adjacency list with a Map that maps Vertex to a list
 * of vertices that can be accessed from the given vertex
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class AdjacencyList implements Graph {

    private Map<Vertex, List<Vertex>> adjacencyList = new HashMap<Vertex, List<Vertex>>();

    private List<Edge> edges = new LinkedList<Edge>();

    @Override
    public List<Vertex> getReachableVertices(Vertex from) {
        if (from == null) return Collections.emptyList();

        return adjacencyList.get(from);
    }

    @Override
    public boolean addEdge(Vertex from, Vertex to) {
        if (from == null) {
            throw new IllegalArgumentException("Cannot add an edge with null from vertex");
        }

        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).add(to);
        } else {
            LinkedList<Vertex> linkedList = new LinkedList<Vertex>();
            linkedList.add(to);
            adjacencyList.put(from, linkedList);
        }

        return edges.add(new Edge(from, to));
    }

    @Override
    public boolean addEdge(Edge edge) {
        if (edge == null) {
            throw new IllegalArgumentException("Invalid input edge cannot be null");
        }

        return addEdge(edge.getFromVertex(), edge.getToVertex());
    }
}
