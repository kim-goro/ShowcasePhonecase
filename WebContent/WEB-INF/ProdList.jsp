<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.Employee"%>

<%--
	Task 		: product List    
	Description	: Product Ordering System
	DB 			: 'Product'
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
	// show modal window to confirm to add Product to their cart
	function addCart(itemId, itemName) {
		document.getElementById('modal_item_title').innerHTML = itemName;
		document.cartForm.itemId.value = itemId;
		$('#myModal').modal('show');
		return;
	}
	// close modal and then form submit
	function submitCart() {
		$('#myModal').modal('toggle');
		document.cartForm.submit();
	}
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})
	// form을 edit info로
	function moveEditPage(itemId, mode) {
		var form = document.listForm;
		form.mode.value = mode;
		form.itemId.value = itemId;
		form.submit();
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<%
			HttpSession session2 = request.getSession();
			synchronized (session2) { 
				String userType = (String) session2.getAttribute("userType");
				if (session.getAttribute("customer") == null && session.getAttribute("employee") == null) {
					%> <jsp:include page="/TopNavLogin.jsp" /><%
				}else{
					%> <jsp:include page="/TopNav.jsp" /> <%
				}
			}
		%>
		<div class="container">
			<div class="jumbotron">
				<!--  Menu title  -->
				<c:if test="${!empty category }">
					<h3>
						<span class="label label-default"><font size="3">${category}</font></span>
					</h3>
					<br>
				</c:if>
				<ul class="list-group">
					<!--  No List -->
					<c:if test="${empty productList }">
						<h3>등록된 제품이 없습니다.</h3>
						<br>
					</c:if>
					<!-- /////////////    products List Start  : forEach  /////////////// -->
					<c:forEach var="product" items="${requestScope.productList}"
						begin="0" step="1" varStatus="status">
						<li class="list-group-item"><c:if test="${status.end == 0}">
	  	등록된 제품이 없습니다.
	  	</c:if>
							<table>
								<tr>
									<td></td>
									<td colspan="2" class="itemTitle"><h3>${product.itemName}</h3></td>
									<td align="right" width="150px"><span class="badge">${product.price}원</span>
										<!-- show cart image when quantity is bigger than 0   --> <c:if
											test="${product.quantity > 0}">
											<a
												href="javascript:addCart('${product.itemId}','${product.itemName}');"><img
												src="img/cart.png" width="30px"></a>
										</c:if></td>
								</tr>
								<tr>
									<td></td>
									<td width="180px"><img
										src="img/product${product.itemId}.jpg" width="150px"
										class="img-thumbnail" alt="Showcase Phoncase"></td>
									<td width="550">
										<h4> 호환 기종 : <b>${product.device}</b></h4> 
										<br> <i>${product.content} </i>
									</td>
									<%
										HttpSession sessionEmployee = request.getSession();
									Employee employee = (Employee) sessionEmployee.getAttribute("employee");
									if (employee != null) {
									%>
									<td width="120" align="right">
										<button type="button" class="btn btn-primary btn-sm"
											onClick="javascript:moveEditPage('${product.itemId}','search')">수정</button>
										<button class="btn btn-danger btn-sm"
											onClick="javascript:moveEditPage('${product.itemId}','del')">삭제</button>
									</td>
									<%
										}
									%>
								</tr>
							</table></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- the modal to add cart item -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div id="modal_item_title" class="modal-title"></div>
					</div>
					<div class="modal-body">
						<p>장바구니에 추가하시겠습니까?</p>
					</div>
					<!-- form for cart -->
					<form action="CartCon.do" method="post" name="cartForm">
						<input type="hidden" name="category" value="${category}">
						<input type="hidden" value="" name="itemId"> <input
							type="hidden" value="${sessionScope.customer.customerId}"
							name="customerId">
					</form>
					<div class="modal-footer">
						<!-- data-dismiss="modal"  -->
						<button type="button" class="btn btn-default"
							onClick="javascript:submitCart();">장바구니에 담기</button>
					</div>
				</div>
			</div>
			<form action="EmpProdCon.do" method="post" name="listForm">
				<input type="hidden" value="" name="mode"> <input
					type="hidden" value="" name="itemId"> <input type="hidden"
					value="${requestScope.category}" name="category">
			</form>
		</div>
		<!-- END : the modal to add cart item -->
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

