<%@page import="dto.BoardDTO"%>
<%@page import="dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마켓 - 상세보기</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
	function validateForm(form) {
		if (form.payAlert.value == null) {
			alert("수량을 입력해주세요.");
			form.payAlert.focus();
			return false;
		} 
		if (form.payAlert.value > 10) {
			alert("최대 10개까지 주문 가능합니다.")
			return false;
		}
	}
</script>
<style>
  .book-info {
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 10px;
  }
</style>
</head>
<body>
	<!-- navi, Header-->
	<jsp:include page="../Header.jsp" />

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-5">
				<div class="text-center">
					<img src="${dto2.getBoard_Img()}" class="img-fluid border border-dark rounded" alt="Book Image">
				</div>
			</div>
			<div class="col-md-6">
				<div class="book-info">
					<h2 class="text-center mb-4" style="font-weight: bold; color: gray;">${dto.getBook_Name()}</h2>
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th scope="row" style="width: 100px;">저자</th>
								<td >${dto.getBook_Author()}</td>
							</tr>
							<tr>
								<th scope="row" style="width: 100px;">출판사</th>
								<td>${dto.getBook_Publisher()}</td>
							</tr>
							<tr>
								<th scope="row" style="width: 100px;">가격</th>
								<td>${dto.getBook_Price()}원</td>
							</tr>
							<tr>
								<th scope="row" style="width: 100px;">재고</th>
								<td>${dto.getBook_Stock()}</td>
							</tr>
						</tbody>
					</table>
					<h3 style=" color: gray;">간단한 책 소개</h3>
					<p>${dto2.getBoard_Content()}</p>

					<br><br>
					<div class="d-flex justify-content-between">
						&nbsp;&nbsp;
						<form name="basketAlert" method="post" action="./basket.do"
							onsubmit="return validateForm(this);">
							<input type="hidden" name="book_Id" id="book_Id"
								value="${dto.getBook_Id()}"> <input type="number"
								name="order_Num" min="1" max="10" step="1" value="1">
							<button type="submit" name="put_basket" class="btn btn-success">장바구니</button>
						</form>
						<form name="payAlert" method="post" action="./pay.do"
							onsubmit="return validateForm(this);">
							<input type="hidden" name="book_Id" id="book_Id"
								value="${dto.getBook_Id()}"> <input type="number"
								name="order_Num" min="1" max="10" step="1" value="1">
							<button type="submit" name="payment" class="btn btn-primary">결제하기</button>
						</form>
						&nbsp;&nbsp;
					</div>

	

				</div>
			</div>
		</div>
	</div>

	<br><br>
	<!-- Footer-->
	<%@ include file="../Footer.jsp"%>

</body>
</html>