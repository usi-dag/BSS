package bss.queries;

import bss.db.*;
import bss.queries.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

;

public class Query_Q1   {
	static final Date const_0 = Date.valueOf("1995-12-01");;

	public static final class GroupByCollector_0 {
	    long EXPR$0;
	
	    void accumulate(lineitem row) {
	        EXPR$0++;
	    }
	
	    GroupByCollector_0 combine(GroupByCollector_0 other) {
	        EXPR$0 += other.EXPR$0;
	        return this;
	    }
	
	    
	
	    static Collector<lineitem, GroupByCollector_0, GroupByCollector_0> collector(){
		    return Collector.of(
		        GroupByCollector_0::new,
		        GroupByCollector_0::accumulate,
		        GroupByCollector_0::combine);
		}
	}
	

	public List<GroupByCollector_0> exec(DB db) {
		return Stream.of(
				Arrays.stream(db.lineitem)
						.filter(row -> (row.l_shipdate.compareTo(const_0) >= 0))
						.collect(GroupByCollector_0.collector()))
				.toList();
		}
		
}
