<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="../css/login.css" rel="stylesheet" />

<script>
	function validateForm(form) {
		var user_id = form.user_id.value.trim();
		var user_pw = form.user_pw.value.trim();
		
		if (!user_id) {
			alert("아이디를 입력하세요.")
			form.user_id.focus();
			return false;
		}
		if (!user_pw){
			alert("비밀번호를 입력하세요.")
			form.user_pw.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<!-- Header-->
	<%@ include file="../Header.jsp"%>

	<div id="container">
		<!-- login Box -->
		<div class="login-box" style="width: 600px;">
			<div id="loginBoxTitle" style="text-align: center;">로그인이 필요합니다</div>
			<form action="../login.ez" method="post" onsubmit="return validateForm(this);"> 
				<div class="form-group">
					<label>고객 아이디</label> 
					<input type="text" name="user_id" id="user_id" class="form-control" required>
				</div>
				<div class="form-group">
					<label>비밀번호</label> 
					<input type="password" name="user_pw" id="user_pw" class="form-control" autocomplete="off" required>
				</div>
				<div id="login-btn-box">
					<div style="">
						<span> 아이디 저장</span> <input type="checkbox" id="workSite" name="worksite" style="margin-bottom: 5px">
					</div>
					<div>
						<input type="submit" id="btnLogin" value=" 로그인 " class="btn btn-danger">
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Footer-->
	<%@ include file="../Footer.jsp"%>

	

</body>
</html>