package com.shops;



import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
public class ProductController {

	private ArrayList<Product> products;
	private DAO dao;
	
	// Constructors
	public ProductController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the models from the database and store them in an instance variable
	public void loadProducts() {
		try {
			products = dao.getProducts();
		} catch (SQLException se) {
			String m = se.getMessage();
			
			switch(se.getErrorCode()) {
			case 0:
				m = "Cannot connect to database";
				break;
			}
			
			FacesMessage message = new FacesMessage("ERROR: " + m);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to add the new model to the database
	public String addProduct(Product product) {
		try {
			dao.addProduct(product);
			return "list_products";
		} catch (SQLException se) {
			String m = se.getMessage();
			
			switch(se.getErrorCode()) {
			case 0:
				m = "Cannot connect to database";
				break;
			}
			
			FacesMessage message = new FacesMessage("ERROR: " + m);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Getters and Setters
	public ArrayList<Product> getProducts() {
		return products;
	}
}