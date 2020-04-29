<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Employee"%>
<%-- Task : Welcome
Page after login for Employee Description : Product Ordering System --%>

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
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/TopNav.jsp" />
		<div class="container">
			<div class="jumbotron">
				<%
					//Session 
				HttpSession sessionEmployee = request.getSession();
				try {
					Employee employee = (Employee) sessionEmployee.getAttribute("employee");
				%>
				<h2><%=employee.getUserName()%>님 반갑습니다!
				</h2>
				<br /> 고객주문과 제품을 관리할 수 있습니다.
				<%
					} catch (Exception e) {
				%>
				입력한 이메일과 비밀번호가 일치하지 않습니다.
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>
