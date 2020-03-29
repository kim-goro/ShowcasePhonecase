<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%-- Task : Customer Information
Update Description : Product Ordering System DB : 'Customer' --%>

<!DOCTYPE html>
<html>
  <head>
    <title>쇼케이스 폰케이스</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    />
    <style>
      body {
        padding-top: 70px;
      }
      .itemTitle {
        color: #4c4c4c;
        size: 14px;
      }
    </style>
  </head>

  <body>
    <div class="container-fluid">
      <jsp:include page="/TopNav.jsp" />
      <div class="container">
        <h2>${requestScope.customer.userName}</h2>
        <br />
        <form
          class="form-inline"
          method="post"
          name="RegistForm"
          action="EmpCustListCon.do"
        >
          <input type="hidden" id="mode" name="mode" value="edit" />
          <input
            type="hidden"
            id="customerId"
            name="customerId"
            value="${requestScope.customer.customerId}"
          />
          <div class="form-group">
            <label for="focusedInput">
              이메일 : ${requestScope.customer.customerId} </label
            ><br /><br />
          </div>
          <br />
          <div class="form-group">
            <label for="pwd">비밀번호 :</label><br />
            <input
              type="text"
              class="form-control"
              id="pwd"
              size="50"
              value="${requestScope.customer.userPwd }"
              name="pwd"
              maxlength="30"
            />
          </div>
          <br />
          <div class="form-group">
            <label for="focusedInput">이름 : </label><br />
            <input
              class="form-control"
              type="text"
              id="userName"
              size="50"
              value="${requestScope.customer.userName }"
              name="userName"
              maxlength="30"
            />
          </div>
          <br />
          <div class="form-group">
            <label for="focusedInput">주소 : </label><br />
            <input
              class="form-control"
              type="text"
              id="address"
              size="100"
              value="${requestScope.customer.address }"
              name="address"
            />
          </div>
          <br />
          <div class="form-group">
            <label for="focusedInput">우편번호 : </label><br />
            <input
              class="form-control"
              type="text"
              id="postalCode"
              size="10"
              value="${requestScope.customer.postalCode }"
              name="postalCode"
            />
          </div>
          <br />
          <br /><br />
          <button type="submit" class="btn btn-primary active">수정완료</button>
        </form>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
</html>
