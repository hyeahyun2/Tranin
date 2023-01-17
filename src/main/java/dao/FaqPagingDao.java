package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FaqPagingDao {
	DBProperty db = new DBProperty();
	public ResultSet getAllFaq() throws SQLException {
		String sql = "SELECT count(*) FROM faq_bd";
		db.pstmt = db.conn.prepareStatement(sql);
		db.rs = db.pstmt.executeQuery();
		
		return db.rs;
	}
}
