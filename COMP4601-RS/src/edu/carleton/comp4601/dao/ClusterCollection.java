package edu.carleton.comp4601.dao;


import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4601.model.Cluster;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.utils.Constants;

public class ClusterCollection {
	
	private static ClusterCollection instance;
	
	// MongoDB
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	
	private ClusterCollection() {
		mongoClient = new MongoClient("localhost");
		db = mongoClient.getDB(Constants.DB_NAME);
		collection = db.getCollection("clusters");
		collection.setObjectClass(Cluster.class);
	}
	
	public static ClusterCollection getInstance() {
		if(instance == null) {
			instance = new ClusterCollection();
		}
		
		return instance;
	}
	
	public boolean add(Cluster cluster) {
		if(!doesExist(cluster)) {
			collection.insert(cluster);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doesExist(Cluster cluster) {
		BasicDBObject query = new BasicDBObject("cluster", cluster.getCluster());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Cluster find(Cluster cluster) {
		BasicDBObject query = new BasicDBObject("cluster", cluster.getCluster());
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			return (Cluster) cursor.next();
		} else {
			return null;
		}
	}
	
	public List<Cluster> findAll() {
		List<Cluster> clusters = new ArrayList<Cluster>();
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			clusters.add((Cluster) cursor.next());
		}
		
		return clusters;
	}
	
}
