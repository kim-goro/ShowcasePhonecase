# README.md
Jsp Model2 방식으로 CRUD기능을 구현한 간단한 쇼핑몰을 개발하였습니다.  
~~현재 카페24 Access문제로 쿼리문 실행에 에러가 있습니다.~~
<br><br><br>


## 1.개발환경
> 개발환경
* Eclipse IDE 2020-03
* Apache-tomcat-9.0.33
* jdk-14
* (oracleDB & Toad) -> MariaDB

> Cafe24 구성
* Tomcat 8.0.x
* JSP 2.3
* Servlet 3.1
* Open JDK 1.8.x
* MariaDB 10.1.x UTF-8

> 개발기간
* 2020-03-20 ~ 2020-03-29
* hosting url : http://aa5505.cafe24.com/OnlinePhonecaseShopping/Index.jsp
* git repo : https://github.com/kim-goro/ShowcasePhonecase    
<br><br><br>

## 2.주요 메소드 및 기능
> 회원가입 및 로그인

```javascript
<script>
	function openUser(option) {
		document.getElementById("custInfo").style.display = option;
	}
	function MsgLoginValid() {
		var form = document.RegistForm;
		// validate null value
		if (document.getElementById("email").value == ""
				|| document.getElementById("pwd").value == ""
				|| document.getElementById("userName").value == "") {
			$("#myModal").modal("show");
			return;
		} else {
			form.action = "RegisterCon.do";
			form.submit();
		}
	}
	$("#myModal").on("shown.bs.modal", function() {
		$("#myInput").trigger("focus");
	});
</script>
```
`RegiterForm.jsp`에서 작성한 Form은 간단한 Validation을 거친 후 `RegisterCon.do` 서블릿으로 넘겨줍니다.  

```Java
if (category.equals("Customer")) {
	String address = request.getParameter("address");
	String postalCode = request.getParameter("postalCode");
	String innerSql = "INSERT INTO Customer " + " (customerId, userName, userpwd, address, postalCode) values (?,?,?,?,?)";
	pstmt = con.prepareStatement(innerSql);
	pstmt.setString(1, email);
	pstmt.setString(2, userName);
	pstmt.setString(3, pwd);
	pstmt.setString(4, address);
	pstmt.setString(5, postalCode);
	int chk = pstmt.executeUpdate();
	if (chk == 0) {
		//가입 실패 시
		request.setAttribute("Msg", "fail");
		nextPage = "/RegisterForm.jsp";
	} else {
		Customer customer = new Customer();
		customer.setCustomerId(email);
		customer.setUserName(userName);
		customer.setUserPwd(pwd);
		customer.setAddress(address);
		customer.setPostalCode(postalCode);
		// session
		HttpSession session = request.getSession();
		session.setAttribute("userType", "customer");
		session.setAttribute("customer", customer);
		nextPage = "/RegisterCust.jsp";
	}
```
POST로 넘겨받은 `category`를 통해 Customer과 Employee로 구분합니다.  
쿼리문 실패 시 `Msg`를 반환하고 성공 시 `HttpSession`을 저장합니다. 



## 3.구조
> 디렉토리
```
_OnlinePhonecaseShopping
|
├── _build
|   | 
|   └── _classes
|       | 
|       ├── _model
|       |   ├── Cart.class
|       |   ├── CartDAO.class
|       |   ├── Customer.class
|       |   ├── CustomerDAO.class
|       |   ├── CustOrder.class
|       |   ├── CustOrderDAO.class
|       |   ├── Employee.class
|       |   ├── EmployeeDAO.class 
|       |   ├── Product.class
|       |   └── ProductDAO.class
|       |
|       └── _control
|           ├── CartCon.class 
|           ├── CheckoutCon.class 
|           ├── DelCartCon.class
|           ├── EmpCustListCon.class
|           ├── EmpOrderListCon.class
|           ├── EmpProdCon.class 
|           ├── LoginCon.class
|           ├── LoginEmpCon.class 
|           ├── LogoutCon.class
|           ├── MyCartCon.class
|           ├── MyPageCon.class
|           ├── ProdListCon.class
|           ├── RegisterCon.class
|           └── RegisterModiCon.class
|
└── _WebContent
    ├── CustList.jps
    ├── CustMode.jps
    ├── CustOrderList.jps 
    ├── EmpProdForm.jps 
    ├── Index.jps
    ├── Login.jps
    ├── LoginCustProd.jps
    ├── LoginEmpProd.jps
    ├── MyCart.jps 
    ├── MyPage.jps 
    ├── OrderList.jps 
    ├── ProdList.jps 
    ├── RegisterForm.jps
    ├── RegisterModi.jps
    ├── TopNav.jps 
    └── TopNavLogin.jps
```

> 모델링
* Cart

| Field | Type | Null | Key | Default | Extra |
| ----: | :--: | :--: | :-: | :-----: | :---: |
| CartNo | int(11) | NO | PRI | NULL | auto-increment |
| itemId | int(11) | YES |    | NULL |    |
| itemName | varchar(100) | YES |    | NULL |    |
| csutomerId | varchar(100) | YES |    | NULL |    |
| device | varchar(100) | YES |    | NULL |    |
| quantity | int(11) | YES |    | NULL |    |
| price | double | YES |    | NULL |    |

* Employee

| Field | Type | Null | Key | Default | Extra |
| ----: | :--: | :--: | :-: | :-----: | :---: |
| empNo | int(11) | NO | PRI | NULL | auto-increment |
| employeeId | varchar(60) | NO |    | NULL |    |
| username | varchar(30) | NO |    | NULL |    |
| userpwd | varchar(30) | NO |    | NULL |    |

* Customer

| Field | Type | Null | Key | Default | Extra |
| ----: | :--: | :--: | :-: | :-----: | :---: |
| customerNo | int(11) | NO | PRI | NULL | auto-increment |
| customerId | varchar(61) | YES |    | NULL |    |
| username | varchar(30) | YES |    | NULL |    |
| userpwd | varchar(30) | YES |    | NULL |    |
| address | varchar(100) | YES |    | NULL |    |
| postalCode | varchar(10) | YES |    | NULL |    |

* CustOrder

| Field | Type | Null | Key | Default | Extra |
| ----: | :--: | :--: | :-: | :-----: | :---: |
| orderId | int(11) | NO | PRI | NULL | auto-increment |
| itemId | int(11) | YES |    | NULL |    |
| itemName | varchar(100) | YES |    | NULL |    |
| cutomerId | varchar(100) | YES |    | NULL |    |
| device | varchar(100) | YES |    | NULL |    |
| quantity | int(11) | YES |    | NULL |    |
| price | double | YES |    | NULL |    |
| orderStatus | varchar(100) | YES |    | NULL |    |
| orderdate | date | YES |    | NULL |    |

* Product

| Field | Type | Null | Key | Default | Extra |
| ----: | :--: | :--: | :-: | :-----: | :---: |
| itemId | int(11) | NO | PRI | NULL | auto-increment |
| itemName | varchar(50) | YES |    | NULL |    |
| category | varchar(30) | YES |    | NULL |    |
| device | varchar(30) | YES |    | NULL |    |
| quantity | int(11) | YES |    | NULL |    |
| price | int(20) | YES |    | NULL |    |
| content | varchar(300) | YES |    | NULL |    |
