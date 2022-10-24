# BSS - Benchmark Suite for the Stream API

BSS is a simple benchmark suite for the Stream API automatically generated using S2S by translating SQL queries for the TPC-H dataset.

More info on the paper:

SQL to Stream with S2S - An Automatic Benchmark Generator for the Java Stream API

https://doi.org/10.1145/3564719.3568699


## Build

BSS is a Maven project, just run `mvn clean package` to build.

## Run

BSS is composed of JMH benchmarks.

The shaded jar target/BSS.jar is a standalone jar with all dependencies.

To execute the benchmarks run the following:

`java -jar target/BSS.jar ARGS`

Note: `ARGS` refers to JMH arguments.

## Input Data

BSS comes with a TPC-H DB of scale factor (SF) 0.01.

To generate a dataset with different scale factor, e.g., SF 1:

`SCALE_FACTOR=1 ./get-tpch-db.sh`
