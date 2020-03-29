package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDAO;
import model.Customer;

/*Description	: Product Ordering System
PageTask 	: Show cart list to place order -> delete one item data
DB 			: 'Cart'*/

@WebServlet("/DelCartCon.do")
public class DelCartCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DelCartCon() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sessionCustomer = request.getSession();
		Customer customer = (Customer) sessionCustomer.getAttribute("customer");
		if (customer == null) {
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		CartDAO cartDao = new CartDAO();
		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			cartDao.delRow(itemId, customer.getCustomerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/MyCartCon.do").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
