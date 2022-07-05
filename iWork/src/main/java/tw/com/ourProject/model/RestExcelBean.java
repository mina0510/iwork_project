package tw.com.ourProject.model;

public class RestExcelBean {

	private String Num;
	private String RestNum;
	private String RestTel;
	private String RestAddr;
	public String getNum() {
		return Num;
	}
	public void setNum(String num) {
		Num = num;
	}
	public String getRestNum() {
		return RestNum;
	}
	public void setRestNum(String restNum) {
		RestNum = restNum;
	}
	public String getRestTel() {
		return RestTel;
	}
	public void setRestTel(String restTel) {
		RestTel = restTel;
	}
	public String getRestAddr() {
		return RestAddr;
	}
	public void setRestAddr(String restAddr) {
		RestAddr = restAddr;
	}
	@Override
	public String toString() {
		return "ExcelBean [Num=" + Num + ", RestNum=" + RestNum + ", RestTel=" + RestTel + ", RestAddr=" + RestAddr
				+ "]";
	}
	
}
