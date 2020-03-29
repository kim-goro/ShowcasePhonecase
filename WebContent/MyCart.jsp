<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--
	Task 		: My Cart for customer   
	Description	: Product Ordering System
 --%>

<!DOCTYPE html>
<html>
<head>
<title> 쇼케이스 폰케이스 </title>
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
	// delete items from cart
	function delCart(itemId) {
		document.cartForm.itemId.value = itemId;
		document.cartForm.action = "DelCartCon.do";
		document.cartForm.submit();
	}
	// submit form to checkout
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
				<h2>
					<span class="label label-default"><font size="3">장바구니</font></span>
				</h2>
				<br>
				<ul class="list-group">
					<!--  No List Check-->
					<c:choose>
						<c:when test="${!empty cartList }">
							<!--  Cart List Start  : forEach -->
							<c:set var="total" value="${0}" />
							<c:forEach var="cart" items="${requestScope.cartList}" begin="0"
								step="1" varStatus="status">
								<!-- total price for all items -->
								<c:set var="total" value="${total + cart.price}" />
								<li class="list-group-item">
									<c:if test="${status.end == 0}">
								  		장바구니가 비었습니다.
								  	</c:if>
									<table>
										<tr>
											<td width="100px">${status.count}&nbsp;<img
												src="img/product${cart.itemId}.jpg" width="50px"
												class="img-thumbnail" alt="Showcase Phonecase">&nbsp;&nbsp;
											</td>
											<td width="450px" class="itemTitle"><h3>${cart.itemName}</h3></td>
											<td width="250px" align="right" width="100px">호환기기 : ${cart.device}</td>
											<td width="100px" align="right" width="50px">수량 : ${cart.quantity}</td>
											<td align="right" width="100px"><b><fmt:formatNumber value="${cart.price }" type="currency" /></b></td>
											<td align="right" width="50px"><a
												href="javascript:delCart('${cart.itemId}');"><img
													src="img/delete.jpg" width="30px"></a></td>
										</tr>
									</table></li>
							</c:forEach>
							<li class="list-group-item">
								<table>
									<tr>
										<td align="right" width="800px" align="right">
											<h3>
												최종 금액 :
												<fmt:formatNumber value="${total }" type="currency" />
											</h3>
										</td>
										<td align="right" width="200px"><button type="submit"
												class="btn btn-primary active"
												onclick="javascript:location.href='CheckoutCon.do'">결제하기</button>
										</td>
									</tr>
								</table>
							</li>
						</c:when>
						<c:otherwise>
							<h3>품목이 비었습니다.</h3>
							<br>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<!-- form for cart -->
		<form action="DelCartCon.do" method="post" name="cartForm">
			<input type="hidden" value="" name="itemId"> <input
				type="hidden" value="${sessionScope.customer.customerId }"
				name="customerId">
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>