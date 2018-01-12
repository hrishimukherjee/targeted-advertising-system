package edu.carleton.comp4601.computer;

import java.util.List;

import edu.carleton.comp4601.dao.ReviewCollection;
import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.Review;
import edu.carleton.comp4601.model.User;

public class OptimismComputer implements FeatureComputer {
	
	@Override
	public void computeFeature(User user) {
		List<Review> searchResults = ReviewCollection.getInstance().findByUser(user);
		
		if(searchResults.size() > 0) {
			double totalScore = 0;
			
			for(Review review: searchResults) {
				totalScore = totalScore + review.getScore();
			}
			
			double optimism = totalScore/(searchResults.size());
			
			user.setOptimism(optimism);
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
