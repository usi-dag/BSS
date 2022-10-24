package bss.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class region {
    // fields
public final int r_regionkey;
	public final String r_name;
	public final String r_comment;

    public region(int r_regionkey, String r_name, String r_comment) {
    this.r_regionkey = r_regionkey;
		this.r_name = r_name;
		this.r_comment = r_comment;
    }

    // create from database connection
	static region[] create(Connection con) throws SQLException {
	    ResultSet rs;
	    Statement stm = con.createStatement();
	    rs = stm.executeQuery("select count(*) from region");
	    rs.next();
	    int ctn = rs.getInt(1);
	    region[] data = new region[ctn];
	    rs = stm.executeQuery("select * from region");
	    int i=0;
	    while(rs.next()) {
	        data[i++] = new region(rs.getInt(1),
					rs.getString(2),
					rs.getString(3));
	    }
	    return data;
	}
	
}
