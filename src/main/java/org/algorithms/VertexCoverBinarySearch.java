package org.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class VertexCoverBinarySearch {

    public static final int maxn = 25;

    public static boolean[][] gr = new boolean[maxn][maxn];

    static List<Integer> vertexCoverNodes = new ArrayList<>();

    static boolean isCover(int V, int k, int E) {
        int set = (1 << k) - 1;
        int limit = (1 << V);
        boolean[][] vis = new boolean[maxn][maxn];

        while (set < limit) {
            for (int i = 0; i < maxn; i++) {
                for (int j = 0; j < maxn; j++) {
                    vis[i][j] = false;
                }
            }

            int cnt = 0;
            List<Integer> currentSet = new ArrayList<>();

            for (int j = 1, v = 0; j < limit; j = j << 1, v++) {
                if ((set & j) != 0) {
                    currentSet.add(v);
                    for (int co = 1; co <= V; co++) {
                        if (gr[v][co] && !vis[v][co]) {
                            vis[v][co] = true;
                            vis[co][v] = true;
                            cnt++;
                        }
                    }
                }
            }

            if (cnt == E) {
                vertexCoverNodes.clear();
                vertexCoverNodes.addAll(currentSet);
                return true;
            }

            int co = set & -set;
            int ro = set + co;
            set = (((ro ^ set) >> 2) / co) | ro;
        }
        return false;
    }

    public static int findMinCover(int V, int E) {
        int left = 1, right = V;
        while (right > left) {
            int mid = (left + right) >> 1;
            if (!isCover(V, mid, E)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        isCover(V, left, E);
        return left;
    }

    static void insertEdge(int u, int v) {
        gr[u][v] = true;
        gr[v][u] = true;
    }

    public static void main(String[] args) {
        for (int t = 1; t <= 25; t++) {
            try {
                String basePath = new File("").getAbsolutePath(); // Obține calea absolută a proiectului
                File file = new File(basePath + "/src/main/java/org/tests/test" + t + ".txt");

                Scanner scanner = new Scanner(file);

                int V = scanner.nextInt();
                int E = scanner.nextInt();

                for (int i = 0; i < maxn; i++) {
                    for (int j = 0; j < maxn; j++) {
                        gr[i][j] = false;
                    }
                }

                for (int i = 0; i < E; i++) {
                    int u = scanner.nextInt();
                    int v = scanner.nextInt();
                    insertEdge(u, v);
                }

                String outputFileName = basePath + "/src/main/java/org/output/output_test" + t + ".txt";
                PrintWriter writer = new PrintWriter(new File(outputFileName));

                int minCoverSize = findMinCover(V, E);
                writer.println("Dimensiunea aproximativă a vertex cover-ului: " + minCoverSize);
                writer.println("Nodurile incluse în vertex cover sunt: " + vertexCoverNodes);
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
