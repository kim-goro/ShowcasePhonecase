<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
	Description : Product Ordering System
	PageTask : Employee -> Prod Information Form 
	DB : 'Product'
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
	// submit form
	function FormValid() {
		var form = document.RegistForm;
		// validate null value 
		if (document.getElementById("itemName").value == ""
				|| document.getElementById("price").value == ""
				|| document.getElementById("device").value == ""
				|| document.getElementById("quantity").value == "") {
			$('#myModal').modal('show');
			return;
		} else if (form.category[0].checked == false
				&& form.category[1].checked == false
				&& form.category[2].checked == false) {
			$('#myModal').modal('show');
			return;
		} else {
			form.action = "EmpProdCon.do";
			form.submit();
		}
	}
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})
</script>
</head>

<body>

	<div class="container-fluid">
		<jsp:include page="/TopNav.jsp" />
		<div class="container">
			<!--  로그인 Form  -->
			<h2>폰케이스 정보</h2>
			<br>
			<form class="form-inline" method="post" name="RegistForm" action=""
				onSubmit="return false;">
				<!-- new data or existed data to edit -->
				<c:choose>
					<c:when test="${mode == 'edit'}">
						<input type="hidden" name="mode" value="edit">
						<input type="hidden" name="itemId"
							value="${requestScope.product.itemId }">
					</c:when>
					<c:otherwise>
						<input type="hidden" name="mode" value="new">
					</c:otherwise>
				</c:choose>
				<div class="checkbox">
					<label><input type="radio" name="category" id="category"
						value="Slim"
						<c:if test="${requestScope.product.category=='Slim'}"> checked </c:if>>
						슬림 케이스 </label> <label><input type="radio" name="category"
						id="category" value="Card"
						<c:if test="${requestScope.product.category=='Card'}"> checked </c:if>>
						카드 케이스 </label> <label><input type="radio" name="category"
						id="category" value="Battery"
						<c:if test="${requestScope.product.category=='Battery'}"> checked </c:if>>
						배터리 케이스 </label> <i><font color="red">V</font></i>
				</div>
				<br>
				<br>
				<div class="form-group">
					<label for="focusedInput">케이스 명 : </label><br> <input
						class="form-control" type="text" id="itemName" size="50"
						placeholder="Enter Product name (50 characters)" name="itemName"
						value="${requestScope.product.itemName}" maxlength="50"> <i><font
						color="red">V</font></i>
				</div>
				<br>
				<div class="form-group">
					<label for="focusedInput">호환기기 : </label><br> <input
						class="form-control" type="text" id="device" size="10"
						placeholder="Galaxy 20S" name="device"
						value="${requestScope.product.device}"> <i><font
						color="red">V</font></i>
				</div>
				<br>
				<div class="form-group">
					<label for="focusedInput">수량 : </label><br> <input
						class="form-control" type="text" id="quantity" size="10"
						placeholder="100" name="quantity"
						value="${requestScope.product.quantity}"> <i><font
						color="red">V</font></i>
				</div>
				<br>
				<div class="form-group">
					<label for="focusedInput">가격 : </label><br> <input
						class="form-control" type="text" id="price" size="10"
						placeholder="" name="price"
						value="${requestScope.product.price}"> <i><font
						color="red">V</font></i>
				</div>
				<br>
				<div class="form-group">
					<label for="focusedInput">설명 : </label><br>
					<textarea class="form-control" id="content" name="content" rows="5"
						cols="100">${requestScope.product.content}</textarea>
				</div>
				<br> <br>
				<br>
				<button type="submit" class="btn btn-primary active"
					onclick="javascript:FormValid();">등록하기</button>
			</form>
		</div>
		
		<!-- the modal for Login Msg-->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">신규 케이스</h4>
					</div>
					<div class="modal-body">
						<p>
							체크된 항목을 기입해주십시요.
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
