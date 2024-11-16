package org.example;

import java.util.List;
import java.util.Map;

/**
 * Write the following methods to calculate a minimum spanning tree given a graph.
 * These methods should be defined as static in the Main class.
 * Use the given method headers.
 * • public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited)
 *   Implement the version from the slides (not some other version from another source).
 *   Gets the edge with a minimum weight that is on the “frontier” between visited and unvisited.
 *   A “frontier” edge has one of its vertices as visited and the other as not visited.
 *   This method should not print anything on screen.
 * • public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex)
 *   Should return a new MyGraph instance that is the minimum spanning tree of
 *   the graph parameter from the starting vertex parameter.
 *   Implement the simple version of Prim’s algorithm from the slides.
 *   You should use the getMinFrontierEdge method as part of your implementation of this method.
 *   This method should not print anything on screen.
 */
public class Main {
    public static void main(String[] args) {
    }

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
            if(minEdge.weight == Integer.MAX_VALUE) {
                break;// If there are no valid edges, exit the loop
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


}