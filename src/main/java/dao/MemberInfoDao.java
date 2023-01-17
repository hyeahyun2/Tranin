package dao;

import dto.MemberDto;

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
		
		String sql = "SELECT nickname from tranin_member where no = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, no);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				id = db.rs.getString("nickname"); // id 얻기
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
	
	// no로 member 정보 얻기
	public MemberDto getMemberByNo(int no) {

		DBProperty db = new DBProperty();
		
		MemberDto member = null;
		
		String sql = "SELECT * from tranin_member where no = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, no);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				member = new MemberDto();
				member.setNo(no);
				member.setId(db.rs.getString("id"));
				member.setNickName(db.rs.getString("nickname"));
				member.setAddress(db.rs.getString("address"));
				member.setZipCode(db.rs.getString("zipcode"));
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
		
		return member; // 없는 no인 경우 null 반환
	}
	
	// id로 member 정보 얻기
		public MemberDto getMemberById(String id) {

			DBProperty db = new DBProperty();
			
			MemberDto member = null;
			
			String sql = "SELECT * from tranin_member where id = ?";
			try {
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, id);
				db.rs = db.pstmt.executeQuery();
				if(db.rs.next()) {
					member = new MemberDto();
					member.setNo(db.rs.getInt("no"));
					member.setId(id);
					member.setNickName(db.rs.getString("nickname"));
					member.setAddress(db.rs.getString("address"));
					member.setZipCode(db.rs.getString("zipcode"));
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
			
			return member; // 없는 no인 경우 null 반환
		}
}
