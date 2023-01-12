package dao;

public class MemberInfoDao {
	
	// id으로 nickname 얻기
	public String getNicknameById(String memberId) {
		DBProperty db = new DBProperty();
		
		String nick = null;
		
		String sql = "SELECT nickname from tranin_member where id = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, memberId);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				nick = db.rs.getString("nickname"); // no 얻기
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return nick; // 존재하지 않을 경우 null 반환
	}
	
	// no으로 nickname 얻기
	public String getNicknameByNo(int no) {

		DBProperty db = new DBProperty();
		
		String id = null;
		
		String sql = "SELECT id from tranin_member where no = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, no);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				id = db.rs.getString("id"); // id 얻기
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return id; // 없는 no인 경우 null 반환
	}
	
	// nickname으로 id 얻기
	public String getIdByNick(String nickname) {
		DBProperty db = new DBProperty();
		
		String id = null;
		
		String sql = "SELECT id from tranin_member where nickname = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, nickname);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				id = db.rs.getString("id"); // id 얻기
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return id; // 존재하지 않을 경우 null 반환
	}
}
