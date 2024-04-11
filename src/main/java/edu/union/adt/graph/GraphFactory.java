package edu.union.adt.graph;

/**
 * A class to create
 *
 * @author Grant Bouvin
 * @version 4/11/2024
 */
public class GraphFactory {
    public static <V> Graph<V> createGraph() {
        return new GraphAdjacencyList<>();
    }
}
