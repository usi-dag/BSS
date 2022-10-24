package bss.queries;

import bss.db.DB;
import bss.queries.Query_Q8;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery_Q8 extends bss.queries.TestQueryBaseClass {

    @Test
    @Timeout(60) // 1 minute
    public void testQuery() throws Exception {
        // Execute the query on the given connection
		ResultSet rs = run("""
		    WITH TOP_ORDERS AS (
			  SELECT * FROM ORDERS
			  WHERE o_orderdate >= DATE '1995-12-01'
			  ORDER BY o_totalprice DESC
			  LIMIT 1000
			)
			
			SELECT SUM(o_totalprice) as sum_total
			FROM LINEITEM, TOP_ORDERS
			WHERE l_shipdate >= DATE '1995-12-01'
			AND o_orderkey = l_orderkey
		""");
		// Execute the query using the Stream API with the generated class
		Query_Q8 executor = new Query_Q8();
		List<Query_Q8.GroupByCollector_0> rsStream = executor.exec(DB.create());
		
		for(Query_Q8.GroupByCollector_0 streamRow : rsStream) {
		    assertTrue(rs.next(), "Result sets have different size (stream one is larger)");
		    assertEquals(streamRow.sum_total, rs.getDouble("sum_total"));
		}
		assertFalse(rs.next(), "Result sets have different size (stream one is smaller)");
		
    }
}
