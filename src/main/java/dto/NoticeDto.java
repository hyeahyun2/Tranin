package dto;

import java.util.Date;

public class NoticeDto {
	private int noticeNo;
	private String title;
	private String content;
	private Date regDate;
	
	public NoticeDto() {
		super();
	}

	public NoticeDto(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	

	public NoticeDto(int noticeNo, String title, Date regDate) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
		this.regDate = regDate;
	}
	
	public NoticeDto(int noticeNo, String title, String content, Date regDate) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
