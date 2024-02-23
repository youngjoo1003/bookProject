<%@page import="dto.BoardDTO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Book Shopping mall</title>
<!-- Bootstrap icons--> <!-- 별모양아이콘 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- 초기화면 자바코드 -->
<% 
	BoardDAO dao = new BoardDAO();
	List<BoardDTO> dto = dao.showBookListMain();
	dao.close();
%>
</head>
<body>
	<!-- navi, Header-->
	<jsp:include page="Header.jsp" />

	<!-- Section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<%
                	for (BoardDTO dtos : dto) {
        		%>
				<!-- 책 블럭 -->
				<div class="col mb-5">
					<div class="card h-100">
						<!-- 이미지-->
						<img class="card-img-top" src="<%= dtos.getBoard_Img() %>"
							style="width: auto; height: 340px;" alt="..." />
						<!-- Product details-->
						<div class="card-body p-4">
							<div class="text-center">
								<!-- 이름-->
								<h5 class="fw-bolder"><%= dtos.getBoard_Title() %></h5>
								<!-- 별점-->
								<div
									class="d-flex justify-content-center small text-warning mb-2">
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
									<div class="bi-star-fill"></div>
								</div>
								<!-- 가격-->
								<%= dtos.getBook_Price() %>원
							</div>
						</div>
						<!-- 페이지이동-->
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<!-- <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">상세 보기</a></div> -->
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" href="./detail.do?book_id=<%=dtos.getBook_Id()%>">상세 보기</a>
							</div>
						</div>
					</div>
				</div>
				<!-- 책 블럭 종료 -->
				<%
        			}
                %>

			</div>
		</div>
	</section>

	<!-- Footer-->
	<%@ include file="Footer.jsp"%>
	

</body>
</html>