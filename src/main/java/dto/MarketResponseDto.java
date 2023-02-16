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
	ip VARCHAR(20), -- 글쓴이 ip
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
public class MarketResponseDto {
	private int marketNo;
	private int writerNo;
	private String title;
	private String content;
	private String part;
	private int price;
	private String writeDate;
	private int hits;
	private String ip;
	private String[] image = new String[5];
	private String disabled;
	private String report;
	
	public MarketResponseDto() {
		super();
	}
	public MarketResponseDto(int marketNo, int writerNo, String title, String content, String part, int price,
			String writeDate, int hits, String ip, String[] image, String disabled, String report) {
		super();
		this.marketNo = marketNo;
		this.writerNo = writerNo;
		this.title = title;
		this.content = content;
		this.part = part;
		this.price = price;
		this.writeDate = writeDate;
		this.hits = hits;
		this.ip = ip;
		this.image = image;
		this.disabled = disabled;
		this.report = report;
	}
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String[] getImage() {
		return image;
	}
	public void setImage(int index, String image) {
		this.image[index] = image;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
}
