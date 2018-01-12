package edu.carleton.comp4601.clustering;

import java.util.List;

import edu.carleton.comp4601.dao.UserClusterCollection;
import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.model.UserCluster;

public class Clustering {
	
	public static void execute() {
		System.out.println("x----------------------x");
		System.out.println("USER CLUSTERING INITIATING...");
		System.out.println();
		
		System.out.println("Retrieving All Users...");
		List<User> allUsers = UserCollection.getInstance().findAll();
		
		KMeans clustering = new KMeans(4, 2, allUsers);
		List<UserCluster> clusteredUsers = clustering.execute();
		
		System.out.println("Clustered Users: " + clusteredUsers.size());
		
		System.out.println("Adding to DB...");
		UserClusterCollection collection = UserClusterCollection.getInstance();
		
		for(UserCluster user: clusteredUsers) {
			collection.add(user);
		} 
		
		System.out.println();
		System.out.println("USER CLUSTERING COMPLETE!");
		System.out.println("x----------------------x");
	}

}
