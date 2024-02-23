package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.BookDAO;
import dto.BoardDTO;
import dto.BookDTO;


@WebServlet("/detail.do")
public class DetailController extends HttpServlet{
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 책 상세보기
	  String book_Id = req.getParameter("book_id");
      BookDAO dao = new BookDAO();
      BookDTO dto = dao.selectView(book_Id);
      dao.close();

      BoardDAO dao2 = new BoardDAO();
      BoardDTO dto2 = dao2.selectView2(book_Id);
      dao2.close();
      
      req.setAttribute("dto", dto);
      req.setAttribute("dto2", dto2);
      req.getRequestDispatcher("/bookMarket/Detail.jsp").forward(req, resp);
   }
}