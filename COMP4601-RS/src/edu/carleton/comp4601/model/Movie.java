package edu.carleton.comp4601.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

public class Movie implements DBObject {
	private String name;
	private String genre;
	
	//MongoDB Keys
	private static final String NAME = "name";
	private static final String GENRE = "genre";
	
	public Movie() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("Movie: " + this.getName() + "; ");
		result.append("Genre: " + this.getGenre() + ";");
		
		return result.toString();
	}

	/*
	 * MONGO FUNCTIONS
	 */
	@Override
	public boolean containsField(String keys) {
		if(keys.equals(NAME) || keys.equals(GENRE)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsKey(String keys) {
		if(keys.equals(NAME) || keys.equals(GENRE)) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object get(String keys) {
		if(keys.equals(NAME)) {
			return getName();
		} else if(keys.equals(GENRE)) {
			return getGenre();
		}
		
		return null;
	}

	@Override
	public Set<String> keySet() {
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(NAME);
		keySet.add(GENRE);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		if(key.equals(NAME)) {
			setName((String) value);
		} else if(key.equals(GENRE)) {
			setGenre((String) value);
		}
		
		return null;
	}

	@Override
	public void putAll(BSONObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object removeField(String key) {
		if(key.equals(NAME)) {
			setName(null);
		} else if(key.equals(GENRE)) {
			setGenre(null);
		}
		
		return null;
	}

	@Override
	public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markAsPartialObject() {
		// TODO Auto-generated method stub
		
	}
	
	
}
