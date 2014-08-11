package dependencygraph.utils;

import dependencygraph.model.Graph;
import dependencygraph.model.Vertex;

/**
 * Interface for rendering a give graph representation
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public interface DependencyGraphRenderer {

    /**
     * Renders the given graph representation by traversing the graph starting at the
     * given start vertex
     *
     * @param representation the graph representation to be traversed
     * @param start          the vertex to start the graph traversal with
     * @return the string rendering of the given graph
     */
    String renderGraph(Graph representation, Vertex start);

}
