package gson;

public class MemberInfo {
	private String ID;
	private String passwd;
	private String name;
	private String phone;
	private String GCMID;
	

	public MemberInfo(){}
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
