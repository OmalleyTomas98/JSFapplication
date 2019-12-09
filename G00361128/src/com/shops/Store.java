package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {

	private int ID;
	private String Name;
	private String Founded;

	// Constructors
	public Store() {
	}

	public Store(int ID, String Name, String Founded) {
		super();

		this.ID = ID;
		this.Name = Name;
		this.Founded = Founded;
	}

	// Getters and Setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = ID;
	}

	public String getName() {

		return Name;

	}

	public void setName(String Name) {

		this.Name = Name;

	}

	public String getFounded() {

		return Founded;

	}

	public void setFounded(String founded) {

		Founded = founded;

	}
}