package bss.queries;

import bss.db.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;


public class Query_Q4   {
	static final Date const_0 = Date.valueOf("1995-12-01");;

	public record ProjClass_0(double EXPR$0) {}

	public List<ProjClass_0> exec(DB db) {
		return Arrays.stream(db.lineitem)
				.filter(row -> (row.l_shipdate.compareTo(const_0) >= 0))
				.map(row -> new ProjClass_0((row.l_discount * row.l_extendedprice)))
				.toList();
	}
}
