package dao;

import java.util.ArrayList;

import dto.MarketDto;

public class MarketDao {
	
	// 글 목록 loadNum만큼 불러오기
	public ArrayList<MarketDto> getPostList(String part, int clickNum, int loadNum){
		DBProperty db = new DBProperty();
		ArrayList<MarketDto> postList = new ArrayList<MarketDto>();

		String sql = "SELECT market_no, writer_no, title, price, write_date, hits, image_1 "
				+ "FROM tranin_market k "
				+ "JOIN tranin_member m ON k.writer_no = m.`no` "
				+ "WHERE part = ? AND disabled = 'false' "
				+ "AND m.banned = 'false' "
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
				post.setImage(0, db.rs.getString("image_1"));
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
	
	// 검색 - 글 목록 loadNum만큼 불러오기
	public ArrayList<MarketDto> getSearchPostList(String part, String searchKey, int clickNum, int loadNum){
		DBProperty db = new DBProperty();
		ArrayList<MarketDto> postList = new ArrayList<MarketDto>();

		String sql = "SELECT market_no, writer_no, title, price, write_date, hits, image_1 "
				+ "FROM tranin_market k "
				+ "JOIN tranin_member m ON k.writer_no = m.`no` "
				+ "WHERE part = ? AND title LIKE ? AND disabled = 'false' "
				+ "AND m.banned = 'false' " 
				+ "ORDER BY write_date DESC "
				+ "LIMIT ?, ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, part);
			db.pstmt.setString(2, "%" + searchKey + "%");
			db.pstmt.setInt(3, clickNum * loadNum);
			db.pstmt.setInt(4, loadNum);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) {
				MarketDto post = new MarketDto();
				post.setMarketNo(db.rs.getInt("market_no"));
				post.setWriterNo(db.rs.getInt("writer_no"));
				post.setTitle(db.rs.getString("title"));
				post.setPrice(db.rs.getInt("price"));
				post.setWriteDate(db.rs.getString("write_date"));
				post.setHits(db.rs.getInt("hits"));
				post.setImage(0, db.rs.getString("image_1"));
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
		String sql = "SELECT COUNT(*) "
				+ "FROM tranin_market k "
				+ "JOIN tranin_member m ON k.writer_no = m.`no` "
				+ "WHERE part = ? AND disabled = 'false' "
				+ "AND m.banned = 'false' " ;
		
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
	
	// part별 검색 키워드를 가진 게시글 총 개수
	public int getSearchPostCount(String part, String searchKey) {
		DBProperty db = new DBProperty();
		int count = 0;
		String sql = "SELECT COUNT(*) "
				+ "FROM tranin_market k "
				+ "JOIN tranin_member m ON k.writer_no = m.`no` "
				+ "WHERE part = ? AND title LIKE ? AND disabled = 'false' "
				+ "AND m.banned = 'false' ";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, part);
			db.pstmt.setString(2, "%" + searchKey + "%");
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
	public int insertPost(MarketDto post, String writeID) {
		DBProperty db = new DBProperty();
		System.out.println("게시글 등록 메서드 실행");
		String sql = "insert into tranin_market "
				+ "(writer_no, title, content, part, price, hits, write_date, ip, image_1, image_2, image_3, image_4, image_5) "
				+ "values ((select `no` from tranin_member where id = ?), ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?)";
		
		int ins = 0;
		int marketNo = 0;
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, writeID);
			db.pstmt.setString(2, post.getTitle());
			db.pstmt.setString(3, post.getContent());
			db.pstmt.setString(4, post.getPart());
			db.pstmt.setInt(5, post.getPrice());
			db.pstmt.setInt(6, post.getHits());
			db.pstmt.setString(7, post.getIp());
			db.pstmt.setString(8, post.getImage()[0]);
			db.pstmt.setString(9, post.getImage()[1]);
			db.pstmt.setString(10, post.getImage()[2]);
			db.pstmt.setString(11, post.getImage()[3]);
			db.pstmt.setString(12, post.getImage()[4]);
			ins = db.pstmt.executeUpdate();
			if(ins != 0) {
				sql = "SELECT market_no FROM tranin_market k "
						+ "JOIN tranin_member m ON k.writer_no = m.`no` "
						+ "WHERE m.id = ? "
						+ "ORDER BY market_no DESC "
						+ "LIMIT 0, 1";
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
				+ "WHERE market_no = ? AND disabled = 'false' ";
		
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
				marketPost.setPart(db.rs.getString("part"));
				marketPost.setPrice(db.rs.getInt("price"));
				marketPost.setWriteDate(db.rs.getString("write_date"));
				marketPost.setHits(db.rs.getInt("hits"));
				marketPost.setImage(0, db.rs.getString("image_1"));
				marketPost.setImage(1, db.rs.getString("image_2"));
				marketPost.setImage(2, db.rs.getString("image_3"));
				marketPost.setImage(3, db.rs.getString("image_4"));
				marketPost.setImage(4, db.rs.getString("image_5"));
				marketPost.setDisabled(db.rs.getString("disabled"));
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
	
	// disabled = true로 변경
	public boolean setDisabledByNo(int marketNo) {
		DBProperty db = new DBProperty();
		
		int upd = 0;
		String sql = "UPDATE tranin_market SET disabled = 'true' WHERE market_no = ?";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, marketNo);
			upd = db.pstmt.executeUpdate();
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
		return upd != 0;
	}
	
	// disabled table insert (비활성화이유, 비활성화한marketNo)
	public void insertToDisabled(String report, int marketNo) {
		DBProperty db = new DBProperty();
		
		int ins = 0;
		String sql = "INSERT INTO tranin_market_disabled "
					+ "(market_no, report, reg_date) "
					+ "values (?, ?, now())";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, marketNo);
			db.pstmt.setString(2, report);
			ins = db.pstmt.executeUpdate();
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
	}
	
	// 글 수정으로 인한 disalbed 테이블 insert
	public void insertToDisabledReportUpdate(int marketNo, int newMarketNo) {
		DBProperty db = new DBProperty();
		
		int ins = 0;
		String sql = "INSERT INTO tranin_market_disabled "
					+ "(market_no, report, new_market_no, reg_date) "
					+ "values (?, 'update', ?, now())";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setInt(1, marketNo);
			db.pstmt.setInt(2, newMarketNo);
			ins = db.pstmt.executeUpdate();
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
	}
}
