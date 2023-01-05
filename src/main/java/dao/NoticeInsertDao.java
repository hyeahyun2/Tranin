package dao;

import java.util.Date;

public class NoticeInsertDao {
	
	public boolean registContent(String title, String content) {
		
		DBProperty db = new DBProperty();

		String sql = "insert into notice_bd(title, content) values (?, ?)";  
		int rs = 0;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, title);
			db.pstmt.setString(2, content);
			
			rs = db.pstmt.executeUpdate(); 
			System.out.println("글 등록에 성공하였습니다.");
		} catch(Exception e) {
			System.out.println("글 등록을 실패하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return (rs == 1);
	}
}
