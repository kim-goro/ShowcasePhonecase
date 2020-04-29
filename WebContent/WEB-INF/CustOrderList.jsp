<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
	Task 		: 1. show order list    
	Description	: Product Ordering System
	PageTask 	: Employee -> Order management
	DB 			: 'Product'
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
	// Form을 change order status로
	function modifyOrder(orderId, orderStatus) {
		var form = document.cartForm;
		form.orderId.value = orderId;
		form.orderStatus.value = orderStatus;
		form.mode.value = "status";
		form.submit();
	}

	// Form과 mode를 옮김  주문량 추가 / 주문 취소 
	function changeOrder(orderId, quantity, mode) {
		var form = document.cartForm;
		form.orderId.value = orderId;
		form.quantity.value = quantity;
		form.mode.value = mode;
		form.submit();
	}

	// Form을 delete order로 
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
			<h1>주문조회</h1>
			<br>
			<ul>
				<c:choose>
				<!--  Order List가 있을 떄-->
					<c:when test="${!empty orderList }">
						<c:set var="total" value="${0}" />
						<table class="table">
							<thead>
								<tr class="active">
									<th>번호</th>
									<th>이름</th>
									<th>고객</th>
									<th>수량</th>
									<th>가격</th>
									<th>상태</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<!-- order List 출력 -->
								<c:forEach var="order" items="${requestScope.orderList}"
									begin="0" step="1" varStatus="status">
									<!-- 최종 금액 -->
									<c:set var="total" value="${total + order.price}" />
									<c:if test="${status.end == 0}">
								  		등록된 데이터가 없습니다.
								  	</c:if>
									<tr>
										<td>${status.count}&nbsp;</td>
										<td>${order.itemName}</td>
										<td>${order.customerId}</td>
										<td>${order.quantity}
										<c:if test="${order.quantity > 1}">
												<button type="button" class="btn btn-primary btn-xs"
													onClick="javascript:changeOrder('${order.orderId}','${order.quantity-1}', 'down');">
													-</button>
											</c:if>
											<button type="button" class="btn btn-primary btn-xs"
												onClick="javascript:changeOrder('${order.orderId}','${order.quantity+1}', 'add');">
												+</button>
										</td>
										<td>$${order.price}</td>
										<td>${order.orderStatus}</td>
										<td class="active">
											<button type="button" class="btn btn-info btn-sm"
												onClick="javascript:modifyOrder('${order.orderId}', 'In-Process');">배송 중</button>
											<button type="button" class="btn btn-warning btn-sm"
												onClick="javascript:modifyOrder('${order.orderId}', 'Delivered');">배송 완료</button>
											<button type="button" class="btn btn-danger btn-sm"
												onClick="javascript:delOrder('${order.orderId}');">삭제</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<!--  Order List가 없을 떄-->
					<c:otherwise>
						<h3>등록된 주문조회가 없습니다.</h3>
						<br>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div>
			<!-- Form을 edit, delete로 -->
			<form action="EmpOrderListCon.do" method="post" name="cartForm">
				<input type="hidden" value="" name="orderId"> <input
					type="hidden" value="" name="mode"> <input type="hidden"
					value="" name="quantity"> <input type="hidden" value=""
					name="orderStatus">
			</form>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
