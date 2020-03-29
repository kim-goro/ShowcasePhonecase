<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%--
	Task 		: Top navigation before login 
	Description	: MVC Modeling - Product Ordering System
 --%>
 
 
<!DOCTYPE html>
<html>
<nav  class="navbar navbar-default navbar-fixed-top">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="Index.jsp">쇼케이스 폰케이스</a>
	    </div>
 		<ul class="nav navbar-nav">
	      <li><a href="ProdListCon.do?category=Slim">슬림 케이스</a></li>
	      <li><a href="ProdListCon.do?category=Card">카드 케이스</a></li>
	      <li><a href="ProdListCon.do?category=Battery">배터리 케이스</a></li>
	    </ul>
		<ul class="nav navbar-nav navbar-right">
	      <li><a href="RegisterForm.jsp"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
	      <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li> 
	    </ul>
	  </div>
	</nav>
</html>