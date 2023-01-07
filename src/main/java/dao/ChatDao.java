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
		String sql = "SELECT c.from_no, m1.nickname AS from_nick, c.to_no, m2.nickname AS to_nick "
				+ "FROM tranin_chat c "
				+ "JOIN tranin_member m1 ON c.from_no = m1.`no` "
				+ "JOIN tranin_member m2 ON c.to_no = m2.`no` "
				+ "WHERE m1.nickname = ? OR m2.nickname = ? "
				+ "GROUP BY from_nick, to_nick";
		
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
		
		// chat_no, from_no, from_nick, to_no, to_nick, cht_content, chat_time
		String sql = "SELECT chat_no, from_no, m1.nickname AS from_nick, to_no, m2.nickname AS to_nick, chat_content, chat_time "
				+ "from tranin_chat c "
				+ "join tranin_member m1 on c.from_no = m1.`no` " // m1 : from member의 정보
				+ "join tranin_member m2 on c.to_no = m2.`no` " // m2 : to member의 정보
				+ "WHERE ((m1.nickname = ? and m2.nickname = ?) or (m1.nickname = ? and m2.nickname = ?)) " // 챗 받은사람과 보낸사람 일치
				+ "order by chat_time DESC " // 시간 역순 (최신순)
				+ "limit ?"; // 불러올 리스트 수
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, fromNick);
			db.pstmt.setString(2, toNick);
			db.pstmt.setString(3, toNick);
			db.pstmt.setString(4, fromNick);
			db.pstmt.setInt(5, number);
			db.rs = db.pstmt.executeQuery();
			chatList = new ArrayList<ChatDto>();
			while(db.rs.next()) {
				ChatDto chat = new ChatDto();
				chat.setChatNO(db.rs.getLong("chat_no"));
				chat.setFromNo(db.rs.getInt("from_no"));
				chat.setToNo(db.rs.getInt("to_no"));
				chat.setChatContent(db.rs.getString("chat_content"));
				chat.setChatTime(db.rs.getString("chat_time"));
				chatList.add(chat);
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
		return chatList;
	}
	
	// 새로운 채팅 내역 가져오기
	public ArrayList<ChatDto> getChatListByNo(String fromNick, String toNick, String chatNo){
		DBProperty db = new DBProperty();
		ArrayList<ChatDto> chatList = null;
		
		// chat_no, from_no, from_nick, to_no, to_nick, cht_content, chat_time
		String sql = "SELECT chat_no, from_no, m1.nickname AS from_nick, to_no, m2.nickname AS to_nick, chat_content, chat_time "
				+ "FROM tranin_chat c "
				+ "JOIN tranin_member m1 ON c.from_no = m1.`no` "
				+ "JOIN tranin_member m2 ON c.to_no = m2.`no` "
				+ "WHERE ((m1.nickname = ? AND m2.nickname = ?) OR (m1.nickname = ? AND m2.nickname = ?)) "
					+ "AND chat_no > ? "
				+ "ORDER BY chat_time DESC";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, fromNick);
			db.pstmt.setString(2, toNick);
			db.pstmt.setString(3, toNick);
			db.pstmt.setString(4, fromNick);
			db.pstmt.setInt(5, Integer.parseInt(chatNo));
			db.rs = db.pstmt.executeQuery();
			chatList = new ArrayList<ChatDto>();
			while(db.rs.next()) {
				ChatDto chat = new ChatDto();
				chat.setChatNO(db.rs.getLong("chat_no"));
				chat.setFromNo(db.rs.getInt("from_no"));
				chat.setToNo(db.rs.getInt("to_no"));
				chat.setChatContent(db.rs.getString("chat_content"));
				chat.setChatTime(db.rs.getString("chat_time"));
				chatList.add(chat);
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
		return chatList;
	}
}
