package bss.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class partsupp {
    // fields
public final int ps_partkey;
	public final int ps_suppkey;
	public final int ps_availqty;
	public final double ps_supplycost;
	public final String ps_comment;

    public partsupp(int ps_partkey, int ps_suppkey, int ps_availqty, double ps_supplycost, String ps_comment) {
    this.ps_partkey = ps_partkey;
		this.ps_suppkey = ps_suppkey;
		this.ps_availqty = ps_availqty;
		this.ps_supplycost = ps_supplycost;
		this.ps_comment = ps_comment;
    }

    // create from database connection
	static partsupp[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from partsupp");
	    rs.next();
	    int ctn = rs.getInt(1);
	    partsupp[] data = new partsupp[ctn];
	    rs = stm.executeQuery("select * from partsupp");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new partsupp(rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getDouble(4),
					rs.getString(5));
	    }
	    return data;
	}
	
}
