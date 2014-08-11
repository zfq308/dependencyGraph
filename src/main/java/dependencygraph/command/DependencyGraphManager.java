package dependencygraph.command;

import dependencygraph.ApplicationConstants;
import dependencygraph.exception.InvalidDependencyGraphRepresentationException;
import dependencygraph.model.Edge;
import dependencygraph.model.Graph;
import dependencygraph.model.Vertex;
import dependencygraph.utils.DependencyGraphRenderer;
import dependencygraph.utils.InputFormatValidator;
import dependencygraph.utils.InputReader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * Manages and orchestrates the different steps on rendering the graph
 * by calling multiple dependencies.
 * <p/>
 * Mainly calling input reader to read the input. Creating the graph and passing it on to the graph renderer
 * for creating the dependency graph
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class DependencyGraphManager {

    private InputReader inputReader;

    private Graph graph;

    private DependencyGraphRenderer graphRenderer;

    /**
     * Orchestrates reading the input file and creating the graph out of it and finally calling
     * the dependency graphRenderer to render the graph
     * <p/>
     * NOTE: This class also keeps track of the first vertex read and attempts to render the graph by starting the
     * traversal at the first vertex encountered.
     */
    public String generateDependencyGraph(String location) {
        try {
            String[] edges = inputReader.read(location).split(ApplicationConstants.LINE_SEPARATOR);

            Vertex firstVertex = null;
            // Now construct the graph
            for (String singleEdge : edges) {
                if (!InputFormatValidator.validateInputLine(singleEdge)) {
                    throw new InvalidDependencyGraphRepresentationException(String.format("Invalid input format at found on input. Found %s " +
                            "Expected to be in format VertexNameFrom%sVertexNameTo", singleEdge, ApplicationConstants.VERTEX_SEPARATOR_INDICATOR));
                }

                Edge edge = createEdge(singleEdge);

                // if first vertex keep track of it for rendering later
                firstVertex = firstVertex == null ? edge.getFromVertex() : firstVertex;

                graph.addEdge(edge);
            }

            return graphRenderer.renderGraph(graph, firstVertex);

        } catch (Exception e) {
            return "" + ExceptionUtils.getStackTrace(e);
        }
    }

    private Edge createEdge(String edgeRepresentation) {
        String[] array = edgeRepresentation.split(ApplicationConstants.VERTEX_SEPARATOR_INDICATOR);
        return new Edge(new Vertex(array[0]), new Vertex(array[1]));
    }

    @Required
    public void setGraphRenderer(DependencyGraphRenderer graphRenderer) {
        this.graphRenderer = graphRenderer;
    }

    @Required
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Required
    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }

}
