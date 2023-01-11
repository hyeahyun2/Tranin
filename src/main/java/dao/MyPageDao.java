package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ManagerDto;
import dto.MemberDto;

public class MyPageDao {
	
	DBProperty dbProperty = null;

	public void modifyMyPageInfo(String myPageMyInfoId,String myPageMyInfoPassword,
			String myPageMyInfoNickName,String myPageMyAddress,String myPageMyZipCode) {
		//UPDATE `runeah`.`tranin_member` SET `memberId`='ssss', `password`='ssss', `name`='aaaa', `nickname`='gdfd', `sex`='여성', `birthYear`='1212', `phone1`='4124124', `phone4`='412312', `address1`='ㄹㅇㅁㅇㄻㅇㄻㅇㄹ' WHERE  `no`=17;
		dbProperty = new DBProperty();
		String sql = "UPDATE tranin_member SET id='"+myPageMyInfoId+"', pw='"+myPageMyInfoPassword+"', nickname='"+myPageMyInfoNickName+"', address='"+myPageMyAddress+"', zipcode='"+myPageMyZipCode+"' WHERE id='"+myPageMyInfoId+"'";
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
		String sql = "UPDATE tranin_admin SET id='"+myPageMyInfoId+"', pw='"+myPageMyInfoPassword+"', name='"+myPageMyInfoName+"' WHERE id='"+myPageMyInfoId+"'";
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
	
	public ArrayList<MemberDto> getMemberList(){
		return null;
	}
}
