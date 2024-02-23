<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//관리자만 접근가능
if (session.getAttribute("UserManager") == null || !session.getAttribute("UserManager").equals(1)) {
	JSFunction.alertBack("관리자 전용페이지입니다", out);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddBook.jsp</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
	function validateForm(form){
		
		if (form.book_name.value == ""){
			alert("제목을 입력하세요.");
			form.book_name.focus();
			return false;
		}
		if (form.book_price.value == ""){
			alert("가격을 입력하세요.");
			form.book_price.focus();
			return false;
		}
		if (form.book_author.value == ""){
			alert("저자를 입력하세요.");
			form.book_author.focus();
			return false;
		}
		if (form.book_publisher.value == ""){
			alert("출판사를 입력하세요.");
			form.book_publisher.focus();
			return false;
		}
		if (form.book_category.value == ""){
			alert("카테고리를 선택하세요.");
			form.book_category.focus();
			return false;
		}
		if (form.book_stock.value == ""){
			alert("수량을 입력하세요.");
			form.book_stock.focus();
			return false;
		}
	}
</script>
</head>
<body>

	<!-- navi, Header-->
	<jsp:include page="../Header.jsp" />
	
	<br><br>
	<div class="container">
	    <div class="row justify-content-center">
	        <div class="col-md-6">
	            <h2 class="text-center" style="font-weight: bold; color: gray;">도서 등록</h2>
	            <hr>
	            <form name="addFrm" method="post" action="../add.ez" onsubmit="return validateForm(this);">
	                
	                <div class="mb-3 row" style="text-align: center;">
	                    <label for="book_name" class="col-sm-4 col-form-label">책 제목</label>
	                    <div class="col-sm-8">
	                        <input type="text" id="book_name" name="book_name" class="form-control" style="width: 320px">
	                    </div>
	                </div>
	                <div class="mb-3 row" style="text-align: center;">
	                    <label for="book_author" class="col-sm-4 col-form-label">저자</label>
	                    <div class="col-sm-8">
	                        <input type="text" id="book_author" name="book_author" class="form-control" style="width: 320px">
	                    </div>
	                </div>
	                <div class="mb-3 row" style="text-align: center;">
	                    <label for="book_publisher" class="col-sm-4 col-form-label">출판사</label>
	                    <div class="col-sm-8">
	                        <input type="text" id="book_publisher" name="book_publisher" class="form-control" style="width: 320px">
	                    </div>
	                </div>
	                <div class="mb-3 row" style="text-align: center;">
	                    <label for="book_category" class="col-sm-4 col-form-label">카테고리</label>
	                    <div class="col-sm-8">
	                        <select id="book_category" name="book_category" class="form-control" style="width: 320px">
	                            <option value="none">=== 선택 ===</option>
	                            <option value="소설">소설</option>
	                            <option value="자기계발">자기계발</option>
	                            <option value="언어">언어</option>
	                            <option value="과학">과학</option>
	                            <option value="역사">역사</option>
	                        </select>
	                    </div>
	                </div>
	                <div class="mb-3 row" style="text-align: center;">
	                    <label for="book_price" class="col-sm-4 col-form-label">가격</label>
	                    <div class="col-sm-8">
	                        <input type="number" id="book_price" name="book_price" class="form-control" value="0" min="0" style="width: 320px">
	                    </div>
	                </div>
	                <div class="mb-3 row" style="text-align: center;">
	                    <label for="book_stock" class="col-sm-4 col-form-label">수량</label>
	                    <div class="col-sm-8">
	                        <input type="number" id="book_stock" name="book_stock" value="0" min="0" class="form-control" style="width: 320px">
	                    </div>
	                </div>
	                <div class="text-center">
	                    <button type="submit" class="btn btn-primary">등록</button>
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