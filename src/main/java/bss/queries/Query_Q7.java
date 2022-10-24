package bss.queries;

import bss.db.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Query_Q7   {
	static final Date const_0 = Date.valueOf("1998-11-01");;

	public record ProjClass_0(int l_orderkey) {}

	public record JoinedRecord_0(int l_orderkey,int o_orderkey,double o_totalprice) {}

	public record ProjClass_1(int o_orderkey,double o_totalprice) {}

	public static final class GroupByCollector_0 {
		double EXPR$0;

		void accumulate(JoinedRecord_0 row) {
			EXPR$0 += row.o_totalprice;
		}

		GroupByCollector_0 combine(GroupByCollector_0 other) {
			EXPR$0 += other.EXPR$0;
			return this;
		}

		static Collector<JoinedRecord_0, GroupByCollector_0, GroupByCollector_0> collector(){
			return Collector.of(
					GroupByCollector_0::new,
					GroupByCollector_0::accumulate,
					GroupByCollector_0::combine);
		}

	}

	public List<GroupByCollector_0> exec(DB db) {
		List<ProjClass_0> empty_0 = Collections.emptyList();
		var hashmap_0 = Arrays.stream(db.lineitem)
				.filter(row -> (row.l_shipdate.compareTo(const_0) >= 0))
				.map(row -> new ProjClass_0(row.l_orderkey))
				.collect(Collectors.groupingBy(l_0 -> l_0.l_orderkey()));

		return Stream.of(
				Arrays.stream(db.orders)
						.filter(row -> (row.o_orderdate.compareTo(const_0) >= 0))
						.map(row -> new ProjClass_1(row.o_orderkey, row.o_totalprice))
						.flatMap(r_0 -> hashmap_0.getOrDefault(r_0.o_orderkey(), empty_0)
								.stream()
								.map(l_0 -> new JoinedRecord_0(l_0.l_orderkey, r_0.o_orderkey, r_0.o_totalprice))
						)
						.collect(GroupByCollector_0.collector()))
				.toList();

	}

}
