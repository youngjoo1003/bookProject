package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	
	//쿠키를 생성하고, 찾고, 삭제하는 공통의 코드
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		Cookie cookie = new Cookie(cName, cValue);	//쿠키 생성
		cookie.setPath("/");		//경로설정
		cookie.setMaxAge(cTime);	//유지 기간 설정
		response.addCookie(cookie);	//응답 객체에 추가
	} //makeCookie() 메서드 종료
	
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue = "";	//반환 값
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				if (cookieName.equals(cName)) {
					cookieValue = c.getValue();	//반환 값 갱신
				}
			}
		}
		return cookieValue;
	} //readCookie() 메서드 종료
	
	//명시한 이름의 쿠키를 삭제합니다.
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);	//위에서 만든 makeCookie를 활용하여 AGE를 0으로 만듬
	} //deleteCookie() 메서드 종료	

}
