package edu.carleton.comp4601.dao;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.utils.Constants;

public class UserCollection {
	
	private static UserCollection instance;
	
	// MongoDB
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	
	private UserCollection() {
		mongoClient = new MongoClient("localhost");
		db = mongoClient.getDB(Constants.DB_NAME);
		collection = db.getCollection("users");
		collection.setObjectClass(User.class);
	}
	
	public static UserCollection getInstance() {
		if(instance == null) {
			instance = new UserCollection();
		}
		
		return instance;
	}
	
	public boolean add(User user) {
		if(!doesExist(user)) {
			collection.insert(user);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean update(User user) {
		BasicDBObject query = new BasicDBObject("name", user.getName());
		
		DBObject updatedUser = collection.findAndModify(query, user);
		
		if(updatedUser == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean doesExist(User user) {
		BasicDBObject query = new BasicDBObject("name", user.getName());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public User find(User user) {
		BasicDBObject query = new BasicDBObject("name", user.getName());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return (User) cursor.next();
		} else {
			return null;
		}
	}
	
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			users.add((User) cursor.next());
		}
		
		return users;
	}
	
	public long count() {
		return collection.count();
	}
	
}
