package dao;

import java.util.ArrayList;

import dto.MarketDto;

public class MarketDao {
	
	// 글 목록 loadNum만큼 불러오기
	public ArrayList<MarketDto> getPostList(String part, int clickNum, int loadNum){
		DBProperty db = new DBProperty();
		ArrayList<MarketDto> postList = new ArrayList<MarketDto>();

		String sql = "SELECT market_no, writer_no, title, price, write_date, hits, image_1 "
				+ "FROM tranin_market "
				+ "WHERE part = ? AND disabled = 'false' "
				+ "ORDER BY write_date DESC "
				+ "LIMIT ?, ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, part);
			db.pstmt.setInt(2, clickNum * loadNum);
			db.pstmt.setInt(3, loadNum);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				MarketDto post = new MarketDto();
				post.setMarketNo(db.rs.getInt("market_no"));
				post.setWriterNo(db.rs.getInt("writer_no"));
				post.setTitle(db.rs.getString("title"));
				post.setPrice(db.rs.getInt("price"));
				post.setWriteDate(db.rs.getString("write_date"));
				post.setHits(db.rs.getInt("hits"));
				post.setImage1(db.rs.getString("image_1"));
				postList.add(post);
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
		
		return postList;
	}
	
	// part별 게시글 총 개수 구하기
	public int getPostCount(String part) {
		DBProperty db = new DBProperty();
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market "
				+ "WHERE part = ? AND disabled = 'false'";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, part);
			db.rs = db.pstmt.executeQuery();
			db.rs.next();
			count = db.rs.getInt(1);
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
		return count;
	}
	
	// 게시글 등록
	public int insertPost(String part, String writeID, String title, int price, String content, String ip, String[] imageList) {
		DBProperty db = new DBProperty();
		
		String sql = "insert into tranin_market "
				+ "(writer_no, title, content, part, price, write_date, ip, image_1, image_2, image_3, image_4, image_5) "
				+ "values ((select `no` from tranin_member where id = ?), ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?)";
		
		int ins = 0;
		int marketNo = 0;
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, writeID);
			db.pstmt.setString(2, title);
			db.pstmt.setString(3, content);
			db.pstmt.setString(4, part);
			db.pstmt.setInt(5, price);
			db.pstmt.setString(6, ip);
			db.pstmt.setString(7, imageList[0]);
			db.pstmt.setString(8, imageList[1]);
			db.pstmt.setString(9, imageList[2]);
			db.pstmt.setString(10, imageList[3]);
			db.pstmt.setString(11, imageList[4]);
			ins = db.pstmt.executeUpdate();
			if(ins != 0) {
				sql = "select market_no from tranin_market k "
						+ "join tranin_member m on k.writer_no = m.`no` "
						+ "where m.id = ? "
						+ "order by market_no desc "
						+ "limit 0, 1";
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, writeID);
				db.rs = db.pstmt.executeQuery();
				if(db.rs.next()) { // 게시글 등록 성공
					marketNo = db.rs.getInt("market_no");
				}
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
		return marketNo; // 실패 : 0, 성공 : market_no 반환
	}
	
	// 조회수 증가시키기
	public boolean increaseHits(int no) {
		DBProperty db = new DBProperty();
		
		int upd = 0;
		String sql = "UPDATE tranin_market SET hits = hits + 1 WHERE market_no = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, no);
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
		return upd != 0;
	}
	// market_no로 해당 게시글 정보 불러오기
	public MarketDto getPostInfoByNo(int no) {
		DBProperty db = new DBProperty();
		MarketDto marketPost = null;
		
		String sql = "SELECT * FROM tranin_market "
				+ "WHERE market_no = ? AND disabled = 'false'";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, no);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) { // 결과 존재하면
				marketPost = new MarketDto();
				marketPost.setMarketNo(no);
				marketPost.setWriterNo(db.rs.getInt("writer_no"));
				marketPost.setTitle(db.rs.getString("title"));
				marketPost.setContent(db.rs.getString("content"));
				marketPost.setPrice(db.rs.getInt("price"));
				marketPost.setWriteDate(db.rs.getString("write_date"));
				marketPost.setHits(db.rs.getInt("hits"));
				marketPost.setImage1(db.rs.getString("image_1"));
				marketPost.setImage2(db.rs.getString("image_2"));
				marketPost.setImage3(db.rs.getString("image_3"));
				marketPost.setImage4(db.rs.getString("image_4"));
				marketPost.setImage5(db.rs.getString("image_5"));
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
		
		// select 결과 존재하지 않을 때 => null 반환
		return marketPost;
	}
	
}
