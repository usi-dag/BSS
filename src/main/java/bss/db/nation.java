package bss.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class nation {
    // fields
public final int n_nationkey;
	public final String n_name;
	public final int n_regionkey;
	public final String n_comment;

    public nation(int n_nationkey, String n_name, int n_regionkey, String n_comment) {
    this.n_nationkey = n_nationkey;
		this.n_name = n_name;
		this.n_regionkey = n_regionkey;
		this.n_comment = n_comment;
    }

    // create from database connection
	static nation[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from nation");
	    rs.next();
	    int ctn = rs.getInt(1);
	    nation[] data = new nation[ctn];
	    rs = stm.executeQuery("select * from nation");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new nation(rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4));
	    }
	    return data;
	}
	
}
