<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
Task : Customer Register Description : Product Ordering System DB : 'Customer'
--%>

<!DOCTYPE html>
<html>
<head>
<title>쇼케이스 폰케이스</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<style>
body {
	padding-top: 70px;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<jsp:include page="/TopNav.jsp" />
		<div class="container">
			<div class="jumbotron">
				<%
					//Session 
				HttpSession sessionCustomer = request.getSession();
				try {
					Customer customer = (Customer) sessionCustomer.getAttribute("customer");
				%>
				<h2>
					<%=customer.getUserName()%>님 반갑습니다!
				</h2>
				<br />
				<h4>쇼케이스 폰케이스에 가입해주셔서 감사합니다!</h4>
				<%
					} catch (Exception e) {
				%>
				일치하는 이메일 또는 비밀번호가 없습니다.
				<%
					}
				%>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
