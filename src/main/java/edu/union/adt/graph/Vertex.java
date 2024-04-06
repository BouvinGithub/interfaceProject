package edu.union.adt.graph;
import java.util.LinkedList;
import java.lang.StringBuilder;

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
  * @param source the origin of the vertex
  */
  public Vertex(V source) {
    source = source;
    degree = 0;
    edges = new LinkedList();
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
   * adds an edge to this vertex from itself to vertex from,
   * does nothing if there is already an edge there.
   * @param from the end vertex of the edge to be added
   */
   public void addEdge(V from) {
     if (!this.hasEdge(from)) {
       edges.add(from);
       degree++;
     }
   }

   /**
    * @return true if there is an edge starting at the source and ending
    * at vertex from, false otherwise.
    */
    public boolean hasEdge(V from) {
      return edges.contains(from);
    }

    /**
     * @return true if this vertex is adjacent to the given vertex from,
     * false otherwise.
     */
    public boolean isAdjacent(V from) {
      return edges.contains(from);
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



}
