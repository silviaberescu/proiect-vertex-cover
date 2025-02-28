package org.algorithms;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Main {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(org.benchmarks.VertexCoverBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }
}