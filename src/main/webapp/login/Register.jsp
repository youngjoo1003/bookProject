<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<link href="../css/login.css" rel="stylesheet" />
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
	function validateForm(form) {
		var user_id = form.user_id.value.trim();
		var user_pw = form.user_pw.value.trim();
		var user_name = form.user_name.value.trim();
		var user_email = form.user_email.value.trim();
		var user_tel = form.user_tel.value.trim();
		var user_address = form.user_address.value.trim();

		if (!user_id) { // 아이디
			alert("아이디를 입력하세요.");
			form.user_id.focus();
			return false;
		} else if (user_id.length > 20) {
			alert("아이디는 20글자를 넘길 수 없습니다.")
			form.user_id.focus();
			return false;
		} else if (!/^[a-zA-Z0-9_]+$/.test(user_id)) {
			alert("아이디는 영어, 숫자, _ 만 사용할 수 있습니다.")
			form.user_id.focus();
			return false;
		} // 아이디
	
		if (!user_pw) { // 비밀번호
			alert("비밀번호를 입력하세요.");
			form.user_pw.focus();
			return false;
		} else if (user_pw.length > 20) {
			alert("비밀번호는 20글자를 넘길 수 없습니다.")
			form.user_pw.focus();
			return false;
		} else if (!/^[a-zA-Z0-9_]+$/.test(user_pw)) {
			alert("비밀번호는 영어, 숫자, _ 만 사용할 수 있습니다.")
			form.user_pw.focus();
			return false;
		}// 비밀번호
	
		if (!user_name) { // 이름
			alert("이름을 입력하세요.");
			form.user_name.focus();
			return false;
		} else if (user_name.length > 5) {
			alert("이름은 5글자를 넘길 수 없습니다.")
			form.user_name.focus();
			return false;
		} else if (!/^[가-힣]+$/.test(user_name)) {
			alert("이름은 한글로만 입력해주세요");
			form.user_name.focus();
			return false;
		} // 이름
	
		if (!user_email) { // 이메일
			alert("이메일을 입력하세요.");
			form.user_email.focus();
			return false;
		} else if (user_email.length > 30) {
			alert("이메일은 30글자를 넘길 수 없습니다.")
			form.user_email.focus();
			return false;
		} else if (!/\.(com|net)$/.test(user_email)) {
			alert("올바른 이메일 형식이 아닙니다.");
			form.user_email.focus();
			return false;
		} // 이메일
	
		if (!user_tel || !/^\d{3}-\d{4}-\d{4}$/.test(user_tel)) { // 전화번호
			alert("올바른 전화번호 형식이 아닙니다. (010-1234-5678)");
			form.user_tel.focus();
			return false;
		} // 전화번호
	
		if (!user_address) { // 주소
			alert("주소를 입력하세요.");
			form.user_address.focus();
			return false;
		} else if (user_address.length > 25) {
			alert("주소는 25글자를 넘길 수 없습니다.")
			form.user_address.focus();
			return false;
		}// 주소
	
		return true;
	}
	
</script>
</head>
<body>

	<!-- Header-->
	<%@ include file="../Header.jsp"%>
	
	<div class="container">
		<div class="login-box" style="width: 600px;">
	        <div id="loginBoxTitle" style="text-align: center;">회원가입이 필요합니다</div>
	        <form method="post" action="../register.ez" onsubmit="return validateForm(this);">
	            <div class="mb-3">
	                <label for="userId" class="form-label">아이디</label>
	                <input type="text" class="form-control" id="user_id" name="user_id" required>
	            </div>
	            <div class="mb-3">
	                <label for="password" class="form-label">비밀번호</label>
	                <input type="password" class="form-control" id="user_pw" name="user_pw" required>
	            </div>
	            <div class="mb-3">
	                <label for="name" class="form-label">이름</label>
	                <input type="text" class="form-control" id="user_name" name="user_name" required>
	            </div>
	            <div class="mb-3">
	                <label for="email" class="form-label">이메일</label>
	                <input type="email" class="form-control" id="user_email" name="user_email" required>
	            </div>
	            <div class="mb-3">
	                <label for="phone" class="form-label">전화번호</label>
	                <input type="text" class="form-control" id="user_tel" name="user_tel" required>
	            </div>
	            <div class="mb-3">
	                <label for="address" class="form-label">주소</label>
	                <input type="text" class="form-control" id="user_address" name="user_address" required>
	            </div>
	            <div class="d-flex justify-content-end">
               		<button type="submit" class="btn btn-primary">가입하기</button>
            	</div>
	        </form>
	    </div>
	</div>
	
	<!-- Footer-->
	<%@ include file="../Footer.jsp"%>

</body>
</html>