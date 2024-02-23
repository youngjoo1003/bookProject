<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #DEE2E6;">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand fw-bolder" href="${pageContext.request.contextPath}/Main.jsp" style="color: #666666;">꿈꾸는 도서관</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-left: auto; ">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4 ml-auto">
					&nbsp;&nbsp;&nbsp;
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/Main.jsp">Home</a></li> &nbsp;&nbsp;&nbsp;
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sale/BoardList.jsp">도서 목록보기</a></li>
					&nbsp;&nbsp;&nbsp;
					<% if (session.getAttribute("UserManager") != null && session.getAttribute("UserManager").equals(1)) { %>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">도서 관리</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/bookclub/AddBook.jsp">도서 정보 등록</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/bookclub/SelectBook_Edit.jsp">도서 정보 관리</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/sale/BoardWrite.jsp">판매 게시글 등록</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/sale/BoardEditList.jsp">판매 게시글 관리</a></li>
						</ul></li> &nbsp;&nbsp;&nbsp;
					<% } %>
					<% if (session.getAttribute("UserId") != null) { %> 
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/bookMarket/Orders.jsp">주문내역</a> 
					</li>
					&nbsp;&nbsp;&nbsp;
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/login/Logout.jsp">로그아웃</a>
						<% } else { %> <a class="nav-link" href="${pageContext.request.contextPath}/login/Login.jsp">로그인</a> 
					</li>
					&nbsp;&nbsp;&nbsp;
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/login/Register.jsp">회원가입</a> 
					</li>
					 <% } %>
				</ul>
			</div>
		</div>
	</nav>
	<header class="py-5" style="background-image: url('${pageContext.request.contextPath}/images/메인.jpg'); height: 200px; background-size: cover; background-position: center;">
	    <div class="text-center text-white">
	         <h1 class="display-4 fw-bolder" style="text-shadow: 
            -2px -2px 0 #555555, 2px -2px 0 #555555, -2px 2px 0 #555555, 2px 2px 0 #555555;">꿈꾸는 도서관</h1>
	        <p class="lead fw-normal text-white mb-0">Book Shopping mall</p>
	    </div>
	</header>
