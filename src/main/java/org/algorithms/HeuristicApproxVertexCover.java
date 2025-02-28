package org.algorithms;
import java.io.*;
import java.util.*;

public class HeuristicApproxVertexCover {

    public static int heuristicApproxVertexCover(int N, List<int[]> edges, boolean[] sol) {
        int nrEdges = edges.size();
        int[] deg = new int[N + 1];
        for (int[] edge : edges) {
            deg[edge[0]]++;
            deg[edge[1]]++;
        }

        // Reprezentare a listei de adiacență a grafului
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        while (nrEdges > 0) {
            int max = 1;
            for (int i = 2; i <= N; i++) {
                if (deg[i] > deg[max]) {
                    max = i;
                }
            }

            sol[max] = true;
            deg[max] = -1;

            for (int a : adj.get(max)) {
                if (!sol[a]) {
                    deg[a]--;
                    nrEdges--;
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (sol[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        for (int t = 1; t <= 25; t++) {
            try {
                // Deschide fișierul de test t
                String basePath = new File("").getAbsolutePath(); // Obține calea absolută a proiectului
                File file = new File(basePath + "/src/main/java/org/tests/test" + t + ".txt");

                if (!file.exists()) {
                    System.out.println("Fișierul test" + t + ".txt nu a fost găsit.");
                    continue;
                }

                Scanner scanner = new Scanner(file);

                int V = scanner.nextInt();
                int E = scanner.nextInt();

                List<int[]> edges = new ArrayList<>();
                for (int i = 0; i < E; i++) {
                    int u = scanner.nextInt();
                    int v = scanner.nextInt();
                    edges.add(new int[] { u, v });
                }

                boolean[] sol = new boolean[V + 1];

                String outputFileName = basePath + "/src/main/java/org/output/output_test" + t + ".txt";
                PrintWriter writer = new PrintWriter(new File(outputFileName));

                int vertexCoverSize = heuristicApproxVertexCover(V, edges, sol);
                writer.println("Dimensiunea aproximativă a vertex cover-ului: " + vertexCoverSize);

                writer.print("Nodurile incluse în vertex cover sunt: ");
                for (int i = 1; i <= V; i++) {
                    if (sol[i]) {
                        writer.print(i + " ");
                    }
                }
                writer.println();
                writer.println();

                writer.close();
                scanner.close();

            } catch (FileNotFoundException e) {
                System.out.println("Fișierul test" + t + ".txt nu a fost găsit.");
                e.printStackTrace();
            }
        }
    }
}

