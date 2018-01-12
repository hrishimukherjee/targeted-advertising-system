package edu.carleton.comp4601.computer;

import java.util.List;

import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.User;

public class FeatureComputation {
	
	public static void computeUserFeatures() {
		System.out.println("x----------------------x");
		System.out.println("USER FEATURE COMPUTATION INITIATING...");
		System.out.println();
		
		// Retrieve ALL USERS
		List<User> allUsers = UserCollection.getInstance().findAll();
		System.out.println("Number of Users: " + allUsers.size());
		
		System.out.println();
		System.out.println("Computing Reliability...");
		
		FeatureComputer feature = new ReliabilityComputer();
		feature.computeFeatureForAll(allUsers);
		
		System.out.println("Done.");
		
		System.out.println();
		System.out.println("Computing Optimism...");
		
		feature = new OptimismComputer();
		feature.computeFeatureForAll(allUsers);
		
		System.out.println("Done.");
		
		// Update all users in DB
		UserCollection usersDB = UserCollection.getInstance();
		
		System.out.println("\nUpdating in DB...");
		for(User user: allUsers) {
			usersDB.update(user);
		}
		
		System.out.println();
		System.out.println("USER FEATURE COMPUTATION COMPLETE!");
		System.out.println("x----------------------x");
	}

}
