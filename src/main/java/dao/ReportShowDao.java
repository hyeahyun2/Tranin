package dao;

import java.util.ArrayList;

import dto.ReportMemberDto;

public class ReportShowDao {
	public ArrayList<ReportMemberDto> showReport() {
		DBProperty db = new DBProperty();
		ArrayList<ReportMemberDto> list = new ArrayList<>();
		String sql = "SELECT * FROM report_request WHERE accept = 'false' ORDER BY regDate DESC";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.rs = db.pstmt.executeQuery();
			
			while(db.rs.next()) {
				ReportMemberDto dto = new ReportMemberDto();
				dto.setReportNo(db.rs.getInt("reportNo"));
				dto.setUserId(db.rs.getString("userId"));
				dto.setReportId(db.rs.getString("reportId"));
				dto.setReportUrl(db.rs.getString("reportUrl"));
				dto.setRegDate(db.rs.getDate("regDate"));
				dto.setAccept(db.rs.getBoolean("accept"));
				dto.setAccept(db.rs.getBoolean("reject"));
				list.add(dto);
			}
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
