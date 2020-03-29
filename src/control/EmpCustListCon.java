package control;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.CustomerDAO;

@WebServlet("/EmpCustListCon.do")
public class EmpCustListCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EmpCustListCon() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CustomerDAO custDao= new CustomerDAO();	
		String mode = request.getParameter("mode");
		// delete or edit info
		if(mode != null && !mode.isEmpty()) {
			String customerId = request.getParameter("customerId");
			// delete customer
			if(mode.equals("del")) { 
				try {
					custDao.delRow(customerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			// search customer's info 	
			}else if(mode.equals("search")) {
				try {
					Customer customer = custDao.searchCustomer(customerId);
					System.out.println(customerId);
			        request.setAttribute("customer", customer);
					getServletContext().getRequestDispatcher("/CsrCustModi.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			// change order status	
			}else if(mode.equals("edit")) {
				String pwd = request.getParameter("pwd");
				String userName = request.getParameter("userName");
				String address = request.getParameter("address");
				String postalCode = request.getParameter("postalCode");
				try {
					custDao.updateRow(customerId, pwd, userName, address, postalCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			customerList = custDao.searchAllCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
        request.setAttribute("customerList", customerList);
		getServletContext().getRequestDispatcher("/CsrCustList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
