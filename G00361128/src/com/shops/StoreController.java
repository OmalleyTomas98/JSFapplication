package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StoreController {

	DAO dao;
	ArrayList<Store> stores;

	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadStores() {
		System.out.println("In loadStores()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//Use the DAO to delete the given store  from the database
	public String deleteStore(Store store) {
		try {
			dao.deleteStore(store);
			stores.remove(store);
		} catch (SQLException se) {
			String m = se.getMessage();

			switch (se.getErrorCode()) {
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

	// Use the DAO to add the new Store to the mySQL database

	public String addStore(Store store) {
		try {
			dao.addStore(store);
			return "list_stores";
		} catch (SQLException se) {
			String m = se.getMessage();

			switch (se.getErrorCode()) {
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

	public ArrayList<Store> getStores() {
		return stores;
	}

}
