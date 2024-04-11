package edu.union.adt.graph.tests;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class myGraphTests {
    private Graph<String> g;
    private Graph<String> g2;
    private Graph<Object> objectGraph;

    @Before
    public void setUp() {
        g = GraphFactory.<String>createGraph();
        g2 = GraphFactory.<String>createGraph();
        objectGraph = GraphFactory.<Object>createGraph();
    }

    @After
    public void tearDown() {
        g = null;
        g2 = null;
        objectGraph = null;
    }

    @Test
    public void testNumVertices() {

    }

    @Test
    public void testNumEdges() {

    }
}
