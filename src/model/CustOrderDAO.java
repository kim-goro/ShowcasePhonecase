package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustOrderDAO {
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
	// 'customerId'에 해당하는 'Cart'를 'Order'에 삽입 (주문완료)
	// ---------------------------------------------------------
	public void orderAllItems(String customerId) throws Exception {
		try {
			getCon();
			String sql = "SELECT itemId, device, quantity, price FROM Cart WHERE customerId ='" + customerId
					+ "' ORDER BY cartNo ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.print("Error1");
			while (rs.next()) {
				System.out.print("Error2");
				String innerSql = "INSERT INTO CustOrder (itemId,customerId,device,quantity,price,orderStatus,Orderdate) "
						+ "values (?,'" + customerId + "',?,?,?,'"
								+ "Order Placed',now())";
				pstmt = con.prepareStatement(innerSql);
				pstmt.setInt(1, rs.getInt(1));
				pstmt.setString(2, rs.getString(2));
				pstmt.setInt(3, rs.getInt(3));
				pstmt.setDouble(4, rs.getDouble(4));
				pstmt.executeUpdate();
				con.close();
				System.out.print("Error3");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 모든 'Order'를 조회 (모든 주문조회) 
	// ---------------------------------------------------------
	public ArrayList<CustOrder> listAllOrder() throws Exception {
		ArrayList<CustOrder> orderList = new ArrayList<CustOrder>();
		try {
			getCon();
			String sql = "SELECT o.orderID, o.itemId, o.customerId, o.device, o.quantity, o.price, o.orderStatus, o.orderdate, p.itemName "
					+ " FROM CustOrder o, Product p " + " WHERE o.itemId = p.itemId " + " ORDER BY o.orderId ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustOrder order = new CustOrder();
				order.setOrderId(rs.getInt(1));
				order.setItemId(rs.getInt(2));
				order.setCustomerId(rs.getString(3));
				order.setDevice(rs.getString(4));
				order.setQuantity(rs.getInt(5));
				order.setPrice(rs.getDouble(6));
				order.setOrderStatus(rs.getString(7));
				order.setItemName(rs.getString(9));
				orderList.add(order);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'customerId'에 해당하는 모든 'Order'를 조회 (고객별 주문조회) 
	// ---------------------------------------------------------
	public ArrayList<CustOrder> listOrder(String customerId) throws Exception {
		ArrayList<CustOrder> orderList = new ArrayList<CustOrder>();
		try {
			getCon();
			String sql = "SELECT o.orderID, o.itemId, o.customerId, o.device, o.quantity, o.price, o.orderStatus, o.orderdate, p.itemName "
					+ " FROM CustOrder o, Product p " + " where o.customerId ='" + customerId + "' AND o.itemId = p.itemId "
					+ " ORDER BY o.orderId ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustOrder order = new CustOrder();
				order.setOrderId(rs.getInt(1));
				order.setItemId(rs.getInt(2));
				order.setCustomerId(rs.getString(3));
				order.setDevice(rs.getString(4));
				order.setQuantity(rs.getInt(5));
				order.setPrice(rs.getDouble(6));
				order.setOrderStatus(rs.getString(7));
				order.setItemName(rs.getString(9));
				orderList.add(order);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
		return orderList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'Order Placed'된 고객의 'Order'를 반환 (주문완료된 고객별 주문조회)
	// ---------------------------------------------------------
	public ArrayList<CustOrder> listOrderPlaced(String customerId) throws Exception {
		ArrayList<CustOrder> orderList = new ArrayList<CustOrder>();
		try {
			getCon();
			String sql = "SELECT o.orderID, o.itemId, o.customerId, o.device, o.quantity, o.price, o.orderStatus, o.Orderdate, s.itemName "
					+ " from CustOrder o, Product s " + " WHERE o.customerId ='" + customerId
					+ "' AND o.orderStatus = 'Order Placed' and o.itemId = s.itemId " + " ORDER BY o.orderId ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustOrder order = new CustOrder();
				order.setOrderId(rs.getInt(1));
				order.setItemId(rs.getInt(2));
				order.setCustomerId(rs.getString(3));
				order.setDevice(rs.getString(4));
				order.setQuantity(rs.getInt(5));
				order.setPrice(rs.getDouble(6));
				order.setOrderStatus(rs.getString(7));
				order.setItemName(rs.getString(9));
				orderList.add(order);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
		return orderList;
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'orderId'에 해당하는 'Order'행 삭제
	// ---------------------------------------------------------
	public void delRow(int orderId) throws Exception {
		try {
			getCon();
			String sql = "DELETE FROM CustOrder WHERE orderId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'orderId'에 해당하는 'Order'의 'quantity'값 삽입 (추가)
	// ---------------------------------------------------------
	public void addQty(int orderId, int quantity) throws Exception {
		try {
			getCon();
			String sql = "UPDATE CustOrder SET quantity=" + quantity + ", " + "price = (price / (" + (quantity - 1)
					+ ")) * " + quantity + " WHERE orderId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'orderId'에 해당하는 'Order'의 'quantity'값 삽입 (내림)
	// ---------------------------------------------------------
	public void downQty(int orderId, int quantity) throws Exception {
		try {
			getCon();
			String sql = "UPDATE CustOrder SET quantity=" + quantity + ", " + "price = (price / (" + (quantity + 1)
					+ ")) * " + quantity + " WHERE orderId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}

	
	
	
	
	
	// ---------------------------------------------------------
	// 'orderId'에 해당하는 'Order'의 'orderStatus'값 삽입 (상태 변경)
	// ---------------------------------------------------------
	public void updateStatus(int orderId, String orderStatus) throws Exception {
		try {
			getCon();
			String sql = "UPDATE CustOrder SET orderStatus = ?  WHERE orderId=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderStatus);
			pstmt.setInt(2, orderId);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pstmt.toString());
			e.printStackTrace();
		}
	}
}
