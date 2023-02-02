package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.member.PasswdEncry;
import dto.ManagerDto;
import dto.MarketDto;
import dto.MemberDto;
import dto.NoticeDto;

public class MyPageDao {
	
	DBProperty dbProperty = null;

	public void modifyMyPageInfo(String myPageMyInfoId,String myPageMyInfoPassword,
			String myPageMyInfoNickName,String myPageMyAddress,String myPageMyZipCode) {
		//UPDATE `runeah`.`tranin_member` SET `memberId`='ssss', `password`='ssss', `name`='aaaa', `nickname`='gdfd', `sex`='여성', `birthYear`='1212', `phone1`='4124124', `phone4`='412312', `address1`='ㄹㅇㅁㅇㄻㅇㄻㅇㄹ' WHERE  `no`=17;
		dbProperty = new DBProperty();
		// 객체 생성
   	    PasswdEncry pwEn = new PasswdEncry();
		String myPageMyInfoPasswordNew = pwEn.getEncry(myPageMyInfoPassword, "testSalt");
		System.out.println(myPageMyInfoPassword);
		System.out.println(myPageMyInfoPasswordNew);
		String sql = "UPDATE tranin_member SET pw='"+myPageMyInfoPasswordNew+"', address='"+myPageMyAddress+"', zipcode='"+myPageMyZipCode+"' WHERE id='"+myPageMyInfoId+"'";
		try {
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.executeUpdate();
			System.out.println("수정완료");
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifyManagerInfo(String myPageMyInfoId,String myPageMyInfoPassword,
			String myPageMyInfoName) {
		//UPDATE `runeah`.`tranin_member` SET `memberId`='ssss', `password`='ssss', `name`='aaaa', `nickname`='gdfd', `sex`='여성', `birthYear`='1212', `phone1`='4124124', `phone4`='412312', `address1`='ㄹㅇㅁㅇㄻㅇㄻㅇㄹ' WHERE  `no`=17;
		dbProperty = new DBProperty();
		// 객체 생성
   	    PasswdEncry pwEn = new PasswdEncry();
		String myPageMyInfoPasswordNew = pwEn.getEncry(myPageMyInfoPassword,"testSalt");
		String sql = "UPDATE tranin_admin SET id='"+myPageMyInfoId+"', pw='"+myPageMyInfoPasswordNew+"', name='"+myPageMyInfoName+"' WHERE id='"+myPageMyInfoId+"'";
		try {
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.executeUpdate();
			System.out.println("수정완료");
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMember(String id) {
		dbProperty = new DBProperty();
		String sql = "DELETE FROM tranin_member WHERE id='"+id+"'";
		try {
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.executeUpdate();
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMemberByNo(int no) {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql2 = "insert into tranin_banned_member(member_no,report) values(?,?)";
		try {
			dbProperty = new DBProperty();
			pstmt = dbProperty.conn.prepareStatement(sql2);
			pstmt.setInt(1, no);
			pstmt.setString(2, "from_admin_oneban");
			pstmt.executeUpdate();
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbProperty = new DBProperty();
		String sql1 = "UPDATE tranin_member SET banned='true' WHERE no="+no;
		try {
			pstmt = dbProperty.conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public int getMemberNoById(String id) {
		dbProperty = new DBProperty();
		MemberDto dto = new MemberDto();
		System.out.println("매개변수:"+id);
		try {
			String sql = "SELECT no FROM tranin_member where id=?";
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.setString(1, id);
			ResultSet rs = dbProperty.pstmt.executeQuery();
			rs.first();
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public MemberDto getMemberById(String id) {
		dbProperty = new DBProperty();
		MemberDto dto = new MemberDto();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tranin_member where id=?";
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.first();
			dto = new MemberDto(rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemberDto getMemberByNo(int no) {
		dbProperty = new DBProperty();
		MemberDto dto = new MemberDto();
		System.out.println("매개변수:"+no);
		try {
			String sql = "SELECT * FROM tranin_member where no=?";
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.setInt(1, no);
			ResultSet rs = dbProperty.pstmt.executeQuery();
			//System.out.println("내용:"+dbProperty.pstmt.toString());
			rs.first();
			dto = new MemberDto(rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ManagerDto getManagerById(String id) {
		dbProperty = new DBProperty();
		ManagerDto dto = new ManagerDto();
		System.out.println("매개변수:"+id);
		try {
			String sql = "SELECT * FROM tranin_admin where id=?";
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.setString(1, id);
			ResultSet rs = dbProperty.pstmt.executeQuery();
			//System.out.println("내용:"+dbProperty.pstmt.toString());
			rs.first();
			dto = new ManagerDto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getBoolean(4));
			
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void deleteManager(String id) {
		dbProperty = new DBProperty();
		String sql = "DELETE FROM tranin_admin WHERE id='"+id+"'";
		try {
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.executeUpdate();
			if(dbProperty.rs!=null)
				dbProperty.rs.close();
			if(dbProperty.pstmt != null)
				dbProperty.pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//멤버 메니저 시리즈
	public ResultSet getAllMemberList() throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_member WHERE banned='false'";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getAllSearchedMemberList(String select,String keyword) throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_member WHERE banned='false' AND "+select+" LIKE CONCAT('%',?,'%')";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getAllBannedMemberList() throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_member WHERE banned='true'";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getAllSearchedBannedMemberList(String select,String keyword) throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_member WHERE banned='true' AND "+select+" LIKE CONCAT('%',?,'%')";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ArrayList<MemberDto> getMemberListNoPaging(){
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
		// 페이징 처리
    	String sql = "SELECT * FROM tranin_member WHERE banned='false' ORDER BY no DESC";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
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
	
	public ArrayList<MemberDto> getMemberList(int pageNum){
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_member WHERE banned='false' ORDER BY no DESC LIMIT ?, ?";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, cntListPerPage);
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
	
	public ArrayList<MemberDto> getBannedMemberList(int pageNum){
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_member WHERE banned='true' ORDER BY no DESC LIMIT ?, ?";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, cntListPerPage);
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
	
	private Statement statement = null;
	public boolean deleteSelMember(String chkdId) {
		String[] strArray = chkdId.split(",");
		for(int i=0;i<strArray.length;i++) {
			dbProperty = new DBProperty();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql2 = "insert into tranin_banned_member(member_no,report) values(?,?)";
			try {
				dbProperty = new DBProperty();
				pstmt = dbProperty.conn.prepareStatement(sql2);
				pstmt.setInt(1, Integer.parseInt(strArray[i]));
				pstmt.setString(2, "from_admin_selban");
				pstmt.executeUpdate();
				if(rs!=null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(dbProperty.conn!=null)
					dbProperty.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		dbProperty = new DBProperty();
		int flag = 0;
		System.out.println(chkdId);
		String sql = "UPDATE tranin_member SET banned='true' WHERE no IN (" + chkdId + ")";
		try {
			statement = dbProperty.conn.createStatement();
			flag= statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag!=0;
	}

	public void restoreMemberByNo(int no) {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql2 = "UPDATE tranin_member SET banned='false' WHERE no="+no;;
		try {
			dbProperty = new DBProperty();
			pstmt = dbProperty.conn.prepareStatement(sql2);
			pstmt.executeUpdate();
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean restoreSelMember(String chkdId) {
		String[] strArray = chkdId.split(",");
		for(int i=0;i<strArray.length;i++) {
			dbProperty = new DBProperty();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql2 = "UPDATE tranin_member SET banned='false' WHERE no IN (" + chkdId + ")";
			try {
				dbProperty = new DBProperty();
				pstmt = dbProperty.conn.prepareStatement(sql2);
				pstmt.executeUpdate();
				if(rs!=null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(dbProperty.conn!=null)
					dbProperty.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public void deleteAllMember() {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int memberNo = 0;
		String str = ",("+memberNo+",'from_admin_allban')";
		String sql = "INSERT INTO tranin_banned_member(member_no,report) values(?,'from_admin_allban')";
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		ArrayList<Integer> memberNoList = new ArrayList<Integer>();
		list=getMemberListNoPaging();
		for(int i=1;i<list.size();i++) {
			memberNo = list.get(i).getNo();
			sql.concat(str);
		}
		
		try {
			dbProperty = new DBProperty();
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, list.get(0).getNo());
			pstmt.executeUpdate();
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbProperty = new DBProperty();
		String sql1 = "UPDATE tranin_member SET banned='true'";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void restoreAllMember() {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "UPDATE tranin_member SET banned='false'";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			if(rs!=null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(dbProperty.conn!=null)
				dbProperty.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MemberDto> searchMember(String parameter, String parameter2, int pageNum) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_member WHERE banned='false' AND "+parameter+" LIKE CONCAT('%',?,'%') ORDER BY no DESC LIMIT ?, ?";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, parameter2);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, cntListPerPage);
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

	public ArrayList<MemberDto> searchBannedMember(String parameter, String parameter2, int pageNum) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_member WHERE banned='true' AND "+parameter+" LIKE CONCAT('%',?,'%') ORDER BY no DESC LIMIT ?, ?";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, parameter2);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, cntListPerPage);
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

	// 글 목록 loadNum만큼 불러오기
	public ArrayList<MarketDto> getPostList(String part, int clickNum, int loadNum, int no){
		DBProperty db = new DBProperty();
		ArrayList<MarketDto> postList = new ArrayList<MarketDto>();

		String sql = "SELECT market_no, writer_no, title, price, write_date, hits, image_1 "
				+ "FROM tranin_market "
				+ "WHERE part = ? AND disabled = 'false' AND writer_no = ? "
				+ "ORDER BY write_date DESC "
				+ "LIMIT ?, ?";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, part);
			db.pstmt.setInt(2, no);
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
	public int getPostCount(String part,int no) {
		DBProperty db = new DBProperty();
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market "
				+ "WHERE part = ? AND writer_no = ? AND disabled = 'false'";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, part);
			db.pstmt.setInt(2, no);
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

	public String getDayRegMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_member WHERE banned='false' AND reg_date >= DATE_ADD(current_timestamp, INTERVAL -1 day)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getWeekRegMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_member WHERE banned='false' AND reg_date >= DATE_ADD(current_timestamp, INTERVAL -7 day)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getMonthRegMemberCount() {
DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_member WHERE banned='false' AND reg_date >= DATE_ADD(current_timestamp, INTERVAL -30 day)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getAllRegMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_member WHERE banned='false'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String[] getDayPopPost() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String no = "";
		String count = "";
		String sql = "SELECT market_no, title FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -1 DAY) ORDER BY hits DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = String.valueOf(rs.getInt(1));
				count = rs.getString(2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		String[] str = {no,count};
		return str;
	}

	public String[] getWeekPopPost() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String no = "";
		String count = "";
		String sql = "SELECT market_no, title FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -7 DAY) ORDER BY hits DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = String.valueOf(rs.getInt(1));
				count = rs.getString(2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		String[] str = {no,count};
		return str;
	}

	public String[] getMonthPopPost() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String no = "";
		String count = "";
		String sql = "SELECT market_no, title FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -30 DAY) ORDER BY hits DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = String.valueOf(rs.getInt(1));
				count = rs.getString(2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		String[] str = {no,count};
		return str;
	}

	public String[] getAllPopPost() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String no = "";
		String count = "";
		String sql = "SELECT market_no, title FROM tranin_market ORDER BY hits DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = String.valueOf(rs.getInt(1));
				count = rs.getString(2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		String[] str = {no,count};
		return str;
	}

	public String getDayMostUser() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String count = "";
		String sql = "SELECT writer_no AS count FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -1 DAY) GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(count=="") {
			return "없음";
		}else {
			return getMemberByNo(Integer.parseInt(count)).getId();
		}
	}

	public String getWeekMostUser() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String count = "";
		String sql = "SELECT writer_no AS count FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -7 DAY) GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(count=="") {
			return "없음";
		}else {
			return getMemberByNo(Integer.parseInt(count)).getId();
		}
	}

	public String getMonthMostUser() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String count = "";
		String sql = "SELECT writer_no AS count FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -30 DAY) GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(count=="") {
			return "없음";
		}else {
			return getMemberByNo(Integer.parseInt(count)).getId();
		}
	}

	public String getAllMostUser() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String count = "";
		String sql = "SELECT writer_no AS count FROM tranin_market GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(count=="") {
			return "없음";
		}else {
			return getMemberByNo(Integer.parseInt(count)).getId();
		}
	}

	public String getDayMostUserCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) AS count FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -1 DAY) GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getWeekMostUserCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) AS count FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -7 DAY) GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getMonthMostUserCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) AS count FROM tranin_market WHERE write_date >= DATE_ADD(current_timestamp, INTERVAL -30 DAY) GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getAllMostUserCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) AS count FROM tranin_market GROUP BY writer_no ORDER BY COUNT(*) DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getDayPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market WHERE disabled='false' AND write_date >= DATE_ADD(current_timestamp, INTERVAL -1 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getWeekPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market WHERE disabled='false' AND write_date >= DATE_ADD(current_timestamp, INTERVAL -7 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getMonthPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market WHERE disabled='false' AND write_date >= DATE_ADD(current_timestamp, INTERVAL -30 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getAllPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market WHERE disabled='false'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getDayBanPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market_disabled WHERE reg_date >= DATE_ADD(current_timestamp, INTERVAL -1 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getWeekBanPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market_disabled WHERE reg_date >= DATE_ADD(current_timestamp, INTERVAL -7 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getMonthBanPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market_disabled WHERE reg_date >= DATE_ADD(current_timestamp, INTERVAL -30 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getAllBanPostCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_market_disabled";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getDayBanMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_banned_member WHERE reg_date >= DATE_ADD(current_timestamp, INTERVAL -1 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getWeekBanMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_banned_member WHERE reg_date >= DATE_ADD(current_timestamp, INTERVAL -7 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getMonthBanMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_banned_member WHERE reg_date >= DATE_ADD(current_timestamp, INTERVAL -30 DAY)";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}

	public String getAllBanMemberCount() {
		DBProperty db = new DBProperty();
		
		Connection conn = db.conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "SELECT COUNT(*) FROM tranin_banned_member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(count);
	}
}
