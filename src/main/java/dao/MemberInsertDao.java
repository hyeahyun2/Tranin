package dao;
import dto.MemberDto;

public class MemberInsertDao {
	
	
	// 회원가입
	public boolean register(String id, String pw, String nick, String zipCode, String address) {
		
		DBProperty db = new DBProperty();
		String sql = "insert into tranin_member(id, pw, nickName, address, zipCode) values(?, ?, ?, ?, ?)";
		int upd = 0;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, id);
			db.pstmt.setString(2, pw);
			db.pstmt.setString(3, nick);
			db.pstmt.setString(4, zipCode);
			db.pstmt.setString(5, address);

			// 1
			upd = db.pstmt.executeUpdate(); // 영향 받은 행 개수 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return (upd==1); // 성공 -> 1 -> true
	}
}
