package edu.union.adt.graph;

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
    public void ensureCapacity(int newCapacity);
}
