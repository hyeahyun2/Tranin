package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.ChatDto;

public class ChatDao {

	// 로그인한 아이디와의 채팅 내역이 있는 사용자 목록 들고오기 (member Table -> no)
	public ArrayList<String> getOtherNickList(String myId){
		DBProperty db = new DBProperty();
		ArrayList<String> list = new ArrayList<String>();
		
		// myNo에 따른 채팅 내역 있는 아이디 목록 불러오기
		// from_id, from_nick, to_id, to_nick
		String sql = "SELECT m1.id AS from_id, m1.nickname AS from_nick, m2.id AS to_id, m2.nickname AS to_nick "
				+ "FROM tranin_chat c "
				+ "JOIN tranin_member m1 ON c.from_no = m1.`no` "
				+ "JOIN tranin_member m2 ON c.to_no = m2.`no` "
				+ "WHERE m1.id = ? OR m2.id = ? "
				+ "GROUP BY m1.id, m2.id";
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, myId);
			db.pstmt.setString(2, myId);
			db.rs = db.pstmt.executeQuery();
			while(db.rs.next()) { // 값 있으면
				// 중복값 걸러내기
				// 포함되지 않고, 로그인한 사용자가 아니면
				if(!list.contains(db.rs.getString("to_nick")) 
						&& !db.rs.getString("to_id").equals(myId)) { 
					list.add(db.rs.getString("to_nick")); // 리스트에 추가
				}
				// 포함되지 않고, 로그인한 사용자가 아니면
				if(!list.contains(db.rs.getString("from_nick"))
						&& !db.rs.getString("from_id").equals(myId)) {
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
	
	// 마지막 대화 읽음 확인 여부
	public boolean isReadChat(String myId, String otherNick) {
		DBProperty db = new DBProperty();
		boolean status = true;
		String sql = "SELECT is_read FROM tranin_chat c "
				+ "JOIN tranin_member m1 ON c.from_no = m1.`no` " // 챗 보낸 사람
				+ "JOIN tranin_member m2 ON c.to_no = m2.`no` " // 챗 받은 사람
				+ "WHERE m1.nickname = ? AND m2.id = ? " // 챗 받은 사람 = 로그인한 멤버
				+ "ORDER BY chat_time DESC " // 최신순 나열
				+ "LIMIT 1"; // 가장 마지막 채팅 내역
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, otherNick);
			db.pstmt.setString(2, myId);
			db.rs = db.pstmt.executeQuery();
			if(db.rs.next()) {
				status = db.rs.getString("is_read").equals("true") ? true : false; 
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
		
		return status;
	}
	
	// 다른 사람에게 채팅 보내기
	public int submit(String fromID, String toID, String chatContent) {
		DBProperty db = new DBProperty();
		
		int upd = -1;
		//
		String sql = "insert into tranin_chat (from_no, to_no, chat_content, chat_time) "
				+ "values ((select `no` from tranin_member where id = ?), (select `no` from tranin_member where id = ?), ?, now())";
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, fromID);
			db.pstmt.setString(2, toID);
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
	public ArrayList<ChatDto> getChatListByRecent(String myNick, String otherNick, int number){
		DBProperty db = new DBProperty();
		ArrayList<ChatDto> chatList = null;
		
		// chat_no, from_no, to_no, cht_content, chat_time
		String sql = "SELECT chat_no, from_no, to_no, chat_content, chat_time "
				+ "from tranin_chat c "
				+ "join tranin_member m1 on c.from_no = m1.`no` " // m1 : from member의 정보
				+ "join tranin_member m2 on c.to_no = m2.`no` " // m2 : to member의 정보
				+ "WHERE ((m1.nickname = ? and m2.nickname = ?) or (m1.nickname = ? and m2.nickname = ?)) " // 챗 받은사람과 보낸사람 일치
				+ "order by chat_time DESC " // 시간 역순 (최신순)
				+ "limit 0, ?"; // 불러올 리스트 수
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, myNick);
			db.pstmt.setString(2, otherNick);
			db.pstmt.setString(3, otherNick);
			db.pstmt.setString(4, myNick);
			db.pstmt.setInt(5, number);
			db.rs = db.pstmt.executeQuery();
			chatList = new ArrayList<ChatDto>();
			while(db.rs.next()) {
				// 채팅 정보 저장
				ChatDto chat = new ChatDto();
				chat.setChatNO(db.rs.getLong("chat_no"));
				chat.setFromNo(db.rs.getInt("from_no"));
				chat.setToNo(db.rs.getInt("to_no"));
				chat.setChatContent(db.rs.getString("chat_content"));
				chat.setChatTime(db.rs.getString("chat_time"));
				chatList.add(chat);
				
				// 채팅 읽음 처리
				sql = "UPDATE tranin_chat SET is_read = 'true' "
						+ "WHERE to_no = (SELECT `no` FROM tranin_member WHERE nickname = ?)"; // 받는 사람 = 로그인 멤버
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, myNick);
				db.pstmt.executeUpdate();
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
	public ArrayList<ChatDto> getChatListByNo(String myNick, String otherNick, String chatNo){
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
			db.pstmt.setString(1, myNick);
			db.pstmt.setString(2, otherNick);
			db.pstmt.setString(3, otherNick);
			db.pstmt.setString(4, myNick);
			db.pstmt.setInt(5, Integer.parseInt(chatNo));
			db.rs = db.pstmt.executeQuery();
			chatList = new ArrayList<ChatDto>();
			while(db.rs.next()) {
				// 채팅 정보 저장
				ChatDto chat = new ChatDto();
				chat.setChatNO(db.rs.getLong("chat_no"));
				chat.setFromNo(db.rs.getInt("from_no"));
				chat.setToNo(db.rs.getInt("to_no"));
				chat.setChatContent(db.rs.getString("chat_content"));
				chat.setChatTime(db.rs.getString("chat_time"));
				chatList.add(chat);
				
				// 채팅 읽음 처리
				sql = "UPDATE tranin_chat SET is_read = 'true' "
						+ "WHERE to_no = (SELECT `no` FROM tranin_member WHERE nickname = ?)"; // 받는 사람 = 로그인 멤버
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, myNick);
				db.pstmt.executeUpdate();
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
	
	// 이전 대화내역 10개 가져오기
	public ArrayList<ChatDto> getPrevChatListByfirstNo(String myNick, String otherNick, String numOfChat, int number){
		DBProperty db = new DBProperty();
		ArrayList<ChatDto> chatList = null;
		
		// chat_no, from_no, to_no, cht_content, chat_time
		String sql = "SELECT chat_no, from_no, to_no, chat_content, chat_time "
				+ "from tranin_chat c "
				+ "join tranin_member m1 on c.from_no = m1.`no` " // m1 : from member의 정보
				+ "join tranin_member m2 on c.to_no = m2.`no` " // m2 : to member의 정보
				+ "WHERE ((m1.nickname = ? and m2.nickname = ?) or (m1.nickname = ? and m2.nickname = ?)) " // 챗 받은사람과 보낸사람 일치
				+ "order by chat_time DESC " // 시간 역순 (최신순)
				+ "limit ?, ?"; // 불러올 리스트 수
		
		try {
			db.pstmt = db.conn.prepareStatement(sql);
			db.pstmt.setString(1, myNick);
			db.pstmt.setString(2, otherNick);
			db.pstmt.setString(3, otherNick);
			db.pstmt.setString(4, myNick);
			db.pstmt.setInt(5, Integer.parseInt(numOfChat));
			db.pstmt.setInt(6, number);
			db.rs = db.pstmt.executeQuery();
			chatList = new ArrayList<ChatDto>();
			while(db.rs.next()) {
				// 채팅 정보 저장
				ChatDto chat = new ChatDto();
				chat.setChatNO(db.rs.getLong("chat_no"));
				chat.setFromNo(db.rs.getInt("from_no"));
				chat.setToNo(db.rs.getInt("to_no"));
				chat.setChatContent(db.rs.getString("chat_content"));
				chat.setChatTime(db.rs.getString("chat_time"));
				chatList.add(chat);
				
				// 채팅 읽음 처리
				sql = "UPDATE tranin_chat SET is_read = 'true' "
						+ "WHERE to_no = (SELECT `no` FROM tranin_member WHERE nickname = ?)"; // 받는 사람 = 로그인 멤버
				db.pstmt = db.conn.prepareStatement(sql);
				db.pstmt.setString(1, myNick);
				db.pstmt.executeUpdate();
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
