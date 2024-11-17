package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * Do the following in main:
 * • Create an instance of MyGraph that matches the following graph:
 * • Call the shortestPath method on the graph above.
 *   The method call for shortestPath should produce output as detailed above in the method description.
 * • Call the minimumSpanningTree method on the above graph.
 *   The minimumSpanningTree method will return a MyGraph instance.
 *   Show the returned graph on screen (just show vertices and adjacency lists).
 *   Here is sample output for the graph from the slides (the output below is NOT for the above graph)
 */

/**
 * Main method to execute the graph algorithms.
 * It initializes the graph, computes the shortest path,
 * calculates the minimum spanning tree, and prints the results.
 */
public class Main {
    public static void main(String[] args) {
        MyGraph g = new MyGraph();
        initializeGraph(g);
        int startingVertex = 0;

        shortestPath(g,startingVertex);

        System.out.println("------------------------------");
        MyGraph MST = minimumSpanningTree(g,startingVertex);
        printMST(g,MST);
    }

    //depends on the graph image to initialize my graph g
    private static void initializeGraph(MyGraph g){
        g.addVertex(0);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(8);
        g.addVertex(9);

        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 1);
        g.addEdge(0, 3, 8);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 4, 1);
        g.addEdge(2, 5, 7);
        g.addEdge(3, 5, 8);
        g.addEdge(3, 6, 1);
        g.addEdge(4, 7, 1);
        g.addEdge(5, 7, 1);
        g.addEdge(5, 8, 9);
        g.addEdge(6, 8, 1);
        g.addEdge(6, 9, 9);
        g.addEdge(7, 8, 2);
        g.addEdge(8, 9, 1);
    }


/*
 * Write the following methods to calculate a minimum spanning tree given a graph.
 * These methods should be defined as static in the Main class.
 * Use the given method headers.
 * • public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited)
 *   Implement the version from the slides (not some other version from another source).
 *   Gets the edge with a minimum weight that is on the “frontier”
 *   between visited and unvisited.
 *   A “frontier” edge has one of its vertices as visited and the other as not visited.
 *   This method should not printShortestPath anything on screen.
 * • public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex)
 *   Should return a new MyGraph instance that is the minimum spanning tree of
 *   the graph parameter from the starting vertex parameter.
 *   Implement the simple version of Prim’s algorithm from the slides.
 *   You should use the getMinFrontierEdge method as part of your
 *   implementation of this method.
 *   This method should not printShortestPath anything on screen.
 */
    /**
     * Retrieves the minimum edge connecting a visited vertex to an unvisited vertex.
     *
     * @param g The graph from which to find the minimum frontier edge.
     * @param visited An array indicating which vertices have been visited.
     * @return The Edge object representing the minimum weight frontier edge.
     */
    public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited) {
        Map<Integer, List<Edge>> adjList = g.getAdjList();
        Edge minEdge = new Edge(0, 0, Integer.MAX_VALUE);

        for (int v1 = 0; v1 < visited.length; v1++) {
            if(visited[v1]){
                List<Edge> adjEdges = adjList.get(v1);
                if(adjEdges != null) {
                    for (Edge e : adjEdges) {
                        if (!visited[e.v2] && (e.weight < minEdge.weight)) {
                            minEdge = e;
                        }
                    }
                }
            }
        }

        return minEdge;
    }
    /**
     * Constructs a minimum spanning tree (MST) for a given graph using
     * a starting vertex. The method utilizes a deep copy of the graph
     * to ensure that the original graph remains unchanged.
     *
     * @param g The input graph from which the MST will be created.
     * @param startingVertex The vertex from which to start constructing the MST.
     * @return A new graph representing the minimum spanning tree.
     */
    public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex){
        boolean[] visited = new boolean[g.getVertices().size()];
        MyGraph MST = deepCopyMyGraph(g);
        visited[startingVertex] = true;

        while (true){
            Edge minEdge = getMinFrontierEdge(g,visited);
            if(minEdge.v1 == minEdge.v2) {
                // If it is cycle, it is no valid edges, exit the loop
                break;
            }
            visited[minEdge.v1] = true;
            visited[minEdge.v2] = true;

            MST.addEdge(minEdge.v1, minEdge.v2, minEdge.weight);

        }

        return MST;
    }

    // Creates a deep copy of the given MyGraph instance.
    private static MyGraph deepCopyMyGraph(MyGraph g){
        List<Integer> vertices = g.getVertices();
        MyGraph deepCopy = new MyGraph();

        for(Integer v: vertices){
            deepCopy.addVertex(v);
        }

        return deepCopy;
    }
    // Prints the source graph and its Minimum Spanning Tree (MST) representation.
    private static void printMST(MyGraph g,MyGraph MST){
        System.out.println("Source Graph");
        g.printVertices();
        g.printAdjList();

        System.out.println("MST Graph");
        MST.printVertices();
        MST.printAdjList();
    }


