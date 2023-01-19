package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.FaqDto;


public class FaqShowDao {
	DBProperty db = new DBProperty();
	
	public ArrayList<FaqDto> showFaq(int pageNum) {
		FaqDto dto = null;
		ArrayList<FaqDto> list = new ArrayList<>();
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM faq_bd ORDER BY faq_no DESC LIMIT ?, ?";
//		String sql = "select * from notice_bd order by notice_no desc";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, startNum);
			db.pstmt.setInt(2, cntListPerPage);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				dto = new FaqDto(db.rs.getInt("faq_no"), db.rs.getString("title"), db.rs.getString("content"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
