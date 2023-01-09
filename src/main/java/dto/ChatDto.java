package dto;

/* create table tranin_chat(
	chat_no bigint PRIMARY key,
	from_no int ,
	to_no int,
	chat_content varchar(300),
	chat_time timestamp,
	CONSTRAINT from_fk FOREIGN key(from_no) REFERENCES tranin_member(no),
	CONSTRAINT to_fk foreign key(to_no) REFERENCES tranin_member(no)
	);
*/
public class ChatDto {
	long chatNO;
	int fromNo;
	int toNo;
	String chatContent;
	String chatTime;
	
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
}