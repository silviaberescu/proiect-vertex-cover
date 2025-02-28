package org.algorithms;

import java.util.ArrayList;

public class Graph {
    public int V; // Number of vertices
    public ArrayList<int[]> edges;

    public Graph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }

    // Add an edge to the graph
    public void addEdge(int u, int v) {
        edges.add(new int[]{u, v});
    }
}

