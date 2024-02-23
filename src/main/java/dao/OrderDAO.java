package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import dto.OrderDTO;

public class OrderDAO extends DBConnPool {
	public OrderDAO() {
		super();
	}

	public int dataBaseInsert(OrderDTO dto) {
		int result = 0;

		try {
			String query = "INSERT INTO ORDERS ( ORDER_NO, ORDER_DATE, ORDER_NUM, BOOK_ID, USER_ID ) VALUES (SEQ_ORDER_NO.nextval, SYSDATE, ?, ?, ?)";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, dto.getOrder_Num());
			pstmt.setString(2, dto.getBook_Id());
			pstmt.setString(3, dto.getUser_Id());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("dataBaseInsert 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM ORDERS";
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
	public List<OrderDTO> selectListPage(Map<String, Object> map) {
		List<OrderDTO> board = new Vector<OrderDTO>();
		String query = "SELECT * FROM (SELECT Tb.*, ROWNUM AS rNum FROM (SELECT O.ORDER_NO, O.BOOK_ID, O.USER_ID, O.ORDER_NUM,O.ORDER_DATE, BK.BOOK_NAME, BK.BOOK_PRICE, U.USER_ADDRESS FROM ORDERS O"
			           + " INNER JOIN BOOK BK ON O.BOOK_ID = BK.BOOK_ID "
			           + " INNER JOIN USERS U ON O.USER_ID = U.USER_ID "
			           + " WHERE U.USER_ID = ?"
			           + " ORDER BY O.ORDER_NO DESC) Tb ) WHERE rNum BETWEEN ? AND ?";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, map.get("user").toString());
			pstmt.setString(2, map.get("start").toString());
			pstmt.setString(3, map.get("end").toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderDTO dto = new OrderDTO();

				dto.setOrder_No(rs.getInt("order_No"));
				dto.setBook_Id(rs.getString("book_Id"));
				dto.setOrder_Date(rs.getDate("order_Date"));
				dto.setUser_Id(rs.getString("user_Id"));
				dto.setOrder_Num(rs.getInt("order_Num"));
				dto.setBook_Name(rs.getString("book_Name"));
				dto.setBook_Price(rs.getInt("book_Price"));
				dto.setUser_Address(rs.getString("user_Address"));

				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}

	// 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환
	public List<OrderDTO> selectView(String user_Id) {
		List<OrderDTO> board = new Vector<OrderDTO>();
		OrderDTO dto = new OrderDTO();

		try {
			String query = "SELECT * FROM ORDERS WHERE user_Id=?"; // 쿼리문 템플릿 준비
			pstmt = con.prepareStatement(query); // 쿼리문 준비
			pstmt.setString(1, user_Id); // 인파라미터 설정
			rs = pstmt.executeQuery(); // 쿼리문 실행

			while (rs.next()) { // 결과를 DTO 객체에 저장

				dto.setOrder_No(rs.getInt("order_no"));
				dto.setOrder_Date(rs.getDate("order_date"));
				dto.setOrder_Num(rs.getInt("order_num"));
				dto.setBook_Id(rs.getString("book_Id"));
				dto.setUser_Id(user_Id);

				board.add(dto);
			}

			String query2 = "SELECT * FROM BOOK WHERE BOOK_ID=?";
			pstmt = con.prepareStatement(query2);
			pstmt.setString(1, dto.getBook_Id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setBook_Name(rs.getString("book_Name"));
				dto.setBook_Price(rs.getInt("book_Price"));
			}

			String query3 = "SELECT * FROM USER WHERE USER_ID=?";
			pstmt = con.prepareStatement(query3);
			pstmt.setString(1, dto.getUser_Id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setUser_Name(rs.getString(0));
				dto.setUser_Address(rs.getString("user_Address"));
			}

		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		System.out.println(board);
		return board; // 결과 반환
	}

}
