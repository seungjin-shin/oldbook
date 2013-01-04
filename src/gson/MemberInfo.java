package gson;

public class MemberInfo {
	private String ID;
	private String passwd;
	private String name;
	private String number;
	private String GCMID;
	private String sellerID;
	private String sellerTitle;
	
	public MemberInfo(){}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSellerID() {
		return sellerID;
	}
	
	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}
	
	public String getSellerTitle() {
		return sellerTitle;
	}
	
	public void setSellerTitle(String sellerTitle) {
		this.sellerTitle = sellerTitle;
	}
	public String getGCMID() {
		return GCMID;
	}
	
	public void setGCMID(String gCMID) {
		GCMID = gCMID;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
