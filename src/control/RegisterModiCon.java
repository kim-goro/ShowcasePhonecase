package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.CustomerDAO;

@WebServlet("/RegisterModiCon.do")
public class RegisterModiCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterModiCon() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sessionCustomer = request.getSession();
		Customer customer = (Customer)sessionCustomer.getAttribute("customer"); 
		if(customer==null ){
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		String address = request.getParameter("address");
		String postalCode = request.getParameter("postalCode");
		CustomerDAO customerDao= new CustomerDAO();
		try {
			customerDao.updateRow(customer.getCustomerId(), address, postalCode); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	    customer.setAddress(address);
	    customer.setPostalCode(postalCode);
	    sessionCustomer.setAttribute("customer", customer);
	    sessionCustomer.setMaxInactiveInterval(120*60);
		getServletContext().getRequestDispatcher("/MyPageCon.do").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

