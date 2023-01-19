package dto;

public class ManagerDto {
	
	//관리자 중복 접속금지
	/*
	 * 1.세션은 30분유지함
	 * 2.관리자 로그인시 currentStatus 1 로 셋팅,세션아이디 저장
	 * 3.로그아웃시 0으로 셋팅
	 * 4.로그인시 currentStatus가 0이거나, 1이지만 마지막 로그인한 관리자가 별다른 행동을 하지않고
	 * 30분이 지난경우 접속가능
	 */
	private String id;
	private String pw;//1q2w3e4r!Q!
	private String name;
	private boolean currentStatus;
	
	public ManagerDto(String id, String pw, String name, boolean currentStatus) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.currentStatus = currentStatus;
	}
	public ManagerDto() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ManagerDto [id=" + id + ", pw=" + pw + ", name=" + name + ", currentStatus=" + currentStatus + "]";
	}
}
