<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Employee"%>
 
 <%--
	Task 		: Top navigation for customers     
	Description	: Product Ordering System
 --%>
 
 
<!DOCTYPE html>
<html><!--  TOP MENU : After Login -->
	 <nav class="navbar navbar-default navbar-fixed-top"> 
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="Index.jsp">쇼케이스 폰케이스</a>
	    </div>
 		<ul class="nav navbar-nav">
	      <li><a href="ProdListCon.do?">상품리스트</a></li>
	      <li><a href="ProdListCon.do?category=Slim">슬림 케이스</a></li>
	      <li><a href="ProdListCon.do?category=Card">카드 케이스</a></li>
	      <li><a href="ProdListCon.do?category=Battery">배터리 케이스</a></li>
	    </ul>
		<ul class="nav navbar-nav navbar-right">
		<%
		HttpSession sessionEmployee = request.getSession();
		Employee employee = (Employee) sessionEmployee.getAttribute("employee");
			if (employee == null) { %>
		  <li><a href="MyCartCon.do"><span class="glyphicon glyphicon-shopping-cart"></span> 장바구니 </a></li>
	      <li><a href="MyPageCon.do"><span class="glyphicon glyphicon-user"></span> 마이페이지 </a></li> 
	      <% }else{%>
	      <li><a href="EmpProdForm.jsp">상품등록</a></li>
	      <% }%>
	      <li><a href="LogoutCon.do"><span class="glyphicon glyphicon-log-out"></span> 로그아웃 </a></li>
	    </ul>
	  </div>
	</nav>
</html>
