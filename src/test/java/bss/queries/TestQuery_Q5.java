package bss.queries;

import bss.db.DB;
import bss.queries.Query_Q5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery_Q5 extends bss.queries.TestQueryBaseClass {

    @Test
    @Timeout(60) // 1 minute
    public void testQuery() throws Exception {
        // Execute the query on the given connection
		ResultSet rs = run("""
		    SELECT l_extendedprice FROM LINEITEM
			WHERE l_shipdate >= DATE '1995-12-01'
			ORDER BY l_orderkey
			LIMIT 1000
		""");
		// Execute the query using the Stream API with the generated class
		Query_Q5 executor = new Query_Q5();
		List<Query_Q5.ProjClass_1> rsStream = executor.exec(DB.create());
		
		for(Query_Q5.ProjClass_1 streamRow : rsStream) {
		    assertTrue(rs.next(), "Result sets have different size (stream one is larger)");
		    assertEquals(streamRow.l_extendedprice(), rs.getDouble("l_extendedprice"));
		}
		assertFalse(rs.next(), "Result sets have different size (stream one is smaller)");
		
    }
}
