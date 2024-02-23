package dto;

import java.sql.Date;

public class BoardDTO {
	private String board_No ;
	private String board_Title ;
	private String board_Content ;
	private java.sql.Date board_Date ;
	private String board_Hit ;
	private String board_Img ;
	private String book_Id ;
	private int book_Price;
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(String board_No, String board_Title, String board_Content, Date board_Date, String board_Hit,
			String board_Img, String book_Id) {
		super();
		this.board_No = board_No;
		this.board_Title = board_Title;
		this.board_Content = board_Content;
		this.board_Date = board_Date;
		this.board_Hit = board_Hit;
		this.board_Img = board_Img;
		this.book_Id = book_Id;
	}

	public String getBoard_No() {
		return board_No;
	}

	public void setBoard_No(String board_No) {
		this.board_No = board_No;
	}

	public String getBoard_Title() {
		return board_Title;
	}

	public void setBoard_Title(String board_Title) {
		this.board_Title = board_Title;
	}

	public String getBoard_Content() {
		return board_Content;
	}

	public void setBoard_Content(String board_Content) {
		this.board_Content = board_Content;
	}

	public java.sql.Date getBoard_Date() {
		return board_Date;
	}

	public void setBoard_Date(java.sql.Date board_Date) {
		this.board_Date = board_Date;
	}

	public String getBoard_Hit() {
		return board_Hit;
	}

	public void setBoard_Hit(String board_Hit) {
		this.board_Hit = board_Hit;
	}

	public String getBoard_Img() {
		return board_Img;
	}

	public void setBoard_Img(String board_Img) {
		this.board_Img = board_Img;
	}

	public String getBook_Id() {
		return book_Id;
	}

	public void setBook_Id(String book_Id) {
		this.book_Id = book_Id;
	}

	public int getBook_Price() {
		return book_Price;
	}

	public void setBook_Price(int book_Price) {
		this.book_Price = book_Price;
	}


	
	
	
}