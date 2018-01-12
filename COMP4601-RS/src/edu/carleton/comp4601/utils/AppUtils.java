package edu.carleton.comp4601.utils;

import edu.carleton.comp4601.dao.ConfigCollection;
import edu.carleton.comp4601.model.Config;

public class AppUtils {
	
	public static boolean isContextInitialized() {
		Config query = new Config();
		query.setKey("contextInitialized");
		
		Config context = ConfigCollection.getInstance().find(query);
		
		if(context.getValue() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isDatabaseMigrated() {
		Config query = new Config();
		query.setKey("dataMigrated");
		
		Config context = ConfigCollection.getInstance().find(query);
		
		if(context.getValue() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
