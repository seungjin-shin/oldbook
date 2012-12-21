package gson;

public class Member {
	private String ID;
	private String name;
	private String phone;
	private String passwd;
	
	public Member(){}
	public Member(String id, String n, String ph, String p){
		this.ID = id;
		this.name = n;
		this.phone = ph;
		this.passwd = p;
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