# README.md
Jsp Model2 방식으로 CRUD기능을 구현한 간단한 쇼핑몰을 개발하였습니다.
<br><br><br>
> 주요기능: 일자, BLD별로 일정한 비율로 validation set을 추출

* Input: training set 경로, validation set의 비율

* Output: valid폴더로 일부 파일이 이동 

## 1.개발환경
<br>
> 개발환경
* Eclipse IDE 2020-03 
* Apache-tomcat-9.0.33 
* jdk-14
* (oracleDB & Toad) -> MariaDB 
<br>
> Cafe24 호스팅
<br><br>
* Tomcat 8.0.x 
* JSP 2.3 
* Servlet 3.1
* Open JDK 1.8.x 
* MariaDB 10.1.x UTF-8    
<br><br>
> 개발기간   
<br>
* 2020-03-20 ~ 2020-03-29
* hosting url : http://aa5505.cafe24.com/OnlinePhonecaseShopping/Index.jsp
* git repo : https://github.com/kim-goro/ShowcasePhonecase    
<br><br><br>


## 2.구현부  
<br><br>
> 주요 메소드 및 기능
<br>
OnlinePhonecaseShopping
<div>
├── _build <br>
|&nbsp;&nbsp;&nbsp;&nbsp;└── _classes <br>
|&nbsp;&nbsp;&nbsp;&nbsp;├── _model <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── Cart.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── CartDAO.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── Customer.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── CustomerDAO.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── CustOrder.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── CustOrderDAO.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── Employee.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── EmployeeDAO.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;├── Product.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;└── ProductDAO.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;└── _control <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CartCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── CheckoutCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── DelCartCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EmpCustListCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EmpOrderListCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── EmpProdCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── LoginCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── LoginEmpCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── LogoutCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── MyCartCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── MyPageCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ProdListCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── RegisterCon.class <br>
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── RegisterModiCon.class <br>
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
</div>
<br><br>
> 모델링
<br>

| Header One | Header Two | Header Three | Header Four |
| ---------- | :--------- | :----------: | ----------: |
| Default    | Left       | Center       | Right       |

<br><br>
> 메소드 및 Bean클래스
