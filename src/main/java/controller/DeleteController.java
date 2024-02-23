package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.BookDAO;
import utils.JSFunction;

@WebServlet("/delete.ez")
public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String book_Id = req.getParameter("book_id");
		
		// foreign key 때문에 삭제가 안됨 (관련 게시물먼저 삭제해야함)
		BoardDAO dao2 = new BoardDAO();
		int result2 = dao2.cheokBookId(book_Id);
		dao2.close();
		
		if (result2 != 0) {	//0이면 삭제가능
			JSFunction.alertBack(resp, "먼저 도서ID에 해당하는 게시물을 삭제하고 진행해주세요.");
		} else {
			// 게시물 가져오기
			BookDAO dao = new BookDAO();
			int result = dao.deleteBook(book_Id);
			dao.close();
			
			// 성공 or 실패?
			if (result == 1) { // 글쓰기 성공
				JSFunction.alertLocation(resp, "성공적으로 삭제하였습니다.", req.getContextPath() + "/bookclub/SelectBook_Edit.jsp");
			} else { // 글쓰기 실패
				JSFunction.alertBack(resp, "삭제가 실패하였습니다.");
			}
		}
		
		
	}
}