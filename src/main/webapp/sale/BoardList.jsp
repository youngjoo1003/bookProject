<%@page import="utils.BoardPage"%>
<%@page import="dto.BookDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 초기화면 자바코드 -->
<%
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>책 판매 리스트 페이지</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Custom styles -->
    <style>
        /* Custom CSS here */
        .product-card {
            border: none;
            border-radius: 15px;
            transition: all 0.3s ease;
        }

        .product-card:hover {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transform: translateY(-5px);
        }

        .product-img {
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
            object-fit: cover;
            object-position: center; 
        }

        .product-title {
            font-size: 1rem;
            font-weight: bold;
            text-align: center;
        }

        .product-price {
            font-size: 1rem;
            color: #007bff;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <%@ include file="../Header.jsp" %>

	<br><br><h2 class="text-center" style="font-weight: bold; color: gray;">도서 목록</h2><hr>
    <!-- Product Section -->
    <section class="py-5">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
                <% 
                    for (BoardDTO dtos : dto) {
                %>
                <!-- Product Card -->
                <div class="col" style="width: 260px; height: 450px;">
                    <div class="card product-card mb-4">
                        <!-- Product Image -->
                        <img src="../<%= dtos.getBoard_Img() %>" class="card-img-top mx-auto d-block product-img" alt="..." style="width: 220px; height: 300px;">
                        <div class="card-body">
                            <!-- Product Title -->
                            <h5 class="card-title product-title"><%= dtos.getBoard_Title() %></h5>
                            <!-- Product Price -->
                            <p class="card-text product-price"><%= dtos.getBook_Price() %>원</p>
                            <!-- Detail Button -->
                            <div class="text-center">
                            	<a href="../detail.do?book_id=<%=dtos.getBook_Id()%>" class="btn btn-primary">상세 보기</a>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
                <!-- End Product Card -->
            </div>
        </div>
    </section>
    
    <div class="container">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <%=BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI())%>
            </ul>
        </nav>
    </div></a>
    
    <!-- Footer -->
    <%@ include file="../Footer.jsp" %>

    
</body>
</html>