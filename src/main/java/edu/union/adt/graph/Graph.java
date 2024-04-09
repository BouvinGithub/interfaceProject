package edu.union.adt.graph;
import java.util.ArrayList;
import java.lang.RuntimeException;
import java.util.Stack;
import java.lang.StringBuilder;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).return "";
 *
 * The API is based on one from
 *     <a href="http://introcs.cs.princeton.edu/java/home/">...</a>
 *
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Grant Bouvin
 * @version 4/6/2024
 */
public class Graph<V> {

    private ArrayList<Vertex<V>> vertexList;
    private int vertices;
    private int edges;

   /**
    * Create an empty graph.
    */
    public Graph() {
      final int DEFAULT_CAPACITY = 10;
      vertexList = new ArrayList<>(DEFAULT_CAPACITY);
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
        getVertex(from).addEdge(to);
        edges++;
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
            vertexList.add(new Vertex<V>(vertex));
            vertices++;
        }
    }

   /**
    * @return an iterable collection for the set of vertices of
    * the graph.
    */
    public Iterable<V> getVertices() {
        Stack<V> toReturn = new Stack<>();
        if (!isEmpty()) {
          for (Vertex<V> vertex : vertexList) {
            toReturn.push(vertex.getSource());
          }
        }
        return toReturn;
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
      Stack<V> toReturn = new Stack<>();
      if (!isEmpty()) {
        for (Vertex<V> vertex : vertexList) {
          if (vertex.isAdjacent(from)) {
            toReturn.push(vertex.getSource());
          }
        }
      }
      return toReturn;
    }

   /**
    * Tells whether a vertex is in the graph.
    *
    * @param vertex a vertex
    * @return true iff 'vertex' is a vertex in the graph.
    */
    public boolean contains(V vertex) {
      if (!isEmpty()) {
        for(Vertex<V> vert : vertexList) {
            if (vert.fromSource(vertex)) {
                return true;
            }
        }
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
    *
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
        for(Vertex<V> vertex : vertexList) {
          toReturn.append(vertex.toString());
          toReturn.append("\n");
        }
        toReturn.deleteCharAt(toReturn.length()-1);
        return toReturn.toString();
      }

    }

    /**
     *
     *
     */
    // public boolean equals(Object anotherObject){
    //   if (!anotherObject.isInstance(Graph)) {
    //     return false;
    //   }
    //   else {
    //     Graph<?> otherGraph = (Graph<?>) anotherObject;
    //
    //   }
    // }

   /**
    * gets the Vertex object that this graph contains of source vert
    *
    * @param vert the source of the Vertex object
    * @return the Vertex object, null if there is no Vertex object
    * of source vert
    */
    private Vertex<V> getVertex(V vert) {
      if (!isEmpty()) {
          for(Vertex<V> vertex : vertexList) {
              if (vertex.fromSource(vert)) {
                  return vertex;
              }
          }
      }
      return null;
    }

    /**
     * @return true if this graph has no verticies, false otherwise
     */
     private boolean isEmpty() {
       return numVertices()==0;
     }


}
