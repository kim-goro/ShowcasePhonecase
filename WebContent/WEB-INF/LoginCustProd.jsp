<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Customer"%>
<%-- *
--------------------------------------------- * MVC Modeling - Product Ordering
System * 최근 수정일 : 2020-03-27 *
--------------------------------------------- * * Page Task : Welcome Page after
login for customer * --%>
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
		<!--  INCLUDE : TOP MENU NAVIGATION  -->
		<jsp:include page="/TopNav.jsp" />

		<div class="container">
			<div class="jumbotron" style="background-color: #D5D5D5;">
				<%
					//Session 
				HttpSession sessionCustomer = request.getSession();
				try {
					Customer customer = (Customer) sessionCustomer.getAttribute("customer");
				%>
				<h2>
					<%=customer.getUserName()%>님 쇼케이스 폰케이스에 오신 것을 환영합니다!
				</h2>
				<br /> <br />
				<h3>
					첫 고객 무료배송! <br /> <font color="red">new 슈퍼슬림 케이스 XR/XM </font> 20%
					파격세일 <br />
				</h3>
				<%
					} catch (Exception e) {
				%>
				<jsp:forward page="Login.jsp" />
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