/*
 * Write the following methods to calculate the shortest paths
 * from a given starting vertex for given a graph.
 * These methods should be defined as static in the Main class.
 * Use the given method headers.
 * • public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist)
 *   Implement the version from the slides (not some other version from another source).
 *   This method should search the list of unvisited vertices
 *   for the one that has the minimum distance to the starting vertex.
 *   Hint: Use the dist array to help with this.
 * • public static void shortestPath(MyGraph g, int startingVertex)
 *   Implement the simple version of Dijkstra’s shortest path algorithm
 *   from the slides (use the above method header).
 *   This method should calculate the shortest paths to
 *   all vertices given the graph and starting vertex parameters.
 *   This should printShortestPath all distance array data and all previous array data on screen.
 *   Here is some sample output from this method (this graph is from the slides;
 *   it is NOT for the graph pictured in the Main code section below)
 */

    /**
     * This method retrieves the vertex with the minimum distance from the
     * list of unvisited vertices in the graph. It compares the distances of
     * vertices from the distance array and returns the vertex that has the
     * smallest distance.
     *
     * @param g The graph being analyzed.
     * @param unvisitedList A list of vertices that have not been visited yet.
     * @param dist An array containing the shortest distance to each vertex.
     * @return The vertex with the minimum distance from the unvisited list.
     */
    public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist){
        int minNeighborVertex = unvisitedList.get(0);
        int minNeighborDist = dist[0];

        for (int v = 1; v < dist.length; v++) {
            if(dist[v] <minNeighborDist){
                minNeighborDist = dist[v];
                minNeighborVertex = unvisitedList.get(v);
            }
        }
        return minNeighborVertex;
    }

    /**
     * Computes the shortest path in the given graph from a specified starting vertex.
     *
     * @param g The graph for which the shortest path is to be calculated.
     * @param startingVertex The vertex from which to start calculating the shortest path.
     */
    public static void shortestPath(MyGraph g, int startingVertex){
        List<Integer> unvisitedList = new ArrayList<>();
        int[] dist = new int[g.getVertices().size()];
        int[] previous = new int[g.getVertices().size()];
        boolean[] visited = new boolean[g.getVertices().size()];

        //Set all vertex distances to the max value
        //Set all previous to -1
        //Set all visited to false
        //Put all vertices in unvisitedList
        for (int i = 0; i < g.getVertices().size(); i++) {
            dist[i] = Integer.MAX_VALUE;
            previous[i] = -1;
            visited[i] = false;
            unvisitedList.add(g.getVertices().get(i));
        }
        //Pick a starting vertex and set its distance to 0
        dist[startingVertex] = 0;

        while (!unvisitedList.isEmpty()){
            int currV = getMinDistVertex(g,unvisitedList,dist);
            unvisitedList.remove((Integer) currV);
            visited[currV] = true;

            Map<Integer, List<Edge>> adjList = g.getAdjList();
            if(adjList == null) {
                System.out.println("adjList is null, no edge in graph");
                break;
            }
            List<Edge> adjEdges = adjList.get(currV);
            if(adjEdges != null) {
                // all unvisited neighbors of currV
                for (Edge edge : adjEdges) { // all edge of currV
                    int n = edge.v2; // neighbors of currV
                    if(!visited[n]) { // unvisited neighbors of currV
                        int possibleDist = dist[currV] + edge.weight;
                        if (possibleDist < dist[n]) {
                            dist[n] = possibleDist;
                            previous[n] = currV;
                        }
                    }
                }
            }

        }
        printShortestPath(g,startingVertex,dist,previous);
    }

    // Prints the graph information about the shortest path data.
    private static void printShortestPath(MyGraph g, int startingVertex, int[] dist, int[] previous){
        System.out.println("Source Graph");
        g.printVertices();
        g.printAdjList();

        System.out.println("Shortest Path Data");
        System.out.println("Starting Vertex :"+ startingVertex);

        System.out.printf("%10s%10s%n", "Vertex", "Dist");
        for (int i = 0; i < dist.length; i++) {
            System.out.printf("%10d%10d\n", i, dist[i]);
        }

        System.out.printf("%10s%10s%n", "Vertex", "Previous");
        for (int i = 0; i < previous.length; i++) {
            System.out.printf("%10d%10d\n", i, previous[i]);
        }

    }



}