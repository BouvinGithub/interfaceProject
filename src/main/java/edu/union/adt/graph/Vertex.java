package edu.union.adt.graph;
import java.util.linkedlist;

/**
 * A vertex on a graph. It contains any edges that
 * have this vertex as a source, and can get the degree of this vertex.
 *
 * @author Grant Bouvin
 * @version 4/6/2024
 */

public class Vertex<V>
{
  private <V> source;
  private int degree;
  private LinkedList<V> edges;

/**
 * constructor
 */
  public Vertext(V source) {
    source = source;
    degree = 0;
    edges = new LinkedList;
  }
}
