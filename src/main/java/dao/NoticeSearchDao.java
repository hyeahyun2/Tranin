package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.NoticeDto;

public class NoticeSearchDao {
	
	private Statement stmt = null;
	
	public ArrayList<NoticeDto> getSearch(String text) {
		DBProperty db = new DBProperty();
		ArrayList<NoticeDto> list = new ArrayList<NoticeDto>(); 
		try {
			String sql = "SELECT * FROM notice_bd WHERE title LIKE '%" + text + "%'";
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
