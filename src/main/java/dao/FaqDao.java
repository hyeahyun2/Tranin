package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.FaqDto;

public class FaqDao {
	
	// 자주 묻는 질문 글 작성하기.
	public boolean registFaqContent(String title, String content) {
		
		DBProperty db = new DBProperty();

		String sql = "insert into faq_bd(title, content) values (?, ?)";  
		int rs = 0;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, title);
			db.pstmt.setString(2, content);
			
			rs = db.pstmt.executeUpdate(); 
			System.out.println("글 등록에 성공하였습니다.");
		} catch(Exception e) {
			System.out.println("글 등록을 실패하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return (rs == 1);
	}
	
	// 모든 질문에 대한 페이징 처리
	public ResultSet getAllFaq() throws SQLException {
		DBProperty db = new DBProperty();
		String sql = "SELECT count(*) FROM faq_bd";
		db.pstmt = db.conn.prepareStatement(sql);
		db.rs = db.pstmt.executeQuery();
		
		return db.rs;
	}
	
	// 등록된 질문들 보여지게 불러오기
	public ArrayList<FaqDto> showFaq(int pageNum) {
		DBProperty db = new DBProperty();
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
	
	// 특정 질문 수정
	public boolean updateFaq(String title, String content, int faqNo) {
		DBProperty db = new DBProperty();
		String sql = "UPDATE faq_bd SET title = ?, content = ? WHERE faq_no = ?";
		
		int rs = 0;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, title);
			db.pstmt.setString(2, content);
			db.pstmt.setInt(3, faqNo);
			
			rs = db.pstmt.executeUpdate();
			System.out.println("글 수정을 성공하였습니다.");
		} catch(Exception e) {
			System.out.println("글 수정을 실패하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return (rs==1);
	}
	
	
}
