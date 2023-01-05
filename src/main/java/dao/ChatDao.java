package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.ChatDto;

public class ChatDao {

	// 로그인한 아이디와의 채팅 내역이 있는 사용자 목록 들고오기 (member Table -> no)
	public ArrayList<String> getOtherNickList(String myNick){
		DBProperty db = new DBProperty();
		ArrayList<String> list = new ArrayList<String>();
		
		// myNo에 따른 채팅 내역 있는 아이디 목록 불러오기
		/*
		String sql = "SELECT to_no, from_no FROM tranin_chat "
				+ "GROUP BY from_no, to_no "
				+ "HAVING to_no = ? OR from_no = ?";
		 * */
		// from_no, from_nick, to_no, to_nick
		String sql = "select from_no, "
				+ "(SELECT nickname from tranin_member where `no`= c.from_no) as from_nick "
				+ ", to_no, (SELECT nickname from tranin_member where `no`= c.to_no) as to_nick "
				+ "from tranin_chat c "
				+ "where c.from_no = (SELECT `no` from tranin_member where nickname = ?) or "
				+ "c.to_no = (SELECT `no` from tranin_member where nickname = ?)";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, myNick);
			db.pstmt.setString(2, myNick);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) { // 값 있으면
				// 중복값 걸러내기
				// 포함되지 않고, 로그인한 사용자가 아니면
				if(!list.contains(db.rs.getString("to_nick")) 
						&& !db.rs.getString("to_nick").equals(myNick)) { 
					list.add(db.rs.getString("to_nick")); // 리스트에 추가
				}
				// 포함되지 않고, 로그인한 사용자가 아니면
				if(!list.contains(db.rs.getString("from_nick"))
						&& !db.rs.getString("from_nick").equals(myNick)) {
					list.add(db.rs.getString("from_nick")); // 리스트에 추가
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
		
		return list;
	}
	
	// 다른 사람에게 채팅 보내기
	public int submit(String fromNick, String toNick, String chatContent) {
		DBProperty db = new DBProperty();
		
		int upd = -1;
		String sql = "insert into tranin_chat (from_no, to_no, chat_content, chat_time) "
				+ "values ((select `no` from tranin_member where nickname = ?), (select `no` from tranin_member where nickname = ?), ?, now())";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, fromNick);
			db.pstmt.setString(2, toNick);
			db.pstmt.setString(3, chatContent);
			upd = db.pstmt.executeUpdate(); // 성공시 : 1
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
		return upd;
	}
	
	// 대화내역 최근 10개 가져오기 (채팅창 열었을 때 1번 실행)
	public ArrayList<ChatDto> getChatListByRecent(String fromNick, String toNick, int number){
		DBProperty db = new DBProperty();
		ArrayList<ChatDto> chatList = null;
		
		String sql = "select * from chat where "
				+ "((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) "
				+ "AND chatID > (select Max(chatID) - ? from chat) order by chatTime";
		
		try {
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
		return chatList;
	}
}
