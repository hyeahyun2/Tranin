package dto;

public class ReportMemberDto {
	private int no;
	private String id;
	private String nickName;
	private int reportNo;
	private String reportId;
	private String reportNickName;
	
	public ReportMemberDto() {
		super();
	}

	public ReportMemberDto(int no, String id, String nickName, int reportNo, String reportId, String reportNickName) {
		super();
		this.no = no;
		this.id = id;
		this.nickName = nickName;
		this.reportNo = reportNo;
		this.reportId = reportId;
		this.reportNickName = reportNickName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportNickName() {
		return reportNickName;
	}

	public void setReportNickName(String reportNickName) {
		this.reportNickName = reportNickName;
	}
	
}
