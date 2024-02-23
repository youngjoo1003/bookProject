package dto;

public class OrderDTO {
	private int order_No; // 주문번호
	private java.sql.Date order_Date;
	private int order_Num; // 수량
	private String book_Id;
	private String user_Id;
	private String book_Name;
	private int book_Price;
	private String user_Address;
	private String user_Name;
	
	
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public int getOrder_No() {
		return order_No;
	}
	public java.sql.Date getOrder_Date() {
		return order_Date;
	}
	public int getOrder_Num() {
		return order_Num;
	}
	public String getBook_Id() {
		return book_Id;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public String getBook_Name() {
		return book_Name;
	}
	public int getBook_Price() {
		return book_Price;
	}
	public String getUser_Address() {
		return user_Address;
	}
	
	
	public void setOrder_No(int order_No) {
		this.order_No = order_No;
	}
	public void setOrder_Date(java.sql.Date order_Date) {
		this.order_Date = order_Date;
	}
	public void setOrder_Num(int order_Num) {
		this.order_Num = order_Num;
	}
	public void setBook_Id(String book_Id) {
		this.book_Id = book_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}
	public void setBook_Price(int book_Price) {
		this.book_Price = book_Price;
	}
	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}
	
}
