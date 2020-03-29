package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustOrder;
import model.CustOrderDAO;

/*Task 		: 1. Product order list 
 			  2. update orderStatus
 			  3. cancel order  
Description	: Product Ordering System
PageTask 	: Employee -> Order management
DB 			: 'Product'*/

@WebServlet("/EmpOrderListCon.do")
public class EmpOrderListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EmpOrderListCon() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CustOrderDAO order= new CustOrderDAO();	
		String mode = request.getParameter("mode");
		if(mode != null && !mode.isEmpty()) {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			if(mode.equals("del")) { 
				try {
					order.delRow(orderId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(mode.equals("add")) {
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				try {
					order.addQty(orderId, quantity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(mode.equals("down")) {
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				try {
					order.downQty(orderId, quantity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(mode.equals("status")) {
				String orderStatus = request.getParameter("orderStatus");
				try {
					order.updateStatus(orderId, orderStatus);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ArrayList<CustOrder> ordersList = new  ArrayList<CustOrder>();
		try {
			ordersList = order.listAllOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.setAttribute("ordersList", ordersList);
		getServletContext().getRequestDispatcher("/CsrOrderList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
