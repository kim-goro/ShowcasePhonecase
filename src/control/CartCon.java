package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CartDAO;
import model.Product;
import model.ProductDAO;


/*
Task 		: Add to Cart from customers
Description	: Product Ordering System
*/


@WebServlet("/CartCon.do")
public class CartCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartCon() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		String customerId = request.getParameter("customerId");
		String itemId = request.getParameter("itemId");
		ProductDAO productDao = new ProductDAO();
		Product product = new Product();
		try {
			product = productDao.searchProduct(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// add to cart
		CartDAO cartDao = new CartDAO();
		try {
			cartDao.addRow(product.getItemId(), product.getItemName(), customerId, product.getDevice(), 1, product.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("CartCon.do : "+category+"/"+customerId+"/"+itemId+"/를 카트에 담았습니다.");
		request.setAttribute("category", null);
		getServletContext().getRequestDispatcher("/ProdListCon.do?category="+category).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
