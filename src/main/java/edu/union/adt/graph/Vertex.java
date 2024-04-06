package edu.union.adt.graph;
import java.util.LinkedList;

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
  * constructor
  */
  public Vertex(V source) {
    source = source;
    degree = 0;
    edges = new LinkedList();
  }

  /**
   * addes an edge to this vertex from itself to endVertex
   * does nothing if there is already an edge to endVertex
   * @param endVertex the end vertex of the edge to be added
   */
   public void addEdge(V endVertex) {
     if (!this.hasEdge(endVertex)) {
       edges.add(endVertex);
       degree++;
     }
   }

   /**
    * @return true if there is an edge starting at the source and ending
    * at end, false otherwise.
    */
    public boolean hasEdge(V endVertex) {
      return edges.contains(endVertex);
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
