package dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.DBConnPool;
import dto.BookDTO;

public class BookDAO extends DBConnPool {

	public BookDAO() {
		super();
	}
	
	public BookDTO selectView(String book_Id) {
	      BookDTO dto = new BookDTO();
	      
	      try {
	         String query = "SELECT * FROM book WHERE book_Id=?"; // 쿼리문 템플릿 준비
	         pstmt = con.prepareStatement(query); // 쿼리문 준비
	         pstmt.setString(1, book_Id); // 인파라미터 설정
	         rs = pstmt.executeQuery(); // 쿼리문 실행

	         if (rs.next()) { // 결과를 DTO 객체에 저장
	            
	            dto.setBook_Id(rs.getInt("book_Id"));
	            dto.setBook_Name(rs.getString("book_Name"));
	            dto.setBook_Price(rs.getInt("book_Price"));
	            dto.setBook_Author(rs.getString("book_Author"));
	            dto.setBook_Publisher(rs.getString("book_Publisher"));
	            dto.setBook_Category(rs.getString("book_Category"));
	            dto.setBook_Stock(rs.getInt("book_Stock"));
	            
	         }
	      } catch (Exception e) {
	         System.out.println("게시물 상세보기 중 예외 발생");
	         e.printStackTrace();
	      }
	      
	      return dto; // 결과 반환
	  }

	// 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환
	public int selectView2(String book_Id) {
		int result = 0;
		try {
			String query = "SELECT * FROM BOOK WHERE BOOK_ID=?"; // 쿼리문 템플릿 준비
			pstmt = con.prepareStatement(query); // 쿼리문 준비
			pstmt.setString(1, book_Id); // 인파라미터 설정
			rs = pstmt.executeQuery(); // 쿼리문 실행

			if (rs.next()) { // 결과를 DTO 객체에 저장
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return result; // 결과 반환
	}
	
	public List<BookDTO> arrayBook() {
		BookDTO dto = new BookDTO();
		List<BookDTO> bArray = new Vector<BookDTO>();
		
		String query = "SELECT * FROM BOOK";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				dto.setBook_Id(rs.getInt("Book_Id"));
				dto.setBook_Name(rs.getString("Book_Name"));
				dto.setBook_Author(rs.getString("Book_Author"));
				dto.setBook_Category(rs.getString("Book_Category"));
				dto.setBook_Price(rs.getInt("book_price"));
				dto.setBook_Publisher(rs.getString("book_publisher"));
				dto.setBook_Stock(rs.getInt("book_stock"));
				
				bArray.add(dto);
			}
		} catch (Exception e) {
			System.out.println("Book 객체 배열화 중 오류 (BookDAO.arrayBook)");
			e.printStackTrace();
		}
		return bArray;
	}

    // 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) {
       int totalCount = 0;
         String query = "SELECT COUNT(*) FROM BOOK";
         if (map.get("searchWord") != null) {
             query += " WHERE " + map.get("searchField") + " "
                    + " LIKE '%" + map.get("searchWord") + "%'";
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
    public List<BookDTO> selectListPage(Map<String, Object> map) {
       List<BookDTO> board = new Vector<BookDTO>();
         String query = " "
                      + "SELECT * FROM ( "
                      + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                      + "        SELECT * FROM BOOK ";

         if (map.get("searchWord") != null)
         {
             query += " WHERE " + map.get("searchField")
                    + " LIKE '%" + map.get("searchWord") + "%' ";
         }

         query += "        ORDER BY BOOK_ID DESC "
                + "    ) Tb "
                + " ) "
                + " WHERE rNum BETWEEN ? AND ?";

       try {
          pstmt = con.prepareStatement(query);
          pstmt.setString(1, map.get("start").toString());
          pstmt.setString(2, map.get("end").toString());
          rs = pstmt.executeQuery();

          while (rs.next()) {
             BookDTO dto = new BookDTO();

             dto.setBook_Id(rs.getInt(1));
             dto.setBook_Name(rs.getString(2));
             dto.setBook_Price(rs.getInt(3));
             dto.setBook_Author(rs.getString(4));
             dto.setBook_Publisher(rs.getString(5));
             dto.setBook_Category(rs.getString(6));
             dto.setBook_Stock(rs.getInt(7));

             board.add(dto);
          }
       } catch (Exception e) {
          System.out.println("게시물 조회 중 예외 발생");
          e.printStackTrace();
       }
       return board;
    }

    // 수정할 데이터를 받아 업데이트
    public int updateBook(BookDTO dto) {
        int result = 0;
        try {
            String query = "UPDATE BOOK SET BOOK_NAME=?, BOOK_PRICE=?, BOOK_AUTHOR=?, BOOK_PUBLISHER=?, BOOK_CATEGORY=?, BOOK_STOCK=? WHERE BOOK_ID=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, dto.getBook_Name());
            pstmt.setInt(2, dto.getBook_Price());
            pstmt.setString(3, dto.getBook_Author());
            pstmt.setString(4, dto.getBook_Publisher());
            pstmt.setString(5, dto.getBook_Category());
            pstmt.setInt(6, dto.getBook_Stock());
            pstmt.setInt(7, dto.getBook_Id());
            result = pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("BOOK 수정 중 예외 발생 (BookDAO.updateBook)");
            e.printStackTrace();
        }
        return result;
    }
    
    // 게시글 데이터를 받아 DB에 추가합니다(파일 업로드 지원).
    public int insertBook(BookDTO dto) {
        int result = 0;
        
        try {
            String query = "INSERT INTO BOOK (BOOK_ID, BOOK_NAME, BOOK_PRICE, BOOK_AUTHOR, BOOK_PUBLISHER, BOOK_CATEGORY, BOOK_STOCK) VALUES (SEQ_BOOK_ID.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, dto.getBook_Name());
            pstmt.setInt(2, dto.getBook_Price());
            pstmt.setString(3, dto.getBook_Author());
            pstmt.setString(4, dto.getBook_Publisher());
            pstmt.setString(5, dto.getBook_Category());
            pstmt.setInt(6, dto.getBook_Stock());
            result = pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("BOOK 추가 중 예외 발생 (BookDAO.insertBook)");
            e.printStackTrace();
        }
        return result;
    }
    
    public int deleteBook(String book_Id) {
    	int result = 0;
    	
    	String query = "DELETE FROM BOOK WHERE BOOK_ID=?";
    	try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, book_Id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("BOOK 삭제 중 예외 발생 (BookDAO.deleteBook)");
			e.printStackTrace();
		}
    	return result;
    }

    //주문할때 재고확인용
	public int checkStock(int order_Num, String book_Id) {
		int result = 0;
		String query = "SELECT * FROM BOOK WHERE BOOK_ID=? AND BOOK_STOCK >= ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, book_Id);
			pstmt.setInt(2, order_Num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int stock = rs.getInt("BOOK_STOCK");
	            if (stock >= order_Num) {
	                result = 1; // 재고 충분
	            } 
			}
			
		} catch (Exception e) {
			System.out.println("checkStock 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	//수량만큼 재고 삭제
	public void updateStock(int order_Num, String book_Id) {
        try {
            String query = "UPDATE BOOK SET BOOK_STOCK=BOOK_STOCK-? WHERE BOOK_ID=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, order_Num);
            pstmt.setString(2, book_Id);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("updateStock 중 예외 발생");
            e.printStackTrace();
        }
		
	}
    

 

	
}