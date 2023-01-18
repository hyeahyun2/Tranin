package dao;

import java.sql.SQLException;

public class ReportAcceptDao {
	
	public boolean finishReport(int reportNo, String userId) throws SQLException {
		DBProperty db = new DBProperty();
		int result = 0;
		String sql = "DELETE FROM report_request WHERE reportNo = ?";
		db.pstmt = db.conn.prepareStatement(sql);
		db.pstmt.setInt(1, reportNo);
		result = db.pstmt.executeUpdate();
		if(result == 1) {
			try {
				sql = "UPDATE tranin_member SET banned = 'false' WHERE memberId = ?";
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, userId);
				result = db.pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(db.conn != null) db.conn.close();
					if(db.pstmt != null) db.pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("회원 차단 상태로 수정 실패");
		}
		return result == 1;
	}
}
