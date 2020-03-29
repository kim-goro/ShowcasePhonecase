<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Task : Customer Register Form
Description : Product Ordering System DB : 'Customer' --%>

<!DOCTYPE html>
<html>
<head>
<title>쇼케이스 폰케이스</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	padding-top: 70px;
}

.itemTitle {
	color: #4c4c4c;
	size: 14px;
}
</style>
<script>
	function openUser(option) {
		document.getElementById("custInfo").style.display = option;
	}
	function MsgLoginValid() {
		var form = document.RegistForm;
		// validate null value
		if (document.getElementById("email").value == ""
				|| document.getElementById("pwd").value == ""
				|| document.getElementById("userName").value == "") {
			$("#myModal").modal("show");
			return;
		} else {
			form.action = "RegisterCon.do";
			form.submit();
		}
	}
	$("#myModal").on("shown.bs.modal", function() {
		$("#myInput").trigger("focus");
	});
</script>
</head>

<body
	style="background-image: url(img/main.jpg); background-size: 1370px;">
	<div class="container-fluid">
		<jsp:include page="/TopNavLogin.jsp" />
		<div class="container">
			<!--  Login Form  -->
			<h2>회원가입</h2>
			<%
				// Msg from Controller : Fail to Register 
			try {
				String getMsg = (String) request.getAttribute("Msg");
				if (getMsg.equals("email")) {
			%>
			<div class="col-sm-3 col-md-6 col-lg-4" style="color: red;">
				이미 존재하는 이메일입니다.<br />
			</div>
			<%
				} else if (getMsg.equals("fail")) {
			%>
			<div class="col-sm-3 col-md-6 col-lg-4" style="color: red;">
				회원가입에 실패했습니다. <br />
			</div>
			<%
				}
			} catch (Exception e) {
			}
			%>
			<br />
			<form class="form-inline" method="post" name="RegistForm" action=""
				onSubmit="return false;">
				<div class="checkbox">
					<label><input type="radio" name="category"
						id="category" value="Customer"
						onClick="javascript:openUser('block');" checked />
						일반 고객</label> &nbsp;&nbsp; <label><input
						type="radio" name="category" id="category" value="Employee"
						onClick="javascript:openUser('none');" /> 판매자 </label>
				</div>
				<br /> <br />

				<div class="form-group">
					<label for="focusedInput">이메일 :</label><br /> <input
						class="form-control" type="email" id="email" size="50"
						placeholder="Enter email" name="email" maxlength="50" />
					<i><font color="red">* 필수사항 입니다.</font></i>
				</div>
				<br />
				<div class="form-group">
					<label for="pwd">비밀번호 :</label><br /> <input type="password"
						class="form-control" id="pwd" size="50"
						placeholder="Enter password" name="pwd" maxlength="30" />
					<i><font color="red">* 필수사항 입니다.</font></i>
				</div>
				<br />
				<div class="form-group">
					<label for="focusedInput"> 이름 :</label><br /> <input
						class="form-control" type="text" id="userName" size="50"
						placeholder="Enter your name" name="userName" maxlength="30" />
				</div>
				<br />
				<!--  Customer address infomation -->
				<div id="custInfo">
					<div class="form-group">
						<label for="focusedInput">주소 :</label><br /> <input
							class="form-control" type="text" id="address" size="100"
							placeholder="Enter your address" name="address" maxlength="90" />
					</div>
					<br />
					<div class="form-group">
						<label for="focusedInput">우편번호 :</label><br /> <input
							class="form-control" type="text" id="postalCode" size="10"
							placeholder="Postal code" name="postalCode" maxlength="6" />
					</div>
					<br />
				</div>
				<br /> <br />
				<button type="submit" class="btn btn-primary active"
					onclick="javascript:MsgLoginValid();">완료</button>
			</form>
		</div>
		<!-- the modal for Login Msg-->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							&times;</button>
						<h4 class="modal-title">가입하기</h4>
					</div>
					<div class="modal-body">
						<p>모든 사항을 입력해주세요.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
