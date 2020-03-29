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

import model.Employee;


@WebServlet("/LoginEmpCon.do")
public class LoginEmpCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pstmt;
	ResultSet rs = null;

	public LoginEmpCon() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nextPage = "";
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		try {
			Context context = new InitialContext();
			Context envctx = (Context) context.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envctx.lookup("jdbc/pool");
			con = dataSource.getConnection();

			String sql = "SELECT * FROM Employee WHERE employeeId=? AND userpwd=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			// 매칭되는 아이디가 있는지 확인
			if (!rs.next()) {
				// 로그인 실패 시
				request.setAttribute("loginMsg", "fail");
				nextPage = "/Login.jsp";
			} else {
				// move to first row
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employeeId"));
				employee.setUserName(rs.getString("userName"));
				// set session
				HttpSession session = request.getSession();
				session.setAttribute("userType", "employee");
				session.setAttribute("employee", employee);
				session.setMaxInactiveInterval(600 * 60);
				nextPage = "/LoginEmpProd.jsp";
			}
			con.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		RequestDispatcher view = request.getRequestDispatcher(nextPage);
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

