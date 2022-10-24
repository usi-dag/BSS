package bss.queries;

import bss.db.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Query_Q5   {
	static final Date const_0 = Date.valueOf("1995-12-01");;
	static final Comparator<ProjClass_0> comp_0 = Comparator.comparing((ProjClass_0 row) -> row.l_orderkey());

	public record ProjClass_0(double l_extendedprice,int l_orderkey) {}

	public record ProjClass_1(double l_extendedprice) {}

	// methods
	public List<ProjClass_1> exec(DB db) {
		return Arrays.stream(db.lineitem)
				.filter(row -> (row.l_shipdate.compareTo(const_0) >= 0))
				.map(row -> new ProjClass_0(row.l_extendedprice, row.l_orderkey))
				.sorted(comp_0)
				.limit(1000)
				.map(row -> new ProjClass_1(row.l_extendedprice()))
				.toList();
	}

}
