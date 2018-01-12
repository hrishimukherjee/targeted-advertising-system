package edu.carleton.comp4601.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.BSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import edu.carleton.comp4601.utils.Constants;

@XmlRootElement
public class Cluster implements DBObject {
	private Integer cluster;
	private Integer optimismClass;
	private Integer reliabilityClass;
	
	/*
	 * MONGO DB KEYS
	 */
	private static final String CLUSTER = "cluster";
	private static final String OPTIMISM_CLASS = "optimismClass";
	private static final String RELIABILITY_CLASS = "reliabilityClass";

	public Cluster() {
		this.cluster = -1;
	}
	
	public Integer getCluster() { return this.cluster; }
	public Integer getOptimismClass() { return this.optimismClass; }
	public Integer getReliabilityClass() { return this.reliabilityClass; }
	
	public void setCluster(Integer cluster) { this.cluster = cluster; }
	public void setOptimismClass(Integer optimismClass) { this.optimismClass = optimismClass; }
	public void setReliabilityClass(Integer reliabilityClass) { this.reliabilityClass = reliabilityClass; }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("Cluster: " + this.getCluster() + "; ");
		
		if(this.getOptimismClass() != null) {
			if(this.getOptimismClass() == Constants.HIGH_OPTIMISM) {
				result.append("Optimism Class: HIGH; ");
			} else if(this.getOptimismClass() == Constants.LOW_OPTIMISM) {
				result.append("Optimism Class: LOW; ");
			}
		}
		
		if(this.getReliabilityClass() != null) {
			if(this.getReliabilityClass() == Constants.HIGH_RELIABILITY) {
				result.append("Reliability Class: HIGH; ");
			} else if(this.getReliabilityClass() == Constants.LOW_RELIABILITY) {
				result.append("Reliability Class: LOW; ");
			}
		}
		
		return result.toString();
	}
	
	/*
	 * MONGO FUNCTIONS
	 */
	@Override
	public boolean containsField(String key) {
		//System.out.println("containsField()");
		
		if(key.equals(CLUSTER) || key.equals(OPTIMISM_CLASS) ||
				key.equals(RELIABILITY_CLASS)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsKey(String key) {
		//System.out.println("containsKey(" + key + ")");
		
		if(key.equals(CLUSTER) || key.equals(OPTIMISM_CLASS) ||
				key.equals(RELIABILITY_CLASS)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object get(String key) {
		//System.out.println("get(" + key + ")");
		
		if(key.equals(CLUSTER)) {
			return getCluster();
		} else if(key.equals(OPTIMISM_CLASS)) {
			return getOptimismClass();
		} else if(key.equals(RELIABILITY_CLASS)) {
			return getReliabilityClass();
		} else {
			return null;
		}
	}

	@Override
	public Set<String> keySet() {
		//System.out.println("keySet()");
		
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(CLUSTER);
		keySet.add(OPTIMISM_CLASS);
		keySet.add(RELIABILITY_CLASS);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		//System.out.println("put(" + key + ", " + value.getClass() + ")");
		
		if(key.equals(CLUSTER)) {
			setCluster((Integer) value);
		} else if(key.equals(OPTIMISM_CLASS)) {
			setOptimismClass((Integer) value);
		} else if(key.equals(RELIABILITY_CLASS)) {
			setReliabilityClass((Integer) value);
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
		
		if(key.equals(CLUSTER)) {
			setCluster(null);
		} else if(key.equals(OPTIMISM_CLASS)) {
			setOptimismClass(null);
		} else if(key.equals(RELIABILITY_CLASS)) {
			setReliabilityClass(null);
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
