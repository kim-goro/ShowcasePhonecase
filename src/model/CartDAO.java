package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class CartDAO {
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
	// 'customerId'에 해당하는 'Cart'행 추가
	// ---------------------------------------------------------
	public void addRow(int itemId, String itemName, String customerId, String device, int quantity, double price)
			throws Exception {
		try {
			getCon();
			String sql = "SELECT * FROM Cart WHERE itemId=? AND cutomerId=?";
			pstmt.setInt(1, itemId);
			pstmt.setString(2, customerId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "UPDATE Cart SET quantity=quantity+"+quantity
						+" WHERE itemId=? AND cutomerId=?";
				pstmt.setInt(1, itemId);
				pstmt.setString(2, customerId);
				pstmt.executeUpdate();
			}
			else {
				sql = "INSERT INTO Cart (itemId,itemName,customerId,device,quantity,price) "
						+ "VALUES (?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, itemId);
				pstmt.setString(2, itemName);
				pstmt.setString(3, customerId);
				pstmt.setString(4, device);
				pstmt.setInt(5, quantity);
				pstmt.setDouble(6, price);
				pstmt.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 'Cart'리스트 조회
	// ---------------------------------------------------------
	public ArrayList<Cart> listCart(String customerId) throws Exception {
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		try {
			getCon();
			String sql = "SELECT cartNo, itemId, itemName, device, quantity, price FROM Cart WHERE customerId ='"
					+ customerId + "' ORDER BY cartNo ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Cart cart = new Cart();
				cart.setCartNo(rs.getInt(1));
				cart.setItemId(rs.getInt(2));
				cart.setItemName(rs.getString(3));
				cart.setDevice(rs.getString(4));
				cart.setQuantity(rs.getInt(5));
				cart.setPrice(rs.getDouble(6));
				cartList.add(cart);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		} 
		return cartList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 'Cart'테이블 삭제
	// ---------------------------------------------------------
	public void delAllRows(String customerId) throws Exception {
		try {
			getCon();
			String sql = "DELETE FROM Cart WHERE customerId='\" + customerId + \"'\"";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 'Cart'에서 'itemId'에 해당하는 픔목 삭제
	// ---------------------------------------------------------
	public void delRow(int itemId, String customerId) throws Exception {
		try {
			getCon();
			String sql = "DELETE FROM Cart WHERE itemId=? AND customerId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemId);
			pstmt.setString(2, customerId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
