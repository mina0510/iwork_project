package tw.com.ourProject.model;

public class DishExcelBean {

	private String Num;
	private String dishnum;
	private String dishphoto;
	private Integer price;
	private String restnum;
	public String getNum() {
		return Num;
	}
	public void setNum(String num) {
		Num = num;
	}
	public String getDishnum() {
		return dishnum;
	}
	public void setDishnum(String dishnum) {
		this.dishnum = dishnum;
	}
	public String getDishphoto() {
		return dishphoto;
	}
	public void setDishphoto(String dishphoto) {
		this.dishphoto = dishphoto;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getRestnum() {
		return restnum;
	}
	public void setRestnum(String restnum) {
		this.restnum = restnum;
	}
	@Override
	public String toString() {
		return "dishExcelBean [Num=" + Num + ", dishnum=" + dishnum + ", setcontent="  + ", dishphoto="
				+ dishphoto + ", price=" + price + ", restnum=" + restnum + "]";
	}
	
	
}
