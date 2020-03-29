package control;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.Customer;

/*
Task 		: Login for customer 
Description	: Product Ordering System
*/

@WebServlet("/LoginCon.do")
public class LoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	
	public LoginCon() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = "";
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String userType = request.getParameter("userType");
		try {
			Context context = new InitialContext();
			Context envctx = (Context) context.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envctx.lookup("jdbc/pool");
			con = dataSource.getConnection();
			
			String sql = "";
			if (userType.equals("user")) {
				sql = "SELECT * FROM Customer WHERE customerId=? AND userpwd=? ";
			} else if (userType.equals("employee")) {
				sql = "SELECT * FROM Employee WHERE employeeId=? AND userpwd=? ";
			}
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				// fail to login
				request.setAttribute("loginMsg", "fail");
				nextPage = "/Login.jsp";
			} else {
				Customer customer = new Customer();
				// get information
				customer.setCustomerId(rs.getString("customerId"));
				customer.setUserName(rs.getString("userName"));
				customer.setAddress(rs.getString("address"));
				customer.setPostalCode(rs.getString("postalCode"));
				// set session
				HttpSession session = request.getSession();
				session.setAttribute("userType", "customer");
				session.setAttribute("customer", customer);
				session.setMaxInactiveInterval(120 * 60);
				nextPage = "/LoginCustProd.jsp";
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// forward to result page
		RequestDispatcher view = request.getRequestDispatcher(nextPage);
		view.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
