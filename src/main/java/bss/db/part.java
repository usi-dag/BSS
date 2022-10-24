package bss.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class part {
    // fields
public final int p_partkey;
	public final String p_name;
	public final String p_mfgr;
	public final String p_brand;
	public final String p_type;
	public final int p_size;
	public final String p_container;
	public final double p_retailprice;
	public final String p_comment;

    public part(int p_partkey, String p_name, String p_mfgr, String p_brand, String p_type, int p_size, String p_container, double p_retailprice, String p_comment) {
    this.p_partkey = p_partkey;
		this.p_name = p_name;
		this.p_mfgr = p_mfgr;
		this.p_brand = p_brand;
		this.p_type = p_type;
		this.p_size = p_size;
		this.p_container = p_container;
		this.p_retailprice = p_retailprice;
		this.p_comment = p_comment;
    }

    // create from database connection
	static part[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from part");
	    rs.next();
	    int ctn = rs.getInt(1);
	    part[] data = new part[ctn];
	    rs = stm.executeQuery("select * from part");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new part(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getString(7),
					rs.getDouble(8),
					rs.getString(9));
	    }
	    return data;
	}
	
}
