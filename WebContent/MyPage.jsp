<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--
	Task 		: My page for customer   
	  			  1. show personal information to edit
 				  2. show ordered status 
	Description	: Product Ordering System
 --%>

<!DOCTYPE html>
<html>
<head>
<title>쇼케이스 폰케이스</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	// move form to add quantity
	function modifyOrder(orderId, quantity, mode) {
		var form = document.cartForm;
		form.orderId.value = orderId;
		form.quantity.value = quantity;
		form.mode.value = mode;
		form.submit();
	}

	// move form to delete order
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
			<div class="jumbotron">
				<!--  Menu title  -->
				<h1>${sessionScope.customer.userName}</h1>
				<br>
				<h2>
					<span class="label label-default"><font size="3">주문내역</font></span>
				</h2>
				<br>
				<ul class="list-group">
					<!--  Order List Check-->
					<c:choose>
						<c:when test="${!empty orderList }">
							<!-- /////////////    Order List Start  : forEach  /////////////// -->
							<c:set var="total" value="${0}" />
							<c:forEach var="order" items="${requestScope.orderList}"
								begin="0" step="1" varStatus="status">
								<!-- total price for all items -->
								<c:set var="total" value="${total + order.price}" />
								<li class="list-group-item">
								<c:if test="${status.end == 0}">
							  		최근 주문한 상품이 없습니다.
							  	</c:if>
									<table >
										<tr>
											<td width="100px">${status.count}&nbsp; <img
												src="img/product${order.itemId}.jpg" width="50px"
												class="img-thumbnail" alt="Showcase Phonecase">&nbsp;&nbsp;
											</td>
											<td width="400px" class="itemTitle"><h3>${order.itemName}</h3>
												(호환기기 : ${order.device})</td>
											<td align="right" width="100px"><b><fmt:formatNumber
														value="${order.price }" type="currency" /></b></td>
											<td align="right" width="100px">수량 : ${order.quantity}</td>
											<td align="right" width="100px"><c:if
													test="${order.quantity > 1 && order.orderStatus=='Order Placed'}">
													<button type="button" class="btn btn-warning"
														onClick="javascript:modifyOrder('${order.orderId}','${order.quantity-1}', 'down');">
														-1</button>
												</c:if> <c:if test="${order.orderStatus=='Order Placed' }">
													<button type="button" class="btn btn-warning"
														onClick="javascript:modifyOrder('${order.orderId}', '${order.quantity+1}', 'add');">
														+1</button>
												</c:if></td>
											<td align="right" width="100px"><font color="red">
													${order.orderStatus}</font></td>
											<td align="right" width="100px"><c:if
													test="${order.orderStatus=='Order Placed' }">
													<button type="button" class="btn btn-danger"
														onClick="javascript:delOrder('${order.orderId}');">cancel</button>
												</c:if></td>
										</tr>
									</table></li>
							</c:forEach>
							<!-- /////////////   End  : forEach  /////////////// -->
							<!--  total amount -->
							<li class="list-group-item">
								<table>
									<tr>
										<td align="right" width="800px" align="right">
											<h3>
												최종 품목 :
												<fmt:formatNumber value="${total }" type="currency" />
											</h3>
										</td>
										<td align="right" width="200px"></td>
									</tr>
								</table>
							</li>
						</c:when>
						<c:otherwise>
							<h3>최근 주문내역이 없습니다.</h3>
							<br>
						</c:otherwise>
					</c:choose>
				</ul>
				<!-- Customer's delivery address information  -->
				<h2>
					<span class="label label-default"><font size="3">내 주소</font></span>
				</h2>
				<br>
				<ul class="list-group">
					<li class="list-group-item"><b>배송지 :</b>
						${sessionScope.customer.address} <br> <b>Postal Code :</b>
						${sessionScope.customer.postalCode} <br>
					<br>
						<button type="button" class="btn btn-default"
							onclick="javascript:location.href='RegisterModi.jsp'">수정하기</button></li>
				</ul>
			</div>
		</div>


		<!-- form to edit and delete -->
		<form action="MyPageCon.do" method="post" name="cartForm">
			<input type="hidden" value="" name="orderId"> <input
				type="hidden" value="" name="mode"> <input type="hidden"
				value="" name="quantity">
		</form>


	</div>
</body>
</html>