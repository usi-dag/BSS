package bss.benchmarks;

import org.openjdk.jmh.annotations.*;
import bss.db.*;
import bss.queries.*;

import java.util.List;

@State(Scope.Thread)
public class BenchQuery_Q1 {
    DB db = DB.create();
    Query_Q1 executor = new Query_Q1();

    @Benchmark
    public List run() {
        return executor.exec(db);
    }
}
