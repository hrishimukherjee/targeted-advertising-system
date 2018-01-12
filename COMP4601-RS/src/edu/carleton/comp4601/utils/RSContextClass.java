package edu.carleton.comp4601.utils;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mongodb.MongoClient;

import edu.carleton.comp4601.dao.ConfigCollection;
import edu.carleton.comp4601.migration.DatabaseMigration;
import edu.carleton.comp4601.model.Config;

/**
 * This listens for the web application starting
 * and closing.
 * 
 * @author @author Hrishi Mukherjee (100888108) & Yonathan Kidanemariam (100890519).
 * 
 */

public class RSContextClass implements ServletContextListener {

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 * We make sure that we stop the running timer which talks to the registry.
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("CLOSING RS APP...");
	}

	/* 
	 * (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * Any context-param elements in web.xml will have their values saved in the SDAConstants.properties
	 * object which is accessible anywhere within the application.
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("INITIALIING RS APP...");
		
		System.out.println("Verifying if DB '" + Constants.DB_NAME + "' exists...");
		
		MongoClient client = new MongoClient();
		List<String> dbNames = client.getDatabaseNames();
		
		System.out.println("Current DBs: " + dbNames);
		
		final boolean dbExists = dbNames.contains(Constants.DB_NAME);
		
		/*
		 * If DB does not exist then create DB and migrate data.
		 * Else, do nothing.
		 */
		if(!dbExists) {
			System.out.println("DB does not exist.");
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					// Create new configuration settings
					System.out.println("Creating new Configuration Settings...");
					Config property = new Config();
					property.setKey("dataMigrated");
					property.setValue(0);
					
					Config property2 = new Config();
					property2.setKey("contextInitialized");
					property2.setValue(0);
					
					ConfigCollection.getInstance().add(property);
					ConfigCollection.getInstance().add(property2);
					
					System.out.println("Migrating data in the background... Please wait for completion update.");
					
					// Initiate Migration Sequence
					DatabaseMigration.migrate();
					
					// Data has been migrated, update Config
					System.out.println("Updating Migration Property...");
					Config update = new Config();
					update.setKey("dataMigrated");
					update.setValue(1);
					
					ConfigCollection.getInstance().update(update);
					
					System.out.println("Data has been migrated successfully!");
				}
			}).start();
		} else {
			System.out.println("DB already exists! Migration not being done.");
		}
	}

}
