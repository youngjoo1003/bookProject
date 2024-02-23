package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import dto.BookDTO;
import utils.JSFunction;


@WebServlet("/add.ez")
public class AddController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 폼값을 DTO에 저장
		BookDTO dto = new BookDTO();
		dto.setBook_Id(0);
		dto.setBook_Name(req.getParameter("book_name"));
		dto.setBook_Price(Integer.parseInt(req.getParameter("book_price")));
		dto.setBook_Author(req.getParameter("book_author"));
		dto.setBook_Publisher(req.getParameter("book_publisher"));
		dto.setBook_Category(req.getParameter("book_category"));
		dto.setBook_Stock(Integer.parseInt(req.getParameter("book_stock")));

		// DAO를 통해 DB에 게시 내용 저장
		BookDAO dao = new BookDAO();
		int result = dao.insertBook(dto);
		dao.close();

		// 성공 or 실패?
		if (result == 1) { // 글쓰기 성공
			JSFunction.alertLocation(resp, "성공적으로 등록하였습니다.", req.getContextPath() + "/bookclub/SelectBook_Edit.jsp");
		} else { // 글쓰기 실패
			JSFunction.alertBack(resp, "등록이 실패하였습니다.");
		}
	}
}