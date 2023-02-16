package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;
import dto.PromotionDto;

public class PromotionDao {
	
	DBProperty dbProperty = null;

	public List<PromotionDto> getPromotionList() {
		dbProperty = new DBProperty();
		PromotionDto dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PromotionDto> list = new ArrayList<>();
		String sql = "SELECT market_no, writer_no, title, part, price, write_date, image_1 FROM tranin_market WHERE disabled='false' AND image_1 IS NOT NULL ORDER BY market_no DESC LIMIT 4";
		try {
			pstmt = dbProperty.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new PromotionDto(
						rs.getInt("market_no"),
						rs.getInt("writer_no"),
						rs.getString("title"),
						rs.getString("part"),
						rs.getInt("price"),
						rs.getString("write_date"),
						rs.getString("image_1")
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
