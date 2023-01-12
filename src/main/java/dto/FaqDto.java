package dto;

import java.util.Date;

public class FaqDto {
	private int faqNo;
	private String title;
	private String content;
	private Date regDate;
	
	public FaqDto() {
		super();
	}
	
	public FaqDto(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public FaqDto(int faqNo, String title, Date regDate) {
		super();
		this.faqNo = faqNo;
		this.title = title;
		this.regDate = regDate;
	}

	public FaqDto(int faqNo, String title, String content, Date regDate) {
		super();
		this.faqNo = faqNo;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
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
