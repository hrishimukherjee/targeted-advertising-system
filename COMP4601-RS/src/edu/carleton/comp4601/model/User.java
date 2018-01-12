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
public class User implements DBObject {
	private String name;
	private Double optimism;
	private Double reliability;
	
	/*
	 * MONGO DB KEYS
	 */
	private static final String NAME = "name";
	private static final String OPTIMISM = "optimism";
	private static final String RELIABILITY = "reliability";

	public User() {
		this.optimism = null;
		this.reliability = null;
	}
	
	public String getName() { return this.name; }
	public Double getOptimism() { return this.optimism; }
	public Double getReliability() { return this.reliability; }
	
	public void setName(String name) { this.name = name; }
	public void setOptimism(Double optimism) { this.optimism = optimism; }
	public void setReliability(Double reliability) { this.reliability = reliability; }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("User: " + this.getName() + "; ");
		result.append("Optimism: " + this.getOptimism() + "; ");
		result.append("Reliability: " + this.getReliability() + ";");
		
		return result.toString();
	}
	
	/*
	 * MONGO FUNCTIONS
	 */
	@Override
	public boolean containsField(String key) {
		//System.out.println("containsField()");
		
		if(key.equals(NAME) || key.equals(OPTIMISM) || 
				key.equals(RELIABILITY)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsKey(String key) {
		//System.out.println("containsKey(" + key + ")");
		
		if(key.equals(NAME) || key.equals(OPTIMISM) || 
				key.equals(RELIABILITY)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object get(String key) {
		//System.out.println("get(" + key + ")");
		
		if(key.equals(NAME)) {
			return getName();
		} else if(key.equals(OPTIMISM)) {
			return getOptimism();
		} else if(key.equals(RELIABILITY)) {
			return getReliability();
		} else {
			return null;
		}
	}

	@Override
	public Set<String> keySet() {
		//System.out.println("keySet()");
		
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(NAME);
		keySet.add(OPTIMISM);
		keySet.add(RELIABILITY);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		//System.out.println("put(" + key + ", " + value.getClass() + ")");
		
		if(key.equals(NAME)) {
			setName((String) value);
		} else if(key.equals(OPTIMISM)) {
			setOptimism((Double) value);
		} else if(key.equals(RELIABILITY)) {
			setReliability((Double) value);
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
		
		if(key.equals(NAME)) {
			setName(null);
		} else if(key.equals(OPTIMISM)) {
			setOptimism(null);
		} else if(key.equals(RELIABILITY)) {
			setReliability(null);
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
