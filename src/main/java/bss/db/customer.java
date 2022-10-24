package bss.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class customer {
    // fields
public final int c_custkey;
	public final String c_name;
	public final String c_address;
	public final int c_nationkey;
	public final String c_phone;
	public final double c_acctbal;
	public final String c_mktsegment;
	public final String c_comment;

    public customer(int c_custkey, String c_name, String c_address, int c_nationkey, String c_phone, double c_acctbal, String c_mktsegment, String c_comment) {
    this.c_custkey = c_custkey;
		this.c_name = c_name;
		this.c_address = c_address;
		this.c_nationkey = c_nationkey;
		this.c_phone = c_phone;
		this.c_acctbal = c_acctbal;
		this.c_mktsegment = c_mktsegment;
		this.c_comment = c_comment;
    }

    // create from database connection
	static customer[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from customer");
	    rs.next();
	    int ctn = rs.getInt(1);
	    customer[] data = new customer[ctn];
	    rs = stm.executeQuery("select * from customer");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new customer(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getDouble(6),
					rs.getString(7),
					rs.getString(8));
	    }
	    return data;
	}
	
}
