package dto;

/* create table tranin_chat(
	chat_no int PRIMARY key,
	from_no int ,
	to_no int,
	chat_content varchar(300),
	chat_time timestamp,
	CONSTRAINT from_fk FOREIGN key(from_no) REFERENCES tranin_member(no),
	CONSTRAINT to_fk foreign key(to_no) REFERENCES tranin_member(no)
	);
*/
public class ChatDTO {
	int chatID;
	String fromID;
	String toID;
	String chatContent;
	String chatTime;
	
	public int getChatID() {
		return chatID;
	}
	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getToID() {
		return toID;
	}
	public void setToID(String toID) {
		this.toID = toID;
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
