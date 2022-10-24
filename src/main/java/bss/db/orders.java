package bss.db;

import java.sql.*;
import java.time.LocalDate;

public final class orders {
    // fields
public final int o_orderkey;
	public final int o_custkey;
	public final String o_orderstatus;
	public final double o_totalprice;
	public final Date o_orderdate;
	public final String o_orderpriority;
	public final String o_clerk;
	public final int o_shippriority;
	public final String o_comment;

    public orders(int o_orderkey, int o_custkey, String o_orderstatus, double o_totalprice, Date o_orderdate, String o_orderpriority, String o_clerk, int o_shippriority, String o_comment) {
    this.o_orderkey = o_orderkey;
		this.o_custkey = o_custkey;
		this.o_orderstatus = o_orderstatus;
		this.o_totalprice = o_totalprice;
		this.o_orderdate = o_orderdate;
		this.o_orderpriority = o_orderpriority;
		this.o_clerk = o_clerk;
		this.o_shippriority = o_shippriority;
		this.o_comment = o_comment;
    }

    // create from database connection
	static orders[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from orders");
	    rs.next();
	    int ctn = rs.getInt(1);
	    orders[] data = new orders[ctn];
	    rs = stm.executeQuery("select * from orders");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new orders(rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getDouble(4),
					new Date((int) LocalDate.parse(rs.getObject(5).toString()).toEpochDay()),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getString(9));
	    }
	    return data;
	}
	
}
