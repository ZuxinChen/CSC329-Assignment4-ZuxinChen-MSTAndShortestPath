package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create the MyGraph class.
 * It should use an adjacency list implementation (same class as done in the lab).
 * MyGraph Specifications
 * 1. Create private members as necessary.
 *      a. Member variable for vertices. Should be a list of Integer.
 *          Use a Java List for the type and use an ArrayList for the instance.
 *      b. Member variable for the adjacency lists.
 *          Should be a Java Map of vertices to lists of edges.
 *          It should have Integer as the key and a List of edges as the value.
 *          Use the Java HashMap class for the map instance.
 * 2. Your class must also implement the following methods (use the given method headers):
 *      a. Default constructor.
 *         Should initialize the member variables
 *         (create an instance of the vertex list and an instance of the adjacency list map).
*       b. void addVertex(int v). Add a vertex to the vertex collection.
 *         Add a key/value pair for the adjacency list for this new vertex.
 *         Hint: Need to add an empty adjacency list.
 *      c. void addEdge(int v1, int v2, int weight).
 *         Add a new edge to the adjacency lists for both v1 and v2.
 */

public class MyGraph {
    private final List<Integer> vertices;
    private final Map<Integer, List<Edge>> adjList;

    /**
     * Default constructor.
     */
    public MyGraph() {
        vertices = new ArrayList<>();
        adjList = new HashMap<>();
    }

    /**
     * Add a vertex to the graph.
     * @param v The vertex need to add.
     */
    public void addVertex(int v) {
        vertices.add(v);
        adjList.put(v, new ArrayList<>());
    }

    /**
     * Add a new edge to the graph.
     * @param v1 The first vertex of the edge.
     * @param v2 The second vertex of the edge.
     * @param weight The weight of the edge.
     */
    void addEdge(int v1, int v2, int weight){
        List<Edge> edge1 = adjList.get(v1);
        List<Edge> edge2 = adjList.get(v2);
        edge1.add(new Edge(v1,v2, weight));
        edge2.add(new Edge(v2,v1, weight));
    }

    /**
     * Get the list of vertices in the graph.
     * @return The list of vertices.
     */
    public List<Integer> getVertices() {
        return vertices;
    }
    /**
     * Get the adjacency list of the graph.
     * @return The adjacency list.
     */
    public Map<Integer, List<Edge>> getAdjList() {
        return adjList;
    }

    public void printVertices(){

        System.out.print("V = { ");
        vertices.forEach(v->System.out.print(v+" "));
        System.out.print("}");
        System.out.println();
    }

    public void printAdjList(){
        System.out.println("Adj List");
        for(Map.Entry<Integer, List<Edge>> AdjList : adjList.entrySet()){
            System.out.print(AdjList.getKey()+": ");
            for (Edge edge: AdjList.getValue()){
                System.out.printf("(%d, %d, %d)",edge.v1,edge.v2,edge.weight);
            }
            System.out.println();
        }

    }

}
