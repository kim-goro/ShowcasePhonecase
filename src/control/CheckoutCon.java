package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDAO;
import model.CustOrder;
import model.CustOrderDAO;
import model.Customer;

/*
 * Task : check out from cart 1. move to order db 2. make cart empty 3. forward
 * to result of order Description : Product Ordering System
 */

@WebServlet("/CheckoutCon.do")
public class CheckoutCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckoutCon() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sessionCustomer = request.getSession();
		Customer customer = (Customer)sessionCustomer.getAttribute("customer"); 
		if(customer==null ){
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		CustOrderDAO orderDao = new CustOrderDAO();	
		CartDAO cartDao = new CartDAO();
		CustOrderDAO order= new CustOrderDAO();
		ArrayList<CustOrder> orderList = new  ArrayList<CustOrder>();
		try {
			orderDao.orderAllItems(customer.getCustomerId());
			System.out.println("CheckoutCon.do : "+customer.getCustomerId()+"고객님의 장바구니를 주문하였습니다.");
			cartDao.delAllRows(customer.getCustomerId());
			System.out.println("CheckoutCon.do : "+customer.getCustomerId()+"고객님의 장바구니를 삭제하였습니다.");
			orderList = order.listOrderPlaced(customer.getCustomerId());
			System.out.println("CheckoutCon.do : "+customer.getCustomerId()+"고객님의 주문내역을 조회하였습니다.");
	        request.setAttribute("orderList", orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/OrderList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
