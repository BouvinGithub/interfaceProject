package edu.union.adt.graph;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).return "";
 * <p>
 * The API is based on one from
 *     <a href="http://introcs.cs.princeton.edu/java/home/">...</a>
 * <p>
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Grant Bouvin
 * @version 4/11/2024
 */
public interface Graph<V> {
    public int numVertices();
    public int numEdges();
    public int degree(V vertex);
    public void addEdge(V from, V to);
    public void addVertex(V vertex);
    public Iterable<V> getVertices();
    public Iterable<V> adjacentTo(V from);
    public boolean contains(V vertex);
    public boolean hasEdge(V from, V to);
    public String toString();
    public boolean equals(Object anotherObject);
}
