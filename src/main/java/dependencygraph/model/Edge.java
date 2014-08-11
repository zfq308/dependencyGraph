package dependencygraph.model;

/**
 * Model object that encapsulates an edge of a graph
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class Edge {
    private Vertex fromVertex;
    private Vertex toVertex;

    /**
     * Constructor requires the vertices that make up the edge
     *
     * @param fromVertex the from vertex for the directed edge
     * @param toVertex   the to vertex for the directed edge
     */
    public Edge(Vertex fromVertex, Vertex toVertex) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;

    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }


}

