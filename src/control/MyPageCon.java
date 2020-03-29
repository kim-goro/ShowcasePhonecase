package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustOrder;
import model.CustOrderDAO;
import model.Customer;

/*Task 		: Customer - My Page 
			  1. show order list 
			  2. add quantity 
			  3. cancel
Description	: Product Ordering System
DB 			: 'Product'*/

@WebServlet("/MyPageCon.do")
public class MyPageCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyPageCon() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sessionCustomer = request.getSession();
		Customer customer = (Customer) sessionCustomer.getAttribute("customer");
		CustOrderDAO order = new CustOrderDAO();
		if (customer == null) {
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		String mode = request.getParameter("mode");
		
		// 명령어 실행
		if (mode != null && !mode.isEmpty()) {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			if (mode.equals("del")) {
				try {
					order.delRow(orderId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (mode.equals("add")) {
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				try {
					order.addQty(orderId, quantity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// update quantity (- 1)
			} else if (mode.equals("down")) {
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				try {
					order.downQty(orderId, quantity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		ArrayList<CustOrder> orderList = new ArrayList<CustOrder>();
		try {
			orderList = order.listOrder(customer.getCustomerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderList", orderList);
		getServletContext().getRequestDispatcher("/MyPage.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
