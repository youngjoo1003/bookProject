package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import dto.OrderDTO;
import utils.JSFunction;

@WebServlet("/basket.do")
public class BasketController extends HttpServlet{
	// 결제 완료 알림창 띄우고
	// 구매현황으로 값 전달
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 클라이언트에서 입력한 숫자 가져오기
		HttpSession session = req.getSession();
		
			if(session.getAttribute("UserId") != null) { // 로그인 상태
				JSFunction.alertLocation(resp, "장바구니에 추가되었습니다.", req.getContextPath() + "/Main.jsp");
				return;
			} else { // 로그아웃 상태
				JSFunction.alertLocation(resp, "로그인 후에 사용 가능합니다. 로그인 페이지로 이동합니다.", req.getContextPath() + "/login/Login.jsp");
				return;
			}
	}
}
