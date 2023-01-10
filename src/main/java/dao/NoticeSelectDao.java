package dao;

import java.util.ArrayList;

import dto.NoticeDto;

public class NoticeSelectDao {
	
	public ArrayList<NoticeDto> getNotice(int noticeNo) {
		DBProperty db = new DBProperty();
		
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		String sql = "SELECT * FROM notice_bd WHERE notice_no = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, noticeNo);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				NoticeDto dto = new NoticeDto(db.rs.getInt("notice_no"), db.rs.getString("title"), db.rs.getString("content"), db.rs.getDate("reg_date"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	
}
