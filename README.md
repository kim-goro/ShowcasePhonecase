# README.md
Jsp Model2 방식으로 CRUD기능을 구현한 간단한 쇼핑몰을 개발하였습니다.
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

## 2.구현부
> 주요 메소드 및 기능
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
** Cart **

| Field | Type | Null | Key | Default | Extra |
| ----: | :--: | :--: | :-: | :-----: | :---: |
| CartNo | int(11) | NO | PRI | NULL | auto-increment |
| itemId | int(11) | YES |    | NULL |    |
| itemName | varchar(100) | YES |    | NULL |    |
| csutomerId | varchar(100) | YES |    | NULL |    |
| device | varchar(100) | YES |    | NULL |    |
| quantity | int(11) | YES |    | NULL |    |
| price | double | YES |    | NULL |    |

<br><br>
> 메소드 및 Bean클래스
