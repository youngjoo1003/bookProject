package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import utils.JSFunction;

@WebServlet("/register.ez")
public class RegisterController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user_Id = req.getParameter("user_id");
		String user_Pw = req.getParameter("user_pw");
		String user_Name = req.getParameter("user_name");
		String user_Email = req.getParameter("user_email");
		String user_Tel = req.getParameter("user_tel");
		String user_Address = req.getParameter("user_address");
		
		UserDAO dao = new UserDAO();
		int result = dao.registerUser(user_Id, user_Pw, user_Name, user_Email, user_Tel, user_Address);
		dao.close();
		
		if (result == 1) { //성공
			JSFunction.alertLocation(resp, "회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.", req.getContextPath() + "/login/Login.jsp");
		} else {	//실패
			JSFunction.alertBack(resp, "이미 등록된 회원입니다. 다시 입력해주세요.");
		}
		
	}
	
	
	
	
}
