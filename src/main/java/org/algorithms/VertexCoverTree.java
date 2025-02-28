package org.algorithms;

import java.io.*;
import java.util.*;

public class VertexCoverTree {
    // Utility function to add an edge in the tree
    public static void addEdge(List<List<Integer>> adj, int x, int y) {
        adj.get(x).add(y);
        adj.get(y).add(x);
    }

    static void dfs(List<List<Integer>> adj, List<List<Integer>> dp,
                    List<Set<Integer>> vertexCoverNodes, int src, int par) {
        for (Integer child : adj.get(src)) {
            if (child != par) {
                dfs(adj, dp, vertexCoverNodes, child, src);
            }
        }

        for (Integer child : adj.get(src)) {
            if (child != par) {
                // Not including source in the vertex cover
                dp.get(src).set(0, dp.get(src).get(0) + dp.get(child).get(1));

                // Including source in the vertex cover
                dp.get(src).set(1, dp.get(src).get(1)
                        + Math.min(dp.get(child).get(1), dp.get(child).get(0)));
            }
        }

        // Build the vertex cover set
        if (dp.get(src).get(1) <= dp.get(src).get(0)) {
            vertexCoverNodes.get(src).add(src);
            for (Integer child : adj.get(src)) {
                if (child != par) {
                    vertexCoverNodes.get(src).addAll(vertexCoverNodes.get(child));
                }
            }
        } else {
            for (Integer child : adj.get(src)) {
                if (child != par) {
                    vertexCoverNodes.get(src).addAll(vertexCoverNodes.get(child));
                }
            }
        }
    }
    private static void writeResultsToFile(String filePath, int minCover, Set<Integer> nodes) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("Minimum Size of Vertex Cover: " + minCover);
            writer.println("Nodes in Vertex Cover: " + nodes);
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }


    public static int minSizeVertexCover(List<List<Integer>> adj, int N, String outputFile) {
        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            dp.add(new ArrayList<>(Arrays.asList(0, 1)));
        }

        List<Set<Integer>> vertexCoverNodes = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            vertexCoverNodes.add(new HashSet<>());
        }

        dfs(adj, dp, vertexCoverNodes, 1, -1);

        int minCover = Math.min(dp.get(1).get(0), dp.get(1).get(1));
        Set<Integer> nodes = (dp.get(1).get(1) <= dp.get(1).get(0))
                ? vertexCoverNodes.get(1)
                : vertexCoverNodes.get(1);

        // Scrierea rezultatelor în fișier
        writeResultsToFile(outputFile, minCover, nodes);
        return minCover;
    }


    public static void main(String[] args) {
        // Citirea fișierelor de test
        for (int t = 22; t <= 25; t++) {
            try {
                // Deschide fișierul test t
                String basePath = new File("").getAbsolutePath(); // Obține calea absolută a proiectului
                File file = new File(basePath + "/src/main/java/org/tests/test" + t + ".txt");

                Scanner scanner = new Scanner(file);

                // Citirea numărului de vârfuri (V) și muchii (E)
                int N = scanner.nextInt(); // Numărul de noduri
                int E = scanner.nextInt(); // Numărul de muchii

                // Adjacency list representation of the tree
                List<List<Integer>> adj = new ArrayList<>();
                for (int i = 0; i <= N; i++) {
                    adj.add(new ArrayList<>());
                }

                // Citirea muchiilor (u v) și inserarea lor în graf
                for (int i = 0; i < E; i++) {
                    int u = scanner.nextInt();
                    int v = scanner.nextInt();
                    addEdge(adj, u, v);
                }

                // Generarea numelui fișierului de ieșire
                String outputFileName = basePath + "/src/main/java/org/output/output_test" + t + ".txt";

                // Găsirea și salvarea vertex cover-ului minim
                minSizeVertexCover(adj, N, outputFileName);

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Fișierul " + t + " nu a fost găsit.");
                e.printStackTrace();
            }
        }
    }

}