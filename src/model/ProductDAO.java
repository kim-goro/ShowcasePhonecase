package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {
	Connection con = null;
	PreparedStatement pstmt;
	ResultSet rs = null;

	public void getCon() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/pool");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	// ---------------------------------------------------------
	// 정렬된 모든 Product 쿼리셋을 조회
	// ---------------------------------------------------------
	public ArrayList<Product> listAllProduct() throws Exception {
		ArrayList<Product> ProductList = new ArrayList<Product>();
		try {
			getCon();
			String sql = "SELECT itemId, itemName, category, device, quantity, price, content FROM Product ORDER BY itemId DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setItemId(rs.getInt(1));
				product.setItemName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setDevice(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setPrice(rs.getDouble(6));
				product.setContent(rs.getString(7));
				ProductList.add(product);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProductList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 카테고리를 포함한 Product 쿼리셋을 조회
	// ---------------------------------------------------------
	public ArrayList<Product> listProduct(String category) throws Exception {
		ArrayList<Product> ProductList = new ArrayList<Product>();
		try {
			getCon();
			String sql = "SELECT itemId, itemName, category, device, quantity, price, content FROM Product ";
			if (category != null)
				sql = sql + "WHERE category ='" + category + "' ";
			sql = sql + "ORDER BY itemId DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setItemId(rs.getInt(1));
				product.setItemName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setDevice(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setPrice(rs.getDouble(6));
				product.setContent(rs.getString(7));
				ProductList.add(product);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ProductList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// itemId에 해당하는 Product를 조회
	// ---------------------------------------------------------
	public Product searchProduct(int itemId) throws Exception {
		Product product = new Product();
		try {
			getCon();
			String sql = "SELECT itemId, itemName, category, device, quantity, price, content FROM Product WHERE itemId=" + itemId;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				product.setItemId(rs.getInt(1));
				product.setItemName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setDevice(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setPrice(rs.getDouble(6));
				product.setContent(rs.getString(7));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
		return product;
	}
	
	//Overloading
	public Product searchProduct(String itemId) throws Exception {
		Product product = new Product();
		try {
			getCon();
			String sql = "SELECT itemId, itemName, category, device, quantity, price, content FROM Product WHERE itemId=" + itemId;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				product.setItemId(rs.getInt(1));
				product.setItemName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setDevice(rs.getString(4));
				product.setQuantity(rs.getInt(5));
				product.setPrice(rs.getDouble(6));
				product.setContent(rs.getString(7));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
		return product;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// Product행을 삽입
	// ---------------------------------------------------------
	public void addRow(String itemName, String category, String device, int quantity, double price, String content)
			throws Exception {	
		try {
			getCon();
			String sql = "INSERT INTO Product (itemName, category, device, quantity, price, content ) "
					+ "VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setString(2, category);
			pstmt.setString(3, device);
			pstmt.setInt(4, quantity);
			pstmt.setDouble(5, price);
			pstmt.setString(6, content);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	// ---------------------------------------------------------
	// 'itemId'에 해당하는 Product행을 삽입
	// ---------------------------------------------------------
	public void updateRow(int itemId, String itemName, String category, String device, int quantity, double price,
			String content) throws Exception {
		try {
			getCon();
			String sql = "UPDATE Product SET itemName=?, category=?, device=?, quantity=?, price=?, content=? "
					+ " WHERE itemId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setString(2, category);
			pstmt.setString(3, device);
			pstmt.setInt(4, quantity);
			pstmt.setDouble(5, price);
			pstmt.setString(6, content);
			pstmt.setInt(7, itemId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'itemId'에 해당하는 Product행을 삭제
	// ---------------------------------------------------------
	public void delRow(int itemId) throws Exception {
		try {
			getCon();
			String sql = "DELETE FROM Product WHERE itemId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}
}
