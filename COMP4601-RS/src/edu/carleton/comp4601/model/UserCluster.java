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
public class UserCluster implements DBObject {
	private String name;
	private Integer cluster;
	
	/*
	 * MONGO DB KEYS
	 */
	private static final String NAME = "name";
	private static final String CLUSTER = "cluster";

	public UserCluster() {
		this.cluster = -1;
	}
	
	public String getName() { return this.name; }
	public Integer getCluster() { return this.cluster; }
	
	public void setName(String name) { this.name = name; }
	public void setCluster(Integer cluster) { this.cluster = cluster; }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("User: " + this.getName() + "; ");
		result.append("Cluster: " + this.getCluster() + "; ");
		
		return result.toString();
	}
	
	/*
	 * MONGO FUNCTIONS
	 */
	@Override
	public boolean containsField(String key) {
		//System.out.println("containsField()");
		
		if(key.equals(NAME) || key.equals(CLUSTER)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsKey(String key) {
		//System.out.println("containsKey(" + key + ")");
		
		if(key.equals(NAME) || key.equals(CLUSTER)) {
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
		} else if(key.equals(CLUSTER)) {
			return getCluster();
		} else {
			return null;
		}
	}

	@Override
	public Set<String> keySet() {
		//System.out.println("keySet()");
		
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(NAME);
		keySet.add(CLUSTER);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		//System.out.println("put(" + key + ", " + value.getClass() + ")");
		
		if(key.equals(NAME)) {
			setName((String) value);
		} else if(key.equals(CLUSTER)) {
			setCluster((Integer) value);
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
		} else if(key.equals(CLUSTER)) {
			setCluster(null);
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
