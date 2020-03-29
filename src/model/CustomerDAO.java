package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDAO {
	// connection
	Connection con = null;
	PreparedStatement pstmt;
	ResultSet rs = null;

	public void getCon() {
		try {
			Context context = new InitialContext();
			Context envctx = (Context) context.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envctx.lookup("jdbc/pool");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 모든 고객리스트 조회
	// ---------------------------------------------------------
	public ArrayList<Customer> searchAllCustomer() throws Exception {
		ArrayList<Customer> custList = new ArrayList<Customer>();
		try {
			getCon();
			String sql = "SELECT customerId, userName, userPwd, address, postalcode FROM Customer ORDER BY customerId ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString(1));
				customer.setUserName(rs.getString(2));
				customer.setUserPwd(rs.getString(3));
				customer.setAddress(rs.getString(4));
				customer.setPostalCode(rs.getString(5));
				custList.add(customer);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
		return custList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 고객 조회
	// ---------------------------------------------------------
	public Customer searchCustomer(String customerId) throws Exception {
		Customer customer = new Customer();
		try {
			getCon();
			String sql = "SELECT userName, userPwd, address, postalcode FROM Customer WHERE customerId=?";
			pstmt.setString(1, customerId);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				customer.setCustomerId(customerId);
				customer.setUserName(rs.getString(1));
				customer.setUserPwd(rs.getString(2));
				customer.setAddress(rs.getString(3));
				customer.setPostalCode(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
		return customer;
	}
	
	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 고객 정보 삽입 (수정)
	// ---------------------------------------------------------
	public void updateRow(String customerId, String address, String postalCode) throws Exception {
		try {
			getCon();
			String sql = "UPDATE Customer " + "SET address =?, postalCode =? " + "WHERE customerId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, postalCode);
			pstmt.setString(3, customerId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}
	
	// Overlaoding
 	public void updateRow(String customerId, String userName, String customerPwd, String address, String postalCode) throws Exception {
		try {
			getCon();
			String sql = "UPDATE Customer "
					+ "SET userName=?, userPwd=?, address =?, postalCode =? "
					+ "WHERE customerId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, customerPwd);
			pstmt.setString(3, address);
			pstmt.setString(4, postalCode);
			pstmt.setString(5, customerId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 'customer'행 삭제
	// ---------------------------------------------------------
	public void delRow(String customerId) throws Exception {
		try {
			getCon();
			String sql = "DELETE FROM Customer WHERE customerId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customerId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}
}

