package edu.carleton.comp4601.dao;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import edu.carleton.comp4601.model.Review;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.utils.Constants;

public class ReviewCollection {
	
	private static ReviewCollection instance;
	
	// MongoDB
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	
	private ReviewCollection() {
		mongoClient = new MongoClient("localhost");
		db = mongoClient.getDB(Constants.DB_NAME);
		collection = db.getCollection("reviews");
		collection.setObjectClass(Review.class);
	}
	
	public static ReviewCollection getInstance() {
		if(instance == null) {
			instance = new ReviewCollection();
		}
		
		return instance;
	}
	
	public boolean add(Review review) {
		if(!doesExist(review)) {
			collection.insert(review);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doesExist(Review review) {
		BasicDBObject query = new BasicDBObject("id", review.getId());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Review find(Review review) {
		BasicDBObject query = new BasicDBObject("id", review.getId());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return (Review) cursor.next();
		} else {
			return null;
		}
	}
	
	public List<Review> findByUser(User user) {
		List<Review> searchResults = new ArrayList<Review>();
		BasicDBObject query = new BasicDBObject("userName", user.getName());
		
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			searchResults.add((Review) cursor.next());
		}
		
		return searchResults;
	}
	
	public long count() {
		return collection.count();
	}
	
}
