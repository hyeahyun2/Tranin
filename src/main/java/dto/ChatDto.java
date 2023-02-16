package dto;

/* create table tranin_chat(
	chat_no BIGINT PRIMARY key AUTO_INCREMENT,
	from_no int ,
	to_no int,
	chat_content varchar(300),
	chat_time timestamp,
	CONSTRAINT from_fk FOREIGN key(from_no) REFERENCES tranin_member(no),
	CONSTRAINT to_fk foreign key(to_no) REFERENCES tranin_member(no)
);
*/
/**
 * 채팅 내역 DTO
 * */
public class ChatDto {
	private long chatNO;
	private int fromNo;
	private int toNo;
	private String chatContent;
	private String chatTime;
	private boolean isRead;
	
	// getter setter
	public long getChatNO() {
		return chatNO;
	}
	public void setChatNO(long chatNO) {
		this.chatNO = chatNO;
	}
	public int getFromNo() {
		return fromNo;
	}
	public void setFromNo(int fromNo) {
		this.fromNo = fromNo;
	}
	public int getToNo() {
		return toNo;
	}
	public void setToNo(int toNo) {
		this.toNo = toNo;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
