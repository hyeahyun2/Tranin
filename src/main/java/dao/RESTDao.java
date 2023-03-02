package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MarketResponseDto;
import dto.MemberDto;
import dto.NoticeDto;

public class RESTDao {
	DBProperty dbProperty = null;
	
	public ArrayList<MarketResponseDto> getSearchTransactionDoneMarketListREST(String parameter, String parameter2,
			int writerNo) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MarketResponseDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketResponseDto> list = new ArrayList<>();
		
    	String sql = "SELECT tranin_market.*, tranin_market_disabled.report FROM tranin_market RIGHT JOIN tranin_market_disabled ON tranin_market.market_no = tranin_market_disabled.market_no WHERE tranin_market.disabled='true' AND tranin_market_disabled.report='soldOut' AND tranin_market.writer_no=? AND tranin_market."+parameter+" LIKE CONCAT('%',?,'%') ORDER BY tranin_market.market_no DESC";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, writerNo);
			pstmt.setString(2, parameter2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MarketResponseDto(
						rs.getInt("market_no"),
						rs.getInt("writer_no"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getString("part"),
						rs.getInt("price"),
						rs.getString("write_date"),
						rs.getInt("hits"),
						rs.getString("ip"),
						new String[]{
							rs.getString("image_1"),
							rs.getString("image_2"),
							rs.getString("image_3"),
							rs.getString("image_4"),
							rs.getString("image_5")
						},
						rs.getString("disabled"),
						rs.getString("report")
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MarketResponseDto> getTransactionDoneMarketListREST(int writerNo) {
		dbProperty = new DBProperty();
		MarketResponseDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketResponseDto> list = new ArrayList<>();
		
    	String sql = "SELECT DISTINCT tranin_market.*, tranin_market_disabled.report FROM tranin_market RIGHT JOIN tranin_market_disabled ON tranin_market.market_no = tranin_market_disabled.market_no WHERE tranin_market.disabled='true' AND tranin_market_disabled.report='soldOut' AND tranin_market.writer_no=? ORDER BY market_no DESC";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, writerNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MarketResponseDto(
						rs.getInt("market_no"),
						rs.getInt("writer_no"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getString("part"),
						rs.getInt("price"),
						rs.getString("write_date"),
						rs.getInt("hits"),
						rs.getString("ip"),
						new String[]{
							rs.getString("image_1"),
							rs.getString("image_2"),
							rs.getString("image_3"),
							rs.getString("image_4"),
							rs.getString("image_5")
						},
						rs.getString("disabled"),
						rs.getString("report")
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberDto> searchBannedMemberREST(String parameter, String parameter2) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
    	String sql = "SELECT * FROM tranin_member WHERE banned='true' AND "+parameter+" LIKE CONCAT('%',?,'%') ORDER BY no DESC";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, parameter2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MemberDto(rs.getInt("no"), rs.getString("id"), rs.getString("nickname"),rs.getString("address"),rs.getString("zipcode"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberDto> searchMemberREST(String parameter, String parameter2) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
    	String sql = "SELECT * FROM tranin_member WHERE banned='false' AND "+parameter+" LIKE CONCAT('%',?,'%') ORDER BY no DESC";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, parameter2);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MemberDto(rs.getInt("no"), rs.getString("id"), rs.getString("nickname"),rs.getString("address"),rs.getString("zipcode"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 글 목록 가져오기
	public ArrayList<NoticeDto> showNoticeREST() {
		NoticeDto dto = null;
		ArrayList<NoticeDto> list = new ArrayList<>();
		DBProperty db = new DBProperty();
    	String sql = "SELECT * FROM notice_bd ORDER BY notice_no DESC";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
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
	public ArrayList<NoticeDto> getNoticeREST(int noticeNo) {
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
	public ArrayList<NoticeDto> getSearchREST(String text) {
		
		DBProperty db = new DBProperty();
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>(); 
		// 페이징 처리
		try {
			String sql = "SELECT * FROM notice_bd WHERE title LIKE '%" + text + "%' ORDER BY notice_no DESC";
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
}
