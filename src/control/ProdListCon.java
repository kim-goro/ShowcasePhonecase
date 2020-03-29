package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;

/*Task 		: Show Product list    
			  SELECT data (call ProductDAO obj)-> SET ATTRIBUTE FOR Product ARRAYLIST-> forward to ProdList.jsp
Description	: Product Ordering System
PageTask 	: Employee -> Order management
DB 			: 'Product'*/


@WebServlet("/ProdListCon.do")
public class ProdListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProdListCon() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		ProductDAO productDao = new ProductDAO();
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			if(category != null)
			{
				productList = productDao.listProduct(category);
				request.setAttribute("category", category);
				System.out.println("ProdListCon.do : "+category+"별 품목리스트 출력하기");
			}
			else {
				productList = productDao.listProduct(null);
				request.setAttribute("category", null);
				System.out.println("ProdListCon.do : 전 품목리스트 출력하기");
			}
	        request.setAttribute("productList", productList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/ProdList.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
