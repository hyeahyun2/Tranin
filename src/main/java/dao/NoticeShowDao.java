package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeDto;

public class NoticeShowDao {
	DBProperty db = null;
	
	public List<NoticeDto> showNotice() {
		NoticeDto dto = null;
		List<NoticeDto> list = new ArrayList<>();
		db = new DBProperty();
		String sql = "select * from notice_bd order by notice_no desc";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				dto = new NoticeDto(db.rs.getInt("notice_no"), db.rs.getString("title"), db.rs.getDate("reg_Date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
