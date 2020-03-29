<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--
	Product Ordering System 
	index page -> forward page
 --%>


	<%
		//Session check
	HttpSession session2 = request.getSession();
	synchronized (session2) {
		String userType = (String) session2.getAttribute("userType");
		if (session.getAttribute("customer") == null && session.getAttribute("employee") == null) {
	%>
	<jsp:forward page="Login.jsp" />
	<%
		} else {
		if (userType.equals("employee")) {
	%>
	<jsp:forward page="LoginEmpProd.jsp" />
	<%
		} else if (userType.equals("customer")) {
	%>
	<jsp:forward page="LoginCustProd.jsp" />
	<%
		}
	}
	}
	%>

</body>
</html>