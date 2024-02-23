package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dto.UserDTO;
import utils.JSFunction;

@WebServlet("/login.ez")
public class LoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId = req.getParameter("user_id");
		String userPwd = req.getParameter("user_pw");

		UserDAO dao = new UserDAO();
		UserDTO dto = dao.loginUser(userId, userPwd);
		dao.close();

		if (dto.getUser_Id() != null) {
			HttpSession session = req.getSession();
			session.setAttribute("UserId", dto.getUser_Id());
			session.setAttribute("UserName", dto.getUser_Name());
			session.setAttribute("UserManager", dto.getUser_Manager());
			JSFunction.alertLocation(resp, "로그인에 성공하였습니다! " + dto.getUser_Name() + "님 환영합니다.", req.getContextPath() + "/Main.jsp");
		} else { // 로그인 실패
			JSFunction.alertBack(resp, "회원 정보가 일치하지 않습니다.");
		}
	}
	

}
