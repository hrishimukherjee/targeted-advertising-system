package edu.carleton.comp4601.computer;

import java.util.List;

import edu.carleton.comp4601.model.User;

public interface FeatureComputer {
	
	public void computeFeature(User user);
	public void computeFeatureForAll(List<User> user);
	
}
