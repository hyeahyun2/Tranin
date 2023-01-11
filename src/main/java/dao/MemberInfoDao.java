package dao;

public class MemberInfoDao {
	
	// nickname으로 no 얻기
	public int getMemberNo(String memberId) {
		DBProperty db = new DBProperty();
		
		int myNo = -1;
		
		String sql = "SELECT no from tranin_member where id = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, memberId);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				myNo = db.rs.getInt("no"); // no 얻기
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
		
		return myNo; // 존재하지 않는 nickname인 경우 -1 반환
	}
	
	// no으로 id 얻기
	public String getMemberId(int no) {
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
}
