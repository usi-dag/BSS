package bss.queries;

import bss.db.DB;
import bss.queries.Query_Q7;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery_Q7 extends bss.queries.TestQueryBaseClass {

    @Test
    @Timeout(60) // 1 minute
    public void testQuery() throws Exception {
        // Execute the query on the given connection
		ResultSet rs = run("""
		    SELECT SUM(o_totalprice)
			FROM LINEITEM, ORDERS
			WHERE o_orderdate >= DATE '1998-11-01'
			AND l_shipdate >= DATE '1998-11-01'
			AND o_orderkey = l_orderkey
		""");
		// Execute the query using the Stream API with the generated class
		Query_Q7 executor = new Query_Q7();
		List<Query_Q7.GroupByCollector_0> rsStream = executor.exec(DB.create());
		
		for(Query_Q7.GroupByCollector_0 streamRow : rsStream) {
		    assertTrue(rs.next(), "Result sets have different size (stream one is larger)");
		    assertEquals(streamRow.EXPR$0, rs.getDouble("EXPR$0"));
		}
		assertFalse(rs.next(), "Result sets have different size (stream one is smaller)");
		
    }
}
