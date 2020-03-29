<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
	Task 		: Ordered items list for customer  
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

.itemTitle {
	color: #4C4C4C;
	size: 14px
}
</style>
<script>
	function delCart(itemId) {
		document.cartForm.itemId.value = itemId;
		document.cartForm.submit();
	}
	function checkOut() {
		document.cartForm.submit();
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<jsp:include page="/TopNav.jsp" />
		<div class="container">
			<div class="jumbotron">
				<!--  Menu title  -->
				<h2>${sessionScope.customer.userName},</h2>
				<h3>주문이 완료되었습니다. 마이페이지에서 확인할 수 있습니다.</h3>
				<br>
				<ul class="list-group">
					<!-- Order List Start -->
					<c:set var="total" value="${0}" />
					<c:forEach var="order" items="${requestScope.orderList}"
						begin="0" step="1" varStatus="status">
						<!-- total price for all items -->
						<c:set var="total" value="${total + order.price}" />
						<li class="list-group-item">
							<table>
								<tr>
									<td width="100px">${status.count}&nbsp;<img
										src="img/product${order.itemId}.jpg" width="50px"
										class="img-thumbnail" alt="Showcase Phonecase">&nbsp;&nbsp;
									</td>
									<td width="450px" class="itemTitle"><h3>${order.itemName}</h3></td>
									<td width="250px" align="right" width="70px">호환기기 : ${order.device}</td>
									<td width="100px" align="right" width="50px">수량 : ${order.quantity}</td>
									<td align="right" width="100px"><b>${order.price}원</b></td>
								</tr>
							</table>
						</li>
					</c:forEach>
					<li class="list-group-item">
						<table>
							<tr>
								<td align="right" width="800px" align="right">
									<h3>최종 금액 : ${total}원</h3>
								</td>
							</tr>
						</table>
					</li>
				</ul>
				<!-- Customer's delivery address information  -->
				<ul class="list-group">
					<li class="list-group-item"><b>배송지 :</b>
						${sessionScope.customer.address} <br>  <b>우편번호 :</b>
						${sessionScope.customer.postalCode} <br> <br></li>
				</ul>
			</div>
		</div>
		<!-- form for cart -->
		<form action="DelCartCon.do" method="post" name="cartForm">
			<input type="hidden" value="" name="itemId"> <input
				type="hidden" value="delete" name="mode"> <input
				type="hidden" value="${sessionScope.customer.customerId}"
				name="customerId">
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>