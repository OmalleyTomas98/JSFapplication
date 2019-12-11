package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;


@ManagedBean
@SessionScoped
public class ProductController {

DAO dao;
ArrayList<Product> products;

public ProductController() {
super();
try {
dao = new DAO();
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

public void loadProducts() {
System.out.println("In loadproducts()");
try {
products = dao.loadProducts();
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}

//Use the DAO to delete the given manufacturer from the database
	public String deleteProduct(Product product) {
		try {
			dao.deleteProduct(product);
			products.remove(product);
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

public ArrayList<Product> getProducts() {
return products;
}

}
