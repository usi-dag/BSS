package bss.db;

import java.sql.*;
import java.time.LocalDate;

public final class lineitem {
    // fields
public final int l_orderkey;
	public final int l_partkey;
	public final int l_suppkey;
	public final int l_linenumber;
	public final double l_quantity;
	public final double l_extendedprice;
	public final double l_discount;
	public final double l_tax;
	public final String l_returnflag;
	public final String l_linestatus;
	public final Date l_shipdate;
	public final Date l_commitdate;
	public final Date l_receiptdate;
	public final String l_shipinstruct;
	public final String l_shipmode;
	public final String l_comment;

    public lineitem(int l_orderkey, int l_partkey, int l_suppkey, int l_linenumber, double l_quantity, double l_extendedprice, double l_discount, double l_tax, String l_returnflag, String l_linestatus, Date l_shipdate, Date l_commitdate, Date l_receiptdate, String l_shipinstruct, String l_shipmode, String l_comment) {
    this.l_orderkey = l_orderkey;
		this.l_partkey = l_partkey;
		this.l_suppkey = l_suppkey;
		this.l_linenumber = l_linenumber;
		this.l_quantity = l_quantity;
		this.l_extendedprice = l_extendedprice;
		this.l_discount = l_discount;
		this.l_tax = l_tax;
		this.l_returnflag = l_returnflag;
		this.l_linestatus = l_linestatus;
		this.l_shipdate = l_shipdate;
		this.l_commitdate = l_commitdate;
		this.l_receiptdate = l_receiptdate;
		this.l_shipinstruct = l_shipinstruct;
		this.l_shipmode = l_shipmode;
		this.l_comment = l_comment;
    }

    // create from database connection
	static lineitem[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from lineitem");
	    rs.next();
	    int ctn = rs.getInt(1);
	    lineitem[] data = new lineitem[ctn];
	    rs = stm.executeQuery("select * from lineitem");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new lineitem(rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getInt(4),
					rs.getDouble(5),
					rs.getDouble(6),
					rs.getDouble(7),
					rs.getDouble(8),
					rs.getString(9),
					rs.getString(10),
					new Date((int) LocalDate.parse(rs.getObject(11).toString()).toEpochDay()),
					new Date((int) LocalDate.parse(rs.getObject(12).toString()).toEpochDay()),
					new Date((int) LocalDate.parse(rs.getObject(13).toString()).toEpochDay()),
					rs.getString(14),
					rs.getString(15),
					rs.getString(16));
	    }
	    return data;
	}
	
}
