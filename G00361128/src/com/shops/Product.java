package com.shops;


import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {

	//private Store store;
	private Store store;
	private int  pid;
	private int  sid;
	private String prodName;
	private double Price;


	// Constructors

	public Product() {
		store = new Store();
	}

	public Product(Store store , int  pid, int sid, String prodName,double Price) {

		
			super();
		this.store=store;
		this.pid = pid; 

		this.sid = sid;

		this.prodName = prodName;

		this.Price = Price;
	}

	
	public Store getStore()
	{
		return store;
	}
	
	public void setStore(Store store)
	{
		
		this.store=store;
	}
	
	public int getPid() {

		return pid;

	}

	public void setPid(int pid) {

		this.pid = pid;

	}





	public int getSid() {

		return sid;

	}





	public void setSid(int sid) {

		this.sid = sid;

	}





	public String getProdName() {

		return prodName;

	}





	public void setProdName(String prodName) {

		this.prodName = prodName;

	}





	public double getPrice() {

		return Price;

	}



	public void setPrice(double price) {

		Price = price;

	}

	

	

}




