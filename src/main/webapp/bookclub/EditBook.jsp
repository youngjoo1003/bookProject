<%@page import="dto.BookDTO"%>
<%@page import="dao.BookDAO"%>
<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

//관리자만 접근가능
if (session.getAttribute("UserManager") == null || !session.getAttribute("UserManager").equals(1)) {
	JSFunction.alertBack("관리자 전용페이지입니다", out);
}

String book_Id = request.getParameter("book_id"); // 일련번호 받기 
BookDAO dao = new BookDAO(); // DAO 생성
BookDTO dto = dao.selectView(book_Id); // 게시물 가져오기 
pageContext.setAttribute("dto", dto);

dao.close(); // DB 연결 해제
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EditBook.jsp</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<!-- navi, Header-->
	<jsp:include page="../Header.jsp" />
	
	<br><br>
	<div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center" style="font-weight: bold; color: gray;">도서 수정</h2>
                <hr>
                <form name="editFrm" method="post" action="../edit.ez" onsubmit="return validateForm(this);">
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_id" class="col-sm-4 col-form-label">책 ID</label>
                        <div class="col-sm-8">
                            <input type="text" id="book_id" name="book_id" class="form-control" value="${dto.book_Id}" style="width: 320px" readonly>
                        </div>
                    </div>
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_name" class="col-sm-4 col-form-label">책 제목</label>
                        <div class="col-sm-8">
                            <input type="text" id="book_name" name="book_name" class="form-control" value="${dto.book_Name}"  style="width: 320px">
                        </div>
                    </div>
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_author" class="col-sm-4 col-form-label">저자</label>
                        <div class="col-sm-8">
                            <input type="text" id="book_author" name="book_author" class="form-control" value="${dto.book_Author}" style="width: 320px">
                        </div>
                    </div>
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_publisher" class="col-sm-4 col-form-label">출판사</label>
                        <div class="col-sm-8">
                            <input type="text" id="book_publisher" name="book_publisher" class="form-control" value="${dto.book_Publisher}" style="width: 320px">
                        </div>
                    </div>
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_category" class="col-sm-4 col-form-label">카테고리</label>
                        <div class="col-sm-8">
                            <select id="book_category" name="book_category" class="form-control" style="width: 320px">
                                <option value="none">=== 선택 ===</option>
                                <c:if test="${dto.book_Category eq '소설'}">
                                    <option value="소설" selected>소설</option>
                                </c:if>
                                <c:if test="${dto.book_Category eq '자기계발'}">
                                    <option value="자기계발" selected>자기계발</option>
                                </c:if>
                                <c:if test="${dto.book_Category eq '언어'}">
                                    <option value="언어" selected>언어</option>
                                </c:if>
                                <c:if test="${dto.book_Category eq '과학'}">
                                    <option value="과학" selected>과학</option>
                                </c:if>
                                <c:if test="${dto.book_Category eq '역사'}">
                                    <option value="역사" selected>역사</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_price" class="col-sm-4 col-form-label">가격</label>
                        <div class="col-sm-8">
                            <input type="number" id="book_price" name="book_price" class="form-control" value="${dto.book_Price}" min="0" style="width: 320px">
                        </div>
                    </div>
                    <div class="mb-3 row" style="text-align: center;">
                        <label for="book_stock" class="col-sm-4 col-form-label">수량</label>
                        <div class="col-sm-8">
                            <input type="number" id="book_stock" name="book_stock" class="form-control" value="${dto.book_Stock}" min="0" style="width: 320px">
                        </div>
                    </div>
                    <div class="text-center" >
                        <button type="submit" class="btn btn-primary" >등록</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
	<br><br>
	
	<!-- Footer-->
	<%@ include file="../Footer.jsp"%>
	
</body>
</html>