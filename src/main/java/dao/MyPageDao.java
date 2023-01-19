package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Cryptoutils.Sha;
import dto.ManagerDto;
import dto.MemberDto;
import dto.NoticeDto;

public class MyPageDao {
	
	DBProperty dbProperty = null;

	public void modifyMyPageInfo(String myPageMyInfoId,String myPageMyInfoPassword,
			String myPageMyInfoNickName,String myPageMyAddress,String myPageMyZipCode) {
		//UPDATE `runeah`.`tranin_member` SET `memberId`='ssss', `password`='ssss', `name`='aaaa', `nickname`='gdfd', `sex`='여성', `birthYear`='1212', `phone1`='4124124', `phone4`='412312', `address1`='ㄹㅇㅁㅇㄻㅇㄻㅇㄹ' WHERE  `no`=17;
		dbProperty = new DBProperty();
		Sha sha = new Sha();
		String myPageMyInfoPasswordNew = sha.encode(myPageMyInfoPassword);
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
		Sha sha = new Sha();
		String myPageMyInfoPasswordNew = sha.encode(myPageMyInfoPassword);
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

	public MemberDto getMemberById(String id) {
		dbProperty = new DBProperty();
		MemberDto dto = new MemberDto();
		System.out.println("매개변수:"+id);
		try {
			String sql = "SELECT * FROM tranin_member where id=?";
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.setString(1, id);
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
		
		dbProperty = new DBProperty();
		String sql1 = "DELETE FROM tranin_banned_member WHERE member_no="+no;
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
			}
		}
		
		dbProperty = new DBProperty();
		int flag = 0;
		System.out.println(chkdId);
		String sql = "DELETE FROM tranin_banned_member where member_no IN (" + chkdId + ")";
		try {
			statement = dbProperty.conn.createStatement();
			flag= statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag!=0;
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
		String sql = "DELETE FROM tranin_banned_member";
		try {
			dbProperty = new DBProperty();
			pstmt = dbProperty.conn.prepareStatement(sql);
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

	
}
