package edu.carleton.comp4601.migration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.utils.Constants;

public class UserMigration {
	
	public static List<User> extractUsers() {
		List<User> users = new ArrayList<User>();
		
		File folder = new File(Constants.RAW_DATA_PATH_USERS);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println("Number of Files: " + listOfFiles.length);
		System.out.println("Number of Users: " + users.size());
		
		System.out.println("Creating Users...");
		
		for(int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				
				// Clean Up Name
				fileName = fileName.replaceAll(".html", "");
				
				// Create New User
				User user = new User();
				user.setName(fileName);
				
				users.add(user);
			}
		}
		
		System.out.println("Number of Users: " + users.size());
		
		return users;
	}
	
	public static void migrate() {
		System.out.println();
		System.out.println("x----------------------x");
		System.out.println("Extracting Users from Source...");
		System.out.println();
		
		// Extract users from source
		List<User> users = extractUsers();
		
		// Add each user to Mongo DB
		System.out.println();
		System.out.println("Adding Users to DB...");
		
		UserCollection collection = UserCollection.getInstance();
		
		for(User user: users) {
			collection.add(user);
		}
		
		System.out.println("Users migrated successfully!");
		System.out.println("x----------------------x");
	}

}
