package dto;
/*
 * 	create table tranin_market(
		market_no int PRIMARY key AUTO_INCREMENT,
		writer_no int not null, -- 글쓴이
		title varchar(100) not null, -- 제목
		content text null, -- 글 내용
		part ENUM('sell', 'buy') not null, -- 판매글 or 구매글 여부
		price int null, -- 가격 / 희망가격
		write_date timestamp not null, -- 글 작성 날짜
		hits int DEFAULT 0, -- 조회수
		image_1 varchar(300) null, -- 이미지1 url (메인 이미지)
		image_2 varchar(300) null, -- 이미지2 url
		image_3 varchar(300) null, -- 이미지3 url
		image_4 varchar(300) null, -- 이미지4 url
		image_5 varchar(300) null, -- 이미지5 url
		is_upd ENUM('true', 'false') DEFAULT 'false', -- 수정 여부 (true : 수정글 있음, 게시글 안보이게 처리)
		trade_acpt ENUM('true', 'false') DEFAULT 'false', -- 거래 완료 여부  (true : 거래 완료, 게시글 안보이게 처리)
		CONSTRAINT writer_fk foreign key(writer_no) REFERENCES tranin_member(`no`)
		-- foreign key 지정 (tranin_member의 `no` 컬럼)
	);
	
	create table tranin_market(
	market_no int PRIMARY key AUTO_INCREMENT,
	writer_no int not null, -- 글쓴이
	title varchar(100) not null, -- 제목
	content text null, -- 글 내용
	part ENUM('sell', 'buy') not null, -- 판매글 or 구매글 여부
	price int null, -- 가격 / 희망가격
	write_date timestamp not null, -- 글 작성 날짜
	hits int DEFAULT 0, -- 조회수
	image_1 varchar(300) null, -- 이미지1 url (메인 이미지)
	image_2 varchar(300) null, -- 이미지2 url
	image_3 varchar(300) null, -- 이미지3 url
	image_4 varchar(300) null, -- 이미지4 url
	image_5 varchar(300) null, -- 이미지5 url
	disabled ENUM('true', 'false') DEFAULT 'false', -- 게시글 비활성화 여부 (true시 -> tranin_market_disabled에 추가)
	CONSTRAINT writer_fk foreign key(writer_no) REFERENCES tranin_member(`no`)
	-- foreign key 지정 (tranin_member의 `no` 컬럼)
);

 * */
/**
 * 장터 게시글 DTO
 * */
public class MarketDto {
	private int marketNo;
	private int writerNo;
	private String title;
	private String content;
	private String part;
	private int price;
	private String writeDate;
	private int hits;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private String disabled;

	// getter setter
	public int getMarketNo() {
		return marketNo;
	}
	public void setMarketNo(int marketNo) {
		this.marketNo = marketNo;
	}
	public int getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
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
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
}
