package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import javax.faces.bean.ManagedBean;


public class DAO {

	private  DataSource mysqlDS;
	
	// Constructors
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);

	}
	
	
	// Return an array list of all manufacturers from the database
	public ArrayList<Store> getStores() throws Exception {
		ArrayList<Store> stores = new ArrayList<Store>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from store");
					 									
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			int ID = rs.getInt("store_Id");
			String Name = rs.getString("store_Name");
			String Founded = rs.getString("store_Founded");
			stores.add(new Store(ID, Name, Founded));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return stores;
	}
	
	
	
	
	// Delete the given shops from the manufacturer table
	public void deleteStore(Store store) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM shops " +
					 									"WHERE ID=?");
	
		myStmt.setInt(1, store.getID());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Insert a new Store into the shops table
	public void addStore(Store newStore) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO manufacturer " +
					 									"VALUES (?,?,?)");
		myStmt.setInt(1, newStore.getID());
		myStmt.setString(2, newStore.getName());
		myStmt.setString(3, newStore.getFounded());
		myStmt.executeUpdate();
		myStmt.close();
		conn.close();
	}
	

	
	// Update the manufacturer in the manufacturer table with the code of the given manufacturer
	public void updateStore(Store store) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("UPDATE store " +
					 									"SET manu_name=?, " +
					 									"manu_details=? " +
					 									"WHERE manu_code=?;");
		
		myStmt.setInt(1, store.getID());
		myStmt.setString(2, store.getName());
		myStmt.setString(3, store.getFounded());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	

	// Return an array list of all models from the database
		public ArrayList<Product> getProducts() throws Exception {
			ArrayList<Product> products = new ArrayList<Product>();
			
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("SELECT * " +
						 									"FROM product");
			ResultSet rs = myStmt.executeQuery();
			
			
			while (rs.next()) {
				int ID = rs.getInt("ID");
				Store store = new Store();
		
				int pid = rs.getInt("pid");
				int sid = rs.getInt("sid");
				String prodName = rs.getString("prodName");
				double Price = rs.getDouble("Price");


				products.add(new Product(store, pid, sid, prodName,Price));
			}
			
			myStmt.close();
			rs.close();
			conn.close();
			
			return products;
		}
		
	
		// Insert a new model into the model table
		public void addProduct(Product newProduct) throws Exception {
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("INSERT INTO model " +
						 									"VALUES (?,?,?,?)");
			
			
			myStmt.setInt(1, newProduct.getStore().getID());
			myStmt.setInt(2, newProduct.getPid());
			myStmt.setInt(3, newProduct.getSid());
			myStmt.setString(4, newProduct.getProdName());
			myStmt.setDouble(5, newProduct.getPrice());

			myStmt.executeUpdate();
			
			myStmt.close();
			conn.close();
		}
		
		
	}


