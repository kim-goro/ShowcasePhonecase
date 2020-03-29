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
import model.Employee;

/*Task 		: Register for customer    
Description	: Product Ordering System
DB 			: 'Customer'*/


@WebServlet("/RegisterCon.do")
public class RegisterCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	
	public RegisterCon() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = "";
		String category = request.getParameter("category");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String userName = request.getParameter("userName");
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/pool");
			con = ds.getConnection();
			// 1. validate email address
			// DB connection
			String sql = "";
			if (category.equals("Customer")) {
				sql = "SELECT * FROM Customer WHERE customerId=?";
			} else {
				sql = "SELECT * FROM Employee WHERE employeeId=? ";
			}
			// resultSet
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			// move to last row to count rows
			if (!rs.next()) {
				if (category.equals("Customer")) {
					String address = request.getParameter("address");
					String postalCode = request.getParameter("postalCode");
					String innerSql = "INSERT INTO Customer " + " (customerId, userName, userpwd, address, postalCode) values (?,?,?,?,?)";
					pstmt = con.prepareStatement(innerSql);
					pstmt.setString(1, email);
					pstmt.setString(2, userName);
					pstmt.setString(3, pwd);
					pstmt.setString(4, address);
					pstmt.setString(5, postalCode);
					int chk = pstmt.executeUpdate();
					if (chk == 0) {
						//가입 실패 시
						request.setAttribute("Msg", "fail");
						nextPage = "/RegisterForm.jsp";
					} else {
						Customer customer = new Customer();
						customer.setCustomerId(email);
						customer.setUserName(userName);
						customer.setUserPwd(pwd);
						customer.setAddress(address);
						customer.setPostalCode(postalCode);
						// session
						HttpSession session = request.getSession();
						session.setAttribute("userType", "customer");
						session.setAttribute("customer", customer);
						nextPage = "/RegisterCust.jsp";
					}
				} else {
					String innerSql = "INSERT INTO Employee " + " (employeeId, username, userpwd) values (?,?,?)";
					pstmt = con.prepareStatement(innerSql);
					pstmt.setString(1, email);
					pstmt.setString(2, userName);
					pstmt.setString(3, pwd);
					int chk = pstmt.executeUpdate();
					if (chk == 0) {
						request.setAttribute("Msg", "fail");
						nextPage = "/RegisterForm.jsp";
					} else {
						Employee employee = new Employee();
						employee.setEmployeeId(email);
						employee.setUserName(userName);
						// session
						HttpSession session = request.getSession();
						session.setAttribute("userType", "employee");
						session.setAttribute("employee", employee);
						session.setMaxInactiveInterval(600 * 60); 
						nextPage = "/LoginEmpProd.jsp";
					}
				}
				// 2.2 fail to register
			} else {
				request.setAttribute("Msg", "email");
				nextPage = "/RegisterForm.jsp";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
