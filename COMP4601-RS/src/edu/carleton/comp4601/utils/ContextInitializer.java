package edu.carleton.comp4601.utils;

import edu.carleton.comp4601.classification.Classifier;
import edu.carleton.comp4601.clustering.Clustering;
import edu.carleton.comp4601.computer.FeatureComputation;
import edu.carleton.comp4601.dao.ConfigCollection;
import edu.carleton.comp4601.model.Config;

public class ContextInitializer {
	
	public static void initialize() {
		System.out.println("Initializing Context...");
		
		System.out.println("PHASE 1: Compute User Features.");
		FeatureComputation.computeUserFeatures();
		
		System.out.println("PHASE 2: Create Clusters.");
		Clustering.execute();
		
		System.out.println("PHASE 3: Classify Clusters.");
		Classifier.classify();
		
		System.out.println("PHASE 4: Insert Advertisements.");
		AdvertismentFactory.createAds();
		
		System.out.println("Updating DB flag...");
		Config update = new Config();
		update.setKey("contextInitialized");
		update.setValue(1);
		
		ConfigCollection.getInstance().update(update);
		
		System.out.println("Context Initialized!");
	}
	
}
