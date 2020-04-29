<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--
	Task 		: login form 
 				  customer / employee 
	Description	: Product Ordering System
 --%>

<!DOCTYPE html>
<html>
<head>
<title>쇼케이스 폰케이스</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {
	padding-top: 70px;
}
</style>
<script>
	// show modal to validate null value
	function MsgLoginValid() {
		// validate null value 
		if (document.getElementById("email").value == ""
				|| document.getElementById("pwd").value == "") {
			$('#myModal').modal('show');
			return;
		} else {
			// submit form  
			var form = document.LoginForm;
			if (form.userType[0].checked) {
				form.action = "LoginEmpCon.do";
			} else {
				form.action = "LoginCon.do";
			}
			form.submit();
		}
	}
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})
</script>
</head>

<body
	style="background-image: url(img/main.jpg); background-size: 1370px;">
	<div class="container-fluid">
		<jsp:include page="/TopNavLogin.jsp" />
		<div class="container">
			<!--  Login Form  -->
			<h2>로그인하기</h2>
			<%
				// Fail to login
			try {
				String getMsg = (String) request.getAttribute("loginMsg");

				if (getMsg.equals("fail")) {
			%>
			<div class='col-sm-3 col-md-6 col-lg-4' style='color: red;'>
				입력한 이메일과 비밀번호가 일치하지 않습니다.<br>
			</div>
			<%
				}
			} catch (Exception e) {

			}
			%>
			<br>
			<form class="form-inline" method="post" name="LoginForm" action=""
				onSubmit="return false;">
				<div class="form-group">
					<label for="focusedInput">이메일 :</label><br> <input
						class="form-control" type="email" id="email" size="50"
						placeholder="Enter email" name="email">
				</div>
				<br>
				<div class="form-group">
					<label for="pwd">비밀먼호 :</label><br>
					<input type="password" class="form-control" id="pwd" size="50"
						placeholder="Enter password" name="pwd">
				</div>
				<br> <br>
				<div class="checkbox">
					<label><input type="radio" name="userType" id="userType"
						value="employee">판매자</label> <label><input
						type="radio" name="userType" id="userType" checked value="user">일반 고객</label>
				</div>
				<br> <br>
				<button type="submit" class="btn btn-primary active"
					onclick="javascript:MsgLoginValid();">로그인
				</button>
				<button type="submit" class="btn btn-default active"
					onclick="javascript:location.href='RegisterForm.jsp';">회원가입</button>
			</form>
		</div>
		<!-- the modal for Login Msg-->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">로그인</h4>
					</div>
					<div class="modal-body">
						<p>이메일과 비밀번호를 입력해주십시요.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
