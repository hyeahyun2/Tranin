package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeDto;

public class NoticePagingDao {
	DBProperty db = new DBProperty();
	public ResultSet getAllNotice() throws SQLException {
		String sql = "SELECT count(*) FROM notice_bd";
		db.pstmt = db.conn.prepareStatement(sql);
		db.rs = db.pstmt.executeQuery();
		
		return db.rs;
		
	}
}
