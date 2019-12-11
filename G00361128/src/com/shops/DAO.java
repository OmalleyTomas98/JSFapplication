package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	public ArrayList<Product> loadProducts() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Product> products = new ArrayList<Product>();

// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setPID(myRs.getInt("PID"));
			p.setSID(myRs.getInt("SID"));
			p.setPrice(myRs.getDouble("PRICE"));
			p.setProdName(myRs.getString("ProdName"));
			products.add(p);
		}
		return products;
	}

	public ArrayList<Store> loadStores() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Store> stores = new ArrayList<Store>();

// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setId(myRs.getInt("id"));
			s.setName(myRs.getString("name"));
			s.setFounded(myRs.getString("founded"));
			stores.add(s);
		}
		return stores;
	}

	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "Insert into store value (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getPID());
		myStmt.setInt(2, product.getSID());
		myStmt.setDouble(3, product.getPrice());
		myStmt.setString(4, product.getProdName());

	}

// Delete the given shops from the manufacturer table
	public void deleteProduct(Product product) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM Product " + "WHERE PID=?");

		myStmt.setInt(1, product.getPID());
		myStmt.executeUpdate();

		myStmt.close();
		conn.close();
	}

//Delete the given shops from the Store table
	public void deleteStore(Store store) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM Store " + "WHERE founded=?");

		myStmt.setString(1, store.getFounded());
		myStmt.executeUpdate();

		myStmt.close();
		conn.close();
	}

//Insert a new manufacturer into the manufacturer table
	public void addStore(Store newstore) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO Store " + "VALUES (?,?,?)");

		myStmt.setInt(1, newstore.getId());
		myStmt.setString(2, newstore.getName());
		myStmt.setString(3, newstore.getFounded());
		myStmt.executeUpdate();

		myStmt.close();
		conn.close();
	}

}