package edu.carleton.comp4601.dao;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import edu.carleton.comp4601.model.Movie;
import edu.carleton.comp4601.utils.Constants;



public class MovieCollection {
	private static MovieCollection instance;

	public static void setInstance(MovieCollection instance) {
		MovieCollection.instance = instance;
	}
	
	public static MovieCollection getInstance() {
		if(instance == null) {
			instance = new MovieCollection();
		}
		
		return instance;
	}
	
	//MongoDB
	private static MongoClient mongoClient;
	private static DB database;
	private static DBCollection movieColl;
	
	private MovieCollection() {
		mongoClient = new MongoClient("localhost");
		
		System.out.println("Creating 'rs' database...");
		database = mongoClient.getDB(Constants.DB_NAME);
		
		System.out.println("Creating 'movies' collection...");
		movieColl = database.getCollection("movies");
		movieColl.setObjectClass(Movie.class);
		
		System.out.println("Database Complete.");
	}
	
	public boolean add(Movie movie) {
		if(!doesExist(movie)) {
			movieColl.insert(movie);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean doesExist(Movie movie) {
		BasicDBObject query = new BasicDBObject("name", movie.getName());
		DBCursor cursor = movieColl.find(query);
		
		if(cursor.hasNext()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Movie find(Movie movie) {		
		BasicDBObject query = new BasicDBObject("name", movie.getName());
		DBCursor cursor = movieColl.find(query);
		
		if(cursor.hasNext()) {
			return (Movie) cursor.next();
		} else {
			return null;
		}
	}
	
	public void addAll(ArrayList<Movie> movies) {
		for(Movie movie: movies) {
			this.add(movie);
		}
	}
	
	public long count() {
		return movieColl.count();
	}
	
}
