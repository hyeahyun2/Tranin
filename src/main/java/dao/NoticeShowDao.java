package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeDto;

public class NoticeShowDao {
	DBProperty db = null;
	
	public ArrayList<NoticeDto> showNotice(int pageNum) {
		NoticeDto dto = null;
		ArrayList<NoticeDto> list = new ArrayList<>();
		db = new DBProperty();
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM notice_bd ORDER BY notice_no DESC LIMIT ?, ?";
//		String sql = "select * from notice_bd order by notice_no desc";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, startNum);
			db.pstmt.setInt(2, cntListPerPage);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				dto = new NoticeDto(db.rs.getInt("notice_no"), db.rs.getString("title"), db.rs.getDate("reg_Date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
				if(db.rs != null) db.rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
