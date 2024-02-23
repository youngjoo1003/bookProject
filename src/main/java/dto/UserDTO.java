package dto;

public class UserDTO {
	
	private String user_Id;
	private String user_Pw;
	private String user_Name;
	private String user_Email;
	private String user_Tel;
	private String user_Address;
	private java.sql.Date user_Date;
	private int user_Manager;
	
	public UserDTO() {
		super();
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Pw() {
		return user_Pw;
	}

	public void setUser_Pw(String user_Pw) {
		this.user_Pw = user_Pw;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getUser_Email() {
		return user_Email;
	}

	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}

	public String getUser_Tel() {
		return user_Tel;
	}

	public void setUser_Tel(String user_Tel) {
		this.user_Tel = user_Tel;
	}

	public String getUser_Address() {
		return user_Address;
	}

	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}

	public java.sql.Date getUser_Date() {
		return user_Date;
	}

	public void setUser_Date(java.sql.Date user_Date) {
		this.user_Date = user_Date;
	}

	public int getUser_Manager() {
		return user_Manager;
	}

	public void setUser_Manager(int user_Manager) {
		this.user_Manager = user_Manager;
	}
	
	
	

}
