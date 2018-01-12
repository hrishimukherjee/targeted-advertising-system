package edu.carleton.comp4601.computer;

import java.util.List;

import edu.carleton.comp4601.dao.ReviewCollection;
import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.Review;
import edu.carleton.comp4601.model.User;

public class ReliabilityComputer implements FeatureComputer {
	
	@Override
	public void computeFeature(User user) {
		List<Review> searchResults = ReviewCollection.getInstance().findByUser(user);
	
		if(searchResults.size() > 0) {
			double totalHelpfulness = 0;
			
			for(Review review: searchResults) {
				totalHelpfulness = totalHelpfulness + review.getHelpfulness();
			}
			
			double reliability = totalHelpfulness/(searchResults.size());
		
			user.setReliability(reliability);
		} else {
			//System.out.println("No Reviews Found!");
		}
	}
	
	@Override
	public void computeFeatureForAll(List<User> users) {
		for(User user: users) {
			computeFeature(user);
		}
	}

}
