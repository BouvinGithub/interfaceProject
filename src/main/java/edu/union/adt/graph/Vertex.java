package edu.union.adt.graph;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.StringBuilder;
import java.util.Iterator;

/**
 * A vertex on a graph. It contains any edges that
 * have this vertex as a source, and can get the degree of this vertex.
 *
 * @author Grant Bouvin
 * @version 4/6/2024
 */
public class Vertex<V> {
    private V source;
    private int degree;
    private LinkedList<V> edges;

    /**
     * Create a vertex
     * @param from the origin of the vertex
     */
    public Vertex(V from) {
        source = from;
        degree = 0;
        edges = new LinkedList<>();
    }

    /**
     * @return a string representation of this object
     */
    public String toString() {
        StringBuilder toReturn = new StringBuilder(source+":");
        if (degree>0) {
            toReturn.append(" ");
            for (V vertex : edges) {
                toReturn.append(vertex);
                toReturn.append(", ");
            }
            toReturn.delete(toReturn.length()-2, toReturn.length());
        }
        return toReturn.toString();
    }

    /**
     * @return true if this vertex is equal to anotherObject, false otherwise
     * (they are equal if they are both instances of the Vertex class and have the same source)
     */
    public boolean equals(Object anotherObject) {
        if (!(anotherObject instanceof Vertex)) {
            return false;
        }
        else {
            Vertex<?> anotherVertex = (Vertex<?>) anotherObject;
            if (this.getSource() != anotherVertex.getSource()) {
                return false;
            } else if (this.getDegree() != anotherVertex.getDegree()) {
                return false;
            } else {
                try {
                    Vertex<V> myTypeVertex = (Vertex<V>) anotherVertex;
                    for(V edge : this.getEdges()) {
                        if (!myTypeVertex.hasEdge(edge)) {
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
     * adds an edge to this vertex from itself to vertex to,
     * does nothing if there is already an edge there.
     * @param to the end vertex of the edge to be added
     */
    public void addEdge(V to) {
        if (!this.hasEdge(to)) {
            edges.add(to);
            degree++;
        }
    }

    /**
     * @return true if given source is the same as my source, false otherwise
     */
    public boolean fromSource(V source) {
        return source == getSource();
    }


    /**
     * @return true if there is an edge starting at the source and ending
     * at vertex to, false otherwise.
     */
    public boolean hasEdge(V to) {
        return edges.contains(to);
    }

    /**
     * @return true if this vertex is adjacent to the given source vert,
     * false otherwise.
     */
    public boolean isAdjacent(V to) {
        return edges.contains(to);
    }

    /**
     * @return the source of the vertex
     */
    public V getSource() {
        return source;
    }

    /**
     * @return the degree of the vertex
     */
    public int getDegree() {
        return degree;
    }

    /**
     * @return
     */
    public Iterable<V> getEdges() {
        Stack myStack = new Stack<V>();
        myStack.addAll(edges);
        return myStack;

    }
}