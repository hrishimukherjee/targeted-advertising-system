package edu.carleton.comp4601.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.BSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

@XmlRootElement
public class Review implements DBObject {
	private int id;
	private String userName;
	private String movieName;
	private Double score;
	private Double helpfulness;
	
	/*
	 * MONGO DB KEYS
	 */
	private static final String ID = "id";
	private static final String USER_NAME = "userName";
	private static final String MOVIE_NAME = "movieName";
	private static final String SCORE = "score";
	private static final String HELPFULNESS = "helpfulness";

	public Review() {
		this.id = 0;
		this.score = null;
		this.helpfulness = null;
	}
	
	public int getId() { return this.id; }
	public String getUserName() { return this.userName; }
	public String getMovieName() { return this.movieName; }
	public Double getScore() { return this.score; }
	public Double getHelpfulness() { return this.helpfulness; }
	
	public void setId(int id) { this.id = id; }
	public void setUserName(String userName) { this.userName = userName; }
	public void setMovieName(String movieName) { this.movieName = movieName; }
	public void setScore(Double score) { this.score = score; }
	public void setHelpfulness(Double helpfulness) { this.helpfulness = helpfulness; }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("Review: " + 
				this.getId() + "-" +
				this.getMovieName() +  "-" + 
				this.getUserName() + "; ");
		result.append("Score: " + this.getScore() + "; ");
		result.append("Helpfulness: " + this.getHelpfulness() + ";");
		
		return result.toString();
	}
	
	/*
	 * MONGO FUNCTIONS
	 */
	@Override
	public boolean containsField(String key) {
		//System.out.println("containsField()");
		
		if(key.equals(ID) || key.equals(USER_NAME) || 
				key.equals(MOVIE_NAME) || key.equals(SCORE) || 
				key.equals(HELPFULNESS)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsKey(String key) {
		//System.out.println("containsKey(" + key + ")");
		
		if(key.equals(ID) || key.equals(USER_NAME) || 
				key.equals(MOVIE_NAME) || key.equals(SCORE) || 
				key.equals(HELPFULNESS)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object get(String key) {
		//System.out.println("get(" + key + ")");
		
		if(key.equals(ID)) {
			return getId();
		} else if(key.equals(USER_NAME)) {
			return getUserName();
		} else if(key.equals(MOVIE_NAME)) {
			return getMovieName();
		} else if(key.equals(SCORE)) {
			return getScore();
		} else if(key.equals(HELPFULNESS)) {
			return getHelpfulness();
		} else {
			return null;
		}
	}

	@Override
	public Set<String> keySet() {
		//System.out.println("keySet()");
		
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(ID);
		keySet.add(USER_NAME);
		keySet.add(MOVIE_NAME);
		keySet.add(SCORE);
		keySet.add(HELPFULNESS);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		//System.out.println("put(" + key + ", " + value.getClass() + ")");
		
		if(key.equals(ID)) {
			setId((Integer) value);
		} else if(key.equals(USER_NAME)) {
			setUserName((String) value);
		} else if(key.equals(MOVIE_NAME)) {
			setMovieName((String) value);
		} else if(key.equals(SCORE)) {
			setScore((Double) value);
		} else if(key.equals(HELPFULNESS)) {
			setHelpfulness((Double) value);
		} 
		
		return value;
	}

	@Override
	public void putAll(BSONObject arg0) {
		//System.out.println("putAllBSON()");
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAll(Map arg0) {
		//System.out.println("putAllMap()");
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object removeField(String key) {
		//System.out.println("removeField(" + key + ")");
		
		if(key.equals(ID)) {
			setId(-1);
		} else if(key.equals(USER_NAME)) {
			setUserName(null);
		} else if(key.equals(MOVIE_NAME)) {
			setMovieName(null);
		} else if(key.equals(SCORE)) {
			setScore(null);
		} else if(key.equals(HELPFULNESS)) {
			setHelpfulness(null);
		}
		
		return null;
	}

	@Override
	public Map toMap() {
		//System.out.println("toMap()");
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPartialObject() {
		//System.out.println("isPartialObject()");
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markAsPartialObject() {
		//System.out.println("markAsPartialObject()");
		
		// TODO Auto-generated method stub
		
	}
}
