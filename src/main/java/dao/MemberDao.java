package dao;

import dto.MemberDto;

public class MemberDao {
	
	// 로그인
	public boolean loginMember(String id, String pw) {
		DBProperty db = new DBProperty();
		
		int memberNum = -1; // 초기값
		String sql = "SELECT COUNT(*) AS cnt FROM tranin_member "
				+ "WHERE id = ? AND pw = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, id);
			db.pstmt.setString(2, pw);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				memberNum = db.rs.getInt("cnt");
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
		
		// 로그인 정보 일치 >= 1, 불일치 = 0
		return memberNum > 0;
	}
	
	// 회원가입
	public boolean insertMember(MemberDto member) {
		DBProperty db = new DBProperty();
		
		int ins = 0;
		String sql = "INSERT INTO tranin_member "
				+ "(id, pw, nickname, address, zipcode, reg_date) "
				+ "VALUES (?, ?, ?, ?, ?, now())";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, member.getId());
			db.pstmt.setString(2, member.getPw());
			db.pstmt.setString(3, member.getNickName());
			db.pstmt.setString(4, member.getAddress());
			db.pstmt.setString(5, member.getZipCode());
			ins = db.pstmt.executeUpdate(); // 1 : 회원가입 성공
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
		return ins == 1;
	}
	
	// 이메일 중복 검사
	public boolean isMemberId(String id) {
		DBProperty db = new DBProperty();
		
		int memberNum = -1; // 초기값
		String sql = "SELECT COUNT(*) FROM tranin_member "
				+ "WHERE id = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, id);
			db.rs = db.pstmt.executeQuery();
			db.rs.next();
			memberNum = db.rs.getInt(1); // 별명 중복 없을 경우 0
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
		
		// 별명 중복 없을 경우 0 , 있을 경우 >= 1
		return memberNum > 0;
	}
	
	// 별명 중복 검사
	public boolean isMemberNick(String nickname) {
		DBProperty db = new DBProperty();
		
		int memberNum = -1; // 초기값
		String sql = "SELECT COUNT(*) FROM tranin_member "
				+ "WHERE nickname = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, nickname);
			db.rs = db.pstmt.executeQuery();
			db.rs.next();
			memberNum = db.rs.getInt(1); // 별명 중복 없을 경우 0
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
		
		// 별명 중복 없을 경우 0 , 있을 경우 >= 1
		return memberNum > 0;
	}
	
	// 비밀번호 변경
	public boolean changePw(String id, String pw) {
		DBProperty db = new DBProperty();
		
		int upd = 0; // 초기값
		String sql = "UPDATE tranin_member SET pw = ? WHERE id = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, pw);
			db.pstmt.setString(2, id);
			upd = db.pstmt.executeUpdate();
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
		
		// 별명 중복 없을 경우 0 , 있을 경우 >= 1
		return upd != 0;
	}
}
