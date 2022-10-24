package bss.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class supplier {
    // fields
public final int s_suppkey;
	public final String s_name;
	public final String s_address;
	public final int s_nationkey;
	public final String s_phone;
	public final double s_acctbal;
	public final String s_comment;

    public supplier(int s_suppkey, String s_name, String s_address, int s_nationkey, String s_phone, double s_acctbal, String s_comment) {
    this.s_suppkey = s_suppkey;
		this.s_name = s_name;
		this.s_address = s_address;
		this.s_nationkey = s_nationkey;
		this.s_phone = s_phone;
		this.s_acctbal = s_acctbal;
		this.s_comment = s_comment;
    }

    // create from database connection
	static supplier[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from supplier");
	    rs.next();
	    int ctn = rs.getInt(1);
	    supplier[] data = new supplier[ctn];
	    rs = stm.executeQuery("select * from supplier");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new supplier(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getDouble(6),
					rs.getString(7));
	    }
	    return data;
	}
	
}
