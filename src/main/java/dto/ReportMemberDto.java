package dto;

import java.util.Date;

public class ReportMemberDto {
	private int reportNo;
	private String userId;
	private String reportId;
	private String reportUrl;
	private Date regDate;
	private String accept;
	private String reject;
	
	public ReportMemberDto() {
		super();
	}

	public ReportMemberDto(String userId, String reportId, String reportUrl) {
		super();
		this.userId = userId;
		this.reportId = reportId;
		this.reportUrl = reportUrl;
	}

	public ReportMemberDto(int reportNo, String userId, String reportId, String reportUrl, Date regDate, String accept,
			String reject) {
		super();
		this.reportNo = reportNo;
		this.userId = userId;
		this.reportId = reportId;
		this.reportUrl = reportUrl;
		this.regDate = regDate;
		this.accept = accept;
		this.reject = reject;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getReject() {
		return reject;
	}

	public void setReject(String reject) {
		this.reject = reject;
	}

}
