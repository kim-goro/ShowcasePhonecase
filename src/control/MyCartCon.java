package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartDAO;
import model.Customer;

/*Task 		: 주문 완료된 카트내역을 가져옴 forward to MyCart.jsp  
Description	: Product Ordering System*/

@WebServlet("/MyCartCon.do")
public class MyCartCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyCartCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sessionCustomer = request.getSession();
		Customer customer = (Customer)sessionCustomer.getAttribute("customer"); 
		if(customer==null ){
			// Dispatcher - forward to result page
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		// objcustomerId
		CartDAO cartDao = new CartDAO();
		// result
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		// search Cart
		try {
			cartList = cartDao.listCart(customer.getCustomerId());
	        request.setAttribute("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Dispatcher - forward to result page
		getServletContext().getRequestDispatcher("/MyCart.jsp").forward(request, response);
		 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
