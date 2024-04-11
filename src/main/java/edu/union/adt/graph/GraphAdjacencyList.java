package edu.union.adt.graph;
import java.util.*;
import java.lang.RuntimeException;
import java.lang.StringBuilder;

/**
 * An implementation of the Graph interface using an AdjacencyList
 *
 * @author Grant Bouvin
 * @version 4/11/2024
 */
public class GraphAdjacencyList<V> implements Graph<V> {

    private HashMap<V, Vertex<V>> vertexList;
    private int capacity;
    private int vertices;
    private int edges;

    /**
     * Create an empty graph.
     */
    public GraphAdjacencyList() {
        final int DEFAULT_CAPACITY = 10;
        vertexList = new HashMap<>(DEFAULT_CAPACITY);
        capacity = 10;
        vertices = 0;
        edges = 0;
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices() {
        return vertices;
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges() {
        return edges;
    }

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex) throws RuntimeException {
        return getVertex(vertex).getDegree();
    }

    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(V from, V to) {
        if (!this.contains(from)) {
            addVertex(from);
            vertices++;
        }
        if (!this.contains(to)) {
            addVertex(to);
            vertices++;
        }
        Vertex<V> myVertexFrom = getVertex(from);
        if (!myVertexFrom.hasEdge(to)) {
            myVertexFrom.addEdge(to);
            edges++;
        }
    }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex) {
        if (!this.contains(vertex)) {
            ensureCapacity(numVertices()+1);
            vertexList.put(vertex, new Vertex<>(vertex));
            vertices++;
        }
    }

    /**
     * @return an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices() {
        return (Iterable<V>) vertexList.keySet();
    }

    /**
     * Gets the vertices adjacent to a given vertex.  A vertex y is
     * "adjacent to" vertex x if there is an edge (x, y) in the graph.
     * Because edges are directed, if (x, y) is an edge but (y, x) is
     * not an edge, we would say that y is adjacent to x but that x is
     * NOT adjacent to y.
     *
     * @param from the source vertex
     * @return an iterable collection for the set of vertices that are
     * the destinations of edges for which 'from' is the source
     * vertex.  If 'from' is not a vertex in the graph, returns an
     * empty iterator.
     */
    public Iterable<V> adjacentTo(V from) {
        if(contains(from)) {
            return getVertex(from).getEdges();
        }
        else {
            return (Iterable<V>) Collections.emptyIterator();
        }
    }

    /**
     * Tells whether a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex) {
        if (!isEmpty()) {
            return vertexList.containsKey(vertex);
        }
        return false;
    }

    /**
     * Tells whether an edge exists in the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     *
     * @return true iff there is an edge from the source vertex to the
     * destination vertex in the graph.  If either of the given
     * vertices are not vertices in the graph, then there is no edge
     * between them.
     */
    public boolean hasEdge(V from, V to) {
        if (!isEmpty()) {
            if (this.contains(from) && this.contains(to)) {
                return getVertex(from).hasEdge(to);
            }
        }
        return false;
    }

    /**
     * Gives a string representation of the graph.  The representation
     * is a series of lines, one for each vertex in the graph.  On
     * each line, the vertex is shown followed by ": " and then
     * followed by a list of the vertices adjacent to that vertex.  In
     * this list of vertices, the vertices are separated by ", ".  For
     * example, for a graph with String vertices "A", "B", and "C", we
     * might have the following string representation:
     *
     * <PRE>
     * A: A, B
     * B:
     * C: A, B
     * </PRE>
     *
     * This representation would indicate that the following edges are
     * in the graph: (A, A), (A, B), (C, A), (C, B) and that B has no
     * adjacent vertices.
     * <p>
     * Note: there are no extraneous spaces in the output.  So, if we
     * replace each space with '*', the above representation would be:
     *
     * <PRE>
     * A:*A,*B
     * B:
     * C:*A,*B
     * </PRE>
     *
     * @return the string representation of the graph
     */
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        else {
            StringBuilder toReturn = new StringBuilder();
            for(Vertex<V> vertex : vertexList.values()) {
                toReturn.append(vertex.toString());
                toReturn.append("\n");
            }
            toReturn.deleteCharAt(toReturn.length()-1);
            return toReturn.toString();
        }

    }

    /**
     * compares this Graph with an Object
     * @return true if the graph has the same edges and vertices, false otherwise
     */
    public boolean equals(Object anotherObject){
        if (!(anotherObject instanceof Graph)) {
            return false;
        }
        else {
            Graph<?> anotherGraph = (Graph<?>) anotherObject;
            if (this.numVertices() != anotherGraph.numVertices()) {
                return false;
            }
            else if (this.numEdges() != anotherGraph.numEdges()) {
                return false;
            }
            else {
                try {
                    Graph<V> myTypeGraph = (Graph<V>) anotherGraph;
                    for(V vertex : this.getVertices()) {
                        if (!myTypeGraph.contains(vertex)) {
                            return false;
                        }
                    }
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    }

    /**
     * makes sure the graph has enough space for a newCapacity number of items
     * @param newCapacity the graph will have at least newCapacity capacity
     */
    public void ensureCapacity(int newCapacity) {
        if (newCapacity>capacity) {
            int capacityModifier = 2;
            HashMap<V, Vertex<V>> biggerMap = new HashMap<>(capacity*capacityModifier);
            biggerMap.putAll(this.vertexList);
            vertexList = biggerMap;
        }
    }

    /**
     * @return the capacity of this graph
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * gets the Vertex object that this graph contains of source vert
     *
     * @param vert the source of the Vertex object
     * @return the Vertex object, null if there is no Vertex object
     * of source vert
     */
    private Vertex<V> getVertex(V vert) {
        return vertexList.get(vert);
    }

    /**
     * @return true if this graph has no vertices, false otherwise
     */
    private boolean isEmpty() {
        return numVertices()==0;
    }
}
