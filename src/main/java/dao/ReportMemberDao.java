package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.ReportMemberDto;

public class ReportMemberDao {
	
	// 신고가 수락된 데이터의 accept 컬럼의 값을 true로 변경
	public void acceptMemberReport(int reportNo) {
		DBProperty db = new DBProperty();
		String sql = "UPDATE report_request SET accept ='true' WHERE reportNo = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, reportNo);
			db.rs = db.pstmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.conn != null) db.conn.close();
				if(db.pstmt != null) db.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 신고가 거절되었을 경우 데이터의 reject 컬럼의 값을 true로 변경
	public void rejectMemberReport(int reportNo) {
		DBProperty db = new DBProperty();
		String sql = "UPDATE report_request SET reject ='true' WHERE reportNo = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, reportNo);
			db.rs = db.pstmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.conn != null) db.conn.close();
				if(db.pstmt != null) db.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 신고 요청된 사용자 id로 no 찾아오기
	public int getReportMemberNoById(String reportId) {
		DBProperty db = new DBProperty();
		int reportNo = 0;
		String sql = "SELECT no FROM tranin_member WHERE id = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, reportId);
			db.rs = db.pstmt.executeQuery();
			
			if(db.rs.next()) {
				reportNo = db.rs.getInt("no");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.conn != null) db.conn.close();
				if(db.pstmt != null) db.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reportNo;
	}
	
	// 신고요청된 글 작성자의 ID 불러오기
	public String getReportMemberIdByNo(int no) {
		DBProperty db = new DBProperty();
		String reportId = null;
		String sql = "SELECT id FROM tranin_member WHERE no = ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);			
			db.pstmt.setInt(1, no);
			db.rs = db.pstmt.executeQuery();
			
			if(db.rs.next())
				reportId = db.rs.getString("id");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.conn != null) db.conn.close();
				if(db.pstmt != null) db.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reportId;
	}
	
	// 신고 요청 페이지에 데이터 추가
	public boolean insertReportPost(String memberId, String reportId, String reportUrl) {
		DBProperty db = new DBProperty();
		int rs = 0;
		String sql = "INSERT INTO report_request VALUES (null, ?, ?, ?, now(), 'false', 'false')";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, memberId);
			db.pstmt.setString(2, reportId);
			db.pstmt.setString(3, reportUrl);
			rs = db.pstmt.executeUpdate();	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.rs != null) db.rs.close();
				if(db.conn != null) db.conn.close();
				if(db.pstmt != null) db.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs == 1;
	}
	
	// 신고 요청된 멤버 불러오기
	public ArrayList<ReportMemberDto> showReport() {
		DBProperty db = new DBProperty();
		ArrayList<ReportMemberDto> list = new ArrayList<>();
		String sql = "SELECT * FROM report_request WHERE accept = 'false' AND reject = 'false' ORDER BY regDate DESC";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.rs = db.pstmt.executeQuery();
			System.out.println("여기까지 옴.");
			while(db.rs.next()) {
				ReportMemberDto dto = new ReportMemberDto();
				dto.setReportNo(db.rs.getInt("reportNo"));
				dto.setUserId(db.rs.getString("userId"));
				dto.setReportId(db.rs.getString("reportId"));
				dto.setReportUrl(db.rs.getString("reportUrl"));
				dto.setRegDate(db.rs.getDate("regDate"));
				dto.setAccept(db.rs.getString("accept"));
				dto.setReject(db.rs.getString("reject"));
				list.add(dto);
			}
			System.out.println("모두 끝");
		} catch(Exception e) {
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
