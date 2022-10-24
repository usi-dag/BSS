package bss.queries;

import bss.db.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Query_Q3   {
	static final Date const_0 = Date.valueOf("1995-12-01");;
	static final Date const_1 = Date.valueOf("1997-01-01");;

	public record ProjClass_0(double $f0) {}

	public static final class GroupByCollector_0 {
		double EXPR$0;

		void accumulate(ProjClass_0 row) {
			EXPR$0 += row.$f0();
		}

		GroupByCollector_0 combine(GroupByCollector_0 other) {
			EXPR$0 += other.EXPR$0;
			return this;
		}



		static Collector<ProjClass_0, GroupByCollector_0, GroupByCollector_0> collector(){
			return Collector.of(
					GroupByCollector_0::new,
					GroupByCollector_0::accumulate,
					GroupByCollector_0::combine);
		}

	}


	// methods
	public List<GroupByCollector_0> exec(DB db) {
		return Stream.of(
						Arrays.stream(db.lineitem)
								.filter(row -> (row.l_shipdate.compareTo(const_0) >= 0) && (row.l_shipdate.compareTo(const_1) < 0))
								.map(row -> new ProjClass_0((row.l_discount * row.l_extendedprice)))
								.collect(GroupByCollector_0.collector()))
				.toList();

	}

}
