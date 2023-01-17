package dto;

import java.util.Date;

public class FaqDto {
	private int faqNo;
	private String title;
	private String content;
		
	public FaqDto() {
		super();
	}

	public FaqDto(int faqNo, String title, String content) {
		super();
		this.faqNo = faqNo;
		this.title = title;
		this.content = content;
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
	
}
