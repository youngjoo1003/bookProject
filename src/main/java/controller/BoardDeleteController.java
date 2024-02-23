package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import utils.JSFunction;

@WebServlet("/boardDelete.ez")
public class BoardDeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		String board_No = req.getParameter("board_no");
		int result = dao.deleteBoard(board_No);
		dao.close();
		
		// 성공 or 실패?
		if (result == 1) { // 글쓰기 성공
			JSFunction.alertLocation(resp, "성공적으로 삭제하였습니다.", req.getContextPath() + "/bookclub/SelectBook_Edit.jsp");
		} else { // 글쓰기 실패
			JSFunction.alertBack(resp, "삭제가 실패하였습니다.");
		}
		
	}
}