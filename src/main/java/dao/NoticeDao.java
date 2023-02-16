package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.NoticeDto;

public class NoticeDao {
	
	// 글 등록하기
	public boolean registContent(String title, String content) {
		
		DBProperty db = new DBProperty();

		String sql = "insert into notice_bd(title, content) values (?, ?)";  
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
	
	// 글 목록 가져오기
	public ArrayList<NoticeDto> showNotice(int pageNum) {
		NoticeDto dto = null;
		ArrayList<NoticeDto> list = new ArrayList<>();
		DBProperty db = new DBProperty();
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM notice_bd ORDER BY notice_no DESC LIMIT ?, ?";
//		String sql = "select * from notice_bd order by notice_no desc";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, startNum);
			db.pstmt.setInt(2, cntListPerPage);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				dto = new NoticeDto(db.rs.getInt("notice_no"), db.rs.getString("title"), db.rs.getDate("reg_Date"));
				list.add(dto);
			}
		} catch (SQLException e) {
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
	
	// 특정 글 가져오기
	public ArrayList<NoticeDto> getNotice(int noticeNo) {
		DBProperty db = new DBProperty();
		
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>();
		String sql = "SELECT * FROM notice_bd WHERE notice_no = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, noticeNo);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				NoticeDto dto = new NoticeDto(db.rs.getInt("notice_no"), db.rs.getString("title"), db.rs.getString("content"), db.rs.getDate("reg_date"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db.pstmt != null) db.pstmt.close();
				if(db.conn != null) db.conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	// 검색 키워드 바탕으로 글 목록 가져오기
	private Statement stmt = null;
	public ArrayList<NoticeDto> getSearch(String text, int pageNum) {
		
		DBProperty db = new DBProperty();
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>(); 
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
		try {
			String sql = "SELECT * FROM notice_bd WHERE title LIKE '%" + text + "%' ORDER BY notice_no DESC LIMIT " + startNum + ", " + cntListPerPage;
			stmt = db.conn.createStatement();
			System.out.println("text 내용 : "+text);
			db.rs = stmt.executeQuery(sql);
			while(db.rs.next()) {
				NoticeDto dto = new NoticeDto();
				dto.setNoticeNo(db.rs.getInt("notice_no"));
				dto.setTitle(db.rs.getString("title"));
				dto.setRegDate(db.rs.getDate("reg_date"));
				list.add(dto);
			}
			System.out.println("데이터 베이스 담기 성공");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 특정 글 수정하기 
	public boolean updateContent(String title, String content, int noticeNo) {
		
		DBProperty db = new DBProperty();
		String sql = "update notice_bd set title=?, content=? where notice_no=?";
		int rs = 0;
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, title);
			db.pstmt.setString(2, content);
			db.pstmt.setInt(3, noticeNo);
			
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
	
	// 모든 게시물에 대한 페이징 처리
	public ResultSet getAllNotice() throws SQLException {
		DBProperty db = new DBProperty();
		String sql = "SELECT count(*) FROM notice_bd";
		db.pstmt = db.conn.prepareStatement(sql);
		db.rs = db.pstmt.executeQuery();
		
		return db.rs;
	}
	
	// 검색 키워드로 불러온 게시물 목록에 대한 페이징 처리
	public ResultSet getSearchNotice(String text) throws SQLException {
		DBProperty db = new DBProperty();
		String sql = "SELECT count(*) FROM notice_bd WHERE title LIKE '%" + text + "%'";
		db.pstmt = db.conn.prepareStatement(sql);
		db.rs = db.pstmt.executeQuery();
		
		return db.rs;
	}
	
	// 특정 게시물 삭제하기.
	 public boolean deleteNotice(int notice_no) {

	        DBProperty db = new DBProperty();
	        String sql = "delete from notice_bd where `notice_no`=? ";
	        int upd = 0;
	        try {
	            db.pstmt = db.conn.prepareStatement(sql);
	            db.pstmt.setInt(1, notice_no);


	            // 1
	            upd = db.pstmt.executeUpdate(); // 영향 받은 행 개수 반환

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (db.pstmt != null) db.pstmt.close();
	                if (db.conn != null) db.conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return (upd == 1); // 성공 -> 1 -> true
	    }
	
	
	// 삭제된 게시물 데이터를 삭제 관리 태이블에 추가하기
	 public boolean insertToDB(int noticeNo, String title, String content) {
	        DBProperty db = new DBProperty();
	        String sql = "insert into notice_deteled_bd (notice_deletedno ,title, content) values(?,?, ?)";
	        int upd = 0;
	        try {
	            db.pstmt = db.conn.prepareStatement(sql);
	            db.pstmt.setInt(1, noticeNo);
	            db.pstmt.setString(2, title);
	            db.pstmt.setString(3, content);

	            // 1
	            upd = db.pstmt.executeUpdate(); // 영향 받은 행 개수 반환

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (db.pstmt != null) db.pstmt.close();
	                if (db.conn != null) db.conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return (upd == 1); // 성공 -> 1 -> true
	    }
}
