package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.BookDAO;
import dto.BoardDTO;
import utils.JSFunction;


@WebServlet("/boardWrite.ez")
public class BoardWriteController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String book_Id = req.getParameter("book_id");
		
		BookDAO dao = new BookDAO();
		int result = dao.selectView2(book_Id);
		dao.close();
		
		if (result != 1) {
		    JSFunction.alertBack(resp, "존재하는 도서 아이디를 입력하세요.");
		} else {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_Title(req.getParameter("board_title"));
			dto.setBoard_Content(req.getParameter("board_content"));
			dto.setBoard_Img(req.getParameter("board_img"));	//이미지 삽입 미구현
			dto.setBook_Id(req.getParameter("book_id"));
			
			BoardDAO dao2 = new BoardDAO();
			int result2 = dao2.writeBoard(dto);
			dao2.close();
			
			// 성공 or 실패?
			if (result2 == 1) { // 글쓰기 성공
				JSFunction.alertLocation(resp, "성공적으로 등록하였습니다.", req.getContextPath() + "/sale/BoardList.jsp");
			} else { // 글쓰기 실패
				JSFunction.alertBack(resp, "등록이 실패하였습니다.");
			}
		}
		
		
	}
}