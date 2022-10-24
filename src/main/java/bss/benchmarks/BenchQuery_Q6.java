package bss.benchmarks;

import org.openjdk.jmh.annotations.*;
import bss.db.*;
import bss.queries.*;

import java.util.List;

@State(Scope.Thread)
public class BenchQuery_Q6 {
    DB db = DB.create();
    Query_Q6 executor = new Query_Q6();

    @Benchmark
    public List run() {
        return executor.exec(db);
    }
}
