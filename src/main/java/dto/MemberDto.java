package dto;

import java.util.Objects;

public class MemberDto {
	private int no;//11 no auto-increment
	private String id;//50 id not-null
	private String pw;//500 pw not-null
	private String nickName;//20 nickname not-null
	private String address;//100 address not-null
	private String zipCode;//5 zipcode not-null
	public MemberDto() {}
	//회원가입용 컨스트럭터
	public MemberDto(String id, String pw, String nickName, String address, String zipCode) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.address = address;
		this.zipCode = zipCode;
	}
	//로그인 세션용 컨스트럭터

	public MemberDto(int no, String id, String pw, String nickName, String address, String zipCode) {
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
		this.address = address;
		this.zipCode = zipCode;
	}

	public int getNo() {
		return no;
	}

	public MemberDto setNo(int no) {
		this.no = no;
		return this;
	}

	public String getId() {
		return id;
	}

	public MemberDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getPw() {
		return pw;
	}

	public MemberDto setPw(String pw) {
		this.pw = pw;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public MemberDto setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public MemberDto setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getZipCode() {
		return zipCode;
	}

	public MemberDto setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MemberDto memberDto = (MemberDto) o;
		return no == memberDto.no;
	}

	@Override
	public int hashCode() {
		return Objects.hash(no);
	}

	@Override
	public String toString() {
		return "MemberDto{" +
				"no=" + no +
				", id='" + id + '\'' +
				", pw='" + pw + '\'' +
				", nickName='" + nickName + '\'' +
				", address='" + address + '\'' +
				", zipCode='" + zipCode + '\'' +
				'}';
	}
}
