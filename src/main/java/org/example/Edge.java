package org.example;

/*
 * Create the Edge class with the following members:
 * • Member variables for v1, v2, and weight.
 *  They should all have the data type int.
 *  These variables should have package scope
 *  (leave out the access modifier for package scope).
 * • Constructor that sets all three member variables.
 */
public class Edge {
    int v1, v2, weight;

    // Constructor
    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}
