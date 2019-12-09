package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StoreController {
	private ArrayList<Store> stores;
	private DAO dao;
	
	
	// Constructors
	public StoreController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Use the DAO to get all the Stores  from the database and store in an instance variable
	public void loadStores() {
		try {
			stores = dao.getStores();
			System.out.println(stores);
		} catch (SQLException se) {
			String m = se.getMessage();
			System.out.println("HERE");

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
	
	
	//===============================================================
	
		
	// Use the DAO to delete the given manufacturer from the database
	public String deleteStore(Store store) {
		try {
			dao.deleteStore(store);
			stores.remove(store);
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

	
	// Use the DAO to add the new manufacturer to the database
		public String addStore(Store store) {
			try {
				dao.addStore(store);
				return "list_stores";
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
		
		
		
	
	// Use the DAO to update a Store in the database
	public String updateStore(Store store) {
		try {
			dao.updateStore(store);
			return "list_stores";
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
	
	
	// Add the selected manufacturer to ExternalContext 
	public String setUpdateStore(Store store) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("store", store);
		
		return "update_store";
	}
	
	// Get the manufacturer from the ExternalContext
	public Store getUpdateStore() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Store store = (Store)sessionMap.get("store");
		
		return store;
	}
	
	// Getters and Setters
	public ArrayList<Store> getStores() {
		return stores;
	}
}
