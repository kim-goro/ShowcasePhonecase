<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%--
	Task 		: Customers Register Form to edit their personal info    
	Description	: Product Ordering System
	DB 			: 'Customers'
 --%>
     
<!DOCTYPE html>
<html>
<head>
  <title> 쇼케이스 폰케이스 </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body { padding-top: 70px; }
.itemTitle{ color:#4C4C4C; size:14px }
</style>  
</head>

<body>
<div class="container-fluid">
 <jsp:include page="/TopNav.jsp" />
 <div class="container"> 
	<c:choose>
		<c:when test="${empty sessionScope.customer.customerId }"> 
			<jsp:forward page="Login.jsp" />
		</c:when>
		<c:otherwise> 
			<!--  Login Form  --> 
			  <h2>${sessionScope.customer.userName} </h2>
				<br> 
			  <form class="form-inline"  method="post" name="RegistForm" action="RegisterModiCon.do" >
			    <div class="form-group">
			      <label for="focusedInput">이메일 : ${sessionScope.customer.customerId} </label><br><br>
			   </div>  
			   <br> 
			 	<div class="form-group">
			      <label for="focusedInput">주소 : </label><br>
			      <input class="form-control" type="text" id="address" size="100" value="${sessionScope.customer.address }" name="address">
			   </div>
			   <br> 
			   <div class="form-group">
			      <label for="focusedInput">우편번호: </label><br>
			      <input class="form-control" type="text" id="postalCode" size="10" value="${sessionScope.customer.postalCode }" name="postalCode">
			   </div>
			   <br> 
			   <br><br>
			    <button type="submit" class="btn btn-primary active" >완료</button>
			  </form>
		</c:otherwise>
	</c:choose> 
	</div> 
	</div> 
</body>
</html>

