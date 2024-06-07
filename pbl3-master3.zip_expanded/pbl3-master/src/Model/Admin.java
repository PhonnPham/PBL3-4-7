package Model;

public class Admin {
	private String UserName;
	private String Password;
	public Admin() {
		UserName = "";
		Password = "";
		
	}
	public Admin(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
