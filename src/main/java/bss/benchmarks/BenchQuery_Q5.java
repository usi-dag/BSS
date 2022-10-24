package bss.benchmarks;

import org.openjdk.jmh.annotations.*;
import bss.db.*;
import bss.queries.*;

import java.util.List;

@State(Scope.Thread)
public class BenchQuery_Q5 {
    DB db = DB.create();
    Query_Q5 executor = new Query_Q5();

    @Benchmark
    public List run() {
        return executor.exec(db);
    }
}
