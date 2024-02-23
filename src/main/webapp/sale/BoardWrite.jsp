<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% //관리자만 접근가능
if (session.getAttribute("UserManager") == null || !session.getAttribute("UserManager").equals(1)) {
	JSFunction.alertBack("관리자 전용페이지입니다", out);
} 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>책 판매 게시물 등록 페이지</title>
    <!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- 부트스트랩 자바스크립트 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<!-- Header -->
    <%@ include file="../Header.jsp" %>
    
	<div class="container mt-5" style="width: 900px;">
	    <h2 class="text-center mb-4" style="font-weight: bold; color: gray;">책 판매 게시물 등록</h2>
	    <hr>
	    <form action="../boardWrite.ez" method="POST" >
	        <div class="mb-3">
	            <label for="title" class="form-label">제목</label>
	            <input type="text" class="form-control" id="board_title" name="board_title" required>
	        </div>
	        <div class="mb-3">
	            <label for="content" class="form-label">내용</label>
	            <textarea class="form-control" id="board_content" name="board_content" rows="6" required></textarea>
	        </div>
	        <div class="mb-3">	<!-- 이미지 삽입 미구현 -->
	            <label for="image" class="form-label">이미지 업로드</label>
	            <input type="file" class="form-control" id="board_img" name="board_img" >
	        </div>
	        <div class="mb-3">
	            <label for="content" class="form-label">책 ID</label>
	            <input type="text" class="form-control" id="book_id" name="book_id" required>
	        </div>
	        <div class="text-center">
	            <button type="submit" class="btn btn-primary" style="width: 100px;">등록</button>
	        </div>
	    </form>
	</div>
	<br><br>
	<!-- Header -->
    <%@ include file="../Footer.jsp" %>

</body>
</html>