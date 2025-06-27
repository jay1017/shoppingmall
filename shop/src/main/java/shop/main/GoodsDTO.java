package shop.main;

public class GoodsDTO {
	private int gnum; // 상품번호
	private int canum; // 카테고리번호
	private String gname;// 상품명
	private int gprice;// 상품의 가격
	private String gcontent; // 상품설명
	private int ginum; // 상품이미지 번호
	private int discount; // 할인율
	private int gread; // 조회수

	public int getGnum() {
		return gnum;
	}

	public void setGnum(int gnum) {
		this.gnum = gnum;
	}

	public int getCanum() {
		return canum;
	}

	public void setCanum(int canum) {
		this.canum = canum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getGprice() {
		return gprice;
	}

	public void setGprice(int gprice) {
		this.gprice = gprice;
	}

	public String getGcontent() {
		return gcontent;
	}

	public void setGcontent(String gcontent) {
		this.gcontent = gcontent;
	}

	public int getGinum() {
		return ginum;
	}

	public void setGinum(int ginum) {
		this.ginum = ginum;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getGread() {
		return gread;
	}

	public void setGread(int gread) {
		this.gread = gread;
	}

}
