package dao;

import dto.NoticeDto;

public class NoticeUpdateDao {
	
	public boolean updateContent(String title, String content, int noticeNo) {
		
		DBProperty db = new DBProperty();
		String sql = "update notice_bd set title=?, content=? where notice_no=?";
		int rs = 0;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, title);
			db.pstmt.setString(2, content);
			db.pstmt.setInt(3, noticeNo);
			
			rs = db.pstmt.executeUpdate();
			System.out.println("글 수정을 성공하였습니다.");
		} catch(Exception e) {
			System.out.println("글 수정을 실패하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return (rs==1);
	}
	
}
