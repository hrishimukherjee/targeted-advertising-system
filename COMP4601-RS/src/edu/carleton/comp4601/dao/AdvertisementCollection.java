package edu.carleton.comp4601.dao;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4601.model.Advertisement;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.utils.Constants;

public class AdvertisementCollection {
	
	private static AdvertisementCollection instance;
	
	// MongoDB
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	
	private AdvertisementCollection() {
		mongoClient = new MongoClient("localhost");
		db = mongoClient.getDB(Constants.DB_NAME);
		collection = db.getCollection("advertisements");
		collection.setObjectClass(Advertisement.class);
	}
	
	public static AdvertisementCollection getInstance() {
		if(instance == null) {
			instance = new AdvertisementCollection();
		}
		
		return instance;
	}
	
	public boolean add(Advertisement ad) {
		if(!doesExist(ad)) {
			collection.insert(ad);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doesExist(Advertisement ad) {
		BasicDBObject query = new BasicDBObject("title", ad.getTitle());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Advertisement find(Advertisement ad) {
		BasicDBObject query = new BasicDBObject("title", ad.getTitle());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return (Advertisement) cursor.next();
		} else {
			return null;
		}
	}
	
	public List<Advertisement> findByOptimism(Integer optimismClass) {
		List<Advertisement> results = new ArrayList<Advertisement>();
		
		BasicDBObject query = new BasicDBObject("optimismClass", optimismClass);
		
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			results.add((Advertisement) cursor.next());
		}
		
		return results;
	}
	
	public List<Advertisement> findByReliability(Integer reliabilityClass) {
		List<Advertisement> results = new ArrayList<Advertisement>();
		
		BasicDBObject query = new BasicDBObject("reliabilityClass", reliabilityClass);
		
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			results.add((Advertisement) cursor.next());
		}
		
		return results;
	}
	
}
