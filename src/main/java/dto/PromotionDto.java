package dto;

public class PromotionDto {
	private int marketNo;
	private int writerNo;
	private String title;
	private String part;
	private int price;
	private String writeDate;
	private String image;
	public PromotionDto() {}
	public PromotionDto(int marketNo, int writerNo, String title, String part, int price, String writeDate,
			String image) {
		super();
		this.marketNo = marketNo;
		this.writerNo = writerNo;
		this.title = title;
		this.part = part;
		this.price = price;
		this.writeDate = writeDate;
		this.image = image;
	}
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "PromotionDto [marketNo=" + marketNo + ", writerNo=" + writerNo + ", title=" + title + ", part=" + part
				+ ", price=" + price + ", writeDate=" + writeDate + ", image=" + image + "]";
	}
}
