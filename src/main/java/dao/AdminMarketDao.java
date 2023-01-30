package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.MarketDto;
import dto.MarketResponseDto;
import dto.MarketDto;
import dto.MarketDto;

public class AdminMarketDao {
	DBProperty dbProperty = null;
	public void deleteMarketByNo(int marketNo) {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql2 = "insert into tranin_market_disabled(market_no,report) values(?,?)";
		try {
			dbProperty = new DBProperty();
			pstmt = dbProperty.conn.prepareStatement(sql2);
			pstmt.setInt(1, marketNo);
			pstmt.setString(2, "remove_admin");
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
		String sql1 = "UPDATE tranin_market SET disabled='true' WHERE market_no="+marketNo;
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

	public MarketDto getMarketById(String id) {
		dbProperty = new DBProperty();
		MarketDto dto = new MarketDto();
		System.out.println("매개변수:"+id);
		try {
			String sql = "SELECT * FROM tranin_member where id=?";
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.setString(1, id);
			ResultSet rs = dbProperty.pstmt.executeQuery();
			//System.out.println("내용:"+dbProperty.pstmt.toString());
			rs.first();
			/*
			dto = new MarketDto(rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			*/
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
	
	public MarketDto getMarketByNo(int no) {
		dbProperty = new DBProperty();
		MarketDto dto = new MarketDto();
		System.out.println("매개변수:"+no);
		try {
			String sql = "SELECT * FROM tranin_member where no=?";
			dbProperty.pstmt = dbProperty.conn.prepareStatement(sql);
			dbProperty.pstmt.setInt(1, no);
			ResultSet rs = dbProperty.pstmt.executeQuery();
			//System.out.println("내용:"+dbProperty.pstmt.toString());
			rs.first();
			/*
			dto = new MarketDto(rs.getString(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			*/
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
	
	public ResultSet getAllMarketList() throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_market WHERE disabled='false'";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getAllSearchedMarketList(String select,String keyword) throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_market WHERE disabled='false' AND "+select+" LIKE CONCAT('%',?,'%')";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getAllBannedMarketList() throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_market WHERE disabled='true'";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet getAllSearchedBannedMarketList(String select,String keyword) throws SQLException {
		dbProperty = new DBProperty();
		String sql = "SELECT count(*) FROM tranin_market WHERE disabled='true' AND "+select+" LIKE CONCAT('%',?,'%')";
		PreparedStatement pstmt = dbProperty.conn.prepareStatement(sql);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public ArrayList<MarketDto> getMarketListNoPaging(){
		dbProperty = new DBProperty();
		MarketDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketDto> list = new ArrayList<>();
		
		// 페이징 처리
    	String sql = "SELECT * FROM tranin_market WHERE disabled='false' ORDER BY no DESC";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//dto = new MarketDto(rs.getInt("no"), rs.getString("id"), rs.getString("nickname"),rs.getString("address"),rs.getString("zipcode"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MarketDto> getMarketList(int pageNum){
		dbProperty = new DBProperty();
		MarketDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_market WHERE disabled='false' ORDER BY market_no DESC LIMIT ?, ?";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, cntListPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MarketDto();
				dto.setMarketNo(rs.getInt("market_no"));
				dto.setWriterNo(rs.getInt("writer_no"));
				dto.setTitle(rs.getString("title"));
				
				dto.setContent(rs.getString("content"));
				dto.setPart(rs.getString("part"));
				dto.setPrice(rs.getInt("price"));
				dto.setWriteDate(rs.getString("write_date"));
				dto.setHits(rs.getInt("hits"));
				dto.setIp(rs.getString("ip"));
				dto.setImage(0, rs.getString("image_1"));
				dto.setImage(1, rs.getString("image_2"));
				dto.setImage(2, rs.getString("image_3"));
				dto.setImage(3, rs.getString("image_4"));
				dto.setImage(4, rs.getString("image_5"));
				dto.setDisabled(rs.getString("disabled"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MarketResponseDto> getBannedMarketList(int pageNum){
		dbProperty = new DBProperty();
		MarketResponseDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketResponseDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT DISTINCT tranin_market.*, tranin_market_disabled.report FROM tranin_market RIGHT JOIN tranin_market_disabled ON tranin_market.market_no = tranin_market_disabled.market_no WHERE tranin_market.disabled='true' ORDER BY market_no DESC LIMIT ?, ?";
    	
    	try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, cntListPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MarketResponseDto();
				dto.setMarketNo(rs.getInt("market_no"));
				dto.setWriterNo(rs.getInt("writer_no"));
				dto.setTitle(rs.getString("title"));
				
				dto.setContent(rs.getString("content"));
				dto.setPart(rs.getString("part"));
				dto.setPrice(rs.getInt("price"));
				dto.setWriteDate(rs.getString("write_date"));
				dto.setHits(rs.getInt("hits"));
				dto.setIp(rs.getString("ip"));
				dto.setImage(0, rs.getString("image_1"));
				dto.setImage(1, rs.getString("image_2"));
				dto.setImage(2, rs.getString("image_3"));
				dto.setImage(3, rs.getString("image_4"));
				dto.setImage(4, rs.getString("image_5"));
				dto.setDisabled(rs.getString("disabled"));
				dto.setReport(rs.getString("report"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Statement statement = null;
	public boolean deleteSelMarket(String chkdId) {
		String[] strArray = chkdId.split(",");
		for(int i=0;i<strArray.length;i++) {
			dbProperty = new DBProperty();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql2 = "insert into tranin_market_disabled(market_no,report) values(?,?)";
			try {
				dbProperty = new DBProperty();
				pstmt = dbProperty.conn.prepareStatement(sql2);
				pstmt.setInt(1, Integer.parseInt(strArray[i]));
				pstmt.setString(2, "remove_admin");
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
		String sql = "UPDATE tranin_market SET disabled='true' WHERE market_no IN (" + chkdId + ")";
		try {
			statement = dbProperty.conn.createStatement();
			flag= statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag!=0;
	}

	public void restoreMarketByNo(int no) {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql2 = "UPDATE tranin_market SET disabled='false' WHERE market_no="+no;;
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
		String sql1 = "INSERT INTO tranin_market_disabled(market_no,report) values(?,?)";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql1);
			pstmt.setInt(1, no);
			pstmt.setString(2, "restore_admin");
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

	public boolean restoreSelMarket(String chkdId) {
		String[] strArray = chkdId.split(",");
		PreparedStatement pstmt = null;
		int flag = 0;
		for(int i=0;i<strArray.length;i++) {
			dbProperty = new DBProperty();
			ResultSet rs = null;
			String sql2 = "UPDATE tranin_market SET disabled='false' WHERE market_no IN (" + chkdId + ")";
			try {
				dbProperty = new DBProperty();
				pstmt = dbProperty.conn.prepareStatement(sql2);
				flag = pstmt.executeUpdate();
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
		return flag!=0;
	}

	public void deleteAllMarket() {
		dbProperty = new DBProperty();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int marketNo = 0;
		String str = ",("+marketNo+",'remove_admin')";
		String sql = "INSERT INTO tranin_market_disabled(market_no,report) values(?,'remove_admin')";
		ArrayList<MarketDto> list = new ArrayList<MarketDto>();
		ArrayList<Integer> marketNoList = new ArrayList<Integer>();
		list=getMarketListNoPaging();
		for(int i=1;i<list.size();i++) {
			marketNo = list.get(i).getMarketNo();
			sql.concat(str);
		}
		
		try {
			dbProperty = new DBProperty();
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setInt(1, list.get(0).getMarketNo());
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
		String sql1 = "UPDATE tranin_market SET disabled='true'";
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
	
	public void restoreAllMarket() {
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
	
	public ArrayList<MarketDto> searchMarket(String parameter, String parameter2, int pageNum) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MarketDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT * FROM tranin_market WHERE disabled='false' AND "+parameter+" LIKE CONCAT('%',?,'%') ORDER BY market_no DESC LIMIT ?, ?";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, parameter2);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, cntListPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MarketDto();
				dto.setMarketNo(rs.getInt("market_no"));
				dto.setWriterNo(rs.getInt("writer_no"));
				dto.setTitle(rs.getString("title"));
				
				dto.setContent(rs.getString("content"));
				dto.setPart(rs.getString("part"));
				dto.setPrice(rs.getInt("price"));
				dto.setWriteDate(rs.getString("write_date"));
				dto.setHits(rs.getInt("hits"));
				dto.setIp(rs.getString("ip"));
				dto.setImage(0, rs.getString("image_1"));
				dto.setImage(1, rs.getString("image_2"));
				dto.setImage(2, rs.getString("image_3"));
				dto.setImage(3, rs.getString("image_4"));
				dto.setImage(4, rs.getString("image_5"));
				dto.setDisabled(rs.getString("disabled"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<MarketResponseDto> searchBannedMarket(String parameter, String parameter2, int pageNum) {
		System.out.println("param: "+parameter);
		System.out.println("param2: "+parameter2);
		dbProperty = new DBProperty();
		MarketResponseDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MarketResponseDto> list = new ArrayList<>();
		
		// 페이징 처리
    	int cntListPerPage = 10;
    	int startNum = (pageNum - 1) * cntListPerPage; 
    	String sql = "SELECT DISTINCT tranin_market.*, tranin_market_disabled.report FROM tranin_market RIGHT JOIN tranin_market_disabled ON tranin_market.market_no = tranin_market_disabled.market_no WHERE tranin_market.disabled='true' AND tranin_market."+parameter+" LIKE CONCAT('%',?,'%') ORDER BY market_no DESC LIMIT ?, ?";
    	try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			pstmt.setString(1, parameter2);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, cntListPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MarketResponseDto();
				dto.setMarketNo(rs.getInt("market_no"));
				dto.setWriterNo(rs.getInt("writer_no"));
				dto.setTitle(rs.getString("title"));
				
				dto.setContent(rs.getString("content"));
				dto.setPart(rs.getString("part"));
				dto.setPrice(rs.getInt("price"));
				dto.setWriteDate(rs.getString("write_date"));
				dto.setHits(rs.getInt("hits"));
				dto.setIp(rs.getString("ip"));
				dto.setImage(0, rs.getString("image_1"));
				dto.setImage(1, rs.getString("image_2"));
				dto.setImage(2, rs.getString("image_3"));
				dto.setImage(3, rs.getString("image_4"));
				dto.setImage(4, rs.getString("image_5"));
				dto.setDisabled(rs.getString("disabled"));
				dto.setReport(rs.getString("report"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
