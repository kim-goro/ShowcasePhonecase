package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;

@WebServlet("/EmpProdCon.do")
public class EmpProdCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EmpProdCon() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO productDao = new ProductDAO();
		try {
			String mode = request.getParameter("mode");
			System.out.println("EmpProdCon : '"+ mode + "' 명령어가 실행되었습니다.");
			int itemId = 0;
			String itemName = "";
			String category = "";
			String device = "";
			String content = "";
			int quantity = 0;
			Double price = 0.0;
			
			if(mode.equals("new")) {
				 itemName = request.getParameter("itemName");
				 category = request.getParameter("category");
				 device = request.getParameter("device");
				 content = request.getParameter("content");
				 quantity = Integer.parseInt(request.getParameter("quantity"));
				 price = Double.parseDouble(request.getParameter("price"));
				 productDao.addRow( itemName, category, device, quantity, price, content);
				System.out.println("EmpProdCon.do : "+category+"에 '"+itemName+"'등록을 완료하였습니다.");
				response.sendRedirect("ProdListCon.do");
			// delete data
			}else if(mode.equals("del")) {
				itemId = Integer.parseInt(request.getParameter("itemId"));
				category = request.getParameter("category");
				productDao.delRow(itemId);
				System.out.println("EmpProdCon.do : "+category+"에 '"+itemId+"'번째 품목을 삭제하였습니다.");
				request.setAttribute(category, null);
				getServletContext().getRequestDispatcher("/ProdListCon.do").forward(request, response);
			// update data
			}else if(mode.equals("edit")) {
				 itemId = Integer.parseInt(request.getParameter("itemId"));
				 itemName = request.getParameter("itemName");
				 category = request.getParameter("category");
				 device = request.getParameter("device");
				 content = request.getParameter("content");
				 quantity = Integer.parseInt(request.getParameter("quantity"));
				 price = Double.parseDouble(request.getParameter("price"));
				 productDao.updateRow( itemId, itemName, category, device, quantity, price, content);
				request.setAttribute(category, null);
				System.out.println("EmpProdCon.do : "+category+"에 '"+itemName+"'수정을 완료하였습니다.");
				getServletContext().getRequestDispatcher("/ProdListCon.do?category="+category).forward(request, response);
			// to search data for edit-page 
			}else if(mode.equals("search")) {
				itemId = Integer.parseInt(request.getParameter("itemId"));
				Product product = productDao.searchProduct(itemId);
				request.setAttribute("mode", "edit");   //  should be changed to edit mode
				request.setAttribute("product", product);
				System.out.println("EmpProdCon.do : '"+itemName+"'검색을 완료하였습니다.");
				getServletContext().getRequestDispatcher("/EmpProdForm.jsp").forward(request, response);
			}	 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
