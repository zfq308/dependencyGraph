package dependencygraph.utils;

import dependencygraph.ApplicationConstants;
import dependencygraph.model.AdjacencyList;
import dependencygraph.model.Edge;
import dependencygraph.model.Graph;
import dependencygraph.model.Vertex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test verifying dependency rendering
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class DependencyGraphRendererTest {

    @Test
    public void simpleRenderingTest() {
        String multiLevel = "A->B\n" +
                "A->J\n" +
                "B->C\n" +
                "C->E\n" +
                "B->H\n" +
                "B->N\n" +
                "A->C";


        String multiLevelExpected = "A\n" +
                "|_ B\n" +
                "|  |_ C\n" +
                "|  |  \\_ E\n" +
                "|  |_ H\n" +
                "|  \\_ N\n" +
                "|_ J\n" +
                "\\_ C\n" +
                "   \\_ E";

        String simpleDependency = "A->B\n" +
                "A->J\n" +
                "B->C\n" +
                "C->E\n" +
                "E->F\n";

        String simpleDependencyExpected = "A\n" +
                "|_ B\n" +
                "|  \\_ C\n" +
                "|     \\_ E\n" +
                "|        \\_ F\n" +
                "\\_ J";

        String simpleDependency2 = "A->B\n" +
                "A->J\n" +
                "B->C\n" +
                "C->E\n";

        String simpleDependency2Expected = "A\n" +
                "|_ B\n" +
                "|  \\_ C\n" +
                "|     \\_ E\n" +
                "\\_ J";

        String basicTwoLevel = "A->B\n" +
                "B->C\n";

        String basicTwoLevelExpected = "A\n" +
                "\\_ B\n" +
                "   \\_ C";

        String basicThreeLevel = "A->B\n" +
                "A->C\n" +
                "B->C\n" +
                "B->D";

        String basicThreeLevelExpected = "A\n" +
                "|_ B\n" +
                "|  |_ C\n" +
                "|  \\_ D\n" +
                "\\_ C";

        String cycle = "A->B\n" +
                "B->C\n" +
                "C->A";

        String cycleExpected = "A\n" +
                "\\_ B\n" +
                "   \\_ C\n" +
                "      \\_ A*";

        String multiLevelWithDependency = "A->B\n" +
                "A->J\n" +
                "B->C\n" +
                "C->E\n" +
                "B->H\n" +
                "B->N\n" +
                "A->C\n" +
                "H->A";

        String multiLevelWithDependencyExpected = "A\n" +
                "|_ B\n" +
                "|  |_ C\n" +
                "|  |  \\_ E\n" +
                "|  |_ H\n" +
                "|  |  \\_ A*\n" +
                "|  \\_ N\n" +
                "|_ J\n" +
                "\\_ C\n" +
                "   \\_ E";

        DefaultDependencyGraphRenderer defaultDependencyGraphRenderer = new DefaultDependencyGraphRenderer();

        String[] strings = new String[]{multiLevel, simpleDependency, simpleDependency2, basicTwoLevel, basicThreeLevel, cycle, multiLevelWithDependency};
        String[] result = new String[]{multiLevelExpected, simpleDependencyExpected, simpleDependency2Expected, basicTwoLevelExpected, basicThreeLevelExpected,
                cycleExpected, multiLevelWithDependencyExpected};

        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            System.out.println("#########################################################");
            System.out.println(string);
            System.out.println("\n");
            System.out.println("Rendered graph: \n");
            Graph graph = new AdjacencyList();
            for (String path : string.split("\n")) {
                graph.addEdge(getEdge(path));
            }

            String renderGraph = defaultDependencyGraphRenderer.renderGraph(graph, new Vertex("A"));

            System.out.print(renderGraph + "\n");
            System.out.println("#########################################################");

            assertEquals(result[i], renderGraph);

        }

    }

    private Edge getEdge(String edgeRepresentation) {
        String[] array = edgeRepresentation.split(ApplicationConstants.VERTEX_SEPARATOR_INDICATOR);
        return new Edge(new Vertex(array[0]), new Vertex(array[1]));
    }

}
