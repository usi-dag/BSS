package bss.queries;

import bss.db.DB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery_Q1 extends bss.queries.TestQueryBaseClass {

    @Test
    @Timeout(60) // 1 minute
    public void testQuery() throws Exception {
        // Execute the query on the given connection
		ResultSet rs = run("""
		    SELECT COUNT(*) FROM lineitem
			WHERE l_shipdate >= DATE '1995-12-01'
		""");
		// Execute the query using the Stream API with the generated class
		Query_Q1 executor = new Query_Q1();
		List<Query_Q1.GroupByCollector_0> rsStream = executor.exec(DB.create());
		
		for(Query_Q1.GroupByCollector_0 streamRow : rsStream) {
		    assertTrue(rs.next(), "Result sets have different size (stream one is larger)");
		    assertEquals(streamRow.EXPR$0, rs.getLong("EXPR$0"));
		}
		assertFalse(rs.next(), "Result sets have different size (stream one is smaller)");
		
    }
}
