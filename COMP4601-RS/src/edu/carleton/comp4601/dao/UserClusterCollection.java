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
import edu.carleton.comp4601.model.UserCluster;
import edu.carleton.comp4601.utils.Constants;

public class UserClusterCollection {
	
	private static UserClusterCollection instance;
	
	// MongoDB
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	
	private UserClusterCollection() {
		mongoClient = new MongoClient("localhost");
		db = mongoClient.getDB(Constants.DB_NAME);
		collection = db.getCollection("user_cluster");
		collection.setObjectClass(UserCluster.class);
	}
	
	public static UserClusterCollection getInstance() {
		if(instance == null) {
			instance = new UserClusterCollection();
		}
		
		return instance;
	}
	
	public boolean add(UserCluster user) {
		if(!doesExist(user)) {
			collection.insert(user);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doesExist(UserCluster user) {
		BasicDBObject query = new BasicDBObject("name", user.getName());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public UserCluster find(UserCluster user) {
		BasicDBObject query = new BasicDBObject("name", user.getName());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return (UserCluster) cursor.next();
		} else {
			return null;
		}
	}
	
	public List<UserCluster> findAll() {
		List<UserCluster> users = new ArrayList<UserCluster>();
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			users.add((UserCluster) cursor.next());
		}
		
		return users;
	}
	
	public List<UserCluster> findUsersByCluster(int cluster) {
		List<UserCluster> users = new ArrayList<UserCluster>();
		
		// Perform the query
		BasicDBObject query = new BasicDBObject("cluster", cluster);
		
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			users.add((UserCluster) cursor.next());
		}
		
		return users;
	}
	
}
