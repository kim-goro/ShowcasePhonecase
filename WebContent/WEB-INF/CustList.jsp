<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<%--
	Task 		: 1. show customer list
 			  	  2. modify customer info
 				  3. delete customer  
	Description	: Product Ordering System
	PageTask 	: Employee -> Customer Information management
	DB 			: 'Product'
 --%>

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

.itemTitle {
	color: #4C4C4C;
	size: 14px
}
</style>
<script>
	// Form데이터를 change info로
	function modifyInfo(customerId, mode) {
		var form = document.cartForm;
		form.customerId.value = customerId;
		form.mode.value = mode;
		form.submit();
	}

	// Form데이터를 delete order로
	function delOrder(orderId) {
		var form = document.cartForm;
		form.orderId.value = orderId;
		form.mode.value = "del";
		form.submit();
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<jsp:include page="/TopNav.jsp" />
		<div class="container">
			<h1>고객 리스트</h1>
			<br>
			<c:choose>
			<!-- 등록된 고객이 있을 떄 -->
				<c:when test="${!empty customerList }">
					<!-- ■■■■■■■■■■■■■■■■■■■■■ 고객정보, Order List 출력하기 ■■■■■■■■■■■■■■■■■■■■■-->
					<c:set var="total" value="${0}" />
					<table class="table">
						<thead>
							<tr class="active">
								<th>번호</th>
								<th>고객명</th>
								<th>고객 Id</th>
								<th>주소</th>
								<th>우편번호</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="customerList"
								items="${requestScope.customerList}" begin="0" step="1"
								varStatus="status">
								<tr>
									<td>${status.count}&nbsp;</td>
									<td>${customerList.userName}</td>
									<td>${customerList.customerId}</td>
									<td>${customerList.address}</td>
									<td>${customerList.postalCode}</td>
									<td class="active">
										<button type="button" class="btn btn-warning btn-sm"
											onClick="javascript:modifyInfo('${customerList.customerId}', 'search');">Edit</button>
										<button type="button" class="btn btn-danger btn-sm"
											onClick="javascript:modifyInfo('${customerList.customerId}', 'del');">delete</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- End ■■■■■■■■■■■■■■■■■■■■■ 고객정보, Order List 출력하기 ■■■■■■■■■■■■■■■■■■■■■-->
				<!-- 등록된 고객이 없을 떄 -->
				</c:when>
				<c:otherwise>
					<h3>고객이 없습니다.</h3>
					<br>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- Form을 edit, delete로 -->
		<form action="EmpCustListCon.do" method="post" name="cartForm">
			<input type="hidden" value="" name="customerId"> <input
				type="hidden" value="" name="mode">
		</form>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
