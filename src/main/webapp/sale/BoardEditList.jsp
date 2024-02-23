<%@page import="utils.JSFunction"%>
<%@page import="utils.BoardPage"%>
<%@page import="dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 초기화면 자바코드 -->
<%
//관리자만 접근가능
if (session.getAttribute("UserManager") == null || !session.getAttribute("UserManager").equals(1)) {
	JSFunction.alertBack("관리자 전용페이지입니다", out);
}

BoardDAO dao = new BoardDAO();

Map<String, Object> param = new HashMap<String, Object>(); // 파라미터 생성용 맵
String searchField = request.getParameter("searchField"); // 검색 필드 생성
String searchWord = request.getParameter("searchWord"); // 검색 내용 생성

if (searchWord != null) { // searchWord 값이 null 이 아니면 파라미터 메모리에 값 추가
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}
int totalCount = dao.selectCount(param); // 위에서 만든 파라미터 값을 매개값으로 전달받아 게시물 수 확인
//전체 페이지 수 계산
int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE")); // web.xml에 설정한 POSTS_PER_PAGE 값을 pageSize에 넣음
int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK")); // web.xml에 설정한 PAGES_PER_BLOCK 값을 blockPage에 넣음
int totalPage = (int) Math.ceil((double) totalCount / pageSize); // 전체 페이지 수를 계산

//현재 페이지 확인
int pageNum = 1; // pageNum 에 초기 page값 1 저장
String pageTemp = request.getParameter("pageNum"); // request에서 넘어온 pageNum을 pageTemp에 저장
if (pageTemp != null && !pageTemp.equals("")) { // pageTemp가 null이 아니면
	pageNum = Integer.parseInt(pageTemp); // pageNum을 요청받은 페이지로 수정
}

//목록에 출력할 게시물 범위 계산
int start = (pageNum - 1) * pageSize + 1; // 첫 게시물 번호 계산
int end = pageNum * pageSize; // 마지막 게시물 번호 계산

//위에서 선언한 Map<String, Object> param = new HashMap<String, Object>() 에 k, v 삽입
param.put("start", start); // 시작값 저장
param.put("end", end); // 마지막값 저장

List<BoardDTO> dto = dao.selectListPage(param); // 위에서 만든 파라미터 값을 매개값으로 전달받아 게시물 목록 받기
dao.close(); // DB 연결 끊기



%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SelectBook_Edit.jsp</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function confirmDelete(boardNo) {
            var confirmDelete = confirm("정말로 삭제하시겠습니까?");
            if (confirmDelete) {
                location.href = "../boardDelete.ez?board_no=" + boardNo;
            }
        }
    </script>
</head>
<body>

	<!-- Header -->
    <%@ include file="../Header.jsp" %>
	<br><br>
	<div class="container">
	    <h3 class="text-center" style="font-weight: bold; color: gray;">정보를 수정할 도서를 선택</h3>
	    <hr>
	    <table class="table table-striped table-hover">
	        <thead>
	        <tr class="text-center">
	            <th style="width: 10%;">번호</th>
	            <th style="width: 10%;">게시판ID</th>
	            <th style="width: 30%; text-align: center;">제목</th>
	            <th style="width: 15%;">등록일</th>
	            <th style="width: 15%;">도서ID</th>
	            <th style="width: 20%;">비고</th>
	        </tr>
	        </thead>
	        <tbody>
	        <%
	        if (dto.isEmpty()) { // 게시물이 하나도 없을 때
	        %>
	        <tr>
	            <td colspan="5" class="text-center">등록된 판매게시물이 없습니다.</td>
	        </tr>
	        <%
	        } else { // 게시물이 있을 때
	            int virtualNum = 0; // 화면 상의 게시물 번호
	            int countNum = 0;
	            for (BoardDTO dtos : dto) {
	                virtualNum = totalCount - (((pageNum - 1) * pageSize) + countNum++);
	        %>
	        <tr class="text-center">
	            <td><%=virtualNum%></td>
	            <td><%=dtos.getBoard_No()%></td>
	            <td><%=dtos.getBoard_Title()%></td>
	            <td><%=dtos.getBoard_Date()%></td>
	            <td><%=dtos.getBook_Id()%></td>
	            <td>
	                <button class="btn btn-primary" style="background-color: rgb(34, 141, 212);">
	                    <a href="BoardUpdate.jsp?board_id=<%=dtos.getBoard_No()%>" style="text-decoration: none; color: white; ">수정</a>
	                </button>
	                <button class="btn btn-danger" onclick="confirmDelete('<%=dtos.getBoard_No()%>')" style="background-color: rgb(230, 71, 67);">삭제</button>
	            </td>
	        </tr>
	        <%
	        }
	        }
	        %>
	        </tbody>
	    </table>
	    <nav aria-label="Page navigation example">
	        <ul class="pagination justify-content-center">
	            <%=BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI())%>
	        </ul>
	    </nav>
	</div>
	<br><br>
	<!-- Footer-->
	<%@ include file="../Footer.jsp"%>

</body>
</html>