package com.shops;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

import org.bson.Document;

public class mongoExercise {
   public static void main( String args[] ) {
	  
		
		
		/* ======================================================================================================
		 * Constructor
		 * ====================================================================================================== */
	   String mongoDB = "storeHeadOfficeDB";
		String mongoCollection = "storeHeadOffice";
		
		MongoClient mongoClient;
		MongoDatabase database;
		MongoCollection<Document> collection;
		
		
			mongoClient = new MongoClient();
			database = mongoClient.getDatabase(mongoDB);
			collection = database.getCollection(mongoCollection);
		

			ArrayList<office> officeList = new ArrayList<office>();
			Gson gson = new Gson();
			FindIterable<Document> offices = collection.find();

			for (Document d : offices) {
				office o = gson.fromJson(d.toJson(), office.class);
				 System.out.println(o.get_id());

				officeList.add(o);
				}
			   mongoClient.close();

		}