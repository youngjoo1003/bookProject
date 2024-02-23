package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import dto.BoardDTO;
import dto.BookDTO;

public class BoardDAO extends DBConnPool {

	public BoardDAO() {
		super();
	}
	
	
	
	public int writeBoard(BoardDTO dto) {
		int result = 0;
		
		String board_Title = dto.getBoard_Title();
		String board_Content = dto.getBoard_Content();
		String board_Img = dto.getBoard_Img();
		String book_Id = dto.getBook_Id();
		
		try {
			
			String query = "INSERT INTO BOARD (BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_DATE, BOARD_HIT, BOARD_IMG, BOOK_ID) "
					+ " VALUES (SEQ_BOARD_NO.nextval, ?, ?, SYSDATE, 0, ?, ?)"; // 쿼리문 작성
			pstmt = con.prepareStatement(query); 
			pstmt.setString(1, board_Title); 
			pstmt.setString(2, board_Content); 
			pstmt.setString(3, board_Img); 
			pstmt.setString(4, book_Id); 
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			System.out.println("writeBoard 중 오류 발생");
			e.printStackTrace();
		}
		return result; // DTO 객체에 저장 한 값을 리턴
	}
	
	
	public int deleteBoard(String board_No) {
    	int result = 0;
    	
    	String query = "DELETE FROM BOARD WHERE BOARD_NO=?";
    	try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board_No);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Board 삭제 중 예외 발생 ");
			e.printStackTrace();
		}
    	return result;
    }
	
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		
		String board_No = dto.getBoard_No();
		String board_Title = dto.getBoard_Title();
		String board_Content = dto.getBoard_Content();
		String board_Img = dto.getBoard_Img();
		String book_Id = dto.getBook_Id();
		
		try {
			String query = "UPDATE BOARD SET BOARD_TITLE=?, BOARD_CONTENT=?, BOARD_DATE=SYSDATE, BOARD_HIT=0, BOARD_IMG=?, BOOK_ID=? WHERE BOARD_NO=?"; // 쿼리문 작성
			pstmt = con.prepareStatement(query); 
			pstmt.setString(1, board_Title); 
			pstmt.setString(2, board_Content); 
			pstmt.setString(3, board_Img); 
			pstmt.setString(4, book_Id); 
			pstmt.setString(5, board_No); 
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			System.out.println("updateBoard 중 오류 발생");
			e.printStackTrace();
		}
		return result; // DTO 객체에 저장 한 값을 리턴
	}

	// 메인
	public List<BoardDTO> showBookListMain() {
		List<BoardDTO> board = new Vector<>();
		try {
			String query = " SELECT * FROM ( SELECT Tb.*, ROWNUM rNum FROM ( SELECT BD.*, BK.BOOK_PRICE "
					+ " FROM BOARD BD INNER JOIN BOOK BK ON BD.BOOK_ID = BK.BOOK_ID ORDER BY BOARD_NO DESC ) Tb ) WHERE rNum BETWEEN 1 AND 8";

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setBoard_No(rs.getString("board_No"));
				dto.setBoard_Title(rs.getString("board_Title"));
				dto.setBoard_Content(rs.getString("board_Content"));
				dto.setBoard_Date(rs.getDate("board_Date"));
				dto.setBoard_Hit(rs.getString("board_Hit"));
				dto.setBoard_Img(rs.getString("board_Img"));
				dto.setBook_Id(rs.getString("book_Id"));
				dto.setBook_Price(rs.getInt("book_Price"));

				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}

		return board;
	}

	// 메인
	public List<BoardDTO> showBookListMain2() {
		List<BoardDTO> board = new Vector<>();
		try {
			String query = " SELECT * FROM ( SELECT Tb.*, ROWNUM rNum FROM ( SELECT BD.*, BK.BOOK_PRICE "
					+ " FROM BOARD BD INNER JOIN BOOK BK ON BD.BOOK_ID = BK.BOOK_ID ORDER BY BOARD_NO DESC ) Tb ) WHERE rNum BETWEEN 1 AND 10";

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setBoard_No(rs.getString("board_No"));
				dto.setBoard_Title(rs.getString("board_Title"));
				dto.setBoard_Content(rs.getString("board_Content"));
				dto.setBoard_Date(rs.getDate("board_Date"));
				dto.setBoard_Hit(rs.getString("board_Hit"));
				dto.setBoard_Img(rs.getString("board_Img"));
				dto.setBook_Id(rs.getString("book_Id"));
				dto.setBook_Price(rs.getInt("book_Price"));

				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}

		return board;
	}

	// 자세히 보기 (번호를 받아 dto 로 리턴)
	public BoardDTO selectView(String board_No) {
		BoardDTO dto2 = new BoardDTO();

		try {
			String query = "SELECT * FROM BOARD WHERE board_no=?"; // 쿼리문 작성
			pstmt = con.prepareStatement(query); // 쿼리문 생성
			pstmt.setString(1, board_No); // 넘어온 num 값을 쿼리문에 삽입
			rs = pstmt.executeQuery(); // 쿼리문 실행

			if (rs.next()) { // 쿼리문 처리 값이 있으면

				dto2.setBoard_No(rs.getString("board_No"));
				dto2.setBoard_Title(rs.getString("board_Title"));
				dto2.setBoard_Content(rs.getString("board_Content"));
				dto2.setBoard_Date(rs.getDate("board_Date"));
				dto2.setBoard_Hit(rs.getString("board_Hit"));
				dto2.setBoard_Img(rs.getString("board_Img"));
				dto2.setBook_Id(rs.getString("book_Id"));

			}
		} catch (SQLException e) {
			System.out.println("상세보기 중 오류 발생 (selectView)");
			e.printStackTrace();
		}
		return dto2; // DTO 객체에 저장 한 값을 리턴
	}

	public BoardDTO selectView2(String book_Id) {
		BoardDTO dto2 = new BoardDTO();

		try {
			String query = "SELECT * FROM BOARD WHERE BOOK_ID=?"; // 쿼리문 작성
			pstmt = con.prepareStatement(query); // 쿼리문 생성
			pstmt.setString(1, book_Id); // 넘어온 num 값을 쿼리문에 삽입
			rs = pstmt.executeQuery(); // 쿼리문 실행

			if (rs.next()) { // 쿼리문 처리 값이 있으면

				dto2.setBoard_No(rs.getString("board_No"));
				dto2.setBoard_Title(rs.getString("board_Title"));
				dto2.setBoard_Content(rs.getString("board_Content"));
				dto2.setBoard_Date(rs.getDate("board_Date"));
				dto2.setBoard_Hit(rs.getString("board_Hit"));
				dto2.setBoard_Img(rs.getString("board_Img"));
				dto2.setBook_Id(rs.getString("book_Id"));

			}
		} catch (SQLException e) {
			System.out.println("상세보기 중 오류 발생 (selectView)");
			e.printStackTrace();
		}
		return dto2; // DTO 객체에 저장 한 값을 리턴
	}

	// 검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM BOARD";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}

		return totalCount;
	}

	// 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> board = new Vector<BoardDTO>();
		String query = " SELECT * FROM ( SELECT Tb.*, ROWNUM rNum FROM ( SELECT BD.*, BK.BOOK_PRICE "
				+ " FROM BOARD BD INNER JOIN BOOK BK ON BD.BOOK_ID = BK.BOOK_ID ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}

		query += " ORDER BY BOARD_NO DESC ) Tb ) WHERE rNum BETWEEN ? AND ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, map.get("start").toString());
			pstmt.setString(2, map.get("end").toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setBoard_No(rs.getString("board_No"));
				dto.setBoard_Title(rs.getString("board_Title"));
				dto.setBoard_Content(rs.getString("board_Content"));
				dto.setBoard_Date(rs.getDate("board_Date"));
				dto.setBoard_Hit(rs.getString("board_Hit"));
				dto.setBoard_Img(rs.getString("board_Img"));
				dto.setBook_Id(rs.getString("book_Id"));
				dto.setBook_Price(rs.getInt("book_Price"));

				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}



	public int cheokBookId(String book_Id) {
		int result2 = 0;
		
		try {
		String query = "SELECT * FROM BOARD WHERE BOOK_ID = ?";
		
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, book_Id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result2 = 1;	//1이면 삭제불가능
			}
		} catch (Exception e) {
			System.out.println("cheokBookId 중 예외 발생");
			e.printStackTrace();
		}
		
		return result2;
	}
	
	
	
	
}