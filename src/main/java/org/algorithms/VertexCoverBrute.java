
package org.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class VertexCoverBrute {

    static boolean allEdgesCovered(Graph graph, boolean[] included) {
        for (int[] edge : graph.edges) {
            int u = edge[0];
            int v = edge[1];
            if (!included[u] && !included[v]) {
                return false;
            }
        }
        return true;
    }

    public static int vertexCoverBruteForce(int N, Graph graph, boolean[] minSol) {
        int minSize = N + 1;

        for (int i = 0; i < (1 << N); i++) {
            boolean[] sol = new boolean[N];
            int size = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) { // If the j-th vertex is in the current subset
                    sol[j] = true;
                    size++;
                }
            }

            if (size < minSize && allEdgesCovered(graph, sol)) {
                minSize = size;
                System.arraycopy(sol, 0, minSol, 0, N);
            }
        }

        return minSize;
    }

    // Method to write the results to a file
    private static void writeResultsToFile(String filePath, int minCoverSize, boolean[] minSol) {
        try {
            PrintWriter writer = new PrintWriter(new File(filePath));

            writer.print("Nodurile incluse în vertex cover sunt: ");
            for (int i = 0; i < minSol.length; i++) {
                if (minSol[i]) {
                    writer.print(i + " ");
                }
            }
            writer.println();

            writer.println("Dimensiunea minima a vertex cover-ului: " + minCoverSize);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int t = 1; t <= 25; t++) {
            try {
                String basePath = new File("").getAbsolutePath(); // Get absolute path
                File file = new File(basePath + "/src/main/java/org/tests/test" + t + ".txt");

                Scanner scanner = new Scanner(file);

                int V = scanner.nextInt();
                int E = scanner.nextInt();

                Graph graph = new Graph(V);

                for (int i = 0; i < E; i++) {
                    int u = scanner.nextInt();
                    int v = scanner.nextInt();
                    graph.addEdge(u, v);
                }

                boolean[] minSol = new boolean[V];

                int minCoverSize = vertexCoverBruteForce(V, graph, minSol);

                String outputFileName = basePath + "/src/main/java/org/output/output_test" + t + ".txt";
                writeResultsToFile(outputFileName, minCoverSize, minSol);

                scanner.close();

            } catch (FileNotFoundException e) {
                System.out.println("File " + t + " not found.");
                e.printStackTrace();
            }
        }
    }
}
