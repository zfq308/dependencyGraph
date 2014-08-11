/**
 * Copyright 2014 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dependencygraph.command;

import dependencygraph.model.Edge;
import dependencygraph.model.Graph;
import dependencygraph.model.Vertex;
import dependencygraph.utils.DependencyGraphRenderer;
import dependencygraph.utils.InputReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class DependencyGraphManagerTest {

    @Mock
    private DependencyGraphRenderer dependencyGraphRenderer;

    @Mock
    private InputReader inputReader;

    @Mock
    private Graph graph;

    private DependencyGraphManager testDependencyManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testDependencyManager = new DependencyGraphManager();
        testDependencyManager.setGraphRenderer(dependencyGraphRenderer);
        testDependencyManager.setInputReader(inputReader);
        testDependencyManager.setGraph(graph);
    }


    /**
     * Test that the right dependencies are called with the manager for a valid edge
     */
    @Test
    public void testDependencyMangerForValidEdge() throws IOException {
        String location = UUID.randomUUID().toString();
        String validEdge = "A->B\nB->C";

        when(inputReader.read(location)).thenReturn(validEdge);

        testDependencyManager.generateDependencyGraph(location);

        verify(inputReader).read(location);
        verify(graph, times(2)).addEdge((Edge) anyObject());
        // verify render was called with graph and the first vertex
        verify(dependencyGraphRenderer).renderGraph(graph, new Vertex("A"));

        verifyNoMoreInteractions(inputReader);
        verifyNoMoreInteractions(dependencyGraphRenderer);
        verifyNoMoreInteractions(graph);
    }


    @Test
    public void testDependencyMangerForInValidEdge() throws IOException {
        String location = UUID.randomUUID().toString();
        String invalidEdge = "A->B\nB->";

        when(inputReader.read(location)).thenReturn(invalidEdge);

        // sine exception is only caught and logged
        assertTrue(testDependencyManager.generateDependencyGraph(location).contains("InvalidDependencyGraphRepresentationException"));

    }
}
