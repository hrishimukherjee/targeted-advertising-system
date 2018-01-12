package edu.carleton.comp4601.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

import edu.carleton.comp4601.utils.Constants;

public class Advertisement implements DBObject {
	private String title;
	private String caption;
	private String visual;
	private Integer optimismClass;
	private Integer reliabilityClass;
	
	//MongoDB Keys
	private static final String TITLE = "title";
	private static final String CAPTION = "caption";
	private static final String VISUAL = "visual";
	private static final String OPTIMISM_CLASS = "optimismClass";
	private static final String RELIABILITY_CLASS = "reliabilityClass";
	
	public Advertisement() {
		
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getVisual() {
		return this.visual;
	}
	
	public void setVisual(String visual) {
		this.visual = visual;
	}
	
	public Integer getOptimismClass() { return this.optimismClass; }
	public Integer getReliabilityClass() { return this.reliabilityClass; }
	
	public void setOptimismClass(Integer optimismClass) { this.optimismClass = optimismClass; }
	public void setReliabilityClass(Integer reliabilityClass) { this.reliabilityClass = reliabilityClass; }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("\nAd: " + this.getTitle() + "; \n");
		result.append("Caption: " + this.getCaption() + "; \n");
		result.append("Visual: " + this.getVisual() + "; \n");
		
		if(this.getOptimismClass() != null) {
			if(this.getOptimismClass() == Constants.HIGH_OPTIMISM) {
				result.append("Optimism Class: HIGH; \n");
			} else if(this.getOptimismClass() == Constants.LOW_OPTIMISM) {
				result.append("Optimism Class: LOW; \n");
			}
		} else {
			result.append("Optimism Class: null; \n");
		}
		
		if(this.getReliabilityClass() != null) {
			if(this.getReliabilityClass() == Constants.HIGH_RELIABILITY) {
				result.append("Reliability Class: HIGH; \n");
			} else if(this.getReliabilityClass() == Constants.LOW_RELIABILITY) {
				result.append("Reliability Class: LOW; \n");
			}
		} else {
			result.append("Reliability Class: null; \n");
		}
		
		return result.toString();
	}

	/*
	 * MONGO FUNCTIONS
	 */
	@Override
	public boolean containsField(String keys) {
		if(keys.equals(TITLE) || keys.equals(CAPTION)
				|| keys.equals(VISUAL) || keys.equals(OPTIMISM_CLASS)
				|| keys.equals(RELIABILITY_CLASS)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsKey(String keys) {
		if(keys.equals(TITLE) || keys.equals(CAPTION)
				|| keys.equals(VISUAL) || keys.equals(OPTIMISM_CLASS)
				|| keys.equals(RELIABILITY_CLASS)) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object get(String keys) {
		if(keys.equals(TITLE)) {
			return getTitle();
		} else if(keys.equals(CAPTION)) {
			return getCaption();
		} else if(keys.equals(VISUAL)) {
			return getVisual();
		} else if(keys.equals(OPTIMISM_CLASS)) {
			return getOptimismClass();
		} else if(keys.equals(RELIABILITY_CLASS)) {
			return getReliabilityClass();
		}
		
		return null;
	}

	@Override
	public Set<String> keySet() {
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(TITLE);
		keySet.add(CAPTION);
		keySet.add(VISUAL);
		keySet.add(OPTIMISM_CLASS);
		keySet.add(RELIABILITY_CLASS);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		if(key.equals(TITLE)) {
			setTitle((String) value);
		} else if(key.equals(CAPTION)) {
			setCaption((String) value);
		} else if(key.equals(VISUAL)) {
			setVisual((String) value);
		} else if(key.equals(OPTIMISM_CLASS)) {
			setOptimismClass((Integer) value);
		} else if(key.equals(RELIABILITY_CLASS)) {
			setReliabilityClass((Integer) value);
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
		if(key.equals(TITLE)) {
			setTitle(null);
		} else if(key.equals(CAPTION)) {
			setCaption(null);
		} else if(key.equals(VISUAL)) {
			setVisual(null);
		} else if(key.equals(OPTIMISM_CLASS)) {
			setOptimismClass(null);
		} else if(key.equals(RELIABILITY_CLASS)) {
			setReliabilityClass(null);
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
