package dao;

import java.sql.SQLException;

public class ReportMemberDao {
	DBProperty db = null;
	public String getReportMemberIdByNo(int no) {
		db = new DBProperty();
		String reportId = null;
		String sql = "SELECT id FROM tranin_member WHERE id = ?";
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
	
	public boolean insertReportPost(String memberId, String reportId, String reportUrl) {
		db = new DBProperty();
		int rs = 0;
		String sql = "INSERT INTO report_request VALUES (?, ?, ?)";
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

}
