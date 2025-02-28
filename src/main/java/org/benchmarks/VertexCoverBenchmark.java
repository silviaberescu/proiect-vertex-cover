package org.benchmarks;
import org.algorithms.Graph;

import org.algorithms.HeuristicApproxVertexCover;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.algorithms.VertexCoverBinarySearch.*;
import static org.algorithms.VertexCoverBrute.vertexCoverBruteForce;
import static org.algorithms.VertexCoverTree.addEdge;
import static org.algorithms.VertexCoverTree.minSizeVertexCover;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5)
public class VertexCoverBenchmark {

    private List<int[]> edges;
    private int V;
    private int E;

    @Param({"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"})
    private int testNumber;

    @Setup(Level.Trial)
    public void setUpTrial() throws IOException {
        String basePath = new File("").getAbsolutePath();
    }

    @Setup(Level.Iteration)
    public void setUp() throws FileNotFoundException {
        String basePath = new File("").getAbsolutePath();
        File file = new File(basePath + "/src/main/java/org/tests/test" + testNumber + ".txt");

        if (!file.exists()) {
            throw new FileNotFoundException("Fișierul test" + testNumber + ".txt nu a fost găsit.");
        }

        Scanner scanner = new Scanner(file);
        V = scanner.nextInt();
        E = scanner.nextInt();
        edges = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edges.add(new int[]{u, v});
        }
        scanner.close();
    }

    @TearDown(Level.Trial) // Se execută la sfârșitul fiecărui trial
    public void tearDownTrial() throws IOException {

    }


    @Benchmark
    public void benchmarkVertexCoverBinarySearch() throws IOException {
        for (int i = 0; i < maxn; i++) {
            for (int j = 0; j < maxn; j++) {
                gr[i][j] = false;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            gr[u][v] = true;
            gr[v][u] = true;
        }

        int coverSize = findMinCover(V, E);
    }

    @Benchmark
    public void benchmarkVertexCoverBrute() throws IOException {
        Graph graph = new Graph(V);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        boolean[] minSol = new boolean[V];
        int minCoverSize = vertexCoverBruteForce(V, graph, minSol);
    }

    @Benchmark
    public void benchmarkVertexCoverTree() throws IOException {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            addEdge(adj, edge[0], edge[1]);
        }
        String basePath = new File("").getAbsolutePath();
        String outputFileName = basePath + "/src/main/java/org/output/output_test_tree.txt";
        int minCoverSize = minSizeVertexCover(adj, V, outputFileName);
    }

    @Benchmark
    public void benchmarkHeuristicApproxVertexCover() throws IOException {
        boolean[] sol = new boolean[V + 1];
        int coverSize = HeuristicApproxVertexCover.heuristicApproxVertexCover(V, edges, sol);
    }
}