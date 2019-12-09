package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class office {

	//private Store store;
	private Store store;
	private String Location;
	// Constructors

	public office() {
		store = new Store();
	}

	public office(Store store ,String Location) {	
		super();
		this.store=store;
	}

	public Store getStore()
	{
		return store;
	}
	
	public void setStore(Store store)
	{
		
		this.store=store;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

}




