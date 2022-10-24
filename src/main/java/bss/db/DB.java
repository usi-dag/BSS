package bss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DB {

    private static DB INSTANCE;

    // tables
    public final customer[] customer;
	public final lineitem[] lineitem;
	public final nation[] nation;
	public final orders[] orders;
	public final part[] part;
	public final partsupp[] partsupp;
	public final region[] region;
	public final supplier[] supplier;

    public DB(customer[] customer, lineitem[] lineitem, nation[] nation, orders[] orders, part[] part, partsupp[] partsupp, region[] region, supplier[] supplier) {
        this.customer = customer;
		this.lineitem = lineitem;
		this.nation = nation;
		this.orders = orders;
		this.part = part;
		this.partsupp = partsupp;
		this.region = region;
		this.supplier = supplier;
    }

    public static DB create() {
        try {
            if(INSTANCE == null) {
                Connection con = connection();
                return new DB(
                    bss.db.customer.create(con),
					bss.db.lineitem.create(con),
					bss.db.nation.create(con),
					bss.db.orders.create(con),
					bss.db.part.create(con),
					bss.db.partsupp.create(con),
					bss.db.region.create(con),
					bss.db.supplier.create(con));
            }
            return INSTANCE;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection connection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:./TPC-H.db");
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
