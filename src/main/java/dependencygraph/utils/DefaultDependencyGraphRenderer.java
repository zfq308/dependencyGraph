package dependencygraph.utils;

import dependencygraph.ApplicationConstants;
import dependencygraph.model.Graph;
import dependencygraph.model.Vertex;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Implements the dependency graph renderer by traversing the graph in DFS fashion
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class DefaultDependencyGraphRenderer implements DependencyGraphRenderer {


    private static final char BLANK_SPACE = ' ';
    private static final char NEXT_GRAPH_LEVEL = '|';
    private static final String UNDERSCORE = "_ ";
    private static final String LAST_VERTEX = "\\";


    /**
     * Walk the dependency graph, starting at A, printing out each dependency.
     * Repeat for each dependency visited, indenting along the way. E.g.
     * If A depends on B and C, and B depends on C and D the output should look like this:
     * <p/>
     * A
     * |_ B
     * |  |_ C
     * |  \_ D
     * |_ C
     *
     * @param representation the graph representation to be traversed
     * @param start          the vertex to start the graph traversal with
     * @return the string representation of the dependency graph
     */
    @Override
    public String renderGraph(Graph representation, Vertex start) {

        Set<Vertex> visited = new HashSet<Vertex>();
        int indent = 0;

        return renderGraph(representation, start, visited, indent, false);
    }


    private String renderGraph(Graph representation, Vertex start, Set<Vertex> visited, int indent, boolean cycleDetected) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(start.getName());

        // Two base cases are if cycle is detected or if all reachable vertices have been rendered
        if (cycleDetected) {
            return stringBuilder.append(ApplicationConstants.CYCLE_INDICATOR).toString();
        }

        List<Vertex> listVertexFrom;
        if ((listVertexFrom = representation.getReachableVertices(start)) == null) {
            return stringBuilder.toString();
        }

        visited.add(start);
        StringBuffer indentSpaces = createSpaces(indent);

        Iterator<Vertex> vertexIterator = listVertexFrom.iterator();

        // Iterate over each of the vertices associated with start vertex and attempt to render each vertex recursively
        while (vertexIterator.hasNext()) {
            Vertex vertex = vertexIterator.next();
            boolean isCycle = visited.contains(vertex);

            stringBuilder.append(ApplicationConstants.LINE_SEPARATOR);

            // Append representation for next level based on if its last level or not
            if (vertexIterator.hasNext()) {
                stringBuilder.append(indentSpaces).append(NEXT_GRAPH_LEVEL).append(UNDERSCORE);
            } else {
                stringBuilder.append(indentSpaces).append(LAST_VERTEX).append(UNDERSCORE);
            }

            // now work on rendering the levels below which is the sub problem
            String subProblemSolution = renderGraph(representation, vertex, visited, indent + 3, isCycle);

            // now merge the result of the sub-problem (result of rendering) with existing solution
            mergeSolution(vertexIterator, stringBuilder, subProblemSolution, indent);


        }

        visited.remove(start);

        return stringBuilder.toString();
    }

    private void mergeSolution(Iterator<Vertex> vertexIterator, StringBuilder stringBuilder, String subProblem, int indent) {

        // If vertex still has more element to go. Break existing result and go over them one line at a time and add the nextLevel "|" symbol
        if (vertexIterator.hasNext()) {
            String[] lineArray = subProblem.split(ApplicationConstants.LINE_SEPARATOR);
            if (lineArray.length > 0) stringBuilder.append(lineArray[0]);

            for (int i = 1; i < lineArray.length; i++) {
                // replace the space at the indent with next level symbol
                StringBuilder temp = new StringBuilder(lineArray[i]);
                temp.setCharAt(indent, NEXT_GRAPH_LEVEL);
                stringBuilder.append(ApplicationConstants.LINE_SEPARATOR).append(temp);
            }
        } else {
            // if no more vertex to go for parent. Just append the sub problem solution
            stringBuilder.append(subProblem);
        }

    }

    private StringBuffer createSpaces(int indent) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < indent; i++) {
            buffer.append(BLANK_SPACE);
        }

        return buffer;
    }
}
