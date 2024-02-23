package controller;

import java.io.IOException;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.OrderDAO;
import dto.BoardDTO;
import dto.BookDTO;
import dto.OrderDTO;
import utils.JSFunction;

@WebServlet("/pay.do")
public class PayController extends HttpServlet {
	// 결제 완료 알림창 띄우고
	// 구매현황으로 값 전달
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트에서 입력한 숫자 가져오기
		HttpSession session = req.getSession();
		int order_Num = Integer.parseInt(req.getParameter("order_Num"));
		String book_Id = req.getParameter("book_Id");
		
		if (session.getAttribute("UserId") != null) { // 로그인 상태
			
			BookDAO bdao = new BookDAO();
			BookDTO bdto = new BookDTO();
			int checkNum = bdao.checkStock(order_Num, book_Id);
			
			if (checkNum != 1) {
				bdao.close();
				JSFunction.alertBack(resp, "해당 상품의 재고가 부족합니다.");
			} else {
				
				bdao.updateStock(order_Num, book_Id);
				bdao.close();
				
				OrderDAO dao = new OrderDAO();
				OrderDTO dto = new OrderDTO();
				
				dto.setBook_Id(req.getParameter("book_Id"));
				dto.setUser_Id((String) session.getAttribute("UserId"));
				dto.setOrder_Num(Integer.parseInt(req.getParameter("order_Num")));
				
				
				int result = dao.dataBaseInsert(dto);
				
				dao.close();
				
				
				
				if (result == 1) {
					JSFunction.alertLocation(resp, "성공적으로 구매하였습니다! 주문내역 페이지로 이동합니다.", req.getContextPath() + "/bookMarket/Orders.jsp");
				} else {
					JSFunction.alertBack(resp, "구매 중 오류가 발생했습니다. 다시 시도해주세요.");
				}
			}
		} else { // 로그아웃 상태
			JSFunction.alertLocation(resp, "로그인 후에 사용 가능합니다. 로그인 페이지로 이동합니다.",
					req.getContextPath() + "/login/Login.jsp");
		}
		
	}
}
