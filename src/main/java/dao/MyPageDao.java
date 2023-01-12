package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		//1q2w3e4r!Q,!
		//ef3e1dd6a8ae58c96de9451c49a0efd4520b569b4d1d95a94e1b98626834d99d
		//9751c10787a9bfd6577bc77929cfd342804c476759dc4fba01a9e4d4551e7644
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
			dto = new ManagerDto(rs.getString(1),rs.getString(2),rs.getString(3));
			
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
	
	
	
	public ArrayList<MemberDto> getMemberList(int pageNum){
		dbProperty = new DBProperty();
		MemberDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> list = new ArrayList<>();
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_member ORDER BY no DESC LIMIT ?, ?";
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

	
}
